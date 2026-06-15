/*
 * angle_crack_plate.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:09 by COMSOL 6.3.0.290. */
public class angle_crack_plate {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Fracture_Mechanics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.param().set("b", "50[mm]");
    model.param().descr("b", "\u534a\u5bbd");
    model.param().set("h0", "1.25*b");
    model.param().descr("h0", "\u534a\u9ad8");
    model.param().set("a", "b*0.5");
    model.param().descr("a", "\u534a\u88c2\u7eb9\u957f\u5ea6");
    model.param().set("beta", "90[deg]");
    model.param().descr("beta", "\u88c2\u7eb9\u89d2");
    model.param().set("load", "100[MPa]");
    model.param().descr("load", "\u5916\u52a0\u8f7d\u8377");
    model.param().set("K0", "load/1[N/m^2]*sqrt(pi*a/1[m])");
    model.param().descr("K0", "\u76ee\u6807\u5e94\u529b\u5f3a\u5ea6\u56e0\u5b50");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"2*b", "2*h0"});
    model.component("comp1").geom("geom1").feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new String[]{"-a", "0"});
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"a", "0"});
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("ls1");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", "90-beta");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"207[GPa]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"8000"});

    model.component("comp1").physics("solid").create("crack1", "Crack", 1);
    model.component("comp1").physics("solid").feature("crack1").selection().set(4);
    model.component("comp1").physics("solid").feature("crack1").create("jint1", "JIntegral", 0);
    model.component("comp1").physics("solid").feature("crack1").create("jint2", "JIntegral", 0);
    model.component("comp1").physics("solid").feature("crack1").feature("jint2").selection().set(4);
    model.component("comp1").physics("solid").create("roll1", "Roller", 1);
    model.component("comp1").physics("solid").feature("roll1").selection().set(2);
    model.component("comp1").physics("solid").create("disp1", "Displacement0", 0);
    model.component("comp1").physics("solid").feature("disp1").selection().set(5);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(3);
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"0", "load", "0"});

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", "a/20");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "b", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "b", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "beta", 0);
    model.study("std1").feature("param").setIndex("plistarr", "90 67.5 22.5", 0);
    model.study("std1").feature("param").setIndex("punit", "deg", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 3, 0);
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("resolution", "normal");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").set("smooth", "none");
    model.result("pg1").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("surf1").set("colorcalibration", -1);
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"displacement", "\u4f4d\u79fb", "mm", "mm"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "\u00b5m", 0, 3);
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 1);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 1, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("expr", "1");
    model.result("pg1").feature("line1").set("titletype", "none");
    model.result("pg1").feature("line1").set("coloring", "uniform");
    model.result("pg1").feature("line1").set("color", "black");
    model.result("pg1").feature("line1").set("inheritplot", "surf1");
    model.result("pg1").feature("line1").set("inheritcolor", false);
    model.result("pg1").feature("line1").set("inheritrange", false);
    model.result("pg1").feature("line1").set("inheritheightscale", false);
    model.result("pg1").feature("line1").set("inherittubescale", false);
    model.result("pg1").feature("line1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("line1").create("sel1", "Selection");
    model.result("pg1").feature("line1").feature("sel1").selection().set(1, 2, 3, 5);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").set("edges", false);
    model.result("pg1").run();
    model.result("pg1").stepFirst(0);
    model.result("pg1").run();
    model.result("pg1").stepNext(0);
    model.result("pg1").run();
    model.result("pg1").stepNext(0);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 3, 0);
    model.result("pg2").label("\u88c2\u7eb9 (solid)");
    model.result("pg2").set("showlegends", true);
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("frametype", "material");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").create("arpt1", "ArrowPoint");
    model.result("pg2").feature("arpt1")
         .set("expr", new String[]{"solid.crack1.e1X*solid.crack1.crackSize ", "solid.crack1.e1Y*solid.crack1.crackSize "});
    model.result("pg2").feature("arpt1").set("scaleactive", true);
    model.result("pg2").feature("arpt1").set("scale", "1");
    model.result("pg2").feature("arpt1").label("\u88c2\u7eb9\u6269\u5c55\u65b9\u5411 (\u88c2\u7eb9 1)");
    model.result("pg2").create("con1", "Contour");
    model.result("pg2").feature("con1").set("expr", "sqrt((X-solid.crack1.jint1.Xp)^2+(Y-solid.crack1.jint1.Yp)^2)");
    model.result("pg2").feature("con1").set("levelmethod", "levels");
    model.result("pg2").feature("con1").set("levels", new String[]{"solid.crack1.jint1.r"});
    model.result("pg2").feature("con1").set("coloring", "uniform");
    model.result("pg2").feature("con1").set("color", "magenta");
    model.result("pg2").feature("con1").set("colorlegend", false);
    model.result("pg2").feature("con1").label("J \u79ef\u5206 1, \u79ef\u5206\u8def\u5f84");
    model.result("pg2").create("ann1", "Annotation");
    model.result("pg2").feature("ann1").set("text", "J=eval(solid.crack1.jint1.J)");
    model.result("pg2").feature("ann1").set("posxexpr", "solid.crack1.jint1.Xp");
    model.result("pg2").feature("ann1").set("posyexpr", "solid.crack1.jint1.Yp");
    model.result("pg2").feature("ann1").label("J \u79ef\u5206 1, \u8ba1\u7b97");
    model.result("pg2").create("con2", "Contour");
    model.result("pg2").feature("con2").set("expr", "sqrt((X-solid.crack1.jint2.Xp)^2+(Y-solid.crack1.jint2.Yp)^2)");
    model.result("pg2").feature("con2").set("levelmethod", "levels");
    model.result("pg2").feature("con2").set("levels", new String[]{"solid.crack1.jint2.r"});
    model.result("pg2").feature("con2").set("coloring", "uniform");
    model.result("pg2").feature("con2").set("color", "magenta");
    model.result("pg2").feature("con2").set("colorlegend", false);
    model.result("pg2").feature("con2").label("J \u79ef\u5206 2, \u79ef\u5206\u8def\u5f84");
    model.result("pg2").create("ann2", "Annotation");
    model.result("pg2").feature("ann2").set("text", "J=eval(solid.crack1.jint2.J)");
    model.result("pg2").feature("ann2").set("posxexpr", "solid.crack1.jint2.Xp");
    model.result("pg2").feature("ann2").set("posyexpr", "solid.crack1.jint2.Yp");
    model.result("pg2").feature("ann2").label("J \u79ef\u5206 2, \u8ba1\u7b97");
    model.result("pg2").label("\u88c2\u7eb9 (solid)");
    model.result("pg2").run();
    model.result().evaluationGroup().create("eg_dset2solid", "EvaluationGroup");
    model.result().evaluationGroup("eg_dset2solid").label("\u65ad\u88c2\u529b\u5b66\u7ed3\u679c (solid)");
    model.result().evaluationGroup("eg_dset2solid").set("data", "dset2");
    model.result().evaluationGroup("eg_dset2solid").set("transpose", true);
    model.result().evaluationGroup("eg_dset2solid").create("jint", "EvalGlobal");
    model.result().evaluationGroup("eg_dset2solid").feature("jint").label("J \u79ef\u5206");
    model.result().evaluationGroup("eg_dset2solid").feature("jint").setIndex("expr", "solid.crack1.jint1.J", 0);
    model.result().evaluationGroup("eg_dset2solid").feature("jint")
         .setIndex("descr", "J \u79ef\u5206 [crack1/jint1]", 0);
    model.result().evaluationGroup("eg_dset2solid").feature("jint").setIndex("expr", "solid.crack1.jint2.J", 1);
    model.result().evaluationGroup("eg_dset2solid").feature("jint")
         .setIndex("descr", "J \u79ef\u5206 [crack1/jint2]", 1);
    model.result().evaluationGroup("eg_dset2solid").create("sif1", "EvalGlobal");
    model.result().evaluationGroup("eg_dset2solid").feature("sif1")
         .label("\u5e94\u529b\u5f3a\u5ea6\u56e0\u5b50\uff0c\u6a21\u5f0f\u201c1\u201d");
    model.result().evaluationGroup("eg_dset2solid").feature("sif1").setIndex("expr", "solid.crack1.jint1.KI", 0);
    model.result().evaluationGroup("eg_dset2solid").feature("sif1")
         .setIndex("descr", "\u5e94\u529b\u5f3a\u5ea6\u56e0\u5b50\uff0c\u6a21\u5f0f\u201cI\u201d [crack1/jint1]", 0);
    model.result().evaluationGroup("eg_dset2solid").feature("sif1").setIndex("expr", "solid.crack1.jint2.KI", 1);
    model.result().evaluationGroup("eg_dset2solid").feature("sif1")
         .setIndex("descr", "\u5e94\u529b\u5f3a\u5ea6\u56e0\u5b50\uff0c\u6a21\u5f0f\u201cI\u201d [crack1/jint2]", 1);
    model.result().evaluationGroup("eg_dset2solid").create("sif2", "EvalGlobal");
    model.result().evaluationGroup("eg_dset2solid").feature("sif2")
         .label("\u5e94\u529b\u5f3a\u5ea6\u56e0\u5b50\uff0c\u6a21\u5f0f\u201c2\u201d");
    model.result().evaluationGroup("eg_dset2solid").feature("sif2").setIndex("expr", "solid.crack1.jint1.KII", 0);
    model.result().evaluationGroup("eg_dset2solid").feature("sif2")
         .setIndex("descr", "\u5e94\u529b\u5f3a\u5ea6\u56e0\u5b50\uff0c\u6a21\u5f0f\u201cII\u201d [crack1/jint1]", 0);
    model.result().evaluationGroup("eg_dset2solid").feature("sif2").setIndex("expr", "solid.crack1.jint2.KII", 1);
    model.result().evaluationGroup("eg_dset2solid").feature("sif2")
         .setIndex("descr", "\u5e94\u529b\u5f3a\u5ea6\u56e0\u5b50\uff0c\u6a21\u5f0f\u201cII\u201d [crack1/jint2]", 1);
    model.result().evaluationGroup("eg_dset2solid").label("\u65ad\u88c2\u529b\u5b66\u7ed3\u679c (solid)");
    model.result("pg2").run();
    model.result("pg2").feature("arpt1").set("scaleactive", false);
    model.result("pg2").run();
    model.result("pg2").stepFirst(0);
    model.result("pg2").run();
    model.result("pg2").stepNext(0);
    model.result("pg2").run();
    model.result("pg2").stepNext(0);
    model.result("pg2").run();
    model.result().evaluationGroup("eg_dset2solid").feature("sif1").setIndex("expr", "solid.crack1.jint1.KI/K0", 0);
    model.result().evaluationGroup("eg_dset2solid").feature("sif1").setIndex("expr", "solid.crack1.jint2.KI/K0", 1);
    model.result().evaluationGroup("eg_dset2solid").feature("sif2").setIndex("expr", "solid.crack1.jint1.KII/K0", 0);
    model.result().evaluationGroup("eg_dset2solid").feature("sif2").setIndex("expr", "solid.crack1.jint2.KII/K0", 1);
    model.result().evaluationGroup("eg_dset2solid").run();
    model.result("pg1").run();
    model.result().duplicate("pg3", "pg1");
    model.result("pg3").run();
    model.result("pg3").label("\u5e94\u529b\uff0c\u591a\u4e2a\u89d2");
    model.result("pg3").set("showlegends", false);
    model.result("pg3").set("plotarrayenable", true);
    model.result("pg3").feature("surf1").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("data", "dset2");
    model.result("pg3").feature("surf1").setIndex("looplevel", 1, 0);
    model.result("pg3").feature("line1").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("line1").set("data", "dset2");
    model.result("pg3").feature("line1").setIndex("looplevel", 1, 0);
    model.result("pg3").feature("line1").set("belongstoplotarray", false);
    model.result("pg3").feature("surf1").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("surf2", "surf1");
    model.result("pg3").feature().duplicate("line2", "line1");
    model.result("pg3").feature("surf2").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").setIndex("looplevel", 2, 0);
    model.result("pg3").feature("surf2").set("titletype", "none");
    model.result("pg3").feature("surf2").set("inheritplot", "surf1");
    model.result("pg3").feature("line2").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("line2").setIndex("looplevel", 2, 0);
    model.result("pg3").feature("line2").set("belongstoplotarray", true);
    model.result("pg3").feature("line2").set("manualindexing", true);
    model.result("pg3").feature("line2").set("arrayindex", 1);
    model.result("pg3").feature("surf2").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("surf3", "surf2");
    model.result("pg3").feature().duplicate("line3", "line2");
    model.result("pg3").feature("surf3").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("surf3").setIndex("looplevel", 3, 0);
    model.result("pg3").feature("surf3").set("manualindexing", true);
    model.result("pg3").feature("surf3").set("arrayindex", 2);
    model.result("pg3").feature("line3").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("line3").setIndex("looplevel", 3, 0);
    model.result("pg3").feature("line3").set("arrayindex", 2);
    model.result("pg3").run();
    model.result("pg3").set("view", "new");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u88c2\u7eb9\u4f4d\u79fb");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").selection().set(4);
    model.result("pg4").feature("lngr1").set("expr", "solid.crack1.jint1.delta_u1");
    model.result("pg4").feature("lngr1").set("descr", "\u88c2\u7eb9\u5f00\u53e3\u4f4d\u79fb");
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("legendprefix", "\u5f00\u53e3\uff0c\\beta =");
    model.result("pg4").run();
    model.result("pg4").create("lngr2", "LineGraph");
    model.result("pg4").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr2").set("linewidth", "preference");
    model.result("pg4").feature("lngr2").selection().set(4);
    model.result("pg4").feature("lngr2").set("expr", "solid.crack1.jint1.delta_u2");
    model.result("pg4").feature("lngr2").set("descr", "\u88c2\u7eb9\u6ed1\u52a8\u4f4d\u79fb");
    model.result("pg4").feature("lngr2").set("linestyle", "dashed");
    model.result("pg4").feature("lngr2").set("linecolor", "cyclereset");
    model.result("pg4").feature("lngr2").set("legend", true);
    model.result("pg4").feature("lngr2").set("legendprefix", "\u6ed1\u52a8\uff0c\\beta=");
    model.result("pg4").run();
    model.result("pg3").run();

    model.title("\u5e26\u89d2\u88c2\u7eb9\u7684\u677f");

    model
         .description("\u672c\u6a21\u578b\u53ef\u4ee5\u91cd\u73b0\u4e00\u4e2a NAFEMS \u57fa\u51c6\u95ee\u9898\uff0c\u5176\u4e2d\u5177\u6709\u89d2\u88c2\u7eb9\u7684\u677f\u53d7\u5230\u62c9\u4f38\u8f7d\u8377\u3002\u901a\u8fc7\u8ba1\u7b97 J \u79ef\u5206\uff0c\u5e76\u9488\u5bf9\u591a\u4e2a\u89d2\u5ea6\u503c\u7684\u60c5\u51b5\uff0c\u5c06\u6a21\u5f0f I \u548c\u6a21\u5f0f II \u7684\u5e94\u529b\u5f3a\u5ea6\u56e0\u5b50\u4e0e\u57fa\u51c6\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.label("angle_crack_plate.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
