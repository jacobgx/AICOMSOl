/*
 * waveguide_s_bend.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:46 by COMSOL 6.3.0.290. */
public class waveguide_s_bend {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Waveguides");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ewbe", "ElectromagneticWavesBeamEnvelopes", "geom1");
    model.component("comp1").physics().create("ewfd", "ElectromagneticWavesFrequencyDomain", "geom1");
    model.component("comp1").physics().create("g", "GeneralFormPDE", "geom1");
    model.component("comp1").physics("g").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("g").feature("gfeq1").set("Ga", new String[][]{{"-ux", "-uy"}});
    model.component("comp1").physics().create("g2", "GeneralFormPDE", "geom1");
    model.component("comp1").physics("g2").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("g2").feature("gfeq1").set("Ga", new String[][]{{"-u2x", "-u2y"}});

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
    model.study("std1").feature("bma").setSolveFor("/physics/ewfd", true);
    model.study("std1").feature("bma").setSolveFor("/physics/g", true);
    model.study("std1").feature("bma").setSolveFor("/physics/g2", true);
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
    model.study("std1").feature("freq").setSolveFor("/physics/ewfd", true);
    model.study("std1").feature("freq").setSolveFor("/physics/g", true);
    model.study("std1").feature("freq").setSolveFor("/physics/g2", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("lda0", "1550[nm]", "\u6ce2\u957f");
    model.param().set("f0", "c_const/lda0", "\u9891\u7387");
    model.param().set("w_core", "1.35[um]", "\u82af\u5bbd\u5ea6");
    model.param().set("w_clad", "40[um]+w_core", "\u5305\u5c42\u5bbd\u5ea6");
    model.param().set("d_bent_wg", "40[um]", "\u5f2f\u66f2\u6ce2\u5bfc\u957f\u5ea6");
    model.param().set("offset", "w_clad/8", "\u6ce2\u5bfc\u672b\u7aef\u504f\u79fb\u91cf");
    model.param().set("d_straight_wg", "10[um]", "\u76f4\u6ce2\u5bfc\u957f\u5ea6");
    model.param().set("n_core", "1.55", "\u82af\u5c42\u6298\u5c04\u7387");
    model.param().set("n_clad", "1.4", "\u5305\u5c42\u6298\u5c04\u7387");

    model.geom()
         .load(new String[]{"part1"}, "Wave_Optics_Module\\Slab_Waveguides\\slab_waveguide_s_bend.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "core_width", "w_core");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "cladding_width", "w_clad");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "horizontal_displacement", "offset");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "element_length", "d_bent_wg");
    model.component("comp1").geom("geom1").feature("pi1").set("rot", -90);
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u7ea4\u82af");
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u5305\u5c42");
    model.component("comp1").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel3").label("\u975e PML");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetodom", "pi1_unisel1", "csel3");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetodom", "pi1_sel1", "csel1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetodom", "pi1_sel2", "csel2");
    model.geom()
         .load(new String[]{"part2"}, "Wave_Optics_Module\\Slab_Waveguides\\slab_waveguide_straight.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi2").set("part", "part2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "core_width", "w_core");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "cladding_width", "w_clad");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "element_length", "d_straight_wg/2");
    model.component("comp1").geom("geom1").feature("pi2").set("rot", 90);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetodom", "pi2_sel1", "csel1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetodom", "pi2_sel2", "csel2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetodom", "pi2_unisel1", "csel3");
    model.component("comp1").geom("geom1").selection().create("csel4", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel4").label("\u7aef\u53e3 1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetobnd", "pi2_unisel3", "csel4");
    model.component("comp1").geom("geom1").run("pi2");
    model.component("comp1").geom("geom1").feature().duplicate("pi3", "pi2");
    model.component("comp1").geom("geom1").feature("pi2").set("selcontributetoobj", new String[]{});
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepobj", new String[]{});
    model.component("comp1").geom("geom1").feature("pi2").set("selcontributetopnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi2").set("selkeeppnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi2").set("selshowpnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi2")
         .set("selcontributetobnd", new String[]{"none", "none", "none", "none", "none", "none", "csel4", "none"});
    model.component("comp1").geom("geom1").feature("pi2")
         .set("selkeepbnd", new String[]{"off", "off", "off", "off", "off", "off", "off", "off"});
    model.component("comp1").geom("geom1").feature("pi2")
         .set("selshowbnd", new String[]{"on", "on", "on", "on", "on", "on", "on", "on"});
    model.component("comp1").geom("geom1").feature("pi2")
         .set("selcontributetodom", new String[]{"csel1", "csel2", "csel3"});
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepdom", new String[]{"off", "off", "off"});
    model.component("comp1").geom("geom1").feature("pi2").set("selshowdom", new String[]{"on", "on", "on"});
    model.component("comp1").geom("geom1").feature("pi3").set("displ", new String[]{"-d_straight_wg/2", "0"});
    model.component("comp1").geom("geom1").selection().create("csel5", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel5").label("\u5de6\u4fa7 PML");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetodom", "pi3_unisel1", "csel5");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetobnd", "pi3_unisel3", "none");
    model.component("comp1").geom("geom1").run("pi3");
    model.component("comp1").geom("geom1").feature().duplicate("pi4", "pi2");
    model.component("comp1").geom("geom1").feature("pi2").set("selcontributetoobj", new String[]{});
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepobj", new String[]{});
    model.component("comp1").geom("geom1").feature("pi2").set("selcontributetopnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi2").set("selkeeppnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi2").set("selshowpnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi2")
         .set("selcontributetobnd", new String[]{"none", "none", "none", "none", "none", "none", "csel4", "none"});
    model.component("comp1").geom("geom1").feature("pi2")
         .set("selkeepbnd", new String[]{"off", "off", "off", "off", "off", "off", "off", "off"});
    model.component("comp1").geom("geom1").feature("pi2")
         .set("selshowbnd", new String[]{"on", "on", "on", "on", "on", "on", "on", "on"});
    model.component("comp1").geom("geom1").feature("pi2")
         .set("selcontributetodom", new String[]{"csel1", "csel2", "csel3"});
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepdom", new String[]{"off", "off", "off"});
    model.component("comp1").geom("geom1").feature("pi2").set("selshowdom", new String[]{"on", "on", "on"});
    model.component("comp1").geom("geom1").feature("pi4").set("displ", new String[]{"d_bent_wg", "-offset"});
    model.component("comp1").geom("geom1").feature("pi4").set("rot", -90);
    model.component("comp1").geom("geom1").selection().create("csel6", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel6").label("\u7aef\u53e3 2");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("selcontributetobnd", "pi4_unisel3", "csel6");
    model.component("comp1").geom("geom1").run("pi4");
    model.component("comp1").geom("geom1").feature().duplicate("pi5", "pi3");
    model.component("comp1").geom("geom1").feature("pi3").set("selcontributetoobj", new String[]{});
    model.component("comp1").geom("geom1").feature("pi3").set("selkeepobj", new String[]{});
    model.component("comp1").geom("geom1").feature("pi3").set("selcontributetopnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi3").set("selkeeppnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi3").set("selshowpnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi3")
         .set("selcontributetobnd", new String[]{"none", "none", "none", "none", "none", "none", "none", "none"});
    model.component("comp1").geom("geom1").feature("pi3")
         .set("selkeepbnd", new String[]{"off", "off", "off", "off", "off", "off", "off", "off"});
    model.component("comp1").geom("geom1").feature("pi3")
         .set("selshowbnd", new String[]{"on", "on", "on", "on", "on", "on", "on", "on"});
    model.component("comp1").geom("geom1").feature("pi3")
         .set("selcontributetodom", new String[]{"csel1", "csel2", "csel5"});
    model.component("comp1").geom("geom1").feature("pi3").set("selkeepdom", new String[]{"off", "off", "off"});
    model.component("comp1").geom("geom1").feature("pi3").set("selshowdom", new String[]{"on", "on", "on"});
    model.component("comp1").geom("geom1").feature("pi5")
         .set("displ", new String[]{"d_bent_wg+d_straight_wg/2", "0"});
    model.component("comp1").geom("geom1").feature("pi5").setIndex("displ", "-offset", 1);
    model.component("comp1").geom("geom1").feature("pi5").set("rot", -90);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").selection().create("csel7", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel7").label("\u53f3\u4fa7 PML");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("selcontributetodom", "pi5_unisel1", "csel7");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("PML");
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"csel5", "csel7"});

    model.component("comp1").selection().create("int1", "Intersection");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("int1").label("\u975e PML \u7ea4\u82af");
    model.component("comp1").selection("int1").set("input", new String[]{"geom1_csel1_dom", "geom1_csel3_dom"});
    model.component("comp1").selection().duplicate("int2", "int1");
    model.component("comp1").selection("int2").label("\u975e PML \u5305\u5c42");
    model.component("comp1").selection("int2").set("input", new String[]{"geom1_csel3_dom", "geom1_csel2_dom"});
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").label("\u975e PML \u7ea4\u82af\u8fb9\u754c");
    model.component("comp1").selection("adj1").set("input", new String[]{"int1"});
    model.component("comp1").selection().duplicate("adj2", "adj1");
    model.component("comp1").selection("adj2").label("\u975e PML \u5305\u5c42\u8fb9\u754c");
    model.component("comp1").selection("adj2").set("input", new String[]{"int2"});
    model.component("comp1").selection().create("int3", "Intersection");
    model.component("comp1").selection("int3").label("\u975e PML \u7ea4\u82af-\u5305\u5c42\u8fb9\u754c");
    model.component("comp1").selection("int3").set("entitydim", 1);
    model.component("comp1").selection("int3").set("input", new String[]{"adj1", "adj2"});

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u5305\u5c42");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"n_clad"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u7ea4\u82af");
    model.component("comp1").material("mat2").selection().named("geom1_csel1_dom");
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").set("n", new String[]{"n_core"});

    model.component("comp1").physics("ewbe").create("port1", "Port", 1);
    model.component("comp1").physics("ewbe").feature("port1").selection().named("geom1_csel4_bnd");
    model.component("comp1").physics("ewbe").feature("port1").set("PortType", "Numeric");
    model.component("comp1").physics("ewbe").feature("port1").set("PortSlit", true);
    model.component("comp1").physics("ewbe").feature("port1").set("SlitType", "DomainBacked");
    model.component("comp1").physics("ewbe").feature("port1").set("PortOrientation", "ReversePort");
    model.component("comp1").physics("ewbe").create("port2", "Port", 1);
    model.component("comp1").physics("ewbe").feature("port2").selection().named("geom1_csel6_bnd");
    model.component("comp1").physics("ewbe").feature("port2").set("PortType", "Numeric");
    model.component("comp1").physics("ewbe").feature("port2").set("PortSlit", true);
    model.component("comp1").physics("ewbe").feature("port2").set("SlitType", "DomainBacked");
    model.component("comp1").physics("ewbe").feature("port2").set("PortOrientation", "ReversePort");
    model.component("comp1").physics("ewfd").feature().copy("port1", "ewbe/port1");
    model.component("comp1").physics("ewfd").feature().copy("port2", "ewbe/port2");
    model.component("comp1").physics("g").selection().named("int1");
    model.component("comp1").physics("g").prop("Units").set("DependentVariableQuantity", "length");
    model.component("comp1").physics("g").prop("Units").setIndex("CustomSourceTermUnit", "m^-1", 0, 0);
    model.component("comp1").physics("g").feature("gfeq1").setIndex("f", 0, 0);
    model.component("comp1").physics("g").feature("gfeq1").setIndex("da", 0, 0);
    model.component("comp1").physics("g").create("dir1", "DirichletBoundary", 1);
    model.component("comp1").physics("g").feature("dir1").selection().named("geom1_csel4_bnd");
    model.component("comp1").physics("g").feature("dir1").setIndex("r", "-d_straight_wg/2", 0);
    model.component("comp1").physics("g").create("flux1", "FluxBoundary", 1);
    model.component("comp1").physics("g").feature("flux1").selection().named("geom1_csel6_bnd");
    model.component("comp1").physics("g").feature("flux1").setIndex("g", 1, 0);
    model.component("comp1").physics("g").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("g").feature("ge1").setIndex("name", "u0", 0, 0);
    model.component("comp1").physics("g").feature("ge1").set("DependentVariableQuantity", "length");
    model.component("comp1").physics("g").create("cons1", "Constraint", 1);
    model.component("comp1").physics("g").feature("cons1").selection().named("geom1_csel6_bnd");
    model.component("comp1").physics("g").feature("cons1").setIndex("R", "u0-u", 0);
    model.component("comp1").physics("g2").selection().named("int2");
    model.component("comp1").physics("g2").prop("Units").set("DependentVariableQuantity", "length");
    model.component("comp1").physics("g2").prop("Units").setIndex("CustomSourceTermUnit", "m^-1", 0, 0);
    model.component("comp1").physics("g2").feature("gfeq1").setIndex("f", 0, 0);
    model.component("comp1").physics("g2").feature("gfeq1").setIndex("da", 0, 0);
    model.component("comp1").physics("g2").feature().copy("dir1", "g/dir1");
    model.component("comp1").physics("g2").feature().copy("flux1", "g/flux1");
    model.component("comp1").physics("g2").feature().copy("cons1", "g/cons1");
    model.component("comp1").physics("g2").feature("cons1").setIndex("R", "u0-u2", 0);
    model.component("comp1").physics("g2").feature().duplicate("dir2", "dir1");
    model.component("comp1").physics("g2").feature("dir2").selection().named("int3");
    model.component("comp1").physics("g2").feature("dir2").setIndex("r", "u", 0);

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().named("geom1_unisel1");
    model.component("comp1").coordSystem("pml1").set("wavelengthSourceType", "userDefined");
    model.component("comp1").coordSystem("pml1").set("typicalWavelength", "2*pi/ewbe.beta_1");
    model.component("comp1").coordSystem().duplicate("pml2", "pml1");
    model.component("comp1").coordSystem("pml2").set("typicalWavelength", "2*pi/ewfd.beta_1");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("\u7ea4\u82af\u5149\u7a0b\u957f\u5ea6");
    model.component("comp1").variable("var1").selection().geom("geom1", 2);
    model.component("comp1").variable("var1").selection().named("int1");
    model.component("comp1").variable("var1").set("s", "u");
    model.component("comp1").variable("var1").descr("s", "\u5149\u7a0b\u957f\u5ea6");
    model.component("comp1").variable().duplicate("var2", "var1");
    model.component("comp1").variable("var2").label("\u5305\u5c42\u5149\u7a0b\u957f\u5ea6");
    model.component("comp1").variable("var2").selection().named("int2");
    model.component("comp1").variable("var2").set("s", "u2");
    model.component("comp1").variable().create("var3");
    model.component("comp1").variable("var3").label("\u5de6\u4fa7 PML");
    model.component("comp1").variable("var3").selection().geom("geom1", 2);
    model.component("comp1").variable("var3").selection().named("geom1_csel5_dom");
    model.component("comp1").variable("var3").set("phi", "ewbe.beta_1*pml1.x*1[m]");
    model.component("comp1").variable("var3").descr("phi", "\u76f8\u4f4d");
    model.component("comp1").variable("var3").set("k1x", "ewbe.beta_1");
    model.component("comp1").variable("var3")
         .descr("k1x", "\u6ce2\u77e2\uff0c\u7b2c\u4e00\u4e2a\u6ce2\uff0cx \u5206\u91cf");
    model.component("comp1").variable("var3").set("k1y", "0[rad/m]");
    model.component("comp1").variable("var3")
         .descr("k1y", "\u6ce2\u77e2\uff0c\u7b2c\u4e00\u4e2a\u6ce2\uff0cy \u5206\u91cf");
    model.component("comp1").variable().duplicate("var4", "var3");
    model.component("comp1").variable("var4").label("\u975e PML");
    model.component("comp1").variable("var4").selection().named("geom1_csel3_dom");
    model.component("comp1").variable("var4").set("phi", "ewbe.beta_1*s");
    model.component("comp1").variable("var4").set("k1x", "d(phi,x)");
    model.component("comp1").variable("var4").set("k1y", "d(phi,y)");
    model.component("comp1").variable().duplicate("var5", "var4");
    model.component("comp1").variable("var5").label("\u53f3\u4fa7 PML");
    model.component("comp1").variable("var5").selection().named("geom1_csel7_dom");
    model.component("comp1").variable("var5")
         .set("phi", "ewbe.beta_1*u0+ewbe.beta_1*(pml1.x-d_bent_wg-d_straight_wg/2)");
    model.component("comp1").variable("var5").set("k1x", "ewbe.beta_1");
    model.component("comp1").variable("var5").set("k1y", "0[rad/m]");

    model.component("comp1").physics("ewbe").prop("components").set("components", "outofplane");
    model.component("comp1").physics("ewbe").prop("WaveVector").set("dirCount", "UniDirectionality");
    model.component("comp1").physics("ewbe").prop("WaveVector").set("PhaseSpec", "UserDefined");
    model.component("comp1").physics("ewbe").prop("WaveVector").set("phase1", "phi");
    model.component("comp1").physics("ewbe").prop("UserDefinedWaveVectorSpecification")
         .set("k1Phase", new String[]{"k1x", "k1y", "0"});

    model.component("comp1").mesh("mesh1").label("\u6620\u5c04\u7f51\u683c");
    model.component("comp1").mesh("mesh1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("dis1").label("PML \u5206\u5e03");
    model.component("comp1").mesh("mesh1").feature("dis1").selection().set(2, 29);
    model.component("comp1").mesh("mesh1").feature("dis1").set("numelem", 10);
    model.component("comp1").mesh("mesh1").feature("size").label("\u6cbf\u6ce2\u5bfc\u7684\u5c3a\u5bf8");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "2*lda0");
    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size1").label("\u7ea4\u82af\u5c3a\u5bf8");
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("size1").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", "w_core/3");
    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").label("\u5de6\u4fa7\u8fb9");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(1, 3, 5);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").set("adjustedgdistr", true);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("bma").set("modeFreq", "f0");
    model.study("std1").feature("bma").set("shiftactive", true);
    model.study("std1").feature("bma").set("shift", "n_core");
    model.study("std1").feature("bma").setSolveFor("/physics/ewfd", false);
    model.study("std1").feature("bma").setSolveFor("/physics/g", false);
    model.study("std1").feature("bma").setSolveFor("/physics/g2", false);
    model.study("std1").feature().duplicate("bma1", "bma");
    model.study("std1").feature().move("bma1", 1);
    model.study("std1").feature("bma1").set("PortName", "2");
    model.study("std1").feature("freq").set("plist", "f0");
    model.study("std1").feature("freq").set("useadvanceddisable", true);
    model.study("std1").feature("freq").set("disabledcoordinatesystems", new String[]{"pml2"});
    model.study("std1").feature("freq").setSolveFor("/physics/ewfd", false);
    model.study("std1").feature("freq").setSolveFor("/physics/g", false);
    model.study("std1").feature("freq").setSolveFor("/physics/g2", false);
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature().move("stat", 0);
    model.study("std1").feature("stat").setSolveFor("/physics/g2", false);
    model.study("std1").feature().duplicate("stat1", "stat");
    model.study("std1").feature().move("stat1", 1);
    model.study("std1").feature("stat1").setSolveFor("/physics/g", false);
    model.study("std1").feature("stat1").setSolveFor("/physics/g2", true);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u573a (ewbe)");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u7535\u573a");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1")
         .label("\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387 (ewbe)");
    model.result().numerical("gev1").set("data", "dset1");
    model.result().numerical("gev1")
         .set("expr", new String[]{"ewbe.Rport_1", "ewbe.Rtotal", "ewbe.Tport_2", "ewbe.Ttotal", "ewbe.RTtotal", "ewbe.Atotal"});
    model.result().table().create("tbl1", "Table");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").run();
    model.result().numerical("gev1").setResult();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").label("\u7535\u6a21\u573a\uff0c\u7aef\u53e3 1 (ewbe)");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").selection().set(8, 10, 12);
    model.result("pg2").feature("lngr1").set("expr", "root.comp1.ewbe.normEbm_1");
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").feature("lngr1").set("legendmethod", "evaluated");
    model.result("pg2").feature("lngr1")
         .set("legendpattern", "\u6709\u6548\u6a21\u5f0f\u6298\u5c04\u7387: eval(comp1.ewbe.neff_1)");
    model.result("pg2").feature("lngr1").set("legendexprprecision", 5);
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").label("\u7535\u6a21\u573a\uff0c\u7aef\u53e3 2 (ewbe)");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").set("data", "dset1");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").selection().set(28, 30, 32);
    model.result("pg3").feature("lngr1").set("expr", "root.comp1.ewbe.normEbm_2");
    model.result("pg3").feature("lngr1").set("legend", true);
    model.result("pg3").feature("lngr1").set("legendmethod", "evaluated");
    model.result("pg3").feature("lngr1")
         .set("legendpattern", "\u6709\u6548\u6a21\u5f0f\u6298\u5c04\u7387: eval(comp1.ewbe.neff_2)");
    model.result("pg3").feature("lngr1").set("legendexprprecision", 5);
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").set("data", "dset1");
    model.result().numerical("gev2").set("expr", new String[]{"u0"});
    model.result().numerical("gev2").set("descr", new String[]{"\u72b6\u6001\u53d8\u91cf u0"});
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").label("\u4e00\u822c\u5f62\u5f0f\u504f\u5fae\u5206\u65b9\u7a0b");
    model.result("pg4").feature("surf1").set("expr", "u");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").setIndex("looplevel", 1, 0);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").label("\u4e00\u822c\u5f62\u5f0f\u504f\u5fae\u5206\u65b9\u7a0b 2");
    model.result("pg5").feature("surf1").set("expr", "u2");
    model.result("pg1").run();
    model.result("pg1").selection().geom("geom1", 2);
    model.result("pg1").selection().named("geom1_csel3_dom");
    model.result("pg1").set("applyselectiontodatasetedges", true);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").create("hght1", "Height");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("hght1").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("hght1").set("scale", "5e-10");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg1").run();
    model.result("pg4").set("applyselectiontodatasetedges", false);
    model.result("pg4").run();
    model.result("pg4").label("\u76f8\u4f4d");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "phi");
    model.result("pg4").run();
    model.result("pg4").create("con1", "Contour");
    model.result("pg4").feature("con1").set("expr", "phi");
    model.result("pg4").feature("con1").set("coloring", "uniform");
    model.result("pg4").feature("con1").set("color", "white");
    model.result("pg4").feature("con1").set("colorlegend", false);
    model.result("pg4").run();
    model.result("pg4").create("arws1", "ArrowSurface");
    model.result("pg4").feature("arws1").set("expr", new String[]{"k1x", "k1y"});
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").label("\u5927\u5c0f\uff0c\u7b2c\u4e00\u4e2a\u6ce2");
    model.result("pg5").selection().geom("geom1", 2);
    model.result("pg5").selection().geom("geom1", 2);
    model.result("pg5").selection().set(4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
    model.result("pg5").selection().named("geom1_csel3_dom");
    model.result("pg5").set("applyselectiontodatasetedges", true);
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("expr", "E1z");
    model.result("pg5").run();

    model.component("comp1").physics("ewfd").prop("components").set("components", "outofplane");

    return model;
  }

  public static Model run2(Model model) {

    model.component("comp1").mesh().create("mesh2");
    model.component("comp1").mesh("mesh2").label("\u4e09\u89d2\u5f62\u7f51\u683c");
    model.component("comp1").mesh("mesh2").contribute("physics/ewbe", false);
    model.component("comp1").mesh("mesh2").contribute("physics/g", false);
    model.component("comp1").mesh("mesh2").contribute("physics/g2", false);
    model.component("comp1").mesh("mesh2").run();

    model.study().create("std2");
    model.study("std2").feature().copy("bma", "std1/bma");
    model.study("std2").feature().copy("bma1", "std1/bma1");
    model.study("std2").feature().copy("freq", "std1/freq");
    model.study("std2").feature("bma").setSolveFor("/physics/ewbe", false);
    model.study("std2").feature("bma").setSolveFor("/physics/ewfd", true);
    model.study("std2").feature("bma").setEntry("mesh", "geom1", "mesh2");
    model.study("std2").feature("bma1").setSolveFor("/physics/ewbe", false);
    model.study("std2").feature("bma1").setSolveFor("/physics/ewfd", true);
    model.study("std2").feature("bma1").setEntry("mesh", "geom1", "mesh2");
    model.study("std2").feature("freq").set("disabledcoordinatesystems", new String[]{"pml1"});
    model.study("std2").feature("freq").setSolveFor("/physics/ewbe", false);
    model.study("std2").feature("freq").setSolveFor("/physics/ewfd", true);
    model.study("std2").feature("freq").setEntry("mesh", "geom1", "mesh2");
    model.study("std2").createAutoSequences("all");

    model.sol("sol6").runAll();

    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").label("\u7535\u573a (ewfd)");
    model.result("pg6").set("data", "dset6");
    model.result("pg6").setIndex("looplevel", 1, 0);
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "ewfd.normE");
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result().numerical().create("gev3", "EvalGlobal");
    model.result().numerical("gev3")
         .label("\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387 (ewfd)");
    model.result().numerical("gev3").set("data", "dset6");
    model.result().numerical("gev3")
         .set("expr", new String[]{"ewfd.Rport_1", "ewfd.Rtotal", "ewfd.Tport_2", "ewfd.Ttotal", "ewfd.RTtotal", "ewfd.Atotal"});
    model.result().table().create("tbl2", "Table");
    model.result().numerical("gev3").set("table", "tbl2");
    model.result().numerical("gev3").run();
    model.result().numerical("gev3").setResult();
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").label("\u7535\u6a21\u573a\uff0c\u7aef\u53e3 1 (ewfd)");
    model.result("pg7").set("showlegendsmaxmin", true);
    model.result("pg7").set("data", "dset6");
    model.result("pg7").create("line1", "Line");
    model.result("pg7").feature("line1").set("expr", "root.comp1.ewfd.normEbm_1");
    model.result("pg7").feature("line1").set("colortable", "RainbowLight");
    model.result("pg7").feature("line1").set("linetype", "tube");
    model.result("pg7").feature("line1").create("hght1", "Height");
    model.result("pg7").create("ann1", "Annotation");
    model.result("pg7").feature("ann1").set("posxexpr", "ewfd.aveport1(x)");
    model.result("pg7").feature("ann1").set("posyexpr", "ewfd.aveport1(y)");
    model.result("pg7").feature("ann1").set("backgroundcolor", "fromtheme");
    model.result("pg7").feature("ann1")
         .set("text", "\u6709\u6548\u6a21\u5f0f\u6298\u5c04\u7387: eval(comp1.ewfd.neff_1)");
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").label("\u7535\u6a21\u573a\uff0c\u7aef\u53e3 2 (ewfd)");
    model.result("pg8").set("showlegendsmaxmin", true);
    model.result("pg8").set("data", "dset6");
    model.result("pg8").create("line1", "Line");
    model.result("pg8").feature("line1").set("expr", "root.comp1.ewfd.normEbm_2");
    model.result("pg8").feature("line1").set("colortable", "RainbowLight");
    model.result("pg8").feature("line1").set("linetype", "tube");
    model.result("pg8").feature("line1").create("hght1", "Height");
    model.result("pg8").create("ann1", "Annotation");
    model.result("pg8").feature("ann1").set("posxexpr", "ewfd.aveport2(x)");
    model.result("pg8").feature("ann1").set("posyexpr", "ewfd.aveport2(y)");
    model.result("pg8").feature("ann1").set("backgroundcolor", "fromtheme");
    model.result("pg8").feature("ann1")
         .set("text", "\u6709\u6548\u6a21\u5f0f\u6298\u5c04\u7387: eval(comp1.ewfd.neff_2)");
    model.result("pg6").set("applyselectiontodatasetedges", false);
    model.result("pg6").run();
    model.result("pg6").selection().geom("geom1", 2);
    model.result("pg6").selection().named("geom1_csel3_dom");
    model.result("pg6").set("applyselectiontodatasetedges", true);
    model.result("pg6").run();
    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").set("applyselectiontodatasetedges", false);
    model.result("pg9").run();
    model.result("pg9").label("\u573a\u6bd4\u8f83\uff0c\u7ebf\u6027\u6bd4\u4f8b\u5c3a");
    model.result("pg9").selection().geom("geom1", 2);
    model.result("pg9").selection().named("geom1_csel3_dom");
    model.result("pg9").set("applyselectiontodatasetedges", true);
    model.result("pg9").set("showlegendsmaxmin", true);
    model.result("pg9").set("plotarrayenable", true);
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("arraydim", "1");
    model.result("pg9").feature().duplicate("surf2", "surf1");
    model.result("pg9").feature("surf2").set("arraydim", "1");
    model.result("pg9").run();
    model.result("pg9").feature("surf2").set("data", "dset6");
    model.result("pg9").feature("surf2").set("expr", "ewfd.normE");
    model.result("pg9").feature("surf2").set("inheritplot", "surf1");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result().duplicate("pg10", "pg9");
    model.result("pg10").run();
    model.result("pg10").label("\u573a\u6bd4\u8f83\uff0c\u5bf9\u6570\u6bd4\u4f8b\u5c3a");
    model.result("pg10").feature("surf1").set("arraydim", "1");
    model.result("pg10").run();
    model.result("pg10").feature("surf1").set("expr", "20*log10(ewbe.normE)");
    model.result("pg10").feature("surf2").set("arraydim", "1");
    model.result("pg10").run();
    model.result("pg10").feature("surf2").set("expr", "20*log10(ewfd.normE)");
    model.result("pg10").run();

    model.title("\u6ce2\u5bfc S \u5f2f");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u6a21\u62df\u5bfc\u6ce2\u5728\u4ecb\u8d28 S \u5f2f\u66f2\u5149\u6ce2\u5bfc\u4e2d\u7684\u4f20\u64ad\uff0c\u901a\u8fc7\u6c42\u89e3\u9644\u52a0\u7684\u504f\u5fae\u5206\u65b9\u7a0b\uff0c\u5bf9\u201c\u7535\u78c1\u6ce2\uff0c\u6ce2\u675f\u5305\u7edc\u201d\u63a5\u53e3\u6240\u9700\u7684\u76f8\u4f4d\u8fd1\u4f3c\u8fdb\u884c\u4e86\u6570\u503c\u8ba1\u7b97\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();

    model.label("waveguide_s_bend.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
