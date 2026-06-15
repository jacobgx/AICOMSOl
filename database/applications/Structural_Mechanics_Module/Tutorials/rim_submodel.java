/*
 * rim_submodel.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:21 by COMSOL 6.3.0.290. */
public class rim_submodel {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.param().set("pInflation", "2[bar]");
    model.param().descr("pInflation", "\u81a8\u80c0\u538b\u529b");
    model.param().set("tireLoad", "1120[kg]*g_const");
    model.param().descr("tireLoad", "\u8f6e\u4e0a\u7684\u8f7d\u8377");
    model.param().set("spokeNo", "0");
    model.param().descr("spokeNo", "\u8f90\u6761\u9009\u62e9");
    model.param().set("spokeAngle", "spokeNo*2*pi[rad]/5");
    model.param().descr("spokeAngle", "\u9009\u5b9a\u8f90\u6761\u7684\u65cb\u8f6c\u89d2\u5ea6");
    model.param().set("phiLoad", "0[deg]");
    model.param().descr("phiLoad", "\u5cf0\u503c\u8f7d\u8377\u89d2");
    model.param().set("numLpos", "4");
    model.param().descr("numLpos", "\u7b2c\u4e00\u6247\u533a\u4e2d\u7684\u8f7d\u8377\u4f4d\u7f6e\u6570\u91cf");
    model.param().set("angleStep", "360[deg]/(5*numLpos)");
    model.param().descr("angleStep", "\u5cf0\u503c\u8f7d\u8377\u89d2\u9636\u8dc3 [deg]");
    model.param().set("angleLast", "angleStep*(numLpos-1)");
    model.param().descr("angleLast", "\u6700\u540e\u5cf0\u503c\u8f7d\u8377\u89d2 [deg]");

    model.component("comp1").geom("geom1").insertFile("wheel_rim_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("cmd1");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u8f6e\u80ce\u9644\u4ef6");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(2, 3, 4, 6);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u538b\u529b\u9762");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(2, 3, 4, 5, 6);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u56fa\u5b9a\u5230\u8f6e\u6bc2");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").set(8, 9, 10, 11, 12);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat1").label("Aluminum");
    model.component("comp1").material("mat1").set("family", "aluminum");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "-350[GPa]");

    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().named("sel3");
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").selection().named("sel2");
    model.component("comp1").physics("solid").feature("bndl1").set("forceType", "FollowerPressure");
    model.component("comp1").physics("solid").feature("bndl1").set("pressure", "pInflation");

    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").set("expr", "(abs(atan2(x,y)-phi)<pi/6)*cos(3*(atan2(x,y)-phi))");
    model.component("comp1").func("an1").set("args", "x, y, phi");
    model.component("comp1").func("an1").setIndex("argunit", "m", 0);
    model.component("comp1").func("an1").setIndex("argunit", "m", 1);
    model.component("comp1").func("an1").setIndex("argunit", "rad", 2);
    model.component("comp1").func("an1").set("fununit", "Pa");
    model.component("comp1").func("an1").set("funcname", "loadDistr");

    model.component("comp1").coordSystem().create("sys2", "Cylindrical");

    model.component("comp1").physics("solid").create("bndl2", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl2").set("coordinateSystem", "sys2");
    model.component("comp1").physics("solid").feature("bndl2").selection().named("sel1");
    model.component("comp1").physics("solid").feature("bndl2")
         .set("forceReferenceArea", new String[]{"-loadAmpl*loadDistr(X,Y,phiLoad)", "0", "0.2*loadAmpl*loadDistr(X,Y,phiLoad)*(2*(Z>0)-1)"});

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().named("sel1");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("loadAmpl", "tireLoad/intop1(loadDistr(X,Y,0)*cos(atan2(X,Y)))");
    model.component("comp1").variable("var1").descr("loadAmpl", "\u8f7d\u8377\u5e45\u503c");

    model.component("comp1").mesh("mesh1").autoMeshSize(8);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "pInflation", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std1").feature("stat").setIndex("pname", "pInflation", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std1").feature("stat").setIndex("pname", "phiLoad", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,angleStep,angleLast)", 0);
    model.study("std1").feature("stat").setIndex("punit", "deg", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("i1").active(true);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 4, 0);
    model.result("pg1").set("defaultPlotID", "stress");
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("vol1").set("threshold", "manual");
    model.result("pg1").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("vol1").set("colortable", "Rainbow");
    model.result("pg1").feature("vol1").set("colortabletrans", "none");
    model.result("pg1").feature("vol1").set("colorscalemode", "linear");
    model.result("pg1").feature("vol1").set("resolution", "custom");
    model.result("pg1").feature("vol1").set("refine", 2);
    model.result("pg1").feature("vol1").set("colortable", "Prism");
    model.result("pg1").feature("vol1").create("def", "Deform");
    model.result("pg1").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").run();
    model.result("pg1").feature("vol1").set("unit", "MPa");
    model.result("pg1").feature("vol1").set("rangecoloractive", true);
    model.result("pg1").feature("vol1").set("rangecolormax", 90);

    model.component("comp1").view().create("view2", "geom1");
    model.component("comp1").view("view2").set("locked", true);

    model.result("pg1").run();
    model.result("pg1").set("view", "view2");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 4, 0);
    model.result("pg2").set("defaultPlotID", "boundaryLoads");
    model.result("pg2").label("\u8fb9\u754c\u8f7d\u8377 (solid)");
    model.result("pg2").set("showlegends", true);
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"1"});
    model.result("pg2").feature("surf1").label("\u7070\u8272\u8868\u9762");
    model.result("pg2").feature("surf1").set("coloring", "uniform");
    model.result("pg2").feature("surf1").set("color", "gray");
    model.result("pg2").feature("surf1").set("inheritcolor", false);
    model.result("pg2").feature("surf1").set("inheritrange", false);
    model.result("pg2").feature("surf1").set("inherittransparency", false);
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg2").feature("surf1").feature("def").set("scale", 0);
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg2").feature("surf1").feature("sel1").selection().set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
    model.result("pg2").feature("surf1").create("tran1", "Transparency");
    model.result("pg2").feature("surf1").feature("tran1").set("transparency", 0.8);
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1")
         .set("expr", new String[]{"solid.bndl1.fax", "solid.bndl1.fay", "solid.bndl1.faz"});
    model.result("pg2").feature("arws1").set("placement", "gausspoints");
    model.result("pg2").feature("arws1").set("arrowbase", "head");
    model.result("pg2").feature("arws1").label("\u8fb9\u754c\u8f7d\u8377 1");
    model.result("pg2").feature("arws1").set("inheritplot", "none");
    model.result("pg2").feature("arws1").create("col", "Color");
    model.result("pg2").feature("arws1").feature("col").set("colortable", "Rainbow");
    model.result("pg2").feature("arws1").feature("col").set("colortabletrans", "none");
    model.result("pg2").feature("arws1").feature("col").set("colorscalemode", "linear");
    model.result("pg2").feature("arws1").feature("col").set("colordata", "arrowlength");
    model.result("pg2").feature("arws1").feature("col").set("coloring", "gradient");
    model.result("pg2").feature("arws1").feature("col").set("topcolor", "red");
    model.result("pg2").feature("arws1").feature("col").set("bottomcolor", "custom");
    model.result("pg2").feature("arws1").feature("col")
         .set("custombottomcolor", new double[]{0.5882353186607361, 0.5137255191802979, 0.5176470875740051});
    model.result("pg2").feature("arws1").set("color", "red");
    model.result("pg2").feature("arws1").create("def", "Deform");
    model.result("pg2").feature("arws1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("arws1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").feature("arws1").feature("def").set("scaleactive", true);
    model.result("pg2").feature("arws1").feature("def").set("scale", 0);
    model.result("pg2").feature().move("surf1", 1);
    model.result("pg2").create("arws2", "ArrowSurface");
    model.result("pg2").feature("arws2")
         .set("expr", new String[]{"solid.bndl2.fax", "solid.bndl2.fay", "solid.bndl2.faz"});
    model.result("pg2").feature("arws2").set("placement", "gausspoints");
    model.result("pg2").feature("arws2").set("arrowbase", "tail");
    model.result("pg2").feature("arws2").label("\u8fb9\u754c\u8f7d\u8377 2");
    model.result("pg2").feature("arws2").set("inheritplot", "arws1");
    model.result("pg2").feature("arws2").create("col", "Color");
    model.result("pg2").feature("arws2").feature("col").set("colortable", "Rainbow");
    model.result("pg2").feature("arws2").feature("col").set("colortabletrans", "none");
    model.result("pg2").feature("arws2").feature("col").set("colorscalemode", "linear");
    model.result("pg2").feature("arws2").feature("col").set("colordata", "arrowlength");
    model.result("pg2").feature("arws2").feature("col").set("coloring", "gradient");
    model.result("pg2").feature("arws2").feature("col").set("topcolor", "red");
    model.result("pg2").feature("arws2").feature("col").set("bottomcolor", "custom");
    model.result("pg2").feature("arws2").feature("col")
         .set("custombottomcolor", new double[]{0.5882353186607361, 0.5137255191802979, 0.5176470875740051});
    model.result("pg2").feature("arws2").set("color", "red");
    model.result("pg2").feature("arws2").create("def", "Deform");
    model.result("pg2").feature("arws2").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("arws2").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").feature("arws2").feature("def").set("scaleactive", true);
    model.result("pg2").feature("arws2").feature("def").set("scale", 0);
    model.result("pg2").feature().move("surf1", 2);
    model.result("pg2").label("\u8fb9\u754c\u8f7d\u8377 (solid)");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("arws1").set("scaleactive", true);
    model.result("pg2").feature("arws1").set("scale", "4E-8");
    model.result("pg2").run();

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("comsol");

    model.component("comp2").mesh().create("mesh2");
    model.component("comp2").mesh("mesh2").contribute("geom/detail", true);

    model.component("comp2").geom("geom2").insertFile("wheel_rim_geom_sequence.mph", "geom1");
    model.component("comp2").geom("geom2").run("cmd1");
    model.component("comp2").geom("geom2").feature("rot1").active(false);
    model.component("comp2").geom("geom2").run("cmd1");
    model.component("comp2").geom("geom2").run("rot1");
    model.component("comp2").geom("geom2").create("blk1", "Block");
    model.component("comp2").geom("geom2").feature("blk1").set("size", new String[]{"6e-2", "7e-2", "6e-2"});
    model.component("comp2").geom("geom2").feature("blk1").set("pos", new String[]{"0", "6.5e-2", "6e-2"});
    model.component("comp2").geom("geom2").run("blk1");
    model.component("comp2").geom("geom2").create("int1", "Intersection");
    model.component("comp2").geom("geom2").feature("int1").selection("input").set("blk1", "imp1");
    model.component("comp2").geom("geom2").run("cmd1");

    model.component("comp2").material().create("mat2", "Common");
    model.component("comp2").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp2").material("mat2").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp2").material("mat2").label("Aluminum");
    model.component("comp2").material("mat2").set("family", "aluminum");
    model.component("comp2").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat2").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp2").material("mat2").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp2").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp2").material("mat2").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp2").material("mat2").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp2").material("mat2").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp2").material("mat2").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp2").material("mat2").propertyGroup("Murnaghan").set("n", "-350[GPa]");

    model.component("comp1").cpl().create("genext1", "GeneralExtrusion");
    model.component("comp1").cpl("genext1").set("opname", "from_global");
    model.component("comp1").cpl("genext1").selection().all();
    model.component("comp1").cpl("genext1").set("srcframe", "material");
    model.component("comp1").cpl("genext1")
         .set("dstmap", new String[]{"X*cos(spokeAngle)-Y*sin(spokeAngle)", "y", "z"});
    model.component("comp1").cpl("genext1").setIndex("dstmap", "Y*cos(spokeAngle)+X*sin(spokeAngle)", 1);
    model.component("comp1").cpl("genext1").setIndex("dstmap", "Z", 2);
    model.component("comp1").cpl("genext1").set("exttol", 0.5);

    model.component("comp2").physics().create("solid2", "SolidMechanics", "geom2");

    model.study("std1").feature("stat").setSolveFor("/physics/solid2", true);

    model.component("comp2").physics("solid2").create("fix1", "Fixed", 2);
    model.component("comp2").physics("solid2").feature("fix1").selection().set(3);
    model.component("comp2").physics("solid2").create("disp1", "Displacement2", 2);
    model.component("comp2").physics("solid2").feature("disp1").selection().set(1, 6, 7, 8);
    model.component("comp2").physics("solid2").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp2").physics("solid2").feature("disp1")
         .setIndex("U0", "comp1.from_global(comp1.u*cos(spokeAngle)+comp1.v*sin(spokeAngle))", 0);
    model.component("comp2").physics("solid2").feature("disp1").setIndex("Direction", "prescribed", 1);
    model.component("comp2").physics("solid2").feature("disp1")
         .setIndex("U0", "comp1.from_global(comp1.v*cos(spokeAngle)-comp1.u*sin(spokeAngle))", 1);
    model.component("comp2").physics("solid2").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp2").physics("solid2").feature("disp1").setIndex("U0", "comp1.from_global(comp1.w)", 2);

    model.component("comp2").mesh("mesh2").autoMeshSize(4);
    model.component("comp2").mesh("mesh2").automatic(false);
    model.component("comp2").mesh("mesh2").feature("ftet1").create("size1", "Size");
    model.component("comp2").mesh("mesh2").feature("ftet1").feature("size1").selection().geom("geom2", 2);
    model.component("comp2").mesh("mesh2").feature("ftet1").feature("size1").selection().set(5);
    model.component("comp2").mesh("mesh2").feature("ftet1").feature("size1").set("hauto", 1);
    model.component("comp2").mesh("mesh2").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", false);
    model.study("std2").feature("stat").setSolveFor("/physics/solid2", true);
    model.study("std2").feature("stat").set("usesol", true);
    model.study("std2").feature("stat").set("notsolmethod", "sol");
    model.study("std2").feature("stat").set("notstudy", "std1");
    model.study("std2").feature("stat").set("notsolnum", "all");
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").set("sweeptype", "filled");
    model.study("std2").feature("stat").setIndex("pname", "pInflation", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std2").feature("stat").setIndex("pname", "pInflation", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std2").feature("stat").setIndex("pname", "spokeNo", 0);
    model.study("std2").feature("stat").setIndex("pname", "pInflation", 1);
    model.study("std2").feature("stat").setIndex("plistarr", "", 1);
    model.study("std2").feature("stat").setIndex("punit", "Pa", 1);
    model.study("std2").feature("stat").setIndex("pname", "pInflation", 1);
    model.study("std2").feature("stat").setIndex("plistarr", "", 1);
    model.study("std2").feature("stat").setIndex("punit", "Pa", 1);
    model.study("std2").feature("stat").setIndex("plistarr", "range(0,1,4)", 0);
    model.study("std2").feature("stat").setIndex("pname", "phiLoad", 1);
    model.study("std2").feature("stat").setIndex("plistarr", "range(0,angleStep,angleLast)", 1);
    model.study("std2").feature("stat").setIndex("punit", "deg", 1);
    model.study("std2").feature("stat").setEntry("outputmap", "solid", "none");
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("s1").feature("i1").active(true);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").set("geomuse", new String[]{"geom2"});
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("geomuse", new String[]{"geom1"});

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").setIndex("looplevel", 4, 0);
    model.result("pg3").setIndex("looplevel", 5, 1);
    model.result("pg3").set("defaultPlotID", "stress");
    model.result("pg3").label("\u5e94\u529b (solid2)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("expr", new String[]{"solid2.misesGp"});
    model.result("pg3").feature("vol1").set("threshold", "manual");
    model.result("pg3").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("vol1").set("colortable", "Rainbow");
    model.result("pg3").feature("vol1").set("colortabletrans", "none");
    model.result("pg3").feature("vol1").set("colorscalemode", "linear");
    model.result("pg3").feature("vol1").set("resolution", "custom");
    model.result("pg3").feature("vol1").set("refine", 2);
    model.result("pg3").feature("vol1").set("colortable", "Prism");
    model.result("pg3").feature("vol1").create("def", "Deform");
    model.result("pg3").feature("vol1").feature("def").set("expr", new String[]{"u2", "v2", "w2"});
    model.result("pg3").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("vol1").set("unit", "MPa");
    model.result("pg3").feature("vol1").set("rangecoloractive", true);
    model.result("pg3").feature("vol1").set("rangecolormin", 0);
    model.result("pg3").feature("vol1").set("rangecolormax", 90);
    model.result("pg3").feature("vol1").create("mrkr1", "Marker");
    model.result("pg3").run();
    model.result("pg3").feature("vol1").feature("mrkr1").set("display", "max");
    model.result("pg3").feature("vol1").feature("mrkr1").set("precision", 3);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 1, 1);
    model.result("pg3").setIndex("looplevel", 2, 0);
    model.result("pg3").create("pris1", "PrincipalSurface");
    model.result("pg3").feature("pris1").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("pris1").set("inheritplot", "vol1");
    model.result("pg3").feature("pris1").set("inheritarrowscale", false);
    model.result("pg3").feature("pris1").set("inheritcolor", false);
    model.result("pg3").feature("pris1").set("inheritrange", false);
    model.result("pg3").run();
    model.result("pg3").label("\u5b50\u6a21\u578b\u4e2d\u7684\u5e94\u529b");

    model.component("comp2").view().create("view4", "geom2");
    model.component("comp2").view("view4").set("showgrid", false);
    model.component("comp2").view("view4").set("locked", true);

    model.result("pg3").run();
    model.result("pg3").set("view", "view4");
    model.result("pg3").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("fontsize", "9");
    model.result().export("anim1").set("colortheme", "globaltheme");
    model.result().export("anim1").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim1").set("background", "color");
    model.result().export("anim1").set("gltfincludelines", "on");
    model.result().export("anim1").set("title1d", "on");
    model.result().export("anim1").set("legend1d", "on");
    model.result().export("anim1").set("logo1d", "on");
    model.result().export("anim1").set("options1d", "on");
    model.result().export("anim1").set("title2d", "on");
    model.result().export("anim1").set("legend2d", "on");
    model.result().export("anim1").set("logo2d", "on");
    model.result().export("anim1").set("options2d", "off");
    model.result().export("anim1").set("title3d", "on");
    model.result().export("anim1").set("legend3d", "on");
    model.result().export("anim1").set("logo3d", "on");
    model.result().export("anim1").set("options3d", "off");
    model.result().export("anim1").set("axisorientation", "on");
    model.result().export("anim1").set("grid", "on");
    model.result().export("anim1").set("axes1d", "on");
    model.result().export("anim1").set("axes2d", "on");
    model.result().export("anim1").set("showgrid", "on");
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("plotgroup", "pg3");
    model.result().export("anim1").set("solnumtype", "inner");
    model.result().export("anim1")
         .set("solnum", new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20});
    model.result().export("anim1").set("framesel", "all");
    model.result().export("anim1").run();

    model.study("std1").feature("stat").setSolveFor("/physics/solid2", false);

    model.result("pg3").run();

    model.title("\u8f6e\u8f8b\u5b50\u6a21\u578b");

    model
         .description("\u672c\u6559\u7a0b\u4f7f\u7528\u5b50\u6a21\u578b\u6280\u672f\u7cbe\u786e\u6c42\u89e3\u8f6e\u8f8b\u4e2d\u7684\u5e94\u529b\u96c6\u4e2d\u3002\u9996\u5148\uff0c\u901a\u8fc7\u6c42\u89e3\u5168\u5c40\u6a21\u578b\u5f97\u5230\u4f4d\u79fb\uff0c\u7136\u540e\u5c06\u5176\u4f5c\u4e3a\u8fb9\u754c\u6761\u4ef6\uff0c\u5e94\u7528\u4e8e\u53d1\u751f\u5e94\u529b\u96c6\u4e2d\u7684\u533a\u57df\u5c40\u90e8\u6a21\u578b\u4e2d\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("rim_submodel.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
