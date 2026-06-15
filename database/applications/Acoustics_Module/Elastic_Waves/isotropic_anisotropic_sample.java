/*
 * isotropic_anisotropic_sample.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:27 by COMSOL 6.3.0.290. */
public class isotropic_anisotropic_sample {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Elastic_Waves");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("elte", "ElasticWavesTimeExplicit", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/elte", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("L", "18[cm]", "\u6837\u672c\u534a\u957f\u5ea6");
    model.param().set("f0", "170[kHz]", "\u6e90\u4e2d\u5fc3\u9891\u7387");
    model.param().set("t0", "6[us]", "\u6e90\u65f6\u79fb");
    model.param().set("x0", "-2[cm]", "\u6e90\u4f4d\u7f6e x \u5750\u6807");
    model.param().set("y0", "0[cm]", "\u6e90\u4f4d\u7f6e y \u5750\u6807");
    model.param().set("dS", "2e-7[1/m^2]", "\u6e90\u8303\u56f4");
    model.param()
         .set("cs_an", "2150[m/s]", "\u526a\u5207\u6ce2\u901f\uff08\u5404\u5411\u5f02\u6027\u6750\u6599\uff09");
    model.param()
         .set("cs_iso", "2350[m/s]", "\u526a\u5207\u6ce2\u901f\uff08\u5404\u5411\u540c\u6027\u6750\u6599\uff09");

    model.func().create("an1", "Analytic");
    model.func("an1").set("funcname", "G_space");
    model.func("an1").set("expr", "1/(pi*dS)*exp(-((x - x0)^2 + (y - y0)^2)/dS)");
    model.func("an1").set("args", "x, y");
    model.func("an1").setIndex("argunit", "m", 0);
    model.func("an1").set("fununit", "1");
    model.func("an1").setIndex("plotargs", -0.04, 0, 1);
    model.func("an1").setIndex("plotargs", 0, 0, 2);
    model.func("an1").setIndex("plotargs", -0.02, 1, 1);
    model.func("an1").setIndex("plotargs", 0.02, 1, 2);
    model.func().create("an2", "Analytic");
    model.func("an2").set("funcname", "G_time");
    model.func("an2").set("expr", "(1 - 2*pi^2*f0^2*(t - t0)^2)*exp(-pi^2*f0^2*(t - t0)^2)");
    model.func("an2").set("args", "t");
    model.func("an2").setIndex("argunit", "s", 0);
    model.func("an2").set("fununit", "N");
    model.func("an2").setIndex("plotargs", "5*t0", 0, 2);
    model.func("an2").createPlot("pg1");

    model.result("pg1").run();
    model.result("pg1").label("\u8109\u51b2\u9891\u7387\u8c31");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "FFT of G_time (Pa)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u4fe1\u53f7\u7684 FFT \u7ed3\u679c (Pa)");
    model.result("pg1").run();
    model.result("pg1").feature("plot1").set("display", "fourier");
    model.result("pg1").feature("plot1").set("fouriershow", "spectrum");
    model.result("pg1").feature("plot1").set("scale", "multiplyperiod");
    model.result("pg1").run();
    model.result("pg1").feature("plot1").set("freqrangeactive", true);
    model.result("pg1").feature("plot1").set("freqmax", 1000000);
    model.result("pg1").run();

    model.component("comp1").geom("geom1").create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("sq1").set("size", "2*L");
    model.component("comp1").geom("geom1").feature("sq1").set("base", "center");
    model.component("comp1").geom("geom1").feature("sq1").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("sq1").setIndex("layer", "L/6", 0);
    model.component("comp1").geom("geom1").feature("sq1").set("layerright", true);
    model.component("comp1").geom("geom1").feature("sq1").set("layertop", true);
    model.component("comp1").geom("geom1").run("sq1");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new String[]{"0", "-L"});
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"0", "L"});
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "-10.5[cm] -3.5[cm] -1[cm] 10.5[cm]", 0);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "-8[cm] -8[cm] -8[cm] -8[cm]", 1);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").geom(0);
    model.component("comp1").selection("sel1").set(9, 10, 11, 16);
    model.component("comp1").selection("sel1").label("\u63a2\u9488\u70b9");

    model.component("comp1").coordSystem().create("ab1", "AbsorbingLayer");
    model.component("comp1").coordSystem("ab1").selection().set(1, 2, 3, 4, 6, 7, 9, 10, 11, 12);

    model.component("comp1").physics("elte").feature("eltem1").set("SolidModel", "Anisotropic");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u6750\u6599\u5404\u5411\u540c\u6027");
    model.component("comp1").material("mat1").selection().set(7, 8, 9, 10, 11, 12);
    model.component("comp1").material("mat1").propertyGroup()
         .create("AnisotropicVoGrp", "AnisotropicVoGrp", "Anisotropic_Voigt_notation");
    model.component("comp1").material("mat1").propertyGroup("AnisotropicVoGrp")
         .set("DVo", new String[]{"16.5e10", "8.58e10", "16.5e10", "8.58e10", "8.58e10", "16.5e10", "0", "0", "0", "3.96e10", 
         "0", "0", "0", "0", "3.96e10", "0", "0", "0", "0", "0", 
         "3.96e10"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"7100"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u6750\u6599\u5404\u5411\u5f02\u6027");
    model.component("comp1").material("mat2").selection().set(1, 2, 3, 4, 5, 6);
    model.component("comp1").material("mat2").propertyGroup()
         .create("AnisotropicVoGrp", "AnisotropicVoGrp", "Anisotropic_Voigt_notation");
    model.component("comp1").material("mat2").propertyGroup("AnisotropicVoGrp")
         .set("DVo", new String[]{"16.5e10", "5.0e10", "6.2e10", "5.0e10", "5.0e10", "6.2e10", "0", "0", "0", "3.96e10", 
         "0", "0", "0", "0", "3.96e10", "0", "0", "0", "0", "0", 
         "3.96e10"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"7100"});

    model.component("comp1").physics("elte").feature("eltem1").create("cdisp1", "ComputeDisplacement", 0);
    model.component("comp1").physics("elte").feature("eltem1").feature("cdisp1").selection().named("sel1");
    model.component("comp1").physics("elte").create("bl1", "BodyLoad", 2);
    model.component("comp1").physics("elte").feature("bl1").selection().set(5, 8);
    model.component("comp1").physics("elte").feature("bl1").set("forceType", "TotalForce");
    model.component("comp1").physics("elte").feature("bl1")
         .set("force", new String[]{"0", "G_space(x,y)*G_time(t)", "0"});
    model.component("comp1").physics("elte").create("lrb1", "LowReflectingBoundary", 1);
    model.component("comp1").physics("elte").feature("lrb1").selection()
         .set(1, 2, 3, 5, 7, 9, 14, 16, 21, 23, 28, 29, 30, 31);
    model.component("comp1").physics("elte").create("mde1", "MaterialDiscontinuityElem", 1);
    model.component("comp1").physics("elte").feature("mde1").selection().set(15, 17, 19);

    model.component("comp1").probe().create("point1", "Point");
    model.component("comp1").probe("point1").selection().set(9);
    model.component("comp1").probe("point1").set("expr", "elte.uy");
    model.component("comp1").probe("point1").set("descr", "\u4f4d\u79fb\uff0cy \u5206\u91cf");
    model.component("comp1").probe("point1").set("descractive", true);
    model.component("comp1").probe("point1").set("descr", "(-10.5 cm, -8 cm) \u7684\u4f4d\u79fb");
    model.component("comp1").probe().duplicate("point2", "point1");
    model.component("comp1").probe("point2").selection().set(10);
    model.component("comp1").probe("point2").set("descr", "(-3.5 cm, -8 cm) \u7684\u4f4d\u79fb");
    model.component("comp1").probe().duplicate("point3", "point2");
    model.component("comp1").probe("point3").selection().set(11);
    model.component("comp1").probe("point3").set("descr", "(-1 cm, -8 cm) \u7684\u4f4d\u79fb");
    model.component("comp1").probe().duplicate("point4", "point3");
    model.component("comp1").probe("point4").selection().set(16);
    model.component("comp1").probe("point4").set("descr", "(10.5 cm, -8 cm) \u7684\u4f4d\u79fb");

    model.nodeGroup().create("grp1", "Definitions", "comp1");
    model.nodeGroup("grp1").set("type", "probe");
    model.nodeGroup("grp1").add("probe", "point1");
    model.nodeGroup("grp1").add("probe", "point2");
    model.nodeGroup("grp1").add("probe", "point3");
    model.nodeGroup("grp1").add("probe", "point4");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "cs_an/(2*f0)/1.5");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1 - ELTE \uff08\u50a8\u5b58\u6240\u6709\u89e3\uff09");
    model.study("std1").feature("time").set("tunit", "\u00b5s");
    model.study("std1").feature("time").set("tlist", "range(0, 30[us], 90[us])");
    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("point1").genResult("none");
    model.component("comp1").probe("point2").genResult("none");
    model.component("comp1").probe("point3").genResult("none");
    model.component("comp1").probe("point4").genResult("none");

    model.sol("sol1").runAll();

    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u901f\u5ea6\u5927\u5c0f (elte)");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("resolution", "custom");
    model.result("pg3").feature("surf1").set("refine", 6);
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u538b\u529b (elte)");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "elte.p");
    model.result("pg4").feature("surf1").set("colortable", "Wave");
    model.result("pg4").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg4").feature("surf1").set("resolution", "custom");
    model.result("pg4").feature("surf1").set("refine", 6);
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg3").run();
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().set(5, 8);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 2, 0);
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("colortable", "GrayScale");
    model.result("pg3").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg3").feature("surf1").set("rangecoloractive", true);
    model.result("pg3").feature("surf1").set("rangecolormin", 0);
    model.result("pg3").feature("surf1").set("rangecolormax", "1e-5");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 3, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 4, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 2, 0);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 2, 0);
    model.result("pg4").run();
    model.result("pg2").set("window", "window2");
    model.result("pg2").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg2").run();
    model.result("pg2").label("(x,y) = (-10.5 cm, -8 cm) \u7684\u4f4d\u79fb");
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("showlegends", false);
    model.result("pg2").set("window", "window2");
    model.result("pg2").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg2").run();
    model.result("pg2").feature("tblp1").set("plotcolumns", new int[]{2});
    model.result("pg2").set("window", "window2");
    model.result("pg2").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg2").run();
    model.result("pg2").set("window", "window2");
    model.result("pg2").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg2").run();
    model.result().duplicate("pg5", "pg2");
    model.result("pg5").set("window", "window2");
    model.result("pg5").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg5").run();
    model.result("pg5").label("(x,y) = (-3.5 cm, -8 cm) \u7684\u4f4d\u79fb");
    model.result("pg5").set("window", "window2");
    model.result("pg5").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg5").run();
    model.result("pg5").feature("tblp1").set("plotcolumns", new int[]{3});
    model.result("pg5").set("window", "window2");
    model.result("pg5").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg5").run();
    model.result("pg5").set("window", "window2");
    model.result("pg5").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").set("window", "window2");
    model.result("pg6").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg6").run();
    model.result("pg6").label("(x,y) = (-1 cm, -8 cm) \u7684\u4f4d\u79fb");
    model.result("pg6").set("window", "window2");
    model.result("pg6").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg6").run();
    model.result("pg6").feature("tblp1").set("plotcolumns", new int[]{4});
    model.result("pg6").set("window", "window2");
    model.result("pg6").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg6").run();
    model.result("pg6").set("window", "window2");
    model.result("pg6").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").set("window", "window2");
    model.result("pg7").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg7").run();
    model.result("pg7").label("(x,y) = (10.5 cm, -8 cm) \u7684\u4f4d\u79fb");
    model.result("pg7").set("window", "window2");
    model.result("pg7").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg7").run();
    model.result("pg7").feature("tblp1").set("plotcolumns", new int[]{5});
    model.result("pg7").set("window", "window2");
    model.result("pg7").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").run();
    model.result("pg8").label("\u8868\u89c2\u526a\u5207\u6ce2\u901f");
    model.result("pg8").setIndex("looplevel", 2, 0);
    model.result("pg8").set("showlegendsunit", true);
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", "elte.cs");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result().duplicate("pg9", "pg8");
    model.result("pg9").run();
    model.result("pg9").label("\u8868\u89c2\u538b\u529b\u6ce2\u901f");
    model.result("pg9").run();
    model.result("pg9").feature("surf1").set("expr", "elte.cp");
    model.result("pg9").run();
    model.result("pg3").run();

    model.title("\u5404\u5411\u540c\u6027-\u5404\u5411\u5f02\u6027\u8bd5\u6837\uff1a\u5f39\u6027\u6ce2\u4f20\u64ad");

    model
         .description("\u5728\u8fd9\u4e2a\u4e8c\u7ef4\u6559\u7a0b\u4e2d\uff0c\u6d4b\u8bd5\u6837\u672c\u7684\u4e00\u4fa7\u7531\u5404\u5411\u540c\u6027\u6750\u6599\uff0c\u53e6\u4e00\u4fa7\u7531\u975e\u5747\u8d28\u5404\u5411\u5f02\u6027\u6750\u6599\uff08\u6a2a\u5411\u5404\u5411\u5f02\u6027\u950c\u6676\u4f53\uff09\u7ec4\u6210\uff0c\u5176\u4e2d\u7684\u5f39\u6027\u6ce2\u7531\u70b9\u72b6\u529b\u6fc0\u52b1\u3002\u8be5\u6a21\u578b\u901a\u8fc7\u201c\u5f39\u6027\u6ce2\uff0c\u65f6\u57df\u663e\u5f0f\u201d\u7269\u7406\u573a\u63a5\u53e3\u6c42\u89e3\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("isotropic_anisotropic_sample.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
