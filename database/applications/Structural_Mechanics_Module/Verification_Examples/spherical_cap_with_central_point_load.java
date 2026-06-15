/*
 * spherical_cap_with_central_point_load.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:26 by COMSOL 6.3.0.290. */
public class spherical_cap_with_central_point_load {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics().create("shell", "Shell", "geom1");

    model.param().set("a", "10[m]");
    model.param().descr("a", "\u5e3d\u534a\u5f84");
    model.param().set("th", "0.203840[m]");
    model.param().descr("th", "\u5e3d\u539a\u5ea6");
    model.param().set("EE", "210e9[Pa]");
    model.param().descr("EE", "\u6768\u6c0f\u6a21\u91cf");
    model.param().set("Nu", "0.3");
    model.param().descr("Nu", "\u6cca\u677e\u6bd4");
    model.param().set("Rho", "7800");
    model.param().descr("Rho", "\u5bc6\u5ea6");
    model.param().set("disp", "0[m]");
    model.param().descr("disp", "\u4f4d\u79fb\u53c2\u6570");
    model.param().set("meshdist", "4");
    model.param().descr("meshdist", "\u7f51\u683c\u5206\u5e03\u53c2\u6570");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("Fn1", "-F1*a/(EE*th^3*2*pi)");
    model.component("comp1").variable("var1").descr("Fn1", "\u65e0\u91cf\u7eb2\u529b");
    model.component("comp1").variable("var1").set("wn1", "-w/th");
    model.component("comp1").variable("var1").descr("wn1", "\u65e0\u91cf\u7eb2\u4f4d\u79fb");
    model.component("comp1").variable("var1").set("Fn2", "-F2*a/(EE*th^3*2*pi)");
    model.component("comp1").variable("var1").descr("Fn2", "\u65e0\u91cf\u7eb2\u529b");
    model.component("comp1").variable("var1").set("wn2", "-w2/th");
    model.component("comp1").variable("var1").descr("wn2", "\u65e0\u91cf\u7eb2\u4f4d\u79fb");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("type", "curve");
    model.component("comp1").geom("geom1").feature("c1").set("angle", 45);
    model.component("comp1").geom("geom1").feature("c1").set("r", "a+th");
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").feature("c1").set("rot", 45);
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "th", 0);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("c1", 1, 2);
    model.component("comp1").geom("geom1").run("del1");

    model.material().create("mat1", "Common", "");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("matlnk1", "Link");
    model.component("comp1").material().create("matlnk2", "Link");
    model.component("comp1").material("matlnk2").selection().geom("geom1", 1);
    model.component("comp1").material("matlnk2").selection().set(3);
    model.material("mat1").propertyGroup().create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.material("mat1").propertyGroup("Enu").set("E", new String[]{"EE"});
    model.material("mat1").propertyGroup("Enu").set("nu", new String[]{"Nu"});
    model.material("mat1").propertyGroup("def").set("density", new String[]{"Rho"});

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(1);
    model.component("comp1").cpl("intop1").set("method", "summation");

    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().set(2);
    model.component("comp1").physics("solid").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("solid").feature("ge1").setIndex("name", "F1", 0, 0);
    model.component("comp1").physics("solid").feature("ge1").setIndex("equation", "intop1(w)-disp", 0, 0);
    model.component("comp1").physics("solid").feature("ge1").set("DependentVariableQuantity", "force");
    model.component("comp1").physics("solid").feature("ge1").set("SourceTermQuantity", "displacement");
    model.component("comp1").physics("solid").create("pla1", "PointLoadOnAxis", 0);
    model.component("comp1").physics("solid").feature("pla1").selection().set(1);
    model.component("comp1").physics("solid").feature("pla1").set("Fz_src", "root.comp1.F1");
    model.component("comp1").physics("shell").selection().set(3);
    model.component("comp1").physics("shell").feature("to1").set("d", "th");
    model.component("comp1").physics("shell").feature("to1").set("OffsetDefinition", "top");
    model.component("comp1").physics("shell").create("fix1", "Fixed", 0);
    model.component("comp1").physics("shell").feature("fix1").selection().set(3);
    model.component("comp1").physics("shell").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("shell").feature("ge1").setIndex("name", "F2", 0, 0);
    model.component("comp1").physics("shell").feature("ge1").setIndex("equation", "intop1(w2)-disp", 0, 0);
    model.component("comp1").physics("shell").feature("ge1").set("DependentVariableQuantity", "force");
    model.component("comp1").physics("shell").feature("ge1").set("SourceTermQuantity", "displacement");
    model.component("comp1").physics("shell").create("pla1", "PointLoadOnAxis", 0);
    model.component("comp1").physics("shell").feature("pla1").selection().set(1);
    model.component("comp1").physics("shell").feature("pla1").set("Fz_src", "root.comp1.F2");

    model.component("comp1").mesh().create("mesh2");
    model.component("comp1").mesh("mesh1").label("\u7f51\u683c\uff1a\u56fa\u4f53\u529b\u5b66");
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(3, 4);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 40);
    model.component("comp1").mesh("mesh1").run("map1");
    model.component("comp1").mesh("mesh2").label("\u7f51\u683c\uff1a\u58f3");
    model.component("comp1").mesh("mesh2").create("edg1", "Edge");
    model.component("comp1").mesh("mesh2").feature("edg1").selection().set(3);
    model.component("comp1").mesh("mesh2").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh2").feature("edg1").feature("dis1").set("numelem", "meshdist");
    model.component("comp1").mesh("mesh2").run("edg1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/physics/shell", false);
    model.study("std1").label("\u56fa\u4f53\u529b\u5b66");
    model.study("std1").feature("stat").set("geometricNonlinearity", true);
    model.study("std1").feature("stat").setEntry("mesh", "geom1", "mesh1");
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "a", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "a", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "disp", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,-0.01,-6.2)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 621, 0);
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
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def").set("scale", "1");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "w"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().dataset().create("dset1solidrev", "Revolve2D");
    model.result().dataset("dset1solidrev").set("data", "dset1");
    model.result().dataset("dset1solidrev").set("revangle", 225);
    model.result().dataset("dset1solidrev").set("startangle", -90);
    model.result().dataset("dset1solidrev").set("hasspacevars", true);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1solidrev");
    model.result("pg2").setIndex("looplevel", 621, 0);
    model.result("pg2").label("\u5e94\u529b, 3D (solid)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg2").feature("surf1").set("threshold", "manual");
    model.result("pg2").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result().dataset("dset1solidrev").set("hasspacevars", true);
    model.result("pg2").feature("surf1").feature("def").set("revcoordsys", "cylindrical");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").feature("surf1").feature("def").set("descractive", true);
    model.result("pg2").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg2").feature("surf1").feature("def").set("scale", "1");
    model.result("pg1").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", false);
    model.study("std2").feature("stat").setSolveFor("/physics/shell", true);
    model.study("std2").label("\u7814\u7a76\uff1a\u58f3");
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "a", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m", 0);
    model.study("std2").feature("param").setIndex("pname", "a", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m", 0);
    model.study("std2").feature("param").setIndex("pname", "meshdist", 0);
    model.study("std2").feature("param").setIndex("plistarr", "4,8,16", 0);
    model.study("std2").feature("stat").set("geometricNonlinearity", true);
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "a", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "m", 0);
    model.study("std2").feature("stat").setIndex("pname", "a", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "m", 0);
    model.study("std2").feature("stat").setIndex("pname", "disp", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "range(0,-0.01,-6.2)", 0);
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std2");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("p1").run("compute");

    model.result().dataset().create("dset3shellshl", "Shell");
    model.result().dataset("dset3shellshl").set("data", "dset3");
    model.result().dataset("dset3shellshl").setIndex("topconst", "1", 6, 1);
    model.result().dataset("dset3shellshl").setIndex("bottomconst", "-1", 6, 1);
    model.result().dataset("dset3shellshl").setIndex("orientation2expr", "shell.nlR", 0);
    model.result().dataset("dset3shellshl").setIndex("displacement2expr", "arr", 0);
    model.result().dataset("dset3shellshl").setIndex("orientation2expr", "shell.nlZ", 1);
    model.result().dataset("dset3shellshl").setIndex("displacement2expr", "arz", 1);
    model.result().dataset("dset3shellshl").set("distanceexpr", "shell.z_pos");
    model.result().dataset("dset3shellshl").set("seplevels", false);
    model.result().dataset("dset3shellshl").set("resolution", 2);
    model.result().dataset("dset3shellshl").set("areascalefactor", "shell.ASF");
    model.result().dataset("dset3shellshl").set("linescalefactor", "shell.LSF");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset3shellshl");
    model.result("pg3").setIndex("looplevel", 621, 0);
    model.result("pg3").setIndex("looplevel", 3, 1);
    model.result("pg3").label("\u5e94\u529b (shell)");
    model.result("pg3").set("showlegends", true);
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"shell.misesGp"});
    model.result("pg3").feature("surf1").set("threshold", "manual");
    model.result("pg3").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("surf1").set("colortable", "Rainbow");
    model.result("pg3").feature("surf1").set("colortabletrans", "none");
    model.result("pg3").feature("surf1").set("colorscalemode", "linear");
    model.result("pg3").feature("surf1").set("descr", "von Mises \u5e94\u529b");
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result("pg3").feature("surf1").create("def", "Deform");
    model.result("pg3").feature("surf1").feature("def").set("expr", new String[]{"shell.u", "shell.w"});
    model.result("pg3").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg3").feature("surf1").feature("def").set("scale", "1");
    model.result().dataset().create("dset3shellrev", "Revolve2D");
    model.result().dataset("dset3shellrev").set("data", "dset3shellshl");
    model.result().dataset("dset3shellrev").set("revangle", 225);
    model.result().dataset("dset3shellrev").set("startangle", -90);
    model.result().dataset("dset3shellrev").set("hasspacevars", true);
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset3shellrev");
    model.result("pg4").setIndex("looplevel", 621, 0);
    model.result("pg4").setIndex("looplevel", 3, 1);
    model.result("pg4").label("\u5e94\u529b, 3D (shell)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("data", "dset3shellrev");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"shell.misesGp"});
    model.result("pg4").feature("surf1").set("threshold", "manual");
    model.result("pg4").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").feature("surf1").set("colortabletrans", "none");
    model.result("pg4").feature("surf1").set("colorscalemode", "linear");
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result("pg4").feature("surf1").create("def", "Deform");
    model.result("pg4").feature("surf1").feature("def").set("revcoordsys", "cylindrical");
    model.result("pg4").feature("surf1").feature("def").set("expr", new String[]{"shell.u", "shell.v", "shell.w"});
    model.result("pg4").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg4").feature("surf1").feature("def").set("scale", "1");
    model.result("pg3").run();
    model.result().dataset("dset1solidrev").set("startangle", 45);
    model.result().dataset("dset1solidrev").set("revangle", -90);
    model.result().dataset("dset3shellrev").set("startangle", 45);
    model.result().dataset("dset3shellrev").set("revangle", -90);
    model.result("pg1").run();
    model.result("pg1").set("frametype", "material");
    model.result("pg2").run();
    model.result("pg2").label("\u603b\u4f4d\u79fb\uff0c\u4e09\u7ef4\uff08\u5b9e\u4f53\uff09");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u603b\u4f4d\u79fb (m)");
    model.result("pg2").set("edges", false);
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("data", "dset1solidrev");
    model.result("pg2").feature("surf1").setIndex("looplevel", 471, 0);
    model.result("pg2").feature("surf1").set("expr", "solid.disp");
    model.result("pg2").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg2").run();
    model.result("pg2").create("ann1", "Annotation");
    model.result("pg2").feature("ann1").set("data", "dset1solidrev");
    model.result("pg2").feature("ann1").setIndex("looplevel", 471, 0);
    model.result("pg2").feature("ann1").set("text", "F1=eval(F1)");
    model.result("pg2").feature("ann1").set("level", "global");
    model.result("pg2").feature("ann1").set("poszexpr", "a-4.7");
    model.result("pg2").feature("ann1").set("anchorpoint", "lowerright");
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("surf2", "surf1");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("ann2", "ann1");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf2").setIndex("looplevel", 521, 0);
    model.result("pg2").feature("surf2").set("inheritplot", "surf1");
    model.result("pg2").run();
    model.result("pg2").feature("ann2").setIndex("looplevel", 521, 0);
    model.result("pg2").feature("ann2").set("poszexpr", "a-5.2");
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("surf3", "surf2");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("ann3", "ann2");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf3").setIndex("looplevel", 581, 0);
    model.result("pg2").run();
    model.result("pg2").feature("ann3").setIndex("looplevel", 581, 0);
    model.result("pg2").feature("ann3").set("poszexpr", "a-5.8");
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").set("frametype", "material");
    model.result("pg4").run();
    model.result("pg4").label("\u603b\u4f4d\u79fb\uff0c\u4e09\u7ef4\uff08\u58f3\uff09");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "\u603b\u4f4d\u79fb (m)");
    model.result("pg4").set("edges", false);
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("data", "dset3shellrev");
    model.result("pg4").feature("surf1").setIndex("looplevel", 471, 0);
    model.result("pg4").feature("surf1").set("expr", "shell.disp");
    model.result("pg4").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg4").run();
    model.result("pg4").create("ann1", "Annotation");
    model.result("pg4").feature("ann1").set("data", "dset3shellrev");
    model.result("pg4").feature("ann1").setIndex("looplevel", 471, 0);
    model.result("pg4").feature("ann1").set("text", "F2=eval(F2)");
    model.result("pg4").feature("ann1").set("level", "global");
    model.result("pg4").feature("ann1").set("poszexpr", "a-4.7");
    model.result("pg4").feature("ann1").set("anchorpoint", "lowerright");
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("surf2", "surf1");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("ann2", "ann1");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf2").setIndex("looplevel", 521, 0);
    model.result("pg4").feature("surf2").set("inheritplot", "surf1");
    model.result("pg4").run();
    model.result("pg4").feature("ann2").setIndex("looplevel", 521, 0);
    model.result("pg4").feature("ann2").set("poszexpr", "a-5.2");
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("surf3", "surf2");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("ann3", "ann2");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf3").setIndex("looplevel", 581, 0);
    model.result("pg4").run();
    model.result("pg4").feature("ann3").setIndex("looplevel", 581, 0);
    model.result("pg4").feature("ann3").set("poszexpr", "a-5.8");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").setIndex("looplevel", 621, 0);
    model.result("pg5").setIndex("looplevel", 3, 1);
    model.result("pg5").label("\u539a\u5ea6\u548c\u65b9\u5411 (shell)");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").create("line1", "Line");
    model.result("pg5").feature("line1").set("expr", new String[]{"shell.d"});
    model.result("pg5").feature("line1").set("threshold", "manual");
    model.result("pg5").feature("line1").set("thresholdvalue", 0.2);
    model.result("pg5").feature("line1").set("colortable", "HeatCameraLight");
    model.result("pg5").feature("line1").set("colortabletrans", "reverse");
    model.result("pg5").feature("line1").set("colorscalemode", "linear");
    model.result("pg5").feature("line1").set("linetype", "tube");
    model.result("pg5").feature("line1").set("radiusexpr", "shell.d/2");
    model.result("pg5").feature("line1").set("tuberadiusscale", 1);
    model.result("pg5").feature("line1").label("\u539a\u5ea6");
    model.result("pg5").create("syss", "CoordSysLine");
    model.result("pg5").feature("syss").set("sys", "shellsys");
    model.result("pg5").feature("syss").label("\u58f3\u5c40\u90e8\u5750\u6807\u7cfb");
    model.result("pg5").label("\u539a\u5ea6\u548c\u65b9\u5411 (shell)");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("syss").set("arrowcount", 20);
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u5e3d\u51a0\u5904\u8f7d\u8377\u4e0e\u4f4d\u79fb\u7684\u5173\u7cfb");
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("title", "\u5e3d\u51a0\u5904\u8f7d\u8377\u4e0e\u4f4d\u79fb\u7684\u5173\u7cfb");
    model.result("pg6").set("legendpos", "upperleft");
    model.result("pg6").create("ptgr1", "PointGraph");
    model.result("pg6").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg6").feature("ptgr1").set("linewidth", "preference");
    model.result("pg6").feature("ptgr1").selection().set(1);
    model.result("pg6").feature("ptgr1").set("expr", "Fn1");
    model.result("pg6").feature("ptgr1").set("xdata", "expr");
    model.result("pg6").feature("ptgr1").set("xdataexpr", "wn1");
    model.result("pg6").feature("ptgr1").set("legend", true);
    model.result("pg6").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg6").feature("ptgr1")
         .setIndex("legends", "\u56fa\u4f53\u529b\u5b66\uff0c40 \u4e2a\u5355\u5143", 0);
    model.result("pg6").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg6").run();
    model.result("pg6").feature("ptgr2").set("data", "dset3");
    model.result("pg6").feature("ptgr2").set("expr", "Fn2");
    model.result("pg6").feature("ptgr2").set("xdataexpr", "wn2");
    model.result("pg6").feature("ptgr2").set("legendmethod", "evaluated");
    model.result("pg6").feature("ptgr2").set("legendpattern", "\u58f3\uff0ceval(meshdist) \u4e2a\u5355\u5143");
    model.result("pg6").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1")
         .label("\u5e3d\u51a0\u5904\u8f7d\u8377\u4e0e\u4f4d\u79fb\u7684\u5173\u7cfb");
    model.result().evaluationGroup("eg1").create("pev1", "EvalPoint");
    model.result().evaluationGroup("eg1").feature("pev1")
         .label("\u56fa\u4f53\u529b\u5b66\uff0c40 \u4e2a\u5355\u5143");
    model.result().evaluationGroup("eg1").feature("pev1").set("data", "dset1");
    model.result().evaluationGroup("eg1").feature("pev1").selection().set(1);
    model.result().evaluationGroup("eg1").feature("pev1").setIndex("expr", "disp", 0);
    model.result().evaluationGroup("eg1").feature("pev1").setIndex("expr", "Fn1", 1);
    model.result().evaluationGroup("eg1").feature("pev1")
         .setIndex("descr", "\u65e0\u91cf\u7eb2\u529b\uff08\u56fa\u4f53\u529b\u5b66\uff0c40 \u4e2a\u5355\u5143\uff09", 1);
    model.result().evaluationGroup("eg1").feature("pev1").setIndex("expr", "wn1", 2);
    model.result().evaluationGroup("eg1").feature("pev1")
         .setIndex("descr", "\u65e0\u91cf\u7eb2\u4f4d\u79fb\uff08\u56fa\u4f53\u529b\u5b66\uff0c40 \u4e2a\u5355\u5143\uff09", 2);
    model.result().evaluationGroup("eg1").create("pev2", "EvalPoint");
    model.result().evaluationGroup("eg1").feature("pev2").label("\u58f3\uff0c4 \u4e2a\u5355\u5143");
    model.result().evaluationGroup("eg1").feature("pev2").set("data", "dset3");
    model.result().evaluationGroup("eg1").feature("pev2").setIndex("looplevelinput", "manual", 1);
    model.result().evaluationGroup("eg1").feature("pev2").setIndex("looplevel", new int[]{1}, 1);
    model.result().evaluationGroup("eg1").feature("pev2").selection().set(1);
    model.result().evaluationGroup("eg1").feature("pev2").setIndex("expr", "Fn2", 0);
    model.result().evaluationGroup("eg1").feature("pev2")
         .setIndex("descr", "\u65e0\u91cf\u7eb2\u529b\uff08\u58f3\uff0c4 \u4e2a\u5355\u5143\uff09", 0);
    model.result().evaluationGroup("eg1").feature("pev2").setIndex("expr", "wn2", 1);
    model.result().evaluationGroup("eg1").feature("pev2")
         .setIndex("descr", "\u65e0\u91cf\u7eb2\u4f4d\u79fb\uff08\u58f3\uff0c4 \u4e2a\u5355\u5143\uff09", 1);
    model.result().evaluationGroup("eg1").feature().duplicate("pev3", "pev2");
    model.result().evaluationGroup("eg1").feature("pev3").label("\u58f3\uff0c8 \u4e2a\u5355\u5143");
    model.result().evaluationGroup("eg1").feature("pev3").setIndex("looplevel", new int[]{2}, 1);
    model.result().evaluationGroup("eg1").feature("pev3")
         .setIndex("descr", "\u65e0\u91cf\u7eb2\u529b\uff08\u58f3\uff0c8 \u4e2a\u5355\u5143\uff09", 0);
    model.result().evaluationGroup("eg1").feature("pev3")
         .setIndex("descr", "\u65e0\u91cf\u7eb2\u4f4d\u79fb\uff08\u58f3\uff0c8 \u4e2a\u5355\u5143\uff09", 1);
    model.result().evaluationGroup("eg1").feature().duplicate("pev4", "pev3");
    model.result().evaluationGroup("eg1").feature("pev4").label("\u58f3\uff0c16 \u4e2a\u5355\u5143");
    model.result().evaluationGroup("eg1").feature("pev4").setIndex("looplevel", new int[]{3}, 1);
    model.result().evaluationGroup("eg1").feature("pev4")
         .setIndex("descr", "\u65e0\u91cf\u7eb2\u529b\uff08\u58f3\uff0c16 \u4e2a\u5355\u5143\uff09", 0);
    model.result().evaluationGroup("eg1").feature("pev4")
         .setIndex("descr", "\u65e0\u91cf\u7eb2\u4f4d\u79fb\uff08\u58f3\uff0c16 \u4e2a\u5355\u5143\uff09", 1);
    model.result().evaluationGroup("eg1").set("includeparameters", false);
    model.result().evaluationGroup("eg1").run();
    model.result("pg6").run();

    model.title("\u627f\u53d7\u4e2d\u5fc3\u70b9\u8f7d\u8377\u7684\u7403\u5f62\u76d6");

    model
         .description("\u5728\u8fd9\u4e00\u57fa\u51c6\u6a21\u578b\u4e2d\uff0c\u4e00\u4e2a\u7403\u5f62\u76d6\u7684\u4e2d\u5fc3\u53d7\u5230\u70b9\u8f7d\u8377\u4f5c\u7528\u3002\u672c\u4f8b\u4f7f\u7528\u201c\u56fa\u4f53\u529b\u5b66\u201d\u548c\u201c\u58f3\u201d\u63a5\u53e3\u6765\u7814\u7a76\u7531\u6b64\u4ea7\u751f\u7684\u975e\u7ebf\u6027\u53d8\u5f62\u3002\u8fd9\u4e24\u4e2a\u63a5\u53e3\u5747\u4f7f\u7528\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u516c\u5f0f\u3002\n\n\u672c\u4f8b\u5c06\u4e24\u4e2a\u63a5\u53e3\u7684\u5206\u6790\u7ed3\u679c\u4e0e\u8be5\u57fa\u51c6\u7ed9\u51fa\u7684\u4e0d\u540c\u79bb\u6563\u5316\u5f62\u5f0f\u5f97\u5230\u7684\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("spherical_cap_with_central_point_load.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
