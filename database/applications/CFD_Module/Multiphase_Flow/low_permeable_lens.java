/*
 * low_permeable_lens.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:31 by COMSOL 6.3.0.290. */
public class low_permeable_lens {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\CFD_Module\\Multiphase_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("phtr", "PhaseTransportPorous", "geom1");
    model.component("comp1").physics().create("dl", "PorousMediaFlowDarcy", "geom1");
    model.component("comp1").physics("dl").prop("ShapeProperty").set("order_pressure", "1");

    model.component("comp1").multiphysics().create("mfpm1", "MultiphaseFlowInPorousMedia", 2);
    model.component("comp1").multiphysics("mfpm1").set("multiphaseflow_physics", "phtr");
    model.component("comp1").multiphysics("mfpm1").set("darcyc_physics", "dl");
    model.component("comp1").multiphysics("mfpm1").selection().all();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/phtr", true);
    model.study("std1").feature("time").setSolveFor("/physics/dl", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/mfpm1", true);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{0.5, 0.65});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new double[]{0.32, 0.12});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new double[]{0, 0.35});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", 0.07, 0);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", 0.65, 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("phtr").prop("GravityEffects").set("IncludeGravity", true);
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1")
         .set("capillarypressuremodel", "BrooksCorey");
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1").set("pec", 1163.5);
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1").set("rhoint_s1_mat", "userdef");
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1").set("mu_s1_mat", "userdef");
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1").set("sr_s1", 0.12);
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1").set("rhoint_s2_mat", "userdef");
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1").set("rhoint_s2", "1460[kg/m^3]");
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1").set("mu_s2_mat", "userdef");
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1").set("mu_s2", "0.0009[Pa*s]");
    model.component("comp1").physics("phtr").create("porous2", "PorousMedium", 2);
    model.component("comp1").physics("phtr").feature("porous2").selection().set(1);
    model.component("comp1").physics("phtr").feature("porous2").feature("fluid1")
         .set("capillarypressuremodel", "BrooksCorey");
    model.component("comp1").physics("phtr").feature("porous2").feature("fluid1").set("pec", 755);
    model.component("comp1").physics("phtr").feature("porous2").feature("fluid1").set("lambdap", 2.7);
    model.component("comp1").physics("phtr").feature("porous2").feature("fluid1").set("rhoint_s1_mat", "userdef");
    model.component("comp1").physics("phtr").feature("porous2").feature("fluid1").set("mu_s1_mat", "userdef");
    model.component("comp1").physics("phtr").feature("porous2").feature("fluid1").set("sr_s1", 0.1);
    model.component("comp1").physics("phtr").feature("porous2").feature("fluid1").set("rhoint_s2_mat", "userdef");
    model.component("comp1").physics("phtr").feature("porous2").feature("fluid1").set("rhoint_s2", "1460[kg/m^3]");
    model.component("comp1").physics("phtr").feature("porous2").feature("fluid1").set("mu_s2_mat", "userdef");
    model.component("comp1").physics("phtr").feature("porous2").feature("fluid1").set("mu_s2", "0.0009[Pa*s]");
    model.component("comp1").physics("phtr").create("mf1", "MassFlux", 1);
    model.component("comp1").physics("phtr").feature("mf1").selection().set(7);
    model.component("comp1").physics("phtr").feature("mf1").setIndex("phases", true, 1);
    model.component("comp1").physics("phtr").feature("mf1").setIndex("q0", 0.25, 1);
    model.component("comp1").physics("phtr").create("sa1", "VolumeFraction", 1);
    model.component("comp1").physics("phtr").feature("sa1").selection().set(2, 10);
    model.component("comp1").physics("phtr").feature("sa1").setIndex("phases", true, 1);
    model.component("comp1").physics("phtr").feature("sa1").set("constraintOptions", "weakConstraints");
    model.component("comp1").physics("phtr").create("pmd1", "PorousMediumDiscontinuity", 1);
    model.component("comp1").physics("phtr").feature("pmd1").selection().set(4, 6, 9);
    model.component("comp1").physics("dl").prop("GravityEffects").set("IncludeGravity", true);
    model.component("comp1").physics("dl").feature("gr1").set("useRref", true);
    model.component("comp1").physics("dl").feature("gr1").set("rref", new String[]{"r", "0", "0.65"});
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("epsilon_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("epsilon", 0.39);
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("kappa_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1")
         .set("kappa", new String[]{"3.32e-11[m^2]", "0", "0", "0", "3.32e-11[m^2]", "0", "0", "0", "3.32e-11[m^2]"});
    model.component("comp1").physics("dl").create("porous2", "PorousMedium", 2);
    model.component("comp1").physics("dl").feature("porous2").selection().set(1);
    model.component("comp1").physics("dl").feature("porous2").feature("pm1").set("epsilon_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous2").feature("pm1").set("epsilon", 0.4);
    model.component("comp1").physics("dl").feature("porous2").feature("pm1").set("kappa_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous2").feature("pm1")
         .set("kappa", new String[]{"6.64e-11[m^2]", "0", "0", "0", "6.64e-11[m^2]", "0", "0", "0", "6.64e-11[m^2]"});
    model.component("comp1").physics("dl").feature("init1").set("InitType", "H");
    model.component("comp1").physics("dl").create("inl1", "Inlet", 1);
    model.component("comp1").physics("dl").feature("inl1").selection().set(7);
    model.component("comp1").physics("dl").feature("inl1").set("BoundaryCondition", "MassFlow");
    model.component("comp1").physics("dl").feature("inl1").set("MassFlowType", "PointWiseMassFlux");
    model.component("comp1").physics("dl").feature("inl1").set("N0", 0.25);
    model.component("comp1").physics("dl").create("pr1", "Pressure", 1);
    model.component("comp1").physics("dl").feature("pr1").selection().set(10);
    model.component("comp1").physics("dl").feature("pr1").set("p0", "(0.65-z)*g_const*1000[kg/m^3]");
    model.component("comp1").physics("dl").create("mf1", "MassFlux", 1);
    model.component("comp1").physics("dl").feature("mf1").selection().set(2);
    model.component("comp1").physics("dl").feature("mf1").set("N0", "s2_lm");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 0.01);

    model.study("std1").setGenPlots(false);
    model.study("std1").feature("time").set("tlist", "range(0,60,6000)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").set("scalemethod", "init");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 61, 0);
    model.result("pg1").set("edges", false);

    model.view("view2").set("showgrid", false);

    model.result("pg1").create("iso1", "Isosurface");
    model.result("pg1").feature("iso1").set("expr", "s2");
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "1");
    model.result("pg1").feature("surf1").set("coloring", "uniform");
    model.result("pg1").feature("surf1").set("color", "gray");
    model.result("pg1").feature("surf1").create("sel1", "Selection");
    model.result("pg1").feature("surf1").feature("sel1").selection().set(2);
    model.result("pg1").feature("surf1").feature("sel1").set("evalstartcap", false);
    model.result("pg1").feature("surf1").feature("sel1").set("evalendcap", false);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("sel1").selection().set(1);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("surf2", "Surface");
    model.result("pg1").feature("surf2").set("expr", "1");
    model.result("pg1").feature("surf2").set("coloring", "uniform");
    model.result("pg1").feature("surf2").set("titletype", "none");
    model.result("pg1").feature("surf2").create("sel1", "Selection");
    model.result("pg1").feature("surf2").feature("sel1").selection().set(2);
    model.result("pg1").feature("surf2").feature("sel1").set("evalstartcap", false);
    model.result("pg1").feature("surf2").feature("sel1").set("evalendcap", false);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 13, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 61, 0);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").create("con1", "ContourSeries");
    model.result("pg2").feature("con1").set("level", "0.75");
    model.result("pg2").feature("con1").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg2").feature("con1").setIndex("looplevelindices", "range(1,10,101)", 0);
    model.result("pg2").feature("con1").create("col1", "Color");
    model.result("pg2").run();
    model.result("pg2").feature("con1").feature("col1").set("expr", "t");
    model.result("pg2").feature("con1").feature("col1").set("colortabletype", "discrete");
    model.result("pg2").feature("con1").feature("col1").set("colortable", "Cividis");
    model.result("pg2").feature("con1").feature("col1").set("colortabletrans", "none");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").create("iso1", "IsosurfaceSeries");
    model.result("pg3").feature("iso1").set("expr", "s2");
    model.result("pg3").feature("iso1").set("level", "0.2");
    model.result("pg3").feature("iso1").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg3").feature("iso1").setIndex("looplevelindices", "range(1,20,101)", 0);
    model.result("pg3").feature("iso1").create("col1", "Color");
    model.result("pg3").run();
    model.result("pg3").feature("iso1").feature("col1").set("expr", "t");
    model.result("pg3").feature("iso1").feature("col1").set("unit", "min");
    model.result("pg3").feature("iso1").feature("col1").set("colortabletype", "discrete");
    model.result("pg3").feature("iso1").feature("col1").set("colortable", "Cividis");
    model.result("pg3").feature("iso1").feature("col1").set("colortabletrans", "none");
    model.result("pg3").feature("iso1").feature("col1").set("bandcount", 5);
    model.result("pg3").run();

    model.title("\u4f4e\u6e17\u900f\u6027\u6676\u4f53\u4e0a\u65b9\u7684\u4e24\u76f8\u6d41");

    model
         .description("\u672c\u4f8b\u5206\u6790\u542b\u6709\u4f4e\u6e17\u900f\u6027\u6676\u4f53\u7684\u591a\u5b54\u4ecb\u8d28\u4e2d\u7684\u4e24\u76f8\u6d41\u3002\u91cd\u76f8\u4ece\u4e0a\u65b9\u6e17\u5165\u591a\u5b54\u4ecb\u8d28\uff0c\u5e76\u4e14\u4ec5\u5f53\u4f4e\u6e17\u900f\u6027\u6676\u4f53\u5916\u90e8\u8fbe\u5230\u4e34\u754c\u9971\u548c\u5ea6\u65f6\uff0c\u91cd\u76f8\u624d\u6e17\u900f\u5230\u6676\u4f53\u4e2d\u3002\u7531\u4e8e\u91cd\u76f8\u7684\u9971\u548c\u5ea6\u5728\u6676\u4f53\u8fb9\u754c\u4e0d\u8fde\u7eed\uff0c\u56e0\u6b64\u9700\u8981\u4f7f\u7528\u201c\u591a\u5b54\u4ecb\u8d28\u4e0d\u8fde\u7eed\u6027\u201d\u8fb9\u754c\u6761\u4ef6\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("low_permeable_lens.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
