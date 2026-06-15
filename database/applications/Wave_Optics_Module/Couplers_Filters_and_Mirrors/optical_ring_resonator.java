/*
 * optical_ring_resonator.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:36 by COMSOL 6.3.0.290. */
public class optical_ring_resonator {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Couplers_Filters_and_Mirrors");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

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

    model.geom()
         .load(new String[]{"part1"}, "Wave_Optics_Module\\Slab_Waveguides\\slab_waveguide_straight_to_ring_coupler.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").runPre("fin");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("wl0", "1.55[um]", "\u8bbe\u8ba1\u6ce2\u957f");
    model.param().set("w_core", "0.2[um]", "\u82af\u5bbd\u5ea6");
    model.param().set("w_clad", "10*w_core", "\u5305\u5c42\u5bbd\u5ea6");
    model.param().set("r0", "4*wl0", "\u66f2\u7387\u534a\u5f84");
    model.param().set("dx", "3.5833*w_core", "\u6ce2\u5bfc\u95f4\u8ddd");
    model.param().set("lda0", "wl0", "\u6ce2\u957f");
    model.param().set("f0", "c_const/lda0", "\u9891\u7387");
    model.param().set("n_core", "2.5", "\u82af\u5c42\u6298\u5c04\u7387");
    model.param().set("n_clad", "1.5", "\u5305\u5c42\u6298\u5c04\u7387");

    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "core_width", "w_core");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "cladding_width", "w_clad");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "element_length", "2*r0+w_clad");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "coupler_core_separation", "dx");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "ring_radius", "r0");
    model.component("comp1").geom("geom1").feature("pi1").set("displ", new String[]{"0", "-r0-w_clad/2"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_unisel1", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_difsel1", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_boxsel3", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_difsel2", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_csel1.dom", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_boxsel4", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_boxsel5", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_difsel3", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_sel2", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_csel2.bnd", true);

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").label("\u76f8\u4f4d\uff0c\u76f4\u6ce2\u5bfc");
    model.component("comp1").variable("var1").selection().geom("geom1", 2);
    model.component("comp1").variable("var1").selection().named("geom1_pi1_csel1_dom");
    model.component("comp1").variable("var1").set("phi", "ewbe.beta_1*y");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").label("\u76f8\u4f4d\uff0c\u73af\u5f62\u6ce2\u5bfc 1");
    model.component("comp1").variable("var2").selection().geom("geom1", 2);
    model.component("comp1").variable("var2").selection().named("geom1_pi1_boxsel3");
    model.component("comp1").variable("var2").set("phi", "ewbe.beta_1*r0*atan2(y,-(x-r0-dx))");
    model.component("comp1").variable().create("var3");
    model.component("comp1").variable("var3").label("\u76f8\u4f4d\uff0c\u73af\u5f62\u6ce2\u5bfc 2");
    model.component("comp1").variable("var3").selection().geom("geom1", 2);
    model.component("comp1").variable("var3").selection().named("geom1_pi1_difsel2");
    model.component("comp1").variable("var3").set("phi", "ewbe.beta_1*r0*atan2(-y,(x-r0-dx))");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u5305\u5c42");
    model.component("comp1").material("mat1").selection().named("geom1_pi1_difsel1");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"n_clad"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u82af\u5c42");
    model.component("comp1").material("mat2").selection().named("geom1_pi1_unisel1");
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").set("n", new String[]{"n_core"});

    model.component("comp1").physics("ewbe").prop("components").set("components", "outofplane");
    model.component("comp1").physics("ewbe").prop("WaveVector").set("dirCount", "UniDirectionality");
    model.component("comp1").physics("ewbe").prop("WaveVector").set("PhaseSpec", "UserDefined");
    model.component("comp1").physics("ewbe").prop("WaveVector").set("phase1", "phi");
    model.component("comp1").physics("ewbe").create("port1", "Port", 1);
    model.component("comp1").physics("ewbe").feature("port1").selection().named("geom1_pi1_boxsel4");
    model.component("comp1").physics("ewbe").feature("port1").set("PortType", "Numeric");
    model.component("comp1").physics("ewbe").feature().duplicate("port2", "port1");
    model.component("comp1").physics("ewbe").feature("port2").selection().named("geom1_pi1_boxsel5");
    model.component("comp1").physics("ewbe").feature("port2").set("PortExcitation", "off");
    model.component("comp1").physics("ewbe").create("sctr1", "Scattering", 1);
    model.component("comp1").physics("ewbe").feature("sctr1").selection().named("geom1_pi1_difsel3");
    model.component("comp1").physics("ewbe").create("fcont1", "FieldContinuity", 1);
    model.component("comp1").physics("ewbe").feature("fcont1").selection().named("geom1_pi1_csel2_bnd");

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().named("geom1_pi1_sel2");
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("numelem", 3);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "wl0/2");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("bma").set("modeFreq", "f0");
    model.study("std1").feature("bma").set("shiftactive", true);
    model.study("std1").feature("bma").set("shift", "n_core");
    model.study("std1").feature().duplicate("bma1", "bma");
    model.study("std1").feature("bma1").set("PortName", "2");
    model.study("std1").feature("freq").set("plist", "f0");
    model.study("std1").feature().move("freq", 2);
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "wl0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "wl0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "lda0", 0);
    model.study("std1").feature("param")
         .setIndex("plistarr", "range(1.559[um],(1.5615[um]-(1.559[um]))/29,1.5615[um])", 0);
    model.study("std1").feature("param").setIndex("punit", "um", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol4");
    model.sol("sol4").study("std1");
    model.sol("sol4").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol4");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u573a (ewbe)");
    model.result("pg1").set("data", "dset4");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").setIndex("looplevel", 30, 1);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u7535\u573a");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
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
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").label("\u7535\u6a21\u573a\uff0c\u7aef\u53e3 1 (ewbe)");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").set("data", "dset4");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").selection().set(2, 11, 20);
    model.result("pg3").feature("lngr1").set("expr", "root.comp1.ewbe.normEbm_1");
    model.result("pg3").feature("lngr1").set("legend", true);
    model.result("pg3").feature("lngr1").set("legendmethod", "evaluated");
    model.result("pg3").feature("lngr1")
         .set("legendpattern", "\u6709\u6548\u6a21\u5f0f\u6298\u5c04\u7387: eval(comp1.ewbe.neff_1)");
    model.result("pg3").feature("lngr1").set("legendexprprecision", 5);
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").label("\u7535\u6a21\u573a\uff0c\u7aef\u53e3 2 (ewbe)");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").set("data", "dset4");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").selection().set(9, 18, 27);
    model.result("pg4").feature("lngr1").set("expr", "root.comp1.ewbe.normEbm_2");
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("legendmethod", "evaluated");
    model.result("pg4").feature("lngr1")
         .set("legendpattern", "\u6709\u6548\u6a21\u5f0f\u6298\u5c04\u7387: eval(comp1.ewbe.neff_2)");
    model.result("pg4").feature("lngr1").set("legendexprprecision", 5);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "ewbe.Ez");
    model.result("pg1").feature("surf1").create("hght1", "Height");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{1, 16});
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").label("\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u635f\u8017 (ewbe)");
    model.result("pg2").set("ylabel", "\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u635f\u8017");
    model.result("pg2").set("legendpos", "lowerleft");
    model.result("pg2").run();
    model.result("pg2").feature("glob1").setIndex("descr", "\u635f\u8017", 3);
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevelinput", "first", 1);
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevelinput", "first", 1);
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").run();
    model.result("pg5").label("\u76f8\u4f4d\u56fe");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").label("\u76f8\u4f4d");
    model.result("pg5").feature("surf1").set("expr", "ewbe.phi1");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().remove("pg5");
    model.result("pg1").run();

    model.title("\u5149\u5b66\u73af\u5f62\u8c10\u632f\u8154\u9677\u6ce2\u6ee4\u6ce2\u5668");

    model
         .description("\u672c\u4f8b\u8ba1\u7b97\u4e00\u4e2a\u5149\u5b66\u73af\u5f62\u8c10\u632f\u8154\u7684\u5149\u8c31\u7279\u6027\uff0c\u6f14\u793a\u4e86\u5982\u4f55\u5728\u8fb9\u754c\u5904\uff08\u9884\u5b9a\u4e49\u7684\u76f8\u4f4d\u8fd1\u4f3c\u4e2d\u5b58\u5728\u7a81\u53d8\uff09\u4f7f\u7528\u201c\u573a\u8fde\u7eed\u6027\u201d\u8fb9\u754c\u6761\u4ef6\u3002");

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
    model.sol("sol16").clearSolutionData();
    model.sol("sol17").clearSolutionData();
    model.sol("sol18").clearSolutionData();
    model.sol("sol19").clearSolutionData();
    model.sol("sol20").clearSolutionData();
    model.sol("sol21").clearSolutionData();
    model.sol("sol22").clearSolutionData();
    model.sol("sol23").clearSolutionData();
    model.sol("sol24").clearSolutionData();
    model.sol("sol25").clearSolutionData();
    model.sol("sol26").clearSolutionData();
    model.sol("sol27").clearSolutionData();
    model.sol("sol28").clearSolutionData();
    model.sol("sol29").clearSolutionData();
    model.sol("sol30").clearSolutionData();
    model.sol("sol31").clearSolutionData();
    model.sol("sol32").clearSolutionData();
    model.sol("sol33").clearSolutionData();
    model.sol("sol34").clearSolutionData();

    model.label("optical_ring_resonator.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
