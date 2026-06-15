/*
 * single_bit_hologram.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:39 by COMSOL 6.3.0.290. */
public class single_bit_hologram {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Gratings_and_Metamaterials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ewfd", "ElectromagneticWavesFrequencyDomain", "geom1");

    model.study().create("std1");
    model.study("std1").create("wave", "Wavelength");
    model.study("std1").feature("wave").set("ftplistmethod", "manual");
    model.study("std1").feature("wave").set("solnum", "auto");
    model.study("std1").feature("wave").set("notsolnum", "auto");
    model.study("std1").feature("wave").set("outputmap", new String[]{});
    model.study("std1").feature("wave").set("ngenAUX", "1");
    model.study("std1").feature("wave").set("goalngenAUX", "1");
    model.study("std1").feature("wave").set("ngenAUX", "1");
    model.study("std1").feature("wave").set("goalngenAUX", "1");
    model.study("std1").feature("wave").setSolveFor("/physics/ewfd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("wl0", "1[um]", "\u6ce2\u957f");
    model.param().set("k0", "2*pi/wl0", "\u6ce2\u6570");
    model.param().set("L", "30[um]", "\u7a7a\u6c14\u57df\u5927\u5c0f");
    model.param().set("H", "30[um]", "\u7a7a\u6c14\u57df\u9ad8\u5ea6");
    model.param().set("L_h", "30[um]", "\u5168\u606f\u56fe\u6750\u6599\u5927\u5c0f");
    model.param().set("n1", "1.35", "\u5168\u606f\u56fe\u6750\u6599\u6298\u5c04\u7387");
    model.param().set("dn", "0.01", "\u8c03\u5236\u7cfb\u6570");
    model.param().set("a", "10[um]", "\u5168\u606f\u56fe\u534a\u957f");
    model.param().set("w_o", "10[um]", "\u7269\u4f53\u5149\u675f\u7684 1/e^2 \u5f3a\u5ea6\u675f\u8170\u534a\u5f84");
    model.param().set("w_r", "10[um]", "\u53c2\u8003\u5149\u675f\u7684 1/e^2 \u5f3a\u5ea6\u675f\u8170\u534a\u5f84");
    model.param().set("threshold", ".4", "\u66dd\u5149\u9608\u503c");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"L", "H"});
    model.component("comp1").geom("geom1").feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"n1"});

    model.component("comp1").cpl().create("maxop1", "Maximum");
    model.component("comp1").cpl("maxop1").selection().set(1);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("\u8bb0\u5f55\u9636\u6bb5\u7684\u6298\u5c04\u7387");
    model.component("comp1").variable("var1").set("n", "n1");
    model.component("comp1").variable("var1").descr("n", "\u80cc\u666f\u6298\u5c04\u7387");
    model.component("comp1").variable().duplicate("var2", "var1");
    model.component("comp1").variable("var2").label("\u8c03\u5236\u7684\u6298\u5c04\u7387");
    model.component("comp1").variable("var2").rename("n", "n_mod");
    model.component("comp1").variable("var2")
         .set("n_mod", "n1+dn*(((ewfd.normE/maxop1(ewfd.normE))^2>threshold)-0.5)");
    model.component("comp1").variable("var2").descr("n_mod", "\u8c03\u5236\u7684\u6298\u5c04\u7387");
    model.component("comp1").variable().create("var3");
    model.component("comp1").variable("var3").label("\u68c0\u7d22\u9636\u6bb5\u7684\u6298\u5c04\u7387");
    model.component("comp1").variable("var3")
         .set("n", "withsol('sol2',n1+dn*(((ewfd.normE/maxop1(ewfd.normE))^2>threshold)-0.5))", "\u80cc\u666f\u6298\u5c04\u7387");

    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"n"});

    model.component("comp1").physics("ewfd").prop("components").set("components", "outofplane");
    model.component("comp1").physics("ewfd").create("sctr1", "Scattering", 1);
    model.component("comp1").physics("ewfd").feature("sctr1")
         .label("\u53c2\u8003\u6563\u5c04\u8fb9\u754c\u6761\u4ef6");
    model.component("comp1").physics("ewfd").feature("sctr1").selection().set(1);
    model.component("comp1").physics("ewfd").feature("sctr1").set("IncidentField", "EField");
    model.component("comp1").physics("ewfd").feature("sctr1").set("E0i", new String[]{"0", "0", "exp(-(y/w_r)^6)"});
    model.component("comp1").physics("ewfd").create("sctr2", "Scattering", 1);
    model.component("comp1").physics("ewfd").feature("sctr2")
         .label("\u7269\u4f53\u6563\u5c04\u8fb9\u754c\u6761\u4ef6");
    model.component("comp1").physics("ewfd").feature("sctr2").selection().set(3);
    model.component("comp1").physics("ewfd").feature("sctr2").set("IncidentField", "EField");
    model.component("comp1").physics("ewfd").feature("sctr2").set("E0i", new String[]{"0", "0", "exp(-x^6/w_o^6)"});
    model.component("comp1").physics("ewfd").create("sctr3", "Scattering", 1);
    model.component("comp1").physics("ewfd").feature("sctr3")
         .label("\u8bb0\u5f55\u6563\u5c04\u8fb9\u754c\u6761\u4ef6");
    model.component("comp1").physics("ewfd").feature("sctr3").selection().set(2, 4);
    model.component("comp1").physics("ewfd").feature().duplicate("sctr4", "sctr3");
    model.component("comp1").physics("ewfd").feature("sctr4")
         .label("\u68c0\u7d22\u6563\u5c04\u8fb9\u754c\u6761\u4ef6");
    model.component("comp1").physics("ewfd").feature("sctr4").selection().set(2, 3, 4);

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "wl0/n1/10");

    model.study("std1").feature("wave").label("\u8bb0\u5f55");
    model.study("std1").feature("wave").set("plist", "wl0");
    model.study("std1").feature("wave").set("useadvanceddisable", true);
    model.study("std1").feature("wave").set("disabledvariables", new String[]{"var3"});
    model.study("std1").feature("wave").set("disabledphysics", new String[]{"ewfd/sctr4"});
    model.study("std1").feature().duplicate("wave1", "wave");
    model.study("std1").feature("wave1").label("\u68c0\u7d22");
    model.study("std1").feature("wave1").set("disabledvariables", new String[]{"var1"});
    model.study("std1").feature("wave1").set("disabledphysics", new String[]{"ewfd/sctr3"});
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").create("su1", "StoreSolution");
    model.sol("sol1").feature().move("su1", 5);
    model.sol("sol1").feature().move("su1", 4);
    model.sol("sol1").feature().move("su1", 3);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u573a (ewfd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").set("data", "dset2");
    model.result("pg1").label("\u8bb0\u5f55");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "ewfd.Ez");
    model.result("pg1").feature("surf1").set("colorlegend", false);
    model.result("pg1").feature().duplicate("surf2", "surf1");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").set("expr", "ewfd.normE^2");
    model.result("pg1").feature("surf2").create("trn1", "Transformation");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").feature("trn1").set("move", new int[]{34, 0});
    model.result("pg1").run();
    model.result("pg1").set("titletype", "manual");
    model.result("pg1")
         .set("title", "\u5de6\uff1a\u7535\u573a\uff0cz \u5206\u91cf (V/m)\uff0c\u53f3\uff1a\u7535\u573a\u6a21\u7684\u5e73\u65b9 (V<sup>2</sup>/m<sup>2</sup>)");
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature().remove("surf2");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").label("\u8c03\u5236\u7684\u6298\u5c04\u7387");
    model.result("pg2").set("title", "\u8c03\u5236\u7684\u6298\u5c04\u7387");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "n_mod");
    model.result("pg2").feature("surf1").set("descr", "\u8c03\u5236\u7684\u6298\u5c04\u7387");
    model.result("pg2").feature("surf1").set("colorlegend", true);
    model.result("pg2").run();
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").set("data", "dset2");
    model.result().dataset("cln1").setIndex("genpoints", "-L/2", 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", "-H/2", 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", "L/2", 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", "H/2", 1, 1);
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u7535\u573a\u6a21\u7684\u5e73\u65b9");
    model.result("pg3").set("data", "cln1");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr1").set("linewidth", "preference");
    model.result("pg3").feature("lngr1").set("expr", "ewfd.normE^2");
    model.result("pg3").feature("lngr1").set("legend", true);
    model.result("pg3").feature("lngr1").set("legendmethod", "manual");
    model.result("pg3").feature("lngr1").setIndex("legends", "ewfd.normE<sup>2</sup>", 0);
    model.result("pg3").feature().duplicate("lngr2", "lngr1");
    model.result("pg3").run();
    model.result("pg3").feature("lngr2").set("expr", "threshold*maxop1(ewfd.normE^2)");
    model.result("pg3").feature("lngr2").set("linecolor", "red");
    model.result("pg3").feature("lngr2").set("linewidth", 4);
    model.result("pg3").feature("lngr2").setIndex("legends", "threshold*maxop1(ewfd.normE<sup>2</sup>)", 0);
    model.result("pg3").run();
    model.result("pg3").set("titletype", "manual");
    model.result("pg3")
         .set("title", "\u7535\u573a\u6a21\u7684\u5e73\u65b9 (V<sup>2</sup>/m<sup>2</sup>) \u548c\u66dd\u5149\u9608\u503c");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u7535\u573a\u6a21\u7684\u5e73\u65b9 (V<sup>2</sup>/m<sup>2</sup>)");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u8c03\u5236\u6298\u5c04\u7387\u7ebf\u56fe");
    model.result("pg4").set("data", "cln1");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").set("expr", "n_mod");
    model.result("pg4").feature("lngr1").set("descr", "\u8c03\u5236\u7684\u6298\u5c04\u7387");
    model.result("pg4").run();
    model.result("pg4").set("axislimits", true);
    model.result("pg4").set("ymin", 1);
    model.result("pg4").set("ymax", 1.5);
    model.result("pg4").run();
    model.result("pg1").run();
    model.result().duplicate("pg5", "pg1");
    model.result("pg5").run();
    model.result("pg5").label("\u68c0\u7d22\uff08\u4ec5\u53c2\u8003\u5149\u675f\uff09");
    model.result("pg5").set("data", "dset1");
    model.result("pg5")
         .set("title", "\u5de6\uff1a\u7535\u573a\uff0cz \u5206\u91cf (V/m)\uff0c\u53f3\uff1a\u7535\u573a\u6a21 (V/m)");
    model.result("pg5").run();
    model.result("pg5").feature("surf2").set("expr", "ewfd.normE");
    model.result("pg5").run();

    model.title("\u5355\u6bd4\u7279\u5168\u606f\u56fe");

    model
         .description("\u672c\u6a21\u578b\u6a21\u62df\u5305\u542b\u6570\u636e\u8bb0\u5f55\u548c\u68c0\u7d22\u7684\u9010\u4f4d\u5168\u606f\u6570\u636e\u5b58\u50a8\uff0c\u5176\u4e2d\u7684\u6570\u636e\u4e3a 1\u00a0\u6bd4\u7279\u3002\u56e0\u6b64\uff0c\u6a21\u578b\u4e2d\u7684\u5bf9\u8c61\u6fc0\u5149\u675f\u4e0e\u53c2\u8003\u6fc0\u5149\u675f\u7684\u5256\u9762\u76f8\u540c\u3002\n\n\u5728\u8bb0\u5f55\u8fc7\u7a0b\u4e2d\uff0c\u4e24\u4e2a\u6fc0\u5149\u675f\u5f7c\u6b64\u76f8\u4ea4\uff0c\u5f62\u6210\u5e72\u6d89\u6761\u7eb9\u56fe\u6837\uff0c\u5373\u5305\u542b 1\u00a0\u6bd4\u7279\u6570\u636e\u7684\u5168\u606f\u56fe\u3002\n\n\u5728\u68c0\u7d22\u8fc7\u7a0b\u4e2d\uff0c\u5bf9\u8c61\u6fc0\u5149\u675f\u5173\u95ed\uff0c\u968f\u540e\u53c2\u8003\u6fc0\u5149\u675f\u901a\u8fc7\u5168\u606f\u56fe\u53d1\u751f\u6298\u5c04\uff0c\u5e76\u521b\u5efa\u5bf9\u8c61\u6fc0\u5149\u675f\u3002\n\n\u6b64\u6a21\u578b\u901a\u8fc7\u201c\u7535\u78c1\u6ce2\uff0c\u9891\u57df\u201d\u63a5\u53e3\u5b9e\u73b0\u3002\u53ef\u5728\u4e00\u4e2a\u7814\u7a76\u5e8f\u5217\u4e2d\u5904\u7406\u8bb0\u5f55\u548c\u68c0\u7d22\u4e24\u4e2a\u6b65\u9aa4\uff0c\u65b9\u6cd5\u662f\u4fee\u6539\u8fd9\u4e24\u4e2a\u7814\u7a76\u6b65\u9aa4\u7684\u7269\u7406\u573a\u6811\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("single_bit_hologram.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
