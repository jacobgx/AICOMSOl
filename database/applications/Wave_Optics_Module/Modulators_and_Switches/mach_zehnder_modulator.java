/*
 * mach_zehnder_modulator.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:40 by COMSOL 6.3.0.290. */
public class mach_zehnder_modulator {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Modulators_and_Switches");

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

    model.param().label("\u901a\u7528\u53c2\u6570");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("lda0", "1.55[um]", "Wavelength");
    model.param().set("f0", "c_const/lda0", "Frequency");
    model.param().set("n_clad", "2.2", "Cladding refractive index");
    model.param().set("n_core", "2.22", "Core refractive index");
    model.param().set("hx", "lda0/(6*n_core)/(1-cos(alpha))", "Maximum element size in x-direction");
    model.param().set("hy", "lda0/(6*n_core)/sin(alpha)", "Maximum element size in y-direction");
    model.param().set("r13", "30[pm/V]", "Electro-optic coefficient");
    model.param().set("V0", "100[V]", "Applied voltage");
    model.param().set("epsr", "35", "Low-frequency relative permittivity");
    model.param().create("par2");
    model.param("par2").label("\u51e0\u4f55\u53c2\u6570");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("w", "2[um]", "Waveguide width");
    model.param("par2").set("w_tot", "30[um]", "Total waveguide width");
    model.param("par2").set("d0", "2*lda0", "Initial straight waveguide");
    model.param("par2").set("dy_bend", "0.6*w_tot", "Total displacement in y-direction at S-bend");
    model.param("par2").set("r0", "100[um]", "Bend radius");
    model.param("par2").set("alpha", "acos((r0-dy_bend/2)/r0)", "Bend angle");
    model.param("par2").set("dx_bend", "2*r0*sin(alpha)", "Total length in the x-direction for S-bend");
    model.param("par2").set("d_cpl", "90[um]", "Length of straight directional coupler waveguides");
    model.param("par2").set("d_dc", "2*dx_bend+d_cpl", "Total length of directional coupler");
    model.param("par2").set("d_mz", "100[um]", "Length of Mach-Zehnder waveguides");
    model.param("par2").set("dy_wg", "3[um]", "Distance between directional coupler waveguides");
    model.param("par2").set("d_cc", "dy_wg+w", "Directional coupler core center separation");
    model.param("par2").set("d_pcc", "2*dy_bend+d_cc", "Port core center separation");

    model.geom()
         .load(new String[]{"part1"}, "Wave_Optics_Module\\Slab_Waveguides\\slab_waveguide_s_bend_directional_coupler.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "core_width", "w");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "cladding_width", "w_tot");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "port_core_separation", "d_pcc");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "coupler_core_separation", "d_cc");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "coupler_length", "d_cpl");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "element_length", "d_dc");
    model.component("comp1").geom("geom1").feature("pi1").set("rot", -90);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u82af\u5c42");
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u5305\u5c42");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetodom", "pi1_csel1.dom", "csel1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetodom", "pi1_csel2.dom", "csel2");
    model.component("comp1").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel3").label("\u6a2a\u5411\u5916\u56f4");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_difsel2", "csel3");
    model.geom()
         .load(new String[]{"part2"}, "Wave_Optics_Module\\Slab_Waveguides\\slab_waveguide_straight.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi2").set("part", "part2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "core_width", "w");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "cladding_width", "w_tot");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "element_length", "d0");
    model.component("comp1").geom("geom1").feature("pi2").set("displ", new String[]{"-d0", "0"});
    model.component("comp1").geom("geom1").feature("pi2").set("rot", -90);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetodom", "pi2_sel1", "csel1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetodom", "pi2_sel2", "csel2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetobnd", "pi2_difsel1", "csel3");
    model.component("comp1").geom("geom1").feature().duplicate("pi3", "pi2");
    model.component("comp1").geom("geom1").feature("pi2").set("selcontributetoobj", new String[]{});
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepobj", new String[]{});
    model.component("comp1").geom("geom1").feature("pi2").set("selcontributetopnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi2").set("selkeeppnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi2").set("selshowpnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi2")
         .set("selcontributetobnd", new String[]{"none", "none", "none", "none", "none", "none", "none", "csel3"});
    model.component("comp1").geom("geom1").feature("pi2")
         .set("selkeepbnd", new String[]{"off", "off", "off", "off", "off", "off", "off", "off"});
    model.component("comp1").geom("geom1").feature("pi2")
         .set("selshowbnd", new String[]{"on", "on", "on", "on", "on", "on", "on", "on"});
    model.component("comp1").geom("geom1").feature("pi2")
         .set("selcontributetodom", new String[]{"csel1", "csel2", "none"});
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepdom", new String[]{"off", "off", "off"});
    model.component("comp1").geom("geom1").feature("pi2").set("selshowdom", new String[]{"on", "on", "on"});
    model.component("comp1").geom("geom1").feature("pi3").set("displ", new String[]{"-d0", "-d_pcc"});
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
         .set("selkeepbnd", new String[]{"off", "off", "off", "off", "off", "off", "off", "off"});
    model.component("comp1").geom("geom1").feature("pi2")
         .set("selshowbnd", new String[]{"on", "on", "on", "on", "on", "on", "on", "on"});
    model.component("comp1").geom("geom1").feature("pi2")
         .set("selcontributetodom", new String[]{"csel1", "csel2", "none"});
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepdom", new String[]{"off", "off", "off"});
    model.component("comp1").geom("geom1").feature("pi2").set("selshowdom", new String[]{"on", "on", "on"});
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "element_length", "d_mz");
    model.component("comp1").geom("geom1").feature("pi4").set("displ", new String[]{"d_dc", "0"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature().duplicate("pi5", "pi4");
    model.component("comp1").geom("geom1").feature("pi4").set("selcontributetoobj", new String[]{});
    model.component("comp1").geom("geom1").feature("pi4").set("selkeepobj", new String[]{});
    model.component("comp1").geom("geom1").feature("pi4").set("selcontributetopnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi4").set("selkeeppnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi4").set("selshowpnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi4")
         .set("selcontributetobnd", new String[]{"none", "none", "none", "none", "none", "none", "none", "csel3"});
    model.component("comp1").geom("geom1").feature("pi4")
         .set("selkeepbnd", new String[]{"off", "off", "off", "off", "off", "off", "off", "off"});
    model.component("comp1").geom("geom1").feature("pi4")
         .set("selshowbnd", new String[]{"on", "on", "on", "on", "on", "on", "on", "on"});
    model.component("comp1").geom("geom1").feature("pi4")
         .set("selcontributetodom", new String[]{"csel1", "csel2", "none"});
    model.component("comp1").geom("geom1").feature("pi4").set("selkeepdom", new String[]{"off", "off", "off"});
    model.component("comp1").geom("geom1").feature("pi4").set("selshowdom", new String[]{"on", "on", "on"});
    model.component("comp1").geom("geom1").feature("pi5").set("displ", new String[]{"d_dc", "-d_pcc"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature().duplicate("pi6", "pi1");
    model.component("comp1").geom("geom1").feature("pi1").set("selcontributetoobj", new String[]{});
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepobj", new String[]{});
    model.component("comp1").geom("geom1").feature("pi1").set("selcontributetopnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi1").set("selkeeppnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi1").set("selshowpnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi1")
         .set("selcontributetobnd", new String[]{"none", "none", "none", "none", "none", "none", "none", "none", "none", "none", 
         "none", "none", "none", "csel3"});
    model.component("comp1").geom("geom1").feature("pi1")
         .set("selkeepbnd", new String[]{"off", "off", "off", "off", "off", "off", "off", "off", "off", "off", 
         "off", "off", "off", "off"});
    model.component("comp1").geom("geom1").feature("pi1")
         .set("selshowbnd", new String[]{"on", "on", "on", "on", "on", "on", "on", "on", "on", "on", 
         "on", "on", "on", "on"});
    model.component("comp1").geom("geom1").feature("pi1")
         .set("selcontributetodom", new String[]{"csel1", "csel2", "none"});
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepdom", new String[]{"off", "off", "off"});
    model.component("comp1").geom("geom1").feature("pi1").set("selshowdom", new String[]{"on", "on", "on"});
    model.component("comp1").geom("geom1").feature("pi6").set("displ", new String[]{"d_dc+d_mz", "0"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature().duplicate("pi7", "pi2");
    model.component("comp1").geom("geom1").feature("pi2").set("selcontributetoobj", new String[]{});
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepobj", new String[]{});
    model.component("comp1").geom("geom1").feature("pi2").set("selcontributetopnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi2").set("selkeeppnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi2").set("selshowpnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi2")
         .set("selcontributetobnd", new String[]{"none", "none", "none", "none", "none", "none", "none", "csel3"});
    model.component("comp1").geom("geom1").feature("pi2")
         .set("selkeepbnd", new String[]{"off", "off", "off", "off", "off", "off", "off", "off"});
    model.component("comp1").geom("geom1").feature("pi2")
         .set("selshowbnd", new String[]{"on", "on", "on", "on", "on", "on", "on", "on"});
    model.component("comp1").geom("geom1").feature("pi2")
         .set("selcontributetodom", new String[]{"csel1", "csel2", "none"});
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepdom", new String[]{"off", "off", "off"});
    model.component("comp1").geom("geom1").feature("pi2").set("selshowdom", new String[]{"on", "on", "on"});
    model.component("comp1").geom("geom1").feature("pi7").set("displ", new String[]{"2*d_dc+d_mz", "0"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature().duplicate("pi8", "pi7");
    model.component("comp1").geom("geom1").feature("pi7").set("selcontributetoobj", new String[]{});
    model.component("comp1").geom("geom1").feature("pi7").set("selkeepobj", new String[]{});
    model.component("comp1").geom("geom1").feature("pi7").set("selcontributetopnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi7").set("selkeeppnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi7").set("selshowpnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi7")
         .set("selcontributetobnd", new String[]{"none", "none", "none", "none", "none", "none", "none", "csel3"});
    model.component("comp1").geom("geom1").feature("pi7")
         .set("selkeepbnd", new String[]{"off", "off", "off", "off", "off", "off", "off", "off"});
    model.component("comp1").geom("geom1").feature("pi7")
         .set("selshowbnd", new String[]{"on", "on", "on", "on", "on", "on", "on", "on"});
    model.component("comp1").geom("geom1").feature("pi7")
         .set("selcontributetodom", new String[]{"csel1", "csel2", "none"});
    model.component("comp1").geom("geom1").feature("pi7").set("selkeepdom", new String[]{"off", "off", "off"});
    model.component("comp1").geom("geom1").feature("pi7").set("selshowdom", new String[]{"on", "on", "on"});
    model.component("comp1").geom("geom1").feature("pi8").setIndex("displ", "-d_pcc", 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").selection().create("csel4", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel4").label("\u7aef\u53e3 1");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetobnd", "pi3_unisel2", "csel4");
    model.component("comp1").geom("geom1").selection().create("csel5", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel5").label("\u7aef\u53e3 2");
    model.component("comp1").geom("geom1").feature("pi7").setEntry("selcontributetobnd", "pi7_unisel3", "csel5");
    model.component("comp1").geom("geom1").selection().create("csel6", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel6").label("\u7aef\u53e3 3");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetobnd", "pi2_unisel2", "csel6");
    model.component("comp1").geom("geom1").selection().create("csel7", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel7").label("\u7aef\u53e3 4");
    model.component("comp1").geom("geom1").feature("pi8").setEntry("selcontributetobnd", "pi8_unisel3", "csel7");
    model.component("comp1").geom("geom1").selection().create("csel8", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel8")
         .label("\u4e0a\u9a6c\u8d6b-\u66fe\u5fb7\u5c14\u6ce2\u5bfc\u672b\u7aef");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("selcontributetobnd", "pi4_unisel3", "csel8");
    model.component("comp1").geom("geom1").selection().create("csel9", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel9")
         .label("\u4e0b\u9a6c\u8d6b-\u66fe\u5fb7\u5c14\u6ce2\u5bfc\u672b\u7aef");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("selcontributetobnd", "pi5_unisel3", "csel9");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("\u8fb9\u7f51\u683c");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"csel4", "csel6"});
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u5305\u5c42");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"n_clad"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u82af\u5c42");
    model.component("comp1").material("mat2").selection().named("geom1_csel1_dom");
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").set("n", new String[]{"n_core"});

    model.component("comp1").physics("ewbe").prop("components").set("components", "outofplane");
    model.component("comp1").physics("ewbe").prop("WaveVector").set("dirCount", "UniDirectionality");
    model.component("comp1").physics("ewbe").prop("WaveVector").set("k1", new String[]{"ewbe.beta_1", "0", "0"});
    model.component("comp1").physics("ewbe").create("port1", "Port", 1);
    model.component("comp1").physics("ewbe").feature("port1").selection().named("geom1_csel4_bnd");
    model.component("comp1").physics("ewbe").feature("port1").set("PortType", "Numeric");
    model.component("comp1").physics("ewbe").create("port2", "Port", 1);
    model.component("comp1").physics("ewbe").feature("port2").selection().named("geom1_csel5_bnd");
    model.component("comp1").physics("ewbe").feature("port2").set("PortType", "Numeric");
    model.component("comp1").physics("ewbe").create("port3", "Port", 1);
    model.component("comp1").physics("ewbe").feature("port3").selection().named("geom1_csel6_bnd");
    model.component("comp1").physics("ewbe").feature("port3").set("PortType", "Numeric");
    model.component("comp1").physics("ewbe").create("port4", "Port", 1);
    model.component("comp1").physics("ewbe").feature("port4").selection().named("geom1_csel7_bnd");
    model.component("comp1").physics("ewbe").feature("port4").set("PortType", "Numeric");
    model.component("comp1").physics("ewbe").create("sctr1", "Scattering", 1);
    model.component("comp1").physics("ewbe").feature("sctr1").selection().named("geom1_csel3_bnd");

    model.component("comp1").mesh("mesh1").contribute("physics/ewbe", false);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature().remove("ftri1");
    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().named("geom1_unisel1");
    model.component("comp1").mesh("mesh1").feature("edg1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmax", "hy");
    model.component("comp1").mesh("mesh1").feature("edg1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size2").selection().set(3, 10);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size2").set("hmax", "min(hy,w/4)");
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").set("adjustedgdistr", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmax", "hx");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("bma").set("modeFreq", "f0");
    model.study("std1").feature("bma").set("shiftactive", true);
    model.study("std1").feature("bma").set("shift", "n_core");
    model.study("std1").feature().duplicate("bma1", "bma");
    model.study("std1").feature("bma1").set("PortName", "2");
    model.study("std1").feature().duplicate("bma2", "bma1");
    model.study("std1").feature("bma2").set("PortName", "3");
    model.study("std1").feature().duplicate("bma3", "bma2");
    model.study("std1").feature("bma3").set("PortName", "4");
    model.study("std1").feature("freq").set("plist", "f0");
    model.study("std1").feature().move("freq", 2);
    model.study("std1").feature().move("freq", 3);
    model.study("std1").feature().move("freq", 4);
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
         .set("expr", new String[]{"ewbe.Rport_1", "ewbe.Rtotal", "ewbe.Tport_2", "ewbe.Tport_3", "ewbe.Tport_4", "ewbe.Ttotal", "ewbe.RTtotal", "ewbe.Atotal"});
    model.result().table().create("tbl1", "Table");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").run();
    model.result().numerical("gev1").setResult();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").label("\u7535\u6a21\u573a\uff0c\u7aef\u53e3 1 (ewbe)");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").selection().set(1, 3, 5);
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
    model.result("pg3").feature("lngr1").selection().set(152, 153, 154);
    model.result("pg3").feature("lngr1").set("expr", "root.comp1.ewbe.normEbm_2");
    model.result("pg3").feature("lngr1").set("legend", true);
    model.result("pg3").feature("lngr1").set("legendmethod", "evaluated");
    model.result("pg3").feature("lngr1")
         .set("legendpattern", "\u6709\u6548\u6a21\u5f0f\u6298\u5c04\u7387: eval(comp1.ewbe.neff_2)");
    model.result("pg3").feature("lngr1").set("legendexprprecision", 5);
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").label("\u7535\u6a21\u573a\uff0c\u7aef\u53e3 3 (ewbe)");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").set("data", "dset1");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").selection().set(8, 10, 12);
    model.result("pg4").feature("lngr1").set("expr", "root.comp1.ewbe.normEbm_3");
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("legendmethod", "evaluated");
    model.result("pg4").feature("lngr1")
         .set("legendpattern", "\u6709\u6548\u6a21\u5f0f\u6298\u5c04\u7387: eval(comp1.ewbe.neff_3)");
    model.result("pg4").feature("lngr1").set("legendexprprecision", 5);
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").label("\u7535\u6a21\u573a\uff0c\u7aef\u53e3 4 (ewbe)");
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").set("data", "dset1");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").selection().set(149, 150, 151);
    model.result("pg5").feature("lngr1").set("expr", "root.comp1.ewbe.normEbm_4");
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").feature("lngr1").set("legendmethod", "evaluated");
    model.result("pg5").feature("lngr1")
         .set("legendpattern", "\u6709\u6548\u6a21\u5f0f\u6298\u5c04\u7387: eval(comp1.ewbe.neff_4)");
    model.result("pg5").feature("lngr1").set("legendexprprecision", 5);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "alpha", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "rad", 0);
    model.study("std1").feature("param").setIndex("pname", "alpha", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "rad", 0);
    model.study("std1").feature("param").setIndex("pname", "r0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(0.1[mm],0.4[mm],2.5[mm])", 0);
    model.study("std1").feature("param").setIndex("punit", "mm", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol6");
    model.sol("sol6").study("std1");
    model.sol("sol6").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol6");
    model.batch("p1").run("compute");

    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").label("\u7535\u573a (ewbe) 1");
    model.result("pg6").set("data", "dset6");
    model.result("pg6").setIndex("looplevel", 1, 0);
    model.result("pg6").setIndex("looplevel", 7, 1);
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").label("\u7535\u573a");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").set("data", "dset6");
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("unit", new String[]{"", "", "", "", "", "", ""});
    model.result("pg7").feature("glob1")
         .set("expr", new String[]{"ewbe.Rport_1", "ewbe.Tport_2", "ewbe.Tport_3", "ewbe.Tport_4", "ewbe.Ttotal", "ewbe.RTtotal", "ewbe.Atotal"});
    model.result("pg7").feature("glob1")
         .set("descr", new String[]{"\u53cd\u5c04\u7387\uff0c\u7aef\u53e3 1", "\u900f\u5c04\u7387\uff0c\u7aef\u53e3 2", "\u900f\u5c04\u7387\uff0c\u7aef\u53e3 3", "\u900f\u5c04\u7387\uff0c\u7aef\u53e3 4", "\u603b\u900f\u5c04\u7387", "\u603b\u53cd\u5c04\u7387\u548c\u900f\u5c04\u7387", "\u5438\u6536\u7387"});
    model.result("pg7").label("\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387 (ewbe)");
    model.result("pg7").feature("glob1").set("titletype", "none");
    model.result("pg7").feature("glob1").set("xdata", "expr");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387 (1)");
    model.result("pg7").feature("glob1").set("xdataexpr", "r0");
    model.result("pg7").feature("glob1").set("xdataunit", "mm");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("xdatasolnumtype", "all");
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").label("\u7535\u6a21\u573a\uff0c\u7aef\u53e3 1 (ewbe) 1");
    model.result("pg8").set("showlegendsmaxmin", true);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg8").set("data", "dset6");
    model.result("pg8").create("lngr1", "LineGraph");
    model.result("pg8").feature("lngr1").selection().set(1, 3, 5);
    model.result("pg8").feature("lngr1").set("expr", "root.comp1.ewbe.normEbm_1");
    model.result("pg8").feature("lngr1").set("legend", true);
    model.result("pg8").feature("lngr1").set("legendmethod", "evaluated");
    model.result("pg8").feature("lngr1")
         .set("legendpattern", "\u6709\u6548\u6a21\u5f0f\u6298\u5c04\u7387: eval(comp1.ewbe.neff_1)");
    model.result("pg8").feature("lngr1").set("legendexprprecision", 5);
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").label("\u7535\u6a21\u573a\uff0c\u7aef\u53e3 2 (ewbe) 1");
    model.result("pg9").set("showlegendsmaxmin", true);
    model.result("pg9").set("data", "dset6");
    model.result("pg9").create("lngr1", "LineGraph");
    model.result("pg9").feature("lngr1").selection().set(152, 153, 154);
    model.result("pg9").feature("lngr1").set("expr", "root.comp1.ewbe.normEbm_2");
    model.result("pg9").feature("lngr1").set("legend", true);
    model.result("pg9").feature("lngr1").set("legendmethod", "evaluated");
    model.result("pg9").feature("lngr1")
         .set("legendpattern", "\u6709\u6548\u6a21\u5f0f\u6298\u5c04\u7387: eval(comp1.ewbe.neff_2)");
    model.result("pg9").feature("lngr1").set("legendexprprecision", 5);
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").label("\u7535\u6a21\u573a\uff0c\u7aef\u53e3 3 (ewbe) 1");
    model.result("pg10").set("showlegendsmaxmin", true);
    model.result("pg10").set("data", "dset6");
    model.result("pg10").create("lngr1", "LineGraph");
    model.result("pg10").feature("lngr1").selection().set(8, 10, 12);
    model.result("pg10").feature("lngr1").set("expr", "root.comp1.ewbe.normEbm_3");
    model.result("pg10").feature("lngr1").set("legend", true);
    model.result("pg10").feature("lngr1").set("legendmethod", "evaluated");
    model.result("pg10").feature("lngr1")
         .set("legendpattern", "\u6709\u6548\u6a21\u5f0f\u6298\u5c04\u7387: eval(comp1.ewbe.neff_3)");
    model.result("pg10").feature("lngr1").set("legendexprprecision", 5);
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").label("\u7535\u6a21\u573a\uff0c\u7aef\u53e3 4 (ewbe) 1");
    model.result("pg11").set("showlegendsmaxmin", true);
    model.result("pg11").set("data", "dset6");
    model.result("pg11").create("lngr1", "LineGraph");
    model.result("pg11").feature("lngr1").selection().set(149, 150, 151);
    model.result("pg11").feature("lngr1").set("expr", "root.comp1.ewbe.normEbm_4");
    model.result("pg11").feature("lngr1").set("legend", true);
    model.result("pg11").feature("lngr1").set("legendmethod", "evaluated");
    model.result("pg11").feature("lngr1")
         .set("legendpattern", "\u6709\u6548\u6a21\u5f0f\u6298\u5c04\u7387: eval(comp1.ewbe.neff_4)");
    model.result("pg11").feature("lngr1").set("legendexprprecision", 5);
    model.result("pg6").run();
    model.result("pg7").run();
    model.result("pg7").label("\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u635f\u8017 (ewbe)");
    model.result("pg7").set("ylabel", "\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u635f\u8017 (1)");
    model.result("pg7").run();
    model.result("pg7").feature("glob1").setIndex("descr", "\u635f\u8017", 6);
    model.result("pg7").feature("glob1").set("linestyle", "cycle");
    model.result("pg7").feature("glob1").set("linemarker", "cycle");
    model.result("pg7").run();
    model.result("pg7").set("legendpos", "middleleft");
    model.result("pg6").run();
    model.result("pg6").run();

    model.param("par2").set("r0", "2.5[mm]");
    model.param("par2").descr("r0", "\u5f2f\u66f2\u534a\u5f84");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().named("geom1_csel8_bnd");
    model.component("comp1").cpl().duplicate("intop2", "intop1");
    model.component("comp1").cpl("intop2").selection().named("geom1_csel9_bnd");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("P1", "intop1(ewbe.nPoav)");
    model.component("comp1").variable("var1").descr("P1", "\u4e0a\u65b9\u6ce2\u5bfc\u4e2d\u7684\u529f\u7387");
    model.component("comp1").variable("var1").set("P2", "intop2(ewbe.nPoav)");
    model.component("comp1").variable("var1").descr("P2", "\u4e0b\u65b9\u6ce2\u5bfc\u4e2d\u7684\u529f\u7387");

    model.study("std1").feature("param").setIndex("pname", "d_cpl", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(80[um],50[um],430[um])", 0);
    model.study("std1").createAutoSequences("all");

    model.batch("p1").run("compute");

    model.result("pg6").run();
    model.result("pg7").run();
    model.result("pg7").feature("glob1").set("xdataexpr", "d_cpl");
    model.result("pg7").feature("glob1").set("xdataunit", "\u00b5m");
    model.result("pg7").run();
    model.result("pg7").set("legendpos", "middleright");
    model.result("pg7").run();
    model.result().create("pg12", "PlotGroup1D");
    model.result("pg12").run();
    model.result("pg12").label("\u529f\u7387\u5dee");
    model.result("pg12").set("data", "dset6");
    model.result("pg12").create("glob1", "Global");
    model.result("pg12").feature("glob1").set("markerpos", "datapoints");
    model.result("pg12").feature("glob1").set("linewidth", "preference");
    model.result("pg12").feature("glob1").setIndex("expr", "abs(P2-P1)", 0);
    model.result("pg12").feature("glob1").setIndex("descr", "\u529f\u7387\u5dee", 0);
    model.result("pg12").feature("glob1").set("xdatasolnumtype", "outer");
    model.result("pg12").feature("glob1").set("xdata", "expr");
    model.result("pg12").feature("glob1").set("xdataexpr", "d_cpl");
    model.result("pg12").feature("glob1").set("xdataunit", "\u00b5m");
    model.result("pg12").run();
    model.result("pg12").set("showlegends", false);
    model.result("pg12").run();
    model.result("pg6").run();
    model.result("pg6").run();

    model.param("par2").set("d_cpl", "380[um]");
    model.param("par2").descr("d_cpl", "\u5b9a\u5411\u8026\u5408\u5668\u6ce2\u5bfc\u7684\u957f\u5ea6");
    model.param("par2").set("d_mz", "2[cm]");
    model.param("par2").descr("d_mz", "\u9a6c\u8d6b-\u66fe\u5fb7\u5c14\u6ce2\u5bfc\u7684\u957f\u5ea6");

    model.component("comp1").physics().create("es", "Electrostatics", "geom1");

    model.study("std1").feature("bma").setSolveFor("/physics/es", true);
    model.study("std1").feature("bma1").setSolveFor("/physics/es", true);
    model.study("std1").feature("bma2").setSolveFor("/physics/es", true);
    model.study("std1").feature("bma3").setSolveFor("/physics/es", true);
    model.study("std1").feature("freq").setSolveFor("/physics/es", true);

    model.component("comp1").geom("geom1").run();
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "d_dc", 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-w", 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "d_dc+d_mz", 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-w", 1, 1);
    model.component("comp1").geom("geom1").feature().duplicate("pol2", "pol1");
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "w", 0, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "w", 1, 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("es").create("ccns1", "ChargeConservationSolid", 2);
    model.component("comp1").physics("es").feature("ccns1").selection().all();
    model.component("comp1").physics("es").create("pot1", "ElectricPotential", 1);
    model.component("comp1").physics("es").feature("pot1").selection().set(84);
    model.component("comp1").physics("es").feature("pot1").set("V0", "V0");
    model.component("comp1").physics("es").create("gnd1", "Ground", 1);
    model.component("comp1").physics("es").feature("gnd1").selection().set(78);

    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"epsr"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"epsr"});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"n_clad-0.5*n_clad^3*r13*es.Ey"});
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"n_core-0.5*n_core^3*r13*es.Ey"});

    model.study("std1").feature("param").setIndex("pname", "V0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(0[V],0.1[V],1[V])", 0);
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature().move("stat", 5);
    model.study("std1").feature("freq").setSolveFor("/physics/es", false);
    model.study("std1").createAutoSequences("all");

    model.batch("p1").run("compute");

    model.result("pg6").run();
    model.result("pg7").run();
    model.result("pg7").feature("glob1").set("xdataexpr", "V0");
    model.result("pg7").run();

    model.title("\u9a6c\u8d6b-\u66fe\u5fb7\u5c14\u8c03\u5236\u5668");

    model
         .description("\u9a6c\u8d6b-\u66fe\u5fb7\u5c14\u8c03\u5236\u5668\u7528\u4e8e\u63a7\u5236\u5149\u6ce2\u7684\u632f\u5e45\u3002\u8f93\u5165\u6ce2\u5bfc\u88ab\u5206\u6210\u6ce2\u5bfc\u5e72\u6d89\u4eea\u7684\u4e24\u4e2a\u81c2\uff0c\u5982\u679c\u5bf9\u5176\u4e2d\u4e00\u4e2a\u81c2\u65bd\u52a0\u7535\u538b\uff0c\u901a\u8fc7\u8be5\u81c2\u7684\u6ce2\u4f1a\u4ea7\u751f\u76f8\u79fb\u3002\u5f53\u8fd9\u4e24\u4e2a\u81c2\u91cd\u65b0\u7ec4\u5408\u65f6\uff0c\u4e24\u4e2a\u6ce2\u4e4b\u95f4\u7684\u76f8\u5dee\u4f1a\u8f6c\u6362\u4e3a\u8c03\u5e45\u3002\n\u8fd9\u662f\u4e00\u4e2a\u591a\u7269\u7406\u573a\u6a21\u578b\uff0c\u6f14\u793a\u4e86\u5982\u4f55\u7ed3\u5408\u201c\u7535\u78c1\u6ce2\uff0c\u6ce2\u675f\u5305\u7edc\u201d\u4e0e\u201c\u9759\u7535\u201d\u7528\u6237\u754c\u9762\u6765\u63cf\u8ff0\u5b9e\u9645\u7684\u6ce2\u5bfc\u5668\u4ef6\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol2").clearSolutionData();
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

    model.label("mach_zehnder_modulator.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
