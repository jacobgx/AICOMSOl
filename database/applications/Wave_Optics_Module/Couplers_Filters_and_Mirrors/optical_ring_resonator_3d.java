/*
 * optical_ring_resonator_3d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:36 by COMSOL 6.3.0.290. */
public class optical_ring_resonator_3d {

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
    model.study("std1").create("wave", "Wavelength");
    model.study("std1").feature("wave").set("ftplistmethod", "manual");
    model.study("std1").feature("wave").set("solnum", "auto");
    model.study("std1").feature("wave").set("notsolnum", "auto");
    model.study("std1").feature("wave").set("outputmap", new String[]{});
    model.study("std1").feature("wave").set("ngenAUX", "1");
    model.study("std1").feature("wave").set("goalngenAUX", "1");
    model.study("std1").feature("wave").set("ngenAUX", "1");
    model.study("std1").feature("wave").set("goalngenAUX", "1");
    model.study("std1").feature("wave").setSolveFor("/physics/ewbe", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("wl0", "1.55[um]", "\u8bbe\u8ba1\u6ce2\u957f");
    model.param().set("w_core", "wl0/5", "\u82af\u5bbd\u5ea6");
    model.param().set("h_core", "1.2*w_core", "\u82af\u9ad8\u5ea6");
    model.param().set("w_clad", "10*w_core", "\u5305\u5c42\u5bbd\u5ea6");
    model.param().set("r0", "3.1*w_clad", "\u66f2\u7387\u534a\u5f84");
    model.param().set("dx", "2.3*w_core", "\u6ce2\u5bfc\u95f4\u8ddd");
    model.param().set("lda0", "wl0", "\u6ce2\u957f");
    model.param().set("f0", "c_const/lda0", "\u9891\u7387");
    model.param().set("n_core", "2.5", "\u82af\u5c42\u6298\u5c04\u7387");
    model.param().set("n_clad", "1.5", "\u5305\u5c42\u6298\u5c04\u7387");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.geom()
         .load(new String[]{"part1"}, "Wave_Optics_Module\\Rectangular_Waveguides\\rectangular_waveguide_straight_to_ring_coupler.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "core_width", "w_core");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "core_height", "h_core");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "cladding_width", "w_clad");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "cladding_height", "w_clad");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "element_length", "2*r0+w_clad");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "coupler_core_separation", "dx");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "ring_radius", "r0");
    model.component("comp1").geom("geom1").feature("pi1").set("displ", new String[]{"0", "-r0-w_clad/2", "0"});

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_unisel1", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_unisel2", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_boxsel5", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_boxsel6", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_boxsel7", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_difsel2", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_difsel4", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_boxsel8", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_boxsel9", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_difsel3", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_csel1.bnd", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_csel2.bnd", true);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").selection().create("adj1", "Adjacent");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("adj1").label("\u82af\u5c42\u8fb9\u754c");
    model.component("comp1").selection("adj1").set("input", new String[]{"geom1_pi1_unisel1"});
    model.component("comp1").selection("adj1").set("interior", true);
    model.component("comp1").selection().create("int1", "Intersection");
    model.component("comp1").selection("int1").label("\u4e09\u89d2\u5f62\u7f51\u683c\u82af\u5c42\u8fb9\u754c");
    model.component("comp1").selection("int1").set("entitydim", 2);
    model.component("comp1").selection("int1").set("input", new String[]{"adj1", "geom1_pi1_csel1_bnd"});
    model.component("comp1").selection().create("adj2", "Adjacent");
    model.component("comp1").selection("adj2").label("\u5305\u5c42\u8fb9\u754c");
    model.component("comp1").selection("adj2").set("input", new String[]{"geom1_pi1_unisel2"});

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u5305\u5c42");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"n_clad"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u82af\u5c42");
    model.component("comp1").material("mat2").selection().named("geom1_pi1_unisel1");
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").set("n", new String[]{"n_core"});

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").selection().geom("geom1", 3);
    model.component("comp1").variable("var1").selection().named("geom1_pi1_boxsel5");
    model.component("comp1").variable("var1").set("phi", "ewbe.beta_1*y");
    model.component("comp1").variable().duplicate("var2", "var1");
    model.component("comp1").variable("var2").selection().named("geom1_pi1_boxsel6");
    model.component("comp1").variable("var2").set("phi", "ewbe.beta_1*r0*atan2(y,-(x-r0-dx))");
    model.component("comp1").variable().duplicate("var3", "var2");
    model.component("comp1").variable("var3").selection().named("geom1_pi1_difsel2");
    model.component("comp1").variable("var3").set("phi", "ewbe.beta_1*r0*atan2(-y,(x-r0-dx))");

    model.component("comp1").physics("ewbe").prop("WaveVector").set("dirCount", "UniDirectionality");
    model.component("comp1").physics("ewbe").prop("WaveVector").set("PhaseSpec", "UserDefined");
    model.component("comp1").physics("ewbe").prop("WaveVector").set("phase1", "phi");
    model.component("comp1").physics("ewbe").prop("ShapeProperty").set("shapeorder", 3);
    model.component("comp1").physics("ewbe").create("port1", "Port", 2);
    model.component("comp1").physics("ewbe").feature("port1").selection().named("geom1_pi1_boxsel8");
    model.component("comp1").physics("ewbe").feature("port1").set("PortType", "Numeric");
    model.component("comp1").physics("ewbe").create("port2", "Port", 2);
    model.component("comp1").physics("ewbe").feature("port2").selection().named("geom1_pi1_boxsel9");
    model.component("comp1").physics("ewbe").feature("port2").set("PortType", "Numeric");
    model.component("comp1").physics("ewbe").create("sctr1", "Scattering", 2);
    model.component("comp1").physics("ewbe").feature("sctr1").selection().named("geom1_pi1_difsel3");
    model.component("comp1").physics("ewbe").create("fcont1", "FieldContinuity", 2);
    model.component("comp1").physics("ewbe").feature("fcont1").selection().named("geom1_pi1_csel2_bnd");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("geom1_pi1_csel1_bnd");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "w_clad/5");
    model.component("comp1").mesh("mesh1").feature("size").set("hgrad", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().named("int1");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "w_core/2");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().named("geom1_pi1_boxsel7");
    model.component("comp1").mesh("mesh1").feature("swe1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmax", "wl0/2");
    model.component("comp1").mesh("mesh1").create("cpd1", "CopyDomain");
    model.component("comp1").mesh("mesh1").feature("cpd1").selection("source").geom(3);
    model.component("comp1").mesh("mesh1").feature("cpd1").selection("destination").geom(3);
    model.component("comp1").mesh("mesh1").feature("cpd1").selection("source").named("geom1_pi1_boxsel7");
    model.component("comp1").mesh("mesh1").feature("cpd1").selection("destination").named("geom1_pi1_difsel4");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("bma", "BoundaryModeAnalysis");
    model.study("std1").feature("bma").set("modeFreq", "f0");
    model.study("std1").feature("bma").set("shift", "n_core");
    model.study("std1").feature().duplicate("bma1", "bma");
    model.study("std1").feature("bma1").set("PortName", "2");
    model.study("std1").feature("wave").set("plist", "lda0");
    model.study("std1").feature().move("wave", 1);
    model.study("std1").feature().move("wave", 2);
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "wl0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "wl0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "lda0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(1.55[um],0.002[um],1.57[um])", 0);
    model.study("std1").feature("param").setIndex("punit", "um", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol4");
    model.sol("sol4").study("std1");
    model.sol("sol4").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol4");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u7535\u573a (ewbe)");
    model.result("pg1").set("data", "dset4");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").setIndex("looplevel", 11, 1);
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").label("\u7535\u573a");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("smooth", "internal");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset4");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{"", "", "", ""});
    model.result("pg2").feature("glob1")
         .set("expr", new String[]{"ewbe.Rport_1", "ewbe.Tport_2", "ewbe.RTtotal", "ewbe.Atotal"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u53cd\u5c04\u7387\uff0c\u7aef\u53e3 1", "\u900f\u5c04\u7387\uff0c\u7aef\u53e3 2", "\u603b\u53cd\u5c04\u7387\u548c\u900f\u5c04\u7387", "\u5438\u6536\u7387"});
    model.result("pg2").label("\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387 (ewbe)");
    model.result("pg2").feature("glob1").set("titletype", "none");
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387 (1)");
    model.result("pg2").feature("glob1").set("xdataexpr", "lda0");
    model.result("pg2").feature("glob1").set("xdataunit", "um");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "all");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u7535\u6a21\u573a\uff0c\u7aef\u53e3 1 (ewbe)");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").set("data", "dset4");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "root.comp1.ewbe.normEbm_1");
    model.result("pg3").feature("surf1").set("colortable", "RainbowLight");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u7535\u6a21\u573a\uff0c\u7aef\u53e3 2 (ewbe)");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").set("data", "dset4");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "root.comp1.ewbe.normEbm_2");
    model.result("pg4").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{1, 6});
    model.result("pg1").set("edgecolor", "gray");
    model.result("pg1").set("showlegends", false);

    model.component("comp1").view("view1").set("showgrid", false);
    model.component("comp1").view("view1").set("showaxisorientation", false);

    model.result("pg1").run();
    model.result("pg1").feature("mslc1").set("expr", "ewbe.Ez");
    model.result("pg1").feature("mslc1").set("xnumber", "0");
    model.result("pg1").feature("mslc1").set("ynumber", "0");
    model.result("pg1").feature("mslc1").set("colortable", "Dipole");
    model.result("pg1").feature("mslc1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").feature("mslc1").feature("def1").set("expr", new String[]{"", "", "ewbe.Ez"});
    model.result("pg1").feature("mslc1").feature("def1").set("scaleactive", true);
    model.result("pg1").feature("mslc1").feature("def1").set("scale", "5E-8");
    model.result("pg1").run();
    model.result("pg1").feature("mslc1").create("tran1", "Transparency");
    model.result("pg1").run();
    model.result("pg1").feature("mslc1").feature("tran1").set("transparency", 0.6);
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "ewbe.Ez");
    model.result("pg1").feature("surf1").set("rangecoloractive", true);
    model.result("pg1").feature("surf1").set("rangecolormin", "-2.5E7");
    model.result("pg1").feature("surf1").set("rangecolormax", "2.5E7");
    model.result("pg1").feature("surf1").set("colortable", "ThermalWave");
    model.result("pg1").feature("surf1").create("sel1", "Selection");
    model.result("pg1").feature("surf1").feature("sel1").selection().named("adj1");
    model.result("pg1").run();
    model.result("pg1").create("surf2", "Surface");
    model.result("pg1").feature("surf2").set("expr", "ewbe.Ez");
    model.result("pg1").feature("surf2").set("colortable", "Cyclic");
    model.result("pg1").feature("surf2").set("rangecoloractive", true);
    model.result("pg1").feature("surf2").set("rangecolormin", -5000);
    model.result("pg1").feature("surf2").set("rangecolormax", 5000);
    model.result("pg1").feature("surf2").create("sel1", "Selection");
    model.result("pg1").feature("surf2").feature("sel1").selection().named("adj2");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").create("tran1", "Transparency");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").feature("tran1").set("transparency", 0.97);
    model.result("pg1").run();
    model.result("pg1").feature("mslc1").set("resolution", "extrafine");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").label("\u900f\u5c04\u7387\u548c\u635f\u8017 (ewbe)");
    model.result("pg2").set("ylabel", "\u900f\u5c04\u7387\u548c\u635f\u8017 (1)");
    model.result("pg2").run();
    model.result("pg2").feature("glob1").remove("unit", new int[]{0, 2});
    model.result("pg2").feature("glob1").remove("descr", new int[]{0, 2});
    model.result("pg2").feature("glob1").remove("expr", new int[]{0, 2});
    model.result("pg2").feature("glob1").set("legendmethod", "manual");
    model.result("pg2").feature("glob1").setIndex("legends", "\u900f\u5c04\u7387", 0);
    model.result("pg2").feature("glob1").setIndex("legends", "\u635f\u8017", 1);
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").create("arws1", "ArrowSurface");
    model.result("pg3").feature("arws1")
         .set("expr", new String[]{"ewbe.tEmodex_1", "ewbe.tEmodey_1", "ewbe.tEmodez_1"});
    model.result("pg3").feature("arws1").set("descr", "\u7aef\u53e3\u5207\u5411\u7535\u6a21\u573a");
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").create("arws1", "ArrowSurface");
    model.result("pg4").feature("arws1")
         .set("expr", new String[]{"ewbe.tEmodex_2", "ewbe.tEmodey_2", "ewbe.tEmodez_2"});
    model.result("pg4").feature("arws1").set("descr", "\u7aef\u53e3\u5207\u5411\u7535\u6a21\u573a");
    model.result("pg4").run();

    model.title("\u5149\u5b66\u73af\u5f62\u8c10\u632f\u8154\u9677\u6ce2\u6ee4\u6ce2\u5668\uff08\u4e09\u7ef4\uff09");

    model
         .description("\u672c\u4f8b\u8ba1\u7b97\u5149\u5b66\u73af\u5f62\u8c10\u632f\u5668\u4e09\u7ef4\u6a21\u578b\u7684\u5149\u8c31\u5c5e\u6027\uff0c\u6f14\u793a\u4e86\u5982\u4f55\u4f7f\u7528\u201c\u6ce2\u52a8\u5149\u5b66\u201d\u96f6\u4ef6\u5e93\u4e2d\u7684\u96f6\u4ef6\uff0c\u4ee5\u53ca\u5982\u4f55\u5728\u8fb9\u754c\u5904\uff08\u9884\u5b9a\u4e49\u7684\u76f8\u4f4d\u8fd1\u4f3c\u4e2d\u5b58\u5728\u7a81\u53d8\uff09\u8bbe\u7f6e\u201c\u573a\u8fde\u7eed\u6027\u201d\u8fb9\u754c\u6761\u4ef6\u3002");

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
    model.sol("sol15").clearSolutionData();

    model.label("optical_ring_resonator_3d.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
