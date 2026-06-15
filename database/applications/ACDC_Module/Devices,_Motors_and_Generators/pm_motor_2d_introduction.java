/*
 * pm_motor_2d_introduction.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:20 by COMSOL 6.3.0.290. */
public class pm_motor_2d_introduction {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Devices,_Motors_and_Generators");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("rmm", "RotatingMachineryMagnetic", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/rmm", true);

    model.param().set("L", "200[mm]");
    model.param().descr("L", "\u7535\u673a\u7684\u9762\u5916\u539a\u5ea6");
    model.param().set("init_ang", "0[deg]");
    model.param().descr("init_ang", "\u521d\u59cb\u7535\u89d2\u5ea6");
    model.param().set("Np", "10");
    model.param().descr("Np", "\u6781\u6570");
    model.param().set("Ns", "12");
    model.param().descr("Ns", "\u69fd\u6570");
    model.param().set("w_rot", "600[rpm]");
    model.param().descr("w_rot", "\u8f6c\u901f");
    model.param().set("f_el", "w_rot*(Np/2)");
    model.param().descr("f_el", "\u7535\u9891\u7387");
    model.param().set("I0", "10[A]");
    model.param().descr("I0", "\u5cf0\u503c\u7535\u6d41");
    model.param().set("Nturn", "10");
    model.param().descr("Nturn", "\u69fd\u5185\u5bfc\u7ebf\u531d\u6570");
    model.param().set("ff_slot", "0.8");
    model.param().descr("ff_slot", "\u69fd\u586b\u5145\u56e0\u5b50");

    model.geom()
         .load(new String[]{"part1"}, "ACDC_Module\\Rotating_Machinery_2D\\Rotors\\Internal\\surface_mounted_magnet_internal_rotor_2d.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "number_of_poles", "Np");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "number_of_modeled_poles", "Np");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_shaft.dom", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_rotor_iron.dom", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_rotor_magnets", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_all.dom", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_exterior", true);
    model.geom()
         .load(new String[]{"part2"}, "ACDC_Module\\Rotating_Machinery_2D\\Stators\\External\\slotted_external_stator_2d.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi2").set("part", "part2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "number_of_slots", "Ns");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "number_of_modeled_slots", "Ns");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "slot_winding_type", 2);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selkeepdom", "pi2_stator_iron.dom", true);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selkeepdom", "pi2_stator_slots", true);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selkeepdom", "pi2_all.dom", true);
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u94c1");
    model.component("comp1").selection("uni1")
         .set("input", new String[]{"geom1_pi1_rotor_iron_dom", "geom1_pi2_stator_iron_dom"});

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
    model.component("comp1").material("mat5").label("Iron");
    model.component("comp1").material("mat5").set("family", "iron");
    model.component("comp1").material("mat5").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("relpermeability", new String[]{"4000", "0", "0", "0", "4000", "0", "0", "0", "4000"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("electricconductivity", new String[]{"1.12e7[S/m]", "0", "0", "0", "1.12e7[S/m]", "0", "0", "0", "1.12e7[S/m]"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.2e-6[1/K]", "0", "0", "0", "12.2e-6[1/K]", "0", "0", "0", "12.2e-6[1/K]"});
    model.component("comp1").material("mat5").propertyGroup("def").set("heatcapacity", "440[J/(kg*K)]");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat5").propertyGroup("def").set("density", "7870[kg/m^3]");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalconductivity", new String[]{"76.2[W/(m*K)]", "0", "0", "0", "76.2[W/(m*K)]", "0", "0", "0", "76.2[W/(m*K)]"});
    model.component("comp1").material("mat5").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat5").propertyGroup("Enu").set("E", "200[GPa]");
    model.component("comp1").material("mat5").propertyGroup("Enu").set("nu", "0.29");
    model.component("comp1").material("mat2").selection().named("uni1");
    model.component("comp1").material("mat5").selection().named("geom1_pi1_shaft_dom");
    model.component("comp1").material("mat5").propertyGroup("def").set("electricconductivity", new String[]{"0"});
    model.component("comp1").material("mat5").propertyGroup("Enu").set("E", new String[]{"200e9[Pa]"});
    model.component("comp1").material("mat3").selection().named("geom1_pi2_stator_slots");
    model.component("comp1").material("mat4").selection().named("geom1_pi1_rotor_magnets");

    model.component("comp1").common().create("rot1", "RotatingDomain");
    model.component("comp1").common("rot1").selection().all();
    model.component("comp1").common("rot1").selection().named("geom1_pi1_all_dom");
    model.component("comp1").common("rot1").set("rotationType", "rotationalVelocity");
    model.component("comp1").common("rot1").set("angularVelocity", "w_rot*2*pi");

    model.component("comp1").physics("rmm").prop("d").set("d", "L");
    model.component("comp1").physics("rmm").create("lcvf1", "LaminatedCoreVectorFormulation", 2);
    model.component("comp1").physics("rmm").feature("lcvf1").selection().named("uni1");
    model.component("comp1").physics("rmm").feature("lcvf1").set("LaminationModel", "OutOfPlane");
    model.component("comp1").physics("rmm").create("cmag1", "ConductingMagnet", 2);
    model.component("comp1").physics("rmm").feature("cmag1").selection().named("geom1_pi1_rotor_magnets");
    model.component("comp1").physics("rmm").feature("cmag1").set("PatternType", "CircularPattern");
    model.component("comp1").physics("rmm").feature("cmag1").set("PeriodicType", "Alternating");
    model.component("comp1").physics("rmm").feature("cmag1").create("loss1", "LossCalculation", 2);
    model.component("comp1").physics("rmm").feature("cmag1").feature("north1").selection().set(280);
    model.component("comp1").physics("rmm").feature("cmag1").feature("south1").selection().set(277);
    model.component("comp1").physics("rmm").create("wnd1", "MultiphaseWinding", 2);
    model.component("comp1").physics("rmm").feature("wnd1").selection().named("geom1_pi2_stator_slots");
    model.component("comp1").physics("rmm").feature("wnd1").set("Ipk", "I0");
    model.component("comp1").physics("rmm").feature("wnd1").set("alpha_i", "init_ang");
    model.component("comp1").physics("rmm").feature("wnd1").set("freq_t", "f_el");
    model.component("comp1").physics("rmm").feature("wnd1").set("N", "Nturn");
    model.component("comp1").physics("rmm").feature("wnd1").set("FillingFactor", "ff_slot");
    model.component("comp1").physics("rmm").feature("wnd1").set("WindingLayout", "automatic");
    model.component("comp1").physics("rmm").feature("wnd1").set("NoPoles", "Np");
    model.component("comp1").physics("rmm").feature("wnd1").set("NoSlots", "Ns");
    model.component("comp1").physics("rmm").feature("wnd1").set("NoCoilsPerSlot", 2);
    model.component("comp1").physics("rmm").feature("wnd1").runCommand("addPhases");
    model.component("comp1").physics("rmm").feature("wnd1").create("loss1", "LossCalculation", 2);
    model.component("comp1").physics("rmm").create("ark1", "ArkkioTorqueCalculation", 2);

    model.component("comp1").probe().create("var1", "GlobalVariable");
    model.component("comp1").probe("var1").label("\u626d\u77e9");
    model.component("comp1").probe("var1").set("expr", "rmm.Tark_1");
    model.component("comp1").probe("var1").set("descr", "\u8f74\u5411\u626d\u77e9");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").label("\u6e90");
    model.component("comp1").selection("sel1").set(93, 94, 105, 106, 125, 126, 148, 149, 173, 174, 193, 194);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(1);
    model.component("comp1").selection("sel2").label("\u76ee\u6807");
    model.component("comp1").selection("sel2").set(249, 250, 259, 260, 271, 272, 291, 292, 303, 304);
    model.component("comp1").selection().create("uni2", "Union");
    model.component("comp1").selection("uni2").label("\u6c14\u9699\u8fb9\u754c");
    model.component("comp1").selection("uni2").set("entitydim", 1);
    model.component("comp1").selection("uni2").set("input", new String[]{"sel1", "sel2"});

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature().move("size1", 1);
    model.component("comp1").mesh("mesh1").feature().move("size2", 2);
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("size1").selection().named("uni2");
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", "0.5[mm]/3");
    model.component("comp1").mesh("mesh1").feature("size2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("size2").selection().named("geom1_pi2_stator_iron_dom");
    model.component("comp1").mesh("mesh1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size2").set("hmax", "1[mm]");
    model.component("comp1").mesh("mesh1").run();

    return model;
  }

  public static Model run2(Model model) {

    model.study("std1").label("\u7814\u7a76 1\uff1a\u521d\u59cb\u7535\u89d2\u5ea6\u626b\u63cf");
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "L", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "L", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "init_ang", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,10[deg],360[deg])", 0);
    model.study("std1").feature("stat").setIndex("punit", "deg", 0);
    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("var1").genResult("none");

    model.sol("sol1").runAll();

    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u78c1\u901a\u5bc6\u5ea6 (rmm)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solutionparams", "parent");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature().create("str1", "Streamline");
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("solutionparams", "parent");
    model.result("pg2").feature("str1").set("titletype", "none");
    model.result("pg2").feature("str1").set("posmethod", "uniform");
    model.result("pg2").feature("str1").set("udist", 0.03);
    model.result("pg2").feature("str1").set("maxlen", 0.4);
    model.result("pg2").feature("str1").set("maxsteps", 5000);
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("inheritcolor", false);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("data", "parent");
    model.result("pg2").feature("str1").selection().geom("geom1", 1);
    model.result("pg2").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 301, 302, 303, 304, 305, 306, 307, 308);
    model.result("pg2").feature("str1").set("inheritplot", "surf1");
    model.result("pg2").feature("str1").feature().create("col1", "Color");
    model.result("pg2").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg2").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg2").feature("str1").feature().create("filt1", "Filter");
    model.result("pg2").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("solutionparams", "parent");
    model.result("pg2").feature("con1").set("expr", "rmm.Az");
    model.result("pg2").feature("con1").set("titletype", "none");
    model.result("pg2").feature("con1").set("number", 10);
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("coloring", "uniform");
    model.result("pg2").feature("con1").set("colorlegend", false);
    model.result("pg2").feature("con1").set("color", "custom");
    model.result("pg2").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg2").feature("con1").set("resolution", "fine");
    model.result("pg2").feature("con1").set("inheritcolor", false);
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("data", "parent");
    model.result("pg2").feature("con1").set("inheritplot", "surf1");
    model.result("pg2").feature("con1").feature().create("filt1", "Filter");
    model.result("pg2").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("str1").active(false);
    model.result("pg2").run();
    model.result("pg2").feature("con1").set("number", 16);
    model.result("pg2").feature("con1").set("color", "black");
    model.result("pg2").run();
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").label("\u626d\u77e9");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "\u89d2 [deg]");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u626d\u77e9 [N*m]");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u521d\u59cb\u7535\u89d2\u5ea6\u626b\u63cf\u65f6\u7684\u626d\u77e9");
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "\u89d2 [deg]");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u626d\u77e9 [N*m]");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").setIndex("expr", "rmm.Tark_1", 0);
    model.result("pg3").feature("glob1").create("gmrk1", "GraphMarker");
    model.result("pg3").feature("glob1").feature("gmrk1").set("linewidth", "preference");
    model.result("pg3").run();
    model.result("pg3").feature("glob1").feature("gmrk1").set("inclxcoord", true);
    model.result("pg3").feature("glob1").feature("gmrk1").set("includeunit", true);
    model.result("pg3").feature("glob1").feature("gmrk1").set("precision", 3);
    model.result("pg3").feature("glob1").feature("gmrk1").set("display", "max");
    model.result("pg3").run();

    model.param().set("init_ang", "200[deg]");
    model.param().descr("init_ang", "\u521d\u59cb\u673a\u68b0\u89d2\u5ea6");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/rmm", true);
    model.study("std2").label("\u7814\u7a76 2\uff1a\u540c\u6b65\u65cb\u8f6c");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").set("tlist", "range(0,1/12/6,1.1)/f_el");
    model.study("std2").showAutoSequences("all");
    model.study("std2").createAutoSequences("all");

    model.component("comp1").probe("var1").genResult("none");

    model.sol("sol2").runAll();

    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u78c1\u901a\u5bc6\u5ea6 (rmm) 1");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").setIndex("looplevel", 80, 0);
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("solutionparams", "parent");
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result("pg4").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").feature().create("str1", "Streamline");
    model.result("pg4").feature("str1").set("showsolutionparams", "on");
    model.result("pg4").feature("str1").set("solutionparams", "parent");
    model.result("pg4").feature("str1").set("titletype", "none");
    model.result("pg4").feature("str1").set("posmethod", "uniform");
    model.result("pg4").feature("str1").set("udist", 0.03);
    model.result("pg4").feature("str1").set("maxlen", 0.4);
    model.result("pg4").feature("str1").set("maxsteps", 5000);
    model.result("pg4").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("inheritcolor", false);
    model.result("pg4").feature("str1").set("showsolutionparams", "on");
    model.result("pg4").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("showsolutionparams", "on");
    model.result("pg4").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("showsolutionparams", "on");
    model.result("pg4").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("showsolutionparams", "on");
    model.result("pg4").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("data", "parent");
    model.result("pg4").feature("str1").selection().geom("geom1", 1);
    model.result("pg4").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 301, 302, 303, 304, 305, 306, 307, 308);
    model.result("pg4").feature("str1").set("inheritplot", "surf1");
    model.result("pg4").feature("str1").feature().create("col1", "Color");
    model.result("pg4").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg4").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg4").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg4").feature("str1").feature().create("filt1", "Filter");
    model.result("pg4").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg4").feature().create("con1", "Contour");
    model.result("pg4").feature("con1").set("showsolutionparams", "on");
    model.result("pg4").feature("con1").set("solutionparams", "parent");
    model.result("pg4").feature("con1").set("expr", "rmm.Az");
    model.result("pg4").feature("con1").set("titletype", "none");
    model.result("pg4").feature("con1").set("number", 10);
    model.result("pg4").feature("con1").set("levelrounding", false);
    model.result("pg4").feature("con1").set("coloring", "uniform");
    model.result("pg4").feature("con1").set("colorlegend", false);
    model.result("pg4").feature("con1").set("color", "custom");
    model.result("pg4").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg4").feature("con1").set("resolution", "fine");
    model.result("pg4").feature("con1").set("inheritcolor", false);
    model.result("pg4").feature("con1").set("showsolutionparams", "on");
    model.result("pg4").feature("con1").set("data", "parent");
    model.result("pg4").feature("con1").set("inheritplot", "surf1");
    model.result("pg4").feature("con1").feature().create("filt1", "Filter");
    model.result("pg4").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg4").run();
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").label("\u8f6c\u77e9\u6ce2\u52a8");
    model.result("pg1").set("xlabel", "\u65f6\u95f4 [s]");
    model.result("pg1").set("axislimits", true);
    model.result("pg1").set("xmin", 0);
    model.result("pg1").set("xmax", 0.02);
    model.result("pg1").set("ymin", 0);
    model.result("pg1").set("ymax", 4);
    model.result("pg1").set("legendpos", "middleright");
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").set("axislimits", false);
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u6c14\u9699\u5f84\u5411\u78c1\u901a\u5bc6\u5ea6");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").setIndex("looplevelinput", "first", 0);
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").selection().named("geom1_pi1_exterior");
    model.result("pg5").feature("lngr1").set("expr", "rmm.ark1.Brad");
    model.result("pg5").run();

    model.study().create("std3");
    model.study("std3").create("emloss", "TimeToFrequencyLosses");
    model.study("std3").feature("emloss").set("fftinputstudy", "current");
    model.study("std3").feature("emloss").set("lossstarttime", "0");
    model.study("std3").feature("emloss").set("notsolnum", "auto");
    model.study("std3").feature("emloss").set("outputmap", new String[]{});
    model.study("std3").feature("emloss").setSolveFor("/physics/rmm", true);
    model.study("std3").label("\u7814\u7a76 3\uff1a\u65f6\u95f4\u5e73\u5747\u635f\u8017\u8ba1\u7b97");
    model.study("std3").feature("emloss").set("fftinputstudy", "std2");
    model.study("std3").feature("emloss").set("emT0", "1/f_el");
    model.study("std3").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").label("\u5468\u671f\u5e73\u5747\u635f\u8017 (rmm)");
    model.result("pg6").set("data", "dset5");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").set("showlegendsmaxmin", true);
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("solutionparams", "parent");
    model.result("pg6").feature("surf1").set("expr", "rmm.Qh");
    model.result("pg6").feature("surf1").set("colortable", "GrayBody");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result("pg6").feature("surf1").feature().create("filt1", "Filter");
    model.result("pg6").feature("surf1").feature("filt1").set("expr", "rmm.isLossCalculationDomain");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").set("data", "dset3");
    model.result().evaluationGroup("eg1").label("\u4e00\u4e2a\u7535\u5468\u671f\u7684\u626d\u77e9");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "rmm.Tark_1", 0);
    model.result().evaluationGroup("eg1").setIndex("looplevelinput", "interp", 0);
    model.result().evaluationGroup("eg1").setIndex("interp", "range(0.1,1/12/6,1.1)/f_el", 0);
    model.result().evaluationGroup("eg1").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u626d\u77e9\u8c10\u6ce2");
    model.result("pg7").set("titletype", "manual");
    model.result("pg7").set("title", "\u626d\u77e9\u8c10\u6ce2");
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("xlabel", "\u8c10\u6ce2\u9636\u6570");
    model.result("pg7").create("tblp1", "Table");
    model.result("pg7").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg7").feature("tblp1").set("linewidth", "preference");
    model.result("pg7").feature("tblp1").set("source", "evaluationgroup");
    model.result("pg7").feature("tblp1").set("transform", "fourier");
    model.result("pg7").feature("tblp1").set("fouriershow", "spectrum");
    model.result("pg7").feature("tblp1").set("scale", "multiplyperiod");
    model.result("pg7").feature("tblp1").set("preprocx", "linear");
    model.result("pg7").feature("tblp1").set("scalingx", "f_el");
    model.result("pg7").feature("tblp1").set("linewidth", 2);
    model.result("pg7").run();
    model.result("pg4").run();

    model.title("\u6c38\u78c1\u7535\u673a\u4e8c\u7ef4\u6a21\u578b");

    model
         .description("\u672c\u6559\u7a0b\u5728\u4e8c\u7ef4\u4e2d\u6a21\u62df\u5177\u6709 10\u00a0\u4e2a\u8f6c\u5b50\u78c1\u6781\u548c 12\u00a0\u4e2a\u5b9a\u5b50\u69fd\u7684\u6c38\u78c1\u7535\u673a\uff0c\u4ee5\u6355\u83b7\u4e00\u4e2a\u7535\u6c14\u5468\u671f\u7684\u8f6c\u77e9\u8109\u52a8\uff0c\u5e76\u7ed8\u5236\u8f6c\u5b50\u548c\u5b9a\u5b50\u94c1\u7684\u4f53\u79ef\u635f\u8017\u5bc6\u5ea6\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("pm_motor_2d_introduction.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
