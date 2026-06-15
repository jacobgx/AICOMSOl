/*
 * electroosmotic_mixer.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:09 by COMSOL 6.3.0.290. */
public class electroosmotic_mixer {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Microfluidics_Module\\Micromixers");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics().create("tds", "DilutedSpecies", "geom1");
    model.component("comp1").physics().create("ec", "ConductiveMedia", "geom1");

    model.component("comp1").multiphysics().create("rfd1", "ReactingFlowDS", 2);
    model.component("comp1").multiphysics("rfd1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("rfd1").set("Species_physics", "tds");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/tds", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ec", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/rfd1", true);

    model.param().set("U0", "0.1[mm/s]");
    model.param().descr("U0", "\u5e73\u5747\u6d41\u5165\u901f\u5ea6");
    model.param().set("sigma_w", "0.11845[S/m]");
    model.param().descr("sigma_w", "\u79bb\u5b50\u6eb6\u6db2\u7684\u7535\u5bfc\u7387");
    model.param().set("eps_r", "80.2");
    model.param().descr("eps_r", "\u6d41\u4f53\u7684\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570");
    model.param().set("zeta", "-0.1[V]");
    model.param().descr("zeta", "Zeta \u7535\u4f4d");
    model.param().set("V0", "0.1[V]");
    model.param().descr("V0", "\u4ea4\u6d41\u7535\u52bf\u7684\u6700\u5927\u503c");
    model.param().set("omega", "2*pi[rad]*8[Hz]");
    model.param().descr("omega", "\u4ea4\u6d41\u7535\u52bf\u7684\u89d2\u9891\u7387");
    model.param().set("t", "0[s]");
    model.param().descr("t", "\u542f\u52a8\u65f6\u95f4");
    model.param().set("D", "1e-11[m^2/s]");
    model.param().descr("D", "\u6eb6\u6db2\u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("c0", "1[mol/m^3]");
    model.param().descr("c0", "\u521d\u59cb\u6d53\u5ea6");

    model.func().create("step1", "Step");
    model.func("step1").set("smooth", "0.1e-6");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{80, 10});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", 15);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("pare1", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("pare1").selection("edge").set("c1", 1, 2, 3, 4);
    model.component("comp1").geom("geom1").feature("pare1").setIndex("param", 0.47, 0);
    model.component("comp1").geom("geom1").feature("pare1").setIndex("param", 0.53, 1);
    model.component("comp1").geom("geom1").run("pare1");
    model.component("comp1").geom("geom1").create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("c2").set("r", 5);
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("co1", "Compose");
    model.component("comp1").geom("geom1").feature("co1").selection("input").set("c2", "pare1", "r1");
    model.component("comp1").geom("geom1").feature("co1").set("formula", "(r1+pare1)-c2");
    model.component("comp1").geom("geom1").feature("co1").set("intbnd", false);
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1e3[kg/m^3]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("dynamicviscosity", new String[]{"1e-3[Pa*s]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"sigma_w"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"eps_r"});

    model.component("comp1").physics("spf").prop("ShapeProperty").set("order_fluid", 2);
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(1);
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "U0");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().set(6);
    model.component("comp1").physics("spf").feature("wallbc1").set("BoundaryCondition", "ElectroosmoticVelocity");
    model.component("comp1").physics("spf").feature("wallbc1")
         .set("E", new String[]{"ec.Ex*sin(omega*t)", "ec.Ey*sin(omega*t)", "0"});
    model.component("comp1").physics("spf").feature("wallbc1").set("ElectroosmoticOption", "BuiltinExpression");
    model.component("comp1").physics("spf").feature("wallbc1").set("zeta", "zeta");
    model.component("comp1").physics("spf").feature("wallbc1").set("epsilonr", "eps_r");
    model.component("comp1").physics("tds").prop("ShapeProperty").set("order_concentration", 2);
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_c", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp1").physics("tds").create("conc1", "Concentration", 1);
    model.component("comp1").physics("tds").feature("conc1").selection().set(1);
    model.component("comp1").physics("tds").feature("conc1").setIndex("species", true, 0);
    model.component("comp1").physics("tds").feature("conc1").setIndex("c0", "c0*step1(y[1/m])", 0);
    model.component("comp1").physics("tds").create("out1", "Outflow", 1);
    model.component("comp1").physics("tds").feature("out1").selection().set(6);
    model.component("comp1").physics("ec").create("pot1", "ElectricPotential", 1);
    model.component("comp1").physics("ec").feature("pot1").selection().set(9, 20);
    model.component("comp1").physics("ec").feature("pot1").set("V0", "-V0");
    model.component("comp1").physics("ec").create("pot2", "ElectricPotential", 1);
    model.component("comp1").physics("ec").feature("pot2").selection().set(10, 19);
    model.component("comp1").physics("ec").feature("pot2").set("V0", "V0");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(9, 10, 19, 20);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", 0.2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hgradactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hgrad", 1.1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("tlist", "range(0,0.125/60,0.5)");
    model.study("std1").feature("time").setSolveFor("/physics/ec", false);
    model.study("std1").setGenPlots(false);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "manual");
    model.sol("sol1").feature("t1").set("atolglobal", 5.0E-5);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").run();
    model.result("pg1").label("\u901f\u5ea6\u6d41\u7ebf");
    model.result("pg1").setIndex("looplevel", 19, 0);
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("expr", new String[]{"u", "v"});
    model.result("pg1").feature("str1").set("descr", "\u901f\u5ea6\u573a");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("udist", 0.01);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").label("\u7535\u52bf");
    model.result("pg2").setIndex("looplevel", 19, 0);
    model.result("pg2").create("con1", "Contour");
    model.result("pg2").feature("con1").set("expr", "V");
    model.result("pg2").feature("con1").set("descr", "\u7535\u52bf");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").label("\u6d53\u5ea6");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "c");
    model.result("pg3").feature("surf1").set("descr", "\u6469\u5c14\u6d53\u5ea6\uff0cc");
    model.result("pg3").run();
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1").set("expr", new String[]{"u", "v"});
    model.result("pg3").feature("str1").set("descr", "\u901f\u5ea6\u573a");
    model.result("pg3").feature("str1").set("posmethod", "uniform");
    model.result("pg3").feature("str1").set("udist", 0.01);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 226, 0);
    model.result("pg3").run();

    model.title("\u7535\u6e17\u5fae\u6df7\u5408\u5668");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u4e00\u4e2a\u5229\u7528\u7535\u6e17\u4f5c\u7528\u6765\u6df7\u5408\u6d41\u4f53\u7684\u5fae\u6df7\u5408\u5668\u3002\u901a\u8fc7\u65bd\u52a0\u77ac\u6001\u7535\u573a\u6765\u4ea7\u751f\u7535\u6e17\uff0c\u5e76\u501f\u6b64\u6270\u52a8\u6d41\u4f53\u3002\u9897\u7c92\u8f68\u8ff9\u52a8\u753b\u663e\u793a\u4e86\u6750\u6599\u7ebf\u7684\u53cd\u590d\u62c9\u4f38\u4e0e\u6298\u53e0\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("electroosmotic_mixer.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
