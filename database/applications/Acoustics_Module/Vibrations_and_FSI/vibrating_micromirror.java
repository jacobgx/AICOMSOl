/*
 * vibrating_micromirror.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:41 by COMSOL 6.3.0.290. */
public class vibrating_micromirror {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Vibrations_and_FSI");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("shell", "Shell", "geom1");
    model.component("comp1").physics().create("ta", "ThermoacousticsSinglePhysics", "geom1");
    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");

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
    model.study("std1").feature("eig").setSolveFor("/physics/shell", true);
    model.study("std1").feature("eig").setSolveFor("/physics/ta", true);
    model.study("std1").feature("eig").setSolveFor("/physics/acpr", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("f0", "10.5[kHz]", "\u5206\u6790\u7684\u4e2d\u5fc3\u9891\u7387");
    model.param().set("deltaf", "500[Hz]", "\u9891\u7387\u8303\u56f4");
    model.param()
         .set("dvisc", "0.22[mm]*sqrt(100[Hz]/f0)", "f0 \u7684\u7a7a\u6c14\u9ecf\u6ede\u8fb9\u754c\u5c42\u539a\u5ea6");
    model.param().set("h_mirror", "1[um]", "\u955c\u50cf\u539a\u5ea6");

    model.component("comp1").geom("geom1").insertFile("vibrating_micromirror_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("f_num", "withsol('sol1',freq,setind(lambda,1))", "\u7b2c\u4e00\u7279\u5f81\u9891\u7387");
    model.component("comp1").variable("var1")
         .set("f_r", "real(f_num)", "\u8c10\u632f\u9891\u7387\uff08\u7b2c\u4e00\u6a21\u5f0f\uff09");
    model.component("comp1").variable("var1").set("omega_r", "2*pi*f_r", "\u8c10\u632f\u89d2\u9891\u7387");
    model.component("comp1").variable("var1").set("alpha_r", "imag(f_num)*2*pi", "\u8870\u51cf\u7cfb\u6570");
    model.component("comp1").variable("var1").set("Q_r", "omega_r/(2*alpha_r)", "Q \u56e0\u5b50");
    model.component("comp1").variable("var1").set("df_r", "f_r/Q_r", "\u8c10\u632f\u534a\u529f\u7387\u5bbd\u5ea6");
    model.component("comp1").variable("var1")
         .set("P_simple", "20*log10(abs(omega_r^2/((ta.iomega)^2+2*alpha_r*ta.iomega+omega_r^2)))-20*log10(abs(omega_r/(2*i*alpha_r)))", "\u7b80\u8c10\u632f\u5b50\u6a21\u578b\u62df\u5408");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u58f3");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(9);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u5bf9\u79f0");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(1, 2, 6, 11, 15);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u5bf9\u79f0\u8fb9");
    model.component("comp1").selection("sel3").geom(1);
    model.component("comp1").selection("sel3").set(9);

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
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat2").label("Silicon");
    model.component("comp1").material("mat2").set("family", "custom");
    model.component("comp1").material("mat2").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.component("comp1").material("mat2").set("diffuse", "custom");
    model.component("comp1").material("mat2")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.component("comp1").material("mat2").set("ambient", "custom");
    model.component("comp1").material("mat2")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.component("comp1").material("mat2").set("noise", true);
    model.component("comp1").material("mat2").set("fresnel", 0.7);
    model.component("comp1").material("mat2").set("roughness", 0.5);
    model.component("comp1").material("mat2").set("diffusewrap", 0);
    model.component("comp1").material("mat2").set("reflectance", 0);
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-12[S/m]", "0", "0", "0", "1e-12[S/m]", "0", "0", "0", "1e-12[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"2.6e-6[1/K]", "0", "0", "0", "2.6e-6[1/K]", "0", "0", "0", "2.6e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "700[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"11.7", "0", "0", "0", "11.7", "0", "0", "0", "11.7"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "2329[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"130[W/(m*K)]", "0", "0", "0", "130[W/(m*K)]", "0", "0", "0", "130[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "170[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"3.48", "0", "0", "0", "3.48", "0", "0", "0", "3.48"});
    model.component("comp1").material("mat2").selection().geom("geom1", 2);
    model.component("comp1").material("mat2").selection().named("sel1");

    model.component("comp1").physics("shell").selection().named("sel1");
    model.component("comp1").physics("shell").feature("to1").set("d", "h_mirror");
    model.component("comp1").physics("shell").create("fix1", "Fixed", 1);
    model.component("comp1").physics("shell").feature("fix1").selection().set(13);
    model.component("comp1").physics("shell").create("sym1", "SymmetrySolid1", 1);
    model.component("comp1").physics("shell").feature("sym1").selection().named("sel3");
    model.component("comp1").physics("shell").create("bl1", "BodyLoad", 2);
    model.component("comp1").physics("shell").feature("bl1").selection().named("sel1");
    model.component("comp1").physics("shell").feature("bl1")
         .set("forceReferenceVolume", new String[]{"0", "0", "x/(0.25[mm])*1e5[N/m^3]"});
    model.component("comp1").physics("ta").selection().set(3);
    model.component("comp1").physics("ta").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("ta").feature("sym1").selection().named("sel2");
    model.component("comp1").physics("acpr").selection().set(1, 2, 4, 5);
    model.component("comp1").physics("acpr").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("acpr").feature("sym1").selection().named("sel2");
    model.component("comp1").physics("acpr").create("swr1", "SphericalWaveRadiation", 2);
    model.component("comp1").physics("acpr").feature("swr1").selection().set(4, 5, 12, 17);

    model.component("comp1").multiphysics().create("atb1", "AcousticThermoacousticBoundary", 2);
    model.component("comp1").multiphysics("atb1").selection().all();
    model.component("comp1").multiphysics().create("tsb1", "ThermoacousticStructureBoundary", 2);
    model.component("comp1").multiphysics("tsb1").selection().named("sel1");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "0.8*dvisc");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmin", "0.1*dvisc");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hgradactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hgrad", 1.4);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hcurveactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hcurve", 0.4);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().set(4, 7, 15, 18);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmax", "0.2*dvisc");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size3", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").selection()
         .set(8, 10, 11, 12, 13, 27, 28, 29, 30);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").set("hmax", "0.5*dvisc");
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "343[m/s]/f0/6");
    model.component("comp1").mesh("mesh1").feature("size").set("hnarrow", 2);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhmin", "0.3*dvisc");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("eig").set("shift", "10000");
    model.study("std1").feature("eig").set("eigwhich", "lr");
    model.study("std1").feature("eig").set("neigsactive", true);
    model.study("std1").feature("eig").set("neigs", 3);
    model.study("std1").setGenPlots(false);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("e1").feature("i1").active(true);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("dset1shellshl", "Shell");
    model.result().dataset("dset1shellshl").set("data", "dset1");
    model.result().dataset("dset1shellshl").setIndex("topconst", "1", 3, 1);
    model.result().dataset("dset1shellshl").setIndex("bottomconst", "-1", 3, 1);
    model.result().dataset("dset1shellshl").setIndex("orientationexpr", "shell.nlX", 0);
    model.result().dataset("dset1shellshl").setIndex("displacementexpr", "arx", 0);
    model.result().dataset("dset1shellshl").setIndex("orientationexpr", "shell.nlY", 1);
    model.result().dataset("dset1shellshl").setIndex("displacementexpr", "ary", 1);
    model.result().dataset("dset1shellshl").setIndex("orientationexpr", "shell.nlZ", 2);
    model.result().dataset("dset1shellshl").setIndex("displacementexpr", "arz", 2);
    model.result().dataset("dset1shellshl").set("distanceexpr", "shell.z_pos");
    model.result().dataset("dset1shellshl").set("seplevels", false);
    model.result().dataset("dset1shellshl").set("resolution", 2);
    model.result().dataset("dset1shellshl").set("areascalefactor", "shell.ASF");
    model.result().dataset("dset1shellshl").set("linescalefactor", "shell.LSF");
    model.result().dataset("dset1shellshl")
         .set("defaultPlotIDs", new String[]{"modeShape|shell", "modalStress|shell", "shellGeometry|shell|surf1"});
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").label("\u632f\u578b (shell)");
    model.result("pg1").set("showlegends", false);
    model.result("pg1").set("data", "dset1shellshl");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"shell.disp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"shell.u", "shell.v", "shell.w"});
    model.result("pg1").label("\u632f\u578b (shell)");
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{2});
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").label("\u6a21\u6001\u5e94\u529b (shell)");
    model.result("pg2").set("showlegends", true);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("data", "dset1shellshl");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"shell.misesGp"});
    model.result("pg2").feature("surf1").set("threshold", "manual");
    model.result("pg2").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").set("descr", "von Mises \u5e94\u529b");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"shell.u", "shell.v", "shell.w"});
    model.result("pg2").label("\u6a21\u6001\u5e94\u529b (shell)");
    model.result("pg2").run();

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/shell", true);
    model.study("std2").feature("freq").setSolveFor("/physics/ta", true);
    model.study("std2").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std2").feature("freq").setSolveFor("/multiphysics/atb1", true);
    model.study("std2").feature("freq").setSolveFor("/multiphysics/tsb1", true);
    model.study("std2").feature("freq").set("plist", "range(f0-deltaf,50,f0+deltaf)");
    model.study("std2").setGenPlots(false);
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("s1").feature("i1").active(true);

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset("mir1").set("quickplane", "xz");
    model.result().dataset("mir1").set("data", "dset2");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u4f4d\u79fb");
    model.result("pg3").set("data", "mir1");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("unit", "\u00b5m");
    model.result("pg3").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg3").feature("surf1").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("\u6e29\u5ea6\u53d8\u5316");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").create("slc1", "Slice");
    model.result("pg4").feature("slc1").set("expr", "ta.T_t");
    model.result("pg4").feature("slc1").set("unit", "mK");
    model.result("pg4").feature("slc1").set("quickplane", "zx");
    model.result("pg4").feature("slc1").set("colortable", "ThermalWave");
    model.result("pg4").feature("slc1").set("colorscalemode", "linearsymmetric");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").label("\u58f0\u538b");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").create("iso1", "Isosurface");
    model.result("pg5").feature("iso1").set("expr", "atb1.p_t");
    model.result("pg5").feature("iso1").set("descr", "\u603b\u58f0\u538b");
    model.result("pg5").feature("iso1").set("number", 20);
    model.result("pg5").feature("iso1").set("colortable", "Wave");
    model.result("pg5").feature("iso1").set("colorscalemode", "linearsymmetric");
    model.result("pg5").run();
    model.result("pg4").run();
    model.result().duplicate("pg6", "pg4");
    model.result("pg6").run();
    model.result("pg6").label("\u58f0\u901f");
    model.result("pg6").run();
    model.result("pg6").feature("slc1").set("expr", "ta.v_inst");
    model.result("pg6").feature("slc1").set("unit", "mm/s");
    model.result("pg6").feature("slc1").set("colortable", "Rainbow");
    model.result("pg6").feature("slc1").set("colorscalemode", "linear");
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u4f4d\u79fb\u54cd\u5e94");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").set("titletype", "label");
    model.result("pg7").create("ptgr1", "PointGraph");
    model.result("pg7").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg7").feature("ptgr1").set("linewidth", "preference");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg7").feature("ptgr1").selection().set(16);
    model.result("pg7").feature("ptgr1").set("expr", "abs(w)");
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u529f\u7387\u54cd\u5e94");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").set("titletype", "label");
    model.result("pg8").set("legendpos", "lowerleft");
    model.result("pg8").create("ptgr1", "PointGraph");
    model.result("pg8").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg8").feature("ptgr1").set("linewidth", "preference");
    model.result("pg8").feature("ptgr1").selection().set(16);
    model.result("pg8").feature("ptgr1")
         .set("expr", "20*log10(abs(w*ta.iomega))-with(12,20*log10(abs(w*ta.iomega)))");
    model.result("pg8").feature("ptgr1").set("legend", true);
    model.result("pg8").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg8").feature("ptgr1").setIndex("legends", "\u6a21\u62df\u54cd\u5e94", 0);
    model.result("pg8").run();
    model.result("pg8").create("glob1", "Global");
    model.result("pg8").feature("glob1").set("markerpos", "datapoints");
    model.result("pg8").feature("glob1").set("linewidth", "preference");
    model.result("pg8").feature("glob1").setIndex("expr", "P_simple", 0);
    model.result("pg8").run();
    model.result("pg8").feature("ptgr1").create("gmrk1", "GraphMarker");
    model.result("pg8").feature("ptgr1").feature("gmrk1").set("linewidth", "preference");
    model.result("pg8").run();
    model.result("pg8").feature("ptgr1").feature("gmrk1").set("displaymode", "widthmode");
    model.result("pg8").feature("ptgr1").feature("gmrk1").set("cutoffmode", "offsetfrompeak");
    model.result("pg8").feature("ptgr1").feature("gmrk1").set("precision", 5);
    model.result("pg8").feature("ptgr1").feature("gmrk1").set("includeunit", true);
    model.result("pg8").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").setIndex("looplevelinput", "first", 0);
    model.result().evaluationGroup("eg1").label("\u8ba1\u7b97\u7ec4 1 - \u7b80\u8c10\u632f\u5b50\u53c2\u6570");
    model.result().evaluationGroup("eg1").set("transpose", true);
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "f_r", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "\u8c10\u632f\u9891\u7387", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "omega_r", 1);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "alpha_r", 2);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "Q_r", 3);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "df_r", 4);
    model.result().evaluationGroup("eg1").run();
    model.result("pg1").run();

    model.study("std1").label("\u7814\u7a76 1 - \u7279\u5f81\u9891\u7387");
    model.study("std2").label("\u7814\u7a76 2 - \u9891\u57df");

    model.result("pg1").run();

    model.title("\u53d7\u9ecf\u6ede\u963b\u5c3c\u548c\u70ed\u963b\u5c3c\u4f5c\u7528\u7684\u632f\u52a8\u5fae\u955c");

    model
         .description("\u67d0\u4e9b MEMS \u5668\u4ef6\u91c7\u7528\u5fae\u955c\u6765\u63a7\u5236\u5149\u5b66\u5143\u4ef6\u3002\u672c\u4f8b\u63cf\u8ff0\u4e00\u4e2a\u88ab\u7a7a\u6c14\u5305\u56f4\u7684\u632f\u52a8\u5fae\u955c\uff0c\u4f7f\u7528\u201c\u70ed\u9ecf\u6027\u58f0-\u58f3\u76f8\u4e92\u4f5c\u7528\u201d\u591a\u7269\u7406\u573a\u63a5\u53e3\u5bf9\u6d41-\u56fa\u8026\u5408\u8fdb\u884c\u5efa\u6a21\uff0c\u4ece\u800c\u5305\u542b\u5fae\u955c\u4e0e\u5468\u56f4\u7a7a\u6c14\u4e4b\u95f4\u7684\u6b63\u786e\u9ecf\u6ede\u963b\u5c3c\u548c\u70ed\u963b\u5c3c\u6548\u5e94\u3002\u5176\u4e2d\u901a\u8fc7\u9891\u7387\u54cd\u5e94\u548c\u7279\u5f81\u9891\u7387\u5206\u6790\uff0c\u5728\u9891\u57df\u4e2d\u7814\u7a76\u5fae\u955c\u5728\u626d\u8f6c\u8f7d\u8377\u4f5c\u7528\u4e0b\u7684\u5171\u632f\u9891\u7387\u3002\u8be5\u7cfb\u7edf\u5305\u542b\u4e00\u4e2a\u7b80\u5355\u7684 RLC \u6a21\u578b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("vibrating_micromirror.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
