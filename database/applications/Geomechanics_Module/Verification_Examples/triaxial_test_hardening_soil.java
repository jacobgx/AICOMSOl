/*
 * triaxial_test_hardening_soil.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:16 by COMSOL 6.3.0.290. */
public class triaxial_test_hardening_soil {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Geomechanics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("disp", "0[cm]", "\u8f74\u5411\u4f4d\u79fb");
    model.param().set("Nu", "0.2", "\u6cca\u677e\u6bd4");
    model.param().set("Rho", "2400[kg/m^3]", "\u5bc6\u5ea6");
    model.param().set("E50ref", "20[MPa]", "\u4e3b\u8377\u8f7d\u7684\u53c2\u8003\u521a\u5ea6");
    model.param()
         .set("Eurref", "60[MPa]", "\u7528\u4e8e\u5378\u8f7d\u548c\u91cd\u65b0\u52a0\u8f7d\u7684\u53c2\u8003\u521a\u5ea6");
    model.param().set("e0", "0.89", "\u521d\u59cb\u7a7a\u9699\u6bd4");
    model.param().set("m", "0.65", "\u5e94\u529b\u6307\u6570");
    model.param().set("rc", "1.75", "\u81a8\u80c0\u5230\u538b\u7f29\u6bd4");
    model.param().set("c", "1[kPa]", "\u5185\u805a\u529b");
    model.param().set("Psi", "1.5[deg]", "\u81a8\u80c0\u89d2");
    model.param().set("Phi", "34[deg]", "\u5185\u6469\u64e6\u89d2");
    model.param().set("Rc", "1.0428", "\u692d\u5706\u957f\u5bbd\u6bd4");
    model.param().set("p0", "300[kPa]", "\u538b\u529b");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1")
         .set("sigmafc", "-p0*(1+sin(Phi))/(1-sin(Phi))-2*c*cos(Phi)/(1-sin(Phi))");
    model.component("comp1").variable("var1").descr("sigmafc", "\u538b\u7f29\u5931\u6548\u5e94\u529b");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"5[cm]", "10[cm]"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{1, 3});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"0", "20[cm]"});
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("solid").create("epsm1", "ElastoplasticSoilMaterial", 2);
    model.component("comp1").physics("solid").feature("epsm1")
         .label("\u786c\u5316\u571f\uff1a\u6469\u5c14\u2013\u5e93\u4ed1\u5931\u6548\u51c6\u5219");
    model.component("comp1").physics("solid").feature("epsm1").selection().set(1);
    model.component("comp1").physics("solid").feature("epsm1").set("MaterialModel", "HardeningSoil");
    model.component("comp1").physics("solid").feature("epsm1").set("Kc_mat", "FromRatio");
    model.component("comp1").physics("solid").feature("epsm1").set("Rf", 0.95);
    model.component("comp1").physics("solid").feature("epsm1").set("pc0", "1000[kPa]");
    model.component("comp1").physics("solid").feature("epsm1").create("exs1", "ExternalStress", 2);
    model.component("comp1").physics("solid").feature("epsm1").feature("exs1")
         .set("StressInputType", "InSituStress");
    model.component("comp1").physics("solid").feature("epsm1").feature("exs1")
         .set("sins", new String[]{"-p0", "0", "0", "0", "-p0", "0", "0", "0", "-p0"});
    model.component("comp1").physics("solid").feature().duplicate("epsm2", "epsm1");
    model.component("comp1").physics("solid").feature("epsm2")
         .label("\u786c\u5316\u571f\uff1aMatsuoka\u2013Nakai \u5931\u6548\u51c6\u5219");
    model.component("comp1").physics("solid").feature("epsm2").selection().set(2);
    model.component("comp1").physics("solid").feature("epsm2").set("HardeningSoilOption", "HSSmooth");
    model.component("comp1").physics("solid").feature().duplicate("epsm3", "epsm1");
    model.component("comp1").physics("solid").feature("epsm3")
         .label("\u786c\u5316\u571f\uff1aPanteghini\u2013Lagioia \u5931\u6548\u51c6\u5219");
    model.component("comp1").physics("solid").feature("epsm3").selection().set(3);
    model.component("comp1").physics("solid").feature("epsm3").set("HardeningSoilOption", "HSCombined");
    model.component("comp1").physics("solid").create("roll1", "Roller", 1);
    model.component("comp1").physics("solid").feature("roll1").selection().set(2, 5, 8);
    model.component("comp1").physics("solid").create("disp1", "Displacement1", 1);
    model.component("comp1").physics("solid").feature("disp1").selection().set(3, 6, 9);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("solid").feature("disp1").setIndex("U0", "disp", 2);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u571f\u58e4\u6750\u6599");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"Nu"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("HardeningSoilModel", "HardeningSoilModel", "Hardening_soil");
    model.component("comp1").material("mat1").propertyGroup("HardeningSoilModel")
         .set("E50Ref", new String[]{"E50ref"});
    model.component("comp1").material("mat1").propertyGroup("HardeningSoilModel")
         .set("EurRef", new String[]{"Eurref"});
    model.component("comp1").material("mat1").propertyGroup("HardeningSoilModel").set("mH", new String[]{"m"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("MohrCoulomb", "MohrCoulomb", "Mohr_Coulomb_criterion");
    model.component("comp1").material("mat1").propertyGroup("MohrCoulomb").set("cohesion", new String[]{"c"});
    model.component("comp1").material("mat1").propertyGroup("MohrCoulomb").set("psid", new String[]{"Psi"});
    model.component("comp1").material("mat1").propertyGroup("HardeningSoilModel").set("rsc", new String[]{"rc"});
    model.component("comp1").material("mat1").propertyGroup("HardeningSoilModel").set("Rcap", new String[]{"Rc"});
    model.component("comp1").material("mat1").propertyGroup("HardeningSoilModel").set("evoid0", new String[]{"e0"});
    model.component("comp1").material("mat1").propertyGroup("MohrCoulomb").set("internalphi", new String[]{"Phi"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"Rho"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().all();
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").run("map1");

    model.study("std1").setGenPlots(false);
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "disp", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "disp", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,-0.0001,-0.017)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "kPa", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u8f74\u5411\u5e94\u529b vs. \u8f74\u5411\u5e94\u53d8");
    model.result("pg1").set("titletype", "label");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "\u8f74\u5411\u5e94\u53d8 (1)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u8f74\u5411\u5e94\u529b (kPa)");
    model.result("pg1").set("legendpos", "lowerright");
    model.result("pg1").create("ptgr1", "PointGraph");
    model.result("pg1").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg1").feature("ptgr1").set("linewidth", "preference");
    model.result("pg1").feature("ptgr1").selection().set(8);
    model.result("pg1").feature("ptgr1").set("expr", "-solid.SZZ");
    model.result("pg1").feature("ptgr1").set("xdata", "expr");
    model.result("pg1").feature("ptgr1").set("xdataexpr", "-solid.eZZ");
    model.result("pg1").feature("ptgr1").set("linemarker", "cycle");
    model.result("pg1").feature("ptgr1").set("markerpos", "interp");
    model.result("pg1").feature("ptgr1").set("markers", 6);
    model.result("pg1").feature("ptgr1").set("legend", true);
    model.result("pg1").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg1").feature("ptgr1").setIndex("legends", "HS-MC", 0);
    model.result("pg1").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg1").run();
    model.result("pg1").feature("ptgr2").selection().set(10);
    model.result("pg1").feature("ptgr2").set("markers", 7);
    model.result("pg1").feature("ptgr2").setIndex("legends", "HS-MN", 0);
    model.result("pg1").feature().duplicate("ptgr3", "ptgr2");
    model.result("pg1").run();
    model.result("pg1").feature("ptgr3").selection().set(12);
    model.result("pg1").feature("ptgr3").set("markers", 8);
    model.result("pg1").feature("ptgr3").setIndex("legends", "HS-PL", 0);
    model.result("pg1").feature().duplicate("ptgr4", "ptgr3");
    model.result("pg1").run();
    model.result("pg1").feature("ptgr4").set("expr", "-sigmafc");
    model.result("pg1").feature("ptgr4").set("linestyle", "dashed");
    model.result("pg1").feature("ptgr4").set("linecolor", "magenta");
    model.result("pg1").feature("ptgr4").setIndex("legends", "\u5931\u6548\u5e94\u529b", 0);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("von Mises \u5e94\u529b vs. \u8f74\u5411\u5e94\u53d8");
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u8f74\u5411\u5e94\u53d8 (1)");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "von Mises \u5e94\u529b (kPa)");
    model.result("pg2").set("legendpos", "lowerright");
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr1").set("linewidth", "preference");
    model.result("pg2").feature("ptgr1").selection().set(8);
    model.result("pg2").feature("ptgr1").set("expr", "solid.mises");
    model.result("pg2").feature("ptgr1").set("xdata", "expr");
    model.result("pg2").feature("ptgr1").set("xdataexpr", "-solid.eZZ");
    model.result("pg2").feature("ptgr1").set("linemarker", "cycle");
    model.result("pg2").feature("ptgr1").set("markerpos", "interp");
    model.result("pg2").feature("ptgr1").set("markers", 6);
    model.result("pg2").feature("ptgr1").set("legend", true);
    model.result("pg2").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg2").feature("ptgr1").setIndex("legends", "HS-MC", 0);
    model.result("pg2").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr2").selection().set(10);
    model.result("pg2").feature("ptgr2").set("markers", 7);
    model.result("pg2").feature("ptgr2").setIndex("legends", "HS-MN", 0);
    model.result("pg2").feature().duplicate("ptgr3", "ptgr2");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr3").selection().set(12);
    model.result("pg2").feature("ptgr3").set("markers", 8);
    model.result("pg2").feature("ptgr3").setIndex("legends", "HS-PL", 0);
    model.result("pg2").feature().duplicate("ptgr4", "ptgr3");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr4").set("expr", "-sigmafc-p0");
    model.result("pg2").feature("ptgr4").set("linestyle", "dashed");
    model.result("pg2").feature("ptgr4").set("linecolor", "magenta");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u4f53\u79ef\u5e94\u53d8 vs. \u8f74\u5411\u5e94\u53d8");
    model.result("pg3").set("titletype", "label");
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "\u8f74\u5411\u5e94\u53d8 (1)");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u4f53\u79ef\u5e94\u53d8 (1)");
    model.result("pg3").create("ptgr1", "PointGraph");
    model.result("pg3").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg3").feature("ptgr1").set("linewidth", "preference");
    model.result("pg3").feature("ptgr1").selection().set(8);
    model.result("pg3").feature("ptgr1").set("expr", "solid.evol");
    model.result("pg3").feature("ptgr1").set("xdata", "expr");
    model.result("pg3").feature("ptgr1").set("xdataexpr", "-solid.eZZ");
    model.result("pg3").feature("ptgr1").set("linemarker", "cycle");
    model.result("pg3").feature("ptgr1").set("markerpos", "interp");
    model.result("pg3").feature("ptgr1").set("markers", 6);
    model.result("pg3").feature("ptgr1").set("legend", true);
    model.result("pg3").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg3").feature("ptgr1").setIndex("legends", "HS-MC", 0);
    model.result("pg3").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg3").run();
    model.result("pg3").feature("ptgr2").selection().set(10);
    model.result("pg3").feature("ptgr2").set("markers", 7);
    model.result("pg3").feature("ptgr2").setIndex("legends", "HS-MN", 0);
    model.result("pg3").feature().duplicate("ptgr3", "ptgr2");
    model.result("pg3").run();
    model.result("pg3").feature("ptgr3").selection().set(12);
    model.result("pg3").feature("ptgr3").set("markers", 8);
    model.result("pg3").feature("ptgr3").setIndex("legends", "HS-PL", 0);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u4f53\u79ef\u5851\u6027\u5e94\u53d8 vs. \u8f74\u5411\u5e94\u53d8");
    model.result("pg4").set("titletype", "label");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u8f74\u5411\u5e94\u53d8 (1)");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u4f53\u79ef\u5851\u6027\u5e94\u53d8 (1)");
    model.result("pg4").create("ptgr1", "PointGraph");
    model.result("pg4").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg4").feature("ptgr1").set("linewidth", "preference");
    model.result("pg4").feature("ptgr1").selection().set(4);
    model.result("pg4").feature("ptgr1").set("expr", "solid.epvol");
    model.result("pg4").feature("ptgr1").set("xdata", "expr");
    model.result("pg4").feature("ptgr1").set("xdataexpr", "-solid.eZZ");
    model.result("pg4").feature("ptgr1").set("linemarker", "cycle");
    model.result("pg4").feature("ptgr1").set("markerpos", "interp");
    model.result("pg4").feature("ptgr1").set("markers", 6);
    model.result("pg4").feature("ptgr1").set("legend", true);
    model.result("pg4").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg4").feature("ptgr1").setIndex("legends", "HS-MC", 0);
    model.result("pg4").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg4").run();
    model.result("pg4").feature("ptgr2").selection().set(10);
    model.result("pg4").feature("ptgr2").set("markers", 7);
    model.result("pg4").feature("ptgr2").setIndex("legends", "HS-MN", 0);
    model.result("pg4").feature().duplicate("ptgr3", "ptgr2");
    model.result("pg4").run();
    model.result("pg4").feature("ptgr3").selection().set(12);
    model.result("pg4").feature("ptgr3").set("markers", 8);
    model.result("pg4").feature("ptgr3").setIndex("legends", "HS-PL", 0);
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u52a8\u81a8\u80c0\u89d2 vs. \u52a8\u6469\u64e6\u89d2");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u52a8\u6469\u64e6\u89d2 (deg)");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u52a8\u81a8\u80c0\u89d2 (deg)");
    model.result("pg5").set("legendpos", "upperleft");
    model.result("pg5").create("ptgr1", "PointGraph");
    model.result("pg5").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg5").feature("ptgr1").set("linewidth", "preference");
    model.result("pg5").feature("ptgr1").selection().set(8);
    model.result("pg5").feature("ptgr1").set("expr", "solid.epsm1.psim");
    model.result("pg5").feature("ptgr1").set("descr", "\u52a8\u81a8\u80c0\u89d2");
    model.result("pg5").feature("ptgr1").set("expr", "solid.epsm1.psim*180/pi");
    model.result("pg5").feature("ptgr1").set("xdataexpr", "solid.epsm1.phim");
    model.result("pg5").feature("ptgr1").set("xdatadescr", "\u52a8\u6469\u64e6\u89d2");
    model.result("pg5").feature("ptgr1").set("xdataexpr", "solid.epsm1.phim*180/pi");
    model.result("pg5").feature("ptgr1").set("linemarker", "cycle");
    model.result("pg5").feature("ptgr1").set("markerpos", "interp");
    model.result("pg5").feature("ptgr1").set("markers", 6);
    model.result("pg5").feature("ptgr1").set("legend", true);
    model.result("pg5").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg5").feature("ptgr1").setIndex("legends", "HS-MC", 0);
    model.result("pg5").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr2").selection().set(10);
    model.result("pg5").feature("ptgr2").set("expr", "solid.epsm2.psim*180/pi");
    model.result("pg5").feature("ptgr2").set("xdataexpr", "solid.epsm2.phim*180/pi");
    model.result("pg5").feature("ptgr2").set("markers", 7);
    model.result("pg5").feature("ptgr2").setIndex("legends", "HS-MN", 0);
    model.result("pg5").feature().duplicate("ptgr3", "ptgr2");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr3").selection().set(12);
    model.result("pg5").feature("ptgr3").set("expr", "solid.epsm3.psim*180/pi");
    model.result("pg5").feature("ptgr3").set("xdataexpr", "solid.epsm3.phim*180/pi");
    model.result("pg5").feature("ptgr3").set("markers", 8);
    model.result("pg5").feature("ptgr3").setIndex("legends", "HS-PL", 0);
    model.result("pg5").run();
    model.result("pg1").run();

    model.title("\u57fa\u4e8e\u786c\u5316\u571f\u6750\u6599\u6a21\u578b\u7684\u4e09\u8f74\u8bd5\u9a8c");

    model
         .description("\u672c\u4f8b\u4f7f\u7528\u201c\u786c\u5316\u571f\u201d\u6750\u6599\u6a21\u578b\u6a21\u62df\u4e09\u8f74\u8bd5\u9a8c\uff0c\u5176\u4e2d\u5305\u542b\u4e24\u4e2a\u4e3b\u8981\u9636\u6bb5\uff1a\u521d\u59cb\u5404\u5411\u540c\u6027\u538b\u7f29\u548c\u968f\u540e\u7684\u8f74\u5411\u538b\u7f29/\u62c9\u4f38\u8377\u8f7d\u3002\u672c\u4f8b\u91cd\u65b0\u83b7\u5f97\u4e86\u53cc\u66f2\u7ebf\u5e94\u529b-\u5e94\u53d8\u5173\u7cfb\uff0c\u6b64\u5916\uff0c\u8fd8\u9a8c\u8bc1\u4e86\u8f74\u5411\u5e94\u529b\u7684\u6e10\u8fd1\u503c\u7b49\u4e8e\u5931\u6548\u5e94\u529b\u7684\u89e3\u6790\u503c\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("triaxial_test_hardening_soil.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
