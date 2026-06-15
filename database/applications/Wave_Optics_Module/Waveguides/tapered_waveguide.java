/*
 * tapered_waveguide.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:45 by COMSOL 6.3.0.290. */
public class tapered_waveguide {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Waveguides");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

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
    model.param().set("lam0", "1.55[um]", "\u6ce2\u957f");
    model.param().set("f0", "c_const/lam0", "\u9891\u7387");
    model.param().set("n_taper", "200", "\u771f\u7a7a\u6ce2\u957f\u7684\u9525\u533a\u957f\u5ea6");
    model.param().set("d_taper", "n_taper*lam0", "\u9525\u533a\u957f\u5ea6");
    model.param().set("w_core_1", "lam0*2", "\u8f93\u5165\u82af\u5bbd\u5ea6");
    model.param().set("w_clad_1", "100*lam0", "\u8f93\u5165\u5305\u5c42\u5bbd\u5ea6");
    model.param().set("w_core_2", "lam0*0.2", "\u8f93\u51fa\u82af\u5bbd\u5ea6");
    model.param().set("w_clad_2", "w_clad_1", "\u8f93\u51fa\u5305\u5c42\u5bbd\u5ea6");
    model.param().set("n_clad", "1.5", "\u6298\u5c04\u7387\uff0c\u5305\u5c42");
    model.param().set("n_core", "1.55", "\u6298\u5c04\u7387\uff0c\u82af\u5c42");

    model.geom()
         .load(new String[]{"part1"}, "Wave_Optics_Module\\Slab_Waveguides\\slab_waveguide_taper.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").label("\u9525\u5f62\u6ce2\u5bfc");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "input_core_width", "w_core_1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "input_cladding_width", "w_clad_1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "output_core_width", "w_core_2");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "output_cladding_width", "w_clad_2");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "element_length", "d_taper");
    model.component("comp1").geom("geom1").feature("pi1").set("rot", -90);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u82af\u5c42");
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u975e PML");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetodom", "pi1_sel1", "csel1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_unisel1", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetodom", "pi1_unisel1", "csel2");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_difsel1", true);
    model.component("comp1").geom("geom1").runPre("fin");
    model.geom()
         .load(new String[]{"part2"}, "Wave_Optics_Module\\Slab_Waveguides\\slab_waveguide_straight.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi2").set("part", "part2");
    model.component("comp1").geom("geom1").feature("pi2").label("\u8f93\u5165\u6ce2\u5bfc");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "core_width", "w_core_1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "cladding_width", "w_clad_1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "element_length", "lam0");
    model.component("comp1").geom("geom1").feature("pi2").set("displ", new String[]{"-lam0", "0"});
    model.component("comp1").geom("geom1").feature("pi2").set("rot", -90);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetodom", "pi2_sel1", "csel1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selkeepdom", "pi2_unisel1", true);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetodom", "pi2_unisel1", "csel2");
    model.component("comp1").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel3").label("\u76f4\u6ce2\u5bfc\u6a2a\u5411\u5916\u56f4");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selkeepbnd", "pi2_unisel2", true);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetobnd", "pi2_difsel1", "csel3");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature().duplicate("pi3", "pi2");
    model.component("comp1").geom("geom1").feature("pi2").set("selcontributetoobj", new String[]{});
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepobj", new String[]{});
    model.component("comp1").geom("geom1").feature("pi2").set("selcontributetopnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi2").set("selkeeppnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi2").set("selshowpnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi2")
         .set("selcontributetobnd", new String[]{"none", "none", "none", "none", "none", "none", "none", "csel3"});
    model.component("comp1").geom("geom1").feature("pi2")
         .set("selkeepbnd", new String[]{"off", "off", "off", "on", "off", "off", "off", "off"});
    model.component("comp1").geom("geom1").feature("pi2")
         .set("selshowbnd", new String[]{"on", "on", "on", "on", "on", "on", "on", "on"});
    model.component("comp1").geom("geom1").feature("pi2")
         .set("selcontributetodom", new String[]{"csel1", "none", "csel2"});
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepdom", new String[]{"off", "off", "on"});
    model.component("comp1").geom("geom1").feature("pi2").set("selshowdom", new String[]{"on", "on", "on"});
    model.component("comp1").geom("geom1").feature("pi3").label("\u8f93\u5165 PML");
    model.component("comp1").geom("geom1").feature("pi3").set("displ", new String[]{"-2*lam0", "0"});
    model.component("comp1").geom("geom1").selection().create("csel4", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel4").label("PML");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetodom", "pi3_unisel1", "csel4");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selkeepbnd", "pi3_sel3", true);
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selkeepbnd", "pi3_sel4", true);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature().duplicate("pi4", "pi2");
    model.component("comp1").geom("geom1").feature("pi2").set("selcontributetoobj", new String[]{});
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepobj", new String[]{});
    model.component("comp1").geom("geom1").feature("pi2").set("selcontributetopnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi2").set("selkeeppnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi2").set("selshowpnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi2")
         .set("selcontributetobnd", new String[]{"none", "none", "none", "none", "none", "none", "none", "csel3"});
    model.component("comp1").geom("geom1").feature("pi2")
         .set("selkeepbnd", new String[]{"off", "off", "off", "on", "off", "off", "off", "off"});
    model.component("comp1").geom("geom1").feature("pi2")
         .set("selshowbnd", new String[]{"on", "on", "on", "on", "on", "on", "on", "on"});
    model.component("comp1").geom("geom1").feature("pi2")
         .set("selcontributetodom", new String[]{"csel1", "none", "csel2"});
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepdom", new String[]{"off", "off", "on"});
    model.component("comp1").geom("geom1").feature("pi2").set("selshowdom", new String[]{"on", "on", "on"});
    model.component("comp1").geom("geom1").feature("pi4").label("\u8f93\u51fa\u6ce2\u5bfc");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "core_width", "w_core_2");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "cladding_width", "w_clad_2");
    model.component("comp1").geom("geom1").feature("pi4").set("displ", new String[]{"d_taper", "0"});
    model.component("comp1").geom("geom1").feature("pi4").setEntry("selkeepbnd", "pi4_unisel2", false);
    model.component("comp1").geom("geom1").feature("pi4").setEntry("selkeepbnd", "pi4_unisel3", true);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature().duplicate("pi5", "pi3");
    model.component("comp1").geom("geom1").feature("pi3").set("selcontributetoobj", new String[]{});
    model.component("comp1").geom("geom1").feature("pi3").set("selkeepobj", new String[]{});
    model.component("comp1").geom("geom1").feature("pi3").set("selcontributetopnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi3").set("selkeeppnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi3").set("selshowpnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi3")
         .set("selcontributetobnd", new String[]{"none", "none", "none", "none", "none", "none", "none", "csel3"});
    model.component("comp1").geom("geom1").feature("pi3")
         .set("selkeepbnd", new String[]{"off", "on", "on", "on", "off", "off", "off", "off"});
    model.component("comp1").geom("geom1").feature("pi3")
         .set("selshowbnd", new String[]{"on", "on", "on", "on", "on", "on", "on", "on"});
    model.component("comp1").geom("geom1").feature("pi3")
         .set("selcontributetodom", new String[]{"csel1", "none", "csel4"});
    model.component("comp1").geom("geom1").feature("pi3").set("selkeepdom", new String[]{"off", "off", "on"});
    model.component("comp1").geom("geom1").feature("pi3").set("selshowdom", new String[]{"on", "on", "on"});
    model.component("comp1").geom("geom1").feature("pi5").label("\u8f93\u51fa PML");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "core_width", "w_core_2");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "cladding_width", "w_clad_2");
    model.component("comp1").geom("geom1").feature("pi5").set("displ", new String[]{"d_taper+lam0", "0"});
    model.component("comp1").geom("geom1").feature("pi5").setEntry("selkeepbnd", "pi5_sel3", false);
    model.component("comp1").geom("geom1").feature("pi5").setEntry("selkeepbnd", "pi5_sel4", false);
    model.component("comp1").geom("geom1").feature("pi5").setEntry("selkeepbnd", "pi5_unisel2", false);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u5305\u5c42");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"n_clad"});
    model.component("comp1").material().duplicate("mat2", "mat1");
    model.component("comp1").material("mat2").label("\u82af\u5c42");
    model.component("comp1").material("mat2").selection().named("geom1_csel1_dom");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").set("n", new String[]{"n_core"});

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().named("geom1_csel4_dom");
    model.component("comp1").coordSystem("pml1").set("wavelengthSourceType", "userDefined");
    model.component("comp1").coordSystem("pml1").set("typicalWavelength", "2*pi/kx");

    model.component("comp1").physics("ewbe").prop("components").set("components", "outofplane");
    model.component("comp1").physics("ewbe").prop("WaveVector").set("dirCount", "UniDirectionality");
    model.component("comp1").physics("ewbe").prop("WaveVector").set("PhaseSpec", "UserDefined");
    model.component("comp1").physics("ewbe").prop("WaveVector").set("phase1", "phi");
    model.component("comp1").physics("ewbe").prop("UserDefinedWaveVectorSpecification")
         .set("k1Phase", new String[]{"kx", "0", "0"});
    model.component("comp1").physics("ewbe").create("port1", "Port", 1);
    model.component("comp1").physics("ewbe").feature("port1").selection().named("geom1_pi2_unisel2");
    model.component("comp1").physics("ewbe").feature("port1").set("PortType", "Numeric");
    model.component("comp1").physics("ewbe").feature("port1").set("PortSlit", true);
    model.component("comp1").physics("ewbe").feature("port1").set("SlitType", "DomainBacked");
    model.component("comp1").physics("ewbe").feature("port1").set("PortOrientation", "ReversePort");
    model.component("comp1").physics("ewbe").create("port2", "Port", 1);
    model.component("comp1").physics("ewbe").feature("port2").selection().named("geom1_pi4_unisel3");
    model.component("comp1").physics("ewbe").feature("port2").set("PortType", "Numeric");
    model.component("comp1").physics("ewbe").feature("port2").set("PortSlit", true);
    model.component("comp1").physics("ewbe").feature("port2").set("SlitType", "DomainBacked");
    model.component("comp1").physics("ewbe").feature("port2").set("PortOrientation", "ReversePort");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("\u8f93\u5165 PML");
    model.component("comp1").variable("var1").selection().geom("geom1", 2);
    model.component("comp1").variable("var1").selection().named("geom1_pi3_unisel1");
    model.component("comp1").variable("var1").set("kx", "ewbe.beta_1");
    model.component("comp1").variable("var1").set("phi", "kx*pml1.x");
    model.component("comp1").variable().duplicate("var2", "var1");
    model.component("comp1").variable("var2").label("\u8f93\u5165\u6ce2\u5bfc");
    model.component("comp1").variable("var2").selection().named("geom1_pi2_unisel1");
    model.component("comp1").variable("var2").set("phi", "kx*x");
    model.component("comp1").variable().duplicate("var3", "var2");
    model.component("comp1").variable("var3").label("\u9525\u5f62\u6ce2\u5bfc");
    model.component("comp1").variable("var3").selection().named("geom1_pi1_unisel1");
    model.component("comp1").variable("var3").set("kx", "ewbe.beta_1+(ewbe.beta_2-ewbe.beta_1)*x/d_taper");
    model.component("comp1").variable("var3").set("phi", "(kx+ewbe.beta_1)*x/2");
    model.component("comp1").variable().duplicate("var4", "var3");
    model.component("comp1").variable("var4").label("\u8f93\u51fa\u6ce2\u5bfc");
    model.component("comp1").variable("var4").selection().named("geom1_pi4_unisel1");
    model.component("comp1").variable("var4").set("kx", "ewbe.beta_2");
    model.component("comp1").variable("var4").set("phi", "kx*(x-d_taper)+(ewbe.beta_1+ewbe.beta_2)*d_taper/2");
    model.component("comp1").variable().duplicate("var5", "var4");
    model.component("comp1").variable("var5").label("\u8f93\u51fa PML");
    model.component("comp1").variable("var5").selection().named("geom1_pi5_unisel1");
    model.component("comp1").variable("var5")
         .set("phi", "kx*(pml1.x-d_taper-lam0)+(ewbe.beta_1+ewbe.beta_2)*d_taper/2+kx*lam0");

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().named("geom1_pi3_unisel2");
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").selection().named("geom1_pi3_sel4");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("numelem", 50);
    model.component("comp1").mesh("mesh1").feature("edg1").feature().duplicate("dis2", "dis1");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis2").selection().named("geom1_pi3_sel3");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis2").set("numelem", 10);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").set("adjustedgdistr", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().named("geom1_csel3_bnd");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 10);
    model.component("comp1").mesh("mesh1").feature("map1").feature().duplicate("dis2", "dis1");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().named("geom1_pi1_difsel1");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2")
         .set("numelem", "max(10,round(n_taper/20))");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "lam0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "lam0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "n_taper", 0);
    model.study("std1").feature("param").setIndex("plistarr", "10^{range(-1,0.25,4)}", 0);
    model.study("std1").create("bma", "BoundaryModeAnalysis");
    model.study("std1").feature("bma").set("modeFreq", "f0");
    model.study("std1").feature("bma").set("shift", "n_core");
    model.study("std1").feature().duplicate("bma1", "bma");
    model.study("std1").feature("bma1").set("PortName", "2");
    model.study("std1").feature().move("wave", 2);
    model.study("std1").feature().move("wave", 3);
    model.study("std1").feature("wave").set("plist", "lam0");
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
    model.result("pg1").setIndex("looplevel", 21, 1);
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
    model.result("pg2").feature("glob1").set("xdataexpr", "n_taper");
    model.result("pg2").feature("glob1").set("xdataunit", "");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "all");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").label("\u7535\u6a21\u573a\uff0c\u7aef\u53e3 1 (ewbe)");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").set("data", "dset4");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").selection().set(8, 10, 12);
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
    model.result("pg4").feature("lngr1").selection().set(29, 31, 33);
    model.result("pg4").feature("lngr1").set("expr", "root.comp1.ewbe.normEbm_2");
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("legendmethod", "evaluated");
    model.result("pg4").feature("lngr1")
         .set("legendpattern", "\u6709\u6548\u6a21\u5f0f\u6298\u5c04\u7387: eval(comp1.ewbe.neff_2)");
    model.result("pg4").feature("lngr1").set("legendexprprecision", 5);
    model.result("pg1").run();
    model.result("pg1").selection().geom("geom1", 2);
    model.result("pg1").selection().named("geom1_csel2_dom");
    model.result("pg1").set("applyselectiontodatasetedges", true);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").create("hght1", "Height");
    model.result("pg1").run();

    model.view("view4").camera().set("viewscaletype", "automatic");
    model.view("view4").camera().set("autocontext", "anisotropic");
    model.view("view4").camera().set("zweight", 0.2);
    model.view("view4").camera().set("autoupdate", true);

    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").label("\u900f\u5c04\u7387 (ewbe)");
    model.result("pg2").set("ylabel", "\u900f\u5c04\u7387");
    model.result("pg2").set("xlog", true);
    model.result("pg2").set("showlegends", false);
    model.result("pg2").run();
    model.result("pg2").feature("glob1").remove("unit", 0);
    model.result("pg2").feature("glob1").remove("descr", 0);
    model.result("pg2").feature("glob1").remove("expr", new int[]{0});
    model.result("pg2").feature("glob1").remove("unit", 1);
    model.result("pg2").feature("glob1").remove("descr", 1);
    model.result("pg2").feature("glob1").remove("expr", new int[]{1});
    model.result("pg2").feature("glob1").remove("unit", 1);
    model.result("pg2").feature("glob1").remove("descr", 1);
    model.result("pg2").feature("glob1").remove("expr", new int[]{1});
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevelinput", "first", 1);
    model.result("pg3").run();
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "y");
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevelinput", "first", 1);
    model.result("pg4").run();
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "y");
    model.result("pg4").run();
    model.result("pg1").run();

    model.title("\u9525\u5f62\u6ce2\u5bfc");

    model
         .description("\u9525\u5f62\u5149\u6ce2\u5bfc\u7ed3\u6784\u7528\u4e8e\u5339\u914d\u5177\u6709\u4e0d\u540c\u51e0\u4f55\u6a2a\u622a\u9762\u548c/\u6216\u4e0d\u540c\u6750\u6599\u53c2\u6570\u7684\u4e24\u4e2a\u6ce2\u5bfc\u3002\u9525\u5f62\u6ce2\u5bfc\u5728\u8f93\u5165\u5e73\u9762\u6709\u4e00\u4e2a\u51e0\u4f55\u6a2a\u622a\u9762\uff0c\u5728\u8f93\u51fa\u5e73\u9762\u6709\u53e6\u4e00\u4e2a\u6a2a\u622a\u9762\u3002\u8fd9\u4e9b\u5e73\u9762\u4e4b\u95f4\u7684\u82af\u5bbd\u5ea6\u968f\u4f20\u64ad\u8ddd\u79bb\u5448\u7ebf\u6027\u53d8\u5316\u3002\n\n\u6b64\u6a21\u578b\u6f14\u793a\u5f53\u201c\u7535\u78c1\u6ce2\uff0c\u6ce2\u675f\u5305\u7edc\u201d\u63a5\u53e3\u4e0e\u201c\u5b8c\u7f8e\u5339\u914d\u5c42\u201d(PML) \u7ed3\u5408\u4f7f\u7528\u65f6\uff0c\u5982\u4f55\u4e3a\u8be5\u63a5\u53e3\u5b9a\u4e49\u7528\u6237\u5b9a\u4e49\u7684\u76f8\u4f4d\u548c\u6ce2\u77e2\u3002\u5176\u4e2d\u7684 PML \u7528\u4e8e\u5438\u6536\u4e0e\u7aef\u53e3\u6a21\u573a\u4e0d\u5339\u914d\u7684\u8f90\u5c04\u3002");

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

    model.label("tapered_waveguide.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
