/*
 * acousto_optic_modulator.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:39 by COMSOL 6.3.0.290. */
public class acousto_optic_modulator {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Modulators_and_Switches");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics().create("ewfd", "ElectromagneticWavesFrequencyDomain", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").set("ftplistmethod", "manual");
    model.study("std1").feature("freq").set("solnum", "auto");
    model.study("std1").feature("freq").set("notsolnum", "auto");
    model.study("std1").feature("freq").set("outputmap", new String[]{});
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").setSolveFor("/physics/solid", true);
    model.study("std1").feature("freq").setSolveFor("/physics/ewfd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("speed", "6.17e3[m/s]", "\u56fa\u4f53\u4e2d\u58f0\u901f");
    model.param().set("wl", "0.5[\u00b5m]", "\u58f0\u6ce2\u7684\u6ce2\u957f");
    model.param().set("freq_disp", "speed/wl", "\u58f0\u6ce2\u9891\u7387");
    model.param().set("na", "1", "\u6298\u5c04\u7387\uff0c\u7a7a\u6c14");
    model.param().set("N", "1.445", "\u6298\u5c04\u7387\uff0cSiO2");
    model.param().set("B1", "0.65e-12[m^2/N]", "\u7b2c\u4e00\u5e94\u529b\u5149\u5b66\u7cfb\u6570");
    model.param().set("B2", "4.2e-12[m^2/N]", "\u7b2c\u4e8c\u5e94\u529b\u5149\u5b66\u7cfb\u6570");
    model.param().set("lam0", "441[nm]", "\u771f\u7a7a\u4e2d\u7684\u6ce2\u957f");
    model.param().set("f0", "c_const/lam0", "\u5149\u5b66\u9891\u7387");
    model.param().set("alpha", "0", "\u5165\u5c04\u89d2");
    model.param().set("a", "0.5[um]", "\u5bbd\u5ea6");
    model.param().set("b", "3[um]", "\u9ad8\u5ea6");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"a", "b"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "b/3", 0);
    model.component("comp1").geom("geom1").feature("r1").set("layertop", true);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"1"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().set(2);
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", new String[]{"78[GPa]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", new String[]{"0.17"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"2203[kg/m^3]"});
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"N-(B1*solid.sx+B2*(solid.sy+solid.sz))", "N-(B1*solid.sy+B2*(solid.sx+solid.sz))", "N-(B1*solid.sz+B2*(solid.sx+solid.sy))"});
    model.component("comp1").material().duplicate("mat3", "mat2");
    model.component("comp1").material("mat3").selection().set(1);
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").set("n", new String[]{"N"});

    model.component("comp1").physics("solid").selection().set(2);
    model.component("comp1").physics("solid").create("disp1", "Displacement1", 1);
    model.component("comp1").physics("solid").feature("disp1").selection().set(9);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("solid").feature("disp1").setIndex("U0", "0.01[nm]", 0);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().set(3);
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 1);
    model.component("comp1").physics("solid").feature("sym1").selection().set(4, 6);
    model.component("comp1").physics("ewfd").prop("components").set("components", "outofplane");
    model.component("comp1").physics("ewfd").create("ps1", "PeriodicStructure", 2);
    model.component("comp1").physics("ewfd").feature("ps1").set("alpha_inc", "alpha");
    model.component("comp1").physics("ewfd").feature("ps1").feature("fpc1")
         .set("splitPeriodicConditionSelections", true);
    model.component("comp1").physics("ewfd").feature("ps1").feature("pport1").runCommand("addDiffractionOrders");

    model.component("comp1").mesh("mesh1").create("id1", "IdenticalMesh");
    model.component("comp1").mesh("mesh1").feature("id1").selection("group1").set(1, 3, 5);
    model.component("comp1").mesh("mesh1").feature("id1").selection("group2").set(8, 9, 10);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "lam0/N/10");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("freq").setSolveFor("/physics/ewfd", false);
    model.study("std1").feature("freq").set("punit", "GHz");
    model.study("std1").feature("freq").set("plist", "freq_disp");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.misesGp_peak"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("resolution", "normal");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").create("hght1", "Height");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("hght1").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("hght1").set("scale", 1.5E-17);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def").active(false);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "u");
    model.result("pg2").feature("surf1").set("unit", "nm");
    model.result("pg2").feature("surf1").set("colortable", "Dipole");
    model.result("pg2").feature("surf1").create("hght1", "Height");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature("hght1").set("scaleactive", true);
    model.result("pg2").feature("surf1").feature("hght1").set("scale", "2E-8");
    model.result("pg2").run();

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").set("plotgroup", "Default");
    model.study("std2").feature("freq").set("ftplistmethod", "manual");
    model.study("std2").feature("freq").set("solnum", "auto");
    model.study("std2").feature("freq").set("notsolnum", "auto");
    model.study("std2").feature("freq").set("outputmap", new String[]{});
    model.study("std2").feature("freq").set("ngenAUX", "1");
    model.study("std2").feature("freq").set("goalngenAUX", "1");
    model.study("std2").feature("freq").set("ngenAUX", "1");
    model.study("std2").feature("freq").set("goalngenAUX", "1");
    model.study("std2").feature("freq").setSolveFor("/physics/solid", false);
    model.study("std2").feature("freq").setSolveFor("/physics/ewfd", true);
    model.study("std2").feature("freq").set("plist", "f0");
    model.study("std2").feature("freq").set("usesol", true);
    model.study("std2").feature("freq").set("notsolmethod", "sol");
    model.study("std2").feature("freq").set("notstudy", "std1");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u7535\u573a (ewfd)");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "ewfd.normE");
    model.result("pg3").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1")
         .label("\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387 (ewfd)");
    model.result().numerical("gev1").set("data", "dset2");
    model.result().numerical("gev1")
         .set("expr", new String[]{"ewfd.Rorder_0", "ewfd.Rtotal", "ewfd.Torder_0", "ewfd.Torder_n1_op", "ewfd.Torder_p1_op", "ewfd.Ttotal", "ewfd.RTtotal", "ewfd.Atotal"});
    model.result().table().create("tbl1", "Table");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").run();
    model.result().numerical("gev1").setResult();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "ewfd.Ez");
    model.result("pg3").feature("surf1").set("colortable", "WaveLight");
    model.result("pg3").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").feature("surf1").create("hght1", "Height");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").feature("hght1").set("scaleactive", true);
    model.result("pg3").feature("surf1").feature("hght1").set("scale", 1.2E-11);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").run();
    model.result("pg4").set("data", "dset2");
    model.result("pg4").selection().geom("geom1", 2);
    model.result("pg4").selection().geom("geom1", 2);
    model.result("pg4").selection().set(1);
    model.result("pg4").set("applyselectiontodatasetedges", true);
    model.result("pg4").set("plotarrayenable", true);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("arraydim", "1");
    model.result("pg4").feature("surf1").set("expr", "ewfd.Emodez_2");
    model.result("pg4").feature("surf1").set("descr", "\u7535\u6a21\u573a\uff0c\u7aef\u53e3 2\uff0cz \u5206\u91cf");
    model.result("pg4").feature("surf1").set("colortable", "WaveLight");
    model.result("pg4").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg4").feature("surf1").set("manualindexing", true);
    model.result("pg4").feature("surf1").set("arrayindex", 1);
    model.result("pg4").feature("surf1").create("hght1", "Height");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("arraydim", "1");
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("surf2", "surf1");
    model.result("pg4").feature("surf2").set("arraydim", "1");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").set("expr", "ewfd.Emodez_3");
    model.result("pg4").feature("surf2").set("inheritplot", "surf1");
    model.result("pg4").feature("surf2").set("arrayindex", 0);
    model.result("pg4").feature().duplicate("surf3", "surf2");
    model.result("pg4").feature("surf3").set("arraydim", "1");
    model.result("pg4").run();
    model.result("pg4").feature("surf3").set("expr", "ewfd.Emodez_4");
    model.result("pg4").feature("surf3").set("arrayindex", 2);
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("arraydim", "1");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "ewfd.S21*ewfd.Emodez_2");
    model.result("pg4").feature("surf2").set("arraydim", "1");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").set("expr", "ewfd.S31*ewfd.Emodez_3");
    model.result("pg4").feature("surf3").set("arraydim", "1");
    model.result("pg4").run();
    model.result("pg4").feature("surf3").set("expr", "ewfd.S41*ewfd.Emodez_4");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").create("arwl1", "ArrowLine");
    model.result("pg4").feature("arwl1").set("arraydim", "1");
    model.result("pg4").feature("arwl1").set("expr", new String[]{"ewfd.kModex_3", "ewfd.kModey_3"});
    model.result("pg4").feature("arwl1")
         .set("descr", "\u7aef\u53e3\u6a21\u5f0f\u6ce2\u77e2\uff0c\u7aef\u53e3\u201c3\u201d");
    model.result("pg4").feature("arwl1").set("arrowcount", 15);
    model.result("pg4").feature("arwl1").set("color", "magenta");
    model.result("pg4").feature("arwl1").set("manualindexing", true);
    model.result("pg4").feature("arwl1").create("sel1", "Selection");
    model.result("pg4").feature("arwl1").feature("sel1").selection().set(1, 2);
    model.result("pg4").feature("arwl1").set("arraydim", "1");
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("arwl2", "arwl1");
    model.result("pg4").feature("arwl2").set("arraydim", "1");
    model.result("pg4").run();
    model.result("pg4").feature("arwl2").setIndex("expr", "ewfd.kModex_4", 0);
    model.result("pg4").feature("arwl2").setIndex("expr", "ewfd.kModey_4", 1);
    model.result("pg4").feature("arwl2").set("inheritplot", "arwl1");
    model.result("pg4").feature("arwl2").set("arrayindex", 2);
    model.result("pg4").run();
    model.result("pg4").feature("arwl2").feature("sel1").selection().set(2, 8);
    model.result("pg4").feature("arwl1").set("arraydim", "1");
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("arwl3", "arwl1");
    model.result("pg4").feature("arwl3").set("arraydim", "1");
    model.result("pg4").run();
    model.result("pg4").feature("arwl3").setIndex("expr", "ewfd.kModex_2", 0);
    model.result("pg4").feature("arwl3").setIndex("expr", "ewfd.kModey_2", 1);
    model.result("pg4").feature("arwl3").set("inheritplot", "arwl1");
    model.result("pg4").feature("arwl3").set("arrowcount", 5);
    model.result("pg4").feature("arwl3").set("arrayindex", 1);
    model.result("pg4").run();
    model.result("pg4").feature("arwl3").feature("sel1").selection().set(2);
    model.result("pg4").feature("arwl1").set("arraydim", "1");
    model.result("pg4").run();
    model.result("pg4").feature("arwl1").set("scale", 5.000000000000001E-15);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("plotgroup", "pg4");
    model.result().export("anim1").set("sweeptype", "dde");
    model.result().export("anim1").set("repeat", "iterations");
    model.result().export("anim1").set("iterations", 5);
    model.result().export("anim1").run();

    model.title("\u58f0\u5149\u8c03\u5236\u5668");

    model
         .description("\u58f0\u5149\u8c03\u5236\u5668 (AOM) \u662f\u4e00\u79cd\u80fd\u591f\u5229\u7528\u7535\u9a71\u52a8\u4fe1\u53f7\u6765\u63a7\u5236\u6fc0\u5149\u675f\u7684\u529f\u7387\u3001\u9891\u7387\u6216\u7a7a\u95f4\u65b9\u5411\u7684\u8bbe\u5907\u3002\u8fd9\u4e00\u8fc7\u7a0b\u57fa\u4e8e\u58f0-\u5149\u6548\u5e94\uff0c\u5373\u5229\u7528\u58f0\u6ce2\u7684\u632f\u8361\u673a\u68b0\u538b\u529b\u4fee\u6539\u6298\u5c04\u7387\u3002\n\nAOM \u7684\u5173\u952e\u5143\u4ef6\u662f\u4e00\u4e2a\u900f\u660e\u6676\u4f53\uff08\u6216\u73bb\u7483\u7247\uff09\uff0c\u5149\u7ebf\u5728\u5176\u4e2d\u4f20\u64ad\u3002\u6676\u4f53\u4e0a\u9644\u52a0\u7684\u538b\u7535\u6362\u80fd\u5668\u7528\u4e8e\u6fc0\u53d1\u9891\u7387\u7ea6\u4e3a 100 MHz \u7684\u58f0\u6ce2\u3002\u968f\u540e\uff0c\u5149\u53ef\u4ee5\u5728\u58f0\u6ce2\u4ea7\u751f\u7684\u79fb\u52a8\u5468\u671f\u6027\u6298\u5c04\u7387\u5149\u6805\u5904\u53d1\u751f\u5e03\u62c9\u683c\u884d\u5c04\uff1b\u56e0\u6b64\uff0cAOM \u6709\u65f6\u4e5f\u79f0\u4e3a\u5e03\u62c9\u683c\u5355\u5143\u3002\n\n\u672c\u4f8b\u6f14\u793a\u5728\u4ec5\u4f7f\u7528\u5c11\u6570\u6298\u5c04\u7387\u5468\u671f\u7684\u60c5\u51b5\u4e0b\uff0c\u5982\u4f55\u6b63\u786e\u5e94\u7528\u7269\u7406\u539f\u7406\u3002\u800c\u5b9e\u9645\u5236\u9020\u7684\u7ec4\u4ef6\u8981\u5927\u5f97\u591a\uff0c\u4f46\u540c\u6837\u53ef\u4ee5\u91c7\u7528\u4e0e\u201c\u5c04\u7ebf\u5149\u5b66\u201d\u201c\u6848\u4f8b\u5e93\u201d\u793a\u4f8b\u201c\u884d\u5c04\u5149\u6805\u201d\u76f8\u540c\u7684\u539f\u7406\u8fdb\u884c\u5efa\u6a21\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("acousto_optic_modulator.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
