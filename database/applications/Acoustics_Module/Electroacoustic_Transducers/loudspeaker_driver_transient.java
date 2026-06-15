/*
 * loudspeaker_driver_transient.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:39 by COMSOL 6.3.0.290. */
public class loudspeaker_driver_transient {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Electroacoustic_Transducers");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");
    model.component("comp1").physics().create("actd", "TransientPressureAcoustics", "geom1");
    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.component("comp1").multiphysics().create("asb1", "AcousticStructureBoundary", 1);
    model.component("comp1").multiphysics("asb1").set("Acoustics_physics", "actd");
    model.component("comp1").multiphysics("asb1").set("Structure_physics", "solid");
    model.component("comp1").multiphysics("asb1").selection().all();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/mf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/actd", true);
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/asb1", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("N0", "100", "\u7ebf\u5708\u531d\u6570");
    model.param().set("V0", "10[V]", "\u5cf0\u503c\u9a71\u52a8\u7535\u538b");
    model.param().set("c0", "343[m/s]", "\u7a7a\u6c14\u4e2d\u7684\u58f0\u901f");
    model.param().set("f0", "70[Hz]", "\u9a71\u52a8\u9891\u7387");
    model.param().set("omega0", "2*pi*f0", "\u9a71\u52a8\u89d2\u9891\u7387");
    model.param().set("T0", "1/f0", "\u9a71\u52a8\u4fe1\u53f7\u5468\u671f");
    model.param().set("T_end", "4*T0", "\u4eff\u771f\u7ed3\u675f\u65f6\u95f4");
    model.param().set("f_d", "40[Hz]", "\u7ed9\u5b9a\u521a\u5ea6\u963b\u5c3c\u7cfb\u6570\u7684\u9891\u7387");
    model.param()
         .set("omega_d", "2*pi*f_d", "\u7ed9\u5b9a\u521a\u5ea6\u963b\u5c3c\u7cfb\u6570\u7684\u89d2\u9891\u7387");
    model.param().set("E_com", "2[GPa]", "\u6768\u6c0f\u6a21\u91cf\uff08\u590d\u5408\u6750\u6599\uff09");
    model.param().set("nu_com", "0.42", "\u6cca\u677e\u6bd4\uff08\u590d\u5408\u6750\u6599\uff09");
    model.param()
         .set("eta_s_com", "0.04", "\u5404\u5411\u540c\u6027\u635f\u8017\u56e0\u5b50\uff08\u590d\u5408\u6750\u6599\uff09");
    model.param()
         .set("K_com", "E_com/(3*(1-2*nu_com))", "\u4f53\u79ef\u6a21\u91cf\uff08\u590d\u5408\u6750\u6599\uff09");
    model.param()
         .set("G_com", "E_com/(2*(1+nu_com))", "\u526a\u5207\u6a21\u91cf\uff08\u590d\u5408\u6750\u6599\uff09");
    model.param().set("E_gf", "70[GPa]", "\u6768\u6c0f\u6a21\u91cf\uff08\u73bb\u7483\u7ea4\u7ef4\uff09");
    model.param().set("nu_gf", "0.33", "\u6cca\u677e\u6bd4\uff08\u73bb\u7483\u7ea4\u7ef4\uff09");
    model.param()
         .set("eta_s_gf", "0.04", "\u5404\u5411\u540c\u6027\u635f\u8017\u56e0\u5b50\uff08\u73bb\u7483\u7ea4\u7ef4\uff09");
    model.param()
         .set("K_gf", "E_gf/(3*(1-2*nu_gf))", "\u4f53\u79ef\u6a21\u91cf\uff08\u73bb\u7483\u7ea4\u7ef4\uff09");
    model.param().set("G_gf", "E_gf/(2*(1+nu_gf))", "\u526a\u5207\u6a21\u91cf\uff08\u73bb\u7483\u7ea4\u7ef4\uff09");
    model.param().set("mu0", "mu0_const", "\u771f\u7a7a\u78c1\u5bfc\u7387");

    model.component("comp1").geom("geom1").insertFile("loudspeaker_driver_transient_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("igv1");

    model.func().create("rm1", "Ramp");
    model.func("rm1").set("location", "0.1*T0");
    model.func("rm1").set("slope", "1/T0");
    model.func("rm1").set("cutoffactive", true);
    model.func("rm1").set("smoothzonelocactive", true);
    model.func("rm1").set("smoothzoneloc", "0.2*T0");
    model.func("rm1").set("smoothzonecutoffactive", true);
    model.func("rm1").set("smoothzonecutoff", "0.2*T0");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u97f3\u5708");
    model.component("comp1").selection("sel1").set(14);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u56fa\u4f53\u529b\u5b66\u57df");
    model.component("comp1").selection("sel2").set(4, 8, 9, 10, 11, 12, 13, 14, 15, 16, 19);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u78c1\u4f53\u57df");
    model.component("comp1").selection("sel3").set(2, 3, 7, 8, 9, 10, 11, 12, 14, 15, 17, 18);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u58f0\u5b66\u57df");
    model.component("comp1").selection("sel4").set(1, 2, 3, 5, 6);
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").label("\u56fa\u4f53\u529b\u5b66\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").selection("adj1").set("input", new String[]{"sel2"});

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().named("sel1");
    model.component("comp1").cpl("aveop1").set("axisym", false);

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
    model.component("comp1").material("mat2").label("Soft Iron (With Losses)");
    model.component("comp1").material("mat2").set("family", "iron");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"1.12e7[S/m]", "0", "0", "0", "1.12e7[S/m]", "0", "0", "0", "1.12e7[S/m]"});
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
    model.component("comp1").material("mat2").selection().set(7, 17);
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("Composite");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def").set("youngsmodulus", "2[GPa]");
    model.component("comp1").material("mat3").propertyGroup("def").set("poissonsratio", "0.42");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "1200[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def").set("lossfactor", "0.04");
    model.component("comp1").material("mat3").selection().set(4, 16);
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").label("Cloth");
    model.component("comp1").material("mat4").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("electricconductivity", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat4").propertyGroup("def").set("youngsmodulus", "0.58[GPa]");
    model.component("comp1").material("mat4").propertyGroup("def").set("poissonsratio", "0.3");
    model.component("comp1").material("mat4").propertyGroup("def").set("density", "650[kg/m^3]");
    model.component("comp1").material("mat4").selection().set(15);
    model.component("comp1").material().create("mat5", "Common");
    model.component("comp1").material("mat5").label("Foam");
    model.component("comp1").material("mat5").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat5").propertyGroup("def").set("youngsmodulus", "5[MPa]");
    model.component("comp1").material("mat5").propertyGroup("def").set("poissonsratio", "0.4");
    model.component("comp1").material("mat5").propertyGroup("def").set("density", "67[kg/m^3]");
    model.component("comp1").material("mat5").selection().set(19);
    model.component("comp1").material().create("mat6", "Common");
    model.component("comp1").material("mat6").label("Coil");
    model.component("comp1").material("mat6").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat6").propertyGroup("def").set("lossfactor", "0.05");
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat6").propertyGroup("def").set("youngsmodulus", "110[GPa]");
    model.component("comp1").material("mat6").propertyGroup("def").set("poissonsratio", "0.35");
    model.component("comp1").material("mat6").propertyGroup("def").set("density", "4500[kg/m^3]");
    model.component("comp1").material("mat6").selection().set(14);
    model.component("comp1").material().create("mat7", "Common");
    model.component("comp1").material("mat7").label("Glass Fiber");
    model.component("comp1").material("mat7").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat7").propertyGroup("def")
         .set("electricconductivity", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp1").material("mat7").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat7").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat7").propertyGroup("def").set("youngsmodulus", "70[GPa]");
    model.component("comp1").material("mat7").propertyGroup("def").set("poissonsratio", "0.33");
    model.component("comp1").material("mat7").propertyGroup("def").set("density", "2000[kg/m^3]");
    model.component("comp1").material("mat7").propertyGroup("def").set("lossfactor", "0.04");
    model.component("comp1").material("mat7").selection().set(8, 9, 10, 11, 12, 13);
    model.component("comp1").material().create("mat8", "Common");
    model.component("comp1").material("mat8").propertyGroup()
         .create("RemanentFluxDensity", "RemanentFluxDensity", "Remanent flux density");
    model.component("comp1").material("mat8").label("Generic Ferrite");
    model.component("comp1").material("mat8").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat8").propertyGroup("def").set("lossfactor", "0.01");
    model.component("comp1").material("mat8").propertyGroup("def")
         .set("electricconductivity", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp1").material("mat8").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat8").propertyGroup("def").set("poissonsratio", "0.3");
    model.component("comp1").material("mat8").propertyGroup("def").set("youngsmodulus", "200[GPa]");
    model.component("comp1").material("mat8").propertyGroup("def").set("density", "5000[kg/m^3]");
    model.component("comp1").material("mat8").propertyGroup("RemanentFluxDensity").label("Remanent flux density");
    model.component("comp1").material("mat8").propertyGroup("RemanentFluxDensity")
         .set("murec", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat8").propertyGroup("RemanentFluxDensity").set("normBr", "0.4[T]");
    model.component("comp1").material("mat8").selection().set(18);

    model.component("comp1").physics("mf").selection().named("sel3");
    model.component("comp1").physics("mf").create("als1", "AmperesLawSolid", 2);
    model.component("comp1").physics("mf").feature("als1")
         .label("\u56fa\u4f53\u4e2d\u7684\u5b89\u57f9\u5b9a\u5f8b - \u901a\u7528\u94c1\u7d20\u4f53");
    model.component("comp1").physics("mf").feature("als1").selection().set(18);
    model.component("comp1").physics("mf").feature("als1").set("ConstitutiveRelationBH", "RemanentFluxDensity");
    model.component("comp1").physics("mf").feature("als1").set("e_crel_BH_RemanentFluxDensity", new int[]{0, 0, 1});
    model.component("comp1").physics("mf").create("als2", "AmperesLawSolid", 2);
    model.component("comp1").physics("mf").feature("als2")
         .label("\u56fa\u4f53\u4e2d\u7684\u5b89\u57f9\u5b9a\u5f8b - \u8f6f\u94c1");
    model.component("comp1").physics("mf").feature("als2").selection().set(7, 17);
    model.component("comp1").physics("mf").feature("als2").set("ConstitutiveRelationBH", "BHCurve");
    model.component("comp1").physics("mf").create("als3", "AmperesLawSolid", 2);
    model.component("comp1").physics("mf").feature("als3").selection().named("sel2");
    model.component("comp1").physics("mf").feature("als3")
         .label("\u56fa\u4f53\u4e2d\u7684\u5b89\u57f9\u5b9a\u5f8b - \u975e\u5bfc\u7535\u56fa\u4f53");
    model.component("comp1").physics("mf").create("coil1", "Coil", 2);
    model.component("comp1").physics("mf").feature("coil1").selection().named("sel1");
    model.component("comp1").physics("mf").feature("coil1").setIndex("materialType", "solid", 0);
    model.component("comp1").physics("mf").feature("coil1").set("ConductorModel", "Multi");
    model.component("comp1").physics("mf").feature("coil1").set("CoilExcitation", "Voltage");
    model.component("comp1").physics("mf").feature("coil1").set("VCoil", "V0*sin(2*pi*f0*t)*rm1(t[1/s])");
    model.component("comp1").physics("mf").feature("coil1").set("N", "N0");
    model.component("comp1").physics("mf").feature("coil1").set("AreaFrom", "UserDefined");
    model.component("comp1").physics("mf").feature("coil1").set("HarmonicLoss", false);
    model.component("comp1").physics("mf").feature("coil1").set("coilWindArea", "2.4e-8[m^2]");
    model.component("comp1").physics("actd").selection().named("sel4");
    model.component("comp1").physics("actd").prop("TransientSettings").set("fmax", "3*f0");
    model.component("comp1").physics("solid").selection().named("sel2");
    model.component("comp1").physics("solid").feature("lemm1").create("dmp1", "Damping", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").set("beta_dK", "0.14/omega_d");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").selection().set(15);
    model.component("comp1").physics("solid").feature("lemm1").create("dmp2", "Damping", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp2").set("beta_dK", "0.46/omega_d");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp2").selection().set(19);
    model.component("comp1").physics("solid").feature("lemm1").create("dmp3", "Damping", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp3").set("DampingType", "ViscousDamping");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp3").set("etab", "eta_s_gf*K_gf/omega0");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp3").set("etav", "eta_s_gf*G_gf/omega0");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp3").selection().set(8, 9, 10, 11, 12, 13);
    model.component("comp1").physics("solid").feature("lemm1").create("dmp4", "Damping", 2);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp4").set("DampingType", "ViscousDamping");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp4").set("etab", "eta_s_com*K_com/omega0");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp4").set("etav", "eta_s_com*G_com/omega0");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp4").selection().set(4, 16);
    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().set(70, 74);

    model.component("comp1").multiphysics().create("mmcpl1", "Magnetomechanics", 2);
    model.component("comp1").multiphysics("mmcpl1").set("OnlyLorentz", true);
    model.component("comp1").multiphysics("mmcpl1").selection().named("sel1");

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().set(1, 6);
    model.component("comp1").coordSystem("pml1").set("PMLgamma", "3");

    model.component("comp1").common().create("free1", "DeformingDomain");
    model.component("comp1").common("free1").selection().all();
    model.component("comp1").common("free1").selection().set(3, 5);
    model.component("comp1").common("free1").set("smoothingType", "laplace");
    model.component("comp1").common().create("sym1", "Symmetry");
    model.component("comp1").common("sym1").selection().set(3, 6);

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "15[mm]");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "0.10[mm]");
    model.component("comp1").mesh("mesh1").feature("size").set("hcurve", 0.25);
    model.component("comp1").mesh("mesh1").run("size");
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(4, 8, 9, 10, 11, 12, 14, 15, 16, 19);
    model.component("comp1").mesh("mesh1").feature("map1").set("adjustedgdistr", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(18, 30, 33, 37);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").feature("map1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").selection().set(9, 14);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmax", "0.15[mm]");
    model.component("comp1").mesh("mesh1").feature("map1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size2").selection().set(12, 15);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size2").set("hmax", "0.7[mm]");
    model.component("comp1").mesh("mesh1").feature("map1").create("size3", "Size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size3").selection().set(4, 16, 19);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size3").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size3").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size3").set("hmax", "1.0[mm]");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(19);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemcount", 20);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemratio", 3);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").selection().set(15);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("elemcount", 10);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("elemratio", 3);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(2, 3, 5, 7, 13, 17, 18);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(7, 17, 18);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "3[mm]");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmin", "0.5[mm]");
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("map2", "Map");
    model.component("comp1").mesh("mesh1").feature("map2").set("adjustedgdistr", true);
    model.component("comp1").mesh("mesh1").feature("map2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").selection().set(76, 77);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").set("numelem", 8);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").setSolveFor("/physics/solid", false);
    model.study("std1").feature("stat").setSolveFor("/frame/spatial1", false);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("tlist", "{range(0, T0/5, 14*T0/5) range(3*T0, T0/50, T_end)}");
    model.study("std1").feature("time").set("autoremesh", true);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v2").feature("comp1_spatial_disp").set("scaleval", "1e-4");
    model.sol("sol1").feature("t1").set("tout", "tlist");
    model.sol("sol1").feature("t1").set("timemethod", "bdf");
    model.sol("sol1").feature("t1").feature("arDef").set("stopcondtype", "distortion");
    model.sol("sol1").feature("t1").feature("arDef").set("stopdistval", "2.5");
    model.sol("sol1").feature("t1").feature("arDef").set("storesolmesh", false);

    model.study("std1").label("\u7814\u7a76 1 - \u77ac\u6001\u5206\u6790");
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").label("\u91cd\u65b0\u5212\u5206\u7f51\u683c\u540e\u7684\u89e3 1");
    model.sol("sol3").study("std1");
    model.sol("sol1").feature("t1").feature("arDef").set("tadapsol", "sol3");
    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u78c1\u901a\u5bc6\u5ea6 (mf)");
    model.result("pg1").set("data", "dset3");
    model.result("pg1").setIndex("looplevel", 138, 0);
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature().create("str1", "Streamline");
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("solutionparams", "parent");
    model.result("pg1").feature("str1").set("titletype", "none");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("udist", 0.03);
    model.result("pg1").feature("str1").set("maxlen", 0.4);
    model.result("pg1").feature("str1").set("maxsteps", 5000);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("inheritcolor", false);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("data", "parent");
    model.result("pg1").feature("str1").selection().geom("geom1", 1);
    model.result("pg1").feature("str1").selection()
         .set(2, 3, 4, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 26, 28, 29, 30, 31, 32, 33, 34, 35, 36, 38, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 72, 73, 79, 80, 84, 85, 86, 87, 89, 90, 92);
    model.result("pg1").feature("str1").set("inheritplot", "surf1");
    model.result("pg1").feature("str1").feature().create("col1", "Color");
    model.result("pg1").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg1").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg1").feature("str1").feature().create("filt1", "Filter");
    model.result("pg1").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").feature().create("con1", "Contour");
    model.result("pg1").feature("con1").set("showsolutionparams", "on");
    model.result("pg1").feature("con1").set("solutionparams", "parent");
    model.result("pg1").feature("con1").set("expr", "mf.Psi");
    model.result("pg1").feature("con1").set("titletype", "none");
    model.result("pg1").feature("con1").set("number", 10);
    model.result("pg1").feature("con1").set("levelrounding", false);
    model.result("pg1").feature("con1").set("coloring", "uniform");
    model.result("pg1").feature("con1").set("colorlegend", false);
    model.result("pg1").feature("con1").set("color", "custom");
    model.result("pg1").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg1").feature("con1").set("resolution", "fine");
    model.result("pg1").feature("con1").set("inheritcolor", false);
    model.result("pg1").feature("con1").set("showsolutionparams", "on");
    model.result("pg1").feature("con1").set("data", "parent");
    model.result("pg1").feature("con1").set("inheritplot", "surf1");
    model.result("pg1").feature("con1").feature().create("filt1", "Filter");
    model.result("pg1").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset3");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u78c1\u901a\u5bc6\u5ea6\uff0c\u56de\u8f6c\u51e0\u4f55 (mf)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("solutionparams", "parent");
    model.result("pg2").feature("vol1").set("colortable", "Prism");
    model.result("pg2").feature("vol1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("vol1").set("colorcalibration", -0.8);
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("solutionparams", "parent");
    model.result("pg2").feature("con1").set("expr", "mf.Psi");
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
    model.result("pg2").feature("con1").set("inheritplot", "vol1");
    model.result("pg2").feature("con1").feature().create("filt1", "Filter");
    model.result("pg2").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg2").feature("con1").feature("filt1").set("shownodespec", "on");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").setIndex("looplevel", 138, 0);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"actd.p_t"});
    model.result("pg3").feature("surf1").set("colortable", "Wave");
    model.result("pg3").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").label("\u58f0\u538b (actd)");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "rev1");
    model.result("pg4").setIndex("looplevel", 138, 0);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"actd.p_t"});
    model.result("pg4").feature("surf1").set("colortable", "Wave");
    model.result("pg4").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").label("\u58f0\u538b, 3D (actd)");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").setIndex("looplevel", 138, 0);
    model.result("pg5").label("\u5e94\u529b (solid)");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg5").feature("surf1").set("threshold", "manual");
    model.result("pg5").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg5").feature("surf1").set("colortable", "Rainbow");
    model.result("pg5").feature("surf1").set("colortabletrans", "none");
    model.result("pg5").feature("surf1").set("colorscalemode", "linear");
    model.result("pg5").feature("surf1").set("resolution", "normal");
    model.result("pg5").feature("surf1").set("colortable", "Prism");
    model.result().dataset().create("dset3solidrev", "Revolve2D");
    model.result().dataset("dset3solidrev").set("data", "dset3");
    model.result().dataset("dset3solidrev").set("revangle", 225);
    model.result().dataset("dset3solidrev").set("startangle", -90);
    model.result().dataset("dset3solidrev").set("hasspacevars", true);
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset3solidrev");
    model.result("pg6").setIndex("looplevel", 138, 0);
    model.result("pg6").label("\u5e94\u529b, 3D (solid)");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg6").feature("surf1").set("threshold", "manual");
    model.result("pg6").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg6").feature("surf1").set("colortable", "Rainbow");
    model.result("pg6").feature("surf1").set("colortabletrans", "none");
    model.result("pg6").feature("surf1").set("colorscalemode", "linear");
    model.result("pg6").feature("surf1").set("colortable", "Prism");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").setIndex("looplevel", 138, 0);
    model.result("pg7").label("\u52a8\u7f51\u683c");
    model.result("pg7").create("mesh1", "Mesh");
    model.result("pg7").feature("mesh1").set("meshdomain", "surface");
    model.result("pg7").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg7").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg7").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg7").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg7").feature("mesh1").feature("sel1").selection()
         .set(3, 4, 5, 8, 9, 10, 11, 12, 13, 14, 15, 16, 19);
    model.result("pg7").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg7").feature("mesh1").set("qualexpr", "comp1.spatial.relVol");
    model.result("pg7").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg1").run();
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u78c1\u901a\u5bc6\u5ea6\u6a21");
    model.result("pg1").set("paramindicator", "t = eval(t) s");
    model.result("pg1").set("view", "new");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("str1").active(false);
    model.result("pg1").feature("con1").active(false);
    model.result("pg1").run();
    model.result("pg1").create("str2", "Streamline");
    model.result("pg1").feature("str2").selection().set(40);
    model.result("pg1").feature("str2").set("color", "gray");
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u58f0\u538b\uff0c\u65f6\u95f4 = eval(t) s");
    model.result("pg3").set("paramindicator", "");
    model.result("pg3").set("titlenumberformat", "auto");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("rangecoloractive", true);
    model.result("pg3").feature("surf1").set("rangecolormin", -150);
    model.result("pg3").feature("surf1").set("rangecolormax", 150);
    model.result("pg3").feature("surf1").create("sel1", "Selection");
    model.result("pg3").feature("surf1").feature("sel1").selection()
         .set(2, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19);
    model.result("pg3").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u542c\u97f3\u70b9\u7684\u538b\u529b");
    model.result("pg8").set("data", "dset3");
    model.result("pg8").setIndex("looplevelinput", "interp", 0);
    model.result("pg8").setIndex("interp", "range(3*T0, T0/50, T_end)", 0);
    model.result("pg8").set("titletype", "label");
    model.result("pg8").create("ptgr1", "PointGraph");
    model.result("pg8").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg8").feature("ptgr1").set("linewidth", "preference");
    model.result("pg8").feature("ptgr1").selection().set(6);
    model.result("pg8").feature("ptgr1").set("expr", "p");
    model.result("pg8").run();
    model.result().numerical().create("pev1", "EvalPoint");
    model.result().numerical("pev1").label("\u70b9\u4e0a\u7684\u538b\u529b");
    model.result().numerical("pev1").set("data", "dset3");
    model.result().numerical("pev1").setIndex("looplevelinput", "interp", 0);
    model.result().numerical("pev1").setIndex("interp", "range(3*T0, T0/50, T_end)", 0);
    model.result().numerical("pev1").selection().set(6);
    model.result().numerical("pev1").setIndex("expr", "p", 0);
    model.result().numerical("pev1").setIndex("descr", "\u538b\u529b", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u70b9\u4e0a\u7684\u538b\u529b");
    model.result().numerical("pev1").set("table", "tbl1");
    model.result().numerical("pev1").setResult();
    model.result().table("tbl1").label("\u70b9\u4e0a\u7684\u538b\u529b");

    model.func().create("int1", "Interpolation");
    model.func("int1").set("source", "resultTable");
    model.func("int1").setIndex("argunit", "s", 0);
    model.func("int1").setEntry("funcnames", "col2", "p_point");
    model.func("int1").setIndex("fununit", "Pa", 0);
    model.func("int1").set("interp", "cubicspline");
    model.func("int1").set("extrap", "interior");
    model.func().create("an1", "Analytic");
    model.func("an1").set("funcname", "p_periodic");
    model.func("an1").set("expr", "p_point(t)");
    model.func("an1").set("args", "t");
    model.func("an1").set("periodic", true);
    model.func("an1").set("periodiclower", "3*T0");
    model.func("an1").set("periodicupper", "4*T0");
    model.func("an1").setIndex("argunit", "s", 0);
    model.func("an1").set("fununit", "Pa");
    model.func("an1").setIndex("plotargs", "4*T0", 0, 2);

    model.component().create("comp2", true);

    model.component("comp2").physics().create("ge", "GlobalEquations");

    model.study("std1").feature("stat").setSolveFor("/physics/ge", false);
    model.study("std1").feature("time").setSolveFor("/physics/ge", false);

    model.component("comp2").physics("ge").prop("EquationForm").set("form", "Automatic");
    model.component("comp2").physics("ge").feature("ge1").setIndex("name", "P", 0, 0);
    model.component("comp2").physics("ge").feature("ge1").setIndex("equation", "P - p_periodic(t)", 0, 0);
    model.component("comp2").physics("ge").feature("ge1").set("DependentVariableQuantity", "pressure");
    model.component("comp2").physics("ge").feature("ge1").set("SourceTermQuantity", "pressure");

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/mf", true);
    model.study("std2").feature("time").setSolveFor("/physics/actd", true);
    model.study("std2").feature("time").setSolveFor("/physics/solid", true);
    model.study("std2").feature("time").setSolveFor("/physics/ge", true);
    model.study("std2").feature("time").setSolveFor("/multiphysics/asb1", true);
    model.study("std2").feature("time").setSolveFor("/multiphysics/mmcpl1", true);
    model.study("std2").feature("time").set("tlist", "range(0, T0/200, 10*T0)");
    model.study("std2").feature("time").setSolveFor("/physics/mf", false);
    model.study("std2").feature("time").setSolveFor("/physics/actd", false);
    model.study("std2").feature("time").setSolveFor("/physics/solid", false);
    model.study("std2").feature("time").setSolveFor("/frame/spatial1", false);
    model.study("std2").feature("time").setSolveFor("/multiphysics/asb1", false);
    model.study("std2").create("tffft", "TimeToFreqFFT");
    model.study("std2").feature("tffft").set("fftendtime", "10*T0");
    model.study("std2").feature("tffft").set("fftmaxfreq", "10*f0");
    model.study("std2").feature("tffft").setSolveFor("/physics/mf", false);
    model.study("std2").feature("tffft").setSolveFor("/physics/actd", false);
    model.study("std2").feature("tffft").setSolveFor("/physics/solid", false);
    model.study("std2").feature("tffft").setSolveFor("/frame/spatial1", false);
    model.study("std2").feature("tffft").setSolveFor("/multiphysics/asb1", false);
    model.study("std2").label("\u7814\u7a76 2 - \u5468\u671f\u6027\u4fe1\u53f7\u63d0\u53d6\u548c FFT");
    model.study("std2").setGenPlots(false);
    model.study("std2").showAutoSequences("all");

    model.sol("sol4").feature("t1").set("timemethod", "genalpha");
    model.sol("sol4").feature("t1").set("tstepsgenalpha", "manual");
    model.sol("sol4").feature("t1").set("timestepgenalpha", "1/(6*f0)/60");

    model.study("std2").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").label("\u542c\u97f3\u70b9\u7684\u58f0\u538b\u7ea7\uff0cFFT");
    model.result("pg9").set("data", "dset4");
    model.result("pg9").setIndex("looplevelinput", "manual", 0);
    model.result("pg9")
         .setIndex("looplevel", new int[]{5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101}, 0);
    model.result("pg9").set("titletype", "label");
    model.result("pg9").set("xlabelactive", true);
    model.result("pg9").set("xlabel", "\u9891\u7387 (Hz)");
    model.result("pg9").set("ylabelactive", true);
    model.result("pg9").set("ylabel", "\u58f0\u538b\u7ea7 (dB)");
    model.result("pg9").set("xlog", true);
    model.result("pg9").create("oct1", "OctaveBand");
    model.result("pg9").feature("oct1").set("quantity", "bandpower");
    model.result("pg9").feature("oct1").set("markerpos", "datapoints");
    model.result("pg9").feature("oct1").set("linewidth", "preference");
    model.result("pg9").feature("oct1").selection().geom("geom1");
    model.result("pg9").feature("oct1").set("expr", "comp2.P");
    model.result("pg9").feature("oct1").set("quantity", "continuous");
    model.result("pg9").feature("oct1").set("legendmethod", "manual");
    model.result("pg9").feature("oct1").set("legend", true);
    model.result("pg9").feature("oct1").setIndex("legends", "\u8fde\u7eed PSD", 0);
    model.result("pg9").run();
    model.result("pg9").create("oct2", "OctaveBand");
    model.result("pg9").feature("oct2").set("quantity", "bandpower");
    model.result("pg9").feature("oct2").set("markerpos", "datapoints");
    model.result("pg9").feature("oct2").set("linewidth", "preference");
    model.result("pg9").feature("oct2").selection().geom("geom1");
    model.result("pg9").feature("oct2").set("expr", "comp2.P");
    model.result("pg9").feature("oct2").set("quantity", "bandaveragepsd");
    model.result("pg9").feature("oct2").set("bandtype", "octave3");
    model.result("pg9").feature("oct2").set("type", "outline");
    model.result("pg9").feature("oct2").set("legendmethod", "manual");
    model.result("pg9").feature("oct2").set("legend", true);
    model.result("pg9").feature("oct2").setIndex("legends", "1/3 \u500d\u9891\u5e26 (PSD)", 0);
    model.result("pg9").run();
    model.result("pg9").feature().duplicate("oct3", "oct1");
    model.result("pg9").run();
    model.result("pg9").feature("oct3").set("data", "dset4");
    model.result("pg9").feature("oct3").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg9").feature("oct3").setIndex("looplevelindices", "range(11, 10, 101)", 0);
    model.result("pg9").feature("oct3").set("linestyle", "none");
    model.result("pg9").feature("oct3").set("linemarker", "circle");
    model.result("pg9").feature("oct3").set("legend", false);
    model.result("pg9").feature("oct3").create("gmrk1", "GraphMarker");
    model.result("pg9").feature("oct3").feature("gmrk1").set("linewidth", "preference");
    model.result("pg9").run();
    model.result("pg9").feature("oct3").feature("gmrk1").set("scope", "local");
    model.result("pg9").feature("oct3").feature("gmrk1").set("precision", 4);
    model.result("pg9").feature("oct3").feature("gmrk1").set("includeunit", true);
    model.result("pg9").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("THD \u8ba1\u7b97");
    model.result().numerical("gev1").set("data", "dset4");
    model.result().numerical("gev1").setIndex("looplevelinput", "first", 0);
    model.result().numerical("gev1")
         .setIndex("expr", "sqrt(sum(with(11 + 10*k, abs(comp2.P)^2), k, 1, 9))/with(11, abs(comp2.P))", 0);
    model.result().numerical("gev1").setIndex("descr", "THD", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("THD \u8ba1\u7b97");
    model.result().numerical("gev1").set("table", "tbl2");
    model.result().numerical("gev1").setResult();
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").run();
    model.result("pg10").label("\u97f3\u5708\u529f\u7387");
    model.result("pg10").set("data", "dset3");
    model.result("pg10").setIndex("looplevelinput", "interp", 0);
    model.result("pg10").setIndex("interp", "range(3*T0, T0/50, T_end)", 0);
    model.result("pg10").set("titletype", "label");
    model.result("pg10").create("glob1", "Global");
    model.result("pg10").feature("glob1").set("markerpos", "datapoints");
    model.result("pg10").feature("glob1").set("linewidth", "preference");
    model.result("pg10").feature("glob1").set("expr", new String[]{"mf.PCoil_1"});
    model.result("pg10").feature("glob1").set("descr", new String[]{"\u7ebf\u5708\u529f\u7387"});
    model.result("pg10").feature("glob1").set("unit", new String[]{"W"});
    model.result("pg10").run();
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").run();
    model.result("pg11").label("\u52a8\u6001 BL \u529b\u56e0\u5b50");
    model.result("pg11").set("data", "dset3");
    model.result("pg11").setIndex("looplevelinput", "interp", 0);
    model.result("pg11").setIndex("interp", "range(3*T0, T0/50, T_end)", 0);
    model.result("pg11").set("titletype", "manual");
    model.result("pg11")
         .set("title", "\u52a8\u6001 BL \u529b\u56e0\u5b50 vs. \u97f3\u5708\u7684\u76f8\u5bf9\u4f4d\u7f6e");

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg11").create("glob1", "Global");
    model.result("pg11").feature("glob1").set("markerpos", "datapoints");
    model.result("pg11").feature("glob1").set("linewidth", "preference");
    model.result("pg11").feature("glob1").setIndex("expr", "aveop1(-mf.Br*N0*2*pi*r)", 0);
    model.result("pg11").feature("glob1").setIndex("unit", "T*m", 0);
    model.result("pg11").feature("glob1").setIndex("descr", "\u52a8\u6001 Bl \u529b\u56e0\u5b50", 0);
    model.result("pg11").feature("glob1").set("xdata", "expr");
    model.result("pg11").feature("glob1").set("xdataexpr", "aveop1(z - Z)");
    model.result("pg11").feature("glob1").set("xdatadescractive", true);
    model.result("pg11").feature("glob1").set("xdatadescr", "\u97f3\u5708\u7684\u76f8\u5bf9\u4f4d\u7f6e");
    model.result("pg11").run();

    model.title("\u626c\u58f0\u5668\u9a71\u52a8\u5668 - \u77ac\u6001\u5206\u6790");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u5bf9\u626c\u58f0\u5668\u9a71\u52a8\u5668\u7684\u5168\u77ac\u6001\u5206\u6790\uff0c\u652f\u6301\u4e3a\u975e\u7ebf\u6027\u6548\u5e94\u5efa\u6a21\uff0c\u8fd9\u662f\u5bf9\u201c\u626c\u58f0\u5668\u9a71\u52a8\u5668\u201d\u6559\u7a0b\u4e2d\u6267\u884c\u7684\u7ebf\u6027\u9891\u57df\u5206\u6790\u7684\u6269\u5c55\u3002\n\n\u4eff\u771f\u5206\u6790\u5305\u542b\u78c1\u7cfb\u7edf\u4e2d\u8f6f\u94c1\u7684\u975e\u7ebf\u6027\u7279\u6027\u3001\u7ed3\u6784\u4e2d\u7684\u51e0\u4f55\u975e\u7ebf\u6027\uff0c\u4ee5\u53ca\u97f3\u5708\u8fdb\u51fa\u78c1\u9699\u65f6\u7531\u4e8e\u62d3\u6251\u53d8\u5316\u5f15\u8d77\u7684\u975e\u7ebf\u6027\u6548\u5e94\u3002\u6a21\u578b\u4f7f\u7528\u52a8\u7f51\u683c\u7279\u5f81\u548c\u81ea\u52a8\u91cd\u65b0\u5212\u5206\u7f51\u683c\u6765\u6355\u6349\u62d3\u6251\u53d8\u5316\u548c\u97f3\u5708\u7684\u8fd0\u52a8\u3002\n\n\u8f93\u51fa\u5305\u542b\u603b\u8c10\u6ce2\u5931\u771f (THD) \u548c\u52a8\u6001 BL \u66f2\u7ebf\u3002\u8be5\u6a21\u578b\u8fd8\u63cf\u8ff0\u5982\u4f55\u8ba1\u7b97\u4e92\u8c03\u5931\u771f (IMD)\u3002\n\n\u9700\u8981\u201c\u58f0\u5b66\u6a21\u5757\u201d\u548c\u201cAC/DC \u6a21\u5757\u201d\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("loudspeaker_driver_transient.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
