/*
 * uniaxial_loading_of_shape_memory_alloy_souza_auricchio.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:37 by COMSOL 6.3.0.290. */
public class uniaxial_loading_of_shape_memory_alloy_souza_auricchio {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Shape_Memory_Alloys");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.param().set("T", "298[K]");
    model.param().descr("T", "\u5e94\u7528\u6e29\u5ea6");
    model.param().set("para", "0");
    model.param().descr("para", "\u8fde\u7eed\u53c2\u6570");

    model.component("comp1").geom("geom1").lengthUnit("cm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{6, 20});
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("solid").create("sma1", "ShapeMemoryAlloy", 2);
    model.component("comp1").physics("solid").feature("sma1").selection().all();
    model.component("comp1").physics("solid").feature("sma1").set("minput_temperature_src", "userdef");
    model.component("comp1").physics("solid").feature("sma1").set("minput_temperature", "T");
    model.component("comp1").physics("solid").feature("sma1").set("ShapeMemoryAlloyModel", "SouzaAuricchio");
    model.component("comp1").physics("solid").feature("sma1")
         .set("TransformationParametersAuricchio", "temperature");
    model.component("comp1").physics("solid").create("roll1", "Roller", 1);
    model.component("comp1").physics("solid").feature("roll1").selection().set(2);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 1);

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").label("\u8f7d\u8377");
    model.component("comp1").func("int1").set("funcname", "load");
    model.component("comp1").func("int1").setIndex("table", 0, 0, 0);
    model.component("comp1").func("int1").setIndex("table", 0, 0, 1);
    model.component("comp1").func("int1").setIndex("table", 1, 1, 0);
    model.component("comp1").func("int1").setIndex("table", 1, 1, 1);
    model.component("comp1").func("int1").setIndex("table", 2, 2, 0);
    model.component("comp1").func("int1").setIndex("table", 0, 2, 1);

    model.component("comp1").physics("solid").feature("bndl1").selection().set(3);
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"0", "0", "850[MPa]*load(para)"});

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u954d\u949b\u5408\u91d1");
    model.component("comp1").material("mat1").propertyGroup("def").set("poissonsratio", new String[]{"0.33"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("ShapeMemoryAlloyAustenite", "ShapeMemoryAlloyAustenite", "Austenite_phase");
    model.component("comp1").material("mat1").propertyGroup("ShapeMemoryAlloyAustenite")
         .set("E_A", new String[]{"55[GPa]"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("ShapeMemoryAlloyMartensite", "ShapeMemoryAlloyMartensite", "Martensite_phase");
    model.component("comp1").material("mat1").propertyGroup("ShapeMemoryAlloyMartensite")
         .set("E_M", new String[]{"46[GPa]"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("SouzaAuricchioModel", "SouzaAuricchioModel", "Souza-Auricchio_model");
    model.component("comp1").material("mat1").propertyGroup("SouzaAuricchioModel")
         .set("beta", new String[]{"7.4[MPa/K]"});
    model.component("comp1").material("mat1").propertyGroup("SouzaAuricchioModel")
         .set("etrmaxAuricchio", new String[]{"0.056"});
    model.component("comp1").material("mat1").propertyGroup("SouzaAuricchioModel")
         .set("TMs_SA", new String[]{"245[K]"});
    model.component("comp1").material("mat1").propertyGroup("SouzaAuricchioModel")
         .set("TMf_SA", new String[]{"230[K]"});
    model.component("comp1").material("mat1").propertyGroup("SouzaAuricchioModel")
         .set("TAf_SA", new String[]{"280[K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"6500"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 7);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76\uff1a\u4f2a\u5f39\u6027\uff0c\u5355\u6b21\u52a0\u8f7d\u5faa\u73af");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "T", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "K", 0);
    model.study("std1").feature("param").setIndex("pname", "T", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "K", 0);
    model.study("std1").feature("param").setIndex("plistarr", "328 308 276 260", 0);
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "T", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "K", 0);
    model.study("std1").feature("stat").setIndex("pname", "T", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "K", 0);
    model.study("std1").feature("stat").setIndex("pname", "para", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,0.01,2)", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("p1").set("paramtuning", true);
    model.sol("sol1").feature("s1").feature("p1").set("pminstep", "0.0001");

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u5e94\u529b vs. \u5e94\u53d8");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").create("ptgr1", "PointGraph");
    model.result("pg1").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg1").feature("ptgr1").set("linewidth", "preference");
    model.result("pg1").feature("ptgr1").selection().set(1);
    model.result("pg1").feature("ptgr1").set("expr", "solid.sGpzz");
    model.result("pg1").feature("ptgr1").set("descr", "\u5e94\u529b\u5f20\u91cf\uff0czz \u5206\u91cf");
    model.result("pg1").feature("ptgr1").set("unit", "MPa");
    model.result("pg1").feature("ptgr1").set("xdataexpr", "solid.eZZ");
    model.result("pg1").feature("ptgr1").set("xdatadescr", "\u5e94\u53d8\u5f20\u91cf\uff0cZZ \u5206\u91cf");
    model.result("pg1").feature("ptgr1").set("autopoint", false);
    model.result("pg1").feature("ptgr1").set("legend", true);
    model.result("pg1").run();
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("xlabel", "\u8f74\u5411\u5e94\u53d8");
    model.result("pg1").set("ylabel", "\u8f74\u5411\u5e94\u529b (MPa)");
    model.result("pg1").set("legendpos", "upperleft");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u9a6c\u6c0f\u4f53\u4f53\u79ef\u5206\u6570");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr1").set("linewidth", "preference");
    model.result("pg2").feature("ptgr1").selection().set(1);
    model.result("pg2").feature("ptgr1").set("expr", "solid.xiGp_M");
    model.result("pg2").feature("ptgr1").set("descr", "\u9a6c\u6c0f\u4f53\u4f53\u79ef\u5206\u6570");
    model.result("pg2").feature("ptgr1").set("legend", true);
    model.result("pg2").feature("ptgr1").set("autopoint", false);
    model.result("pg2").run();
    model.result("pg2").set("legendpos", "upperleft");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").label("\u5f62\u72b6\u8bb0\u5fc6\u5408\u91d1\u76f8\u56fe (solid)");
    model.result("pg3")
         .label("\u5f62\u72b6\u8bb0\u5fc6\u5408\u91d1\u76f8\u56fe (\u5f62\u72b6\u8bb0\u5fc6\u5408\u91d1 1)");
    model.result("pg3").create("lnsg1", "LineSegments");
    model.result("pg3").feature("lnsg1")
         .set("xexpr", new String[]{"atent0(dset2_solid_sma1_pt,solid.TStar-solid.sig0_SA/solid.beta)", "dset2_solid_sma1_Tmax"});
    model.result("pg3").feature("lnsg1")
         .set("yexpr", new String[]{"0[Pa]", "atent0(dset2_solid_sma1_pt,solid.beta*(dset2_solid_sma1_Tmax-solid.TStar)+solid.sig0_SA)"});
    model.result("pg3").feature("lnsg1").set("linestyle", "dashed");
    model.result("pg3").feature("lnsg1").set("linecolor", "blue");
    model.result("pg3").create("lnsg2", "LineSegments");
    model.result("pg3").feature("lnsg2")
         .set("xexpr", new String[]{"atent0(dset2_solid_sma1_pt,solid.TStar+solid.sig0_SA/solid.beta-solid.Hk*solid.etrmax/solid.beta)", "dset2_solid_sma1_Tmax"});
    model.result("pg3").feature("lnsg2")
         .set("yexpr", new String[]{"0[Pa]", "atent0(dset2_solid_sma1_pt,solid.beta*(dset2_solid_sma1_Tmax-solid.TStar)-solid.sig0_SA+solid.Hk*solid.etrmax)"});
    model.result("pg3").feature("lnsg2").set("linestyle", "dashed");
    model.result("pg3").feature("lnsg2").set("linecolor", "red");
    model.result("pg3").create("lnsg3", "LineSegments");
    model.result("pg3").feature("lnsg3")
         .set("xexpr", new String[]{"atent0(dset2_solid_sma1_pt,solid.TStar-solid.sig0_SA/solid.beta-solid.Hk*solid.etrmax/solid.beta)", "dset2_solid_sma1_Tmax"});
    model.result("pg3").feature("lnsg3")
         .set("yexpr", new String[]{"0[Pa]", "atent0(dset2_solid_sma1_pt,solid.beta*(dset2_solid_sma1_Tmax-solid.TStar)+solid.sig0_SA+solid.Hk*solid.etrmax)"});
    model.result("pg3").feature("lnsg3").set("linestyle", "solid");
    model.result("pg3").feature("lnsg3").set("linecolor", "blue");
    model.result("pg3").create("lnsg4", "LineSegments");
    model.result("pg3").feature("lnsg4")
         .set("xexpr", new String[]{"atent0(dset2_solid_sma1_pt,solid.TStar+solid.sig0_SA/solid.beta)", "dset2_solid_sma1_Tmax"});
    model.result("pg3").feature("lnsg4")
         .set("yexpr", new String[]{"0[Pa]", "atent0(dset2_solid_sma1_pt,solid.beta*(dset2_solid_sma1_Tmax-solid.TStar)-solid.sig0_SA)"});
    model.result("pg3").feature("lnsg4").set("linestyle", "solid");
    model.result("pg3").feature("lnsg4").set("linecolor", "red");
    model.result("pg3").create("ptgr1", "PointGraph");
    model.result("pg3").feature("ptgr1").set("titletype", "manual");
    model.result("pg3").feature("ptgr1").set("title", "\u5e94\u529b vs. \u6e29\u5ea6");
    model.result("pg3").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg3").feature("ptgr1").selection().set(1);
    model.result("pg3").feature("ptgr1").set("expr", "solid.misesGp");
    model.result("pg3").feature("ptgr1").set("xdata", "expr");
    model.result("pg3").feature("ptgr1").set("xdataexpr", new String[]{"solid.T"});
    model.result("pg3").feature("ptgr1").set("linewidth", 2);
    model.result("pg3").feature("ptgr1").create("col1", "Color");
    model.result("pg3").feature("ptgr1").feature("col1").set("expr", new String[]{"solid.xiGp_M"});
    model.result("pg3").feature("ptgr1").feature("col1").set("colortable", "Cividis");
    model.result("pg3").feature("ptgr1").feature("col1").set("colorscalemode", "linear");
    model.result("pg3").feature("ptgr1").feature("col1").set("colortabletrans", "reverse");
    model.result("pg3").feature("ptgr1").feature("col1").set("rangecoloractive", true);
    model.result("pg3").feature("ptgr1").feature("col1").set("rangecolormin", 0);
    model.result("pg3").feature("ptgr1").feature("col1").set("rangecolormax", 1);
    model.result("pg3").feature("ptgr1").feature("col1").set("titletype", "auto");
    model.result("pg3")
         .label("\u5f62\u72b6\u8bb0\u5fc6\u5408\u91d1\u76f8\u56fe (\u5f62\u72b6\u8bb0\u5fc6\u5408\u91d1 1)");
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevelinput", "manual", 0);
    model.result("pg3")
         .setIndex("looplevel", new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101}, 0);
    model.result("pg3").run();
    model.result("pg3")
         .setIndex("looplevel", new int[]{101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201}, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevelinput", "all", 0);

    model.component("comp1").func().create("int2", "Interpolation");
    model.component("comp1").func("int2").label("\u4f4d\u79fb");
    model.component("comp1").func("int2").set("funcname", "disp");
    model.component("comp1").func("int2").setIndex("table", 0, 0, 0);
    model.component("comp1").func("int2").setIndex("table", 0, 0, 1);
    model.component("comp1").func("int2").setIndex("table", 1, 1, 0);
    model.component("comp1").func("int2").setIndex("table", 1, 1, 1);
    model.component("comp1").func("int2").setIndex("table", 2, 2, 0);
    model.component("comp1").func("int2").setIndex("table", 0.4, 2, 1);
    model.component("comp1").func("int2").setIndex("table", 3, 3, 0);
    model.component("comp1").func("int2").setIndex("table", 0.8, 3, 1);
    model.component("comp1").func("int2").setIndex("table", 4, 4, 0);
    model.component("comp1").func("int2").setIndex("table", 0, 4, 1);

    model.component("comp1").physics("solid").create("disp1", "Displacement1", 1);
    model.component("comp1").physics("solid").feature("disp1").selection().set(3);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("solid").feature("disp1").setIndex("U0", "20[cm]*0.07*disp(para)", 2);

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").label("\u7814\u7a76\uff1a\u4f2a\u5f39\u6027\uff0c\u591a\u6b21\u52a0\u8f7d\u5faa\u73af");
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "T", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "K", 0);
    model.study("std2").feature("stat").setIndex("pname", "T", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "K", 0);
    model.study("std2").feature("stat").setIndex("pname", "para", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "range(0,0.02,4)", 0);
    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").set("disabledphysics", new String[]{"solid/bndl1"});
    model.study("std2").setGenPlots(false);
    model.study("std2").showAutoSequences("all");

    model.sol("sol7").feature("s1").feature("p1").set("paramtuning", true);
    model.sol("sol7").feature("s1").feature("p1").set("pminstep", "0.0001");

    model.study("std2").createAutoSequences("all");

    model.sol("sol7").runAll();

    model.result("pg1").run();
    model.result().duplicate("pg4", "pg1");
    model.result().duplicate("pg5", "pg2");
    model.result("pg4").run();
    model.result("pg4").set("data", "dset3");
    model.result("pg4").set("showlegends", false);
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").set("data", "dset3");
    model.result("pg5").set("showlegends", false);
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").label("\u5f62\u72b6\u8bb0\u5fc6\u5408\u91d1\u76f8\u56fe (solid)");
    model.result("pg6")
         .label("\u5f62\u72b6\u8bb0\u5fc6\u5408\u91d1\u76f8\u56fe (\u5f62\u72b6\u8bb0\u5fc6\u5408\u91d1 1) 1");
    model.result("pg6").create("lnsg1", "LineSegments");
    model.result("pg6").feature("lnsg1")
         .set("xexpr", new String[]{"atent0(dset3_solid_sma1_pt,solid.TStar-solid.sig0_SA/solid.beta)", "dset3_solid_sma1_Tmax"});
    model.result("pg6").feature("lnsg1")
         .set("yexpr", new String[]{"0[Pa]", "atent0(dset3_solid_sma1_pt,solid.beta*(dset3_solid_sma1_Tmax-solid.TStar)+solid.sig0_SA)"});
    model.result("pg6").feature("lnsg1").set("linestyle", "dashed");
    model.result("pg6").feature("lnsg1").set("linecolor", "blue");
    model.result("pg6").create("lnsg2", "LineSegments");
    model.result("pg6").feature("lnsg2")
         .set("xexpr", new String[]{"atent0(dset3_solid_sma1_pt,solid.TStar+solid.sig0_SA/solid.beta-solid.Hk*solid.etrmax/solid.beta)", "dset3_solid_sma1_Tmax"});
    model.result("pg6").feature("lnsg2")
         .set("yexpr", new String[]{"0[Pa]", "atent0(dset3_solid_sma1_pt,solid.beta*(dset3_solid_sma1_Tmax-solid.TStar)-solid.sig0_SA+solid.Hk*solid.etrmax)"});
    model.result("pg6").feature("lnsg2").set("linestyle", "dashed");
    model.result("pg6").feature("lnsg2").set("linecolor", "red");
    model.result("pg6").create("lnsg3", "LineSegments");
    model.result("pg6").feature("lnsg3")
         .set("xexpr", new String[]{"atent0(dset3_solid_sma1_pt,solid.TStar-solid.sig0_SA/solid.beta-solid.Hk*solid.etrmax/solid.beta)", "dset3_solid_sma1_Tmax"});
    model.result("pg6").feature("lnsg3")
         .set("yexpr", new String[]{"0[Pa]", "atent0(dset3_solid_sma1_pt,solid.beta*(dset3_solid_sma1_Tmax-solid.TStar)+solid.sig0_SA+solid.Hk*solid.etrmax)"});
    model.result("pg6").feature("lnsg3").set("linestyle", "solid");
    model.result("pg6").feature("lnsg3").set("linecolor", "blue");
    model.result("pg6").create("lnsg4", "LineSegments");
    model.result("pg6").feature("lnsg4")
         .set("xexpr", new String[]{"atent0(dset3_solid_sma1_pt,solid.TStar+solid.sig0_SA/solid.beta)", "dset3_solid_sma1_Tmax"});
    model.result("pg6").feature("lnsg4")
         .set("yexpr", new String[]{"0[Pa]", "atent0(dset3_solid_sma1_pt,solid.beta*(dset3_solid_sma1_Tmax-solid.TStar)-solid.sig0_SA)"});
    model.result("pg6").feature("lnsg4").set("linestyle", "solid");
    model.result("pg6").feature("lnsg4").set("linecolor", "red");
    model.result("pg6").create("ptgr1", "PointGraph");
    model.result("pg6").feature("ptgr1").set("titletype", "manual");
    model.result("pg6").feature("ptgr1").set("title", "\u5e94\u529b vs. \u6e29\u5ea6");
    model.result("pg6").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg6").feature("ptgr1").selection().set(1);
    model.result("pg6").feature("ptgr1").set("expr", "solid.misesGp");
    model.result("pg6").feature("ptgr1").set("xdata", "expr");
    model.result("pg6").feature("ptgr1").set("xdataexpr", new String[]{"solid.T"});
    model.result("pg6").feature("ptgr1").set("linewidth", 2);
    model.result("pg6").feature("ptgr1").create("col1", "Color");
    model.result("pg6").feature("ptgr1").feature("col1").set("expr", new String[]{"solid.xiGp_M"});
    model.result("pg6").feature("ptgr1").feature("col1").set("colortable", "Cividis");
    model.result("pg6").feature("ptgr1").feature("col1").set("colorscalemode", "linear");
    model.result("pg6").feature("ptgr1").feature("col1").set("colortabletrans", "reverse");
    model.result("pg6").feature("ptgr1").feature("col1").set("rangecoloractive", true);
    model.result("pg6").feature("ptgr1").feature("col1").set("rangecolormin", 0);
    model.result("pg6").feature("ptgr1").feature("col1").set("rangecolormax", 1);
    model.result("pg6").feature("ptgr1").feature("col1").set("titletype", "auto");
    model.result("pg6")
         .label("\u5f62\u72b6\u8bb0\u5fc6\u5408\u91d1\u76f8\u56fe (\u5f62\u72b6\u8bb0\u5fc6\u5408\u91d1 1) 1");
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevelinput", "manual", 0);
    model.result("pg6")
         .setIndex("looplevel", new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51}, 0);
    model.result("pg6").run();
    model.result("pg6")
         .setIndex("looplevel", new int[]{51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101}, 0);
    model.result("pg6").run();
    model.result("pg6")
         .setIndex("looplevel", new int[]{101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151}, 0);
    model.result("pg6").run();
    model.result("pg6")
         .setIndex("looplevel", new int[]{151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201}, 0);
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevelinput", "all", 0);

    model.component("comp1").func().create("int3", "Interpolation");
    model.component("comp1").func("int3").label("\u6e29\u5ea6");
    model.component("comp1").func("int3").set("funcname", "temperature");
    model.component("comp1").func("int3").setIndex("table", 2, 0, 0);
    model.component("comp1").func("int3").setIndex("table", 260, 0, 1);
    model.component("comp1").func("int3").setIndex("table", 3, 1, 0);
    model.component("comp1").func("int3").setIndex("table", 300, 1, 1);
    model.component("comp1").func("int3").setIndex("fununit", "K", 0);
    model.component("comp1").func("int3").setIndex("argunit", 1, 0);

    model.component("comp1").physics("solid").feature().duplicate("sma2", "sma1");
    model.component("comp1").physics("solid").feature("sma2").set("minput_temperature", "temperature(para)");
    model.component("comp1").physics("solid").create("bndl2", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl2").selection().set(3);
    model.component("comp1").physics("solid").feature("bndl2")
         .set("forceReferenceArea", new String[]{"0", "0", "300[MPa]*load(para)"});

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std3").label("\u7814\u7a76\uff1a\u5f62\u72b6\u8bb0\u5fc6\u6548\u5e94");
    model.study("std3").feature("stat").set("useparam", true);
    model.study("std3").feature("stat").setIndex("pname", "T", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "K", 0);
    model.study("std3").feature("stat").setIndex("pname", "T", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "K", 0);
    model.study("std3").feature("stat").setIndex("pname", "para", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "range(0,0.02,2) range(2.05,0.05,3)", 0);
    model.study("std3").feature("stat").set("useadvanceddisable", true);
    model.study("std3").feature("stat").set("disabledphysics", new String[]{"solid/bndl1", "solid/disp1"});
    model.study("std3").setGenPlots(false);
    model.study("std3").showAutoSequences("all");

    model.sol("sol8").feature("s1").feature("p1").set("paramtuning", true);
    model.sol("sol8").feature("s1").feature("p1").set("pminstep", "0.0001");

    model.study("std3").createAutoSequences("all");

    model.sol("sol8").runAll();

    model.result("pg4").run();
    model.result().duplicate("pg7", "pg4");
    model.result("pg7").run();
    model.result("pg7").set("data", "dset4");
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").set("data", "dset4");
    model.result("pg8").label("\u5f62\u72b6\u8bb0\u5fc6\u5408\u91d1\u76f8\u56fe (solid)");
    model.result("pg8")
         .label("\u5f62\u72b6\u8bb0\u5fc6\u5408\u91d1\u76f8\u56fe (\u5f62\u72b6\u8bb0\u5fc6\u5408\u91d1 2)");
    model.result("pg8").create("lnsg1", "LineSegments");
    model.result("pg8").feature("lnsg1")
         .set("xexpr", new String[]{"atent0(dset4_solid_sma2_pt,solid.TStar-solid.sig0_SA/solid.beta)", "dset4_solid_sma2_Tmax"});
    model.result("pg8").feature("lnsg1")
         .set("yexpr", new String[]{"0[Pa]", "atent0(dset4_solid_sma2_pt,solid.beta*(dset4_solid_sma2_Tmax-solid.TStar)+solid.sig0_SA)"});
    model.result("pg8").feature("lnsg1").set("linestyle", "dashed");
    model.result("pg8").feature("lnsg1").set("linecolor", "blue");
    model.result("pg8").create("lnsg2", "LineSegments");
    model.result("pg8").feature("lnsg2")
         .set("xexpr", new String[]{"atent0(dset4_solid_sma2_pt,solid.TStar+solid.sig0_SA/solid.beta-solid.Hk*solid.etrmax/solid.beta)", "dset4_solid_sma2_Tmax"});
    model.result("pg8").feature("lnsg2")
         .set("yexpr", new String[]{"0[Pa]", "atent0(dset4_solid_sma2_pt,solid.beta*(dset4_solid_sma2_Tmax-solid.TStar)-solid.sig0_SA+solid.Hk*solid.etrmax)"});
    model.result("pg8").feature("lnsg2").set("linestyle", "dashed");
    model.result("pg8").feature("lnsg2").set("linecolor", "red");
    model.result("pg8").create("lnsg3", "LineSegments");
    model.result("pg8").feature("lnsg3")
         .set("xexpr", new String[]{"atent0(dset4_solid_sma2_pt,solid.TStar-solid.sig0_SA/solid.beta-solid.Hk*solid.etrmax/solid.beta)", "dset4_solid_sma2_Tmax"});
    model.result("pg8").feature("lnsg3")
         .set("yexpr", new String[]{"0[Pa]", "atent0(dset4_solid_sma2_pt,solid.beta*(dset4_solid_sma2_Tmax-solid.TStar)+solid.sig0_SA+solid.Hk*solid.etrmax)"});
    model.result("pg8").feature("lnsg3").set("linestyle", "solid");
    model.result("pg8").feature("lnsg3").set("linecolor", "blue");
    model.result("pg8").create("lnsg4", "LineSegments");
    model.result("pg8").feature("lnsg4")
         .set("xexpr", new String[]{"atent0(dset4_solid_sma2_pt,solid.TStar+solid.sig0_SA/solid.beta)", "dset4_solid_sma2_Tmax"});
    model.result("pg8").feature("lnsg4")
         .set("yexpr", new String[]{"0[Pa]", "atent0(dset4_solid_sma2_pt,solid.beta*(dset4_solid_sma2_Tmax-solid.TStar)-solid.sig0_SA)"});
    model.result("pg8").feature("lnsg4").set("linestyle", "solid");
    model.result("pg8").feature("lnsg4").set("linecolor", "red");
    model.result("pg8").create("ptgr1", "PointGraph");
    model.result("pg8").feature("ptgr1").set("titletype", "manual");
    model.result("pg8").feature("ptgr1").set("title", "\u5e94\u529b vs. \u6e29\u5ea6");
    model.result("pg8").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg8").feature("ptgr1").selection().set(1);
    model.result("pg8").feature("ptgr1").set("expr", "solid.misesGp");
    model.result("pg8").feature("ptgr1").set("xdata", "expr");
    model.result("pg8").feature("ptgr1").set("xdataexpr", new String[]{"solid.T"});
    model.result("pg8").feature("ptgr1").set("linewidth", 2);
    model.result("pg8").feature("ptgr1").create("col1", "Color");
    model.result("pg8").feature("ptgr1").feature("col1").set("expr", new String[]{"solid.xiGp_M"});
    model.result("pg8").feature("ptgr1").feature("col1").set("colortable", "Cividis");
    model.result("pg8").feature("ptgr1").feature("col1").set("colorscalemode", "linear");
    model.result("pg8").feature("ptgr1").feature("col1").set("colortabletrans", "reverse");
    model.result("pg8").feature("ptgr1").feature("col1").set("rangecoloractive", true);
    model.result("pg8").feature("ptgr1").feature("col1").set("rangecolormin", 0);
    model.result("pg8").feature("ptgr1").feature("col1").set("rangecolormax", 1);
    model.result("pg8").feature("ptgr1").feature("col1").set("titletype", "auto");
    model.result("pg8")
         .label("\u5f62\u72b6\u8bb0\u5fc6\u5408\u91d1\u76f8\u56fe (\u5f62\u72b6\u8bb0\u5fc6\u5408\u91d1 2)");
    model.result("pg8").run();
    model.result().param().set("dset4_solid_sma2_Tmax", "310[K]");
    model.result("pg8").run();
    model.result("pg8").setIndex("looplevelinput", "manual", 0);
    model.result("pg8")
         .setIndex("looplevel", new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51}, 0);
    model.result("pg8").run();
    model.result("pg8")
         .setIndex("looplevel", new int[]{51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121}, 0);
    model.result("pg8").run();
    model.result("pg1").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").add("plotgroup", "pg3");
    model.nodeGroup("grp1").label("\u4f2a\u5f39\u6027\uff0c\u5355\u6b21\u52a0\u8f7d\u5faa\u73af");

    model.result("pg4").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup().move("grp2", 1);
    model.nodeGroup("grp2").add("plotgroup", "pg4");
    model.nodeGroup("grp2").add("plotgroup", "pg5");
    model.nodeGroup("grp2").add("plotgroup", "pg6");
    model.nodeGroup("grp2").label("\u4f2a\u5f39\u6027\uff0c\u591a\u6b21\u52a0\u8f7d\u5faa\u73af");

    model.result("pg7").run();

    model.nodeGroup().create("grp3", "Results");
    model.nodeGroup("grp3").set("type", "plotgroup");
    model.nodeGroup().move("grp3", 2);
    model.nodeGroup("grp3").add("plotgroup", "pg7");
    model.nodeGroup("grp3").add("plotgroup", "pg8");
    model.nodeGroup("grp3").label("\u5f62\u72b6\u8bb0\u5fc6\u6548\u5e94");

    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat")
         .set("disabledphysics", new String[]{"solid/disp1", "solid/sma2", "solid/bndl2"});
    model.study("std2").feature("stat")
         .set("disabledphysics", new String[]{"solid/bndl1", "solid/sma2", "solid/bndl2"});

    return model;
  }

  public static Model run2(Model model) {

    model.result("pg8").run();
    model.result("pg8").set("ylabelactive", true);
    model.result("pg8").run();
    model.result("pg8").set("ylabelactive", false);
    model.result("pg8").run();

    model
         .title("Souza-Auricchio \u6a21\u578b\u5728\u5355\u8f74\u52a0\u8f7d\u5f62\u72b6\u8bb0\u5fc6\u5408\u91d1\u4e2d\u7684\u5e94\u7528");

    model
         .description("\u672c\u4f8b\u4f7f\u7528 Souza-Auricchio \u6a21\u578b\u6267\u884c\u4e86\u4e09\u4e2a\u7814\u7a76\uff0c\u4ee5\u6f14\u793a\u954d\u949b\u5408\u91d1\u5757\u5728\u5355\u8f74\u62c9\u4f38-\u538b\u7f29\u8f7d\u8377\u4f5c\u7528\u4e0b\u7684\u5c5e\u6027\u3002\n\n\u7b2c\u4e00\u4e2a\u53c2\u6570\u5316\u7814\u7a76\u663e\u793a\u4e0d\u540c\u6e29\u5ea6\u4e0b\u7684\u4f2a\u5f39\u6027\u6548\u5e94\u3002\u7b2c\u4e8c\u4e2a\u7814\u7a76\u4e2d\u6dfb\u52a0\u4e86\u90e8\u5206\u52a0\u8f7d-\u5378\u8f7d\u5faa\u73af\u3002\u6700\u540e\uff0c\u7b2c\u4e09\u4e2a\u7814\u7a76\u663e\u793a\u5728\u4f4e\u6e29\u52a0\u8f7d\u5faa\u73af\u540e\u5347\u9ad8\u6e29\u5ea6\u7684\u60c5\u51b5\u4e0b\uff0c\u5f62\u72b6\u8bb0\u5fc6\u6548\u5e94\u7684\u8868\u73b0\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();

    model.label("uniaxial_loading_of_shape_memory_alloy_souza_auricchio.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
