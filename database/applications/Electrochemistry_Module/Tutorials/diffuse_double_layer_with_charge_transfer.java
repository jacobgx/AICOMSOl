/*
 * diffuse_double_layer_with_charge_transfer.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:55 by COMSOL 6.3.0.290. */
public class diffuse_double_layer_with_charge_transfer {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electrochemistry_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tcd", "TertiaryCurrentDistributionNernstPlanck", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/tcd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("epsilon", "0.001", "\u65e0\u91cf\u7eb2\u5fb7\u62dc\u957f\u5ea6\u5c3a\u5ea6");
    model.param().set("L", "lambdaD/epsilon", "\u7535\u6c60\u957f\u5ea6");
    model.param().set("Dp", "1e-9[m^2/s]", "\u6269\u6563\u7cfb\u6570\uff0c\u6b63\u79bb\u5b50");
    model.param().set("T", "298.15[K]", "\u6e29\u5ea6");
    model.param().set("RT", "R_const*T", "\u6469\u5c14\u6c14\u4f53\u5e38\u6570 * \u6e29\u5ea6");
    model.param().set("Dm", "Dp", "\u6269\u6563\u7cfb\u6570\uff0c\u8d1f\u79bb\u5b50");
    model.param().set("cref", "1[mol/m^3]", "\u53c2\u8003\u79bb\u5b50\u6d53\u5ea6");
    model.param().set("cM", "1[mol/m^3]", "\u91d1\u5c5e\u53c2\u8003\u6d53\u5ea6");
    model.param().set("Z_ch", "1", "\u79bb\u5b50\u7535\u8377");
    model.param().set("alphac", "0.5", "\u9634\u6781\u7535\u8377\u4f20\u9012\u7cfb\u6570");
    model.param().set("alphaa", "1-alphac", "\u9633\u6781\u7535\u8377\u4f20\u9012\u7cfb\u6570");
    model.param().set("jr", "10", "\u65e0\u91cf\u7eb2\u9633\u6781\u53cd\u5e94\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("kc", "10", "\u65e0\u91cf\u7eb2\u9634\u6781\u901f\u7387\u7cfb\u6570");
    model.param().set("delta", "0.025", "\u65e0\u91cf\u7eb2 Stern \u5c42\u539a\u5ea6");
    model.param().set("J", "0.9", "\u65e0\u91cf\u7eb2\u7535\u6c60\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("Kc", "kc*4*Dp/L", "\u9634\u6781\u901f\u7387\u5e38\u6570");
    model.param().set("Ka", "jr*4*Dp*cref/(L*cM)", "\u9633\u6781\u901f\u7387\u5e38\u6570");
    model.param().set("id", "4*Z_ch*F_const*Dp*cref/L", "Nernst \u6781\u9650\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("icell", "J*id", "\u7535\u6c60\u7535\u6d41\u5bc6\u5ea6");
    model.param()
         .set("lambdaD", "sqrt(epsilon0_const*eps_H2O*RT/(2*Z_ch^2*F_const^2*cref))", "\u5fb7\u62dc\u957f\u5ea6");
    model.param().set("lambdaS", "delta*lambdaD", "Stern \u5c42\u539a\u5ea6");
    model.param().set("eps_H2O", "78.5", "\u6c34\u7684\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570");
    model.param().set("eps_S", "10", "Stern \u5c42\u7684\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "L", 1);

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("deltaphi", "tcd.phisext-phil", "\u91d1\u5c5e-\u53cd\u5e94\u5e73\u9762\u52bf\u5dee");
    model.component("comp1").variable("var1")
         .set("rho_s", "epsilon0_const*eps_S*deltaphi/lambdaS", "\u8868\u9762\u7535\u8377\u5bc6\u5ea6");

    model.component("comp1").physics("tcd").prop("SpeciesProperties").set("ChargeTransportModel", "Poisson");
    model.component("comp1").physics("tcd").field("concentration").component(1, "cp");
    model.component("comp1").physics("tcd").field("concentration").component(2, "cm");
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", "Z_ch", 0);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", "-Z_ch", 1);
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cp", new String[]{"Dp", "0", "0", "0", "Dp", "0", "0", "0", "Dp"});
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cm", new String[]{"Dm", "0", "0", "0", "Dm", "0", "0", "0", "Dm"});
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "cref", 0);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "cref", 1);
    model.component("comp1").physics("tcd").create("es1", "ElectrodeSurface", 0);
    model.component("comp1").physics("tcd").feature("es1").selection().set(1);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("Vi0", -1, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("Eeq_mat", "userdef");
    model.component("comp1").physics("tcd").feature("es1").feature("er1")
         .set("ElectrodeKinetics", "ConcentrationDependentKinetics");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("i0", "Ka*F_const*cM*Z_ch");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("alphaa", "alphaa*Z_ch");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("alphac", "alphac*Z_ch");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("CO", "Kc/Ka*cp/cM");
    model.component("comp1").physics("tcd").feature().duplicate("es2", "es1");
    model.component("comp1").physics("tcd").feature("es2").selection().set(2);
    model.component("comp1").physics("tcd").feature("es2").set("BoundaryCondition", "AverageCurrentDensity");
    model.component("comp1").physics("tcd").feature("es2").set("Ial", "icell");
    model.component("comp1").physics("tcd").create("sfcd1", "SurfaceChargeDensity", 0);
    model.component("comp1").physics("tcd").feature("sfcd1").selection().all();
    model.component("comp1").physics("tcd").feature("sfcd1").set("rhos", "rho_s");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().set(1);

    model.component("comp1").physics("tcd").create("gconstr1", "GlobalConstraint", -1);
    model.component("comp1").physics("tcd").feature("gconstr1").set("constraintExpression", "intop1(cm)-(cref*L)");

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmax", "L/20");
    model.component("comp1").mesh("mesh1").feature("edg1").feature().duplicate("size2", "size1");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size2").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size2").selection().all();
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size2").set("hmax", "lambdaD/10");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "epsilon", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "epsilon", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("plistarr", "0.001 0.01 0.1", 0);
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg1").feature("lngr1").set("linewidth", "preference");
    model.result("pg1").feature("lngr1").selection().set(1);
    model.result("pg1").feature("lngr1").set("expr", "(cp+cm)/(2*cref)");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "x/L");
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result("pg1").run();
    model.result("pg1").label("\u65e0\u91cf\u7eb2\u6d53\u5ea6");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").set("titletype", "label");
    model.result("pg1").set("legendpos", "lowerright");
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u65e0\u91cf\u7eb2\u7535\u8377\u5bc6\u5ea6");
    model.result("pg2").set("legendpos", "upperright");
    model.result("pg2").run();
    model.result("pg2").feature("lngr1").set("expr", "(cp-cm)/(2*cref)");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u65e0\u91cf\u7eb2\u7535\u4f4d");
    model.result("pg3").set("legendpos", "lowerright");
    model.result("pg3").run();
    model.result("pg3").feature("lngr1").set("expr", "phil*Z_ch*F_const/(R_const*T)");
    model.result("pg3").run();

    model.title("\u5e26\u7535\u8377\u8f6c\u79fb\u7684\u6269\u6563\u53cc\u7535\u5c42");

    model
         .description("\u7535\u6781-\u7535\u89e3\u8d28\u754c\u9762\u4e0a\u6709\u4e00\u5c42\u8584\u8584\u7684\u7a7a\u95f4\u7535\u8377\u5c42\uff0c\u79f0\u4e3a\u6269\u6563\u53cc\u7535\u5c42\uff0c\u8fd9\u4e2a\u533a\u57df\u901a\u5e38\u4e0d\u6ee1\u8db3\u7535\u4e2d\u6027\u5047\u8bbe\u3002\u5728\u6a21\u62df\u7535\u5316\u5b66\u8d85\u7ea7\u7535\u5bb9\u5668\u548c\u7eb3\u7c73\u7535\u6781\u7b49\u8bbe\u5907\u65f6\uff0c\u53cc\u7535\u5c42\u901a\u5e38\u5177\u6709\u91cd\u8981\u610f\u4e49\u3002\n\n\u201c\u6269\u6563\u53cc\u7535\u5c42\u201d\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u5c06 Nernst-Planck \u65b9\u7a0b\u4e0e\u6cca\u677e\u65b9\u7a0b\u76f8\u8026\u5408\uff0c\u5e76\u6839\u636e Gouy-Chapman-Stern \u6a21\u578b\u6765\u63cf\u8ff0\u6269\u6563\u53cc\u7535\u5c42\u3002\n\n\u8fd9\u4e2a\u4eff\u771f App \u901a\u8fc7\u5f15\u5165\u4e24\u4e2a\u7535\u6781\u6269\u5c55\u4e86\u8fd9\u4e00\u7b80\u5355\u793a\u4f8b\uff0c\u5e76\u8003\u8651\u4e86\u6cd5\u62c9\u7b2c\uff08\u7535\u8377\u8f6c\u79fb\uff09\u7535\u6781\u53cd\u5e94\uff0c\u6c42\u89e3\u4e00\u4e2a\u9644\u52a0\u65b9\u7a0b\u4ee5\u786e\u4fdd\u603b\u7535\u8377\u5b88\u6052\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("diffuse_double_layer_with_charge_transfer.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
