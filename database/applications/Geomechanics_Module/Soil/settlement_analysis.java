/*
 * settlement_analysis.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:14 by COMSOL 6.3.0.290. */
public class settlement_analysis {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Geomechanics_Module\\Soil");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("dl", "PorousMediaFlowRichards", "geom1");
    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/dl", true);
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("para", "0", "\u53c2\u6570");
    model.param().set("rhos", "1743[kg/m^3]", "\u571f\u58e4\u5bc6\u5ea6");
    model.param().set("rhow", "1000[kg/m^3]", "\u6c34\u5bc6\u5ea6");
    model.param().set("gammaw", "rhow*g_const", "\u6c34\u7684\u5355\u4f4d\u91cd\u91cf");
    model.param().set("muw", "1e-3[Pa*s]", "\u6c34\u52a8\u529b\u9ecf\u5ea6");
    model.param().set("Nu", "0.2", "\u6cca\u677e\u6bd4");
    model.param().set("lambda", "0.22", "\u538b\u7f29\u6307\u6570");
    model.param().set("lambda_s", "0.123", "\u5438\u529b\u53d8\u5316\u7684\u538b\u7f29\u6307\u6570");
    model.param().set("kappa", "0.006", "\u81a8\u80c0\u6307\u6570");
    model.param().set("kappa_s", "0.008", "\u5438\u529b\u53d8\u5316\u7684\u81a8\u80c0\u6307\u6570");
    model.param().set("Mb", "1.24", "\u4e34\u754c\u72b6\u6001\u7ebf\u7684\u659c\u7387");
    model.param().set("wb", "0.4", "\u6743\u91cd\u53c2\u6570");
    model.param().set("mb", "50[kPa]", "\u571f\u58e4\u521a\u5ea6\u53c2\u6570");
    model.param().set("bb", "10", "\u5851\u6027\u52bf\u5e73\u6ed1\u53c2\u6570");
    model.param().set("sy", "100[kPa]", "\u5438\u529b\u7684\u521d\u59cb\u5c48\u670d\u503c");
    model.param().set("kb", "0.6", "\u5f20\u529b\u4e0e\u5438\u529b\u6bd4");
    model.param().set("pref", "18[kPa]", "\u53c2\u8003\u538b\u529b");
    model.param().set("pc0", "80[kPa]", "\u521d\u59cb\u56fa\u7ed3\u538b\u529b");
    model.param().set("phi0", "0.64285", "\u521d\u59cb\u5b54\u9699\u7387");
    model.param().set("e0", "phi0/(1-phi0)", "\u521d\u59cb\u7a7a\u9699\u6bd4");
    model.param().set("K_sat", "1[m/day]", "\u9971\u548c\u6c34\u529b\u4f20\u5bfc\u7387");
    model.param().set("alpha", "2[1/m]", "\u62df\u5408\u53c2\u6570");
    model.param().set("S_res", "0.23", "\u5269\u4f59\u9971\u548c\u5ea6");
    model.param().set("S_sat", "1", "\u5b8c\u5168\u9971\u548c\u65f6\u7684\u9971\u548c\u5ea6");

    model.func().create("int1", "Interpolation");
    model.func("int1").label("\u57fa\u811a\u538b\u529b");
    model.func("int1").set("funcname", "F_P");
    model.func("int1").setIndex("table", 0, 0, 0);
    model.func("int1").setIndex("table", 0, 0, 1);
    model.func("int1").setIndex("table", 1, 1, 0);
    model.func("int1").setIndex("table", 130, 1, 1);
    model.func("int1").setIndex("table", 1.1, 2, 0);
    model.func("int1").setIndex("table", 130, 2, 1);
    model.func("int1").setIndex("argunit", 1, 0);
    model.func("int1").setIndex("fununit", "kPa", 0);
    model.func().create("int2", "Interpolation");
    model.func("int2").label("\u5730\u4e0b\u6c34\u4f4d");
    model.func("int2").set("funcname", "GWL");
    model.func("int2").setIndex("table", 0, 0, 0);
    model.func("int2").setIndex("table", 3, 0, 1);
    model.func("int2").setIndex("table", 1, 1, 0);
    model.func("int2").setIndex("table", 3, 1, 1);
    model.func("int2").setIndex("table", 1.1, 2, 0);
    model.func("int2").setIndex("table", 5, 2, 1);
    model.func("int2").setIndex("argunit", 1, 0);
    model.func("int2").setIndex("fununit", "m", 0);
    model.func().create("an1", "Analytic");
    model.func("an1").label("\u521d\u59cb\u5438\u529b\u5206\u5e03");
    model.func("an1").set("funcname", "InitSuction");
    model.func("an1").set("expr", "rhow*g_const*(Y-3)*(Y>=3)");
    model.func("an1").set("args", "Y");
    model.func("an1").setIndex("argunit", "m", 0);
    model.func("an1").set("fununit", "Pa");
    model.func("an1").setIndex("plotargs", 5, 0, 2);

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("Suction", "-p*(dl.Hp<0)", "\u6d41\u5438\u529b");
    model.component("comp1").variable("var1").set("PorePressure", "p*(dl.Hp>=0)", "\u5b54\u9699\u538b\u529b");
    model.component("comp1").variable("var1").set("k_rel", "Se", "\u76f8\u5bf9\u6e17\u900f\u7387");
    model.component("comp1").variable("var1").set("k", "K_sat*muw/gammaw", "\u571f\u58e4\u6e17\u900f\u7387");
    model.component("comp1").variable("var1").set("Cm", "phi0*(S_sat-S_res)*Se*alpha", "\u6bd4\u5bb9\u6c34\u5ea6");
    model.component("comp1").variable("var1")
         .set("Se", "exp(alpha*dl.Hp)*(dl.Hp<0)+1*(dl.Hp>=0)", "\u6709\u6548\u9971\u548c\u5ea6");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{5, 5});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new double[]{4.5, 5});
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new int[]{5, 5});
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", 3, 1);
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("pmat1", "PorousMedia");

    model.component("comp1").physics("dl").prop("ShapeProperty").set("order_pressure", 1);
    model.component("comp1").physics("dl").feature("usporous1").set("storageModelType", "userdef");
    model.component("comp1").physics("dl").feature("usporous1").feature("pm1").set("retentionModel", "userdef");
    model.component("comp1").physics("dl").feature("usporous1").feature("pm1").set("Se", "Se");
    model.component("comp1").physics("dl").feature("usporous1").feature("pm1")
         .set("theta_l", "S_res*phi0+Se*(phi0-S_res*phi0)");
    model.component("comp1").physics("dl").feature("usporous1").feature("pm1").set("Cm", "Cm");
    model.component("comp1").physics("dl").feature("usporous1").feature("pm1").set("kappa_r", "k_rel");
    model.component("comp1").physics("dl").feature("usporous1").feature("pm1").set("theta_r", "S_res*phi0");
    model.component("comp1").physics("dl").create("ph1", "PressureHead", 1);
    model.component("comp1").physics("dl").feature("ph1").selection().set(1, 3);
    model.component("comp1").physics("dl").feature("ph1").set("Hp0", "GWL(para)-Y");
    model.component("comp1").physics("dl").create("sym1", "Symmetry", 1);
    model.component("comp1").physics("dl").feature("sym1").selection().set(6);
    model.component("comp1").physics("solid").prop("ShapeProperty").set("order_displacement", 1);
    model.component("comp1").physics("solid").create("epsm1", "ElastoplasticSoilMaterial", 2);
    model.component("comp1").physics("solid").feature("epsm1")
         .label("\u4fee\u6b63\u5251\u6865\u9ecf\u571f\u6a21\u578b (MCC)");
    model.component("comp1").physics("solid").feature("epsm1").selection().set(1);
    model.component("comp1").physics("solid").feature("epsm1").set("evoid0C_mat", "from_mat");
    model.component("comp1").physics("solid").feature("epsm1").set("pref", "pref");
    model.component("comp1").physics("solid").feature("epsm1").set("pc0", "pc0");
    model.component("comp1").physics("solid").feature("epsm1").create("exs1", "ExternalStress", 2);
    model.component("comp1").physics("solid").feature("epsm1").feature("exs1")
         .set("StressInputType", "PorePressure");
    model.component("comp1").physics("solid").feature("epsm1").feature("exs1").set("pA", "PorePressure");
    model.component("comp1").physics("solid").feature("epsm1").feature("exs1").set("pref", 0);
    model.component("comp1").physics("solid").feature("epsm1").feature("exs1").set("alphaB_mat", "userdef");
    model.component("comp1").physics("solid").feature("epsm1").feature("exs1").set("alphaB", 1);

    model.component("comp1").material("pmat1").propertyGroup("def").set("density", new String[]{"rhos"});
    model.component("comp1").material("pmat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("pmat1").propertyGroup("Enu").set("nu", new String[]{"Nu"});
    model.component("comp1").material("pmat1").propertyGroup().create("CamClayModel", "CamClayModel", "Camclay");
    model.component("comp1").material("pmat1").propertyGroup("CamClayModel")
         .set("kappaSwelling", new String[]{"kappa"});
    model.component("comp1").material("pmat1").propertyGroup("CamClayModel").set("evoid0", new String[]{"e0"});
    model.component("comp1").material("pmat1").propertyGroup("def").set("hydraulicpermeability", new String[]{"k"});
    model.component("comp1").material("pmat1").propertyGroup("CamClayModel").set("M", new String[]{"Mb"});
    model.component("comp1").material("pmat1").propertyGroup("CamClayModel")
         .set("lambdaComp", new String[]{"lambda"});

    model.component("comp1").physics("solid").create("epsm2", "ElastoplasticSoilMaterial", 2);
    model.component("comp1").physics("solid").feature("epsm2")
         .label("\u6269\u5c55\u5df4\u585e\u7f57\u90a3\u57fa\u672c\u6a21\u578b (BBMx)");
    model.component("comp1").physics("solid").feature("epsm2").set("MaterialModel", "BarcelonaBasic");
    model.component("comp1").physics("solid").feature("epsm2").set("MB_mat", "from_mat");
    model.component("comp1").physics("solid").feature("epsm2").set("evoid0B_mat", "from_mat");
    model.component("comp1").physics("solid").feature("epsm2").set("ss0", "InitSuction(Y)");
    model.component("comp1").physics("solid").feature("epsm2").set("ss", "Suction");
    model.component("comp1").physics("solid").feature("epsm2").set("pref", "pref");
    model.component("comp1").physics("solid").feature("epsm2").set("pc0", "pc0");
    model.component("comp1").physics("solid").feature("epsm2").selection().set(1);
    model.component("comp1").physics("solid").feature("epsm2").create("exs1", "ExternalStress", 2);
    model.component("comp1").physics("solid").feature("epsm2").feature("exs1")
         .set("StressInputType", "PorePressure");
    model.component("comp1").physics("solid").feature("epsm2").feature("exs1").set("pA", "PorePressure");
    model.component("comp1").physics("solid").feature("epsm2").feature("exs1").set("pref", 0);
    model.component("comp1").physics("solid").feature("epsm2").feature("exs1").set("alphaB_mat", "userdef");
    model.component("comp1").physics("solid").feature("epsm2").feature("exs1").set("alphaB", 1);

    model.component("comp1").material("pmat1").propertyGroup()
         .create("BarcelonaBasicModel", "BarcelonaBasicModel", "Barcelona_Basic");
    model.component("comp1").material("pmat1").propertyGroup("BarcelonaBasicModel").set("bB", new String[]{"bb"});
    model.component("comp1").material("pmat1").propertyGroup("BarcelonaBasicModel")
         .set("lambdaComp0", new String[]{"lambda"});
    model.component("comp1").material("pmat1").propertyGroup("BarcelonaBasicModel").set("wB", new String[]{"wb"});
    model.component("comp1").material("pmat1").propertyGroup("BarcelonaBasicModel").set("sy0", new String[]{"sy"});
    model.component("comp1").material("pmat1").propertyGroup("BarcelonaBasicModel").set("M", new String[]{"Mb"});
    model.component("comp1").material("pmat1").propertyGroup("BarcelonaBasicModel")
         .set("lambdaCompss", new String[]{"lambda_s"});
    model.component("comp1").material("pmat1").propertyGroup("BarcelonaBasicModel")
         .set("kappaSwellings", new String[]{"kappa"});
    model.component("comp1").material("pmat1").propertyGroup("BarcelonaBasicModel").set("mB", new String[]{"mb"});
    model.component("comp1").material("pmat1").propertyGroup("BarcelonaBasicModel").set("kB", new String[]{"kb"});
    model.component("comp1").material("pmat1").propertyGroup("BarcelonaBasicModel")
         .set("kappaSwelling", new String[]{"kappa"});
    model.component("comp1").material("pmat1").propertyGroup("BarcelonaBasicModel")
         .set("evoid0", new String[]{"e0"});
    model.component("comp1").material("pmat1").feature().create("fluid1", "Fluid", "comp1");
    model.component("comp1").material("pmat1").feature("fluid1").propertyGroup("def")
         .set("density", new String[]{"rhow"});
    model.component("comp1").material("pmat1").feature("fluid1").propertyGroup("def")
         .set("dynamicviscosity", new String[]{"muw"});

    model.component("comp1").physics("solid").create("gacc1", "GravityAcceleration", -1);
    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().set(2);
    model.component("comp1").physics("solid").create("roll1", "Roller", 1);
    model.component("comp1").physics("solid").feature("roll1").selection().set(1, 3);
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 1);
    model.component("comp1").physics("solid").feature("sym1").selection().set(6);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(5);
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"0", "-F_P(para)", "0"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76\uff1aMCC");
    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"solid/epsm2"});
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "para", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("pname", "para", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,0.02,1.1)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u538b\u529b (dl)");
    model.result("pg1").set("titletype", "custom");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature().create("str1", "Streamline");
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg1").feature("str1").set("color", "gray");
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
    model.result("pg1").feature("str1").selection().geom("geom1", 1);
    model.result("pg1").feature("str1").selection().set(1, 2, 3, 4, 5, 6);
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u6d41\u7f51 (dl)");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("expr", "dl.H");
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("coloring", "uniform");
    model.result("pg2").feature("con1").set("colorlegend", false);
    model.result("pg2").feature("con1").set("color", "green");
    model.result("pg2").feature("con1").set("smooth", "internal");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("data", "parent");
    model.result("pg2").feature().create("str1", "Streamline");
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("posmethod", "magnitude");
    model.result("pg2").feature("str1").set("madv", "manual");
    model.result("pg2").feature("str1").set("msatfactor", 1.4);
    model.result("pg2").feature("str1").set("color", "blue");
    model.result("pg2").feature("str1").set("resolution", "extrafine");
    model.result("pg2").feature("str1").set("smooth", "internal");
    model.result("pg2").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("data", "parent");
    model.result("pg2").feature("str1").selection().geom("geom1", 1);
    model.result("pg2").feature("str1").selection().set(1, 2, 3, 4, 5, 6);
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 56, 0);
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
    model.result("pg3").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg3").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 56, 0);
    model.result("pg4").label("\u4f53\u79ef\u5851\u6027\u5e94\u53d8 (solid)");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"solid.epvolGp"});
    model.result("pg4").feature("surf1").set("inheritplot", "none");
    model.result("pg4").feature("surf1").set("resolution", "normal");
    model.result("pg4").feature("surf1").set("colortabletype", "discrete");
    model.result("pg4").feature("surf1").set("bandcount", 11);
    model.result("pg4").feature("surf1").set("colortable", "AuroraAustralisDark");
    model.result("pg4").feature("surf1").set("descractive", true);
    model.result("pg4").feature("surf1").set("descr", "\u4f53\u79ef\u5851\u6027\u5e94\u53d8");
    model.result("pg4").label("\u4f53\u79ef\u5851\u6027\u5e94\u53d8 (solid)");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").setIndex("looplevel", 56, 0);
    model.result("pg5").label("\u7a7a\u9699\u6bd4 (solid)");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"solid.evoid"});
    model.result("pg5").feature("surf1").create("def", "Deform");
    model.result("pg5").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg5").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg5").feature("surf1").set("resolution", "normal");
    model.result("pg5").feature("surf1").set("colortabletype", "discrete");
    model.result("pg5").feature("surf1").set("bandcount", 11);
    model.result("pg5").feature("surf1").set("colortable", "Traffic");
    model.result("pg5").feature("surf1").set("descractive", true);
    model.result("pg5").feature("surf1").set("descr", "\u7a7a\u9699\u6bd4");
    model.result("pg5").label("\u7a7a\u9699\u6bd4 (solid)");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").setIndex("looplevel", 56, 0);
    model.result("pg6").label("\u4f53\u79ef\u8f7d\u8377 (solid)");
    model.result("pg6").set("showlegends", true);
    model.result("pg6").set("titletype", "label");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").set("showlegendsunit", true);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"1"});
    model.result("pg6").feature("surf1").label("\u7070\u8272\u8868\u9762");
    model.result("pg6").feature("surf1").set("coloring", "uniform");
    model.result("pg6").feature("surf1").set("color", "gray");
    model.result("pg6").feature("surf1").set("inheritcolor", false);
    model.result("pg6").feature("surf1").set("inheritrange", false);
    model.result("pg6").feature("surf1").set("inheritheightscale", false);
    model.result("pg6").feature("surf1").create("def", "Deform");
    model.result("pg6").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg6").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg6").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg6").feature("surf1").feature("def").set("scale", 0);
    model.result("pg6").feature("surf1").create("sel1", "Selection");
    model.result("pg6").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg6").feature("surf1").feature("sel1").selection().set(1);
    model.result("pg6").create("arws1", "ArrowSurface");
    model.result("pg6").feature("arws1").set("expr", new String[]{"solid.gacc1.fvx", "solid.gacc1.fvy"});
    model.result("pg6").feature("arws1").set("placement", "gausspoints");
    model.result("pg6").feature("arws1").set("arrowbase", "tail");
    model.result("pg6").feature("arws1").label("\u91cd\u529b 1");
    model.result("pg6").feature("arws1").set("inheritplot", "none");
    model.result("pg6").feature("arws1").create("col", "Color");
    model.result("pg6").feature("arws1").feature("col").set("colortable", "Rainbow");
    model.result("pg6").feature("arws1").feature("col").set("colortabletrans", "none");
    model.result("pg6").feature("arws1").feature("col").set("colorscalemode", "linear");
    model.result("pg6").feature("arws1").feature("col").set("colordata", "arrowlength");
    model.result("pg6").feature("arws1").feature("col").set("coloring", "gradient");
    model.result("pg6").feature("arws1").feature("col").set("topcolor", "red");
    model.result("pg6").feature("arws1").feature("col").set("bottomcolor", "custom");
    model.result("pg6").feature("arws1").feature("col")
         .set("custombottomcolor", new double[]{0.5882353186607361, 0.5137255191802979, 0.5176470875740051});
    model.result("pg6").feature("arws1").set("color", "magenta");
    model.result("pg6").feature("arws1").create("def", "Deform");
    model.result("pg6").feature("arws1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg6").feature("arws1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg6").feature("arws1").feature("def").set("scaleactive", true);
    model.result("pg6").feature("arws1").feature("def").set("scale", 0);
    model.result("pg6").feature().move("surf1", 1);
    model.result("pg6").label("\u4f53\u79ef\u8f7d\u8377 (solid)");

    model.nodeGroup().create("dset1solidlgrp", "Results");
    model.nodeGroup("dset1solidlgrp").label("\u5916\u52a0\u8f7d\u8377 (solid)");
    model.nodeGroup("dset1solidlgrp").set("type", "plotgroup");
    model.nodeGroup("dset1solidlgrp").placeAfter("plotgroup", "pg6");
    model.nodeGroup("dset1solidlgrp").label("\u5916\u52a0\u8f7d\u8377 (solid)");
    model.nodeGroup("dset1solidlgrp").placeAfter("plotgroup", "pg6");

    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").set("data", "dset1");
    model.result("pg7").setIndex("looplevel", 56, 0);
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
    model.result("pg7").feature("surf1").set("inheritheightscale", false);
    model.result("pg7").feature("surf1").create("def", "Deform");
    model.result("pg7").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg7").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg7").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg7").feature("surf1").feature("def").set("scale", 0);
    model.result("pg7").feature("surf1").create("sel1", "Selection");
    model.result("pg7").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg7").feature("surf1").feature("sel1").selection().set(1);
    model.result("pg7").create("arwl1", "ArrowLine");
    model.result("pg7").feature("arwl1").set("expr", new String[]{"solid.bndl1.fax", "solid.bndl1.fay"});
    model.result("pg7").feature("arwl1").set("placement", "gausspoints");
    model.result("pg7").feature("arwl1").set("arrowbase", "tail");
    model.result("pg7").feature("arwl1").label("\u8fb9\u754c\u8f7d\u8377 1");
    model.result("pg7").feature("arwl1").set("inheritplot", "none");
    model.result("pg7").feature("arwl1").create("col", "Color");
    model.result("pg7").feature("arwl1").feature("col").set("colortable", "Rainbow");
    model.result("pg7").feature("arwl1").feature("col").set("colortabletrans", "none");
    model.result("pg7").feature("arwl1").feature("col").set("colorscalemode", "linear");
    model.result("pg7").feature("arwl1").feature("col").set("colordata", "arrowlength");
    model.result("pg7").feature("arwl1").feature("col").set("coloring", "gradient");
    model.result("pg7").feature("arwl1").feature("col").set("topcolor", "red");
    model.result("pg7").feature("arwl1").feature("col").set("bottomcolor", "custom");
    model.result("pg7").feature("arwl1").feature("col")
         .set("custombottomcolor", new double[]{0.5882353186607361, 0.5137255191802979, 0.5176470875740051});
    model.result("pg7").feature("arwl1").set("color", "red");
    model.result("pg7").feature("arwl1").create("def", "Deform");
    model.result("pg7").feature("arwl1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg7").feature("arwl1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg7").feature("arwl1").feature("def").set("scaleactive", true);
    model.result("pg7").feature("arwl1").feature("def").set("scale", 0);
    model.result("pg7").feature().move("surf1", 1);
    model.result("pg7").label("\u8fb9\u754c\u8f7d\u8377 (solid)");

    model.nodeGroup("dset1solidlgrp").add("plotgroup", "pg6");
    model.nodeGroup("dset1solidlgrp").add("plotgroup", "pg7");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/dl", true);
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").label("\u7814\u7a76\uff1aBBMx");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "para", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "", 0);
    model.study("std2").feature("stat").setIndex("pname", "para", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "range(0,0.02,1.1)", 0);
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("s1").feature("p1").set("paramtuning", true);

    return model;
  }

  public static Model run2(Model model) {
    model.sol("sol2").feature("s1").feature("p1").set("pminstep", "0.0001");

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("mir1", "Mirror2D");
    model.result().dataset("mir1").setIndex("genpoints", 5, 0, 0);
    model.result().dataset("mir1").setIndex("genpoints", 5, 1, 0);
    model.result().dataset("mir1").set("removesymelem", true);
    model.result().dataset().duplicate("mir2", "mir1");
    model.result().dataset("mir2").set("data", "dset2");
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "kPa", 0, 3);
    model.result().configuration("prfu1").set("applytosamedims", true);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature().remove("str1");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("data", "mir1");
    model.result("pg1").feature("surf1").setIndex("looplevel", 51, 0);
    model.result("pg1").feature().duplicate("surf2", "surf1");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").set("data", "parent");
    model.result("pg1").feature("surf2").set("inheritplot", "surf1");
    model.result("pg1").feature("surf2").create("trn1", "Transformation");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").feature("trn1").set("move", new int[]{12, 0});
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("tlan1", "TableAnnotation");
    model.result("pg1").feature("tlan1").set("source", "localtable");
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 2.5, 0, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0, 0, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "GWL = 3[m]", 0, 2);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 14.5, 1, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0, 1, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "GWL = 5[m]", 1, 2);
    model.result("pg1").feature("tlan1").set("showpoint", false);
    model.result("pg1").run();
    model.result("pg1").set("data", "mir1");
    model.result("pg1").set("solutionintitle", false);
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u8868\u9762\uff1a\u538b\u529b (kPa)");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").label("GWL = 3[m] \u65f6\u7684\u9971\u548c\u5ea6");
    model.result("pg2").set("data", "mir1");
    model.result("pg2").setIndex("looplevel", 51, 0);
    model.result("pg2").run();
    model.result("pg2").feature().remove("con1");
    model.result("pg2").feature().remove("str1");
    model.result("pg2").run();
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "dl.Se");
    model.result("pg2").feature("surf1").set("descr", "\u6709\u6548\u9971\u548c\u5ea6");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").label("\u5e94\u529b\uff0cMCC");
    model.result("pg3").set("data", "mir1");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("threshold", "none");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg8", "pg3");
    model.result("pg8").run();
    model.result().move("pg8", 3);
    model.result("pg8").label("\u5e94\u529b\uff0cBBMx");
    model.result("pg8").set("data", "mir2");
    model.result("pg8").run();
    model.result("pg4").run();
    model.result("pg4").label("GWL = 3[m] \u65f6\u7684\u4f53\u79ef\u5851\u6027\u5e94\u53d8");
    model.result("pg4").set("data", "mir1");
    model.result("pg4").setIndex("looplevel", 51, 0);
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg4").feature().duplicate("surf2", "surf1");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").set("data", "mir2");
    model.result("pg4").feature("surf2").setIndex("looplevel", 51, 0);
    model.result("pg4").feature("surf2").set("titletype", "none");
    model.result("pg4").feature("surf2").set("inheritplot", "surf1");
    model.result("pg4").feature("surf2").create("trn1", "Transformation");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").feature("trn1").set("move", new int[]{12, 0});
    model.result("pg4").run();
    model.result("pg4").create("tlan1", "TableAnnotation");
    model.result("pg4").feature("tlan1").set("source", "localtable");
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 2.5, 0, 0);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", -0.2, 0, 1);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", "MCC \u6a21\u578b", 0, 2);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 14.5, 1, 0);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", -0.2, 1, 1);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", "BBMx \u6a21\u578b", 1, 2);
    model.result("pg4").feature("tlan1").set("showpoint", false);
    model.result("pg4").run();
    model.result("pg4").create("arwl1", "ArrowLine");
    model.result("pg4").feature("arwl1").set("expr", new String[]{"solid.fax", "solid.fay"});
    model.result("pg4").feature("arwl1")
         .set("descr", "\u5355\u4f4d\u53d8\u5f62\u9762\u79ef\u7684\u529b \uff08\u7a7a\u95f4\u5750\u6807\u7cfb\uff09");
    model.result("pg4").feature("arwl1").set("titletype", "none");
    model.result("pg4").feature("arwl1").set("arrowbase", "head");
    model.result("pg4").feature().duplicate("arwl2", "arwl1");
    model.result("pg4").run();
    model.result("pg4").feature("arwl2").create("trn1", "Transformation");
    model.result("pg4").run();
    model.result("pg4").feature("arwl2").feature("trn1").set("move", new int[]{12, 0});
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg9", "pg4");
    model.result("pg9").run();
    model.result().move("pg9", 5);
    model.result("pg9").label("GWL = 5[m] \u65f6\u7684\u4f53\u79ef\u5851\u6027\u5e94\u53d8");
    model.result("pg9").setIndex("looplevel", 56, 0);
    model.result("pg9").run();
    model.result("pg9").feature("surf2").setIndex("looplevel", 56, 0);
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg5").run();
    model.result("pg5").label("GWL = 3[m] \u65f6\u7684\u7a7a\u9699\u6bd4");
    model.result("pg5").set("data", "mir1");
    model.result("pg5").setIndex("looplevel", 51, 0);
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("surf2", "surf1");
    model.result("pg5").run();
    model.result("pg5").feature("surf2").set("data", "mir2");
    model.result("pg5").feature("surf2").setIndex("looplevel", 51, 0);
    model.result("pg5").feature("surf2").set("titletype", "none");
    model.result("pg5").feature("surf2").set("inheritplot", "surf1");
    model.result("pg5").feature("surf2").create("trn1", "Transformation");
    model.result("pg5").run();
    model.result("pg5").feature("surf2").feature("trn1").set("move", new int[]{12, 0});
    model.result("pg9").run();
    model.result("pg5").run();
    model.result("pg5").feature().copy("tlan1", "pg9/tlan1");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg10", "pg5");
    model.result("pg10").run();

    model.nodeGroup("dset1solidlgrp").add("plotgroup", "pg10");
    model.nodeGroup("dset1solidlgrp").remove("plotgroup", "pg10", true);

    model.result("pg10").label("GWL = 5[m] \u65f6\u7684\u7a7a\u9699\u6bd4");
    model.result("pg10").stepLast(0);
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").feature("surf2").stepLast(0);
    model.result("pg10").run();
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").run();

    model.nodeGroup("dset1solidlgrp").add("plotgroup", "pg11");
    model.nodeGroup("dset1solidlgrp").remove("plotgroup", "pg11", true);

    model.result("pg11").label("\u57fa\u811a\u538b\u529b vs. \u6c89\u964d");
    model.result("pg11").set("titletype", "label");
    model.result("pg11").set("xlabelactive", true);
    model.result("pg11").set("xlabel", "\u6c89\u964d (mm)");
    model.result("pg11").set("ylabelactive", true);
    model.result("pg11").set("ylabel", "\u57fa\u811a\u538b\u529b (kPa)");
    model.result("pg11").set("axislimits", true);
    model.result("pg11").set("xmax", 65);
    model.result("pg11").set("ymin", -2);
    model.result("pg11").set("ymax", 150);
    model.result("pg11").set("legendpos", "upperleft");
    model.result("pg11").create("ptgr1", "PointGraph");
    model.result("pg11").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg11").feature("ptgr1").set("linewidth", "preference");
    model.result("pg11").feature("ptgr1").selection().set(6);
    model.result("pg11").feature("ptgr1").set("expr", "F_P(para)");
    model.result("pg11").feature("ptgr1").set("xdata", "expr");
    model.result("pg11").feature("ptgr1").set("xdataexpr", "abs(v-withsol('sol1',v,setval(para,0)))");
    model.result("pg11").feature("ptgr1").set("xdataunit", "mm");
    model.result("pg11").feature("ptgr1").set("linewidth", 1);
    model.result("pg11").feature("ptgr1").set("legend", true);
    model.result("pg11").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg11").feature("ptgr1").setIndex("legends", "MCC \u6a21\u578b", 0);
    model.result("pg11").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg11").run();
    model.result("pg11").feature("ptgr2").set("data", "dset2");
    model.result("pg11").feature("ptgr2").set("xdataexpr", "abs(v-withsol('sol2',v,setval(para,0)))");
    model.result("pg11").feature("ptgr2").set("linestyle", "dotted");
    model.result("pg11").feature("ptgr2").setIndex("legends", "BBMx \u6a21\u578b", 0);
    model.result("pg11").run();
    model.result("pg11").create("tlan1", "TableAnnotation");
    model.result("pg11").feature("tlan1").set("source", "localtable");
    model.result("pg11").feature("tlan1").setIndex("localtablematrix", 4, 0, 0);
    model.result("pg11").feature("tlan1").setIndex("localtablematrix", 50, 0, 1);
    model.result("pg11").feature("tlan1").setIndex("localtablematrix", "\u5f39\u6027", 0, 2);
    model.result("pg11").feature("tlan1").setIndex("localtablematrix", 22, 1, 0);
    model.result("pg11").feature("tlan1").setIndex("localtablematrix", 100, 1, 1);
    model.result("pg11").feature("tlan1").setIndex("localtablematrix", "\u5f39\u5851\u6027\uff0cMCC", 1, 2);
    model.result("pg11").feature("tlan1").setIndex("localtablematrix", 9, 2, 0);
    model.result("pg11").feature("tlan1").setIndex("localtablematrix", 110, 2, 1);
    model.result("pg11").feature("tlan1").setIndex("localtablematrix", "\u5f39\u5851\u6027\uff0cBBMx", 2, 2);
    model.result("pg11").feature("tlan1").setIndex("localtablematrix", 30, 3, 0);
    model.result("pg11").feature("tlan1").setIndex("localtablematrix", 137, 3, 1);
    model.result("pg11").feature("tlan1").setIndex("localtablematrix", "\u6da6\u6e7f\uff0cBBMx", 3, 2);
    model.result("pg11").feature("tlan1").setIndex("localtablematrix", 50, 4, 0);
    model.result("pg11").feature("tlan1").setIndex("localtablematrix", 137, 4, 1);
    model.result("pg11").feature("tlan1").setIndex("localtablematrix", "\u6da6\u6e7f\uff0cMCC", 4, 2);
    model.result("pg11").feature("tlan1").set("showpoint", false);
    model.result("pg11").run();
    model.result("pg11").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").set("data", "mir1");
    model.result().dataset("cln1").setIndex("genpoints", 10, 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", 5, 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", 5, 1, 1);
    model.result().dataset().duplicate("cln2", "cln1");
    model.result().dataset("cln2").set("data", "mir2");
    model.result().create("pg12", "PlotGroup1D");
    model.result("pg12").run();

    model.nodeGroup("dset1solidlgrp").add("plotgroup", "pg12");
    model.nodeGroup("dset1solidlgrp").remove("plotgroup", "pg12", true);

    model.result("pg12").label("\u5782\u76f4\u4f4d\u79fb");
    model.result("pg12").set("data", "cln1");
    model.result("pg12").setIndex("looplevelinput", "last", 0);
    model.result("pg12").set("titletype", "manual");
    model.result("pg12").set("title", "\u6da6\u6e7f\u5f15\u8d77\u7684\u5782\u76f4\u4f4d\u79fb");
    model.result("pg12").set("xlabelactive", true);
    model.result("pg12").set("xlabel", "\u4f4d\u7f6e (m)");
    model.result("pg12").set("ylabelactive", true);
    model.result("pg12").set("ylabel", "\u5782\u76f4\u4f4d\u79fb (m)");
    model.result("pg12").set("legendpos", "lowerright");
    model.result("pg12").create("lngr1", "LineGraph");
    model.result("pg12").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg12").feature("lngr1").set("linewidth", "preference");
    model.result("pg12").feature("lngr1").set("expr", "v-withsol('sol1',v,setval(para,1))");
    model.result("pg12").feature("lngr1").set("unit", "mm");
    model.result("pg12").feature("lngr1").set("xdata", "expr");
    model.result("pg12").feature("lngr1").set("xdataexpr", "cln1x");
    model.result("pg12").feature("lngr1").set("legend", true);
    model.result("pg12").feature("lngr1").set("legendmethod", "manual");
    model.result("pg12").feature("lngr1").setIndex("legends", "MMC \u6a21\u578b", 0);
    model.result("pg12").feature().duplicate("lngr2", "lngr1");
    model.result("pg12").run();
    model.result("pg12").feature("lngr2").set("data", "cln2");
    model.result("pg12").feature("lngr2").setIndex("looplevelinput", "last", 0);
    model.result("pg12").feature("lngr2").set("expr", "v-withsol('sol2',v,setval(para,1))");
    model.result("pg12").feature("lngr2").setIndex("legends", "BBMx \u6a21\u578b", 0);
    model.result("pg12").run();
    model.result("pg12").create("tlan1", "TableAnnotation");
    model.result("pg12").feature("tlan1").set("source", "localtable");
    model.result("pg12").feature("tlan1").setIndex("localtablematrix", 9, 0, 0);
    model.result("pg12").feature("tlan1").setIndex("localtablematrix", 1, 0, 1);
    model.result("pg12").feature("tlan1").setIndex("localtablematrix", 5.5, 1, 0);
    model.result("pg12").feature("tlan1").setIndex("localtablematrix", -20, 1, 1);
    model.result("pg12").feature("tlan1").set("showpoint", false);
    model.result("pg12").run();
    model.result("pg12").run();
    model.result("pg6").run();
    model.result("pg6").set("data", "mir1");
    model.result("pg6").run();
    model.result("pg7").run();
    model.result("pg7").set("data", "mir1");
    model.result("pg7").run();
    model.result("pg4").run();

    model.title("\u975e\u9971\u548c\u571f\u5c42\u4e0a\u7684\u6d45\u57fa\u7840");

    model
         .description("\u672c\u4f8b\u91c7\u7528\u201c\u4fee\u6b63\u5251\u6865\u9ecf\u571f\u6a21\u578b\u201d\u548c\u201c\u6269\u5c55\u5df4\u585e\u7f57\u90a3\u57fa\u672c\u571f\u58e4\u6a21\u578b\u201d\u5bf9\u975e\u9971\u548c\u571f\u5c42\u4e0a\u6d45\u57fa\u7840\u7684\u6c89\u964d\u548c\u9686\u8d77\u8fdb\u884c\u5206\u6790\uff0c\u6f14\u793a\u4e86\u7531\u4e8e\u5730\u4e0b\u6c34\u4f4d\u7ebf\u8fd0\u52a8\u5f15\u8d77\u7684\u5b54\u9699\u5438\u529b\u6548\u5e94\u4ee5\u53ca\u7531\u6b64\u9020\u6210\u7684\u9ecf\u571f\u5c42\u6c89\u964d\u548c\u9686\u8d77\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("settlement_analysis.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
