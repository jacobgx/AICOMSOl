/*
 * step_index_fiber_bend.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:44 by COMSOL 6.3.0.290. */
public class step_index_fiber_bend {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Waveguides");

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

    model.param().set("lda0", "1.55[um]");
    model.param().descr("lda0", "\u6ce2\u957f");
    model.param().set("f0", "c_const/lda0");
    model.param().descr("f0", "\u9891\u7387");
    model.param().set("nClad", "1.4378");
    model.param().descr("nClad", "\u6298\u5c04\u7387\uff0c\u5305\u5c42");
    model.param().set("nCore", "1.4457");
    model.param().descr("nCore", "\u6298\u5c04\u7387\uff0c\u7ea4\u82af");
    model.param().set("aCore", "8[um]");
    model.param().descr("aCore", "\u7ea4\u82af\u534a\u5f84");
    model.param().set("aClad", "40[um]");
    model.param().descr("aClad", "\u5305\u5c42\u534a\u5f84");
    model.param().set("Rb", "3[mm]");
    model.param().descr("Rb", "\u5f2f\u66f2\u534a\u5f84");
    model.param().set("aSquare", "100[um]");
    model.param().descr("aSquare", "\u6b63\u65b9\u5f62\u8fb9\u957f");
    model.param().set("tPML", "20[um]");
    model.param().descr("tPML", "PML \u539a\u5ea6");
    model.param().set("dr", "aSquare/2-tPML");
    model.param().descr("dr", "\u7ea4\u82af\u4e2d\u5fc3\u5230 PML \u8fb9\u754c\u7684\u8ddd\u79bb");
    model.param().set("ldaPML", "lda0/sqrt(nClad^2-(nCore*Rb/(Rb+dr))^2)");
    model.param().descr("ldaPML", "PML \u4e2d\u7684\u5f84\u5411\u6ce2\u957f");

    model.material().create("mat1", "Common", "");
    model.material("mat1").label("\u77f3\u82f1\u73bb\u7483");
    model.material().create("mat2", "Common", "");
    model.material("mat2").label("\u63ba\u6742\u7684\u77f3\u82f1\u73bb\u7483");

    model.component("comp1").label("\u76f4\u5149\u7ea4");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "aClad");
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("c2").set("r", "aCore");
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("matlnk1", "Link");
    model.component("comp1").material("matlnk1").label("\u5305\u5c42");
    model.component("comp1").material().create("matlnk2", "Link");
    model.component("comp1").material("matlnk2").label("\u7ea4\u82af");
    model.component("comp1").material("matlnk2").set("link", "mat2");
    model.component("comp1").material("matlnk2").selection().set(2);
    model.material("mat1").propertyGroup().create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"nClad"});
    model.material("mat2").propertyGroup().create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.material("mat2").propertyGroup("RefractiveIndex").set("n", new String[]{"nCore"});

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").selection().geom("geom1", 2);
    model.component("comp1").variable("var1").selection().all();
    model.component("comp1").variable("var1")
         .set("normEt", "(ewfd.Ex*ewfd.Ex+ewfd.Ey*ewfd.Ey)/sqrt(ewfd.Ex*ewfd.Ex+ewfd.Ey*ewfd.Ey)");
    model.component("comp1").variable("var1").descr("normEt", "\u6a2a\u5411\u7535\u573a\u6a21");

    model.component("comp1").mesh("mesh1").autoMeshSize(3);

    model.study("std1").feature("mode").set("shiftactive", true);
    model.study("std1").feature("mode").set("shift", "nCore");
    model.study("std1").feature("mode").set("modeFreq", "f0");
    model.study("std1").label("\u7814\u7a76 1\uff08\u76f4\u5149\u7ea4\uff09");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u573a (ewfd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{6});
    model.result("pg1").set("phasetype", "manual");
    model.result("pg1").set("phase", 45);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "normEt");
    model.result("pg1").feature("surf1").set("descr", "\u6a2a\u5411\u7535\u573a\u6a21");
    model.result("pg1").run();
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").set("expr", new String[]{"ewfd.Ex", "ewfd.Ey"});
    model.result("pg1").feature("arws1").set("descr", "\u7535\u573a");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "ewfd.Ez");
    model.result("pg1").feature("surf1").set("descr", "\u7535\u573a\uff0cz \u5206\u91cf");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("con1", "Contour");
    model.result("pg1").feature("con1").set("expr", "ewfd.Hz");
    model.result("pg1").feature("con1").set("descr", "\u78c1\u573a\uff0cz \u5206\u91cf");
    model.result("pg1").run();
    model.result().dataset("dset1").label("\u89e3 1\uff08\u76f4\u5149\u7ea4\uff09");
    model.result("pg1").run();
    model.result("pg1").label("\u76f4\u5149\u7ea4");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 2);
    model.component("comp2").geom("geom2").axisymmetric(true);

    model.component("comp2").mesh().create("mesh2");

    model.component("comp2").label("\u5f2f\u66f2\u5149\u7ea4");

    model.component("comp2").geom("geom2").create("c1", "Circle");
    model.component("comp2").geom("geom2").feature("c1").set("r", "aCore");
    model.component("comp2").geom("geom2").feature("c1").set("pos", new String[]{"Rb", "0"});
    model.component("comp2").geom("geom2").run("c1");
    model.component("comp2").geom("geom2").create("sq1", "Square");
    model.component("comp2").geom("geom2").feature("sq1").set("size", "aSquare");
    model.component("comp2").geom("geom2").feature("sq1").set("base", "center");
    model.component("comp2").geom("geom2").feature("sq1").set("pos", new String[]{"Rb", "0"});
    model.component("comp2").geom("geom2").run("sq1");
    model.component("comp2").geom("geom2").create("r1", "Rectangle");
    model.component("comp2").geom("geom2").feature("r1").set("size", new String[]{"aSquare", "tPML"});
    model.component("comp2").geom("geom2").feature("r1").set("pos", new String[]{"Rb-aSquare/2", "0"});
    model.component("comp2").geom("geom2").feature("r1").setIndex("pos", "aSquare/2-tPML", 1);
    model.component("comp2").geom("geom2").run("r1");
    model.component("comp2").geom("geom2").create("r2", "Rectangle");
    model.component("comp2").geom("geom2").feature("r2").set("size", new String[]{"tPML", "aSquare"});
    model.component("comp2").geom("geom2").feature("r2").set("pos", new String[]{"Rb+dr", "-aSquare/2"});
    model.component("comp2").geom("geom2").run("r2");
    model.component("comp2").geom("geom2").create("r3", "Rectangle");
    model.component("comp2").geom("geom2").feature("r3").set("size", new String[]{"aSquare", "tPML"});
    model.component("comp2").geom("geom2").feature("r3").set("pos", new String[]{"Rb-aSquare/2", "0"});
    model.component("comp2").geom("geom2").feature("r3").setIndex("pos", "-aSquare/2", 1);
    model.component("comp2").geom("geom2").runPre("fin");

    model.component("comp2").physics().create("ewfd2", "ElectromagneticWavesFrequencyDomain", "geom2");

    model.study("std1").feature("mode").setSolveFor("/physics/ewfd2", false);

    model.component("comp2").geom("geom2").run();

    model.component("comp2").physics("ewfd2").create("pmc1", "PerfectMagneticConductor", 1);
    model.component("comp2").physics("ewfd2").feature("pmc1").selection().set(1, 2, 3, 5, 7, 9, 14, 15, 16, 17);

    model.component("comp2").material().create("matlnk3", "Link");
    model.component("comp2").material("matlnk3").label("\u5305\u5c42");
    model.component("comp2").material().create("matlnk4", "Link");
    model.component("comp2").material("matlnk4").label("\u7ea4\u82af");
    model.component("comp2").material("matlnk4").selection().set(7);
    model.component("comp2").material("matlnk4").set("link", "mat2");

    model.component("comp2").coordSystem().create("pml1", "PML");
    model.component("comp2").coordSystem("pml1").selection().set(1, 3, 4, 5, 6);
    model.component("comp2").coordSystem("pml1").set("ScalingType", "Cylindrical");
    model.component("comp2").coordSystem("pml1").set("stretchingType", "rational");
    model.component("comp2").coordSystem("pml1").set("wavelengthSourceType", "userDefined");
    model.component("comp2").coordSystem("pml1").set("typicalWavelength", "ldaPML");

    model.component("comp2").cpl().create("intop1", "Integration");
    model.component("comp2").cpl("intop1").set("axisym", true);
    model.component("comp2").cpl("intop1").selection().set(2, 7);
    model.component("comp2").cpl("intop1").set("axisym", false);

    model.component("comp2").variable().create("var2");
    model.component("comp2").variable("var2").set("r0", "intop1(r*ewfd2.Poavphi)/intop1(ewfd2.Poavphi)");

    model.component("comp2").mesh("mesh2").contribute("physics/ewfd2", false);
    model.component("comp2").mesh("mesh2").automatic(false);
    model.component("comp2").mesh("mesh2").feature("size").set("hauto", 3);
    model.component("comp2").mesh("mesh2").feature("ftri1").selection().geom("geom2", 2);
    model.component("comp2").mesh("mesh2").feature("ftri1").selection().set(2, 7);
    model.component("comp2").mesh("mesh2").create("map1", "Map");
    model.component("comp2").mesh("mesh2").feature("map1").selection().geom("geom2", 2);
    model.component("comp2").mesh("mesh2").feature("map1").selection().set(1, 3, 4, 5, 6);
    model.component("comp2").mesh("mesh2").feature("map1").create("size1", "Size");
    model.component("comp2").mesh("mesh2").feature("map1").feature("size1").set("hauto", 1);
    model.component("comp2").mesh("mesh2").run();

    model.study().create("std2");
    model.study("std2").create("mode", "ModeAnalysis");
    model.study("std2").feature("mode").set("plotgroup", "Default");
    model.study("std2").feature("mode").set("ftplistmethod", "manual");
    model.study("std2").feature("mode").set("shiftactive", false);
    model.study("std2").feature("mode").set("linpsolnum", "auto");
    model.study("std2").feature("mode").set("solnum", "auto");
    model.study("std2").feature("mode").set("notsolnum", "auto");
    model.study("std2").feature("mode").set("outputmap", new String[]{});
    model.study("std2").feature("mode").set("ngenAUX", "1");
    model.study("std2").feature("mode").set("goalngenAUX", "1");
    model.study("std2").feature("mode").set("ngenAUX", "1");
    model.study("std2").feature("mode").set("goalngenAUX", "1");
    model.study("std2").feature("mode").setSolveFor("/physics/ewfd", false);
    model.study("std2").feature("mode").setSolveFor("/physics/ewfd2", true);
    model.study("std2").feature("mode").set("neigsactive", true);
    model.study("std2").feature("mode").set("neigs", 2);
    model.study("std2").feature("mode").set("shiftactive", true);
    model.study("std2").feature("mode").set("shift", "nCore");
    model.study("std2").feature("mode").set("modeFreq", "f0");
    model.study("std2").label("\u7814\u7a76 2\uff08\u5f2f\u66f2\u5149\u7ea4\uff09");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u7535\u573a (ewfd2)");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").set("dataisaxisym", "off");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset3");
    model.result("pg2").run();
    model.result().dataset("dset3").label("\u89e3 3\uff08\u5f2f\u66f2\u5149\u7ea4\uff09");
    model.result("pg2").run();
    model.result("pg2").set("looplevel", new int[]{2});
    model.result("pg2").set("phasetype", "manual");
    model.result("pg2").set("phase", 45);
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "ewfd2.Ez");
    model.result("pg2").feature("surf1").set("colortable", "Wave");
    model.result("pg2").feature("surf1").set("colorlegend", false);
    model.result("pg2").run();
    model.result("pg2").create("con1", "Contour");
    model.result("pg2").feature("con1").set("expr", "ewfd2.Hphi");
    model.result("pg2").feature("con1").set("coloring", "uniform");
    model.result("pg2").feature("con1").set("color", "black");
    model.result("pg2").feature("con1").set("colorlegend", false);
    model.result("pg2").run();
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").set("expr", new String[]{"ewfd2.Er", "ewfd2.Ez"});
    model.result("pg2").feature("arws1").set("descr", "\u7535\u573a");
    model.result("pg2").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg2").feature("arws1").set("color", "black");
    model.result().dataset("dset3").selection().geom("geom2", 2);
    model.result().dataset("dset3").selection().geom("geom2", 2);
    model.result().dataset("dset3").selection().set(2, 7);
    model.result("pg2").run();
    model.result("pg2").label("\u5f2f\u66f2\u5149\u7ea4");
    model.result().export().create("anim1", "Animation");
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
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("plotgroup", "pg2");
    model.result().export("anim1").set("sweeptype", "dde");
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("r0");
    model.result().numerical("gev1").set("data", "dset3");
    model.result().numerical("gev1").setIndex("looplevelinput", "manual", 0);
    model.result().numerical("gev1").setIndex("looplevel", new int[]{2}, 0);
    model.result().numerical("gev1").set("expr", new String[]{"r0"});
    model.result().numerical("gev1").set("descr", new String[]{""});
    model.result().numerical("gev1").set("unit", new String[]{"m"});
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("r0");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").label("n_eff_geometry");
    model.result().numerical("gev2").set("data", "dset3");
    model.result().numerical("gev2").setIndex("looplevelinput", "manual", 0);
    model.result().numerical("gev2").setIndex("looplevel", new int[]{2}, 0);
    model.result().numerical("gev2").setIndex("expr", "real(ewfd2.neff)", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("n_eff_geometry");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();
    model.result().numerical().create("gev3", "EvalGlobal");
    model.result().numerical("gev3").label("n_eff_power");
    model.result().numerical("gev3").set("data", "dset3");
    model.result().numerical("gev3").setIndex("looplevelinput", "manual", 0);
    model.result().numerical("gev3").setIndex("looplevel", new int[]{2}, 0);
    model.result().numerical("gev3").setIndex("expr", "real(ewfd2.neff*ewfd2.rAverage)/r0", 0);
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("n_eff_power");
    model.result().numerical("gev3").set("table", "tbl3");
    model.result().numerical("gev3").setResult();
    model.result("pg2").run();

    model.title("\u5f2f\u66f2\u7684\u9636\u8dc3\u6298\u5c04\u7387\u5149\u7ea4");

    model
         .description("\u672c\u6a21\u578b\u7684\u7b2c\u4e00\u90e8\u5206\u8ba1\u7b97\u7531\u77f3\u82f1\u73bb\u7483\u5236\u6210\u7684\u9636\u8dc3\u6298\u5c04\u7387\u5149\u7ea4\u7684\u6a21\u5f0f\u3002\n\u7b2c\u4e8c\u90e8\u5206\u5219\u5206\u6790\u4e86\u66f2\u7387\u534a\u5f84\u4e3a 3\u00a0mm \u7684\u9636\u8dc3\u6298\u5c04\u7387\u5149\u7ea4\uff0c\u7814\u7a76\u5176\u4f20\u64ad\u6a21\u5f0f\u548c\u8f90\u5c04\u635f\u8017\u3002\u6a21\u578b\u663e\u793a\u5982\u4f55\u627e\u5230\u529f\u7387\u5e73\u5747\u6a21\u5f0f\u534a\u5f84\uff0c\u4ee5\u53ca\u5982\u4f55\u7528\u6765\u8ba1\u7b97\u6709\u6548\u6a21\u5f0f\u6298\u5c04\u7387\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("step_index_fiber_bend.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
