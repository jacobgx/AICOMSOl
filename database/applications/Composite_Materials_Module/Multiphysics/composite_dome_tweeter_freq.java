/*
 * composite_dome_tweeter_freq.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:33 by COMSOL 6.3.0.290. */
public class composite_dome_tweeter_freq {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Composite_Materials_Module\\Multiphysics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("lshell", "LayeredShell", "geom1");
    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");
    model.component("comp1").physics().create("cir", "Circuit", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/lshell", true);
    model.study("std1").feature("freq").setSolveFor("/physics/solid", true);
    model.study("std1").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std1").feature("freq").setSolveFor("/physics/cir", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("th_dome", "0.1[mm]", "\u7a79\u9876\u539a\u5ea6");
    model.param().set("th_former", "0.2[mm]", "\u524d\u4f53\u539a\u5ea6");
    model.param().set("th_susp", "0.2[mm]", "\u60ac\u6302\u539a\u5ea6");
    model.param().create("par2");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("c0", "343[m/s]", "\u58f0\u901f");
    model.param("par2").set("fmax", "20[kHz]", "\u5206\u6790\u7684\u6700\u5927\u9891\u7387");
    model.param("par2").set("lam0", "c0/fmax", "\u6700\u5c0f\u6ce2\u957f");
    model.param("par2").set("f_loss", "40[Hz]", "\u7ed9\u5b9a\u635f\u8017\u56e0\u5b50\u7684\u9891\u7387");
    model.param("par2")
         .set("omega_loss", "2*pi*f_loss", "\u7ed9\u5b9a\u635f\u8017\u56e0\u5b50\u7684\u89d2\u9891\u7387");
    model.param("par2")
         .set("BL", "3.5[T*m]", "\u529b\u7cfb\u6570\uff0c\u901a\u91cf\u5bc6\u5ea6 (B) \u4e58\u4e0a\u7ebf\u5708\u957f\u5ea6 (L)");
    model.param("par2").set("R_E", "5.5[ohm]", "\u97f3\u5708\u963b\u6297");
    model.param("par2").set("L_e", "0.06[mH]", "\u97f3\u5708\u7535\u611f\uff08\u5e38\u6570\uff09");
    model.param("par2").set("n_e", "0.7", "\u97f3\u5708\u635f\u8017\u56e0\u5b50");
    model.param("par2").set("V0", "sqrt(2)[V]", "\u9a71\u52a8\u7535\u538b");
    model.param("par2").set("V0rms", "V0/sqrt(2)", "\u9a71\u52a8\u7535\u538b (rms)");

    model.func().create("int1", "Interpolation");
    model.func("int1").set("source", "file");
    model.func("int1").set("filename", "composite_dome_tweeter_freq_Lp_data.txt");
    model.func("int1").setIndex("argunit", "Hz", 0);
    model.func("int1").setEntry("columnType", "col2", "value");
    model.func("int1").setEntry("funcnames", "col2", "Lp1");
    model.func("int1").setIndex("fununit", "dB", 0);
    model.func("int1").setEntry("columnType", "col3", "value");
    model.func("int1").setEntry("funcnames", "col3", "Lp2");
    model.func("int1").setIndex("fununit", "dB", 1);
    model.func("int1").setEntry("funcnames", "col4", "Lp3");
    model.func("int1").setIndex("fununit", "dB", 2);
    model.func("int1").set("interp", "cubicspline");
    model.func("int1").set("extrap", "linear");
    model.func().create("int2", "Interpolation");
    model.func("int2").set("source", "file");
    model.func("int2").set("filename", "composite_dome_tweeter_freq_Z_data.txt");
    model.func("int2").setIndex("argunit", "Hz", 0);
    model.func("int2").setEntry("columnType", "col2", "value");
    model.func("int2").setEntry("funcnames", "col2", "Zabs1");
    model.func("int2").setIndex("fununit", "ohm", 0);
    model.func("int2").setEntry("columnType", "col3", "value");
    model.func("int2").setEntry("funcnames", "col3", "Zabs2");
    model.func("int2").setIndex("fununit", "ohm", 1);
    model.func("int2").setEntry("funcnames", "col4", "Zabs3");
    model.func("int2").setIndex("fununit", "ohm", 2);
    model.func("int2").set("extrap", "linear");

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("rho", "Analytic");
    model.material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("cs", "Analytic");
    model.material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.material("mat1").propertyGroup().create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.material("mat1").propertyGroup().create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.material("mat1").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.material("mat1").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.material("mat1").label("Air");
    model.material("mat1").set("family", "air");
    model.material("mat1").propertyGroup("def").label("Basic");
    model.material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.material("mat1").propertyGroup("def").func("rho").label("Analytic");
    model.material("mat1").propertyGroup("def").func("rho").set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.material("mat1").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.material("mat1").propertyGroup("def").func("rho").set("argunit", new String[]{"Pa", "K"});
    model.material("mat1").propertyGroup("def").func("rho").set("plotaxis", new String[]{"off", "on"});
    model.material("mat1").propertyGroup("def").func("rho").set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.material("mat1").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.material("mat1").propertyGroup("def").func("k").label("Piecewise 3");
    model.material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.material("mat1").propertyGroup("def").func("cs").label("Analytic 2");
    model.material("mat1").propertyGroup("def").func("cs").set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.material("mat1").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.material("mat1").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("def").func("cs").set("plotfixedvalue", new String[]{"273.15"});
    model.material("mat1").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.material("mat1").propertyGroup("def").func("an1").set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.material("mat1").propertyGroup("def").func("an1").set("argunit", new String[]{"Pa", "K"});
    model.material("mat1").propertyGroup("def").func("an1").set("plotaxis", new String[]{"off", "on"});
    model.material("mat1").propertyGroup("def").func("an1").set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.material("mat1").propertyGroup("def").func("an2").label("Analytic 2a");
    model.material("mat1").propertyGroup("def").func("an2").set("funcname", "muB");
    model.material("mat1").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.material("mat1").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("def").func("an2").set("plotfixedvalue", new String[]{"200"});
    model.material("mat1").propertyGroup("def").func("an2").set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.material("mat1").propertyGroup("def").set("molarmass", "");
    model.material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.material("mat1").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.material("mat1").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.material("mat1").propertyGroup("def").set("density", "rho(pA,T)");
    model.material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.material("mat1").propertyGroup("def").addInput("temperature");
    model.material("mat1").propertyGroup("def").addInput("pressure");
    model.material("mat1").propertyGroup("RefractiveIndex").label("Refractive index");
    model.material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.material("mat1").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.material("mat1").propertyGroup("idealGas").label("Ideal gas");
    model.material("mat1").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.material("mat1").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.material("mat1").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.material("mat1").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.material("mat1").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.material("mat1").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.material("mat1").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.material("mat1").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.material("mat1").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.material("mat1").propertyGroup("idealGas").addInput("temperature");
    model.material("mat1").propertyGroup("idealGas").addInput("pressure");
    model.material("mat1").materialType("nonSolid");
    model.material().create("mat2", "Common", "");
    model.material("mat2").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat2").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.material("mat2").label("Copper");
    model.material("mat2").set("family", "copper");
    model.material("mat2").propertyGroup("def").label("Basic");
    model.material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.material("mat2").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat2").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.material("mat2").propertyGroup("Enu").set("E", "110[GPa]");
    model.material("mat2").propertyGroup("Enu").set("nu", "0.35");
    model.material("mat2").propertyGroup("linzRes").label("Linearized resistivity");
    model.material("mat2").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.material("mat2").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.material("mat2").propertyGroup("linzRes").set("Tref", "298[K]");
    model.material("mat2").propertyGroup("linzRes").addInput("temperature");
    model.material().create("mat3", "Common", "");
    model.material("mat3").propertyGroup().create("Lame", "Lame", "Lam\u00e9 parameters");
    model.material("mat3").propertyGroup().create("MooneyRivlin", "MooneyRivlin", "Mooney-Rivlin");
    model.material("mat3").propertyGroup().create("Yeoh", "Yeoh", "Yeoh");
    model.material("mat3").propertyGroup().create("Varga", "Varga", "Varga");
    model.material("mat3").propertyGroup().create("ArrudaBoyce", "ArrudaBoyce", "Arruda-Boyce");
    model.material("mat3").label("Rubber");
    model.material("mat3").set("family", "rubber");
    model.material("mat3").propertyGroup("def").label("Basic");
    model.material("mat3").propertyGroup("def").set("density", "");
    model.material("mat3").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.material("mat3").propertyGroup("def").set("lossfactor", "");
    model.material("mat3").propertyGroup("def").set("thermalconductivity", "");
    model.material("mat3").propertyGroup("def").set("heatcapacity", "");
    model.material("mat3").propertyGroup("def").set("density", "1100[kg/m^3]");
    model.material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"200e-6[1/K]", "0", "0", "0", "200e-6[1/K]", "0", "0", "0", "200e-6[1/K]"});
    model.material("mat3").propertyGroup("def").set("lossfactor", "0.06");
    model.material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.5[W/(m*K)]", "0", "0", "0", "0.5[W/(m*K)]", "0", "0", "0", "0.5[W/(m*K)]"});
    model.material("mat3").propertyGroup("def").set("heatcapacity", "1900[J/(kg*K)]");
    model.material("mat3").propertyGroup("Lame").label("Lam\u00e9 parameters");
    model.material("mat3").propertyGroup("Lame").set("lambLame", "24.5[MPa]");
    model.material("mat3").propertyGroup("Lame").set("muLame", "0.5[MPa]");
    model.material("mat3").propertyGroup("MooneyRivlin").set("C10", "0.219[MPa]");
    model.material("mat3").propertyGroup("MooneyRivlin").set("C01", "0.031[MPa]");
    model.material("mat3").propertyGroup("MooneyRivlin").set("C11", "0[Pa]");
    model.material("mat3").propertyGroup("MooneyRivlin").set("C20", "0[Pa]");
    model.material("mat3").propertyGroup("MooneyRivlin").set("C02", "0[Pa]");
    model.material("mat3").propertyGroup("MooneyRivlin").set("C21", "0[Pa]");
    model.material("mat3").propertyGroup("MooneyRivlin").set("C12", "0[Pa]");
    model.material("mat3").propertyGroup("MooneyRivlin").set("C30", "0[Pa]");
    model.material("mat3").propertyGroup("MooneyRivlin").set("C03", "0[Pa]");
    model.material("mat3").propertyGroup("Yeoh").set("c1YE", "100[kPa]");
    model.material("mat3").propertyGroup("Yeoh").set("c2YE", "6[kPa]");
    model.material("mat3").propertyGroup("Yeoh").set("c3YE", "0[Pa]");
    model.material("mat3").propertyGroup("Varga").set("c1VA", "1.05[MPa]");
    model.material("mat3").propertyGroup("Varga").set("c2VA", "0.45[MPa]");
    model.material("mat3").propertyGroup("ArrudaBoyce").set("Nseg", "32");
    model.material("mat3").propertyGroup("ArrudaBoyce").set("mu0", "0.5[MPa]");
    model.material().create("mat4", "Common", "");
    model.material("mat4").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat4").label("Titanium beta-21S");
    model.material("mat4").set("family", "titanium");
    model.material("mat4").propertyGroup("def").label("Basic");
    model.material("mat4").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat4").propertyGroup("def")
         .set("electricconductivity", new String[]{"7.407e5[S/m]", "0", "0", "0", "7.407e5[S/m]", "0", "0", "0", "7.407e5[S/m]"});
    model.material("mat4").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"7.06e-6[1/K]", "0", "0", "0", "7.06e-6[1/K]", "0", "0", "0", "7.06e-6[1/K]"});
    model.material("mat4").propertyGroup("def").set("heatcapacity", "710[J/(kg*K)]");
    model.material("mat4").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat4").propertyGroup("def").set("density", "4940[kg/m^3]");
    model.material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"7.5[W/(m*K)]", "0", "0", "0", "7.5[W/(m*K)]", "0", "0", "0", "7.5[W/(m*K)]"});
    model.material("mat4").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.material("mat4").propertyGroup("Enu").set("E", "105[GPa]");
    model.material("mat4").propertyGroup("Enu").set("nu", "0.33");
    model.material().create("mat5", "Common", "");
    model.material("mat5").propertyGroup("def").set("density", "");
    model.material("mat5").propertyGroup("def").set("poissonsratio", "");
    model.material("mat5").propertyGroup("def").set("youngsmodulus", "");
    model.material("mat5").propertyGroup("def").set("density", new String[]{"2000[kg/m^3]"});
    model.material("mat5").propertyGroup("def").set("poissonsratio", new String[]{"0.33"});
    model.material("mat5").propertyGroup("def").set("youngsmodulus", new String[]{"70[GPa]"});
    model.material().create("mat6", "Common", "");
    model.material("mat6").propertyGroup("def").set("porosity", "");
    model.material("mat6").propertyGroup().create("PoroacousticsModel", "PoroacousticsModel", "Poroacoustics_model");
    model.material("mat6").propertyGroup("def").set("porosity", new String[]{"0.97"});
    model.material("mat6").propertyGroup("PoroacousticsModel").set("Rf", new String[]{"55000[Pa*s/m^2]"});
    model.material("mat6").propertyGroup("PoroacousticsModel").set("Lth", new String[]{"117[um]"});
    model.material("mat6").propertyGroup("PoroacousticsModel").set("Lv", new String[]{"41[um]"});
    model.material("mat6").propertyGroup("PoroacousticsModel").set("tau", new String[]{"2.47"});
    model.material()
         .insert("../Dynamics_and_Vibration/composite_dome_tweeter_eigen.mph", new String[]{"solidcp1mat", "solid2cp1mat"}, new String[]{});
    model.material().create("lmat1", "LayeredMaterial", "");
    model.material("lmat1").setIndex("link", "mat3", 0);
    model.material("lmat1").setIndex("thickness", "th_susp", 0);
    model.material().duplicate("lmat2", "lmat1");
    model.material("lmat2").setIndex("link", "mat5", 0);
    model.material("lmat2").setIndex("thickness", "th_former", 0);
    model.material().create("sw1", "Switch", "");
    model.material("sw1").feature().create("lmat1", "LayeredMaterial", "");
    model.material("sw1").feature("lmat1").setIndex("link", "mat4", 0);
    model.material("sw1").feature("lmat1").setIndex("thickness", "th_dome", 0);
    model.material("sw1").feature().duplicate("lmat2", "lmat1");
    model.material("sw1").feature("lmat2").setIndex("link", "solidcp1mat", 0);
    model.material("sw1").feature().duplicate("lmat3", "lmat2");
    model.material("sw1").feature("lmat3").setIndex("link", "solid2cp1mat", 0);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1")
         .set("filename", "composite_dome_tweeter_freq_geom_sequence.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();

    model.component("comp1").view("view1").set("showgrid", false);

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").set(2, 3, 4, 8, 11);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").set(2, 8);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").set(9);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").geom(2);
    model.component("comp1").selection("sel4").set(41, 51);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").geom(2);
    model.component("comp1").selection("sel5").set(35, 37);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").geom(2);
    model.component("comp1").selection("sel6").set(12, 39);
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").set("entitydim", 2);
    model.component("comp1").selection("uni1").set("input", new String[]{"sel4", "sel5", "sel6"});
    model.component("comp1").selection().create("sel7", "Explicit");
    model.component("comp1").selection("sel7").geom(2);
    model.component("comp1").selection("sel7").set(13);
    model.component("comp1").selection().create("sel8", "Explicit");
    model.component("comp1").selection("sel8").geom(2);
    model.component("comp1").selection("sel8").set(35, 36, 38, 40);
    model.component("comp1").selection().create("sel9", "Explicit");
    model.component("comp1").selection("sel9").geom(2);
    model.component("comp1").selection("sel9").set(12, 35, 37, 39, 41, 51);
    model.component("comp1").selection().create("sel10", "Explicit");
    model.component("comp1").selection("sel10").geom(2);
    model.component("comp1").selection("sel10").set(51);
    model.component("comp1").selection().create("sel11", "Explicit");
    model.component("comp1").selection("sel11").geom(2);
    model.component("comp1").selection("sel11")
         .set(1, 2, 4, 5, 7, 8, 10, 11, 14, 17, 20, 23, 34, 44, 53, 66, 67, 68, 69, 70, 71, 72);
    model.component("comp1").selection().create("sel12", "Explicit");
    model.component("comp1").selection("sel12").geom(1);
    model.component("comp1").selection("sel12").set(12, 13, 44, 47, 50, 55, 116, 118, 120, 122);

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().named("sel3");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("L_E", "(L_e/(sin(n_e*pi/2)))*(acpr.omega[s/rad])^(n_e-1)");
    model.component("comp1").variable("var1").descr("L_E", "Voice coil inductance (frequency dependent)");
    model.component("comp1").variable("var1").set("Rp_E", "(L_e/(cos(n_e*pi/2)))*(acpr.omega[s/rad])^(n_e)[ohm/H]");
    model.component("comp1").variable("var1").descr("Rp_E", "Resistance (losses in magnetic system)");
    model.component("comp1").variable("var1").set("v0", "aveop1(solid.u_tZ)");
    model.component("comp1").variable("var1").descr("v0", "Voice coil velocity");

    model.component("comp1").material().create("matlnk1", "Link");
    model.component("comp1").material("matlnk1").selection().named("sel1");
    model.component("comp1").material().duplicate("matlnk2", "matlnk1");
    model.component("comp1").material("matlnk2").selection().named("sel3");
    model.component("comp1").material("matlnk2").set("link", "mat2");
    model.component("comp1").material().create("llmat1", "LayeredMaterialLink");
    model.component("comp1").material("llmat1").selection().named("sel4");
    model.component("comp1").material("llmat1").set("middlePlane", "top");
    model.component("comp1").material().duplicate("llmat2", "llmat1");

    model.component("comp1").extraDim("llmat2_xdim_ad").selection().geom("geom1", 2);
    model.component("comp1").extraDim("llmat2_xdim_ad").selection().set(41, 51);
    model.component("comp1").extraDim("llmat2_xdim_ad").selection().inherit(false);
    model.component("comp1").extraDim("llmat2_xdim_ad").selection().embedded(false);
    model.component("comp1").extraDim("llmat2_xdim_ad").selection().inherit(true);

    model.component("comp1").material("llmat2").selection().named("sel5");
    model.component("comp1").material("llmat2").set("link", "lmat2");
    model.component("comp1").material().duplicate("llmat3", "llmat2");

    model.component("comp1").extraDim("llmat3_xdim_ad").selection().geom("geom1", 2);
    model.component("comp1").extraDim("llmat3_xdim_ad").selection().set(35, 37);
    model.component("comp1").extraDim("llmat3_xdim_ad").selection().inherit(false);
    model.component("comp1").extraDim("llmat3_xdim_ad").selection().embedded(false);
    model.component("comp1").extraDim("llmat3_xdim_ad").selection().inherit(true);

    model.component("comp1").material("llmat3").selection().named("sel6");
    model.component("comp1").material("llmat3").set("link", "sw1");

    model.component("comp1").coordSystem("sys1").set("mastercoordsystcomp", "1");

    model.component("comp1").physics("lshell").selection().named("uni1");
    model.component("comp1").physics("lshell").feature("lemm1").set("SolidModel", "Anisotropic");
    model.component("comp1").physics("lshell").feature("lemm1").create("dmp1", "Damping", 2);
    model.component("comp1").physics("lshell").feature("lemm1").feature("dmp1").selection().named("sel4");
    model.component("comp1").physics("lshell").feature("lemm1").feature("dmp1").set("beta_dK", "0.46/omega_loss");
    model.component("comp1").physics("lshell").create("contls1", "ContinuityLayeredShell", 1);
    model.component("comp1").physics("lshell").feature("contls1").set("shelllist_src", "llmat3");
    model.component("comp1").physics("lshell").feature("contls1").set("shelllist", "llmat2");
    model.component("comp1").physics("lshell").feature("contls1").setIndex("shelllist_lName", 0, 0, 0);
    model.component("comp1").physics("lshell").feature().duplicate("contls2", "contls1");
    model.component("comp1").physics("lshell").feature("contls2").set("shelllist", "llmat1");
    model.component("comp1").physics("lshell").feature("contls2").setIndex("shelllist_lName", 0, 0, 0);
    model.component("comp1").physics("lshell").create("fix1", "Fixed", 2);
    model.component("comp1").physics("lshell").feature("fix1").selection().named("sel10");
    model.component("comp1").physics("lshell").create("sym1", "Symmetry", 1);
    model.component("comp1").physics("lshell").feature("sym1").selection().named("sel12");
    model.component("comp1").physics("solid").selection().named("sel3");
    model.component("comp1").physics("solid").create("bl1", "BodyLoad", 3);
    model.component("comp1").physics("solid").feature("bl1").selection().all();
    model.component("comp1").physics("solid").feature("bl1").set("forceType", "TotalForce");
    model.component("comp1").physics("solid").feature("bl1")
         .set("force", new String[]{"0", "0", "0.25*BL*cir.R1.i"});
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection().named("sel11");
    model.component("comp1").physics("acpr").selection().named("sel1");
    model.component("comp1").physics("acpr").create("pom1", "PoroacousticsModel", 3);
    model.component("comp1").physics("acpr").feature("pom1").selection().named("sel2");
    model.component("comp1").physics("acpr").feature("pom1").set("FluidModel", "JohnsonChampouxAllard");
    model.component("comp1").physics("acpr").feature("pom1").set("SolidMaterial", "mat6");
    model.component("comp1").physics("acpr").create("pmb1", "PerfectlyMatchedBoundary", 2);
    model.component("comp1").physics("acpr").feature("pmb1").selection().named("sel7");
    model.component("comp1").physics("acpr").create("efc1", "ExteriorFieldCalculation", 2);
    model.component("comp1").physics("acpr").feature("efc1").selection().named("sel7");
    model.component("comp1").physics("acpr").feature("efc1").set("SymmetryType", "SectorSymmetryWithPlane");
    model.component("comp1").physics("acpr").feature("efc1").set("TransformationType", "RotationAndReflection");
    model.component("comp1").physics("acpr").feature("efc1").set("n", 4);
    model.component("comp1").physics("acpr").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("acpr").feature("sym1").selection().named("sel11");
    model.component("comp1").physics("cir").create("V1", "VoltageSource", -1);
    model.component("comp1").physics("cir").feature("V1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("V1").set("value", "V0");
    model.component("comp1").physics("cir").create("R1", "Resistor", -1);
    model.component("comp1").physics("cir").feature("R1").setIndex("Connections", 1, 0, 0);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("cir").feature("R1").setIndex("Connections", 2, 1, 0);
    model.component("comp1").physics("cir").feature("R1").set("R", "R_E");
    model.component("comp1").physics("cir").create("L1", "Inductor", -1);
    model.component("comp1").physics("cir").feature("L1").setIndex("Connections", 2, 0, 0);
    model.component("comp1").physics("cir").feature("L1").setIndex("Connections", 3, 1, 0);
    model.component("comp1").physics("cir").feature("L1").set("L", "L_E");
    model.component("comp1").physics("cir").create("R2", "Resistor", -1);
    model.component("comp1").physics("cir").feature("R2").setIndex("Connections", 2, 0, 0);
    model.component("comp1").physics("cir").feature("R2").setIndex("Connections", 3, 1, 0);
    model.component("comp1").physics("cir").feature("R2").set("R", "Rp_E");
    model.component("comp1").physics("cir").create("V2", "VoltageSource", -1);
    model.component("comp1").physics("cir").feature("V2").setIndex("Connections", 3, 0, 0);
    model.component("comp1").physics("cir").feature("V2").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("V2").set("value", "BL*v0");

    model.component("comp1").multiphysics().create("lssc1", "LayeredShellStructCladding", -1);
    model.component("comp1").multiphysics().create("asb1", "AcousticStructureBoundary", 2);
    model.component("comp1").multiphysics("asb1").set("Structure_physics", "solid");
    model.component("comp1").multiphysics("asb1").selection().named("sel8");
    model.component("comp1").multiphysics().create("asb2", "AcousticStructureBoundary", 2);
    model.component("comp1").multiphysics("asb2").selection().named("sel9");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("uni1");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().named("sel6");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "1[mm]");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmin", "1[mm]");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature().duplicate("size2", "size1");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().named("sel4");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature().duplicate("size3", "size2");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").selection().named("sel5");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").set("hmax", "4[mm]");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().named("sel3");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().named("sel7");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 1);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "lam0/6");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "2[mm]");
    model.component("comp1").mesh("mesh1").feature("size").set("hcurve", 0.5);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").create("matsw", "MaterialSweep");
    model.study("std1").feature("matsw").setIndex("pname", "matsw.sw1", 0);
    model.study("std1").feature("matsw").setIndex("pcase", "all", 0);
    model.study("std1").feature("matsw").setIndex("plistarr", "range(1,1,3)", 0);
    model.study("std1").feature("matsw").setIndex("pname", "matsw.sw1", 0);
    model.study("std1").feature("matsw").setIndex("pcase", "all", 0);
    model.study("std1").feature("matsw").setIndex("plistarr", "range(1,1,3)", 0);
    model.study("std1").feature("freq")
         .set("plist", "{100, 125, 160, 200, 250, 315, 400, 500, 630, 800, 1e3, 1.25e3, 1.6e3, 2e3, 2.5e3, 3.15e3, 4e3, 5e3, 6.3e3, 8e3, 1e4, 1.25e4, 1.6e4}");
    model.study("std1").feature("freq").set("usestol", true);
    model.study("std1").feature("freq").set("stol", "0.0001");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("i2").active(true);
    model.sol("sol1").feature("s1").feature("i2").set("itrestart", 300);
    model.sol("sol1").feature("s1").feature("i2").set("prefuntype", "right");
    model.sol("sol1").feature("s1").feature("i2").feature("dp1").set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("i2").feature("dp1")
         .set("hybridvar", new String[]{"comp1_u2", "comp1_u", "comp1_currents", "comp1_voltages", "comp1_current_time"});

    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("pm1").feature("so1").set("psol", "sol2");
    model.batch("pm1").run("compute");

    model.result().setOnlyPlotWhenRequested(true);
    model.result().dataset().create("dset2lshelllshl", "LayeredMaterial");
    model.result().dataset("dset2lshelllshl").set("data", "dset2");
    model.result().dataset("dset2lshelllshl")
         .set("defaultPlotIDs", new String[]{"stress|lshell", "displacement|lshell", "velocity|lshell"});
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset2lshelllshl");
    model.result("pg1").setIndex("looplevel", 23, 0);
    model.result("pg1").setIndex("looplevel", 3, 1);
    model.result("pg1").label("\u4f4d\u79fb (lshell)");
    model.result("pg1").set("showlegends", true);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"lshell.disp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("surf1").feature("def")
         .set("descr", "\u4f4d\u79fb\u573a \uff08\u6750\u6599\u548c\u51e0\u4f55\u5750\u6807\u7cfb\uff09");
    model.result("pg1").label("\u4f4d\u79fb (lshell)");
    model.result().dataset().create("sec1", "Sector3D");
    model.result().dataset("sec1").set("data", "dset2lshelllshl");
    model.result().dataset("sec1").set("sectors", 4);
    model.result().dataset("sec1").set("trans", "rotrefl");
    model.result("pg1").set("data", "sec1");
    model.result("pg1").setIndex("looplevel", 1, 1);
    model.result("pg1").setIndex("looplevel", 21, 0);
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("paramindicator", "freq=10000 Hz");
    model.result("pg1").set("plotarrayenable", true);
    model.result("pg1").feature("surf1").set("arraydim", "1");
    model.result("pg1").feature().duplicate("surf2", "surf1");
    model.result("pg1").feature("surf2").set("arraydim", "1");
    model.result("pg1").feature("surf2").set("data", "sec1");
    model.result("pg1").feature("surf2").setIndex("looplevel", 2, 1);
    model.result("pg1").feature("surf2").setIndex("looplevel", 21, 0);
    model.result("pg1").feature("surf2").set("inheritplot", "surf1");
    model.result("pg1").feature().duplicate("surf3", "surf2");
    model.result("pg1").feature("surf3").set("arraydim", "1");
    model.result("pg1").feature("surf3").setIndex("looplevel", 3, 1);
    model.result("pg1").create("tlan1", "TableAnnotation");
    model.result("pg1").feature("tlan1").set("arraydim", "1");
    model.result("pg1").feature("tlan1").set("source", "localtable");
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "-20[mm]", 0, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "-80[mm]", 0, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0, 0, 2);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "Titanium", 0, 3);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "80[mm]", 1, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "-80[mm]", 1, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0, 1, 2);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "Composite Material 1", 1, 3);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "200[mm]", 2, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "-80[mm]", 2, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0, 2, 2);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "Composite Material 2", 2, 3);
    model.result("pg1").feature("tlan1").set("showpoint", false);
    model.result("pg1").run();

    model.view("view6").set("showgrid", false);

    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset("mir1").set("data", "dset2");
    model.result().dataset().duplicate("mir2", "mir1");
    model.result().dataset("mir2").set("data", "mir1");
    model.result().dataset("mir2").set("quickplane", "xz");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "mir2");
    model.result("pg2").setIndex("looplevel", 1, 1);
    model.result("pg2").setIndex("looplevel", 21, 0);
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("paramindicator", "freq=10000 Hz");
    model.result("pg2").set("edges", false);
    model.result("pg2").set("plotarrayenable", true);
    model.result("pg2").create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("arraydim", "1");
    model.result("pg2").feature("mslc1").set("expr", "acpr.p_t");
    model.result("pg2").feature("mslc1").set("znumber", "0");
    model.result("pg2").feature("mslc1").set("colortable", "Wave");
    model.result("pg2").feature("mslc1").set("colorscalemode", "linearsymmetric");
    model.result("pg2").feature().duplicate("mslc2", "mslc1");
    model.result("pg2").feature("mslc2").set("arraydim", "1");
    model.result("pg2").feature("mslc2").set("data", "mir2");
    model.result("pg2").feature("mslc2").setIndex("looplevel", 2, 1);
    model.result("pg2").feature("mslc2").setIndex("looplevel", 21, 0);
    model.result("pg2").feature("mslc2").set("inheritplot", "mslc1");
    model.result("pg2").feature().duplicate("mslc3", "mslc2");
    model.result("pg2").feature("mslc3").set("arraydim", "1");
    model.result("pg2").feature("mslc3").setIndex("looplevel", 3, 1);
    model.result("pg2").create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("arraydim", "1");
    model.result().dataset().duplicate("sec2", "sec1");
    model.result().dataset("sec2").set("data", "dset2");
    model.result().dataset("sec2").set("include", "manual");
    model.result().dataset("sec2").set("sectorsinclude", 3);
    model.result().dataset("sec2").set("startsector", 3);
    model.result("pg2").feature("vol1").set("arraydim", "1");
    model.result("pg2").feature("vol1").set("data", "sec2");
    model.result("pg2").feature("vol1").set("expr", "1");
    model.result("pg2").feature("vol1").set("coloring", "uniform");
    model.result("pg2").feature("vol1").set("color", "gray");
    model.result("pg2").feature("vol1").set("manualindexing", true);
    model.result("pg2").feature("vol1").create("sel1", "Selection");
    model.result("pg2").feature("vol1").feature("sel1").selection().set(1, 5, 6, 7, 10);
    model.result("pg2").feature("vol1").set("arraydim", "1");
    model.result("pg2").feature().duplicate("vol2", "vol1");
    model.result("pg2").feature("vol2").set("arraydim", "1");
    model.result("pg2").feature("vol2").set("arrayindex", 1);
    model.result("pg2").feature().duplicate("vol3", "vol2");
    model.result("pg2").feature("vol3").set("arraydim", "1");
    model.result("pg2").feature("vol3").set("arrayindex", 2);
    model.result("pg2").create("tlan1", "TableAnnotation");
    model.result("pg2").feature("tlan1").set("arraydim", "1");
    model.result("pg2").feature("tlan1").set("source", "localtable");
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", "-20[mm]", 0, 0);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", "-140[mm]", 0, 1);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", 0, 0, 2);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", "Titanium", 0, 3);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", "260[mm]", 1, 0);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", "-140[mm]", 1, 1);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", 0, 1, 2);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", "Composite Material 1", 1, 3);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", "540[mm]", 2, 0);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", "-140[mm]", 2, 1);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", 0, 2, 2);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", "Composite Material 2", 2, 3);
    model.result("pg2").feature("tlan1").set("showpoint", false);
    model.result("pg2").run();

    model.view("view7").set("showgrid", false);

    model.result().duplicate("pg3", "pg2");
    model.result("pg3").feature("mslc1").set("arraydim", "1");
    model.result("pg3").feature("mslc1").set("expr", "acpr.Lp_t");
    model.result("pg3").feature("mslc1").set("colortable", "Rainbow");
    model.result("pg3").feature("mslc1").set("colorscalemode", "linear");
    model.result("pg3").feature("mslc2").set("arraydim", "1");
    model.result("pg3").feature("mslc2").set("expr", "acpr.Lp_t");
    model.result("pg3").feature("mslc3").set("arraydim", "1");
    model.result("pg3").feature("mslc3").set("expr", "acpr.Lp_t");
    model.result("pg3").run();

    model.func("int1").createPlot("pg4");

    model.result().dataset("int1_ds1").set("parmin1", 100);
    model.result().dataset("int1_ds1").set("parmax1", 16000);

    model.func("int2").createPlot("pg5");

    model.result().dataset("int2_ds1").set("parmin1", 100);
    model.result().dataset("int2_ds1").set("parmax1", 16000);
    model.result("pg4").set("titletype", "label");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("legendpos", "lowerleft");
    model.result("pg4").create("oct1", "OctaveBand");
    model.result("pg4").feature("oct1").set("quantity", "bandpower");
    model.result("pg4").feature("oct1").set("markerpos", "datapoints");
    model.result("pg4").feature("oct1").set("linewidth", "preference");
    model.result("pg4").feature("oct1").set("data", "dset2");
    model.result("pg4").feature("oct1").set("expr", "pext(0,0,1[m])");
    model.result("pg4").feature("oct1").set("quantity", "continuous");
    model.result("pg4").feature("oct1").set("linestyle", "none");
    model.result("pg4").feature("oct1").set("linemarker", "point");
    model.result("pg4").feature("oct1").set("linecolor", "cyclereset");
    model.result("pg4").feature("oct1").set("legend", true);
    model.result("pg4").feature("oct1").set("legendmethod", "manual");
    model.result("pg4").feature("oct1").setIndex("legends", "Titanium", 0);
    model.result("pg4").feature("oct1").setIndex("legends", "Composite Material 1", 1);
    model.result("pg4").feature("oct1").setIndex("legends", "Composite Material 2", 2);
    model.result("pg4").feature("plot1").setIndex("legends", "Titanium, Resolved", 0);
    model.result("pg4").feature("plot2").setIndex("legends", "Composite Material 1, Resolved", 0);
    model.result("pg4").feature("plot3").setIndex("legends", "Composite Material 2, Resolved", 0);
    model.result("pg4").run();
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").set("data", "dset2");
    model.result("pg5").feature("glob1").setIndex("expr", "abs(cir.V1.v/cir.V1.i)", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "abs(Z)", 0);
    model.result("pg5").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg5").feature("glob1").set("linestyle", "none");
    model.result("pg5").feature("glob1").set("linemarker", "point");
    model.result("pg5").feature("glob1").set("linecolor", "cyclereset");
    model.result("pg5").feature("glob1").set("legendmethod", "manual");
    model.result("pg5").feature("glob1").setIndex("legends", "Titanium", 0);
    model.result("pg5").feature("glob1").setIndex("legends", "Composite Material 1", 1);
    model.result("pg5").feature("glob1").setIndex("legends", "Composite Material 2", 2);
    model.result("pg5").feature("plot1").setIndex("legends", "Titanium, Resolved", 0);
    model.result("pg5").feature("plot2").setIndex("legends", "Composite Material 1, Resolved", 0);
    model.result("pg5").feature("plot3").setIndex("legends", "Composite Material 2, Resolved", 0);
    model.result("pg5").run();
    model.result().create("pg6", "PolarGroup");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").setIndex("looplevelinput", "manual", 1);
    model.result("pg6").setIndex("looplevel", new int[]{1}, 1);
    model.result("pg6").setIndex("looplevelinput", "manual", 0);
    model.result("pg6").setIndex("looplevel", new int[]{21}, 0);
    model.result("pg6").set("titletype", "label");
    model.result("pg6").set("zeroangle", "up");
    model.result("pg6").set("rotdir", "cw");
    model.result("pg6").create("rp1", "RadiationPattern");
    model.result("pg6").feature("rp1").set("markerpos", "datapoints");
    model.result("pg6").feature("rp1").set("linewidth", "preference");
    model.result("pg6").feature("rp1").set("phidisc", 180);
    model.result("pg6").feature("rp1").set("anglerestr", "manual");
    model.result("pg6").feature("rp1").set("phimin", -90);
    model.result("pg6").feature("rp1").set("phirange", 180);
    model.result("pg6").feature("rp1").set("normal", new int[]{0, 1, 0});
    model.result("pg6").feature("rp1").set("radius", 1000);
    model.result("pg6").feature("rp1").set("refdir", new int[]{0, 0, 1});
    model.result("pg6").feature("rp1").set("legend", true);
    model.result("pg6").feature("rp1").set("legendmethod", "manual");
    model.result("pg6").feature("rp1").setIndex("legends", "freq=10000 Hz", 0);
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").setIndex("looplevel", new int[]{2}, 1);
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").setIndex("looplevel", new int[]{3}, 1);
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").set("data", "dset2");
    model.result("pg9").setIndex("looplevelinput", "manual", 1);
    model.result("pg9").setIndex("looplevel", new int[]{3}, 1);
    model.result("pg9").set("ylabelactive", true);
    model.result("pg9").set("xlabelactive", true);
    model.result("pg9").create("dir1", "Directivity");
    model.result("pg9").feature("dir1").set("linewidth", "preference");
    model.result("pg9").feature("dir1").set("phidisc", 180);
    model.result("pg9").feature("dir1").set("anglerestr", "manual");
    model.result("pg9").feature("dir1").set("phimin", -90);
    model.result("pg9").feature("dir1").set("phirange", 180);
    model.result("pg9").feature("dir1").set("normal", new int[]{0, 1, 0});
    model.result("pg9").feature("dir1").set("radius", 1000);
    model.result("pg9").feature("dir1").set("refdir", new int[]{0, 0, 1});
    model.result("pg9").run();
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").set("titletype", "none");
    model.result("pg10").set("data", "mir1");
    model.result("pg10").setIndex("looplevel", 2, 1);
    model.result("pg10").setIndex("looplevel", 21, 0);
    model.result("pg10").set("edges", false);
    model.result("pg10").create("vol1", "Volume");
    model.result("pg10").feature("vol1").set("expr", "1");
    model.result("pg10").feature("vol1").create("mtrl1", "MaterialAppearance");
    model.result("pg10").feature("vol1").feature("mtrl1").set("appearance", "custom");
    model.result("pg10").feature("vol1").feature("mtrl1").set("family", "steelanodized");
    model.result("pg10").feature("vol1").feature("mtrl1").set("color", "custom");
    model.result("pg10").feature("vol1").feature("mtrl1")
         .set("customcolor", new double[]{0.9803921580314636, 0.9411764740943909, 0.9019607901573181});
    model.result("pg10").feature("vol1").create("sel1", "Selection");
    model.result("pg10").feature("vol1").feature("sel1").selection().set(1, 5, 7, 10);
    model.result("pg10").feature().duplicate("vol2", "vol1");
    model.result("pg10").feature("vol2").feature().remove("mtrl1");
    model.result("pg10").feature("vol2").set("coloring", "uniform");
    model.result("pg10").feature("vol2").set("color", "black");
    model.result("pg10").feature("vol2").feature("sel1").selection().set(6);
    model.result("pg10").create("vol3", "Volume");
    model.result("pg10").feature("vol3").set("expr", "1");
    model.result("pg10").feature("vol3").set("coloring", "uniform");
    model.result("pg10").feature("vol3").set("color", "custom");
    model.result("pg10").feature("vol3")
         .set("customcolor", new double[]{0.6509804129600525, 0.8392156958580017, 0.8156862854957581});
    model.result("pg10").feature("vol3").create("sel1", "Selection");
    model.result("pg10").feature("vol3").feature("sel1").selection().named("sel2");
    model.result("pg10").run();
    model.result("pg10").set("view", "new");
    model.result("pg10").run();

    model.view("view9").set("showgrid", false);

    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("expr", "1");
    model.result("pg10").feature("surf1").set("coloring", "uniform");
    model.result("pg10").feature("surf1").set("color", "black");
    model.result("pg10").feature("surf1").create("sel1", "Selection");
    model.result("pg10").feature("surf1").feature("sel1").selection().named("sel4");
    model.result("pg10").feature("surf1").create("filt1", "Filter");
    model.result("pg10").feature("surf1").feature("filt1").set("expr", "y>0");
    model.result("pg10").run();
    model.result("pg10").feature().duplicate("surf2", "surf1");
    model.result("pg10").feature("surf2").set("color", "white");
    model.result("pg10").feature("surf2").feature("sel1").selection().named("sel5");
    model.result("pg10").run();
    model.result("pg10").feature().duplicate("surf3", "surf2");
    model.result("pg10").feature("surf3").feature("sel1").selection().named("sel6");
    model.result("pg10").feature("surf3").create("mtrl1", "MaterialAppearance");
    model.result("pg10").feature("surf3").feature("mtrl1").set("appearance", "custom");
    model.result("pg10").feature("surf3").feature("mtrl1").set("family", "custom");
    model.result("pg10").feature("surf3").feature("mtrl1")
         .set("customspecular", new double[]{0.7411764860153198, 0.7882353067398071, 0.8470588326454163});
    model.result("pg10").feature("surf3").feature("mtrl1")
         .set("customdiffuse", new double[]{0.4117647111415863, 0.4117647111415863, 0.4117647111415863});
    model.result("pg10").feature("surf3").feature("mtrl1")
         .set("customambient", new double[]{0.501960813999176, 0.501960813999176, 0.501960813999176});
    model.result("pg10").feature("surf3").feature("mtrl1").set("noisefreq", "1E3");
    model.result("pg10").feature("surf3").feature("mtrl1").set("normalnoisebrush", "2");
    model.result("pg10").feature("surf3").feature("mtrl1").set("colornoisefrequency", "1E3");
    model.result("pg10").feature("surf3").feature("mtrl1").set("colornoisebrush", "1");
    model.result("pg10").feature("surf3").feature("mtrl1").set("customnoisecolor", new double[]{0, 0, 0});
    model.result("pg10").run();
    model.result("pg10").create("str1", "Streamline");
    model.result("pg10").feature("str1").set("expr", new String[]{"-sin(atan2(y,x))", "v", "w"});
    model.result("pg10").feature("str1").setIndex("expr", "cos(atan2(y,x))", 1);
    model.result("pg10").feature("str1").setIndex("expr", 0, 2);
    model.result("pg10").feature("str1").set("posmethod", "uniform");
    model.result("pg10").feature("str1").set("udist", 0.01);
    model.result("pg10").feature("str1").set("linetype", "tube");
    model.result("pg10").feature("str1").set("radiusexpr", "0.25");
    model.result("pg10").feature("str1").set("tuberadiusscaleactive", true);
    model.result("pg10").feature("str1").set("color", "custom");
    model.result("pg10").feature("str1")
         .set("customcolor", new double[]{0.7686274647712708, 0.4156862795352936, 0.2823529541492462});
    model.result("pg10").feature("str1").create("sel1", "Selection");
    model.result("pg10").feature("str1").feature("sel1").selection().named("sel3");
    model.result("pg10").run();
    model.result("pg10").create("rp1", "RadiationPattern");
    model.result("pg10").feature("rp1").set("data", "dset2");
    model.result("pg10").feature("rp1").set("solutionparams", "parent");
    model.result("pg10").feature("rp1").set("thresholdactive", true);
    model.result("pg10").feature("rp1").set("threshold", 56);
    model.result("pg10").feature("rp1").set("thetadisc", 100);
    model.result("pg10").feature("rp1").set("phidisc", 120);
    model.result("pg10").feature("rp1").set("anglerestr", "manual");
    model.result("pg10").feature("rp1").set("thetarange", 90);
    model.result("pg10").feature("rp1").set("sphere", "manual");
    model.result("pg10").feature("rp1").set("radius", 1000);
    model.result("pg10").feature("rp1").create("tran1", "Transparency");
    model.result("pg10").feature("rp1").feature("tran1").set("transparency", 0.2);
    model.result("pg10").feature("rp1").feature("tran1").set("uniformblending", 0.8);
    model.result("pg10").feature("rp1").create("trn1", "Transformation");
    model.result("pg10").feature("rp1").feature("trn1").set("enablescale", true);
    model.result("pg10").feature("rp1").feature("trn1").set("scale", new int[]{2, 2, 2});
    model.result("pg10").feature("rp1").feature("trn1").set("move", new int[]{0, 0, 10});
    model.result("pg10").run();
    model.result().setOnlyPlotWhenRequested(false);
    model.result("pg10").run();

    model
         .title("\u5e26\u590d\u5408\u632f\u819c\u7684\u5706\u9876\u9ad8\u97f3\u626c\u58f0\u5668 - \u9891\u57df\u54cd\u5e94");

    model
         .description("\u626c\u58f0\u5668\u8bbe\u8ba1\u662f\u4e00\u9879\u5177\u6709\u6311\u6218\u6027\u7684\u4efb\u52a1\uff0c\u5176\u8bbe\u8ba1\u76ee\u6807\u662f\u5728\u4e0d\u8fdd\u53cd\u5236\u9020\u548c\u64cd\u4f5c\u7ea6\u675f\u7684\u60c5\u51b5\u4e0b\u5b9e\u73b0\u66f4\u597d\u7684\u97f3\u8d28\u3002\u97f3\u8d28\u53d6\u51b3\u4e8e\u8bb8\u591a\u56e0\u7d20\uff0c\u5176\u4e2d\u4e4b\u4e00\u662f\u63a7\u5236\u3001\u6291\u5236\u548c\u8c03\u6574\u632f\u819c\u5171\u632f\u7684\u80fd\u529b\u3002\u53e6\u4e00\u4e2a\u56e0\u7d20\u662f\u63a7\u5236\u632f\u819c\u7834\u88c2\u7684\u80fd\u529b\u3002\n\n\u672c\u4f8b\u6bd4\u8f83\u4e86\u91c7\u7528\u4e0d\u540c\u632f\u819c\u6750\u6599\u7684\u5706\u9876\u9ad8\u97f3\u626c\u58f0\u5668\u7684\u9891\u7387\u54cd\u5e94\u3002\u4e0e\u949b\u7b49\u4f20\u7edf\u632f\u819c\u6750\u6599\u76f8\u6bd4\uff0c\u590d\u5408\u6750\u6599\u7684\u9891\u7387\u54cd\u5e94\u66f4\u52a0\u5e73\u6ed1\u3002\u590d\u5408\u6750\u6599\u80fd\u591f\u6253\u7834\u632f\u578b\u7684\u5bf9\u79f0\u6027\u5e76\u6539\u53d8\u5171\u632f\uff0c\u4ece\u800c\u53ef\u4ee5\u6539\u5584\u5706\u9876\u9ad8\u97f3\u626c\u58f0\u5668\u7684\u9891\u54cd\u6027\u80fd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("composite_dome_tweeter_freq.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
