/*
 * diffuse_double_layer.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:56 by COMSOL 6.3.0.290. */
public class diffuse_double_layer {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electrodeposition_Module\\General_Electrochemistry");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics("es").field("electricpotential").field("phi");
    model.component("comp1").physics().create("tds", "DilutedSpecies", "geom1");
    model.component("comp1").physics("tds").prop("TransportMechanism").set("Convection", "0");
    model.component("comp1").physics("tds").prop("TransportMechanism").set("Migration", "1");
    model.component("comp1").physics("tds").prop("ShapeProperty").set("order_concentration", "2");
    model.component("comp1").physics("tds").field("concentration").field("cA");
    model.component("comp1").physics("tds").field("concentration").component(new String[]{"cA", "cX"});

    model.component("comp1").multiphysics().create("pc1", "PotentialCoupling", 1);
    model.component("comp1").multiphysics("pc1").set("PotentialSource_physics", "es");
    model.component("comp1").multiphysics("pc1").set("PotentialDestination_physics", "tds");
    model.component("comp1").multiphysics("pc1").selection().all();
    model.component("comp1").multiphysics().create("scdc1", "SpaceChargeDensityCoupling", 1);
    model.component("comp1").multiphysics("scdc1").set("SpaceChargeDensitySource_physics", "tds");
    model.component("comp1").multiphysics("scdc1").set("SpaceChargeDensityDestination_physics", "es");
    model.component("comp1").multiphysics("scdc1").selection().all();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/es", true);
    model.study("std1").feature("stat").setSolveFor("/physics/tds", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/pc1", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/scdc1", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("T0", "25[degC]", "\u6e29\u5ea6");
    model.param().set("V_therm", "R_const*T0/F_const", "\u70ed\u7535\u538b");
    model.param().set("DA", "1e-9[m^2/s]", "\u6269\u6563\u7cfb\u6570\uff0c\u9633\u79bb\u5b50");
    model.param().set("DX", "DA", "\u6269\u6563\u7cfb\u6570\uff0c\u9634\u79bb\u5b50");
    model.param().set("cA_bulk", "10[mol/m^3]", "\u9633\u79bb\u5b50\u672c\u4f53\u6d53\u5ea6");
    model.param().set("cX_bulk", "cA_bulk", "\u9634\u79bb\u5b50\u672c\u4f53\u6d53\u5ea6");
    model.param().set("zA", "+1", "\u9633\u79bb\u5b50\u7535\u8377");
    model.param().set("zX", "-1", "\u9634\u79bb\u5b50\u7535\u8377");
    model.param().set("Istr_bulk", "0.5*((zA^2+zX^2)*cA_bulk)", "\u672c\u4f53\u79bb\u5b50\u5f3a\u5ea6");
    model.param().set("eps_H2O", "78.5", "\u6c34\u7684\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570");
    model.param()
         .set("xD", "sqrt(epsilon0_const*eps_H2O*V_therm/(2*F_const*Istr_bulk))", "\u5fb7\u62dc\u957f\u5ea6");
    model.param().set("xS", "0.2[nm]", "Stern \u5c42\u539a\u5ea6");
    model.param().set("L_cell", "xD*10", "\u7535\u6c60\u957f\u5ea6");
    model.param().set("h_max", "L_cell/20", "\u6700\u5927\u7f51\u683c\u5355\u5143\u5927\u5c0f");
    model.param()
         .set("h_max_surf", "xD/100", "\u6700\u5927\u7f51\u683c\u5355\u5143\u5927\u5c0f\uff08\u7535\u6781\uff09");
    model.param()
         .set("Cd_GCS", "epsilon0_const/(xD/eps_H2O+xS/eps_S)", "\u5355\u4f4d\u9762\u79ef\u7535\u5bb9\uff08GCS \u7406\u8bba\uff0c\u4f4e\u7535\u4f4d\u6781\u9650\uff09");
    model.param().set("phiM", "1[mV]", "\u7535\u6781\u7535\u538b vs. PZC");
    model.param().set("eps_S", "10", "Stern \u5c42\u7684\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("deltaphi", "phiM-phi", "\u7535\u6781-OHP \u7684\u7535\u4f4d\u5dee");
    model.component("comp1").variable("var1")
         .set("rho_surf", "epsilon0_const*eps_S*deltaphi/xS", "\u8868\u9762\u7535\u8377\u5bc6\u5ea6");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "L_cell", 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("es").create("ccnf1", "ChargeConservationFluid", 1);
    model.component("comp1").physics("es").feature("ccnf1").selection().all();
    model.component("comp1").physics("es").feature("ccnf1").set("epsilonr_mat", "userdef");
    model.component("comp1").physics("es").feature("ccnf1")
         .set("epsilonr", new String[]{"eps_H2O", "0", "0", "0", "eps_H2O", "0", "0", "0", "eps_H2O"});
    model.component("comp1").physics("es").create("sfcd1", "SurfaceChargeDensity", 0);
    model.component("comp1").physics("es").feature("sfcd1").selection().set(1);
    model.component("comp1").physics("es").feature("sfcd1").set("rhoqs", "rho_surf");
    model.component("comp1").physics("es").create("gnd1", "Ground", 0);
    model.component("comp1").physics("es").feature("gnd1").selection().set(2);
    model.component("comp1").physics("tds").feature("sp1").setIndex("z", "zA", 0);
    model.component("comp1").physics("tds").feature("sp1").setIndex("z", "zX", 1);
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_cA", new String[]{"DA", "0", "0", "0", "DA", "0", "0", "0", "DA"});
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_cX", new String[]{"DX", "0", "0", "0", "DX", "0", "0", "0", "DX"});
    model.component("comp1").physics("tds").feature("init1").setIndex("initc", "cA_bulk", 0);
    model.component("comp1").physics("tds").feature("init1").setIndex("initc", "cX_bulk", 1);
    model.component("comp1").physics("tds").create("conc1", "Concentration", 0);
    model.component("comp1").physics("tds").feature("conc1").selection().set(2);
    model.component("comp1").physics("tds").feature("conc1").setIndex("species", true, 0);
    model.component("comp1").physics("tds").feature("conc1").setIndex("c0", "cA_bulk", 0);
    model.component("comp1").physics("tds").feature("conc1").setIndex("species", true, 1);
    model.component("comp1").physics("tds").feature("conc1").setIndex("c0", "cX_bulk", 1);

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T0"}});

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("edg1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmax", "h_max");
    model.component("comp1").mesh("mesh1").feature("edg1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size2").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size2").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size2").set("hmax", "h_max_surf");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "T0", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "K", 0);
    model.study("std1").feature("stat").setIndex("pname", "T0", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "K", 0);
    model.study("std1").feature("stat").setIndex("pname", "phiM", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(1,1,10)", 0);
    model.study("std1").feature("stat").setIndex("punit", "mV", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").label("\u7535\u52bf (es)");
    model.result("pg1").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg1").feature().create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr1").set("solutionparams", "parent");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "x");
    model.result("pg1").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr1").set("data", "parent");
    model.result("pg1").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr1").selection().set(1);
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u6d53\u5ea6\uff0c\u6240\u6709\u7269\u8d28");
    model.result("pg2").label("\u6d53\u5ea6\uff0c\u6240\u6709\u7269\u8d28 (tds)");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "x");
    model.result("pg2").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr1").selection().set(1);
    model.result("pg2").feature("lngr1").set("expr", new String[]{"cA"});
    model.result("pg2").feature("lngr1").label("\u7269\u8d28 A");
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").feature("lngr1").set("autosolution", false);
    model.result("pg2").feature("lngr1").set("autoexpr", false);
    model.result("pg2").feature("lngr1").set("autodescr", false);
    model.result("pg2").feature("lngr1").set("legendprefix", "A ");
    model.result("pg2").feature("lngr1").set("descractive", true);
    model.result("pg2").feature("lngr1").set("descr", "\u6d53\u5ea6");
    model.result("pg2").create("lngr2", "LineGraph");
    model.result("pg2").feature("lngr2").set("xdata", "expr");
    model.result("pg2").feature("lngr2").set("xdataexpr", "x");
    model.result("pg2").feature("lngr2").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr2").selection().set(1);
    model.result("pg2").feature("lngr2").set("expr", new String[]{"cX"});
    model.result("pg2").feature("lngr2").label("\u7269\u8d28 X");
    model.result("pg2").feature("lngr2").set("legend", true);
    model.result("pg2").feature("lngr2").set("autosolution", false);
    model.result("pg2").feature("lngr2").set("autoexpr", false);
    model.result("pg2").feature("lngr2").set("autodescr", false);
    model.result("pg2").feature("lngr2").set("legendprefix", "X ");
    model.result("pg2").feature("lngr2").set("descractive", true);
    model.result("pg2").feature("lngr2").set("descr", "\u6d53\u5ea6");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").label("\u6d53\u5ea6, A (tds)");
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").set("prefixintitle", "");
    model.result("pg3").set("expressionintitle", false);
    model.result("pg3").set("typeintitle", false);
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "x");
    model.result("pg3").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg3").feature("lngr1").selection().set(1);
    model.result("pg3").feature("lngr1").set("expr", new String[]{"cA"});
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").label("\u6d53\u5ea6, X (tds)");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("prefixintitle", "");
    model.result("pg4").set("expressionintitle", false);
    model.result("pg4").set("typeintitle", false);
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "x");
    model.result("pg4").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg4").feature("lngr1").selection().set(1);
    model.result("pg4").feature("lngr1").set("expr", new String[]{"cX"});
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevelinput", "last", 0);
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").run();
    model.result("pg1").feature("lngr1").set("xdataexpr", "x/xD");
    model.result("pg1").feature("lngr1").set("xdatadescractive", true);
    model.result("pg1").feature("lngr1")
         .set("xdatadescr", "\u957f\u5ea6\uff08\u5355\u4f4d\u5fb7\u62dc\u957f\u5ea6\uff09");
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result("pg1").feature("lngr1").set("legendmethod", "manual");
    model.result("pg1").run();
    model.result("pg1").create("ptgr1", "PointGraph");
    model.result("pg1").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg1").feature("ptgr1").set("linewidth", "preference");
    model.result("pg1").feature("ptgr1").selection().set(1);
    model.result("pg1").feature("ptgr1").set("expr", "phiM");
    model.result("pg1").feature("ptgr1").set("xdata", "expr");
    model.result("pg1").feature("ptgr1").set("xdataexpr", "x/xD");
    model.result("pg1").feature("ptgr1").set("xdatadescractive", true);
    model.result("pg1").feature("ptgr1")
         .set("xdatadescr", "\u957f\u5ea6\uff08\u5355\u4f4d\u5fb7\u62dc\u957f\u5ea6\uff09");
    model.result("pg1").feature("ptgr1").set("linestyle", "none");
    model.result("pg1").feature("ptgr1").set("linecolor", "red");
    model.result("pg1").feature("ptgr1").set("linemarker", "point");
    model.result("pg1").feature("ptgr1").set("legend", true);
    model.result("pg1").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevelinput", "last", 0);
    model.result("pg2").set("titletype", "none");
    model.result("pg2").run();
    model.result("pg2").feature("lngr1").set("xdataunit", "nm");
    model.result("pg2").feature("lngr1").set("legendmethod", "manual");
    model.result("pg2").feature("lngr1").setIndex("legends", "A<sup>+</sup> \u6d53\u5ea6", 0);
    model.result("pg2").run();
    model.result("pg2").feature("lngr2").set("xdataunit", "nm");
    model.result("pg2").feature("lngr2").set("legendmethod", "manual");
    model.result("pg2").feature("lngr2").setIndex("legends", "X<sup>-</sup> \u6d53\u5ea6", 0);
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").feature("lngr1").set("linemarker", "cycle");
    model.result("pg3").feature("lngr1").set("markers", 1);
    model.result("pg3").feature("lngr1").set("legend", true);
    model.result("pg3").feature("lngr1").create("col1", "Color");
    model.result("pg3").run();
    model.result("pg3").feature("lngr1").feature("col1").set("expr", "phiM");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").set("legendlayout", "outside");
    model.result("pg4").run();
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("linemarker", "cycle");
    model.result("pg4").feature("lngr1").set("markers", 1);
    model.result("pg4").feature("lngr1").create("col1", "Color");
    model.result("pg4").run();
    model.result("pg4").feature("lngr1").feature("col1").set("expr", "phiM");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").set("legendlayout", "outside");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u8868\u9762\u7535\u8377\u5bc6\u5ea6");
    model.result("pg5").set("titletype", "none");
    model.result("pg5").create("ptgr1", "PointGraph");
    model.result("pg5").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg5").feature("ptgr1").set("linewidth", "preference");
    model.result("pg5").feature("ptgr1").selection().set(1);
    model.result("pg5").feature("ptgr1").set("expr", "rho_surf");
    model.result("pg5").feature("ptgr1").set("xdata", "expr");
    model.result("pg5").feature("ptgr1").set("xdataexpr", "phiM");
    model.result("pg5").feature("ptgr1").set("linestyle", "none");
    model.result("pg5").feature("ptgr1").set("linemarker", "circle");
    model.result("pg5").feature("ptgr1").set("legend", true);
    model.result("pg5").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg5").feature("ptgr1")
         .setIndex("legends", "\u8868\u9762\u7535\u8377\u5bc6\u5ea6\uff08\u4eff\u771f\uff09", 0);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "Cd_GCS*phiM", 0);
    model.result("pg5").feature("glob1")
         .setIndex("descr", "\u8868\u9762\u7535\u8377\u5bc6\u5ea6\uff08\u7406\u8bba\uff09", 0);
    model.result("pg5").feature("glob1").set("xdata", "expr");
    model.result("pg5").feature("glob1").set("xdataexpr", "phiM");
    model.result("pg5").run();
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u7535\u8377\u5bc6\u5ea6 (C/m<sup>2</sup>)");
    model.result("pg5").set("legendpos", "lowerright");
    model.result("pg5").run();

    model.title("\u6269\u6563\u53cc\u7535\u5c42");

    model
         .description("\u672c\u4f8b\u5bf9\u201c\u6269\u6563\u53cc\u7535\u5c42\u201d\u6a21\u578b\u8fdb\u884c\u6269\u5c55\uff0c\u6f14\u793a\u5982\u4f55\u5728\u901a\u8fc7\u8026\u5408\u7684 Nernst-Planck-Poisson \u65b9\u7a0b\u7ec4\u63cf\u8ff0\u7684\u6269\u6563\u53cc\u7535\u5c42\u4e2d\u5f15\u5165\u7535\u8377\u8f6c\u79fb\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("diffuse_double_layer.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
