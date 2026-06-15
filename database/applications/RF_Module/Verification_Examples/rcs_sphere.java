/*
 * rcs_sphere.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:45 by COMSOL 6.3.0.290. */
public class rcs_sphere {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\RF_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("emw", "ElectromagneticWaves", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").set("solnum", "auto");
    model.study("std1").feature("freq").set("notsolnum", "auto");
    model.study("std1").feature("freq").set("outputmap", new String[]{});
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").setSolveFor("/physics/emw", true);

    model.param().set("r_lda", "0.5");
    model.param().descr("r_lda", "\u4ee5\u6ce2\u957f\u8868\u793a\u7684\u7403\u4f53\u534a\u5f84");
    model.param().set("r0", "5[cm]");
    model.param().descr("r0", "\u7403\u4f53\u534a\u5f84");
    model.param().set("lda", "r0/r_lda");
    model.param().descr("lda", "\u6ce2\u957f");
    model.param().set("k0", "2*pi/lda");
    model.param().descr("k0", "\u6ce2\u6570");
    model.param().set("f0", "c_const/lda");
    model.param().descr("f0", "\u9891\u7387");
    model.param().set("t_air", "lda/2");
    model.param().descr("t_air", "\u7403\u4f53\u5468\u56f4\u7a7a\u6c14\u7684\u539a\u5ea6");
    model.param().set("t_pml", "lda/2");
    model.param().descr("t_pml", "PML \u7684\u539a\u5ea6");
    model.param().set("h_size", "8");
    model.param().descr("h_size", "\u6bcf\u4e2a\u6ce2\u957f\u7684\u5355\u5143\u6570");
    model.param().set("E0", "1[V/m]");
    model.param().descr("E0", "\u5165\u5c04\u573a\u5e45\u503c");
    model.param().set("rho", "2*pi*r_lda");
    model.param().descr("rho", "\u4ee5\u6ce2\u957f\u8868\u793a\u7684\u7403\u4f53\u5468\u957f");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").label("\u7b2c\u4e00\u7c7b\u7403\u8d1d\u585e\u5c14\u51fd\u6570");
    model.component("comp1").variable("var1").set("sbesselj1", "sqrt(pi/(2*rho))*besselj(1.5,rho)");
    model.component("comp1").variable("var1").descr("sbesselj1", "\u4e00\u9636");
    model.component("comp1").variable("var1").set("sbesselj2", "sqrt(pi/(2*rho))*besselj(2.5,rho)");
    model.component("comp1").variable("var1").descr("sbesselj2", "\u4e8c\u9636");
    model.component("comp1").variable("var1").set("sbesselj3", "sqrt(pi/(2*rho))*besselj(3.5,rho)");
    model.component("comp1").variable("var1").descr("sbesselj3", "\u4e09\u9636");
    model.component("comp1").variable("var1").set("sbesselj4", "sqrt(pi/(2*rho))*besselj(4.5,rho)");
    model.component("comp1").variable("var1").descr("sbesselj4", "\u56db\u9636");
    model.component("comp1").variable("var1").set("sbesselj5", "sqrt(pi/(2*rho))*besselj(5.5,rho)");
    model.component("comp1").variable("var1").descr("sbesselj5", "\u4e94\u9636");
    model.component("comp1").variable("var1").set("sbesselj6", "sqrt(pi/(2*rho))*besselj(6.5,rho)");
    model.component("comp1").variable("var1").descr("sbesselj6", "\u516d\u9636");
    model.component("comp1").variable("var1").set("sbesselj7", "sqrt(pi/(2*rho))*besselj(7.5,rho)");
    model.component("comp1").variable("var1").descr("sbesselj7", "\u4e03\u9636");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").label("\u7b2c\u4e8c\u7c7b\u7403\u8d1d\u585e\u5c14\u51fd\u6570");
    model.component("comp1").variable("var2").set("sbessely1", "sqrt(pi/(2*rho))*bessely(1.5,rho)");
    model.component("comp1").variable("var2").descr("sbessely1", "\u4e00\u9636");
    model.component("comp1").variable("var2").set("sbessely2", "sqrt(pi/(2*rho))*bessely(2.5,rho)");
    model.component("comp1").variable("var2").descr("sbessely2", "\u4e8c\u9636");
    model.component("comp1").variable("var2").set("sbessely3", "sqrt(pi/(2*rho))*bessely(3.5,rho)");
    model.component("comp1").variable("var2").descr("sbessely3", "\u4e09\u9636");
    model.component("comp1").variable("var2").set("sbessely4", "sqrt(pi/(2*rho))*bessely(4.5,rho)");
    model.component("comp1").variable("var2").descr("sbessely4", "\u56db\u9636");
    model.component("comp1").variable("var2").set("sbessely5", "sqrt(pi/(2*rho))*bessely(5.5,rho)");
    model.component("comp1").variable("var2").descr("sbessely5", "\u4e94\u9636");
    model.component("comp1").variable("var2").set("sbessely6", "sqrt(pi/(2*rho))*bessely(6.5,rho)");
    model.component("comp1").variable("var2").descr("sbessely6", "\u516d\u9636");
    model.component("comp1").variable("var2").set("sbessely7", "sqrt(pi/(2*rho))*bessely(7.5,rho)");
    model.component("comp1").variable("var2").descr("sbessely7", "\u4e03\u9636");
    model.component("comp1").variable().create("var3");
    model.component("comp1").variable("var3").label("\u7b2c\u4e8c\u7c7b\u7403\u6c49\u514b\u5c14\u51fd\u6570");
    model.component("comp1").variable("var3").set("shankel1", "sbesselj1+1j*sbessely1");
    model.component("comp1").variable("var3").descr("shankel1", "\u4e00\u9636");
    model.component("comp1").variable("var3").set("shankel2", "sbesselj2+1j*sbessely2");
    model.component("comp1").variable("var3").descr("shankel2", "\u4e8c\u9636");
    model.component("comp1").variable("var3").set("shankel3", "sbesselj3+1j*sbessely3");
    model.component("comp1").variable("var3").descr("shankel3", "\u4e09\u9636");
    model.component("comp1").variable("var3").set("shankel4", "sbesselj4+1j*sbessely4");
    model.component("comp1").variable("var3").descr("shankel4", "\u56db\u9636");
    model.component("comp1").variable("var3").set("shankel5", "sbesselj5+1j*sbessely5");
    model.component("comp1").variable("var3").descr("shankel5", "\u4e94\u9636");
    model.component("comp1").variable("var3").set("shankel6", "sbesselj6+1j*sbessely6");
    model.component("comp1").variable("var3").descr("shankel6", "\u516d\u9636");
    model.component("comp1").variable("var3").set("shankel7", "sbesselj7+1j*sbessely7");
    model.component("comp1").variable("var3").descr("shankel7", "\u4e03\u9636");
    model.component("comp1").variable().create("var4");
    model.component("comp1").variable("var4").label("\u7cfb\u6570 a");
    model.component("comp1").variable("var4").set("a1", "sbesselj1/shankel1");
    model.component("comp1").variable("var4").set("a2", "sbesselj2/shankel2");
    model.component("comp1").variable("var4").set("a3", "sbesselj3/shankel3");
    model.component("comp1").variable("var4").set("a4", "sbesselj4/shankel4");
    model.component("comp1").variable("var4").set("a5", "sbesselj5/shankel5");
    model.component("comp1").variable("var4").set("a6", "sbesselj6/shankel6");
    model.component("comp1").variable("var4").set("a7", "sbesselj7/shankel7");
    model.component("comp1").variable().create("var5");
    model.component("comp1").variable("var5").label("\u7cfb\u6570 b");
    model.component("comp1").variable("var5").set("b1", "-d(rho*sbesselj1,rho)/d(rho*shankel1,rho)");
    model.component("comp1").variable("var5").set("b2", "-d(rho*sbesselj2,rho)/d(rho*shankel2,rho)");
    model.component("comp1").variable("var5").set("b3", "-d(rho*sbesselj3,rho)/d(rho*shankel3,rho)");
    model.component("comp1").variable("var5").set("b4", "-d(rho*sbesselj4,rho)/d(rho*shankel4,rho)");
    model.component("comp1").variable("var5").set("b5", "-d(rho*sbesselj5,rho)/d(rho*shankel5,rho)");
    model.component("comp1").variable("var5").set("b6", "-d(rho*sbesselj6,rho)/d(rho*shankel6,rho)");
    model.component("comp1").variable("var5").set("b7", "-d(rho*sbesselj7,rho)/d(rho*shankel7,rho)");
    model.component("comp1").variable().create("var6");
    model.component("comp1").variable("var6").label("RCS \u7684\u89e3\u6790\u8868\u8fbe\u5f0f");
    model.component("comp1").variable("var6")
         .set("RCS_analytical", "1/(rho)^2*abs(-3*(a1+b1)+5*(a2+b2)-7*(a3+b3)+9*(a4+b4)-11*(a5+b5)+13*(a6+b6)-15*(a7+b7))^2");
    model.component("comp1").variable("var6").descr("RCS_analytical", "\u89e3\u6790\u8ba1\u7b97\u7684 RCS");

    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").set("r", "r0+t_air+t_pml");
    model.component("comp1").geom("geom1").feature("sph1").setIndex("layer", "t_pml", 0);
    model.component("comp1").geom("geom1").feature("sph1").setIndex("layer", "t_air", 1);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view().create("view2", "geom1");
    model.component("comp1").view("view2").set("renderwireframe", true);

    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input")
         .set("sph1", 1, 2, 3, 5, 6, 7, 9, 10, 11, 12, 13, 14, 15);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("emw").prop("BackgroundField").set("SolveFor", "scatteredField");
    model.component("comp1").physics("emw").prop("BackgroundField")
         .set("Eb", new String[]{"0", "0", "E0*exp(-j*k0*x)"});

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().set(1, 4);
    model.component("comp1").coordSystem("pml1").set("ScalingType", "Spherical");

    model.component("comp1").physics("emw").create("symp1", "SymmetryPlane", 2);
    model.component("comp1").physics("emw").feature("symp1").selection().set(2, 5, 15, 16);
    model.component("comp1").physics("emw").feature("symp1").set("Symmetry_type", "pec");
    model.component("comp1").physics("emw").create("symp2", "SymmetryPlane", 2);
    model.component("comp1").physics("emw").feature("symp2").selection().set(1, 4, 9, 12);
    model.component("comp1").physics("emw").create("ffd1", "FarFieldDomain", 3);
    model.component("comp1").physics("emw").feature("ffd1").feature("ffc1").set("SymmetryInPlane1", true);
    model.component("comp1").physics("emw").feature("ffd1").feature("ffc1").set("SymmetryInPlane2", true);
    model.component("comp1").physics("emw").feature("ffd1").feature("ffc1").set("SymmetryType2", "SymmetryInHz");

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

    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().set(2, 3);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "lda/h_size");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "lda/h_size");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "r_lda", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "r_lda", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(0.1,0.025,0.8)", 0);
    model.study("std1").feature("freq").set("plist", "f0");
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().numerical().create("gevs1", "EvalGlobalSweep");
    model.result().numerical("gevs1").setIndex("pname", "r_lda", 0);
    model.result().numerical("gevs1").setIndex("plistarr", "range(0.1,0.005,0.8)", 0);
    model.result().numerical("gevs1").setIndex("expr", "RCS_analytical", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97\u626b\u63cf 1");
    model.result().numerical("gevs1").set("table", "tbl1");
    model.result().numerical("gevs1").setResult();
    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").set("data", "dset2");
    model.result("pg1").set("titletype", "custom");
    model.result("pg1").set("unitintitle", false);
    model.result("pg1").set("descriptionintitle", false);
    model.result("pg1").set("typeintitle", false);
    model.result("pg1").set("prefixintitle", "RCS \u8ba1\u7b97");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1")
         .set("xlabel", "\u4ee5\u6ce2\u957f\u8868\u793a\u7684\u7403\u4f53\u534a\u5f84 (a/\\lambda<sub>0</sub>)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1")
         .set("ylabel", "\u5f52\u4e00\u5316\u5355\u57fa\u5730\u96f7\u8fbe\u6563\u5c04\u622a\u9762 (\\sigma<sub>3-D</sub>/\\pi a<sup>2</sup>)");
    model.result("pg1").set("ylog", true);
    model.result("pg1").create("ptgr1", "PointGraph");
    model.result("pg1").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg1").feature("ptgr1").set("linewidth", "preference");
    model.result("pg1").feature("ptgr1").selection().set(2);
    model.result("pg1").feature("ptgr1").set("expr", "emw.bRCS3D/(pi*r0^2)");
    model.result("pg1").feature("ptgr1").set("xdatasolnumtype", "outer");
    model.result("pg1").feature("ptgr1").set("linestyle", "none");
    model.result("pg1").feature("ptgr1").set("linemarker", "square");
    model.result("pg1").feature("ptgr1").set("legend", true);
    model.result("pg1").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg1").feature("ptgr1").setIndex("legends", "\u6a21\u62df\u7ed3\u679c", 0);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("tblp1", "Table");
    model.result("pg1").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg1").feature("tblp1").set("linewidth", "preference");
    model.result("pg1").feature("tblp1").set("legend", true);
    model.result("pg1").feature("tblp1").set("legendmethod", "manual");
    model.result("pg1").feature("tblp1").setIndex("legends", "\u89e3\u6790\u7ed3\u679c", 0);
    model.result("pg1").run();

    model.param().set("r_lda", "r1");
    model.param().set("r1", "0.16363636363636364");
    model.param().descr("r1", "\u7b2c\u4e00\u6b21\u5171\u632f\u65f6\u7684\u76f8\u5bf9\u534a\u5f84");
    model.param().set("RCS1", "3.6549540474068576");
    model.param().descr("RCS1", "\u7b2c\u4e00\u6b21\u5171\u632f\u65f6\u7684 RCS");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").mesh().duplicate("mesh2", "mesh1");
    model.component("comp1").mesh("mesh2").feature("size").set("hmin", "if(lda/h_size>r0/2,r0/2,lda/h_size)");
    model.component("comp1").mesh("mesh2").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh2").feature("ftet1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh2").feature("ftet1").feature("size1").selection().set(7, 10);
    model.component("comp1").mesh("mesh2").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh2").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh2").feature("ftet1").feature("size1")
         .set("hmax", "if(lda/h_size>r0/2,r0/2,lda/h_size)");

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").set("plotgroup", "Default");
    model.study("std2").feature("freq").set("solnum", "auto");
    model.study("std2").feature("freq").set("notsolnum", "auto");
    model.study("std2").feature("freq").set("outputmap", new String[]{});
    model.study("std2").feature("freq").set("ngenAUX", "1");
    model.study("std2").feature("freq").set("goalngenAUX", "1");
    model.study("std2").feature("freq").set("ngenAUX", "1");
    model.study("std2").feature("freq").set("goalngenAUX", "1");
    model.study("std2").feature("freq").setSolveFor("/physics/emw", true);
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "r_lda", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "", 0);
    model.study("std2").feature("param").setIndex("pname", "r_lda", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "", 0);
    model.study("std2").feature("param").setIndex("pname", "h_size", 0);
    model.study("std2").feature("param").setIndex("plistarr", "3 6 9 12 15 20", 0);
    model.study("std2").feature("freq").set("plist", "f0");
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol33");
    model.sol("sol33").study("std2");
    model.sol("sol33").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("p2").feature("so1").set("psol", "sol33");
    model.batch("p2").run("compute");

    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").set("data", "dset4");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u5355\u5143\u6570/\\lambda<sub>0</sub>");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u76f8\u5bf9\u8bef\u5dee");
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr1").set("linewidth", "preference");
    model.result("pg2").feature("ptgr1").selection().set(2);
    model.result("pg2").feature("ptgr1").set("expr", "(emw.bRCS3D/(pi*r0^2)-RCS1)/RCS1");
    model.result("pg2").feature("ptgr1").set("xdatasolnumtype", "level2");
    model.result("pg2").feature("ptgr1").set("xdata", "expr");
    model.result("pg2").feature("ptgr1").set("xdataexpr", "h_size");
    model.result("pg2").feature("ptgr1").set("linewidth", 2);
    model.result("pg2").feature("ptgr1").set("linemarker", "square");
    model.result("pg2").run();

    model.title("\u8ba1\u7b97\u7406\u60f3\u5bfc\u4f53\u7403\u7684\u96f7\u8fbe\u6563\u5c04\u622a\u9762");

    model
         .description("\u8fd9\u662f\u4e00\u4e2a\u6709\u5173\u8ba1\u7b97\u7535\u78c1\u5b66\u7684\u7ecf\u5178\u57fa\u51c6\u95ee\u9898\uff0c\u8ba1\u7b97\u81ea\u7531\u7a7a\u95f4\u4e2d\u7684\u7403\u5728\u5e73\u9762\u6ce2\u7167\u5c04\u4e0b\u7684\u96f7\u8fbe\u6563\u5c04\u622a\u9762 (RCS)\u3002\u672c\u4f8b\u6c42\u89e3\u9ad8\u5bfc\u7535\u6027\u91d1\u5c5e\u7403\u7684 RCS\uff0c\u5b83\u5904\u7406\u6210\u5177\u6709\u65e0\u9650\u5927\u7535\u5bfc\u7387\u7684\u6750\u6599\u3002\u4eff\u771f\u7ed3\u679c\u4e0e\u89e3\u6790\u89e3\u8fdb\u884c\u4e86\u6bd4\u8f83\uff0c\u5448\u73b0\u9ad8\u5ea6\u7684\u4e00\u81f4\u6027\u3002");

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

  public static Model run2(Model model) {
    model.sol("sol21").clearSolutionData();
    model.sol("sol22").clearSolutionData();
    model.sol("sol23").clearSolutionData();
    model.sol("sol24").clearSolutionData();
    model.sol("sol25").clearSolutionData();
    model.sol("sol26").clearSolutionData();
    model.sol("sol27").clearSolutionData();
    model.sol("sol28").clearSolutionData();
    model.sol("sol29").clearSolutionData();
    model.sol("sol30").clearSolutionData();
    model.sol("sol31").clearSolutionData();
    model.sol("sol32").clearSolutionData();
    model.sol("sol33").clearSolutionData();
    model.sol("sol34").clearSolutionData();
    model.sol("sol35").clearSolutionData();
    model.sol("sol36").clearSolutionData();
    model.sol("sol37").clearSolutionData();
    model.sol("sol38").clearSolutionData();
    model.sol("sol39").clearSolutionData();

    model.label("rcs_sphere.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
