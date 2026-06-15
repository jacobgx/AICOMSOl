/*
 * split_recombine_mixer.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:10 by COMSOL 6.3.0.290. */
public class split_recombine_mixer {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Microfluidics_Module\\Micromixers");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics().create("tds", "DilutedSpecies", "geom1");

    model.component("comp1").multiphysics().create("rfd1", "ReactingFlowDS", 3);
    model.component("comp1").multiphysics("rfd1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("rfd1").set("Species_physics", "tds");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/tds", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/rfd1", true);

    model.component("comp1").geom("geom1").insertFile("split_recombine_mixer_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("difsel2");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u6d41\u4f53");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"977"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"8.55e-4"});

    model.param().set("v0", "1[mm/s]");
    model.param().descr("v0", "\u6d41\u5165\u901f\u5ea6");
    model.param().set("d", "500[um]");
    model.param().descr("d", "\u901a\u9053\u8fb9\u957f");

    model.component("comp1").physics("spf").prop("ShapeProperty").set("order_fluid", 2);
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().named("geom1_unisel1");
    model.component("comp1").physics("spf").feature("inl1").set("U0in", "v0");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().named("geom1_sel4");
    model.component("comp1").physics("tds").prop("MassConsistentStabilization").set("CrosswindType", "Codina");
    model.component("comp1").physics("tds").prop("ShapeProperty").set("order_concentration", 3);
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_c", new String[]{"2e-20[m^2/s]", "0", "0", "0", "2e-20[m^2/s]", "0", "0", "0", "2e-20[m^2/s]"});
    model.component("comp1").physics("tds").feature("init1").setIndex("initc", 0.5, 0);
    model.component("comp1").physics("tds").create("in1", "Inflow", 2);
    model.component("comp1").physics("tds").feature("in1").selection().named("geom1_sel2");
    model.component("comp1").physics("tds").create("in2", "Inflow", 2);
    model.component("comp1").physics("tds").feature("in2").selection().named("geom1_sel3");
    model.component("comp1").physics("tds").feature("in2").setIndex("c0", 1, 0);
    model.component("comp1").physics("tds").create("out1", "Outflow", 2);
    model.component("comp1").physics("tds").feature("out1").selection().named("geom1_sel4");

    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("size").set("table", "cfd");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").setSolveFor("/physics/tds", false);
    model.study("std1").create("stat2", "Stationary");
    model.study("std1").feature("stat2").setSolveFor("/physics/spf", false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("slc1", "Slice");
    model.result("pg1").feature("slc1").label("\u5207\u9762");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("smooth", "internal");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "dset1");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 13, 14, 15, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 41, 42, 43, 44, 45, 46, 48, 49, 50, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("data", "surf1");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "p");
    model.result("pg2").feature("surf1").set("colortable", "Dipole");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature("surf1").feature().create("tran1", "Transparency");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").label("\u6d53\u5ea6, \u6d41\u7ebf (tds)");
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").set("typeintitle", true);
    model.result("pg3").set("prefixintitle", "");
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1").set("expr", new String[]{"tds.tflux_cx", "tds.tflux_cy", "tds.tflux_cz"});
    model.result("pg3").feature("str1").set("posmethod", "start");
    model.result("pg3").feature("str1").set("pointtype", "arrow");
    model.result("pg3").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg3").feature("str1").set("color", "gray");
    model.result("pg3").feature("str1").create("col", "Color");
    model.result("pg3").feature("str1").feature("col").set("expr", "c");
    model.result("pg3").feature("str1").feature("col").set("colortable", "Prism");
    model.result("pg3").feature("str1").feature("col").set("titletype", "custom");
    model.result("pg3").feature("str1").set("linetype", "ribbon");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").label("\u6d53\u5ea6, \u8868\u9762 (tds)");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("prefixintitle", "");
    model.result("pg4").set("expressionintitle", false);
    model.result("pg4").set("typeintitle", false);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"c"});
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result("pg1").run();
    model.result().dataset().create("surf2", "Surface");
    model.result().dataset("surf2").set("param", "expr");
    model.result().dataset("surf2").set("expry", "z");
    model.result().dataset("surf2").selection().named("geom1_sel5");
    model.result().dataset("surf2").label("A-A");
    model.result().dataset().duplicate("surf3", "surf2");
    model.result().dataset("surf3").label("B-B");
    model.result().dataset("surf3").set("exprx", "-y");
    model.result().dataset("surf3").selection().named("geom1_sel6");
    model.result().dataset().duplicate("surf4", "surf3");
    model.result().dataset("surf4").label("C-C");
    model.result().dataset("surf4").selection().named("geom1_sel7");
    model.result().dataset().duplicate("surf5", "surf4");
    model.result().dataset("surf5").label("D-D");
    model.result().dataset("surf5").selection().named("geom1_sel8");
    model.result().dataset("surf5").set("exprx", "x");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").run();
    model.result("pg5").label("\u6d41\u52a8\u5206\u5c42\u6837\u5f0f");
    model.result("pg5").set("titletype", "none");
    model.result("pg5").set("data", "surf2");
    model.result("pg5").set("edges", false);
    model.result("pg5").create("con1", "Contour");
    model.result("pg5").feature("con1").set("expr", "c");
    model.result("pg5").feature("con1").set("number", 10);
    model.result("pg5").feature("con1").set("contourtype", "filled");
    model.result("pg5").feature("con1").set("colortable", "GrayPrint");
    model.result("pg5").feature().duplicate("con2", "con1");
    model.result("pg5").run();
    model.result("pg5").feature("con2").set("data", "surf3");
    model.result("pg5").feature("con2").set("inheritplot", "con1");
    model.result("pg5").feature("con2").create("trn1", "Transformation");
    model.result("pg5").run();
    model.result("pg5").feature("con2").feature("trn1").set("move", new String[]{"d/2", "-d"});
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("con3", "con2");
    model.result("pg5").run();
    model.result("pg5").feature("con3").set("data", "surf4");
    model.result("pg5").run();
    model.result("pg5").feature("con3").feature("trn1").set("move", new String[]{"-5*d", "0"});
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("con4", "con3");
    model.result("pg5").run();
    model.result("pg5").feature("con4").set("data", "surf5");
    model.result("pg5").run();
    model.result("pg5").feature("con4").feature("trn1").set("move", new String[]{"4.5*d", "0"});
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").create("tlan1", "TableAnnotation");
    model.result("pg5").feature("tlan1").set("source", "localtable");
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", 0.75, 0, 0);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", -0.075, 0, 1);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "A-A", 0, 2);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", 1.5, 1, 0);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", -0.075, 1, 1);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "B-B", 1, 2);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", 2.25, 2, 0);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", -0.075, 2, 1);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "C-C", 2, 2);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", 3, 3, 0);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", -0.075, 3, 1);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "D-D", 3, 2);
    model.result("pg5").feature("tlan1").set("showpoint", false);
    model.result("pg5").feature("tlan1").set("anchorpoint", "center");
    model.result("pg5").run();
    model.result("pg5").set("showlegends", false);

    model.view("view3").set("showgrid", false);

    model.result().numerical().create("av1", "AvSurface");
    model.result().numerical("av1").set("intvolume", true);
    model.result().numerical("av1").selection().named("geom1_unisel1");
    model.result().numerical("av1").setIndex("expr", "p", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u8868\u9762\u5e73\u5747\u503c 1");
    model.result().numerical("av1").set("table", "tbl1");
    model.result().numerical("av1").setResult();
    model.result("pg4").run();

    model.title("\u5206\u79bb\u91cd\u7ec4\u6df7\u5408\u5668\u57fa\u51c6\u6a21\u578b");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u4e00\u4e2a\u5206\u79bb\u91cd\u7ec4\u6df7\u5408\u5668\u901a\u9053\uff0c\u5f15\u5165\u4e86\u793a\u8e2a\u6d41\u4f53\uff0c\u8868\u73b0\u4e3a\u591a\u5c42\u6df7\u5408\u3002\u5c06\u6269\u6563\u7cfb\u6570\u8bbe\u7f6e\u5f97\u6781\u5c0f\uff0c\u4ece\u800c\u53bb\u9664\u4e86\u6269\u6563\u7684\u4f5c\u7528\uff0c\u4ee5\u4fbf\u53ea\u7814\u7a76\u5c42\u754c\u9762\u4e0a\u7684\u6570\u503c\u6269\u6563\u3002\u7ed3\u679c\u4e0e\u53c2\u8003\u7684\u51fa\u7248\u7269\u4e2d\u53d1\u8868\u7684\u5c42\u6d41\u56fe\u6848\u4ee5\u53ca\u6df7\u5408\u5668\u4e0a\u7684\u603b\u538b\u964d\u543b\u5408\u5f97\u5f88\u597d\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("split_recombine_mixer.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
