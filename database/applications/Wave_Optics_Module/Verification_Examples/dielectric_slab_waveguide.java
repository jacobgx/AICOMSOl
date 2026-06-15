/*
 * dielectric_slab_waveguide.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:42 by COMSOL 6.3.0.290. */
public class dielectric_slab_waveguide {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ewfd", "ElectromagneticWavesFrequencyDomain", "geom1");

    model.study().create("std1");

    model.param().set("lda0", "1550[nm]");
    model.param().descr("lda0", "\u6ce2\u957f");
    model.param().set("n_core", "1.5");
    model.param().descr("n_core", "\u6298\u5c04\u7387\uff0c\u82af\u5c42");
    model.param().set("n_cladding", "1");
    model.param().descr("n_cladding", "\u6298\u5c04\u7387\uff0c\u5305\u5c42");
    model.param().set("h_core", "1[um]");
    model.param().descr("h_core", "\u539a\u5ea6\uff0c\u82af\u5c42");
    model.param().set("h_cladding", "7[um]");
    model.param().descr("h_cladding", "\u539a\u5ea6\uff0c\u5305\u5c42");
    model.param().set("w_slab", "5[um]");
    model.param().descr("w_slab", "\u677f\u6761\u5bbd\u5ea6");
    model.param().set("k_core", "2*pi[rad]*n_core/lda0");
    model.param().descr("k_core", "\u6ce2\u6570\uff0c\u82af\u5c42");
    model.param().set("k_cladding", "2*pi[rad]*n_cladding/lda0");
    model.param().descr("k_cladding", "\u6ce2\u6570\uff0c\u5305\u5c42");
    model.param().set("f0", "c_const/lda0");
    model.param().descr("f0", "\u9891\u7387");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"w_slab", "h_core"});
    model.component("comp1").geom("geom1").feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"w_slab", "h_cladding"});
    model.component("comp1").geom("geom1").feature("r2").set("base", "center");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("ewfd").prop("components").set("components", "outofplane");
    model.component("comp1").physics("ewfd").create("port1", "Port", 1);
    model.component("comp1").physics("ewfd").feature("port1").selection().set(1, 3, 5);
    model.component("comp1").physics("ewfd").feature("port1").set("PortType", "Numeric");
    model.component("comp1").physics("ewfd").create("port2", "Port", 1);
    model.component("comp1").physics("ewfd").feature("port2").selection().set(8, 9, 10);
    model.component("comp1").physics("ewfd").feature("port2").set("PortType", "Numeric");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u5305\u5c42");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"n_cladding"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u82af\u5c42");
    model.component("comp1").material("mat2").selection().set(2);
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").set("n", new String[]{"n_core"});

    model.study("std1").create("bma", "BoundaryModeAnalysis");
    model.study("std1").feature("bma").set("shift", "n_core");
    model.study("std1").feature("bma").set("modeFreq", "f0");
    model.study("std1").create("bma2", "BoundaryModeAnalysis");
    model.study("std1").feature("bma2").set("shift", "n_core");
    model.study("std1").feature("bma2").set("PortName", "2");
    model.study("std1").feature("bma2").set("modeFreq", "f0");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").set("plist", "f0");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u573a (ewfd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1")
         .label("\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387 (ewfd)");
    model.result().numerical("gev1").set("data", "dset1");
    model.result().numerical("gev1")
         .set("expr", new String[]{"ewfd.Rport_1", "ewfd.Rtotal", "ewfd.Tport_2", "ewfd.Ttotal", "ewfd.RTtotal", "ewfd.Atotal"});
    model.result().table().create("tbl1", "Table");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").run();
    model.result().numerical("gev1").setResult();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u7535\u6a21\u573a\uff0c\u7aef\u53e3 1 (ewfd)");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("line1", "Line");
    model.result("pg2").feature("line1").set("expr", "root.comp1.ewfd.normEbm_1");
    model.result("pg2").feature("line1").set("colortable", "RainbowLight");
    model.result("pg2").feature("line1").set("linetype", "tube");
    model.result("pg2").feature("line1").create("hght1", "Height");
    model.result("pg2").create("ann1", "Annotation");
    model.result("pg2").feature("ann1").set("posxexpr", "ewfd.aveport1(x)");
    model.result("pg2").feature("ann1").set("posyexpr", "ewfd.aveport1(y)");
    model.result("pg2").feature("ann1").set("backgroundcolor", "fromtheme");
    model.result("pg2").feature("ann1")
         .set("text", "\u6709\u6548\u6a21\u5f0f\u6298\u5c04\u7387: eval(comp1.ewfd.neff_1)");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u7535\u6a21\u573a\uff0c\u7aef\u53e3 2 (ewfd)");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").set("data", "dset1");
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("expr", "root.comp1.ewfd.normEbm_2");
    model.result("pg3").feature("line1").set("colortable", "RainbowLight");
    model.result("pg3").feature("line1").set("linetype", "tube");
    model.result("pg3").feature("line1").create("hght1", "Height");
    model.result("pg3").create("ann1", "Annotation");
    model.result("pg3").feature("ann1").set("posxexpr", "ewfd.aveport2(x)");
    model.result("pg3").feature("ann1").set("posyexpr", "ewfd.aveport2(y)");
    model.result("pg3").feature("ann1").set("backgroundcolor", "fromtheme");
    model.result("pg3").feature("ann1")
         .set("text", "\u6709\u6548\u6a21\u5f0f\u6298\u5c04\u7387: eval(comp1.ewfd.neff_2)");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "ewfd.Ez");
    model.result("pg1").feature("surf1").set("descr", "\u7535\u573a\uff0cz \u5206\u91cf");
    model.result("pg1").feature("surf1").set("colortable", "WaveLight");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg3").run();

    model.component("comp1").physics().create("ge", "GlobalEquations", "geom1");

    model.study("std1").feature("bma").setSolveFor("/physics/ge", false);
    model.study("std1").feature("bma2").setSolveFor("/physics/ge", false);
    model.study("std1").feature("freq").setSolveFor("/physics/ge", false);

    model.component("comp1").physics("ge").prop("EquationForm").set("form", "Automatic");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/ewfd", false);
    model.study("std2").feature("stat").setSolveFor("/physics/ge", true);

    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "alpha", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("equation", "alpha-k_y*tan(k_y*h_core/2)", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueU", "k_core/2", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "k_y", 1, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("equation", "", 1, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueU", 0, 1, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueUt", 0, 1, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("description", "", 1, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("equation", "k_y^2-(k_core^2-k_cladding^2-alpha^2)", 1, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueU", "k_core/2", 1, 0);

    model.study("std2").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").set("data", "dset4");
    model.result().numerical("gev2").set("expr", new String[]{"alpha", "k_y"});
    model.result().numerical("gev2")
         .set("descr", new String[]{"\u72b6\u6001\u53d8\u91cf alpha", "\u72b6\u6001\u53d8\u91cf k_y"});
    model.result().numerical().create("gev3", "EvalGlobal");
    model.result().numerical("gev3").set("descr", new String[]{});
    model.result().numerical("gev3").set("unit", new String[]{});
    model.result().numerical("gev3").set("expr", new String[]{"ewfd.beta_1", "ewfd.beta_2"});
    model.result().numerical("gev3").setIndex("descr", "\u4f20\u64ad\u5e38\u6570\uff0c\u7aef\u53e3 1", 0);
    model.result().numerical("gev3").setIndex("descr", "\u4f20\u64ad\u5e38\u6570\uff0c\u7aef\u53e3 2", 1);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u5168\u5c40\u8ba1\u7b97 3");
    model.result().numerical("gev3").set("table", "tbl2");
    model.result().numerical("gev3").setResult();
    model.result().numerical().create("gev4", "EvalGlobal");
    model.result().numerical("gev4").set("data", "dset4");
    model.result().numerical("gev4").setIndex("expr", "sqrt(k_core^2-k_y^2)", 0);
    model.result().numerical("gev4").setIndex("descr", "\u4f20\u64ad\u5e38\u6570\uff0c\u8ba1\u7b97\u503c", 0);
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("\u5168\u5c40\u8ba1\u7b97 4");
    model.result().numerical("gev4").set("table", "tbl3");
    model.result().numerical("gev4").setResult();
    model.result("pg1").run();

    model.title("\u4ecb\u8d28\u5e73\u677f\u6ce2\u5bfc");

    model
         .description("\u4e00\u4e2a\u5e73\u9762\u4ecb\u8d28\u677f\u6ce2\u5bfc\u6f14\u793a\u4ecb\u8d28\u6ce2\u5bfc\uff08\u5982\u810a\u5f62\u6ce2\u5bfc\u548c\u9636\u8dc3\u6298\u5c04\u7387\u5149\u7ea4\uff09\u7684\u539f\u7406\u3002\u672c\u4f8b\u6c42\u89e3\u4ecb\u8d28\u5e73\u677f\u6ce2\u5bfc\u4e2d\u7684\u7535\u573a\u548c\u78c1\u573a\u3002\u7136\u540e\u8ba1\u7b97\u4e86\u6709\u6548\u6298\u5c04\u7387\uff0c\u5e76\u5c06\u7ed3\u679c\u4e0e\u89e3\u6790\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.label("dielectric_slab_waveguide.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
