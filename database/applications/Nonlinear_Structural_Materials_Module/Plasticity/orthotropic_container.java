/*
 * orthotropic_container.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:34 by COMSOL 6.3.0.290. */
public class orthotropic_container {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Plasticity");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("cc", "CurvilinearCoordinates", "geom1");
    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/cc", true);
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("pressure", "1[N/m^2]", "\u5185\u538b");
    model.param().set("th", "2[cm]", "\u58c1\u539a");
    model.param().set("De", "52[cm]", "\u5916\u90e8\u5706\u67f1\u76f4\u5f84");
    model.param().set("Di", "De-2*th", "\u5185\u90e8\u5706\u67f1\u76f4\u5f84");
    model.param().set("Ri", "Di/2", "\u5185\u90e8\u5706\u67f1\u534a\u5f84");
    model.param().set("sf", "3.5*th", "\u76f4\u8fb9\u6cd5\u5170\u9ad8\u5ea6");
    model.param().set("Rk", "0.1*De", "\u5185\u90e8\u8f6c\u5411\u8282\u534a\u5f84");
    model.param().set("Rc", "0.9*Di", "\u5185\u90e8\u51a0\u534a\u5f84");
    model.param().set("hi", "Rc-sqrt((Rc-Ri)*(Rc+Ri-2*Rk))", "\u5185\u90e8\u5c01\u5934\u9ad8\u5ea6");
    model.param()
         .set("alpha", "atan((Ri-Rk)/(Rc-hi))", "\u51a0\u4e0e\u8f6c\u5411\u8282\u76f8\u4ea4\u7684\u5939\u89d2");
    model.param().set("hcyl", "40[cm]", "\u5706\u67f1\u9ad8\u5ea6\u7684\u4e00\u534a");

    model.component("comp1").cpl().create("intop1", "Integration");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().all();
    model.component("comp1").cpl("intop1").set("frame", "material");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("y_vol", "intop1(solid.epe>0)/intop1(1)");
    model.component("comp1").variable("var1").descr("y_vol", "\u5c48\u670d\u4f53\u79ef\u5206\u6570");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "Rc");
    model.component("comp1").geom("geom1").feature("c1").set("angle", "alpha");
    model.component("comp1").geom("geom1").feature("c1").set("pos", new String[]{"0", "sf-(Rc-hi)"});
    model.component("comp1").geom("geom1").feature("c1").set("rot", "90-alpha");
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("c2").set("r", "Rc+th");
    model.component("comp1").geom("geom1").feature("c2").set("angle", "alpha");
    model.component("comp1").geom("geom1").feature("c2").set("pos", new String[]{"0", "sf-(Rc-hi)"});
    model.component("comp1").geom("geom1").feature("c2").set("rot", "90-alpha");
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").label("\u51a0");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("c2");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("c1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("c3", "Circle");
    model.component("comp1").geom("geom1").feature("c3").set("r", "Rk+th");
    model.component("comp1").geom("geom1").feature("c3").set("angle", "90-alpha");
    model.component("comp1").geom("geom1").feature("c3").set("pos", new String[]{"Ri-Rk", "sf"});
    model.component("comp1").geom("geom1").run("c3");
    model.component("comp1").geom("geom1").create("c4", "Circle");
    model.component("comp1").geom("geom1").feature("c4").set("r", "Rk");
    model.component("comp1").geom("geom1").feature("c4").set("angle", "90-alpha");
    model.component("comp1").geom("geom1").feature("c4").set("pos", new String[]{"Ri-Rk", "sf"});
    model.component("comp1").geom("geom1").run("c4");
    model.component("comp1").geom("geom1").create("dif2", "Difference");
    model.component("comp1").geom("geom1").feature("dif2").selection("input").set("c3");
    model.component("comp1").geom("geom1").feature("dif2").selection("input2").set("c4");
    model.component("comp1").geom("geom1").run("dif2");
    model.component("comp1").geom("geom1").feature("dif2").label("\u68f1\u7f18");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").label("\u6cd5\u5170");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"th", "sf"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"Ri", "0"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").label("Kl\u00f6pper \u5c01\u5934");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("dif1", "dif2", "r1");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").label("\u5706\u7b52");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"th", "hcyl"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"Ri", "-hcyl"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("cc").prop("Settings").set("CreateBasis", true);
    model.component("comp1").physics("cc").create("diff1", "DiffusionMethod", 2);
    model.component("comp1").physics("cc").feature("diff1").create("inl1", "Inlet", 1);
    model.component("comp1").physics("cc").feature("diff1").feature("inl1").selection().set(1);
    model.component("comp1").physics("cc").feature("diff1").create("out1", "Outlet", 1);
    model.component("comp1").physics("cc").feature("diff1").feature("out1").selection().set(3);
    model.component("comp1").physics("solid").create("symp1", "SymmetryPlane", 1);
    model.component("comp1").physics("solid").feature("symp1").selection().set(3);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(2, 4, 8, 10);
    model.component("comp1").physics("solid").feature("bndl1").set("forceType", "FollowerPressure");
    model.component("comp1").physics("solid").feature("bndl1").set("pressure", "pressure");
    model.component("comp1").physics("solid").feature("lemm1").set("coordinateSystem", "cc_cs");
    model.component("comp1").physics("solid").feature("lemm1").create("plsty1", "Plasticity", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty1").set("YieldFunction", "hill");
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty1")
         .set("IsotropicHardeningModel", "PerfectlyPlastic");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").label("Steel AISI 4340");
    model.component("comp1").material("mat1").set("family", "steel");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "205[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic_material_model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("ys", new String[]{"381e6", "381e6", "450e6", "240e6", "240e6", "220e6"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(4, 7, 10, 11);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 10);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("numelem", 25);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").selection().set(2, 6, 8, 9);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").setSolveFor("/physics/solid", false);
    model.study("std1").create("stat2", "Stationary");
    model.study("std1").feature("stat2").setSolveFor("/physics/cc", false);
    model.study("std1").feature("stat2").set("useparam", true);
    model.study("std1").feature("stat2").setIndex("pname", "pressure", 0);
    model.study("std1").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat2").setIndex("punit", "N/m^2", 0);
    model.study("std1").feature("stat2").setIndex("pname", "pressure", 0);
    model.study("std1").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat2").setIndex("punit", "N/m^2", 0);
    model.study("std1").feature("stat2").setIndex("plistarr", "range(16, 0.2, 36)", 0);
    model.study("std1").feature("stat2").setIndex("punit", "MPa", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s2").feature("p1").create("st1", "StopCondition");
    model.sol("sol1").feature("s2").feature("p1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("s2").feature("p1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("s2").feature("p1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("s2").feature("p1").feature("st1")
         .setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("s2").feature("p1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("s2").feature("p1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("s2").feature("p1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("s2").feature("p1").feature("st1")
         .setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("s2").feature("p1").feature("st1").setIndex("stopcondarr", "0.1-comp1.y_vol", 0);
    model.sol("sol1").feature("s2").feature("p1").feature("st1").setIndex("stopcondterminateon", "negative", 0);
    model.sol("sol1").feature("s2").feature("p1").feature("st1").set("storestopcondsol", "stepbefore_stepafter");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u77e2\u91cf\u573a (cc)");
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").feature().create("str1", "Streamline");
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("color", "red");
    model.result("pg1").feature("str1").set("smooth", "internal");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 78, 0);
    model.result("pg2").label("\u5750\u6807\u7cfb (cc)");
    model.result("pg2").create("sys1", "CoordSysSurface");
    model.result("pg2").feature("sys1").set("sys", "cc_cs");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 78, 0);
    model.result("pg3").label("\u5e94\u529b (solid)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg3").feature("surf1").set("threshold", "manual");
    model.result("pg3").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("surf1").set("colortable", "Rainbow");
    model.result("pg3").feature("surf1").set("colortabletrans", "none");
    model.result("pg3").feature("surf1").set("colorscalemode", "linear");
    model.result("pg3").feature("surf1").set("resolution", "normal");
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result("pg3").feature("surf1").create("def", "Deform");
    model.result("pg3").feature("surf1").feature("def").set("expr", new String[]{"u", "w"});
    model.result("pg3").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().dataset().create("dset1solidrev", "Revolve2D");
    model.result().dataset("dset1solidrev").set("data", "dset1");
    model.result().dataset("dset1solidrev").set("revangle", 225);
    model.result().dataset("dset1solidrev").set("startangle", -90);
    model.result().dataset("dset1solidrev").set("hasspacevars", true);
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1solidrev");
    model.result("pg4").setIndex("looplevel", 78, 0);
    model.result("pg4").label("\u5e94\u529b, 3D (solid)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg4").feature("surf1").set("threshold", "manual");
    model.result("pg4").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").feature("surf1").set("colortabletrans", "none");
    model.result("pg4").feature("surf1").set("colorscalemode", "linear");
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result("pg4").feature("surf1").create("def", "Deform");
    model.result().dataset("dset1solidrev").set("hasspacevars", true);
    model.result("pg4").feature("surf1").feature("def").set("revcoordsys", "cylindrical");
    model.result("pg4").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg4").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg4").feature("surf1").feature("def").set("descractive", true);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("str1").set("udist", 0.005);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").label("\u6750\u6599\u4e3b\u65b9\u5411");
    model.result("pg2").set("titletype", "none");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").setIndex("looplevel", 78, 0);
    model.result("pg5").label("\u7b49\u6548\u5851\u6027\u5e94\u53d8 (solid)");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"solid.epeGp"});
    model.result("pg5").feature("surf1").set("inheritplot", "none");
    model.result("pg5").feature("surf1").set("resolution", "normal");
    model.result("pg5").feature("surf1").set("colortabletype", "discrete");
    model.result("pg5").feature("surf1").set("bandcount", 11);
    model.result("pg5").feature("surf1").set("colortable", "AuroraAustralisDark");
    model.result("pg5").feature("surf1").set("descractive", true);
    model.result("pg5").feature("surf1").set("descr", "\u7b49\u6548\u5851\u6027\u5e94\u53d8");
    model.result("pg5").label("\u7b49\u6548\u5851\u6027\u5e94\u53d8 (solid)");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u5c48\u670d\u4f53\u79ef");
    model.result("pg6").set("titletype", "custom");
    model.result("pg6").set("typeintitle", false);
    model.result("pg6").set("unitintitle", false);
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "\u538b\u529b (N/m<sup>2</sup>)");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").setIndex("expr", "y_vol", 0);
    model.result("pg6").feature("glob1").set("legend", false);
    model.result("pg6").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 0, 3);
    model.result().configuration("prfu1").set("applytosamedims", true);
    model.result().configuration("prfu1").apply();
    model.result("pg4").run();
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("typeintitle", false);
    model.result("pg4").set("descriptionintitle", false);
    model.result("pg4").set("unitintitle", false);
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").run();
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset1solidrev");
    model.result("pg7").setIndex("looplevel", 78, 0);
    model.result("pg7").label("\u8fb9\u754c\u8f7d\u8377 (solid)");
    model.result("pg7").set("showlegends", true);
    model.result("pg7").set("titletype", "label");
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").set("showlegendsunit", true);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"1"});
    model.result("pg7").feature("surf1").label("\u7070\u8272\u8868\u9762");
    model.result("pg7").feature("surf1").set("coloring", "uniform");
    model.result("pg7").feature("surf1").set("color", "gray");
    model.result("pg7").feature("surf1").set("inheritcolor", false);
    model.result("pg7").feature("surf1").set("inheritrange", false);
    model.result("pg7").feature("surf1").set("inherittransparency", false);
    model.result("pg7").feature("surf1").create("def", "Deform");
    model.result("pg7").feature("surf1").feature("def").set("revcoordsys", "cylindrical");
    model.result("pg7").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg7").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg7").feature("surf1").feature("def").set("descractive", true);
    model.result("pg7").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg7").feature("surf1").feature("def").set("scale", "0");
    model.result("pg7").feature("surf1").create("tran1", "Transparency");
    model.result("pg7").feature("surf1").feature("tran1").set("transparency", 0.8);
    model.result("pg7").create("arws1", "ArrowSurface");
    model.result("pg7").feature("arws1").set("revcoordsys", "cylindrical");
    model.result("pg7").feature("arws1")
         .set("expr", new String[]{"solid.bndl1.far", "solid.bndl1.faphi", "solid.bndl1.faz"});
    model.result("pg7").feature("arws1").set("placement", "elements");
    model.result("pg7").feature("arws1").set("arrowbase", "head");
    model.result("pg7").feature("arws1").label("\u8fb9\u754c\u8f7d\u8377 1");
    model.result("pg7").feature("arws1").set("inheritplot", "none");
    model.result("pg7").feature("arws1").create("col", "Color");
    model.result("pg7").feature("arws1").feature("col").set("colortable", "Rainbow");
    model.result("pg7").feature("arws1").feature("col").set("colortabletrans", "none");
    model.result("pg7").feature("arws1").feature("col").set("colorscalemode", "linear");
    model.result("pg7").feature("arws1").feature("col").set("colordata", "arrowlength");
    model.result("pg7").feature("arws1").feature("col").set("coloring", "gradient");
    model.result("pg7").feature("arws1").feature("col").set("topcolor", "red");
    model.result("pg7").feature("arws1").feature("col").set("bottomcolor", "custom");
    model.result("pg7").feature("arws1").feature("col")
         .set("custombottomcolor", new double[]{0.5882353186607361, 0.5137255191802979, 0.5176470875740051});
    model.result("pg7").feature("arws1").set("color", "red");
    model.result("pg7").feature("arws1").create("def", "Deform");
    model.result("pg7").feature("arws1").feature("def").set("revcoordsys", "cylindrical");
    model.result("pg7").feature("arws1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg7").feature("arws1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg7").feature("arws1").feature("def").set("descractive", true);
    model.result("pg7").feature("arws1").feature("def").set("scaleactive", true);
    model.result("pg7").feature("arws1").feature("def").set("scale", "0");
    model.result("pg7").feature().move("surf1", 1);
    model.result("pg7").label("\u8fb9\u754c\u8f7d\u8377 (solid)");

    model.nodeGroup().create("dset1solidlgrp", "Results");
    model.nodeGroup("dset1solidlgrp").label("\u5916\u52a0\u8f7d\u8377 (solid)");
    model.nodeGroup("dset1solidlgrp").set("type", "plotgroup");
    model.nodeGroup("dset1solidlgrp").placeAfter("plotgroup", "pg6");
    model.nodeGroup("dset1solidlgrp").label("\u5916\u52a0\u8f7d\u8377 (solid)");
    model.nodeGroup("dset1solidlgrp").placeAfter("plotgroup", "pg7");
    model.nodeGroup("dset1solidlgrp").add("plotgroup", "pg7");

    model.result("pg7").run();
    model.result("pg3").run();
    model.result("pg4").run();

    model.title("\u6b63\u4ea4\u6750\u6599\u538b\u529b\u5bb9\u5668");

    model
         .description("\u672c\u4f8b\u8ba1\u7b97\u7531\u8f67\u94a2\u5236\u6210\u7684\u8584\u58c1\u5bb9\u5668\u5728\u5185\u90e8\u8d85\u538b\u4f5c\u7528\u4e0b\u7684\u5e94\u529b\u548c\u5851\u6027\u5e94\u53d8\u3002\u6750\u6599\u8868\u5f81\u4e3a\u5e26 Hill \u6b63\u4ea4\u5404\u5411\u5f02\u6027\u5851\u6027\u7684\u5404\u5411\u540c\u6027\u5f39\u6027\u6a21\u578b\u3002\u672c\u4f8b\u8fd8\u6f14\u793a\u5982\u4f55\u5904\u7406\u6750\u6599\u7279\u6027\u968f\u5f62\u72b6\u53d8\u5316\u7684\u7ed3\u6784\uff0c\u5982\u8f67\u94a2\u6216\u590d\u5408\u6750\u6599\u3002\u672c\u4f8b\u4e2d\uff0c\u6750\u6599\u7684\u4e3b\u65b9\u5411\u8ddf\u968f\u7ed3\u6784\u7684\u51e0\u4f55\u5f62\u72b6\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("orthotropic_container.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
