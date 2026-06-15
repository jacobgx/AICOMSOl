/*
 * microstructured_optical_fiber.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:43 by COMSOL 6.3.0.290. */
public class microstructured_optical_fiber {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ewfd", "ElectromagneticWavesFrequencyDomain", "geom1");

    model.study().create("std1");
    model.study("std1").create("mode", "ModeAnalysis");
    model.study("std1").feature("mode").set("ftplistmethod", "manual");
    model.study("std1").feature("mode").set("shiftactive", false);
    model.study("std1").feature("mode").set("linpsolnum", "auto");
    model.study("std1").feature("mode").set("outputmap", new String[]{});
    model.study("std1").feature("mode").set("ngenAUX", "1");
    model.study("std1").feature("mode").set("goalngenAUX", "1");
    model.study("std1").feature("mode").set("ngenAUX", "1");
    model.study("std1").feature("mode").set("goalngenAUX", "1");
    model.study("std1").feature("mode").setSolveFor("/physics/ewfd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("d", "5[um]", "\u5b54\u5f84");
    model.param().set("Delta", "6.75[um]", "\u5b54\u5206\u79bb");
    model.param().set("rb", "10[um]", "\u8ba1\u7b97\u57df\u7684\u534a\u5f84");
    model.param().set("nbg", "1.45", "\u80cc\u666f\u6298\u5c04\u7387");
    model.param().set("nair", "1", "\u7a7a\u6c14\u7684\u6298\u5c04\u7387");
    model.param().set("wl", "1.45[um]", "\u6ce2\u957f");
    model.param().set("neffMax", "1.4454", "\u9884\u8ba1\u7684\u6700\u5927\u6709\u6548\u6298\u5c04\u7387,");
    model.param().set("neffMin", "1.429", "\u9884\u8ba1\u7684\u6700\u5c0f\u6709\u6548\u6298\u5c04\u7387,");
    model.param().set("wlr", "wl/sqrt(nbg^2-neffMax^2)", "\u5f84\u5411\u6ce2\u957f");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "rb+wlr");
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "wlr", 0);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("c2").set("r", "d/2");
    model.component("comp1").geom("geom1").feature("c2").set("pos", new String[]{"0", "-Delta"});
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u7d2f\u79ef\u9009\u62e9 1");
    model.component("comp1").geom("geom1").feature("c2").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("c2");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", "range(0,60,300)");
    model.component("comp1").geom("geom1").feature("rot1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").selection().create("adj1", "Adjacent");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("adj1").label("\u6c14\u5b54\u8fb9\u754c");
    model.component("comp1").selection("adj1").set("input", new String[]{"geom1_csel1_dom"});

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u4e8c\u6c27\u5316\u7845");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"nbg"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u7a7a\u6c14");
    model.component("comp1").material("mat2").selection().named("geom1_csel1_dom");
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").set("n", new String[]{"nair"});

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().set(1, 2, 3, 4);
    model.component("comp1").coordSystem("pml1").set("ScalingType", "Cylindrical");
    model.component("comp1").coordSystem("pml1").set("wavelengthSourceType", "userDefined");
    model.component("comp1").coordSystem("pml1").set("typicalWavelength", "wlr");

    model.study("std1").feature("mode").set("modeFreq", "c_const/wl");
    model.study("std1").feature("mode").set("eigmethod", "region");
    model.study("std1").feature("mode").set("appnreigs", 10);
    model.study("std1").feature("mode").set("eigsr", "neffMin");
    model.study("std1").feature("mode").set("eiglr", "neffMax");
    model.study("std1").feature("mode").set("eigsi", "-3e-5");

    model.component("comp1").mesh("mesh1").autoMeshSize(1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u573a (ewfd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").set("showlegends", false);
    model.result("pg1").run();

    model.component("comp1").view("view1").set("showgrid", false);

    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("expr", new String[]{"ewfd.neff"});
    model.result().numerical("gev1").set("descr", new String[]{"\u6709\u6548\u6a21\u5f0f\u6298\u5c04\u7387"});
    model.result().numerical("gev1").set("unit", new String[]{"1"});
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();

    model.component("comp1").physics("ewfd").selection().set(5, 6, 7, 8, 9, 10, 11);
    model.component("comp1").physics("ewfd").create("sctr1", "Scattering", 1);
    model.component("comp1").physics("ewfd").feature("sctr1").selection().all();
    model.component("comp1").physics("ewfd").feature("sctr1").set("WaveType", "CylindricalWave");
    model.component("comp1").physics("ewfd").feature("sctr1").set("UseReducedWaveNumber", true);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").appendResult();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "sqrt(abs(ewfd.Ex)^2+abs(ewfd.Ey)^2)");
    model.result("pg1").feature().duplicate("surf2", "surf1");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").set("expr", "10*abs(ewfd.Ez)");
    model.result("pg1").feature("surf2").set("inheritplot", "surf1");
    model.result("pg1").feature("surf2").create("trn1", "Transformation");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").feature("trn1").set("move", new String[]{"0", "-2.1*rb"});
    model.result("pg1").feature("surf2").feature("trn1").set("applytodatasetedges", false);
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("surf3", "surf1");
    model.result("pg1").feature().duplicate("surf4", "surf2");
    model.result("pg1").run();
    model.result("pg1").feature("surf3").set("expr", "sqrt(abs(ewfd.Hx)^2+abs(ewfd.Hy)^2)");
    model.result("pg1").feature("surf3").create("trn1", "Transformation");
    model.result("pg1").run();
    model.result("pg1").feature("surf3").feature("trn1").set("move", new String[]{"2.1*rb", "0"});
    model.result("pg1").feature("surf3").feature("trn1").set("applytodatasetedges", false);
    model.result("pg1").run();
    model.result("pg1").feature("surf4").set("expr", "10*abs(ewfd.Hz)");
    model.result("pg1").feature("surf4").set("inheritplot", "surf3");
    model.result("pg1").run();
    model.result("pg1").feature("surf4").feature("trn1").set("move", new String[]{"2.1*rb", "-2.1*rb"});
    model.result("pg1").run();
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u5207\u5411\u548c\u7eb5\u5411\u7684\u7535\u573a\u548c\u78c1\u573a");
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").set("color", "white");
    model.result("pg1").feature("arws1").create("trn1", "Transformation");
    model.result("pg1").run();
    model.result("pg1").feature("arws1").feature("trn1").set("move", new String[]{"2.1*rb", "0"});
    model.result("pg1").feature("arws1").feature("trn1").set("applytodatasetedges", false);
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("arws2", "arws1");
    model.result("pg1").run();
    model.result("pg1").feature("arws2").set("expr", new String[]{"ewfd.Ex", "ewfd.Ey"});
    model.result("pg1").feature("arws2").set("descr", "\u7535\u573a");
    model.result("pg1").run();
    model.result("pg1").feature("arws2").feature().remove("trn1");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").set("phasetype", "manual");
    model.result("pg1").set("phase", 45);
    model.result("pg1").set("edges", false);
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("coloring", "uniform");
    model.result("pg1").feature("line1").set("color", "black");
    model.result("pg1").feature("line1").create("sel1", "Selection");
    model.result("pg1").feature("line1").feature("sel1").selection().named("adj1");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("line2", "line1");
    model.result("pg1").run();
    model.result("pg1").feature("line2").create("trn1", "Transformation");
    model.result("pg1").run();
    model.result("pg1").feature("line2").feature("trn1").set("move", new String[]{"2.1*rb", "0"});
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("line3", "line1");
    model.result("pg1").feature().duplicate("line4", "line2");
    model.result("pg1").run();
    model.result("pg1").feature("line3").create("trn1", "Transformation");
    model.result("pg1").run();
    model.result("pg1").feature("line3").feature("trn1").set("move", new String[]{"0", "-2.1*rb"});
    model.result("pg1").run();
    model.result("pg1").feature("line4").feature("trn1").set("move", new String[]{"2.1*rb", "-2.1*rb"});
    model.result("pg1").run();
    model.result("pg1").create("ann1", "Annotation");
    model.result("pg1").feature("ann1").set("text", "\u7535\u573a");
    model.result("pg1").feature("ann1").set("posyexpr", "1.2*rb");
    model.result("pg1").feature("ann1").set("showpoint", false);
    model.result("pg1").feature("ann1").set("anchorpoint", "center");
    model.result("pg1").feature().duplicate("ann2", "ann1");
    model.result("pg1").run();
    model.result("pg1").feature("ann2").set("text", "\u78c1\u573a");
    model.result("pg1").feature("ann2").set("posxexpr", "2.1*rb");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("ann3", "ann1");
    model.result("pg1").run();
    model.result("pg1").feature("ann3").set("text", "\u5207\u5411");
    model.result("pg1").feature("ann3").set("posxexpr", "-1.1*rb");
    model.result("pg1").feature("ann3").set("posyexpr", 0);
    model.result("pg1").feature("ann3").set("anchorpoint", "middleright");
    model.result("pg1").feature().duplicate("ann4", "ann3");
    model.result("pg1").run();
    model.result("pg1").feature("ann4").set("text", "\u7eb5\u5411");
    model.result("pg1").feature("ann4").set("posyexpr", "-2.1*rb");
    model.result("pg1").run();
    model.result("pg1").set("paramindicator", "Effective mode index=eval(ewfd.neff)");
    model.result("pg1").stepLast(0);
    model.result("pg1").run();
    model.result("pg1").stepPrevious(0);
    model.result("pg1").run();
    model.result("pg1").stepPrevious(0);
    model.result("pg1").run();
    model.result("pg1").stepPrevious(0);
    model.result("pg1").run();
    model.result("pg1").stepPrevious(0);
    model.result("pg1").run();
    model.result("pg1").stepPrevious(0);
    model.result("pg1").run();
    model.result("pg1").stepPrevious(0);
    model.result("pg1").run();
    model.result("pg1").stepPrevious(0);
    model.result("pg1").run();
    model.result("pg1").stepPrevious(0);
    model.result("pg1").run();
    model.result("pg1").stepPrevious(0);
    model.result("pg1").run();

    model.title("\u5fae\u7ed3\u6784\u5149\u7ea4\u4e2d\u7684\u6f0f\u6a21");

    model
         .description("\u672c\u6a21\u578b\u4f7f\u7528\u6a21\u6001\u5206\u6790\u7814\u7a76\u6765\u786e\u5b9a\u5fae\u7ed3\u6784\u5149\u7ea4 (MOF) \u7684\u590d\u5408\u6709\u6548\u6298\u5c04\u7387\uff0c\u8fd9\u79cd\u5fae\u7ed3\u6784\u5149\u7ea4\u7531\u4e8c\u6c27\u5316\u7845\u57fa\u4f53\u4e2d\u7684\u6c14\u5b54\u7ec4\u6210\u3002\u7531\u4e8e\u6709\u6548\u6298\u5c04\u7387\u5c0f\u4e8e\u4e8c\u6c27\u5316\u7845\u80cc\u666f\u6750\u6599\u7684\u6298\u5c04\u7387\uff0c\u4ece\u800c\u5bfc\u81f4\u6a21\u6cc4\u6f0f\u3002\n\n\u8be5\u6a21\u578b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u5b8c\u7f8e\u5339\u914d\u5c42 (PML) \u548c\u6563\u5c04\u8fb9\u754c\u6761\u4ef6\u6765\u622a\u65ad\u4eff\u771f\u57df\u3002\n\n\u6709\u6548\u6298\u5c04\u7387\u7684\u5b9e\u90e8\u548c\u865a\u90e8\u4e0e\u5df2\u53d1\u8868\u8bba\u6587\u4e2d\u7684\u503c\u543b\u5408\u826f\u597d\u3002");

    model.label("microstructured_optical_fiber.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
