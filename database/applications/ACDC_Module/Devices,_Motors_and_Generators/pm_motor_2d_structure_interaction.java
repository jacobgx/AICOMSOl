/*
 * pm_motor_2d_structure_interaction.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:20 by COMSOL 6.3.0.290. */
public class pm_motor_2d_structure_interaction {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Devices,_Motors_and_Generators");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("mbd", "MultibodyDynamics", "geom1");
    model.component("comp1").physics("mbd").create("rotf1", "RotatingFrame");
    model.component("comp1").physics("mbd").feature("rotf1").selection().all();
    model.component("comp1").physics("mbd").feature("rotf1").set("DefineSpatialFrameRotation", new String[]{"1"});
    model.component("comp1").physics().create("rmm", "RotatingMachineryMagnetic", "geom1");
    model.component("comp1").physics("rmm").feature("al1").set("materialType", new String[]{"solid"});
    model.component("comp1").physics("rmm").feature("al1").label("\u5b89\u57f9\u5b9a\u5f8b\uff0c\u56fa\u4f53");

    model.component("comp1").multiphysics().create("mfrm1", "MagneticForcesRotatingMachinery", 2);
    model.component("comp1").multiphysics("mfrm1").set("Solid_physics", "mbd");
    model.component("comp1").multiphysics("mfrm1").set("MagneticFields_physics", "rmm");
    model.component("comp1").multiphysics("mfrm1").selection().all();

    model.component("comp1").common().create("rotb1", "RotatingBoundary");
    model.component("comp1").common("rotb1").selection().set();
    model.component("comp1").common().create("free1", "DeformingDomain");
    model.component("comp1").common("free1").set("smoothingType", "hyperelastic");
    model.component("comp1").common("free1").selection().set();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/mbd", true);
    model.study("std1").feature("stat").setSolveFor("/physics/rmm", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/mfrm1", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Np", "10", "\u6781\u6570");
    model.param().set("Ns", "12", "\u69fd\u6570");
    model.param().set("APnr", "5", "\u8f6c\u5b50\u6c14\u7a74\u6570");
    model.param().set("APfct", "0.7", "\u6c14\u7a74\u5bbd\u5ea6\u4e0e\u53e0\u7247\u5bbd\u5ea6\u4e4b\u6bd4");
    model.param().set("geom_scale", "5", "\u51e0\u4f55\u6bd4\u4f8b");
    model.param().set("L", "300[mm]", "\u7535\u673a\u7684\u9762\u5916\u539a\u5ea6");
    model.param().set("Ipk", "10[A]", "\u76f8\u7535\u6d41\u5cf0\u503c");
    model.param().set("init_ang", "198[deg]", "\u5cf0\u503c\u626d\u77e9\u7684\u521d\u59cb\u7535\u6d41\u89d2");
    model.param().set("w_rot", "700[rpm]", "\u8f6c\u901f");
    model.param().set("f_el", "w_rot*Np/2", "\u7535\u9891\u7387");
    model.param().set("t_ramp", "1/f_el/20", "\u52a0\u901f\u65f6\u95f4");
    model.param().set("N_tsteps", "72", "\u65f6\u6b65\u6570");
    model.param().set("t_step", "1/f_el/N_tsteps", "\u65f6\u6b65");
    model.param().set("t_end", "3/f_el", "\u4eff\u771f\u65f6\u95f4");
    model.param().set("t", "0[s]", "\u65f6\u95f4\u53d8\u91cf\u522b\u540d");

    model.geom()
         .load(new String[]{"part1"}, "ACDC_Module\\Rotating_Machinery_2D\\Rotors\\Internal\\surface_mounted_magnet_internal_rotor_2d.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "number_of_poles", "Np");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "number_of_modeled_poles", "Np");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "magnet_h", "1.5[mm]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "magnet_w", "7[mm]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_shaft.dom", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_rotor_magnets", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_rotor_air.dom", true);
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("spl1", "Split");
    model.component("comp1").geom("geom1").feature("spl1").selection("input").set("pi1");
    model.component("comp1").geom("geom1").run("spl1");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", 11.8);
    model.component("comp1").geom("geom1").feature("c1").set("angle", "360/APnr*APfct");
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").feature().duplicate("c2", "c1");
    model.component("comp1").geom("geom1").feature("c2").set("r", 7);
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("c1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("c2");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("point").set("dif1", 1, 2, 3, 4);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", "2/APnr");
    model.component("comp1").geom("geom1").run("fil1");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1")
         .label("\u65cb\u8f6c\uff1a\u8f6c\u5b50\u7a7a\u6c14\u901a\u9053");
    model.component("comp1").geom("geom1").feature("rot1")
         .set("rot", "range((360/APnr-360/APnr*APfct)/2,360/APnr,360)");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("fil1");
    model.component("comp1").geom("geom1").feature("rot1").set("selresult", true);
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").feature().duplicate("dif2", "dif1");
    model.component("comp1").geom("geom1").feature("dif2").label("\u5dee\u96c6\uff1a\u8f6c\u5b50\u94c1\u82af");
    model.component("comp1").geom("geom1").feature("dif2").selection("input").set("spl1(12)");
    model.component("comp1").geom("geom1").feature("dif2").selection("input2").set("rot1");
    model.component("comp1").geom("geom1").feature("dif2").set("keepsubtract", true);
    model.component("comp1").geom("geom1").feature("dif2").set("selresult", true);
    model.component("comp1").geom("geom1").run("dif2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").label("\u5e76\u96c6\uff1a\u8f6c\u5b50");
    model.component("comp1").geom("geom1").feature("uni1").selection("input")
         .set("dif2", "rot1", "spl1(1)", "spl1(10)", "spl1(11)", "spl1(13)", "spl1(2)", "spl1(3)", "spl1(4)", "spl1(5)", "spl1(6)", "spl1(7)", "spl1(8)", "spl1(9)");
    model.component("comp1").geom("geom1").feature("uni1").set("selresult", true);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("disksel1", "DiskSelection");
    model.component("comp1").geom("geom1").feature("disksel1").label("\u65cb\u8f6c\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("disksel1").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("disksel1").set("r", "inf");
    model.component("comp1").geom("geom1").feature("disksel1").set("rin", "30.5/2*0.99");
    model.component("comp1").geom("geom1").nodeGroup().create("grp1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").placeAfter(null);
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("pi1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("spl1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("c1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("c2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("dif1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("fil1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("rot1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("dif2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("uni1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("disksel1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").label("\u5b9a\u5b50");
    model.geom()
         .load(new String[]{"part2"}, "ACDC_Module\\Rotating_Machinery_2D\\Stators\\External\\slotted_external_stator_2d.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").run("disksel1");
    model.component("comp1").geom("geom1").create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").nodeGroup("grp1").remove("pi2", false);
    model.component("comp1").geom("geom1").feature("pi2").set("part", "part2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "number_of_slots", "Ns");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "number_of_modeled_slots", "Ns");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "slot_winding_type", 2);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "Arkkio_toggle", 1);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selkeepdom", "pi2_stator_iron.dom", true);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selkeepdom", "pi2_stator_slots", true);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selkeepdom", "pi2_all.dom", true);
    model.component("comp1").geom("geom1").run("pi2");
    model.component("comp1").geom("geom1").create("sca1", "Scale");
    model.component("comp1").geom("geom1").feature("sca1").selection("input").set("pi2", "uni1");
    model.component("comp1").geom("geom1").feature("sca1").set("isotropic", "geom_scale");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").func().create("rm1", "Ramp");
    model.component("comp1").func("rm1").set("slope", "1/t_ramp");
    model.component("comp1").func("rm1").set("cutoffactive", true);

    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u7ed3\u6784\u57df");
    model.component("comp1").selection("uni1")
         .set("input", new String[]{"geom1_pi1_rotor_magnets", "geom1_dif2_dom"});
    model.component("comp1").selection().duplicate("uni2", "uni1");
    model.component("comp1").selection("uni2").label("\u53d8\u5f62\u57df");
    model.component("comp1").selection("uni2")
         .set("input", new String[]{"geom1_pi1_rotor_air_dom", "geom1_rot1_dom"});
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").label("\u8f74\u8fb9\u754c");
    model.component("comp1").selection("adj1").set("input", new String[]{"geom1_pi1_shaft_dom"});

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
    model.component("comp1").material("mat2").label("Silicon Steel NGO 35PN440");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"2.631578[MS/m]", "0", "0", "0", "2.631578[MS/m]", "0", "0", "0", "2.631578[MS/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1[1]", "0", "0", "0", "1[1]", "0", "0", "0", "1[1]"});
    model.component("comp1").material("mat2").propertyGroup("BHCurve").label("B-H Curve");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH")
         .set("funcnametable", new String[][]{{"BHCurve1", "1"}});
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH")
         .set("table", new String[][]{{"0", "0"}, 
         {"20", "0.059405941"}, 
         {"22.5", "0.0730455860807292"}, 
         {"25", "0.0868399341458333"}, 
         {"27.5", "0.100943688179687"}, 
         {"30", "0.115511551166667"}, 
         {"32.5", "0.130852929054687"}, 
         {"35", "0.147896039645833"}, 
         {"37.5", "0.167723803705729"}, 
         {"40", "0.191419142"}, 
         {"42.5260416666667", "0.220052083388021"}, 
         {"45.2083333333333", "0.254641089104167"}, 
         {"48.203125", "0.296191728476563"}, 
         {"51.6666666666667", "0.345709570833333"}, 
         {"55.7291666666667", "0.403671617015625"}, 
         {"60.4166666666667", "0.468440593916667"}, 
         {"65.7291666666667", "0.537850659942708"}, 
         {"71.6666666666667", "0.6097359735"}, 
         {"78.3854166666667", "0.682459261479167"}, 
         {"86.6666666666667", "0.756497524708333"}, 
         {"97.4479166666667", "0.8328563325"}, 
         {"111.666666666667", "0.912541254166667"}, 
         {"129.921875", "0.995539397796875"}, 
         {"151.458333333333", "1.07776402658333"}, 
         {"175.182291666667", "1.15410994249479"}, 
         {"200", "1.2194719475"}, 
         {"225.260416666667", "1.27022741370313"}, 
         {"252.083333333333", "1.30868399375"}, 
         {"282.03125", "1.33863191042188"}, 
         {"316.666666666667", "1.3638613865"}, 
         {"357.03125", "1.38744069756771"}, 
         {"402.083333333333", "1.40955033041667"}, 
         {"450.260416666667", "1.42964882464062"}, 
         {"500", "1.44719471983333"}, 
         {"550.260416666667", "1.46186571813281"}, 
         {"602.083333333333", "1.47421617185417"}, 
         {"657.03125", "1.48501959585677"}, 
         {"716.666666666667", "1.495049505"}, 
         {"783.854166666667", "1.50504073840104"}, 
         {"866.666666666667", "1.51557343220833"}, 
         {"974.479166666667", "1.52718904682813"}, 
         {"1116.66666666667", "1.54042904266667"}, 
         {"1299.21875", "1.55557704184375"}, 
         {"1514.58333333333", "1.57188531333333"}, 
         {"1751.82291666667", "1.58834828782292"}, 
         {"2000", "1.603960396"}, 
         {"2252.60416666667", "1.61801258257552"}, 
         {"2520.83333333333", "1.63098184835417"}, 
         {"2820.3125", "1.64364170816406"}, 
         {"3166.66666666667", "1.65676567683333"}, 
         {"3570.3125", "1.67092099857292"}, 
         {"4020.83333333333", "1.685849835125"}, 
         {"4502.60416666667", "1.70108807761458"}, 
         {"5000", "1.71617161716667"}, 
         {"5502.60416666667", "1.73076526403646"}, 
         {"6020.83333333333", "1.745049505"}, 
         {"6570.3125", "1.75933374596354"}, 
         {"7166.66666666667", "1.77392739283333"}, 
         {"7838.54166666667", "1.7891527434401"}, 
         {"8666.66666666667", "1.8053836633125"}, 
         {"9744.79166666667", "1.82300690990365"}, 
         {"11166.6666666667", "1.84240924066667"}, 
         {"12992.1875", "1.86368089902344"}, 
         {"15145.8333333333", "1.88572607227083"}, 
         {"17518.2291666667", "1.90715243367448"}, 
         {"20000", "1.9265676565"}, 
         {"22552.0833333333", "1.9429661714349"}, 
         {"25416.6666666667", "1.95688943885417"}, 
         {"28906.25", "1.96926567655469"}, 
         {"33333.3333333333", "1.98102310233333"}, 
         {"38843.7447916667", "1.99281304837785"}, 
         {"44916.625", "2.00417930443947"}, 
         {"50864.4427083333", "2.01438877466029"}, 
         {"55999.6666666667", "2.02270836318242"}, 
         {"60093.015625", "2.02898975888627"}, 
         {"64748.2083333333", "2.03542378960551"}, 
         {"72027.2135416667", "2.04478606791211"}, 
         {"83992", "2.05985220637807"}, 
         {"102079.71875", "2.08258190411622"}, 
         {"125228.25", "2.11167120640287"}, 
         {"151750.65625", "2.14500024505519"}, 
         {"179960", "2.18044915189034"}});
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
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("RemanentFluxDensity", "RemanentFluxDensity", "Remanent flux density");
    model.component("comp1").material("mat3").propertyGroup("RemanentFluxDensity").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat3").label("BMN-42");
    model.component("comp1").material("mat3").set("family", "chrome");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"9.0[W/(m*K)]", "0", "0", "0", "9.0[W/(m*K)]", "0", "0", "0", "9.0[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "7.55[g/cm^3]");
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "440[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"1/1.50[uohm*m]", "0", "0", "0", "1/1.50[uohm*m]", "0", "0", "0", "1/1.50[uohm*m]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("RemanentFluxDensity").label("Remanent flux density");
    model.component("comp1").material("mat3").propertyGroup("RemanentFluxDensity").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat3").propertyGroup("RemanentFluxDensity").func("int1")
         .set("funcname", "Br");
    model.component("comp1").material("mat3").propertyGroup("RemanentFluxDensity").func("int1")
         .set("table", new String[][]{{"293.15", "1.330"}, {"353.15", "1.23"}});
    model.component("comp1").material("mat3").propertyGroup("RemanentFluxDensity").func("int1")
         .set("extrap", "linear");
    model.component("comp1").material("mat3").propertyGroup("RemanentFluxDensity").func("int1")
         .set("fununit", new String[]{"T"});
    model.component("comp1").material("mat3").propertyGroup("RemanentFluxDensity").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat3").propertyGroup("RemanentFluxDensity")
         .set("murec", new String[]{"1.05", "0", "0", "0", "1.05", "0", "0", "0", "1.05"});
    model.component("comp1").material("mat3").propertyGroup("RemanentFluxDensity").set("normBr", "Br(T)");
    model.component("comp1").material("mat3").propertyGroup("RemanentFluxDensity").addInput("temperature");
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").material("mat4").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat4").label("High-strength alloy steel");
    model.component("comp1").material("mat4").set("family", "custom");
    model.component("comp1").material("mat4")
         .set("customspecular", new double[]{0.9803921568627451, 0.9803921568627451, 0.9803921568627451});
    model.component("comp1").material("mat4").set("diffuse", "custom");
    model.component("comp1").material("mat4")
         .set("customdiffuse", new double[]{0.9803921568627451, 0.9803921568627451, 0.9803921568627451});
    model.component("comp1").material("mat4").set("ambient", "custom");
    model.component("comp1").material("mat4")
         .set("customambient", new double[]{0.5882352941176471, 0.5882352941176471, 0.5882352941176471});
    model.component("comp1").material("mat4").set("noise", true);
    model.component("comp1").material("mat4").set("fresnel", 0.99);
    model.component("comp1").material("mat4").set("roughness", 0.12);
    model.component("comp1").material("mat4").set("diffusewrap", 0);
    model.component("comp1").material("mat4").set("reflectance", 0);
    model.component("comp1").material("mat4").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat4").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat4").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat4").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("E", "200[GPa]");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("nu", "0.30");
    model.component("comp1").material("mat4").propertyGroup("Murnaghan").set("l", "-300[GPa]");
    model.component("comp1").material("mat4").propertyGroup("Murnaghan").set("m", "-620[GPa]");
    model.component("comp1").material("mat4").propertyGroup("Murnaghan").set("n", "-720[GPa]");
    model.component("comp1").material("mat2").selection().set(2, 35);
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", new String[]{"195[GPa]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", new String[]{"0.25"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"7700"});
    model.component("comp1").material("mat2").propertyGroup("def").set("electricconductivity", new String[]{"0"});
    model.component("comp1").material("mat3").selection().named("geom1_pi1_rotor_magnets");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", new String[]{"160[GPa]"});
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", new String[]{"0.24"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", new String[]{"7600"});
    model.component("comp1").material("mat4").selection().named("geom1_pi1_shaft_dom");

    model.component("comp1").physics("mbd").selection().named("uni1");
    model.component("comp1").physics("mbd").prop("d").set("d", "L");
    model.component("comp1").physics("mbd").feature("rotf1").selection().named("uni1");
    model.component("comp1").physics("mbd").feature("rotf1").set("RotationalFrequency", "RevolutionsPerTime");
    model.component("comp1").physics("mbd").feature("rotf1").set("rpt", "w_rot*rm1(t[1/s])");
    model.component("comp1").physics("mbd").create("fix1", "Fixed", 1);
    model.component("comp1").physics("mbd").feature("fix1").selection().named("adj1");
    model.component("comp1").physics("rmm").prop("d").set("d", "L");
    model.component("comp1").physics("rmm").create("al2", "AmperesLaw", 2);
    model.component("comp1").physics("rmm").feature("al2").selection().set(2, 35);
    model.component("comp1").physics("rmm").feature("al2").set("ConstitutiveRelationBH", "BHCurve");
    model.component("comp1").physics("rmm").create("cmag1", "ConductingMagnet", 2);
    model.component("comp1").physics("rmm").feature("cmag1").selection().named("geom1_pi1_rotor_magnets");
    model.component("comp1").physics("rmm").feature("cmag1").set("PatternType", "CircularPattern");
    model.component("comp1").physics("rmm").feature("cmag1").set("PeriodicType", "Alternating");
    model.component("comp1").physics("rmm").feature("cmag1").feature("north1").selection().set(326);
    model.component("comp1").physics("rmm").feature("cmag1").feature("south1").selection().set(323);
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

    model.component("comp1").common("free1").selection().named("uni2");
    model.component("comp1").common("rotb1").selection().named("geom1_disksel1");
    model.component("comp1").common("rotb1").set("rotationType", "rotationalVelocity");
    model.component("comp1").common("rotb1").set("rotationalVelocityExpression", "generalRevolutionsPerTime");
    model.component("comp1").common("rotb1").set("revolutionsPerTime", "w_rot*rm1(t[1/s])");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 10);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 1);
    model.component("comp1").mesh("mesh1").feature("size").set("hcurve", 0.5);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(30);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", 1.3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("tlist", "range(0,t_step,t_end)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("bwinitstepfrac", 0.01);
    model.sol("sol1").feature("t1").feature("se1").feature("ss2").set("subdtech", "auto");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u4f4d\u79fb (mbd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u901f\u5ea6 (mbd)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u57df\u7f16\u53f7");
    model.result("pg2").feature("surf1").set("descractive", true);
    model.result("pg2").feature("surf1").set("expr", "mod(dom,10)");
    model.result("pg2").feature("surf1").set("descr", "\u57df\u7f16\u53f7");
    model.result("pg2").feature("surf1").set("rangecoloractive", "on");
    model.result("pg2").feature("surf1").set("rangecolormin", -0.5);
    model.result("pg2").feature("surf1").set("rangecolormax", 9.5);
    model.result("pg2").feature("surf1").set("colortable", "Cyclic");
    model.result("pg2").feature("surf1").set("colorlegend", false);
    model.result("pg2").feature("surf1").set("colortabletype", "discrete");
    model.result("pg2").feature("surf1").set("smooth", "none");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature().create("arwl1", "ArrowLine");
    model.result("pg2").feature("arwl1").label("\u7ebf\u4e0a\u7bad\u5934");
    model.result("pg2").feature("arwl1").set("expr", new String[]{"mbd.u_tX", "mbd.u_tY"});
    model.result("pg2").feature("arwl1").set("placement", "elements");
    model.result("pg2").feature("arwl1").set("data", "parent");
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
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341, 342, 343, 344, 345, 346, 347, 348, 349, 350, 351, 352, 353, 354, 355, 356, 357, 358, 359, 360, 361, 362, 363, 364, 365, 366, 367, 368, 369, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 380);
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
         .set(29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45);
    model.result("pg4").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg4").feature("mesh1").set("qualexpr", "comp1.spatial.relVol");
    model.result("pg4").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg1").run();

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg1").run();
    model.result("pg1").create("arwl1", "ArrowLine");
    model.result("pg1").feature("arwl1").set("arrowcount", 1500);
    model.result("pg1").feature("arwl1").set("scaleactive", true);
    model.result("pg1").feature("arwl1").set("scale", 2000);
    model.result("pg1").feature("arwl1").set("color", "blue");
    model.result().configuration().create("gsty1", "GraphStyle");
    model.result().configuration("gsty1").set("linewidth", "unspecified");
    model.result().configuration("gsty1").set("linecolor", "cycle");
    model.result().configuration("gsty1").set("autodescr", true);
    model.result().configuration("gsty1").set("autosolution", false);
    model.result().configuration("gsty1").set("linewidth", 2);
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u4f4d\u79fb");
    model.result("pg5").set("titletype", "none");
    model.result("pg5").set("styleconfig", "gsty1");
    model.result("pg5").create("ptgr1", "PointGraph");
    model.result("pg5").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg5").feature("ptgr1").set("linewidth", "preference");
    model.result("pg5").feature("ptgr1").selection().set(222);
    model.result("pg5").feature("ptgr1").set("expr", "u");
    model.result("pg5").feature("ptgr1").set("legend", true);
    model.result("pg5").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg5").feature("ptgr1").setIndex("legends", "X \u5206\u91cf", 0);
    model.result("pg5").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr2").set("expr", "v");
    model.result("pg5").feature("ptgr2").setIndex("legends", "Y \u5206\u91cf", 0);
    model.result("pg5").run();
    model.result("pg5").set("twoyaxes", true);
    model.result("pg5").setIndex("plotonsecyaxis", true, 1, 1);
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("yseclabelactive", true);
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u626d\u77e9");
    model.result("pg6").set("titletype", "none");
    model.result("pg6").set("styleconfig", "gsty1");
    model.result("pg6").set("showlegends", false);
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").setIndex("expr", "rmm.Tark_1", 0);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").set("axislimits", true);
    model.result("pg6").set("xmin", 0);
    model.result("pg6").set("ymin", 0);
    model.result("pg6").run();
    model.result("pg1").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").add("plotgroup", "pg5");
    model.nodeGroup("grp1").label("\u7ed3\u6784\u56fe");

    model.result("pg3").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup().move("grp2", 1);
    model.nodeGroup("grp2").add("plotgroup", "pg3");
    model.nodeGroup("grp2").add("plotgroup", "pg6");
    model.nodeGroup("grp2").label("\u7535\u78c1\u56fe");

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
    model.result().export("anim1").showFrame();
    model.result().export("anim1").label("\u4f4d\u79fb (mbd)");
    model.result().export("anim1").set("maxframes", 50);
    model.result().export().duplicate("anim2", "anim1");
    model.result().export("anim2").showFrame();
    model.result().export("anim2").label("\u78c1\u901a\u5bc6\u5ea6\u6a21 (rmm)");
    model.result().export("anim2").set("plotgroup", "pg3");
    model.result("pg3").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 140, 0);
    model.result("pg1").run();

    model.title("\u6c38\u78c1\u7535\u673a\u7684\u78c1-\u7ed3\u6784\u76f8\u4e92\u4f5c\u7528");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u5c06\u201c\u591a\u4f53\u52a8\u529b\u5b66\u201d\u4e0e\u201c\u65cb\u8f6c\u673a\u68b0\uff0c\u78c1\u201d\u76f8\u8026\u5408\uff0c\u4ee5\u6267\u884c\u7535\u78c1\u548c\u673a\u68b0\u5206\u6790\u3002\u5176\u4e2d\u5bf9\u5177\u6709 10 \u4e2a\u8f6c\u5b50\u6781\u548c 12 \u4e2a\u5b9a\u5b50\u69fd\u7684\u6c38\u78c1\u7535\u673a\u8fdb\u884c\u4e8c\u7ef4\u5efa\u6a21\u3002\u78c1\u4f53\u88ab\u56fa\u5b9a\u5728\u8f6c\u5b50\u7684\u5706\u5468\u4e0a\u3002\u4e3a\u4e86\u6a21\u62df\u4e0e\u52a8\u7f51\u683c\u96c6\u6210\u7684\u78c1-\u7ed3\u6784\u8026\u5408\uff0c\u5176\u4e2d\u5c06\u7535\u78c1\u529b\u4f20\u9012\u5230\u8f6c\u5b50\uff0c\u5e76\u5c06\u8f6c\u5b50\u8fd0\u52a8\u4f20\u9012\u5230\u52a8\u7f51\u683c\u3002\u672c\u4f8b\u9488\u5bf9\u4e09\u4e2a\u5b8c\u6574\u7684\u7535\u5468\u671f\u6c42\u89e3\u77ac\u6001\u95ee\u9898\uff0c\u4ee5\u8ba1\u7b97\u78c1\u901a\u5bc6\u5ea6\u548c\u4f4d\u79fb\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("pm_motor_2d_structure_interaction.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
