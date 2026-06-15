/*
 * oscillating_fsi.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:24 by COMSOL 6.3.0.290. */
public class oscillating_fsi {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics("spf").prop("AdvancedSettingProperty").set("UsePseudoTime", "1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("Compressibility", "Incompressible");
    model.component("comp1").physics("spf").field("velocity").field("u_fluid");
    model.component("comp1").physics("spf").field("velocity")
         .component(new String[]{"u_fluid", "v_fluid", "w_fluid"});
    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics("solid").field("displacement").field("u_solid");
    model.component("comp1").physics("solid").field("displacement")
         .component(new String[]{"u_solid", "v_solid", "w_solid"});
    model.component("comp1").physics("solid").prop("ShapeProperty").set("order_displacement", "2");

    model.component("comp1").multiphysics().create("fsi1", "FluidStructureInteractionBC", 1);
    model.component("comp1").multiphysics("fsi1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("fsi1").set("Structure_physics", "solid");
    model.component("comp1").multiphysics("fsi1").selection().all();

    model.component("comp1").common().create("free1", "DeformingDomain");
    model.component("comp1").common("free1").set("smoothingType", "yeoh");
    model.component("comp1").common("free1").selection().set();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/spf", true);
    model.study("std1").feature("time").setSolveFor("/physics/solid", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/fsi1", true);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{2.5, 0.41});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", 0.05);
    model.component("comp1").geom("geom1").feature("c1").set("pos", new double[]{0.2, 0.2});
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"0.35+0.05", "0.02"});
    model.component("comp1").geom("geom1").feature("r2").set("base", "center");
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"0.2+0.4/2", "0.2"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new double[]{0.6, 0.41});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new double[]{0.2, 0});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").label("\u56fa\u4f53");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r2");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("c1");
    model.component("comp1").geom("geom1").feature("dif1").set("keepadd", true);
    model.component("comp1").geom("geom1").feature("dif1").set("keepsubtract", true);
    model.component("comp1").geom("geom1").feature("dif1").set("selresult", true);
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("dif2", "Difference");
    model.component("comp1").geom("geom1").feature("dif2").label("\u6d41\u4f53");
    model.component("comp1").geom("geom1").feature("dif2").selection("input").set("r1", "r3");
    model.component("comp1").geom("geom1").feature("dif2").selection("input2").set("c1", "r2");
    model.component("comp1").geom("geom1").feature("dif2").set("selresult", true);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").common("free1").selection().set(2);

    model.component("comp1").func().create("step1", "Step");
    model.component("comp1").func("step1").set("location", "0.5[s]");
    model.component("comp1").func("step1").set("smooth", 1);
    model.component("comp1").func().create("gp1", "GaussianPulse");
    model.component("comp1").func("gp1").set("location", "1.5[s]");
    model.component("comp1").func("gp1").set("sigma", "5e-2[s]");
    model.component("comp1").func("gp1").set("normalization", "peak");

    model.component("comp1").physics("spf").selection().named("geom1_dif2_dom");
    model.component("comp1").physics("spf").prop("ConsistentStabilization").set("CrosswindDiffusion", false);
    model.component("comp1").physics("spf").prop("ShapeProperty").set("order_fluid", 4);
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(1);
    model.component("comp1").physics("spf").feature("inl1")
         .set("U0in", "1.5*2[m/s]*Y*(0.41[m]-Y)/(0.41[m]/2)^2*step1(t)");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().set(14);
    model.component("comp1").physics("solid").selection().named("geom1_dif1_dom");
    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().set(19, 20);
    model.component("comp1").physics("solid").create("pl1", "PointLoad", 0);
    model.component("comp1").physics("solid").feature("pl1").selection().set(11);
    model.component("comp1").physics("solid").feature("pl1")
         .set("forcePoint", new String[]{"0", "1[N]*gp1(t)", "0"});

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").selection().named("geom1_dif1_dom");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"5.6[MPa]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.4"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1e3"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().named("geom1_dif2_dom");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"1000"});
    model.component("comp1").material("mat2").propertyGroup("def").set("dynamicviscosity", new String[]{"1"});

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 6);
    model.component("comp1").mesh("mesh1").feature("size1").set("hauto", 5);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(1, 2, 3);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature().move("map1", 5);
    model.component("comp1").mesh("mesh1").feature("map1").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(12, 13);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 40);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 5);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("bl1").set("sharpcorners", "none");
    model.component("comp1").mesh("mesh1").feature("bl1").set("smoothtransition", false);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().set(8, 9, 10, 15, 16, 17, 18);

    model.component("comp1").probe().create("var1", "GlobalVariable");
    model.component("comp1").probe("var1").set("probename", "drag");
    model.component("comp1").probe("var1").set("expr", "-intop1(spf.T_stressx)");
    model.component("comp1").probe("var1").set("descractive", true);
    model.component("comp1").probe("var1").set("descr", "\u66f3\u529b");
    model.component("comp1").probe("var1").set("window", "window1");
    model.component("comp1").probe("var1").set("windowtitle", "\u63a2\u9488\u56fe\u201c1\u201d");
    model.component("comp1").probe().duplicate("var2", "var1");
    model.component("comp1").probe("var2").set("probename", "lift");
    model.component("comp1").probe("var2").set("expr", "-intop1(spf.T_stressy)");
    model.component("comp1").probe("var2").set("descr", "\u5347\u529b");
    model.component("comp1").probe().create("pdom1", "DomainPoint");
    model.component("comp1").probe("pdom1").set("frame", "material");
    model.component("comp1").probe("pdom1").setIndex("coords2", 0.595, 0);
    model.component("comp1").probe("pdom1").setIndex("coords2", 0.2, 1);
    model.component("comp1").probe("pdom1").feature("ppb1").set("probename", "u");
    model.component("comp1").probe("pdom1").feature("ppb1").set("expr", "u_solid");
    model.component("comp1").probe("pdom1").feature("ppb1").set("unit", "mm");
    model.component("comp1").probe("pdom1").feature("ppb1").set("window", "window2");
    model.component("comp1").probe("pdom1").feature("ppb1").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.component("comp1").probe("pdom1").feature().duplicate("ppb2", "ppb1");
    model.component("comp1").probe("pdom1").feature("ppb2").set("expr", "v_solid");
    model.component("comp1").probe("pdom1").feature("ppb2").set("probename", "v");

    model.study("std1").feature("time").set("tlist", "range(0,5e-2,5)");
    model.study("std1").feature("time").set("plot", true);
    model.study("std1").feature("time").set("plotfreq", "tsteps");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", "0.9");
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "once");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 8);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 0.5);
    model.sol("sol1").feature("t1").feature("fc1").set("stabacc", "aacc");
    model.sol("sol1").feature("t1").feature("fc1").set("aaccdim", 5);
    model.sol("sol1").feature("t1").feature("fc1").set("aaccmix", 0.9);
    model.sol("sol1").feature("t1").feature("fc1").set("aaccdelay", 1);

    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("var1").genResult("none");
    model.component("comp1").probe("var2").genResult("none");
    model.component("comp1").probe("pdom1").genResult("none");

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u901f\u5ea6 (spf)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");

    model.sol("sol1").runAll();

    model.result().remove("pg3");

    model.study("std1").feature("time").set("plotgroup", "Default");

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u901f\u5ea6 (spf)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u538b\u529b (spf)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("con1", "Contour");
    model.result("pg4").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg4").feature("con1").set("showsolutionparams", "on");
    model.result("pg4").feature("con1").set("expr", "p");
    model.result("pg4").feature("con1").set("number", 40);
    model.result("pg4").feature("con1").set("levelrounding", false);
    model.result("pg4").feature("con1").set("smooth", "internal");
    model.result("pg4").feature("con1").set("showsolutionparams", "on");
    model.result("pg4").feature("con1").set("data", "parent");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").setIndex("looplevel", 101, 0);
    model.result("pg5").label("\u5e94\u529b (solid)");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg5").feature("surf1").set("threshold", "manual");
    model.result("pg5").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg5").feature("surf1").set("colortable", "Rainbow");
    model.result("pg5").feature("surf1").set("colortabletrans", "none");
    model.result("pg5").feature("surf1").set("colorscalemode", "linear");
    model.result("pg5").feature("surf1").set("resolution", "normal");
    model.result("pg5").feature("surf1").set("colortable", "Prism");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").setIndex("looplevel", 101, 0);
    model.result("pg6").label("\u52a8\u7f51\u683c");
    model.result("pg6").create("mesh1", "Mesh");
    model.result("pg6").feature("mesh1").set("meshdomain", "surface");
    model.result("pg6").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg6").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg6").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg6").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg6").feature("mesh1").feature("sel1").selection().set(2, 3);
    model.result("pg6").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg6").feature("mesh1").set("qualexpr", "comp1.spatial.relVol");
    model.result("pg6").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg3").run();
    model.result("pg5").run();
    model.result("pg3").run();
    model.result("pg3").feature().copy("surf2", "pg5/surf1");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").create("arws1", "ArrowSurface");
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("plotgroup", "pg3");
    model.result().export("anim1").run();
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").label("\u5347\u529b\u548c\u66f3\u529b");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u5347\u529b\u548c\u66f3\u529b (N)");
    model.result("pg1").set("legendpos", "middleleft");
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").set("axislimits", true);
    model.result("pg1").set("xmin", 4);
    model.result("pg1").set("xmax", 5);
    model.result("pg1").set("ymin", -160);
    model.result("pg1").set("ymax", 500);
    model.result("pg1").set("legendpos", "upperright");
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg2").set("window", "window2");
    model.result("pg2").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg2").run();
    model.result("pg2").label("\u6881\u5c16\u7aef\u4f4d\u79fb");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("axislimits", true);
    model.result("pg2").set("xmin", 4);
    model.result("pg2").set("xmax", 5);
    model.result("pg2").set("ymin", -40);
    model.result("pg2").set("ymax", 40);
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u6881\u5c16\u7aef\u4f4d\u79fb (mm)");
    model.result("pg2").set("window", "window2");
    model.result("pg2").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg2").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u9891\u8c31");
    model.result("pg7").set("data", "cpt1");
    model.result("pg7").setIndex("looplevelinput", "interp", 0);
    model.result("pg7").setIndex("interp", "range(3,5e-3,5)", 0);
    model.result("pg7").create("ptgr1", "PointGraph");
    model.result("pg7").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg7").feature("ptgr1").set("linewidth", "preference");
    model.result("pg7").feature("ptgr1").set("expr", "u_solid");
    model.result("pg7").feature("ptgr1").set("unit", "mm");
    model.result("pg7").feature("ptgr1").set("xdata", "fourier");
    model.result("pg7").feature("ptgr1").set("fouriershow", "spectrum");
    model.result("pg7").feature("ptgr1").set("freqrangeactive", true);
    model.result("pg7").feature("ptgr1").set("freqmin", 1);
    model.result("pg7").feature("ptgr1").set("freqmax", 15);
    model.result("pg7").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg7").run();
    model.result("pg7").feature("ptgr2").set("expr", "v_solid");
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u6881\u5c16\u7aef\u8f68\u8ff9");
    model.result("pg8").create("tblp1", "Table");
    model.result("pg8").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg8").feature("tblp1").set("linewidth", "preference");
    model.result("pg8").feature("tblp1").set("xaxisdata", 4);
    model.result("pg8").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg8").feature("tblp1").set("plotcolumns", new int[]{5});
    model.result("pg8").run();
    model.result("pg3").run();

    model.title("\u6d41\u4f53\u6d41\u52a8\u4e2d\u7684\u6881\u632f\u52a8");

    model
         .description("\u8fd9\u662f\u6709\u5173\u5728\u6d41\u52a8\u4e2d\u632f\u52a8\u7684\u6881\u7684\u77ac\u6001\u6d41-\u56fa\u8026\u5408\u4eff\u771f\u3002\u5728\u8be5\u7ed3\u6784\u4e0b\u6e38\u51fa\u73b0\u4e86\u5361\u95e8\u6da1\u8857\u73b0\u8c61\u3002\u672c\u4f8b\u5c06\u4eff\u771f\u7ed3\u679c\u4e0e\u57fa\u51c6\u89e3\u8fdb\u884c\u4e86\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("oscillating_fsi.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
