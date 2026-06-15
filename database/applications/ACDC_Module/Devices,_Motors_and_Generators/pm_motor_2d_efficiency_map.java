/*
 * pm_motor_2d_efficiency_map.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:20 by COMSOL 6.3.0.290. */
public class pm_motor_2d_efficiency_map {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Devices,_Motors_and_Generators");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").lengthUnit("mm");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Np", "10", "\u6781\u6570");
    model.param().set("Ns", "12", "\u69fd\u6570");
    model.param().set("L", "50[mm]", "\u7535\u673a\u7684\u9762\u5916\u539a\u5ea6");
    model.param().set("init_ang", "198[deg]", "\u521d\u59cb\u7535\u6d41\u89d2");
    model.param().set("w_rot", "12000[rpm]", "\u8f74\u8f6c\u901f");
    model.param().set("f_el", "w_rot*(Np/2)", "\u7535\u9891\u7387");
    model.param().set("Ipk", "30[A]", "\u76f8\u7535\u6d41\u5cf0\u503c");
    model.param().set("Nturn", "10", "\u6bcf\u4e2a\u7ebf\u5708\u7684\u531d\u6570");
    model.param().set("ff_slot", "0.8", "\u69fd\u586b\u5145\u56e0\u5b50");
    model.param().set("lam_rho", "7500[kg/m^3]", "\u53e0\u7247\u94c1\u82af\u5bc6\u5ea6");
    model.param().set("Nframes", "10", "\u65f6\u95f4\u6846\u67b6\u6570");
    model.param().set("Cu_rho0", "1.667e-8[ohm*m]", "\u94dc\u53c2\u8003\u7535\u963b\u7387");
    model.param().set("Cu_alpha", "3.862e-3[1/K]", "\u94dc\u7535\u963b\u7387\u6e29\u5ea6\u7cfb\u6570");
    model.param().set("Cu_Tref", "293.15[K]", "\u94dc\u53c2\u8003\u6e29\u5ea6");
    model.param()
         .set("Nsec", "gcd(Np,Ns)", "\u5efa\u6a21\u6247\u533a\u6570\uff081 = \u5b8c\u6574\u5faa\u73af\uff09");
    model.param().set("PM_Br_ref", "1.31[T]", "\u78c1\u4f53\u53c2\u8003\u5269\u4f59\u78c1\u901a\u5bc6\u5ea6");
    model.param().set("PM_alpha", "-0.12[%/K]", "\u78c1\u4f53\u5269\u78c1\u6e29\u5ea6\u7cfb\u6570");
    model.param().set("PM_Tref", "20[degC]", "\u78c1\u4f53\u53c2\u8003\u6e29\u5ea6");

    model.geom()
         .load(new String[]{"part1"}, "ACDC_Module\\Rotating_Machinery_2D\\Rotors\\Internal\\surface_mounted_magnet_internal_rotor_2d.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "number_of_poles", "Np");
    model.component("comp1").geom("geom1").feature("pi1")
         .setEntry("inputexpr", "number_of_modeled_poles", "Np/Nsec");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_shaft.dom", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_rotor_iron.dom", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_rotor_magnets", true);
    model.component("comp1").geom("geom1").feature("pi1")
         .setEntry("selkeepdom", "pi1_rotor_solid_domains.dom", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_rotor_air.dom", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_all.dom", true);
    model.geom()
         .load(new String[]{"part2"}, "ACDC_Module\\Rotating_Machinery_2D\\Stators\\External\\slotted_external_stator_2d.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi2").set("part", "part2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "number_of_slots", "Ns");
    model.component("comp1").geom("geom1").feature("pi2")
         .setEntry("inputexpr", "number_of_modeled_slots", "Ns/Nsec");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "slot_winding_type", 2);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selkeepdom", "pi2_stator_iron.dom", true);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selkeepdom", "pi2_stator_slots", true);
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u5b9a\u5b50\u5916\u58f3");
    model.component("comp1").selection("sel1").set(1);
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u56fa\u4f53\u6750\u6599");
    model.component("comp1").selection("uni1")
         .set("input", new String[]{"sel1", "geom1_pi1_shaft_dom", "geom1_pi1_rotor_iron_dom", "geom1_pi1_rotor_magnets", "geom1_pi2_stator_iron_dom", "geom1_pi2_stator_slots"});
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").label("\u56fa\u4f53\u6750\u6599 - \u5916\u90e8\u8fb9\u754c");
    model.component("comp1").selection("adj1").set("input", new String[]{"uni1"});
    model.component("comp1").selection().create("disk1", "Disk");
    model.component("comp1").selection("disk1").label("\u6c14\u9699\u70ed\u901a\u91cf\u8fb9\u754c");
    model.component("comp1").selection("disk1").set("entitydim", 1);
    model.component("comp1").selection("disk1").set("inputent", "selections");
    model.component("comp1").selection("disk1").set("input", new String[]{"adj1"});
    model.component("comp1").selection("disk1").set("r", 18);
    model.component("comp1").selection("disk1").set("rin", 11);
    model.component("comp1").selection("disk1").set("condition", "inside");
    model.component("comp1").selection().create("disk2", "Disk");
    model.component("comp1").selection("disk2").label("\u53e0\u7247\u94c1\u82af - \u5916\u58f3\u8fb9\u754c");
    model.component("comp1").selection("disk2").set("entitydim", 1);
    model.component("comp1").selection("disk2").set("r", 25.5);
    model.component("comp1").selection("disk2").set("rin", 24.5);
    model.component("comp1").selection("disk2").set("condition", "inside");
    model.component("comp1").selection().create("adj2", "Adjacent");
    model.component("comp1").selection("adj2").label("\u7ed5\u7ec4\u7edd\u7f18\u8fb9\u754c");
    model.component("comp1").selection("adj2").set("input", new String[]{"geom1_pi2_stator_slots"});
    model.component("comp1").selection().create("disk3", "Disk");
    model.component("comp1").selection("disk3").label("\u6c34\u5957 - \u5916\u90e8\u8fb9\u754c");
    model.component("comp1").selection("disk3").set("entitydim", 1);
    model.component("comp1").selection("disk3").set("r", 28.8);
    model.component("comp1").selection("disk3").set("rin", 27.5);
    model.component("comp1").selection("disk3").set("condition", "inside");
    model.component("comp1").selection().create("uni2", "Union");
    model.component("comp1").selection("uni2").label("\u5b9a\u5b50\u56fa\u4f53\u6750\u6599");
    model.component("comp1").selection("uni2")
         .set("input", new String[]{"sel1", "geom1_pi2_stator_iron_dom", "geom1_pi2_stator_slots"});

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").label("\u5e73\u5747\u503c 1 - \u7ed5\u7ec4");
    model.component("comp1").cpl("aveop1").selection().named("geom1_pi2_stator_slots");
    model.component("comp1").cpl().create("aveop2", "Average");
    model.component("comp1").cpl("aveop2").set("axisym", true);
    model.component("comp1").cpl("aveop2").label("\u5e73\u5747\u503c 2 - \u5b9a\u5b50\u56fa\u4f53\u6750\u6599");
    model.component("comp1").cpl("aveop2").selection().named("uni2");

    model.component("comp1").physics().create("mmtp", "MagneticMachineryTimePeriodic", "geom1");
    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

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
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat3").label("Copper");
    model.component("comp1").material("mat3").set("family", "copper");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("emissivity", "0.5");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "8940[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", "126e9[Pa]");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", "0.34");
    model.component("comp1").material("mat3").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp1").material("mat3").propertyGroup("linzRes").set("rho0", "1.667e-8[ohm*m]");
    model.component("comp1").material("mat3").propertyGroup("linzRes").set("alpha", "3.862e-3[1/K]");
    model.component("comp1").material("mat3").propertyGroup("linzRes").set("Tref", "293.15[K]");
    model.component("comp1").material("mat3").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").propertyGroup()
         .create("RemanentFluxDensity", "RemanentFluxDensity", "Remanent flux density");
    model.component("comp1").material("mat4").label("N54 (Sintered NdFeB)");
    model.component("comp1").material("mat4").set("family", "chrome");
    model.component("comp1").material("mat4").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("electricconductivity", new String[]{"1/1.4[uohm*m]", "0", "0", "0", "1/1.4[uohm*m]", "0", "0", "0", "1/1.4[uohm*m]"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat4").propertyGroup("RemanentFluxDensity").label("Remanent flux density");
    model.component("comp1").material("mat4").propertyGroup("RemanentFluxDensity")
         .set("murec", new String[]{"1.05", "0", "0", "0", "1.05", "0", "0", "0", "1.05"});
    model.component("comp1").material("mat4").propertyGroup("RemanentFluxDensity").set("normBr", "1.47[T]");
    model.component("comp1").material().create("mat5", "Common");
    model.component("comp1").material("mat5").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat5").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat5").label("High-strength alloy steel");
    model.component("comp1").material("mat5").set("family", "custom");
    model.component("comp1").material("mat5")
         .set("customspecular", new double[]{0.9803921568627451, 0.9803921568627451, 0.9803921568627451});
    model.component("comp1").material("mat5").set("diffuse", "custom");
    model.component("comp1").material("mat5")
         .set("customdiffuse", new double[]{0.9803921568627451, 0.9803921568627451, 0.9803921568627451});
    model.component("comp1").material("mat5").set("ambient", "custom");
    model.component("comp1").material("mat5")
         .set("customambient", new double[]{0.5882352941176471, 0.5882352941176471, 0.5882352941176471});
    model.component("comp1").material("mat5").set("noise", true);
    model.component("comp1").material("mat5").set("fresnel", 0.99);
    model.component("comp1").material("mat5").set("roughness", 0.12);
    model.component("comp1").material("mat5").set("diffusewrap", 0);
    model.component("comp1").material("mat5").set("reflectance", 0);
    model.component("comp1").material("mat5").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat5").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat5").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat5").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat5").propertyGroup("Enu").set("E", "200[GPa]");
    model.component("comp1").material("mat5").propertyGroup("Enu").set("nu", "0.30");
    model.component("comp1").material("mat5").propertyGroup("Murnaghan").set("l", "-300[GPa]");
    model.component("comp1").material("mat5").propertyGroup("Murnaghan").set("m", "-620[GPa]");
    model.component("comp1").material("mat5").propertyGroup("Murnaghan").set("n", "-720[GPa]");
    model.component("comp1").material("mat2").selection().set(2, 18);
    model.component("comp1").material("mat2").propertyGroup("def").set("thermalconductivity", new String[]{"20"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", new String[]{"1"});
    model.component("comp1").material("mat3").selection().named("geom1_pi2_stator_slots");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"2[W/(m*K)]"});
    model.component("comp1").material("mat4").selection().named("geom1_pi1_rotor_magnets");
    model.component("comp1").material("mat4").propertyGroup("def").set("thermalconductivity", new String[]{"9"});
    model.component("comp1").material("mat4").propertyGroup("def").set("density", new String[]{"1"});
    model.component("comp1").material("mat4").propertyGroup("def").set("heatcapacity", new String[]{"1"});
    model.component("comp1").material("mat5").selection().set(1, 20);

    model.component("comp1").view("view1").set("showmaterial", true);

    model.component("comp1").physics("mmtp").prop("d").set("d", "L");
    model.component("comp1").physics("mmtp").prop("TimePeriodicSettings").set("nTP", "Nframes");
    model.component("comp1").physics("mmtp").prop("TimePeriodicSettings").set("freqTP", "w_rot");
    model.component("comp1").physics("mmtp").prop("MotionSettings").set("NoPoles", "Np");
    model.component("comp1").physics("mmtp").create("rcon1", "RotationalContinuityPair", 1);
    model.component("comp1").physics("mmtp").create("rper1", "RotationalPeriodicity", 1);
    model.component("comp1").physics("mmtp").feature("rper1").selection()
         .set(1, 2, 10, 30, 31, 36, 121, 123, 127, 129, 132, 136);
    model.component("comp1").physics("mmtp").create("tprot1", "TimePeriodicRotatingDomain", 2);
    model.component("comp1").physics("mmtp").feature("tprot1")
         .set("TimePeriodicRotationType", "FullMechanicalRevolution");
    model.component("comp1").physics("mmtp").create("lc1", "LaminatedCore", 2);
    model.component("comp1").physics("mmtp").feature("lc1").selection().set(2, 18);
    model.component("comp1").physics("mmtp").create("mag1", "Magnet", 2);
    model.component("comp1").physics("mmtp").feature("mag1").selection().named("geom1_pi1_rotor_magnets");
    model.component("comp1").physics("mmtp").feature("mag1").set("PatternType", "CircularPattern");
    model.component("comp1").physics("mmtp").feature("mag1").set("PeriodicType", "Alternating");
    model.component("comp1").physics("mmtp").feature("mag1")
         .set("normBr_crel_BH_RemanentFluxDensity_mat", "userdef");
    model.component("comp1").physics("mmtp").feature("mag1")
         .set("normBr_crel_BH_RemanentFluxDensity", "PM_Br_ref*(1+PM_alpha*(T-PM_Tref))");
    model.component("comp1").physics("mmtp").feature("mag1").feature("north1").selection().set(163, 165, 166);
    model.component("comp1").physics("mmtp").feature("mag1").feature("south1").selection().set(161);
    model.component("comp1").physics("mmtp").create("wnd1", "MultiphaseWinding", 2);
    model.component("comp1").physics("mmtp").feature("wnd1").selection().named("geom1_pi2_stator_slots");
    model.component("comp1").physics("mmtp").feature("wnd1").set("Ipk", "Ipk");
    model.component("comp1").physics("mmtp").feature("wnd1").set("alpha_i", "init_ang");
    model.component("comp1").physics("mmtp").feature("wnd1").set("freq_signal", "f_el");
    model.component("comp1").physics("mmtp").feature("wnd1").set("WindingLayout", "automatic");
    model.component("comp1").physics("mmtp").feature("wnd1").set("NoSlots", "Ns");
    model.component("comp1").physics("mmtp").feature("wnd1").runCommand("addPhases");
    model.component("comp1").physics("mmtp").feature("wnd1").set("N", "Nturn");
    model.component("comp1").physics("mmtp").feature("wnd1")
         .set("sigmaCoil", "1/(Cu_rho0*(1+Cu_alpha*(aveop1(T)-Cu_Tref)))");
    model.component("comp1").physics("mmtp").feature("wnd1").set("FillingFactor", "ff_slot");
    model.component("comp1").physics("ht").selection().named("uni1");
    model.component("comp1").physics("ht").prop("PhysicalModelProperty").set("dz", "L");
    model.component("comp1").physics("ht").prop("ShapeProperty").set("order_temperature", 1);
    model.component("comp1").physics("ht").create("sls1", "SolidLayeredShell", 1);
    model.component("comp1").physics("ht").feature("sls1")
         .label("\u8584\u5c42 1 - \u53e0\u7247\u94c1\u82af <> \u5916\u58f3");
    model.component("comp1").physics("ht").feature("sls1").selection().named("disk2");
    model.component("comp1").physics("ht").feature("sls1").set("lth_mat", "userdef");
    model.component("comp1").physics("ht").feature("sls1").set("lth", "0.5e-4[m]");
    model.component("comp1").physics("ht").feature("sls1").set("k_mat", "userdef");
    model.component("comp1").physics("ht").feature("sls1")
         .set("k", new double[]{0.02, 0, 0, 0, 0.02, 0, 0, 0, 0.02});
    model.component("comp1").physics("ht").create("sls2", "SolidLayeredShell", 1);
    model.component("comp1").physics("ht").feature("sls2").label("\u8584\u5c42 2 - \u7ed5\u7ec4\u7edd\u7f18");
    model.component("comp1").physics("ht").feature("sls2").selection().named("adj2");
    model.component("comp1").physics("ht").feature("sls2").set("lth_mat", "userdef");
    model.component("comp1").physics("ht").feature("sls2").set("lth", "2e-4[m]");
    model.component("comp1").physics("ht").feature("sls2").set("k_mat", "userdef");
    model.component("comp1").physics("ht").feature("sls2").set("k", new double[]{0.2, 0, 0, 0, 0.2, 0, 0, 0, 0.2});
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf1").label("\u70ed\u901a\u91cf 1 - \u6c34\u5957");
    model.component("comp1").physics("ht").feature("hf1").selection().named("disk3");
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", 500);
    model.component("comp1").physics("ht").feature("hf1").set("Text", "25[degC]");
    model.component("comp1").physics("ht").create("hf2", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf2").label("\u70ed\u901a\u91cf 2 - \u6c14\u9699");
    model.component("comp1").physics("ht").feature("hf2").selection().named("disk1");
    model.component("comp1").physics("ht").feature("hf2").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf2").set("h", 50);
    model.component("comp1").physics("ht").feature("hf2").set("Text", "aveop2(T)");
    model.component("comp1").physics("ht").create("pc1", "PeriodicHeat", 1);
    model.component("comp1").physics("ht").feature("pc1").selection().set(1, 2, 31, 36, 123, 127, 129, 132);

    model.component("comp1").multiphysics().create("emh1", "ElectromagneticHeating", 2);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hcurve", 0.6);
    model.component("comp1").mesh("mesh1").feature("size").set("hnarrow", 0.5);
    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature().move("size1", 1);
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("size1").selection().named("geom1_pi2_stator_iron_dom");
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", 1);
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/mmtp", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/emh1", true);
    model.study("std1")
         .label("\u7814\u7a76 1 - \u968f\u65f6\u95f4\u5e27\u6570\u53d8\u5316\u7684\u6536\u655b\u60c5\u51b5");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "Np", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "Np", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "Nframes", 0);
    model.study("std1").feature("param").setIndex("plistarr", "10 30 60 120 180 240", 0);
    model.study("std1").feature("param").set("paramselect", false);
    model.study("std1").feature("param").set("reusesol", true);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").create("se1", "Segregated");
    model.sol("sol1").feature("s1").feature("se1").feature("ssDef").label("\u78c1\u573a");
    model.sol("sol1").feature("s1").feature("se1").feature("ssDef")
         .set("segvar", new String[]{"comp1_mmtp_AZ_tp0", "comp1_mmtp_rcon1_AZLM_tp0", "comp1_mmtp_mag1_V_0"});
    model.sol("sol1").feature("s1").feature("se1").feature("ssDef").set("subtermconst", "tol");
    model.sol("sol1").feature("s1").feature("se1").create("ss1", "SegregatedStep");
    model.sol("sol1").feature("s1").feature("se1").feature("ss1").label("\u6e29\u5ea6\u573a");
    model.sol("sol1").feature("s1").feature("se1").feature("ss1")
         .set("segvar", new String[]{"comp1_ht_TextFace", "comp1_T"});

    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u78c1\u901a\u5bc6\u5ea6\u6a21 (mmtp)");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 6, 0);
    model.result("pg1").set("titletype", "label");
    model.result("pg1").set("edges", "off");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("expr", "mmtp.normB(phase)");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature("surf1").feature().create("def1", "Deform");
    model.result("pg1").feature("surf1").feature("def1")
         .set("expr", new String[]{"mmtp.displX(phase)", "mmtp.displY(phase)"});
    model.result("pg1").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg1").feature().create("str1", "Streamline");
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("expr", new String[]{"mmtp.BX(phase)", "mmtp.BY(phase)"});
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("udist", 0.03);
    model.result("pg1").feature("str1").set("smooth", "internal");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("data", "parent");
    model.result("pg1").feature("str1").selection().geom("geom1", 1);
    model.result("pg1").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166);
    model.result("pg1").feature("str1").feature().create("col1", "Color");
    model.result("pg1").feature("str1").feature("col1").set("expr", "mmtp.normB(phase)");
    model.result("pg1").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg1").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg1").feature("str1").feature().create("filt1", "Filter");
    model.result("pg1").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").feature("str1").feature().create("def1", "Deform");
    model.result("pg1").feature("str1").feature("def1")
         .set("expr", new String[]{"mmtp.displX(phase)", "mmtp.displY(phase)"});
    model.result("pg1").feature("str1").feature("def1").set("scaleactive", true);
    model.result("pg1").feature().create("con1", "Contour");
    model.result("pg1").feature("con1").set("showsolutionparams", "on");
    model.result("pg1").feature("con1").set("solutionparams", "parent");
    model.result("pg1").feature("con1").set("expr", "mmtp.AZ(phase)");
    model.result("pg1").feature("con1").set("number", 10);
    model.result("pg1").feature("con1").set("levelrounding", false);
    model.result("pg1").feature("con1").set("coloring", "uniform");
    model.result("pg1").feature("con1").set("colorlegend", false);
    model.result("pg1").feature("con1").set("color", "custom");
    model.result("pg1").feature("con1")
         .set("customcolor", new double[]{0.4117647111415863, 0.4117647111415863, 0.4117647111415863});
    model.result("pg1").feature("con1").set("smooth", "internal");
    model.result("pg1").feature("con1").set("showsolutionparams", "on");
    model.result("pg1").feature("con1").set("data", "parent");
    model.result("pg1").feature("con1").feature().create("filt1", "Filter");
    model.result("pg1").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").feature("con1").feature().create("def1", "Deform");
    model.result("pg1").feature("con1").feature("def1")
         .set("expr", new String[]{"mmtp.displX(phase)", "mmtp.displY(phase)"});
    model.result("pg1").feature("con1").feature("def1").set("scaleactive", true);
    model.result("pg1").feature().create("line1", "Line");
    model.result("pg1").feature("line1").set("showsolutionparams", "on");
    model.result("pg1").feature("line1").set("expr", "1");
    model.result("pg1").feature("line1").set("coloring", "uniform");
    model.result("pg1").feature("line1").set("color", "fromtheme");
    model.result("pg1").feature("line1").set("smooth", "internal");
    model.result("pg1").feature("line1").set("showsolutionparams", "on");
    model.result("pg1").feature("line1").set("data", "parent");
    model.result("pg1").feature("line1").feature().create("def1", "Deform");
    model.result("pg1").feature("line1").feature("def1")
         .set("expr", new String[]{"mmtp.displX(phase)", "mmtp.displY(phase)"});
    model.result("pg1").feature("line1").feature("def1").set("scaleactive", true);
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u6e29\u5ea6 (ht)");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 6, 0);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solutionparams", "parent");
    model.result("pg2").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").set("expr", new String[]{"mmtp.rcon1.Tax_tpavg"});
    model.result().evaluationGroup("eg1").feature("gev1")
         .set("descr", new String[]{"\u8f74\u5411\u626d\u77e9\uff0c\u65f6\u95f4\u5468\u671f\u5e73\u5747\u503c"});
    model.result().evaluationGroup("eg1").feature("gev1").set("unit", new String[]{"N*m"});
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "\u626d\u77e9", 0);
    model.result().evaluationGroup("eg1").create("int1", "IntSurface");
    model.result().evaluationGroup("eg1").feature("int1").set("intvolume", true);
    model.result().evaluationGroup("eg1").feature("int1").selection().named("geom1_pi2_stator_slots");
    model.result().evaluationGroup("eg1").feature("int1").setIndex("expr", "mmtp.Qh*L*Nsec", 0);
    model.result().evaluationGroup("eg1").feature("int1").setIndex("descr", "\u7ed5\u7ec4", 0);
    model.result().evaluationGroup("eg1").feature().duplicate("int2", "int1");
    model.result().evaluationGroup("eg1").feature("int2").selection().named("geom1_pi2_stator_iron_dom");
    model.result().evaluationGroup("eg1").feature("int2").setIndex("descr", "\u5b9a\u5b50\u94c1\u82af", 0);
    model.result().evaluationGroup("eg1").feature().duplicate("int3", "int2");
    model.result().evaluationGroup("eg1").feature("int3").selection().named("geom1_pi1_rotor_magnets");
    model.result().evaluationGroup("eg1").feature("int3").setIndex("descr", "\u78c1\u4f53", 0);
    model.result().evaluationGroup("eg1").feature().duplicate("int4", "int3");
    model.result().evaluationGroup("eg1").feature("int4").selection().named("geom1_pi1_rotor_iron_dom");
    model.result().evaluationGroup("eg1").feature("int4").setIndex("descr", "\u8f6c\u5b50\u94c1\u82af", 0);
    model.result().evaluationGroup("eg1").set("data", "dset2");
    model.result().evaluationGroup("eg1").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u968f\u65f6\u95f4\u5e27\u6570\u53d8\u5316\u7684\u6536\u655b\u60c5\u51b5");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u7535\u78c1\u635f\u8017 [W]");
    model.result("pg3").set("twoyaxes", true);
    model.result("pg3").set("legendpos", "middleright");
    model.result("pg3").create("tblp1", "Table");
    model.result("pg3").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg3").feature("tblp1").set("linewidth", "preference");
    model.result("pg3").feature("tblp1").set("source", "evaluationgroup");
    model.result("pg3").feature("tblp1").set("xaxisdata", 1);
    model.result("pg3").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg3").feature("tblp1").set("plotcolumns", new int[]{2});
    model.result("pg3").feature("tblp1").set("plotonsecyaxis", true);
    model.result("pg3").feature("tblp1").set("linewidth", 2);
    model.result("pg3").feature("tblp1").set("legend", true);
    model.result("pg3").feature().duplicate("tblp2", "tblp1");
    model.result("pg3").run();
    model.result("pg3").feature("tblp2").set("plotcolumns", new int[]{3, 4, 5, 6});
    model.result("pg3").feature("tblp2").set("plotonsecyaxis", false);
    model.result("pg3").run();

    model.param().set("Nframes", "120");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/mmtp", true);
    model.study("std2").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/emh1", true);
    model.study("std2").label("\u7814\u7a76 2 - \u6548\u7387\u56fe");
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").set("sweeptype", "filled");
    model.study("std2").feature("param").setIndex("pname", "Np", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "", 0);
    model.study("std2").feature("param").setIndex("pname", "Np", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "", 0);
    model.study("std2").feature("param").setIndex("pname", "w_rot", 0);
    model.study("std2").feature("param").setIndex("plistarr", "range(1200,3600,12000)", 0);
    model.study("std2").feature("param").setIndex("punit", "rpm", 0);
    model.study("std2").feature("param").setIndex("pname", "Np", 1);
    model.study("std2").feature("param").setIndex("plistarr", "", 1);
    model.study("std2").feature("param").setIndex("punit", "", 1);
    model.study("std2").feature("param").setIndex("pname", "Np", 1);
    model.study("std2").feature("param").setIndex("plistarr", "", 1);
    model.study("std2").feature("param").setIndex("punit", "", 1);
    model.study("std2").feature("param").setIndex("pname", "Ipk", 1);
    model.study("std2").feature("param").setIndex("plistarr", "range(3,6.75,30)", 1);
    model.study("std2").feature("param").set("reusesol", true);
    model.study("std2").showAutoSequences("all");

    model.sol("sol9").feature("s1").create("se1", "Segregated");
    model.sol("sol9").feature("s1").feature("se1").feature("ssDef").label("\u78c1\u573a");
    model.sol("sol9").feature("s1").feature("se1").feature("ssDef")
         .set("segvar", new String[]{"comp1_mmtp_AZ_tp0", "comp1_mmtp_rcon1_AZLM_tp0", "comp1_mmtp_mag1_V_0"});
    model.sol("sol9").feature("s1").feature("se1").feature("ssDef").set("subtermconst", "tol");
    model.sol("sol9").feature("s1").feature("se1").create("ss1", "SegregatedStep");
    model.sol("sol9").feature("s1").feature("se1").feature("ss1").label("\u6e29\u5ea6\u573a");
    model.sol("sol9").feature("s1").feature("se1").feature("ss1")
         .set("segvar", new String[]{"comp1_ht_TextFace", "comp1_T"});

    model.study("std2").createAutoSequences("all");

    model.sol("sol9").runAll();

    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u78c1\u901a\u5bc6\u5ea6\u6a21 (mmtp) 1");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").setIndex("looplevel", 5, 0);
    model.result("pg4").setIndex("looplevel", 4, 1);
    model.result("pg4").set("titletype", "label");
    model.result("pg4").set("edges", "off");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("solutionparams", "parent");
    model.result("pg4").feature("surf1").set("expr", "mmtp.normB(phase)");
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result("pg4").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").feature("surf1").feature().create("def1", "Deform");
    model.result("pg4").feature("surf1").feature("def1")
         .set("expr", new String[]{"mmtp.displX(phase)", "mmtp.displY(phase)"});
    model.result("pg4").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg4").feature().create("str1", "Streamline");
    model.result("pg4").feature("str1").set("showsolutionparams", "on");
    model.result("pg4").feature("str1").set("expr", new String[]{"mmtp.BX(phase)", "mmtp.BY(phase)"});
    model.result("pg4").feature("str1").set("posmethod", "uniform");
    model.result("pg4").feature("str1").set("udist", 0.03);
    model.result("pg4").feature("str1").set("smooth", "internal");
    model.result("pg4").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("showsolutionparams", "on");
    model.result("pg4").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("showsolutionparams", "on");
    model.result("pg4").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("showsolutionparams", "on");
    model.result("pg4").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("showsolutionparams", "on");
    model.result("pg4").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("data", "parent");
    model.result("pg4").feature("str1").selection().geom("geom1", 1);
    model.result("pg4").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166);
    model.result("pg4").feature("str1").feature().create("col1", "Color");
    model.result("pg4").feature("str1").feature("col1").set("expr", "mmtp.normB(phase)");
    model.result("pg4").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg4").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg4").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg4").feature("str1").feature().create("filt1", "Filter");
    model.result("pg4").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg4").feature("str1").feature().create("def1", "Deform");
    model.result("pg4").feature("str1").feature("def1")
         .set("expr", new String[]{"mmtp.displX(phase)", "mmtp.displY(phase)"});
    model.result("pg4").feature("str1").feature("def1").set("scaleactive", true);
    model.result("pg4").feature().create("con1", "Contour");
    model.result("pg4").feature("con1").set("showsolutionparams", "on");
    model.result("pg4").feature("con1").set("solutionparams", "parent");
    model.result("pg4").feature("con1").set("expr", "mmtp.AZ(phase)");
    model.result("pg4").feature("con1").set("number", 10);
    model.result("pg4").feature("con1").set("levelrounding", false);
    model.result("pg4").feature("con1").set("coloring", "uniform");
    model.result("pg4").feature("con1").set("colorlegend", false);
    model.result("pg4").feature("con1").set("color", "custom");
    model.result("pg4").feature("con1")
         .set("customcolor", new double[]{0.4117647111415863, 0.4117647111415863, 0.4117647111415863});
    model.result("pg4").feature("con1").set("smooth", "internal");
    model.result("pg4").feature("con1").set("showsolutionparams", "on");
    model.result("pg4").feature("con1").set("data", "parent");
    model.result("pg4").feature("con1").feature().create("filt1", "Filter");
    model.result("pg4").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg4").feature("con1").feature().create("def1", "Deform");
    model.result("pg4").feature("con1").feature("def1")
         .set("expr", new String[]{"mmtp.displX(phase)", "mmtp.displY(phase)"});
    model.result("pg4").feature("con1").feature("def1").set("scaleactive", true);
    model.result("pg4").feature().create("line1", "Line");
    model.result("pg4").feature("line1").set("showsolutionparams", "on");
    model.result("pg4").feature("line1").set("expr", "1");
    model.result("pg4").feature("line1").set("coloring", "uniform");
    model.result("pg4").feature("line1").set("color", "fromtheme");
    model.result("pg4").feature("line1").set("smooth", "internal");
    model.result("pg4").feature("line1").set("showsolutionparams", "on");
    model.result("pg4").feature("line1").set("data", "parent");
    model.result("pg4").feature("line1").feature().create("def1", "Deform");
    model.result("pg4").feature("line1").feature("def1")
         .set("expr", new String[]{"mmtp.displX(phase)", "mmtp.displY(phase)"});
    model.result("pg4").feature("line1").feature("def1").set("scaleactive", true);
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u6e29\u5ea6 (ht) 1");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").setIndex("looplevel", 5, 0);
    model.result("pg5").setIndex("looplevel", 4, 1);
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("solutionparams", "parent");
    model.result("pg5").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result("pg4").run();
    model.result().evaluationGroup().duplicate("eg2", "eg1");
    model.result().evaluationGroup("eg2").set("data", "dset3");
    model.result().evaluationGroup("eg2").feature().duplicate("gev2", "gev1");
    model.result().evaluationGroup("eg2").feature("gev2").setIndex("expr", "mmtp.rcon1.Tax_tpavg*w_rot*2*pi", 0);
    model.result().evaluationGroup("eg2").feature("gev2").setIndex("unit", "W", 0);
    model.result().evaluationGroup("eg2").feature("gev2").setIndex("descr", "\u8f74\u529f\u7387", 0);

    return model;
  }

  public static Model run3(Model model) {
    model.result().evaluationGroup("eg2").set("type", "general");
    model.result().evaluationGroup("eg2").set("keepchildnodes", true);
    model.result().evaluationGroup("eg2").set("generalexpr", "gev2/(gev2+int1+int2+int3+int4)");
    model.result().evaluationGroup("eg2").run();
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "none");
    model.result("pg6").create("tblc1", "TableContour");
    model.result("pg6").feature("tblc1").set("source", "evaluationgroup");
    model.result("pg6").feature("tblc1").set("evaluationgroup", "eg2");
    model.result("pg6").run();
    model.result("pg6").feature("tblc1").set("coly", 4);
    model.result("pg6").feature().duplicate("tblc2", "tblc1");
    model.result("pg6").run();
    model.result("pg6").feature("tblc2").set("contourtype", "lines");
    model.result("pg6").feature("tblc2").set("contourlabels", true);
    model.result("pg6").feature("tblc2").set("labelprec", 2);
    model.result("pg6").feature("tblc2").set("labelcolor", "black");
    model.result("pg6").feature("tblc2").set("colortable", "RainbowDark");
    model.result("pg6").feature("tblc2").set("colorlegend", false);
    model.result("pg6").feature("tblc2").set("titletype", "none");
    model.result("pg6").run();
    model.result("pg6").label("\u7535\u673a\u6548\u7387\u56fe");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "\u8f6c\u901f\uff08\u8f6c/\u5206\u949f\uff09");
    model.result("pg6").run();
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("data", "dset3");
    model.result("pg5").feature("surf1").set("solutionparams", "manual");
    model.result("pg5").feature("surf1").setIndex("looplevel", 1, 1);
    model.result("pg5").feature("surf1").setIndex("looplevel", 1, 0);
    model.result("pg5").feature("surf1").set("unit", "degC");
    model.result("pg5").feature("surf1").set("titletype", "none");
    model.result("pg5").run();
    model.result("pg5").create("ann1", "Annotation");
    model.result("pg5").feature("ann1").set("data", "dset3");
    model.result("pg5").feature("ann1").setIndex("looplevel", 1, 1);
    model.result("pg5").feature("ann1").setIndex("looplevel", 1, 0);
    model.result("pg5").feature("ann1").set("text", "\u901f\u5ea6 = eval(w_rot,rpm,5) Ipk = eval(Ipk,A,3)");
    model.result("pg5").feature("ann1").set("posxexpr", -16);
    model.result("pg5").feature("ann1").set("posyexpr", 6);
    model.result("pg5").feature("ann1").set("showpoint", false);
    model.result("pg5").feature("ann1").set("backgroundcolor", "fromtheme");
    model.result("pg5").feature("ann1").set("showframe", true);
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("surf2", "surf1");
    model.result("pg5").feature().duplicate("ann2", "ann1");
    model.result("pg5").run();
    model.result("pg5").feature("surf2").setIndex("looplevel", 5, 0);
    model.result("pg5").feature("surf2").set("inheritplot", "surf1");
    model.result("pg5").feature("surf2").create("trn1", "Transformation");
    model.result("pg5").run();
    model.result("pg5").feature("surf2").feature("trn1").set("move", new int[]{0, 30});
    model.result("pg5").run();
    model.result("pg5").feature("ann2").create("trn1", "Transformation");
    model.result("pg5").run();
    model.result("pg5").feature("ann2").feature("trn1").set("move", new int[]{0, 30});
    model.result("pg5").run();
    model.result("pg5").feature("ann2").setIndex("looplevel", 5, 0);
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("surf3", "surf2");
    model.result("pg5").feature().duplicate("ann3", "ann2");
    model.result("pg5").run();
    model.result("pg5").feature("surf3").setIndex("looplevel", 4, 1);
    model.result("pg5").run();
    model.result("pg5").feature("surf3").feature("trn1").set("move", new int[]{60, 30});
    model.result("pg5").run();
    model.result("pg5").feature("ann3").setIndex("looplevel", 4, 1);
    model.result("pg5").run();
    model.result("pg5").feature("ann3").feature("trn1").set("move", new int[]{60, 30});
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("surf4", "surf3");
    model.result("pg5").feature().duplicate("ann4", "ann3");
    model.result("pg5").run();
    model.result("pg5").feature("surf4").setIndex("looplevel", 1, 0);
    model.result("pg5").run();
    model.result("pg5").feature("surf4").feature("trn1").set("move", new int[]{60, 0});
    model.result("pg5").run();
    model.result("pg5").feature("ann4").setIndex("looplevel", 1, 0);
    model.result("pg5").run();
    model.result("pg5").feature("ann4").feature("trn1").set("move", new int[]{60, 0});
    model.result("pg5").run();
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "\u56db\u4e2a\u64cd\u4f5c\u70b9\u7684\u6e29\u5ea6\u573a");
    model.result("pg5").set("paramindicator", "");
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").run();
    model.result().duplicate("pg7", "pg5");
    model.result("pg7").run();
    model.result("pg7").label("\u7535\u78c1\u635f\u8017");
    model.result("pg7").set("title", "\u56db\u4e2a\u64cd\u4f5c\u70b9\u7684\u7535\u78c1\u635f\u8017\u5206\u5e03");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").set("expr", "mmtp.Qh");
    model.result("pg7").feature("surf1").set("descr", "\u4f53\u79ef\u635f\u8017\u5bc6\u5ea6\uff0c\u7535\u78c1");
    model.result("pg7").feature("surf1").set("colortable", "Rainbow");
    model.result("pg7").run();
    model.result("pg7").feature("surf2").set("expr", "mmtp.Qh");
    model.result("pg7").feature("surf2").set("descr", "\u4f53\u79ef\u635f\u8017\u5bc6\u5ea6\uff0c\u7535\u78c1");
    model.result("pg7").run();
    model.result("pg7").feature("surf3").set("expr", "mmtp.Qh");
    model.result("pg7").feature("surf3").set("descr", "\u4f53\u79ef\u635f\u8017\u5bc6\u5ea6\uff0c\u7535\u78c1");
    model.result("pg7").run();
    model.result("pg7").feature("surf4").set("expr", "mmtp.Qh");
    model.result("pg7").feature("surf4").set("descr", "\u4f53\u79ef\u635f\u8017\u5bc6\u5ea6\uff0c\u7535\u78c1");
    model.result("pg7").run();
    model.result("pg6").run();

    model.title("\u6c38\u78c1\u7535\u673a\u6548\u7387\u56fe");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u5c06\u201c\u78c1\u529b\u673a\u68b0\uff0c\u65f6\u95f4\u5468\u671f\u201d\u4e0e\u201c\u4f20\u70ed\u201d\uff08\u53cc\u5411\uff09\u8026\u5408\uff0c\u5e76\u751f\u6210\u57fa\u4e8e\u7535\u6d41\u5e45\u5ea6\u548c\u8f6c\u5b50\u8f6c\u901f\u626b\u63cf\u7684\u6548\u7387\u56fe\u3002");

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

    model.result("pg6").feature("tblc2").set("tablechanged", true);
    model.result("pg6").feature("tblc2").set("showparam", false);
    model.result("pg6").feature("tblc1").set("tablechanged", true);
    model.result("pg6").feature("tblc1").set("showparam", false);

    model.label("pm_motor_2d_efficiency_map.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
