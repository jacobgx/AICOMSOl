/*
 * interior_pm_motor_stress_analysis.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:17 by COMSOL 6.3.0.290. */
public class interior_pm_motor_stress_analysis {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Devices,_Motors_and_Generators");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics("solid").create("rotf1", "RotatingFrame");
    model.component("comp1").physics("solid").feature("rotf1").selection().all();
    model.component("comp1").physics("solid").feature("rotf1").set("DefineSpatialFrameRotation", new String[]{"1"});
    model.component("comp1").physics().create("rmm", "RotatingMachineryMagnetic", "geom1");
    model.component("comp1").physics("rmm").feature("al1").set("materialType", new String[]{"solid"});
    model.component("comp1").physics("rmm").feature("al1").label("\u5b89\u57f9\u5b9a\u5f8b\uff0c\u56fa\u4f53");

    model.component("comp1").multiphysics().create("mfrm1", "MagneticForcesRotatingMachinery", 2);
    model.component("comp1").multiphysics("mfrm1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("mfrm1").set("MagneticFields_physics", "rmm");
    model.component("comp1").multiphysics("mfrm1").selection().all();

    model.component("comp1").common().create("rotb1", "RotatingBoundary");
    model.component("comp1").common("rotb1").selection().set();
    model.component("comp1").common().create("free1", "DeformingDomain");
    model.component("comp1").common("free1").set("smoothingType", "hyperelastic");
    model.component("comp1").common("free1").selection().set();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/physics/rmm", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/mfrm1", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Np", "10", "\u6781\u6570");
    model.param().set("Ns", "12", "\u69fd\u6570");
    model.param().set("mag_h", "2.5[mm]", "\u78c1\u4f53\u9ad8\u5ea6");
    model.param().set("d_s", "10[mm]", "\u8f74\u5f84");
    model.param().set("d_r", "60[mm]", "\u8f6c\u5b50\u76f4\u5f84");
    model.param().set("d_st", "100[mm]", "\u5b9a\u5b50\u76f4\u5f84");
    model.param().set("airgap", "0.7[mm]", "\u6c14\u9699\u5927\u5c0f");
    model.param().set("d_cont", "60.7[mm]", "\u5b9a\u5b50-\u8f6c\u5b50\u8fde\u7eed\u754c\u9762\u7684\u76f4\u5f84");
    model.param().set("L", "40[mm]", "\u7535\u673a\u7684\u9762\u5916\u539a\u5ea6");
    model.param().set("w_rot", "20000[rpm]", "\u8f6c\u901f");
    model.param().set("tl", "mag_h/100", "\u8584\u5c42\u539a\u5ea6");
    model.param().set("f_el", "w_rot*Np/2", "\u7535\u9891\u7387");
    model.param().set("t_ramp", "1/f_el/20", "\u52a0\u901f\u65f6\u95f4");
    model.param().set("N_tsteps", "72", "\u65f6\u6b65\u6570");
    model.param().set("Ipk", "5[A]", "\u76f8\u7535\u6d41\u5cf0\u503c");
    model.param().set("init_ang", "200[deg]", "\u5cf0\u503c\u626d\u77e9\u7684\u521d\u59cb\u7535\u6d41\u89d2");
    model.param().set("t_step", "1/f_el/N_tsteps", "\u65f6\u6b65");
    model.param().set("t_end", "3/f_el", "\u4eff\u771f\u65f6\u95f4");
    model.param().set("t", "0[s]", "\u65f6\u95f4\u53d8\u91cf\u522b\u540d");

    model.geom()
         .load(new String[]{"part1"}, "ACDC_Module\\Rotating_Machinery_2D\\Rotors\\Internal\\embedded_magnet_v_shape_internal_rotor_2d.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "number_of_poles", "Np");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "number_of_modeled_poles", "Np");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "shaft_diam", "d_s");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "rotor_diam", "d_r");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "cont_diam", "d_cont");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "magnet_h", "mag_h");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "rotor_bridge_size", "0.3[mm]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "poles_bridge_size", "0.3[mm]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "v_distance", "0.3[mm]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "flux_barrier_indent_fraction", 0);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_shaft.dom", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_rotor_air_gap.dom", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_rotor_magnets.dom", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_rotor_iron.dom", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_flux_barriers.dom", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_rotor_air.dom", true);
    model.component("comp1").geom("geom1").feature("pi1")
         .setEntry("selkeepdom", "pi1_rotor_solid_domains.dom", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_all.dom", true);
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("spl1", "Split");
    model.component("comp1").geom("geom1").feature("spl1").selection("input").set("pi1");
    model.component("comp1").geom("geom1").run("spl1");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", 20);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").feature().duplicate("c2", "c1");
    model.component("comp1").geom("geom1").feature("c2").set("r", 10);
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("c1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("c2");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{30, 3});
    model.component("comp1").geom("geom1").feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("r1").set("pos", new int[]{15, 0});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", "range((360/Np)/2,360/Np,360-360/Np/2)");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("r1");
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("dif2", "Difference");
    model.component("comp1").geom("geom1").feature("dif2").selection("input").set("dif1");
    model.component("comp1").geom("geom1").feature("dif2").selection("input2").set("rot1");
    model.component("comp1").geom("geom1").feature("dif2").set("selresult", true);
    model.component("comp1").geom("geom1").run("dif2");
    model.component("comp1").geom("geom1").create("dif3", "Difference");
    model.component("comp1").geom("geom1").feature("dif3").selection("input").set("spl1(2)");
    model.component("comp1").geom("geom1").feature("dif3").selection("input2").named("dif2");
    model.component("comp1").geom("geom1").feature("dif3").set("keepsubtract", true);
    model.component("comp1").geom("geom1").run("dif3");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").label("\u5e76\u96c6\uff1a\u8f6c\u5b50");
    model.component("comp1").geom("geom1").feature("uni1").selection("input")
         .set("dif2", "dif3", "spl1(1)", "spl1(10)", "spl1(11)", "spl1(12)", "spl1(13)", "spl1(14)", "spl1(15)", "spl1(16)", "spl1(17)", "spl1(18)", "spl1(19)", "spl1(20)", "spl1(21)", "spl1(22)", "spl1(23)", "spl1(24)", "spl1(25)", "spl1(26)", "spl1(27)", "spl1(28)", "spl1(29)", "spl1(3)", "spl1(30)", "spl1(31)", "spl1(32)", "spl1(33)", "spl1(34)", "spl1(35)", "spl1(36)", "spl1(37)", "spl1(38)", "spl1(39)", "spl1(4)", "spl1(40)", "spl1(41)", "spl1(42)", "spl1(43)", "spl1(44)", "spl1(45)", "spl1(46)", "spl1(47)", "spl1(48)", "spl1(49)", "spl1(5)", "spl1(50)", "spl1(51)", "spl1(52)", "spl1(53)", "spl1(54)", "spl1(55)", "spl1(56)", "spl1(57)", "spl1(58)", "spl1(59)", "spl1(6)", "spl1(60)", "spl1(61)", "spl1(62)", "spl1(63)", "spl1(7)", "spl1(8)", "spl1(9)");
    model.component("comp1").geom("geom1").feature("uni1").set("selresult", true);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1").label("\u8f6c\u5b50\u6c14\u7a74\u70b9");
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"pi1_flux_barriers"});
    model.component("comp1").geom("geom1").feature("adjsel1").set("outputdim", 0);
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").create("adjsel2", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel2").label("\u8f6c\u5b50\u7a7a\u6c14\u901a\u9053\u70b9");
    model.component("comp1").geom("geom1").feature("adjsel2").set("input", new String[]{"dif2"});
    model.component("comp1").geom("geom1").feature("adjsel2").set("outputdim", 0);
    model.component("comp1").geom("geom1").run("adjsel2");
    model.component("comp1").geom("geom1").feature().duplicate("adjsel3", "adjsel2");
    model.component("comp1").geom("geom1").feature("adjsel3").label("\u8f6c\u5b50\u78c1\u4f53\u70b9");
    model.component("comp1").geom("geom1").run("adjsel3");
    model.component("comp1").geom("geom1").feature("adjsel3").set("input", new String[]{"pi1_rotor_magnets"});
    model.component("comp1").geom("geom1").run("adjsel3");
    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").label("\u8f6c\u5b50\u6c14\u7a74\u5706\u89d2\u70b9");
    model.component("comp1").geom("geom1").run("difsel1");
    model.component("comp1").geom("geom1").feature("difsel1").set("entitydim", 0);
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"adjsel1"});
    model.component("comp1").geom("geom1").feature("difsel1").set("subtract", new String[]{"adjsel3"});
    model.component("comp1").geom("geom1").run("difsel1");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("point").named("difsel1");
    model.component("comp1").geom("geom1").feature("fil1").set("radius", "0.5[mm]");
    model.component("comp1").geom("geom1").run("fil1");
    model.component("comp1").geom("geom1").create("fil2", "Fillet");
    model.component("comp1").geom("geom1").feature("fil2").selection("point").named("adjsel2");
    model.component("comp1").geom("geom1").feature("fil2").selection("point")
         .set("fil1", 88, 89, 90, 91, 116, 117, 132, 133, 135, 136, 145, 146, 167, 168, 172, 173, 182, 183, 184, 185, 210, 211, 212, 213, 222, 223, 227, 228, 249, 250, 259, 260, 262, 263, 278, 279, 304, 305, 306, 307);
    model.component("comp1").geom("geom1").feature("fil2").set("radius", "2[mm]");
    model.component("comp1").geom("geom1").run("fil2");
    model.component("comp1").geom("geom1").nodeGroup().create("grp1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").placeAfter(null);
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("pi1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("spl1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("c1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("c2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("dif1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("r1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("rot1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("dif2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("dif3");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("uni1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("adjsel1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("adjsel2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("adjsel3");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("difsel1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("fil1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("fil2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").label("\u8f6c\u5b50");
    model.geom()
         .load(new String[]{"part2"}, "ACDC_Module\\Rotating_Machinery_2D\\Stators\\External\\slotted_external_stator_2d.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").nodeGroup("grp1").remove("pi2", false);
    model.component("comp1").geom("geom1").feature("pi2").set("part", "part2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "number_of_slots", "Ns");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "number_of_modeled_slots", "Ns");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "backiron_th", "3[mm]");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "stator_diam", "d_st");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "external_air_size", "6[mm]");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "cont_diam", "d_cont");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "shoe_w", "12[mm]");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "tooth_h", "17[mm]-airgap");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "tooth_w", "8[mm]");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "slot_outer_fillet_size", "0.5[mm]");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "slot_inner_fillet_size", "0.5[mm]");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "slot_winding_type", 2);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "Arkkio_toggle", 2);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selkeepdom", "pi2_stator_iron.dom", true);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selkeepdom", "pi2_stator_slots", true);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selkeepdom", "pi2_all.dom", true);
    model.component("comp1").geom("geom1").run("pi2");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").func().create("rm1", "Ramp");
    model.component("comp1").func("rm1").set("slope", "1/t_ramp");
    model.component("comp1").func("rm1").set("cutoffactive", true);

    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u8f6c\u5b50\u7ed3\u6784\u57df");
    model.component("comp1").selection("uni1")
         .set("input", new String[]{"geom1_pi1_rotor_magnets_dom", "geom1_pi1_rotor_iron_dom"});
    model.component("comp1").selection().duplicate("uni2", "uni1");
    model.component("comp1").selection("uni2").label("\u7ed3\u6784\u57df");
    model.component("comp1").selection("uni2")
         .set("input", new String[]{"geom1_pi1_rotor_magnets_dom", "geom1_pi1_rotor_iron_dom", "geom1_pi2_stator_iron_dom"});
    model.component("comp1").selection().create("uni3", "Union");
    model.component("comp1").selection("uni3").label("\u53d8\u5f62\u57df");
    model.component("comp1").selection("uni3")
         .set("input", new String[]{"geom1_dif2_dom", "geom1_pi1_flux_barriers_dom", "geom1_pi1_rotor_air_gap_dom"});
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").label("\u8f74\u8fb9\u754c");
    model.component("comp1").selection("adj1").set("input", new String[]{"geom1_pi1_shaft_dom"});
    model.component("comp1").selection().duplicate("adj2", "adj1");
    model.component("comp1").selection("adj2").label("\u8f6c\u5b50\u78c1\u4f53\u8fb9\u754c");
    model.component("comp1").selection("adj2").set("input", new String[]{"geom1_pi1_rotor_magnets_dom"});
    model.component("comp1").selection().duplicate("adj3", "adj2");
    model.component("comp1").selection("adj3").label("\u8f6c\u5b50\u6c14\u7a74\u8fb9\u754c");
    model.component("comp1").selection("adj3").set("input", new String[]{"geom1_pi1_flux_barriers_dom"});
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").label("\u78c1\u4f53-\u8f6c\u5b50\u94c1\u63a5\u89e6\u8fb9\u754c");
    model.component("comp1").selection("dif1").set("entitydim", 1);
    model.component("comp1").selection("dif1").set("add", new String[]{"adj2"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"adj3"});
    model.component("comp1").selection().duplicate("adj4", "adj2");
    model.component("comp1").selection("adj4").label("\u8f6c\u5b50\u6c14\u9699\u8fb9\u754c");
    model.component("comp1").selection("adj4").set("input", new String[]{"geom1_pi1_rotor_air_gap_dom"});
    model.component("comp1").selection().create("disk1", "Disk");
    model.component("comp1").selection("disk1").label("\u65cb\u8f6c\u8fb9\u754c");
    model.component("comp1").selection("disk1").set("entitydim", 1);
    model.component("comp1").selection("disk1").set("inputent", "selections");
    model.component("comp1").selection("disk1").set("input", new String[]{"adj4"});
    model.component("comp1").selection("disk1").set("r", "d_cont/2+airgap/4");
    model.component("comp1").selection("disk1").set("rin", "d_cont/2-airgap/4");
    model.component("comp1").selection().duplicate("disk2", "disk1");
    model.component("comp1").selection("disk2").label("\u8fb9\u754c\u5c42\u8fb9\u754c");
    model.component("comp1").selection("disk2").set("inputent", "all");
    model.component("comp1").selection("disk2").set("r", "d_cont/2+airgap/3*4");
    model.component("comp1").selection("disk2").set("rin", "d_cont/2-airgap/4*3");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat1").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat1").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").label("Air");
    model.component("comp1").material("mat1").set("family", "air");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp1").material("mat1").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat1").materialType("nonSolid");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup().create("BHCurve", "BHCurve", "B-H Curve");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func().create("BH", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup()
         .create("EffectiveBHCurve", "EffectiveBHCurve", "Effective B-H Curve");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").func()
         .create("BHeff", "Interpolation");
    model.component("comp1").material("mat2").label("Soft Iron (Without Losses)");
    model.component("comp1").material("mat2").set("family", "iron");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("BHCurve").label("B-H Curve");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH")
         .set("table", new String[][]{{"0", "0"}, 
         {"663.146", "1"}, 
         {"1067.5", "1.1"}, 
         {"1705.23", "1.2"}, 
         {"2463.11", "1.3"}, 
         {"3841.67", "1.4"}, 
         {"5425.74", "1.5"}, 
         {"7957.75", "1.6"}, 
         {"12298.3", "1.7"}, 
         {"20462.8", "1.8"}, 
         {"32169.6", "1.9"}, 
         {"61213.4", "2"}, 
         {"111408", "2.1"}, 
         {"188487.757", "2.2"}, 
         {"267930.364", "2.3"}, 
         {"347507.836", "2.4"}});
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH").set("extrap", "linear");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH").set("fununit", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH")
         .set("argunit", new String[]{"A/m"});
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH").set("defineinv", true);
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH").set("defineprimfun", true);
    model.component("comp1").material("mat2").propertyGroup("BHCurve").set("normB", "BH(normHin)");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").set("normH", "BH_inv(normBin)");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").set("Wpm", "BH_prim(normHin)");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").descr("normHin", "\u78c1\u573a\u6a21");
    model.component("comp1").material("mat2").propertyGroup("BHCurve")
         .descr("normBin", "\u78c1\u901a\u5bc6\u5ea6\u6a21");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").addInput("magneticfield");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").addInput("magneticfluxdensity");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").label("Effective B-H Curve");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").func("BHeff")
         .label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("table", new String[][]{{"0", "0"}, 
         {"663.146", "1.000000051691021"}, 
         {"1067.5", "1.4936495124126294"}, 
         {"1705.23", "1.9415328461315795"}, 
         {"2463.11", "2.257765669366018"}, 
         {"3841.67", "2.609980642431287"}, 
         {"5425.74", "2.8664452090837504"}, 
         {"7957.75", "3.1441438097176118"}, 
         {"12298.3", "3.448538051654125"}, 
         {"20462.8", "3.7816711973679054"}, 
         {"32169.6", "4.058345590113038"}, 
         {"61213.4", "4.420646552950275"}, 
         {"111408", "4.721274089545955"}, 
         {"188487.757", "4.972148140718701"}, 
         {"267930.364", "5.145510860855953"}, 
         {"347507.836", "5.245510861426532"}});
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("extrap", "linear");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("fununit", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("argunit", new String[]{"A/m"});
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").func("BHeff").set("defineinv", true);
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("defineprimfun", true);
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").set("normBeff", "BHeff(normHeffin)");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve")
         .set("normHeff", "BHeff_inv(normBeffin)");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve")
         .set("Wpmeff", "BHeff_prim(normHeffin)");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve")
         .descr("normHeffin", "\u6709\u6548\u78c1\u573a\u6a21");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve")
         .descr("normBeffin", "\u6709\u6548\u78c1\u901a\u5bc6\u5ea6\u6a21");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").addInput("magneticfield");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").addInput("magneticfluxdensity");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("RemanentFluxDensity", "RemanentFluxDensity", "Remanent flux density");
    model.component("comp1").material("mat3").label("N42 (Sintered NdFeB)");
    model.component("comp1").material("mat3").set("family", "chrome");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"1/1.4[uohm*m]", "0", "0", "0", "1/1.4[uohm*m]", "0", "0", "0", "1/1.4[uohm*m]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("RemanentFluxDensity").label("Remanent flux density");
    model.component("comp1").material("mat3").propertyGroup("RemanentFluxDensity")
         .set("murec", new String[]{"1.05", "0", "0", "0", "1.05", "0", "0", "0", "1.05"});
    model.component("comp1").material("mat3").propertyGroup("RemanentFluxDensity").set("normBr", "1.31[T]");
    model.component("comp1").material("mat2").selection().set(2, 29);
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", new String[]{"185[GPa]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"7500"});
    model.component("comp1").material("mat3").selection().named("geom1_pi1_rotor_magnets_dom");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", new String[]{"160[GPa]"});
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", new String[]{"0.24"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", new String[]{"7500"});

    model.component("comp1").physics("solid").selection().named("uni2");
    model.component("comp1").physics("solid").prop("d").set("d", "L");

    model.component("comp1").coordSystem().create("sys2", "Cylindrical");
    model.component("comp1").coordSystem("sys2").set("frametype", "material");

    model.component("comp1").physics("solid").feature("lemm1").set("coordinateSystem", "sys2");
    model.component("comp1").physics("solid").feature("rotf1").selection().named("uni1");
    model.component("comp1").physics("solid").feature("rotf1").set("RotationalFrequency", "RevolutionsPerTime");
    model.component("comp1").physics("solid").feature("rotf1").set("rpt", "w_rot*rm1(t[1/s])");
    model.component("comp1").physics("solid").create("rms1", "RigidMotionSuppression", 2);
    model.component("comp1").physics("solid").feature("rms1").selection().named("geom1_pi2_stator_iron_dom");
    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().named("adj1");
    model.component("comp1").physics("solid").create("tl1", "ThinLayer", 1);
    model.component("comp1").physics("solid").feature("tl1").set("lth", "tl");
    model.component("comp1").physics("solid").feature("tl1").set("thicknessApproximation", "spring");
    model.component("comp1").physics("solid").feature("tl1").selection().named("dif1");
    model.component("comp1").physics("solid").feature("tl1").create("spm1", "SpringMaterial", 1);
    model.component("comp1").physics("solid").feature("tl1").feature("spm1").selection().named("dif1");
    model.component("comp1").physics("solid").feature("tl1").feature("spm1").set("SpringType", "FDefTot");
    model.component("comp1").physics("solid").feature("tl1").feature("spm1")
         .set("FDefTot", new String[]{"kt1*solid.tl1.uelt1", "kn*solid.tl1.ueln", "0"});

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("kt", "5e11[N/m]");
    model.component("comp1").variable("var1").descr("kt", "\u62c9\u4f38\u5f39\u7c27\u521a\u5ea6");
    model.component("comp1").variable("var1").set("kc", "5e13[N/m]");
    model.component("comp1").variable("var1").descr("kc", "\u538b\u7f29\u5f39\u7c27\u521a\u5ea6");
    model.component("comp1").variable("var1").set("kn", "kt*(solid.tl1.ueln>=0)+kc*(solid.tl1.ueln<0)");
    model.component("comp1").variable("var1")
         .descr("kn", "\u5f39\u6027\u8584\u5c42\u5728\u6cd5\u5411\u4e0a\u7684\u521a\u5ea6");
    model.component("comp1").variable("var1").set("kt1", "1e15[N/m]");
    model.component("comp1").variable("var1")
         .descr("kt1", "\u5f39\u6027\u8584\u5c42\u5728\u5207\u5411\u4e0a\u7684\u521a\u5ea6");

    model.component("comp1").physics("rmm").prop("d").set("d", "L");
    model.component("comp1").physics("rmm").create("al2", "AmperesLaw", 2);
    model.component("comp1").physics("rmm").feature("al2").selection().set(2, 29);
    model.component("comp1").physics("rmm").feature("al2").set("ConstitutiveRelationBH", "BHCurve");
    model.component("comp1").physics("rmm").create("cmag1", "ConductingMagnet", 2);
    model.component("comp1").physics("rmm").feature("cmag1").selection().named("geom1_pi1_rotor_magnets_dom");
    model.component("comp1").physics("rmm").feature("cmag1").set("PatternType", "CircularPattern");
    model.component("comp1").physics("rmm").feature("cmag1").set("PeriodicType", "Alternating");
    model.component("comp1").physics("rmm").feature("cmag1").feature("north1").selection().set(394, 414);
    model.component("comp1").physics("rmm").feature("cmag1").feature("south1").selection().set(390, 422);
    model.component("comp1").physics("rmm").create("wnd1", "MultiphaseWinding", 2);
    model.component("comp1").physics("rmm").feature("wnd1").selection().named("geom1_pi2_stator_slots");
    model.component("comp1").physics("rmm").feature("wnd1").set("Ipk", "Ipk");
    model.component("comp1").physics("rmm").feature("wnd1").set("alpha_i", "init_ang");
    model.component("comp1").physics("rmm").feature("wnd1").set("freq_t", "f_el*rm1(t[1/s])");
    model.component("comp1").physics("rmm").feature("wnd1").set("WindingLayout", "automatic");
    model.component("comp1").physics("rmm").feature("wnd1").set("NoPoles", "Np");
    model.component("comp1").physics("rmm").feature("wnd1").set("NoSlots", "Ns");
    model.component("comp1").physics("rmm").feature("wnd1").set("NoCoilsPerSlot", 2);
    model.component("comp1").physics("rmm").feature("wnd1").runCommand("addPhases");
    model.component("comp1").physics("rmm").create("ark1", "ArkkioTorqueCalculation", 2);
    model.component("comp1").physics("rmm").feature("ark1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46);

    model.component("comp1").common("free1").selection().named("uni3");
    model.component("comp1").common("rotb1").selection().named("disk1");
    model.component("comp1").common("rotb1").set("rotationType", "rotationalVelocity");
    model.component("comp1").common("rotb1").set("rotationalVelocityExpression", "generalRevolutionsPerTime");
    model.component("comp1").common("rotb1").set("revolutionsPerTime", "w_rot*rm1(t[1/s])");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hcurve", 0.9);
    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("size1").selection().named("geom1_pi2_stator_iron_dom");
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", 1.5);
    model.component("comp1").mesh("mesh1").feature().move("size1", 1);
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set(10, 99);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().named("disk2");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 1);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhminfact", 5);

    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("tlist", "range(0,t_step,t_end)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("bwinitstepfrac", 0.1);
    model.sol("sol1").feature("t1").feature("se1").feature("ss2").set("subdtech", "auto");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 217, 0);
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("resolution", "normal");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 217, 0);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").label("\u5e94\u529b\uff0c\u8584\u5c42 (solid)");
    model.result("pg2").create("line1", "Line");
    model.result("pg2").feature("line1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg2").feature("line1").set("threshold", "manual");
    model.result("pg2").feature("line1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("line1").set("colortable", "Rainbow");
    model.result("pg2").feature("line1").set("colortabletrans", "none");
    model.result("pg2").feature("line1").set("colorscalemode", "linear");
    model.result("pg2").feature("line1").set("colortable", "Rainbow");
    model.result("pg2").feature("line1").set("resolution", "custom");
    model.result("pg2").feature("line1").set("refine", 2);
    model.result("pg2").feature("line1").set("linetype", "tube");
    model.result("pg2").feature("line1").set("radiusexpr", "solid.lth/2");
    model.result("pg2").feature("line1").create("sel1", "Selection");
    model.result("pg2").feature("line1").feature("sel1").selection().geom("geom1", 1);
    model.result("pg2").feature("line1").feature("sel1").selection()
         .set(282, 284, 289, 290, 293, 295, 319, 320, 324, 326, 329, 330, 354, 356, 365, 366, 388, 390, 393, 394, 412, 414, 421, 422, 442, 444, 451, 452, 472, 474, 477, 478, 483, 485, 496, 498, 517, 518, 521, 522);
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u78c1\u901a\u5bc6\u5ea6 (rmm)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").feature("surf1").set("expr", "rmm.normB");
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result("pg3").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").feature().create("str1", "Streamline");
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("solutionparams", "parent");
    model.result("pg3").feature("str1").set("expr", new String[]{"rmm.Bx", "rmm.By"});
    model.result("pg3").feature("str1").set("titletype", "none");
    model.result("pg3").feature("str1").set("posmethod", "uniform");
    model.result("pg3").feature("str1").set("udist", 0.03);
    model.result("pg3").feature("str1").set("maxlen", 0.4);
    model.result("pg3").feature("str1").set("maxsteps", 5000);
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("inheritcolor", false);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("data", "parent");
    model.result("pg3").feature("str1").selection().geom("geom1", 1);
    model.result("pg3").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341, 342, 343, 344, 345, 346, 347, 348, 349, 350, 351, 352, 353, 354, 355, 356, 357, 358, 359, 360, 361, 362, 363, 364, 365, 366, 367, 368, 369, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 380, 381, 382, 383, 384, 385, 386, 387, 388, 389, 390, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 404, 405, 406, 407, 408, 409, 410, 411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422, 423, 424, 425, 426, 427, 428, 429, 430, 431, 432, 433, 434, 435, 436, 437, 438, 439, 440, 441, 442, 443, 444, 445, 446, 447, 448, 449, 450, 451, 452, 453, 454, 455, 456, 457, 458, 459, 460, 461, 462, 463, 464, 465, 466, 467, 468, 469, 470, 471, 472, 473, 474, 475, 476, 477, 478, 479, 480, 481, 482, 483, 484, 485, 486, 487, 488, 489, 490, 491, 492, 493, 494, 495, 496, 497, 498, 499, 500, 501, 502, 503, 504, 505, 506, 507, 508, 509, 510, 511, 512, 513, 514, 515, 516, 517, 518, 519, 520, 521, 522, 523, 524, 525, 526, 527, 528, 529, 530, 531, 532, 533, 534, 535, 536, 537, 538, 539, 540, 541, 542, 543, 544, 545, 546, 547, 548, 549, 550, 551, 552, 553, 554, 555, 556, 557, 558, 559, 560, 561, 562, 563, 564, 565, 566, 567, 568, 569, 570, 571, 572, 573, 574, 575, 576, 577, 578, 579, 580, 581, 582, 583, 584, 585, 586, 587, 588, 589, 590, 591, 592, 593, 594, 595, 596, 597, 598, 599, 600, 601, 602, 603, 604, 605, 606, 607, 608, 609, 610, 611, 612, 613, 614, 615, 616, 617, 618, 619, 620, 621, 622, 623, 624, 625, 626, 627, 628, 629, 630, 631, 632, 633, 634, 635, 636, 637, 638, 639, 640, 641, 642, 643, 644, 645, 646, 647, 648, 649, 650, 651, 652, 653, 654, 655, 656, 657, 658, 659, 660, 661, 662, 663, 664, 665, 666, 667, 668, 669, 670, 671, 672, 673, 674, 675, 676, 677, 678, 679, 680, 681, 682, 683, 684, 685, 686, 687, 688, 689, 690, 691, 692, 693, 694, 695, 696, 697, 698, 699, 700, 701, 702, 703, 704, 705, 706, 707, 708, 709, 710, 711, 712, 713, 714, 715, 716, 717, 718, 719, 720, 721, 722, 723, 724, 725, 726, 727, 728, 729, 730, 731, 732, 733, 734, 735, 736, 737, 738, 739, 740, 741, 742, 743, 744, 745, 746, 747, 748, 749, 750);
    model.result("pg3").feature("str1").set("inheritplot", "surf1");
    model.result("pg3").feature("str1").feature().create("col1", "Color");
    model.result("pg3").feature("str1").feature("col1").set("expr", "rmm.normB");
    model.result("pg3").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg3").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg3").feature("str1").feature().create("filt1", "Filter");
    model.result("pg3").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg3").feature().create("con1", "Contour");
    model.result("pg3").feature("con1").set("showsolutionparams", "on");
    model.result("pg3").feature("con1").set("solutionparams", "parent");
    model.result("pg3").feature("con1").set("expr", "rmm.Az");
    model.result("pg3").feature("con1").set("titletype", "none");
    model.result("pg3").feature("con1").set("number", 10);
    model.result("pg3").feature("con1").set("levelrounding", false);
    model.result("pg3").feature("con1").set("coloring", "uniform");
    model.result("pg3").feature("con1").set("colorlegend", false);
    model.result("pg3").feature("con1").set("color", "custom");
    model.result("pg3").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg3").feature("con1").set("resolution", "fine");
    model.result("pg3").feature("con1").set("inheritcolor", false);
    model.result("pg3").feature("con1").set("showsolutionparams", "on");
    model.result("pg3").feature("con1").set("data", "parent");
    model.result("pg3").feature("con1").set("inheritplot", "surf1");
    model.result("pg3").feature("con1").feature().create("filt1", "Filter");
    model.result("pg3").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 217, 0);
    model.result("pg4").label("\u52a8\u7f51\u683c");
    model.result("pg4").create("mesh1", "Mesh");
    model.result("pg4").feature("mesh1").set("meshdomain", "surface");
    model.result("pg4").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg4").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg4").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg4").feature("mesh1").feature("sel1").selection()
         .set(2, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99);
    model.result("pg4").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg4").feature("mesh1").set("qualexpr", "comp1.spatial.relVol");
    model.result("pg4").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg1").run();

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg1").run();
    model.result("pg1").label("von Mises \u5e94\u529b (solid)");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("unit", "MPa");
    model.result("pg1").run();
    model.result().duplicate("pg5", "pg1");
    model.result("pg5").run();
    model.result("pg5").label("\u5f84\u5411\u5e94\u529b (solid)");
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("expr", "solid.slGp11");
    model.result("pg5").feature("surf1")
         .set("descr", "\u5e94\u529b\u5f20\u91cf\uff0c\u5c40\u90e8\u5750\u6807\u7cfb\uff0c11 \u5206\u91cf");
    model.result("pg1").run();
    model.result().duplicate("pg6", "pg1");
    model.result("pg6").run();
    model.result("pg6").label("\u4f4d\u79fb (solid)");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").set("expr", "solid.disp");
    model.result().configuration().create("gsty1", "GraphStyle");
    model.result().configuration("gsty1").set("linewidth", "unspecified");
    model.result().configuration("gsty1").set("linecolor", "cycle");
    model.result().configuration("gsty1").set("autodescr", true);
    model.result().configuration("gsty1").set("autosolution", false);
    model.result().configuration("gsty1").set("linewidth", 2);
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u4f4d\u79fb");
    model.result("pg7").set("titletype", "none");
    model.result("pg7").set("styleconfig", "gsty1");
    model.result("pg7").create("ptgr1", "PointGraph");
    model.result("pg7").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg7").feature("ptgr1").set("linewidth", "preference");
    model.result("pg7").feature("ptgr1").selection().set(264);
    model.result("pg7").feature("ptgr1").set("expr", "u");
    model.result("pg7").feature("ptgr1").set("legend", true);
    model.result("pg7").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg7").feature("ptgr1").setIndex("legends", "X \u5206\u91cf", 0);
    model.result("pg7").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg7").run();
    model.result("pg7").feature("ptgr2").set("expr", "v");
    model.result("pg7").feature("ptgr2").setIndex("legends", "Y \u5206\u91cf", 0);
    model.result("pg7").run();
    model.result("pg7").set("twoyaxes", true);
    model.result("pg7").setIndex("plotonsecyaxis", true, 1, 1);
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("yseclabelactive", true);
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u626d\u77e9");
    model.result("pg8").set("titletype", "none");
    model.result("pg8").set("styleconfig", "gsty1");
    model.result("pg8").set("showlegends", false);
    model.result("pg8").create("glob1", "Global");
    model.result("pg8").feature("glob1").set("markerpos", "datapoints");
    model.result("pg8").feature("glob1").set("linewidth", "preference");
    model.result("pg8").feature("glob1").setIndex("expr", "rmm.Tark_1", 0);
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").set("axislimits", true);
    model.result("pg8").set("xmin", 0);
    model.result("pg8").set("ymin", 0);
    model.result("pg8").run();
    model.result("pg3").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").placeAfter("plotgroup", "pg2");
    model.nodeGroup("grp1").add("plotgroup", "pg3");
    model.nodeGroup("grp1").add("plotgroup", "pg8");
    model.nodeGroup("grp1").label("\u7535\u78c1\u56fe");

    model.result("pg1").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup("grp2").add("plotgroup", "pg1");
    model.nodeGroup("grp2").add("plotgroup", "pg2");
    model.nodeGroup("grp2").add("plotgroup", "pg5");
    model.nodeGroup("grp2").add("plotgroup", "pg6");
    model.nodeGroup("grp2").add("plotgroup", "pg7");
    model.nodeGroup("grp2").label("\u7ed3\u6784\u56fe");

    model.result("pg5").run();
    model.result().move("pg5", 1);
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
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

    return model;
  }

  public static Model run3(Model model) {
    model.result().export("anim1").showFrame();
    model.result().export("anim1").label("von Mises \u5e94\u529b (solid)");
    model.result().export("anim1").set("maxframes", 50);
    model.result().export().duplicate("anim2", "anim1");
    model.result().export("anim2").showFrame();
    model.result().export("anim2").label("\u78c1\u901a\u5bc6\u5ea6\u6a21 (rmm)");
    model.result().export("anim2").set("plotgroup", "pg3");
    model.result("pg3").run();
    model.result("pg1").run();

    model.title("\u5185\u7f6e\u5f0f\u6c38\u78c1\u7535\u673a\u7684\u7535\u78c1\u548c\u673a\u68b0\u5206\u6790");

    model
         .description("\u5185\u7f6e\u5f0f\u6c38\u78c1 (IPM) \u7535\u673a\u7684\u78c1\u4f53\u5d4c\u5165\u8f6c\u5b50\u94c1\u82af\u5185\uff0c\u5f62\u6210\u4e00\u4e2a\u88ab\u79f0\u4e3a\u78c1\u6865\u7684\u72ed\u7a84\u533a\u57df\u3002\u65e0\u8bba\u662f\u4ece\u7535\u78c1\u8fd8\u662f\u673a\u68b0\u89d2\u5ea6\u6765\u770b\uff0c\u78c1\u6865\u7684\u539a\u5ea6\u90fd\u662f\u8bbe\u8ba1\u65f6\u9700\u8981\u8003\u8651\u7684\u5173\u952e\u53c2\u6570\u3002\u7531\u4e8e\u6865\u533a\u7684\u78c1\u9971\u548c\u4f1a\u5f71\u54cd IPM \u7684\u7535\u78c1\u7279\u6027\uff0c\u56e0\u6b64\u9700\u8981\u5c3d\u91cf\u51cf\u5c0f\u78c1\u6865\u539a\u5ea6\u4ee5\u964d\u4f4e\u635f\u8017\u3002\u7136\u800c\uff0c\u5728\u9ad8\u901f\u65cb\u8f6c\u8fc7\u7a0b\u4e2d\uff0c\u8fd9\u4e9b\u7a84\u6865\u4e5f\u4f1a\u56e0\u79bb\u5fc3\u529b\u800c\u627f\u53d7\u9ad8\u5e94\u529b\u3002\u8fd9\u4e24\u4e2a\u76f8\u4e92\u51b2\u7a81\u7684\u65b9\u9762\u4f7f\u5f97 IPM \u7535\u673a\u7684\u8bbe\u8ba1\u548c\u5206\u6790\u9887\u5177\u6311\u6218\u3002\n\n\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u5c06\u201c\u56fa\u4f53\u529b\u5b66\u201d\u4e0e\u201c\u65cb\u8f6c\u673a\u68b0\uff0c\u78c1\u201d\u76f8\u8026\u5408\uff0c\u4ee5\u5bf9 IPM \u7535\u673a\u6267\u884c\u7535\u78c1\u548c\u673a\u68b0\u5206\u6790\u3002\u5176\u4e2d\u5bf9\u4e00\u4e2a\u5177\u6709 10\u00a0\u4e2a\u8f6c\u5b50\u6781\u548c 12\u00a0\u4e2a\u5b9a\u5b50\u69fd\u7684\u7535\u673a\u8fdb\u884c\u4e8c\u7ef4\u5efa\u6a21\u3002\u78c1\u4f53\u4ee5 V \u5f62\u6784\u578b\u5d4c\u5165\u8f6c\u5b50\u94c1\u82af\u5185\u90e8\uff0c\u5e76\u4e14\u78c1\u4f53\u4e0e\u8f6c\u5b50\u94c1\u82af\u4e4b\u95f4\u7684\u8fde\u63a5\u4f5c\u4e3a\u5f39\u7c27\u8fdb\u884c\u5efa\u6a21\u3002\u4eff\u771f\u7ed3\u679c\u63ed\u793a\u4e86\u7cfb\u7edf\u7684\u78c1\u901a\u5bc6\u5ea6\u548c\u5e94\u529b\u5206\u5e03\u60c5\u51b5\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("interior_pm_motor_stress_analysis.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
