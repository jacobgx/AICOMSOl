/*
 * pm_motor_2d_campbell_diagram.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:17 by COMSOL 6.3.0.290. */
public class pm_motor_2d_campbell_diagram {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Devices,_Motors_and_Generators");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");
    model.component("comp1").physics().create("mmtp", "MagneticMachineryTimePeriodic", "geom1");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").insertFile("pm_motor_2d_campbell_diagram_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.param().set("n_shaft", "12000[rpm]");
    model.param().descr("n_shaft", "\u6807\u79f0\u8f74\u8f6c\u901f");
    model.param().set("n_cog", "Ns/gcd(Np,Ns)*2");
    model.param().descr("n_cog", "\u4e00\u9636\u9f7f\u69fd\u8c10\u6ce2");
    model.param().set("Nframes", "n_cog*6");
    model.param().descr("Nframes", "\u65f6\u95f4\u5e27\u6570");
    model.param().set("Ipk", "1[A]");
    model.param().descr("Ipk", "\u76f8\u7535\u6d41");
    model.param().set("init_ang", "3.45[rad]");
    model.param().descr("init_ang", "\u5cf0\u503c\u626d\u77e9\u7684\u521d\u59cb\u7535\u6d41\u89d2");
    model.param().set("f_el", "n_shaft*Np/2");
    model.param()
         .descr("f_el", "\u7535\u9891\u7387\u4e0e\u8f74\u8f6c\u901f\u626b\u63cf\u7684\u51fd\u6570\u5173\u7cfb");
    model.param().set("L", "50[mm]");
    model.param().descr("L", "\u8f74\u5411\u957f\u5ea6");

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().set(1, 2, 4, 34, 35);
    model.component("comp1").coordSystem("pml1").set("wavelengthSource", "acpr");

    model.component("comp1").selection().create("disk1", "Disk");
    model.component("comp1").selection("disk1").set("entitydim", 1);
    model.component("comp1").selection("disk1").set("r", 16.3);
    model.component("comp1").selection("disk1").set("rin", 15.1);
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").set("input", new String[]{"geom1_pi2_stator_iron_dom"});
    model.component("comp1").selection().create("disk2", "Disk");
    model.component("comp1").selection("disk2").set("entitydim", 1);
    model.component("comp1").selection("disk2").set("inputent", "selections");
    model.component("comp1").selection("disk2").set("input", new String[]{"adj1"});
    model.component("comp1").selection("disk2").set("r", 24);

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
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").label("Steel AISI 4340");
    model.component("comp1").material("mat2").set("family", "steel");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "205[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup().create("BHCurve", "BHCurve", "B-H Curve");
    model.component("comp1").material("mat3").propertyGroup("BHCurve").func().create("BH", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup()
         .create("EffectiveBHCurve", "EffectiveBHCurve", "Effective B-H Curve");
    model.component("comp1").material("mat3").propertyGroup("EffectiveBHCurve").func()
         .create("BHeff", "Interpolation");
    model.component("comp1").material("mat3").label("Soft Iron (Without Losses)");
    model.component("comp1").material("mat3").set("family", "iron");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("BHCurve").label("B-H Curve");
    model.component("comp1").material("mat3").propertyGroup("BHCurve").func("BH").label("Interpolation 1");
    model.component("comp1").material("mat3").propertyGroup("BHCurve").func("BH")
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
    model.component("comp1").material("mat3").propertyGroup("BHCurve").func("BH").set("extrap", "linear");
    model.component("comp1").material("mat3").propertyGroup("BHCurve").func("BH").set("fununit", new String[]{"T"});
    model.component("comp1").material("mat3").propertyGroup("BHCurve").func("BH")
         .set("argunit", new String[]{"A/m"});
    model.component("comp1").material("mat3").propertyGroup("BHCurve").func("BH").set("defineinv", true);
    model.component("comp1").material("mat3").propertyGroup("BHCurve").func("BH").set("defineprimfun", true);
    model.component("comp1").material("mat3").propertyGroup("BHCurve").set("normB", "BH(normHin)");
    model.component("comp1").material("mat3").propertyGroup("BHCurve").set("normH", "BH_inv(normBin)");
    model.component("comp1").material("mat3").propertyGroup("BHCurve").set("Wpm", "BH_prim(normHin)");
    model.component("comp1").material("mat3").propertyGroup("BHCurve").descr("normHin", "\u78c1\u573a\u6a21");
    model.component("comp1").material("mat3").propertyGroup("BHCurve")
         .descr("normBin", "\u78c1\u901a\u5bc6\u5ea6\u6a21");
    model.component("comp1").material("mat3").propertyGroup("BHCurve").addInput("magneticfield");
    model.component("comp1").material("mat3").propertyGroup("BHCurve").addInput("magneticfluxdensity");
    model.component("comp1").material("mat3").propertyGroup("EffectiveBHCurve").label("Effective B-H Curve");
    model.component("comp1").material("mat3").propertyGroup("EffectiveBHCurve").func("BHeff")
         .label("Interpolation 1");
    model.component("comp1").material("mat3").propertyGroup("EffectiveBHCurve").func("BHeff")
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
    model.component("comp1").material("mat3").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("extrap", "linear");
    model.component("comp1").material("mat3").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("fununit", new String[]{"T"});
    model.component("comp1").material("mat3").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("argunit", new String[]{"A/m"});
    model.component("comp1").material("mat3").propertyGroup("EffectiveBHCurve").func("BHeff").set("defineinv", true);
    model.component("comp1").material("mat3").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("defineprimfun", true);
    model.component("comp1").material("mat3").propertyGroup("EffectiveBHCurve").set("normBeff", "BHeff(normHeffin)");
    model.component("comp1").material("mat3").propertyGroup("EffectiveBHCurve")
         .set("normHeff", "BHeff_inv(normBeffin)");
    model.component("comp1").material("mat3").propertyGroup("EffectiveBHCurve")
         .set("Wpmeff", "BHeff_prim(normHeffin)");
    model.component("comp1").material("mat3").propertyGroup("EffectiveBHCurve")
         .descr("normHeffin", "\u6709\u6548\u78c1\u573a\u6a21");
    model.component("comp1").material("mat3").propertyGroup("EffectiveBHCurve")
         .descr("normBeffin", "\u6709\u6548\u78c1\u901a\u5bc6\u5ea6\u6a21");
    model.component("comp1").material("mat3").propertyGroup("EffectiveBHCurve").addInput("magneticfield");
    model.component("comp1").material("mat3").propertyGroup("EffectiveBHCurve").addInput("magneticfluxdensity");
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").propertyGroup()
         .create("RemanentFluxDensity", "RemanentFluxDensity", "Remanent flux density");
    model.component("comp1").material("mat4").label("N42 (Sintered NdFeB)");
    model.component("comp1").material("mat4").set("family", "chrome");
    model.component("comp1").material("mat4").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("electricconductivity", new String[]{"1/1.4[uohm*m]", "0", "0", "0", "1/1.4[uohm*m]", "0", "0", "0", "1/1.4[uohm*m]"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat4").propertyGroup("RemanentFluxDensity").label("Remanent flux density");
    model.component("comp1").material("mat4").propertyGroup("RemanentFluxDensity")
         .set("murec", new String[]{"1.05", "0", "0", "0", "1.05", "0", "0", "0", "1.05"});
    model.component("comp1").material("mat4").propertyGroup("RemanentFluxDensity").set("normBr", "1.31[T]");
    model.component("comp1").material("mat2").selection().set(10, 27, 36);
    model.component("comp1").material("mat3").selection().set(6, 48);
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", new String[]{"185[GPa]"});
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", new String[]{"7500"});
    model.component("comp1").material("mat4").selection().named("geom1_pi1_rotor_magnets");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("electricconductivity", new String[]{"0*1/1.4[uohm*m]"});

    model.component("comp1").view("view1").set("showmaterial", false);

    model.component("comp1").physics("solid").selection().named("geom1_sel2");
    model.component("comp1").physics("solid").prop("d").set("d", "L");
    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().set(18, 66);
    model.component("comp1").physics("acpr").selection().named("geom1_sel1");
    model.component("comp1").physics("acpr").create("efc1", "ExteriorFieldCalculation", 1);
    model.component("comp1").physics("acpr").feature("efc1").selection().set(6, 9, 79);
    model.component("comp1").physics("acpr").feature("efc1").setIndex("SymmetryCondition1", 1, 0);
    model.component("comp1").physics("acpr").feature("efc1").setIndex("SymmetryPosition1", "-30[mm]", 0);
    model.component("comp1").physics("mmtp").selection().named("geom1_unisel1");
    model.component("comp1").physics("mmtp").prop("d").set("d", "L");
    model.component("comp1").physics("mmtp").prop("TimePeriodicSettings").set("freqTP", "f_el");
    model.component("comp1").physics("mmtp").prop("TimePeriodicSettings").set("nTP", "Nframes");
    model.component("comp1").physics("mmtp").prop("MotionSettings").set("NoPoles", "Np");
    model.component("comp1").physics("mmtp").create("rcon1", "RotationalContinuityPair", 1);
    model.component("comp1").physics("mmtp").create("tprot1", "TimePeriodicRotatingDomain", 2);
    model.component("comp1").physics("mmtp").create("lc1", "LaminatedCore", 2);
    model.component("comp1").physics("mmtp").feature("lc1").selection().set(6, 48);
    model.component("comp1").physics("mmtp").create("mag1", "Magnet", 2);
    model.component("comp1").physics("mmtp").feature("mag1").selection().named("geom1_pi1_rotor_magnets");
    model.component("comp1").physics("mmtp").feature("mag1").set("PatternType", "CircularPattern");
    model.component("comp1").physics("mmtp").feature("mag1").set("PeriodicType", "Alternating");
    model.component("comp1").physics("mmtp").feature("mag1").feature("north1").selection().set(357);
    model.component("comp1").physics("mmtp").feature("mag1").feature("south1").selection().set(354);
    model.component("comp1").physics("mmtp").create("wnd1", "MultiphaseWinding", 2);
    model.component("comp1").physics("mmtp").feature("wnd1").selection().named("geom1_pi2_stator_slots");
    model.component("comp1").physics("mmtp").feature("wnd1").set("Ipk", "Ipk");
    model.component("comp1").physics("mmtp").feature("wnd1").set("alpha_i", "init_ang");
    model.component("comp1").physics("mmtp").feature("wnd1").set("WindingLayout", "automatic");
    model.component("comp1").physics("mmtp").feature("wnd1").set("NoSlots", "Ns");
    model.component("comp1").physics("mmtp").feature("wnd1").runCommand("addPhases");
    model.component("comp1").physics("mmtp").create("fcal1", "ForceCalculation", 2);
    model.component("comp1").physics("mmtp").feature("fcal1").selection().set(6, 36);

    model.component("comp1").multiphysics().create("asb1", "AcousticStructureBoundary", 1);
    model.component("comp1").multiphysics("asb1").selection().all();
    model.component("comp1").multiphysics("asb1").set("Acoustics_physics", "acpr");
    model.component("comp1").multiphysics("asb1").set("Structure_physics", "solid");

    model.component("comp1").mesh("mesh1").contribute("physics/acpr", false);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 2.5);
    model.component("comp1").mesh("mesh1").feature("size").set("hcurve", 0.5);
    model.component("comp1").mesh("mesh1").feature("size").set("hnarrow", 0.3);
    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature().move("size1", 1);
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("size1").selection().set(6);
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", 0.7);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection()
         .set(3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set(3, 13, 37);
    model.component("comp1").mesh("mesh1").feature("bl1").set("smoothtransition", false);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().named("disk1");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 1);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhminfact", 5);
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp1", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").selection().set(6, 9, 79);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blnlayers", 1);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blhminfact", 5);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(1, 2, 4, 34, 35);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(4, 8, 82);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 8);
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std1");
    model.study("std1").create("eig", "Eigenfrequency");
    model.study("std1").feature("eig").set("ftplistmethod", "manual");
    model.study("std1").feature("eig").set("chkeigregion", true);
    model.study("std1").feature("eig").set("storefact", false);
    model.study("std1").feature("eig").set("linpsolnum", "auto");
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").set("outputmap", new String[]{});
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").setSolveFor("/physics/solid", true);
    model.study("std1").feature("eig").setSolveFor("/physics/acpr", true);
    model.study("std1").feature("eig").setSolveFor("/physics/mmtp", true);
    model.study("std1").feature("eig").setSolveFor("/multiphysics/asb1", true);
    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").feature("stat").setSolveFor("/physics/acpr", true);
    model.study("std2").feature("stat").setSolveFor("/physics/mmtp", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/asb1", true);
    model.study().create("std3");
    model.study("std3").create("freq", "Frequency");
    model.study("std3").feature("freq").setSolveFor("/physics/solid", true);
    model.study("std3").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std3").feature("freq").setSolveFor("/physics/mmtp", true);
    model.study("std3").feature("freq").setSolveFor("/multiphysics/asb1", true);
    model.study("std1").label("\u7814\u7a76 1 - \u7ed3\u6784\u6a21\u5f0f");
    model.study("std1").feature("eig").set("neigsactive", true);

    return model;
  }

  public static Model run2(Model model) {
    model.study("std1").feature("eig").set("neigs", 7);
    model.study("std1").feature("eig").set("shiftactive", false);
    model.study("std1").feature("eig").setSolveFor("/physics/acpr", false);
    model.study("std1").feature("eig").setSolveFor("/multiphysics/asb1", false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").label("\u632f\u578b (solid)");
    model.result("pg1").set("showlegends", false);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.disp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().evaluationGroup().create("std1EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std1EvgFrq").set("data", "dset1");
    model.result().evaluationGroup("std1EvgFrq")
         .label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 1 - \u7ed3\u6784\u6a21\u5f0f)");
    model.result().evaluationGroup("std1EvgFrq").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "2*pi*freq", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u89d2\u9891\u7387", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u963b\u5c3c\u6bd4", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u54c1\u8d28\u56e0\u5b50", 2);
    model.result().evaluationGroup("std1EvgFrq").run();
    model.result("pg1").run();
    model.result("pg1").stepNext(0);
    model.result("pg1").run();

    model.param().set("freq_min", "900[Hz]");
    model.param().descr("freq_min", "\u626b\u63cf\u7684\u9608\u503c\u9891\u7387");

    model.study("std2").label("\u7814\u7a76 2 - \u7535\u78c1\u529b");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u78c1\u901a\u5bc6\u5ea6\u6a21 (mmtp)");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("edges", "off");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solutionparams", "parent");
    model.result("pg2").feature("surf1").set("expr", "mmtp.normB(phase)");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature("surf1").feature().create("def1", "Deform");
    model.result("pg2").feature("surf1").feature("def1")
         .set("expr", new String[]{"mmtp.displX(phase)", "mmtp.displY(phase)"});
    model.result("pg2").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg2").feature().create("str1", "Streamline");
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("expr", new String[]{"mmtp.BX(phase)", "mmtp.BY(phase)"});
    model.result("pg2").feature("str1").set("posmethod", "uniform");
    model.result("pg2").feature("str1").set("udist", 0.03);
    model.result("pg2").feature("str1").set("smooth", "internal");
    model.result("pg2").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("data", "parent");
    model.result("pg2").feature("str1").selection().geom("geom1", 1);
    model.result("pg2").feature("str1").selection()
         .set(11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 26, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 60, 61, 62, 63, 64, 65, 67, 68, 69, 70, 71, 72, 74, 75, 76, 77, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341, 342, 343, 344, 345, 346, 347, 348, 349, 350, 351, 352, 353, 354, 355, 356, 357, 358, 359, 360, 361, 362, 363, 364, 365, 366, 367, 368, 369, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 380, 381, 382, 383, 384, 385);
    model.result("pg2").feature("str1").feature().create("col1", "Color");
    model.result("pg2").feature("str1").feature("col1").set("expr", "mmtp.normB(phase)");
    model.result("pg2").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg2").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg2").feature("str1").feature().create("filt1", "Filter");
    model.result("pg2").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg2").feature("str1").feature().create("def1", "Deform");
    model.result("pg2").feature("str1").feature("def1")
         .set("expr", new String[]{"mmtp.displX(phase)", "mmtp.displY(phase)"});
    model.result("pg2").feature("str1").feature("def1").set("scaleactive", true);
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("solutionparams", "parent");
    model.result("pg2").feature("con1").set("expr", "mmtp.AZ(phase)");
    model.result("pg2").feature("con1").set("number", 10);
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("coloring", "uniform");
    model.result("pg2").feature("con1").set("colorlegend", false);
    model.result("pg2").feature("con1").set("color", "custom");
    model.result("pg2").feature("con1")
         .set("customcolor", new double[]{0.4117647111415863, 0.4117647111415863, 0.4117647111415863});
    model.result("pg2").feature("con1").set("smooth", "internal");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("data", "parent");
    model.result("pg2").feature("con1").feature().create("filt1", "Filter");
    model.result("pg2").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg2").feature("con1").feature().create("def1", "Deform");
    model.result("pg2").feature("con1").feature("def1")
         .set("expr", new String[]{"mmtp.displX(phase)", "mmtp.displY(phase)"});
    model.result("pg2").feature("con1").feature("def1").set("scaleactive", true);
    model.result("pg2").feature().create("line1", "Line");
    model.result("pg2").feature("line1").set("showsolutionparams", "on");
    model.result("pg2").feature("line1").set("expr", "1");
    model.result("pg2").feature("line1").set("coloring", "uniform");
    model.result("pg2").feature("line1").set("color", "fromtheme");
    model.result("pg2").feature("line1").set("smooth", "internal");
    model.result("pg2").feature("line1").set("showsolutionparams", "on");
    model.result("pg2").feature("line1").set("data", "parent");
    model.result("pg2").feature("line1").feature().create("def1", "Deform");
    model.result("pg2").feature("line1").feature("def1")
         .set("expr", new String[]{"mmtp.displX(phase)", "mmtp.displY(phase)"});
    model.result("pg2").feature("line1").feature("def1").set("scaleactive", true);
    model.result("pg2").run();

    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl1").selection().named("disk2");
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"mmtp.nTX_1_fft(freq/f_el)", "mmtp.nTY_1_fft(freq/f_el)", "0"});

    model.study("std3").label("\u7814\u7a76 3 - \u632f\u52a8\u4e0e\u566a\u58f0");
    model.study("std3").feature("freq").setSolveFor("/physics/mmtp", false);
    model.study("std3").feature("freq").set("usesol", true);
    model.study("std3").feature("freq").set("notsolmethod", "sol");
    model.study("std3").feature("freq").set("notstudy", "std2");
    model.study("std3").create("param", "Parametric");
    model.study("std3").feature("param").setIndex("pname", "Np", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "", 0);
    model.study("std3").feature("param").setIndex("pname", "Np", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "", 0);
    model.study("std3").feature("param").setIndex("pname", "n_shaft", 0);
    model.study("std3").feature("param").setIndex("plistarr", "range(900,300,3000) range(4000,1000,12000)", 0);
    model.study("std3").feature("param").setIndex("punit", "rpm", 0);
    model.study("std3").feature("param").set("reusesol", true);
    model.study("std3").feature("freq").set("plist", "range(12,-1,floor(max(freq_min/f_el,1)))*f_el");
    model.study("std3").createAutoSequences("all");

    model.sol().create("sol4");
    model.sol("sol4").study("std3");
    model.sol("sol4").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol4");
    model.batch("p1").run("compute");

    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset4");
    model.result("pg3").setIndex("looplevel", 12, 0);
    model.result("pg3").setIndex("looplevel", 17, 1);
    model.result("pg3").label("\u5e94\u529b (solid)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"solid.misesGp_peak"});
    model.result("pg3").feature("surf1").set("threshold", "manual");
    model.result("pg3").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("surf1").set("colortable", "Rainbow");
    model.result("pg3").feature("surf1").set("colortabletrans", "none");
    model.result("pg3").feature("surf1").set("colorscalemode", "linear");
    model.result("pg3").feature("surf1").set("resolution", "normal");
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result("pg3").feature("surf1").create("def", "Deform");
    model.result("pg3").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg3").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset4");
    model.result("pg4").setIndex("looplevel", 12, 0);
    model.result("pg4").setIndex("looplevel", 17, 1);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg4").feature("surf1").set("colortable", "Wave");
    model.result("pg4").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").label("\u58f0\u538b (acpr)");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset4");
    model.result("pg5").setIndex("looplevel", 12, 0);
    model.result("pg5").setIndex("looplevel", 17, 1);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg5").feature("surf1").set("colortable", "Rainbow");
    model.result("pg5").feature("surf1").set("colorscalemode", "linear");
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").label("\u58f0\u538b\u7ea7 (acpr)");
    model.result().create("pg6", "PolarGroup");
    model.result("pg6").set("data", "dset4");
    model.result("pg6").create("rp1", "RadiationPattern");
    model.result("pg6").feature("rp1").set("expr", new String[]{"acpr.efc1.Lp_pext"});
    model.result("pg6").feature("rp1").set("legend", true);
    model.result("pg6").feature("rp1").set("phidisc", 180);
    model.result("pg6").label("\u5916\u573a\u58f0\u538b\u7ea7 (acpr)");
    model.result("pg6").setIndex("looplevelinput", "last", 0);
    model.result("pg6").setIndex("looplevelinput", "last", 1);
    model.result().create("pg7", "PolarGroup");
    model.result("pg7").set("data", "dset4");
    model.result("pg7").create("rp1", "RadiationPattern");
    model.result("pg7").feature("rp1").set("expr", new String[]{"acpr.efc1.pext"});
    model.result("pg7").feature("rp1").set("legend", true);
    model.result("pg7").feature("rp1").set("phidisc", 180);
    model.result("pg7").label("\u5916\u573a\u538b\u529b (acpr)");
    model.result("pg7").setIndex("looplevelinput", "last", 0);
    model.result("pg7").setIndex("looplevelinput", "last", 1);
    model.result("pg3").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u574e\u8d1d\u5c14\u56fe");
    model.result("pg8").create("lnsg1", "LineSegments");
    model.result("pg8").feature("lnsg1").set("markerpos", "datapoints");
    model.result("pg8").feature("lnsg1").set("linewidth", "preference");
    model.result("pg8").feature("lnsg1").setIndex("xexpr", 900, 0);
    model.result("pg8").feature("lnsg1").setIndex("xexpr", 12000, 1);
    model.result("pg8").feature("lnsg1").set("yexpr", new String[]{"solid.freq"});
    model.result("pg8").feature("lnsg1").set("ydescr", new String[]{"\u9891\u7387"});
    model.result("pg8").feature("lnsg1").set("yexpr", new String[]{"solid.freq", "solid.freq"});
    model.result("pg8").feature("lnsg1").set("ydescr", new String[]{"\u9891\u7387", "\u9891\u7387"});
    model.result("pg8").feature("lnsg1").set("titletype", "manual");
    model.result("pg8").feature("lnsg1").set("title", "\u6c34\u5e73\uff1a\u56fa\u6709\u9891\u7387");
    model.result("pg8").feature("lnsg1").set("legend", true);
    model.result("pg8").run();
    model.result("pg8").create("glob1", "Global");
    model.result("pg8").feature("glob1").set("markerpos", "datapoints");
    model.result("pg8").feature("glob1").set("linewidth", "preference");
    model.result("pg8").feature("glob1").set("data", "dset4");
    model.result("pg8").feature("glob1").set("expr", new String[]{"solid.freq"});
    model.result("pg8").feature("glob1").set("descr", new String[]{"\u9891\u7387"});
    model.result("pg8").feature("glob1").set("xdatasolnumtype", "outer");
    model.result("pg8").feature("glob1").set("linewidth", 3);
    model.result("pg8").feature("glob1").set("legend", false);
    model.result("pg8").feature("glob1").create("col1", "Color");
    model.result("pg8").run();
    model.result("pg8").feature("glob1").feature("col1").set("expr", "subst(acpr.efc1.Lp_pext,x,1[m],y,1[m])");
    model.result("pg8").feature("glob1").feature("col1").set("unit", "dB");
    model.result("pg8").run();
    model.result("pg8").set("legendpos", "upperleft");
    model.result("pg8").set("showlegendsmaxmin", true);
    model.result("pg8").set("showlegendsunit", true);
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").feature("glob1").set("titletype", "manual");
    model.result("pg8").feature("glob1").set("title", "\u5bf9\u89d2\uff1ax=y=1 [m] \u5904\u7684\u58f0\u538b\u7ea7");
    model.result("pg8").run();
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevelinput", "manual", 1);
    model.result("pg6").setIndex("looplevel", new int[]{12}, 1);
    model.result("pg6").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg6").setIndex("looplevelindices", 9, 0);
    model.result("pg6").set("zeroangle", "up");
    model.result("pg6").run();
    model.result("pg6").feature("rp1").set("anglerestr", "manual");
    model.result("pg6").feature("rp1").set("phimin", -90);
    model.result("pg6").feature("rp1").set("phirange", 180);
    model.result("pg6").feature("rp1").set("circle", "manual");
    model.result("pg6").feature("rp1").set("radius", "1[m]");
    model.result("pg6").run();
    model.result("pg7").run();
    model.result("pg7").setIndex("looplevelinput", "manual", 1);
    model.result("pg7").setIndex("looplevel", new int[]{12}, 1);
    model.result("pg7").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg7").setIndex("looplevelindices", 9, 0);
    model.result("pg7").set("zeroangle", "up");
    model.result("pg7").run();
    model.result("pg7").feature("rp1").set("anglerestr", "manual");
    model.result("pg7").feature("rp1").set("phimin", -90);
    model.result("pg7").feature("rp1").set("phirange", 180);
    model.result("pg7").feature("rp1").set("circle", "manual");
    model.result("pg7").feature("rp1").set("radius", "1[m]");
    model.result("pg7").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "mmtp.normB_tpph");
    model.result("pg2").feature("surf1")
         .set("descr", "\u78c1\u901a\u5bc6\u5ea6\u6a21\uff0c\u76f8\u4f4d\u51fd\u6570");
    model.result("pg2").run();
    model.result("pg2").feature("str1").active(false);
    model.result("pg2").run();
    model.result("pg2").feature("con1").set("expr", "mmtp.AZ_tpph");
    model.result("pg2").feature("con1").set("descr", "\u9762\u5916\u78c1\u77e2\u52bf\uff0c\u76f8\u4f4d\u51fd\u6570");
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("plotgroup", "pg2");
    model.result().export("anim1").set("sweeptype", "dde");
    model.result().export("anim1").set("maxframes", 36);
    model.result().export("anim1").set("repeat", "iterations");
    model.result().export("anim1").set("iterations", 5);
    model.result().export("anim1").set("synchronize", false);
    model.result().export("anim1").run();
    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").run();
    model.result("pg9").label("\u78c1\u529b\u8c10\u6ce2");
    model.result("pg9").set("data", "dset2");
    model.result("pg9").set("edges", false);
    model.result("pg9").create("line1", "Line");
    model.result("pg9").feature("line1").set("expr", "1");
    model.result("pg9").feature("line1").set("titletype", "none");
    model.result("pg9").feature("line1").set("coloring", "uniform");
    model.result("pg9").feature("line1").set("color", "fromtheme");
    model.result("pg9").feature("line1").create("sel1", "Selection");
    model.result("pg9").feature("line1").feature("sel1").selection().named("disk2");
    model.result("pg9").run();
    model.result("pg9").create("arwl1", "ArrowLine");
    model.result("pg9").feature("arwl1").set("expr", new String[]{"mmtp.nTX_1_fft1", "v"});
    model.result("pg9").feature("arwl1").setIndex("expr", "mmtp.nTY_1_fft1", 1);
    model.result("pg9").feature("arwl1").set("titletype", "none");
    model.result("pg9").feature("arwl1").set("arrowcount", 2000);
    model.result("pg9").run();
    model.result("pg9").feature().duplicate("line2", "line1");
    model.result("pg9").feature().duplicate("arwl2", "arwl1");
    model.result("pg9").run();
    model.result("pg9").feature("line2").create("trn1", "Transformation");
    model.result("pg9").run();
    model.result("pg9").feature("line2").feature("trn1").set("move", new int[]{60, 0});
    model.result("pg9").run();
    model.result("pg9").feature("arwl2").setIndex("expr", "mmtp.nTX_1_fft2", 0);
    model.result("pg9").feature("arwl2").setIndex("expr", "mmtp.nTY_1_fft2", 1);
    model.result("pg9").feature("arwl2").set("inheritplot", "arwl1");
    model.result("pg9").feature("arwl2").create("trn1", "Transformation");
    model.result("pg9").run();
    model.result("pg9").feature("arwl2").feature("trn1").set("move", new int[]{60, 0});
    model.result("pg9").run();
    model.result("pg9").feature().duplicate("line3", "line2");
    model.result("pg9").feature().duplicate("arwl3", "arwl2");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").feature("line3").feature("trn1").set("move", new int[]{120, 0});
    model.result("pg9").run();
    model.result("pg9").feature("arwl3").setIndex("expr", "mmtp.nTX_1_fft3", 0);
    model.result("pg9").feature("arwl3").setIndex("expr", "mmtp.nTY_1_fft3", 1);
    model.result("pg9").run();
    model.result("pg9").feature("arwl3").feature("trn1").set("move", new int[]{120, 0});
    model.result("pg9").run();
    model.result("pg9").feature().duplicate("line4", "line3");
    model.result("pg9").feature().duplicate("arwl4", "arwl3");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").feature("line4").feature("trn1").set("move", new int[]{180, 0});
    model.result("pg9").run();
    model.result("pg9").feature("arwl4").setIndex("expr", "mmtp.nTX_1_fft4", 0);
    model.result("pg9").feature("arwl4").setIndex("expr", "mmtp.nTY_1_fft4", 1);
    model.result("pg9").run();
    model.result("pg9").feature("arwl4").feature("trn1").set("move", new int[]{180, 0});
    model.result("pg9").run();
    model.result("pg9").feature().duplicate("line5", "line4");
    model.result("pg9").feature().duplicate("arwl5", "arwl4");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").feature("line5").feature("trn1").set("move", new int[]{0, -60});
    model.result("pg9").run();
    model.result("pg9").feature("arwl5").setIndex("expr", "mmtp.nTX_1_fft5", 0);
    model.result("pg9").feature("arwl5").setIndex("expr", "mmtp.nTY_1_fft5", 1);
    model.result("pg9").run();
    model.result("pg9").feature("arwl5").feature("trn1").set("move", new int[]{0, -60});
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").feature().duplicate("line6", "line5");
    model.result("pg9").feature().duplicate("arwl6", "arwl5");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").feature("line6").feature("trn1").set("move", new int[]{60, -60});
    model.result("pg9").run();
    model.result("pg9").feature("arwl6").setIndex("expr", "mmtp.nTX_1_fft6", 0);
    model.result("pg9").feature("arwl6").setIndex("expr", "mmtp.nTY_1_fft6", 1);
    model.result("pg9").run();
    model.result("pg9").feature("arwl6").feature("trn1").set("move", new int[]{60, -60});
    model.result("pg9").run();
    model.result("pg9").feature().duplicate("line7", "line6");
    model.result("pg9").feature().duplicate("arwl7", "arwl6");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").feature("line7").feature("trn1").set("move", new int[]{120, -60});
    model.result("pg9").run();
    model.result("pg9").feature("arwl7").setIndex("expr", "mmtp.nTX_1_fft7", 0);
    model.result("pg9").feature("arwl7").setIndex("expr", "mmtp.nTY_1_fft7", 1);
    model.result("pg9").run();
    model.result("pg9").feature("arwl7").feature("trn1").set("move", new int[]{120, -60});
    model.result("pg9").run();
    model.result("pg9").feature().duplicate("line8", "line7");
    model.result("pg9").feature().duplicate("arwl8", "arwl7");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").feature("line8").feature("trn1").set("move", new int[]{180, -60});
    model.result("pg9").run();
    model.result("pg9").feature("arwl8").setIndex("expr", "mmtp.nTX_1_fft8", 0);
    model.result("pg9").feature("arwl8").setIndex("expr", "mmtp.nTY_1_fft8", 1);
    model.result("pg9").run();
    model.result("pg9").feature("arwl8").feature("trn1").set("move", new int[]{180, -60});
    model.result("pg9").run();
    model.result("pg9").feature().duplicate("line9", "line8");
    model.result("pg9").feature().duplicate("arwl9", "arwl8");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").feature("line9").feature("trn1").set("move", new int[]{0, -120});
    model.result("pg9").run();
    model.result("pg9").feature("arwl9").setIndex("expr", "mmtp.nTX_1_fft9", 0);
    model.result("pg9").feature("arwl9").setIndex("expr", "mmtp.nTY_1_fft9", 1);
    model.result("pg9").run();
    model.result("pg9").feature("arwl9").feature("trn1").set("move", new int[]{0, -120});
    model.result("pg9").run();
    model.result("pg9").feature().duplicate("line10", "line9");
    model.result("pg9").feature().duplicate("arwl10", "arwl9");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").feature("line10").feature("trn1").set("move", new int[]{60, -120});
    model.result("pg9").run();
    model.result("pg9").feature("arwl10").setIndex("expr", "mmtp.nTX_1_fft10", 0);
    model.result("pg9").feature("arwl10").setIndex("expr", "mmtp.nTY_1_fft10", 1);
    model.result("pg9").run();
    model.result("pg9").feature("arwl10").feature("trn1").set("move", new int[]{60, -120});
    model.result("pg9").run();
    model.result("pg9").feature().duplicate("line11", "line10");
    model.result("pg9").feature().duplicate("arwl11", "arwl10");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").feature("line11").feature("trn1").set("move", new int[]{120, -120});
    model.result("pg9").run();
    model.result("pg9").feature("arwl11").setIndex("expr", "mmtp.nTX_1_fft11", 0);
    model.result("pg9").feature("arwl11").setIndex("expr", "mmtp.nTY_1_fft11", 1);
    model.result("pg9").run();
    model.result("pg9").feature("arwl11").feature("trn1").set("move", new int[]{120, -120});
    model.result("pg9").run();
    model.result("pg9").feature().duplicate("line12", "line11");
    model.result("pg9").feature().duplicate("arwl12", "arwl11");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").feature("line12").feature("trn1").set("move", new int[]{180, -120});
    model.result("pg9").run();
    model.result("pg9").feature("arwl12").setIndex("expr", "mmtp.nTX_1_fft12", 0);
    model.result("pg9").feature("arwl12").setIndex("expr", "mmtp.nTY_1_fft12", 1);
    model.result("pg9").run();
    model.result("pg9").feature("arwl12").feature("trn1").set("move", new int[]{180, -120});
    model.result("pg9").run();
    model.result("pg9").feature("arwl1").set("scaleactive", true);
    model.result("pg9").feature("arwl1").set("scale", "1e-4");
    model.result("pg9").run();
    model.result("pg8").run();
    model.result("pg8").run();

    model.title("\u6c38\u78c1\u7535\u673a\u574e\u8d1d\u5c14\u56fe");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u5c06\u201c\u78c1\u529b\u673a\u68b0\uff0c\u65f6\u95f4\u5468\u671f\u201d\u4e0e\u201c\u56fa\u4f53\u529b\u5b66\u201d\u548c\u201c\u58f0\u5b66\u201d\u76f8\u8026\u5408\uff0c\u4ee5\u751f\u6210\u574e\u8d1d\u5c14\u56fe\u3002\u901a\u8fc7\u4eff\u771f\u7ed3\u679c\uff0c\u6211\u4eec\u53ef\u4ee5\u6df1\u5165\u4e86\u89e3\u5728\u4e0d\u540c\u7684\u8f6c\u901f\u4e0b\uff0c\u54ea\u4e9b\u78c1\u529b\u8c10\u6ce2\u4f1a\u4ea7\u751f\u6700\u660e\u663e\u7684\u7535\u673a\u566a\u58f0\u3002");

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

    return model;
  }

  public static Model run3(Model model) {
    model.sol("sol21").clearSolutionData();

    model.label("pm_motor_2d_campbell_diagram.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
