/*
 * slot_waveguide.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:44 by COMSOL 6.3.0.290. */
public class slot_waveguide {

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

    model.param().set("w_slab", "180[nm]");
    model.param().descr("w_slab", "\u5e73\u677f\u5bbd\u5ea6");
    model.param().set("w_slot", "50[nm]");
    model.param().descr("w_slot", "\u69fd\u5bbd");
    model.param().set("h_slot", "300[nm]");
    model.param().descr("h_slot", "\u69fd\u9ad8");
    model.param().set("lda0", "1.55[um]");
    model.param().descr("lda0", "\u5de5\u4f5c\u6ce2\u957f");
    model.param().set("f0", "c_const/lda0");
    model.param().descr("f0", "\u5de5\u4f5c\u9891\u7387");
    model.param().set("n_slab", "3.48");
    model.param().descr("n_slab", "\u5e73\u677f\u7684\u6298\u5c04\u7387");
    model.param().set("n_slot", "1.44");
    model.param().descr("n_slot", "\u69fd\u7684\u6298\u5c04\u7387");
    model.param().set("n_clad", "1.44");
    model.param().descr("n_clad", "\u5305\u5c42\u7684\u6298\u5c04\u7387");
    model.param().set("a", "2[um]");
    model.param().descr("a", "\u5916\u90e8\u57df\u8fb9\u957f");
    model.param().set("b", "800[nm]");
    model.param().descr("b", "\u5185\u90e8\u57df\u5bbd\u5ea6");
    model.param().set("c", "500[nm]");
    model.param().descr("c", "\u5185\u90e8\u57df\u9ad8\u5ea6");

    model.component("comp1").geom("geom1").lengthUnit("nm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"w_slot", "h_slot"});
    model.component("comp1").geom("geom1").feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"w_slab", "h_slot"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"w_slot/2", "-h_slot/2"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").set("r2");
    model.component("comp1").geom("geom1").feature("mov1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mov1").set("displx", "-w_slot-w_slab");
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"a", "a"});
    model.component("comp1").geom("geom1").feature("r3").set("base", "center");
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"b", "c"});
    model.component("comp1").geom("geom1").feature("r4").set("base", "center");
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new String[]{"-b/2", "0"});
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"b/2", "0"});
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").cpl().create("maxop1", "Maximum");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").cpl("maxop1").label("\u6700\u5927\u503c 1\uff08\u4e2d\u5fc3\u7ebf\uff09");
    model.component("comp1").cpl("maxop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("maxop1").selection().set(7, 12, 17, 22, 26);
    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").label("\u79ef\u5206 1\uff08\u69fd\uff09");
    model.component("comp1").cpl("intop1").selection().set(6, 7);
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").selection().set(1, 2, 3, 4, 5, 6, 7, 8, 9);
    model.component("comp1").cpl("intop2").label("\u79ef\u5206 2\uff08\u5b8c\u6574\u6ce2\u5bfc\uff09");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u69fd");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"n_slot"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u5e73\u677f");
    model.component("comp1").material("mat2").selection().set(4, 5, 8, 9);
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").set("n", new String[]{"n_slab"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("\u5305\u5c42");
    model.component("comp1").material("mat3").selection().set(1, 2, 3);
    model.component("comp1").material("mat3").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").set("n", new String[]{"n_clad"});

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", false);
    model.component("comp1").mesh("mesh1").feature("size1").selection().set(2, 3, 4, 5, 6, 7, 8, 9);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", "c/20");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("mode").set("modeFreq", "f0");
    model.study("std1").feature("mode").set("neigsactive", true);
    model.study("std1").feature("mode").set("neigs", 2);
    model.study("std1").feature("mode").set("shiftactive", true);
    model.study("std1").feature("mode").set("shift", "n_slab");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "w_slab", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "w_slab", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "w_slot", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(30[nm],10[nm],140[nm])", 0);
    model.study("std1").feature("param").setIndex("punit", "nm", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u573a (ewfd)");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").setIndex("looplevel", 12, 1);
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{1, 3});
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "ewfd.Ex");
    model.result("pg1").run();
    model.result("pg1").create("con1", "Contour");
    model.result("pg1").feature("con1").set("expr", "ewfd.Ex");
    model.result("pg1").feature("con1").set("coloring", "uniform");
    model.result("pg1").feature("con1").set("color", "gray");
    model.result("pg1").feature("con1").set("colorlegend", false);
    model.result("pg1").run();
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").create("sel1", "Selection");
    model.result("pg1").feature("arws1").feature("sel1").selection().set(6, 7);
    model.result("pg1").run();
    model.result("pg1").feature("arws1").set("expr", new String[]{"ewfd.Ex", "ewfd.Ey"});
    model.result("pg1").feature("arws1").set("descr", "\u7535\u573a");
    model.result("pg1").feature("arws1").set("xnumber", 2);
    model.result("pg1").feature("arws1").set("ynumber", 30);
    model.result("pg1").feature("arws1").set("scaleactive", true);
    model.result("pg1").feature("arws1").set("scale", 20000);
    model.result("pg1").feature("arws1").set("color", "white");
    model.result("pg1").run();
    model.result("pg1").set("titletype", "manual");
    model.result("pg1")
         .set("title", "\u8868\u9762\u548c\u7b49\u503c\u7ebf\uff1aE<sub>x</sub> (V/m)\u3002\u9762\u4e0a\u7bad\u5934\uff1a\u7535\u573a\u3002\u69fd\u5bbd\uff1a50 nm");
    model.result("pg1").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").set("data", "dset2");
    model.result().dataset("cln1").setIndex("genpoints", "-b/2", 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", "b/2", 1, 0);
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u5f52\u4e00\u5316\u7684\u6a2a\u5411\u7535\u573a");
    model.result("pg2").set("data", "cln1");
    model.result("pg2").setIndex("looplevelinput", "manual", 1);
    model.result("pg2").setIndex("looplevel", new int[]{3}, 1);
    model.result("pg2").setIndex("looplevelinput", "first", 0);
    model.result("pg2").set("titletype", "manual");
    model.result("pg2")
         .set("title", "\u901a\u8fc7\u6ce2\u5bfc\u4e2d\u5fc3\u7684\u5f52\u4e00\u5316\u6a2a\u5411\u7535\u573a (E<sub>x</sub>)\u3002");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u5f52\u4e00\u5316\u7684\u6a2a\u5411\u7535\u573a");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr1").set("linewidth", "preference");
    model.result("pg2").feature("lngr1").set("expr", "ewfd.Ex/maxop1(ewfd.Ex)");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "x");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").label("\u6a2a\u5411\u7535\u573a");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").set("looplevel", new int[]{1, 3});
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "ewfd.Ex");
    model.result("pg3").feature("surf1").create("hght1", "Height");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u5f52\u4e00\u5316\u7684\u529f\u7387\u548c\u5f3a\u5ea6");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevelinput", "first", 0);
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "\u69fd\u4e2d\u7684\u5f52\u4e00\u5316\u529f\u7387\u548c\u5f3a\u5ea6");
    model.result("pg4").set("twoyaxes", true);
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u69fd\u5bbd (nm)");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u5f52\u4e00\u5316\u529f\u7387 (%)");
    model.result("pg4").set("yseclabelactive", true);
    model.result("pg4").set("yseclabel", "\u5f52\u4e00\u5316\u5f3a\u5ea6 (1/um^2)");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "intop1(ewfd.Poavz)*100/intop2(ewfd.Poavz)", 0);
    model.result("pg4").feature("glob1").set("xdatasolnumtype", "outer");
    model.result("pg4").feature("glob1").set("xdata", "expr");
    model.result("pg4").feature("glob1").set("xdataexpr", "w_slot");
    model.result("pg4").feature("glob1").set("legendmethod", "manual");
    model.result("pg4").feature("glob1").setIndex("legends", "\u5f52\u4e00\u5316\u529f\u7387", 0);
    model.result("pg4").feature().duplicate("glob2", "glob1");
    model.result("pg4").run();
    model.result("pg4").feature("glob2")
         .setIndex("expr", "intop1(ewfd.Poavz)/intop2(ewfd.Poavz)/(w_slot*h_slot)", 0);
    model.result("pg4").feature("glob2").setIndex("unit", "1/um^2", 0);
    model.result("pg4").feature("glob2").setIndex("legends", "\u5f52\u4e00\u5316\u5f3a\u5ea6", 0);
    model.result("pg4").run();
    model.result("pg4").setIndex("plotonsecyaxis", true, 1, 1);
    model.result("pg4").set("legendpos", "middleright");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").run();
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "\u6298\u5c04\u7387");
    model.result("pg5").set("edges", false);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "ewfd.nxx");
    model.result("pg5").feature("surf1").set("descr", "\u6298\u5c04\u7387\uff0c\u5b9e\u90e8\uff0cxx \u5206\u91cf");
    model.result("pg5").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg5").run();
    model.result("pg5").create("ann1", "Annotation");
    model.result("pg5").feature("ann1").set("text", "$SiO_2$");
    model.result("pg5").feature("ann1").set("posyexpr", "-a/4");
    model.result("pg5").feature("ann1").set("latexmarkup", true);
    model.result("pg5").feature("ann1").set("showpoint", false);
    model.result("pg5").run();
    model.result("pg5").create("ann2", "Annotation");
    model.result("pg5").feature("ann2").set("text", "$Si$");
    model.result("pg5").feature("ann2").set("posxexpr", "-w_slot/2-2*w_slab/3");
    model.result("pg5").feature("ann2").set("posyexpr", 5);
    model.result("pg5").feature("ann2").set("latexmarkup", true);
    model.result("pg5").feature("ann2").set("showpoint", false);
    model.result("pg5").feature("ann2").set("color", "white");
    model.result("pg5").feature().duplicate("ann3", "ann2");
    model.result("pg5").run();
    model.result("pg5").feature("ann3").set("posxexpr", "w_slot/2+w_slab/3");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().remove("pg5");
    model.result("pg3").run();

    model.title("\u69fd\u6ce2\u5bfc");

    model
         .description("\u672c\u6a21\u578b\u5206\u6790\u7eb3\u7c73\u69fd\u6ce2\u5bfc\u5185\u7684\u6a21\u5f0f\u4f20\u64ad\u3002\u5728\u69fd\u6ce2\u5bfc\u6784\u578b\u4e2d\uff0c\u4e24\u5757\u6298\u5c04\u7387\u8f83\u9ad8\u7684\u5e73\u677f (~3.48) \u4e0e\u6298\u5c04\u7387\u8f83\u4f4e\u7684\u5e73\u677f (~1.44) \u76f8\u90bb\u653e\u7f6e\u3002\u5bf9\u5de5\u4f5c\u6ce2\u957f\u4e3a 1.55\u00a0um \u7684\u69fd\u6ce2\u5bfc\u4e8c\u7ef4\u6a2a\u622a\u9762\u6267\u884c\u4e86\u6a21\u5f0f\u5206\u6790\u3002\u4e3a\u4f18\u5316\u69fd\u7684\u5bbd\u5ea6\uff0c\u8fd8\u6267\u884c\u4e86\u8fdb\u4e00\u6b65\u5206\u6790\uff0c\u4f7f\u901a\u8fc7\u69fd\u533a\u57df\u7684\u5149\u529f\u7387\u548c\u5149\u5f3a\u8fbe\u5230\u6700\u5927\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();
    model.sol("sol13").clearSolutionData();
    model.sol("sol14").clearSolutionData();

    model.label("slot_waveguide.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
