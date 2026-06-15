/*
 * directional_coupler.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:35 by COMSOL 6.3.0.290. */
public class directional_coupler {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Couplers_Filters_and_Mirrors");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ewbe", "ElectromagneticWavesBeamEnvelopes", "geom1");

    model.study().create("std1");
    model.study("std1").create("bma", "BoundaryModeAnalysis");
    model.study("std1").feature("bma").set("ftplistmethod", "manual");
    model.study("std1").feature("bma").set("shiftactive", false);
    model.study("std1").feature("bma").set("linpsolnum", "auto");
    model.study("std1").feature("bma").set("solnum", "auto");
    model.study("std1").feature("bma").set("notsolnum", "auto");
    model.study("std1").feature("bma").set("outputmap", new String[]{});
    model.study("std1").feature("bma").set("ngenAUX", "1");
    model.study("std1").feature("bma").set("goalngenAUX", "1");
    model.study("std1").feature("bma").set("ngenAUX", "1");
    model.study("std1").feature("bma").set("goalngenAUX", "1");
    model.study("std1").feature("bma").setSolveFor("/physics/ewbe", true);
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").set("ftplistmethod", "manual");
    model.study("std1").feature("freq").set("solnum", "auto");
    model.study("std1").feature("freq").set("notsolnum", "auto");
    model.study("std1").feature("freq").set("outputmap", new String[]{});
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").setSolveFor("/physics/ewbe", true);

    model.param().set("lda0", "1.15[um]");
    model.param().descr("lda0", "\u6ce2\u957f");
    model.param().set("f0", "c_const/lda0");
    model.param().descr("f0", "\u9891\u7387");
    model.param().set("a", "3[um]");
    model.param().descr("a", "\u6ce2\u5bfc\u6a2a\u622a\u9762\u7684\u8fb9\u957f");
    model.param().set("d", "3[um]");
    model.param().descr("d", "\u6ce2\u5bfc\u95f4\u8ddd");
    model.param().set("len", "2.1[mm]");
    model.param().descr("len", "\u6ce2\u5bfc\u957f\u5ea6");
    model.param().set("width", "6*a");
    model.param().descr("width", "\u8ba1\u7b97\u57df\u7684\u5bbd\u5ea6");
    model.param().set("height", "4*a");
    model.param().descr("height", "\u8ba1\u7b97\u57df\u7684\u9ad8\u5ea6");
    model.param().set("ncl", "3.47");
    model.param().descr("ncl", "\u7837\u5316\u9553\u7684\u6298\u5c04\u7387");
    model.param().set("dn", "0.005");
    model.param().descr("dn", "\u6ce2\u5bfc\u82af\u5c42\u7684\u6298\u5c04\u7387\u589e\u91cf");
    model.param().set("nco", "ncl+dn");
    model.param().descr("nco", "\u6ce2\u5bfc\u82af\u5c42\u7684\u6298\u5c04\u7387");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"len", "width", "height"});
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"len", "a", "a"});
    model.component("comp1").geom("geom1").feature("blk2").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"0", "-d", "0"});
    model.component("comp1").geom("geom1").feature().duplicate("blk3", "blk2");
    model.component("comp1").geom("geom1").feature("blk3").set("pos", new String[]{"0", "d", "0"});
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").camera().set("viewscaletype", "automatic");
    model.component("comp1").view("view1").camera().set("autoupdate", true);

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u7837\u5316\u9553\u5305\u5c42");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"ncl"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u79bb\u5b50\u6ce8\u5165\u7684\u7837\u5316\u9553\u82af\u5c42");
    model.component("comp1").material("mat2").selection().set(2, 3);
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").set("n", new String[]{"nco"});

    model.component("comp1").physics("ewbe")
         .label("\u7535\u78c1\u6ce2\uff0c\u6ce2\u675f\u5305\u7edc\uff0c\u5355\u5411");
    model.component("comp1").physics("ewbe").prop("WaveVector").set("dirCount", "UniDirectionality");
    model.component("comp1").physics("ewbe").prop("WaveVector").set("k1", new String[]{"ewbe.beta_1", "0", "0"});
    model.component("comp1").physics("ewbe").create("port1", "Port", 2);
    model.component("comp1").physics("ewbe").feature("port1").selection().set(1, 5, 10);
    model.component("comp1").physics("ewbe").feature("port1").set("PortType", "Numeric");
    model.component("comp1").physics("ewbe").feature().duplicate("port2", "port1");
    model.component("comp1").physics("ewbe").create("port3", "Port", 2);
    model.component("comp1").physics("ewbe").feature("port3").selection().set(16, 17, 18);
    model.component("comp1").physics("ewbe").feature("port3").set("PortType", "Numeric");
    model.component("comp1").physics("ewbe").feature().duplicate("port4", "port3");

    model.component("comp1").mesh("mesh1").label("\u7f51\u683c\uff0c\u5355\u5411");

    model.component("comp1").physics("ewbe").prop("MeshControl").set("elemCountT", 20);
    model.component("comp1").physics("ewbe").prop("MeshControl").set("elemCountL", 20);

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76\uff0c\u5355\u5411");
    model.study("std1").feature("bma").set("neigsactive", true);
    model.study("std1").feature("bma").set("neigs", 4);
    model.study("std1").feature("bma").set("shiftactive", true);
    model.study("std1").feature("bma").set("shift", "nco");
    model.study("std1").feature("bma").set("modeFreq", "f0");
    model.study("std1").createAutoSequences("sol");

    model.sol("sol1").runFromTo("st1", "su1");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u7535\u573a (ewbe)");
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").label("\u7535\u573a");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("smooth", "internal");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u7535\u6a21\u573a\uff0c\u7aef\u53e3 1 (ewbe)");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "root.comp1.ewbe.normEbm_1");
    model.result("pg2").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg2").create("ann1", "Annotation");
    model.result("pg2").feature("ann1").set("posxexpr", "ewbe.aveport1(x)");
    model.result("pg2").feature("ann1").set("posyexpr", "ewbe.aveport1(y)");
    model.result("pg2").feature("ann1").set("poszexpr", "ewbe.aveport1(z)");
    model.result("pg2").feature("ann1").set("backgroundcolor", "fromtheme");
    model.result("pg2").feature("ann1")
         .set("text", "\u6709\u6548\u6a21\u5f0f\u6298\u5c04\u7387: eval(comp1.ewbe.neff_1)");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u7535\u6a21\u573a\uff0c\u7aef\u53e3 2 (ewbe)");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").set("data", "dset1");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "root.comp1.ewbe.normEbm_2");
    model.result("pg3").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg3").create("ann1", "Annotation");
    model.result("pg3").feature("ann1").set("posxexpr", "ewbe.aveport2(x)");
    model.result("pg3").feature("ann1").set("posyexpr", "ewbe.aveport2(y)");
    model.result("pg3").feature("ann1").set("poszexpr", "ewbe.aveport2(z)");
    model.result("pg3").feature("ann1").set("backgroundcolor", "fromtheme");
    model.result("pg3").feature("ann1")
         .set("text", "\u6709\u6548\u6a21\u5f0f\u6298\u5c04\u7387: eval(comp1.ewbe.neff_2)");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u7535\u6a21\u573a\uff0c\u7aef\u53e3 3 (ewbe)");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").set("data", "dset1");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "root.comp1.ewbe.normEbm_3");
    model.result("pg4").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg4").create("ann1", "Annotation");
    model.result("pg4").feature("ann1").set("posxexpr", "ewbe.aveport3(x)");
    model.result("pg4").feature("ann1").set("posyexpr", "ewbe.aveport3(y)");
    model.result("pg4").feature("ann1").set("poszexpr", "ewbe.aveport3(z)");
    model.result("pg4").feature("ann1").set("backgroundcolor", "fromtheme");
    model.result("pg4").feature("ann1")
         .set("text", "\u6709\u6548\u6a21\u5f0f\u6298\u5c04\u7387: eval(comp1.ewbe.neff_3)");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u7535\u6a21\u573a\uff0c\u7aef\u53e3 4 (ewbe)");
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").set("data", "dset1");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "root.comp1.ewbe.normEbm_4");
    model.result("pg5").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg5").create("ann1", "Annotation");
    model.result("pg5").feature("ann1").set("posxexpr", "ewbe.aveport4(x)");
    model.result("pg5").feature("ann1").set("posyexpr", "ewbe.aveport4(y)");
    model.result("pg5").feature("ann1").set("poszexpr", "ewbe.aveport4(z)");
    model.result("pg5").feature("ann1").set("backgroundcolor", "fromtheme");
    model.result("pg5").feature("ann1")
         .set("text", "\u6709\u6548\u6a21\u5f0f\u6298\u5c04\u7387: eval(comp1.ewbe.neff_4)");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "ewbe.tEbm1z");
    model.result("pg2").feature("surf1").set("descr", "\u8fb9\u754c\u6a21\u5f0f\u7535\u573a\uff0cz \u5206\u91cf");
    model.result("pg2").feature("surf1").set("colortable", "WaveLight");
    model.result("pg2").run();
    model.result("pg2").set("looplevel", new int[]{4});
    model.result("pg2").run();
    model.result("pg2").set("looplevel", new int[]{2});
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "ewbe.tEbm1y");
    model.result("pg2").run();
    model.result("pg2").set("looplevel", new int[]{3});
    model.result("pg2").run();
    model.result("pg2").set("looplevel", new int[]{1});
    model.result("pg2").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("expr", new String[]{"ewbe.neff_1"});
    model.result().numerical("gev1").set("descr", new String[]{"\u6709\u6548\u6a21\u5f0f\u6298\u5c04\u7387"});
    model.result().numerical("gev1").set("unit", new String[]{"1"});
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();

    model.study("std1").feature("bma").set("neigs", 1);
    model.study("std1").feature("bma").set("shift", "3.4716717443092047");
    model.study("std1").feature().duplicate("bma1", "bma");
    model.study("std1").feature("bma1").set("shift", "3.4714219480792172");
    model.study("std1").feature("bma1").set("PortName", "2");
    model.study("std1").feature().duplicate("bma2", "bma");
    model.study("std1").feature().duplicate("bma3", "bma1");
    model.study("std1").feature("bma2").set("PortName", "3");
    model.study("std1").feature("bma3").set("PortName", "4");
    model.study("std1").feature("freq").set("plist", "f0");
    model.study("std1").feature().move("freq", 2);
    model.study("std1").feature().move("freq", 3);
    model.study("std1").feature().move("freq", 4);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("mslc1").set("xnumber", "0");
    model.result("pg1").feature("mslc1").set("ynumber", "0");
    model.result("pg1").feature("mslc1").set("smooth", "everywhere");
    model.result("pg1").feature("mslc1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").feature("mslc1").feature("def1")
         .set("expr", new String[]{"", "", "patcheval(ewbe.normE,2)"});
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "ewbe.normEbm_1");
    model.result("pg2").feature("surf1").set("descr", "\u5207\u5411\u8fb9\u754c\u6a21\u5f0f\u7535\u573a\u6a21");
    model.result("pg2").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg5").run();

    model.component("comp1").physics("ewbe").feature("port2").set("Thetap", "pi");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.component("comp1").physics().copy("ewbe2", "ewbe");
    model.component("comp1").physics("ewbe2")
         .label("\u7535\u78c1\u6ce2\uff0c\u6ce2\u675f\u5305\u7edc\uff0c\u53cc\u5411");
    model.component("comp1").physics("ewbe2").prop("WaveVector").set("dirCount", "BiDirectionality");
    model.component("comp1").physics("ewbe2").prop("WaveVector")
         .set("k2", new String[]{"ewbe2.beta_2", "ewbe2.k1y", "ewbe2.k1z"});

    model.component("comp1").mesh("mesh1").contribute("physics/ewbe2", false);
    model.component("comp1").mesh().create("mesh2");
    model.component("comp1").mesh("mesh2").contribute("geom/detail", true);
    model.component("comp1").mesh("mesh2").label("\u7f51\u683c\uff0c\u53cc\u5411");
    model.component("comp1").mesh("mesh2").contribute("physics/ewbe", false);

    model.component("comp1").physics("ewbe2").prop("MeshControl").set("elemCountL", 5);

    model.component("comp1").mesh("mesh2").run();

    model.study().create("std2");
    model.study("std2").label("\u7814\u7a76\uff0c\u53cc\u5411");
    model.study("std2").feature().copy("bma", "std1/bma");
    model.study("std2").feature().copy("bma1", "std1/bma1");
    model.study("std2").feature().copy("bma2", "std1/bma2");
    model.study("std2").feature().copy("bma3", "std1/bma3");
    model.study("std2").feature().copy("freq", "std1/freq");
    model.study("std2").feature("bma").setSolveFor("/physics/ewbe", false);
    model.study("std2").feature("bma").setEntry("mesh", "geom1", "mesh2");
    model.study("std2").feature("bma1").setSolveFor("/physics/ewbe", false);
    model.study("std2").feature("bma1").setEntry("mesh", "geom1", "mesh2");
    model.study("std2").feature("bma2").setSolveFor("/physics/ewbe", false);
    model.study("std2").feature("bma2").setEntry("mesh", "geom1", "mesh2");
    model.study("std2").feature("bma3").setSolveFor("/physics/ewbe", false);
    model.study("std2").feature("bma3").setEntry("mesh", "geom1", "mesh2");
    model.study("std2").feature("freq").setSolveFor("/physics/ewbe", false);
    model.study("std2").feature("freq").setEntry("mesh", "geom1", "mesh2");
    model.study("std2").createAutoSequences("all");

    model.sol("sol6").runAll();

    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u7535\u573a\uff0c\u7b2c\u4e00\u4e2a\u6ce2 (ewbe2)");
    model.result("pg6").set("data", "dset6");
    model.result("pg6").setIndex("looplevel", 1, 0);
    model.result("pg6").feature().create("mslc1", "Multislice");
    model.result("pg6").feature("mslc1").label("\u7535\u573a");
    model.result("pg6").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg6").feature("mslc1").set("expr", "ewbe2.normE1");
    model.result("pg6").feature("mslc1").set("smooth", "internal");
    model.result("pg6").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg6").feature("mslc1").set("data", "parent");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").label("\u7535\u573a\uff0c\u7b2c\u4e8c\u4e2a\u6ce2 (ewbe2)");
    model.result("pg7").set("data", "dset6");
    model.result("pg7").setIndex("looplevel", 1, 0);
    model.result("pg7").feature().create("mslc1", "Multislice");
    model.result("pg7").feature("mslc1").label("\u7535\u573a");
    model.result("pg7").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg7").feature("mslc1").set("expr", "ewbe2.normE2");
    model.result("pg7").feature("mslc1").set("smooth", "internal");
    model.result("pg7").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg7").feature("mslc1").set("data", "parent");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").label("\u7535\u6a21\u573a\uff0c\u7aef\u53e3 1 (ewbe2)");
    model.result("pg8").set("showlegendsmaxmin", true);
    model.result("pg8").set("data", "dset6");
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", "root.comp1.ewbe2.normEbm_1");
    model.result("pg8").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg8").create("ann1", "Annotation");
    model.result("pg8").feature("ann1").set("posxexpr", "ewbe2.aveport1(x)");
    model.result("pg8").feature("ann1").set("posyexpr", "ewbe2.aveport1(y)");
    model.result("pg8").feature("ann1").set("poszexpr", "ewbe2.aveport1(z)");
    model.result("pg8").feature("ann1").set("backgroundcolor", "fromtheme");
    model.result("pg8").feature("ann1")
         .set("text", "\u6709\u6548\u6a21\u5f0f\u6298\u5c04\u7387: eval(comp1.ewbe2.neff_1)");
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").label("\u7535\u6a21\u573a\uff0c\u7aef\u53e3 2 (ewbe2)");
    model.result("pg9").set("showlegendsmaxmin", true);
    model.result("pg9").set("data", "dset6");
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", "root.comp1.ewbe2.normEbm_2");
    model.result("pg9").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg9").create("ann1", "Annotation");
    model.result("pg9").feature("ann1").set("posxexpr", "ewbe2.aveport2(x)");
    model.result("pg9").feature("ann1").set("posyexpr", "ewbe2.aveport2(y)");
    model.result("pg9").feature("ann1").set("poszexpr", "ewbe2.aveport2(z)");
    model.result("pg9").feature("ann1").set("backgroundcolor", "fromtheme");
    model.result("pg9").feature("ann1")
         .set("text", "\u6709\u6548\u6a21\u5f0f\u6298\u5c04\u7387: eval(comp1.ewbe2.neff_2)");
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").label("\u7535\u6a21\u573a\uff0c\u7aef\u53e3 3 (ewbe2)");
    model.result("pg10").set("showlegendsmaxmin", true);
    model.result("pg10").set("data", "dset6");
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("expr", "root.comp1.ewbe2.normEbm_3");
    model.result("pg10").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg10").create("ann1", "Annotation");
    model.result("pg10").feature("ann1").set("posxexpr", "ewbe2.aveport3(x)");
    model.result("pg10").feature("ann1").set("posyexpr", "ewbe2.aveport3(y)");
    model.result("pg10").feature("ann1").set("poszexpr", "ewbe2.aveport3(z)");
    model.result("pg10").feature("ann1").set("backgroundcolor", "fromtheme");
    model.result("pg10").feature("ann1")
         .set("text", "\u6709\u6548\u6a21\u5f0f\u6298\u5c04\u7387: eval(comp1.ewbe2.neff_3)");
    model.result().create("pg11", "PlotGroup3D");
    model.result("pg11").label("\u7535\u6a21\u573a\uff0c\u7aef\u53e3 4 (ewbe2)");
    model.result("pg11").set("showlegendsmaxmin", true);
    model.result("pg11").set("data", "dset6");
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("expr", "root.comp1.ewbe2.normEbm_4");
    model.result("pg11").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg11").create("ann1", "Annotation");
    model.result("pg11").feature("ann1").set("posxexpr", "ewbe2.aveport4(x)");
    model.result("pg11").feature("ann1").set("posyexpr", "ewbe2.aveport4(y)");
    model.result("pg11").feature("ann1").set("poszexpr", "ewbe2.aveport4(z)");
    model.result("pg11").feature("ann1").set("backgroundcolor", "fromtheme");
    model.result("pg11").feature("ann1")
         .set("text", "\u6709\u6548\u6a21\u5f0f\u6298\u5c04\u7387: eval(comp1.ewbe2.neff_4)");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("mslc1").set("xnumber", "0");
    model.result("pg6").feature("mslc1").set("ynumber", "0");
    model.result("pg6").run();
    model.result("pg7").run();
    model.result("pg7").feature("mslc1").set("xnumber", "0");
    model.result("pg7").feature("mslc1").set("ynumber", "0");
    model.result("pg7").run();
    model.result("pg1").run();
    model.result().duplicate("pg12", "pg1");
    model.result("pg12").run();
    model.result("pg12").label("\u7535\u573a\uff0c\u53cc\u5411");
    model.result("pg12").set("data", "dset6");
    model.result("pg12").run();
    model.result("pg12").feature("mslc1").set("expr", "ewbe2.normE");
    model.result("pg12").run();
    model.result("pg12").feature("mslc1").feature("def1").set("expr", new String[]{"", "", "ewbe2.normE"});
    model.result("pg12").run();

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").selection().geom("geom1", 2);
    model.component("comp1").variable("var1").selection().set(1, 5);
    model.component("comp1").variable("var1").set("Etarget", "0[V/m]");
    model.component("comp1").variable("var1").descr("Etarget", "\u76ee\u6807\u7535\u573a");
    model.component("comp1").variable().duplicate("var2", "var1");
    model.component("comp1").variable("var2").selection().set(10);
    model.component("comp1").variable("var2").set("Etarget", "1[V/m]");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().set(1, 5, 10);

    model.component("comp1").physics().copy("ewbe3", "ewbe2");
    model.component("comp1").physics("ewbe3")
         .label("\u7535\u78c1\u6ce2\uff0c\u6ce2\u675f\u5305\u7edc\uff0c\u53cc\u5411\uff0c\u6a21\u5f0f\u5c55\u5f00");

    model.component("comp1").variable().create("var3");
    model.component("comp1").variable("var3")
         .set("Pin1", "abs(intop1(Etarget*conj(ewbe3.tH0modey_1)))^2/abs(4*ewbe3.Pmode_1)");
    model.component("comp1").variable("var3")
         .set("ph1", "arg(intop1(Etarget*conj(ewbe3.tH0modey_1))/ewbe3.Pmode_1)");
    model.component("comp1").variable("var3")
         .set("Pin2", "abs(intop1(Etarget*conj(ewbe3.tH0modey_2)))^2/abs(4*ewbe3.Pmode_2)");
    model.component("comp1").variable("var3")
         .set("ph2", "arg(intop1(Etarget*conj(ewbe3.tH0modey_2))/ewbe3.Pmode_2)");

    model.component("comp1").physics("ewbe3").feature("port1").set("Pin", "Pin1");
    model.component("comp1").physics("ewbe3").feature("port1").set("Thetap", "ph1");
    model.component("comp1").physics("ewbe3").feature("port2").set("Pin", "Pin2");
    model.component("comp1").physics("ewbe3").feature("port2").set("Thetap", "ph2");

    model.study().create("std3");
    model.study("std3").label("\u7814\u7a76\uff0c\u53cc\u5411\uff0c\u6a21\u5f0f\u5c55\u5f00");
    model.study("std3").feature().copy("bma", "std2/bma");
    model.study("std3").feature().copy("bma1", "std2/bma1");
    model.study("std3").feature().copy("bma2", "std2/bma2");
    model.study("std3").feature().copy("bma3", "std2/bma3");
    model.study("std3").feature().copy("freq", "std2/freq");
    model.study("std3").feature("bma").setSolveFor("/physics/ewbe2", false);
    model.study("std3").feature("bma1").setSolveFor("/physics/ewbe2", false);
    model.study("std3").feature("bma2").setSolveFor("/physics/ewbe2", false);
    model.study("std3").feature("bma3").setSolveFor("/physics/ewbe2", false);
    model.study("std3").feature("freq").setSolveFor("/physics/ewbe2", false);
    model.study("std3").createAutoSequences("all");

    model.sol("sol11").runAll();

    model.result().create("pg13", "PlotGroup3D");
    model.result("pg13").label("\u7535\u573a\uff0c\u7b2c\u4e00\u4e2a\u6ce2 (ewbe3)");
    model.result("pg13").set("data", "dset11");
    model.result("pg13").setIndex("looplevel", 1, 0);
    model.result("pg13").feature().create("mslc1", "Multislice");
    model.result("pg13").feature("mslc1").label("\u7535\u573a");
    model.result("pg13").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg13").feature("mslc1").set("expr", "ewbe3.normE1");
    model.result("pg13").feature("mslc1").set("smooth", "internal");
    model.result("pg13").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg13").feature("mslc1").set("data", "parent");
    model.result().create("pg14", "PlotGroup3D");
    model.result("pg14").label("\u7535\u573a\uff0c\u7b2c\u4e8c\u4e2a\u6ce2 (ewbe3)");
    model.result("pg14").set("data", "dset11");
    model.result("pg14").setIndex("looplevel", 1, 0);
    model.result("pg14").feature().create("mslc1", "Multislice");
    model.result("pg14").feature("mslc1").label("\u7535\u573a");
    model.result("pg14").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg14").feature("mslc1").set("expr", "ewbe3.normE2");
    model.result("pg14").feature("mslc1").set("smooth", "internal");
    model.result("pg14").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg14").feature("mslc1").set("data", "parent");
    model.result().create("pg15", "PlotGroup3D");
    model.result("pg15").label("\u7535\u6a21\u573a\uff0c\u7aef\u53e3 1 (ewbe3)");
    model.result("pg15").set("showlegendsmaxmin", true);
    model.result("pg15").set("data", "dset11");
    model.result("pg15").create("surf1", "Surface");
    model.result("pg15").feature("surf1").set("expr", "root.comp1.ewbe3.normEbm_1");
    model.result("pg15").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg15").create("ann1", "Annotation");
    model.result("pg15").feature("ann1").set("posxexpr", "ewbe3.aveport1(x)");
    model.result("pg15").feature("ann1").set("posyexpr", "ewbe3.aveport1(y)");
    model.result("pg15").feature("ann1").set("poszexpr", "ewbe3.aveport1(z)");
    model.result("pg15").feature("ann1").set("backgroundcolor", "fromtheme");
    model.result("pg15").feature("ann1")
         .set("text", "\u6709\u6548\u6a21\u5f0f\u6298\u5c04\u7387: eval(comp1.ewbe3.neff_1)");
    model.result().create("pg16", "PlotGroup3D");
    model.result("pg16").label("\u7535\u6a21\u573a\uff0c\u7aef\u53e3 2 (ewbe3)");
    model.result("pg16").set("showlegendsmaxmin", true);
    model.result("pg16").set("data", "dset11");
    model.result("pg16").create("surf1", "Surface");
    model.result("pg16").feature("surf1").set("expr", "root.comp1.ewbe3.normEbm_2");
    model.result("pg16").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg16").create("ann1", "Annotation");
    model.result("pg16").feature("ann1").set("posxexpr", "ewbe3.aveport2(x)");
    model.result("pg16").feature("ann1").set("posyexpr", "ewbe3.aveport2(y)");
    model.result("pg16").feature("ann1").set("poszexpr", "ewbe3.aveport2(z)");
    model.result("pg16").feature("ann1").set("backgroundcolor", "fromtheme");
    model.result("pg16").feature("ann1")
         .set("text", "\u6709\u6548\u6a21\u5f0f\u6298\u5c04\u7387: eval(comp1.ewbe3.neff_2)");
    model.result().create("pg17", "PlotGroup3D");
    model.result("pg17").label("\u7535\u6a21\u573a\uff0c\u7aef\u53e3 3 (ewbe3)");
    model.result("pg17").set("showlegendsmaxmin", true);
    model.result("pg17").set("data", "dset11");
    model.result("pg17").create("surf1", "Surface");
    model.result("pg17").feature("surf1").set("expr", "root.comp1.ewbe3.normEbm_3");
    model.result("pg17").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg17").create("ann1", "Annotation");
    model.result("pg17").feature("ann1").set("posxexpr", "ewbe3.aveport3(x)");
    model.result("pg17").feature("ann1").set("posyexpr", "ewbe3.aveport3(y)");
    model.result("pg17").feature("ann1").set("poszexpr", "ewbe3.aveport3(z)");
    model.result("pg17").feature("ann1").set("backgroundcolor", "fromtheme");
    model.result("pg17").feature("ann1")
         .set("text", "\u6709\u6548\u6a21\u5f0f\u6298\u5c04\u7387: eval(comp1.ewbe3.neff_3)");
    model.result().create("pg18", "PlotGroup3D");
    model.result("pg18").label("\u7535\u6a21\u573a\uff0c\u7aef\u53e3 4 (ewbe3)");
    model.result("pg18").set("showlegendsmaxmin", true);
    model.result("pg18").set("data", "dset11");
    model.result("pg18").create("surf1", "Surface");
    model.result("pg18").feature("surf1").set("expr", "root.comp1.ewbe3.normEbm_4");
    model.result("pg18").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg18").create("ann1", "Annotation");
    model.result("pg18").feature("ann1").set("posxexpr", "ewbe3.aveport4(x)");
    model.result("pg18").feature("ann1").set("posyexpr", "ewbe3.aveport4(y)");
    model.result("pg18").feature("ann1").set("poszexpr", "ewbe3.aveport4(z)");
    model.result("pg18").feature("ann1").set("backgroundcolor", "fromtheme");
    model.result("pg18").feature("ann1")
         .set("text", "\u6709\u6548\u6a21\u5f0f\u6298\u5c04\u7387: eval(comp1.ewbe3.neff_4)");
    model.result("pg13").run();
    model.result("pg13").run();
    model.result("pg13").feature("mslc1").set("xnumber", "0");
    model.result("pg13").feature("mslc1").set("ynumber", "0");
    model.result("pg13").run();
    model.result("pg14").run();
    model.result("pg14").feature("mslc1").set("xnumber", "0");
    model.result("pg14").feature("mslc1").set("ynumber", "0");
    model.result("pg14").run();
    model.result("pg14").run();
    model.result().duplicate("pg19", "pg14");
    model.result("pg19").run();
    model.result("pg19").label("\u7535\u573a\uff0c\u53cc\u5411\uff0c\u6a21\u5f0f\u5c55\u5f00");
    model.result("pg19").run();

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg19").feature("mslc1").set("expr", "ewbe3.normE");
    model.result("pg19").feature("mslc1").set("smooth", "everywhere");
    model.result("pg19").feature("mslc1").create("def1", "Deform");
    model.result("pg19").run();
    model.result("pg19").feature("mslc1").feature("def1").set("expr", new String[]{"", "", "ewbe3.normE"});
    model.result("pg19").run();
    model.result().create("pg20", "PlotGroup3D");
    model.result("pg20").run();
    model.result("pg20").label("\u7535\u573a\uff0c\u5b9e\u9645\u4e0e\u76ee\u6807\u4e4b\u95f4\u7684\u5dee\u5f02");
    model.result("pg20").set("data", "dset11");
    model.result("pg20").create("surf1", "Surface");
    model.result("pg20").feature("surf1").set("expr", "Etarget");
    model.result("pg20").feature("surf1").create("def1", "Deform");
    model.result("pg20").run();
    model.result("pg20").feature("surf1").feature("def1").set("expr", new String[]{"-Etarget", "", ""});
    model.result("pg20").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg20").feature("surf1").feature("def1").set("scale", "1E-3");
    model.result("pg20").run();
    model.result("pg20").create("surf2", "Surface");
    model.result("pg20").feature("surf2").set("expr", "ewbe3.Ez");
    model.result("pg20").feature("surf2").set("inheritplot", "surf1");
    model.result("pg20").feature("surf2").create("sel1", "Selection");
    model.result("pg20").feature("surf2").feature("sel1").selection().set(1, 5, 10);
    model.result("pg20").run();
    model.result("pg20").feature("surf2").create("def1", "Deform");
    model.result("pg20").run();
    model.result("pg20").feature("surf2").feature("def1").set("expr", new String[]{"-ewbe3.Ez", "", ""});
    model.result("pg20").run();

    model.component("comp1").variable("var1").selection().set(1, 10);
    model.component("comp1").variable("var2").selection().set(5);

    model.study("std3").createAutoSequences("all");

    model.sol("sol11").runAll();

    model.result("pg13").run();
    model.result("pg19").run();
    model.result("pg20").run();

    model.component("comp1").mesh("mesh1").contribute("physics/ewbe3", false);

    model.study("std1").feature("bma").setSolveFor("/physics/ewbe2", false);
    model.study("std1").feature("bma").setSolveFor("/physics/ewbe3", false);
    model.study("std1").feature("bma1").setSolveFor("/physics/ewbe2", false);
    model.study("std1").feature("bma1").setSolveFor("/physics/ewbe3", false);
    model.study("std1").feature("bma2").setSolveFor("/physics/ewbe2", false);
    model.study("std1").feature("bma2").setSolveFor("/physics/ewbe3", false);
    model.study("std1").feature("bma3").setSolveFor("/physics/ewbe2", false);
    model.study("std1").feature("bma3").setSolveFor("/physics/ewbe3", false);
    model.study("std1").feature("freq").setSolveFor("/physics/ewbe2", false);
    model.study("std1").feature("freq").setSolveFor("/physics/ewbe3", false);
    model.study("std2").feature("bma").setSolveFor("/physics/ewbe3", false);
    model.study("std2").feature("bma1").setSolveFor("/physics/ewbe3", false);
    model.study("std2").feature("bma2").setSolveFor("/physics/ewbe3", false);
    model.study("std2").feature("bma3").setSolveFor("/physics/ewbe3", false);
    model.study("std2").feature("freq").setSolveFor("/physics/ewbe3", false);

    model.result("pg1").run();

    model.title("\u5b9a\u5411\u8026\u5408\u5668");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u7531\u4e24\u4e2a\u8fd1\u8ddd\u79bb\u7684\u5d4c\u5165\u5f0f\u5149\u6ce2\u5bfc\u5f62\u6210\u7684\u5b9a\u5411\u8026\u5408\u5668\u3002\u5305\u5c42\u6750\u6599\u662f\u7837\u5316\u9553\uff0c\u82af\u5c42\u6750\u6599\u662f\u79bb\u5b50\u6ce8\u5165\u7837\u5316\u9553\u3002\u6ce2\u5bfc\u7531\u6ce2\u5bfc\u7ed3\u6784\u7684\u4e24\u4e2a\u4e00\u9636\u8d85\u6a21\u6fc0\u52b1\uff1a\u5bf9\u79f0\u6a21\u548c\u53cd\u5bf9\u79f0\u6a21\u3002\u6fc0\u52b1\u8fb9\u754c\u548c\u5438\u6536\u8fb9\u754c\u5747\u4f7f\u7528\u4e24\u4e2a\u6570\u5b57\u7aef\u53e3\u6765\u5b9a\u4e49\u8fd9\u4e24\u79cd\u6a21\u5f0f\u3002\u6a21\u578b\u4e2d\u8bbe\u7f6e\u4e86\u8fb9\u754c\u6a21\u5f0f\u5206\u6790\u7814\u7a76\u5e8f\u5217\uff0c\u5305\u542b\u56db\u79cd\u8fb9\u754c\u6a21\u5f0f\u5206\u6790\u4ee5\u53ca\u6700\u540e\u6267\u884c\u7684\u9891\u57df\u7814\u7a76\uff1b\u6f14\u793a\u4e86\u4ece\u4e00\u4e2a\u6ce2\u5bfc\u5230\u53e6\u4e00\u4e2a\u6ce2\u5bfc\u7684\u8026\u5408\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();
    model.sol("sol14").clearSolutionData();
    model.sol("sol13").clearSolutionData();
    model.sol("sol15").clearSolutionData();
    model.sol("sol12").clearSolutionData();

    model.label("directional_coupler.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
