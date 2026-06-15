/*
 * lumped_loudspeaker_driver_mechanical.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:39 by COMSOL 6.3.0.290. */
public class lumped_loudspeaker_driver_mechanical {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Electroacoustic_Transducers");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");
    model.component("comp1").physics().create("cir", "Circuit", "geom1");
    model.component("comp1").physics().create("lms", "LumpedMechanicalSystem", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std1").feature("freq").setSolveFor("/physics/cir", true);
    model.study("std1").feature("freq").setSolveFor("/physics/lms", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("fmax", "10[kHz]", "\u6700\u5927\u7814\u7a76\u9891\u7387");
    model.param().set("c0", "343[m/s]", "\u7a7a\u6c14\u4e2d\u7684\u58f0\u901f");
    model.param().set("rho0", "1.2[kg/m^3]", "\u7a7a\u6c14\u5bc6\u5ea6");
    model.param().set("M_MD", "33.4[g]", "\u52a8\u8d28\u91cf\uff08\u97f3\u5708\u548c\u632f\u819c\uff09");
    model.param().set("C_MS", "1.18e-3[m/N]", "\u60ac\u67b6\u67d4\u6027");
    model.param().set("R_MS", "1.85[N*s/m]", "\u60ac\u67b6\u673a\u68b0\u635f\u8017");
    model.param()
         .set("BL", "11.4[T*m]", "\u529b\u56e0\u5b50\uff0c\u78c1\u901a\u5bc6\u5ea6 (B) \u4e58\u4ee5\u97f3\u5708\u957f\u5ea6 (L)");
    model.param().set("R_E", "7[ohm]", "\u97f3\u5708\u7535\u963b");
    model.param().set("L_e", "6.89[mH]", "\u97f3\u5708\u7535\u611f\uff08\u6052\u5b9a\uff09");
    model.param().set("n_e", "0.7", "\u97f3\u5708\u635f\u8017\u56e0\u5b50");
    model.param().set("V0", "sqrt(2)[V]", "\u9a71\u52a8\u7535\u538b\uff08\u5cf0\u503c\uff09");
    model.param().set("V0rms", "V0/sqrt(2)", "\u9a71\u52a8\u7535\u538b\uff08\u5747\u65b9\u6839\uff09");
    model.param().set("R_g", "0[ohm]", "\u9a71\u52a8\u8f93\u51fa\u7535\u963b");
    model.param().set("a", "12[cm]", "\u9a71\u52a8\u5668\u7684\u6d3b\u585e\u534a\u5f84\uff08\u7b49\u6548\uff09");
    model.param().set("Rair", "20[cm]", "\u7a7a\u6c14\u57df\u534a\u5f84");
    model.param().set("Rpml", "6[cm]", "PML \u5c42\u539a\u5ea6");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1")
         .set("filename", "lumped_loudspeaker_driver_mechanical.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("\u6a21\u578b\u53d8\u91cf");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("L_E", "(L_e/(sin(n_e*pi/2)))*(acpr.omega[s/rad])^(n_e-1)", "\u97f3\u5708\u7535\u611f\uff08\u9891\u7387\u76f8\u5173\uff09");
    model.component("comp1").variable("var1")
         .set("Rp_E", "(L_e/(cos(n_e*pi/2)))*(acpr.omega[s/rad])^(n_e)[ohm/H]", "\u7535\u963b\uff08\u78c1\u7cfb\u7edf\u4e2d\u7684\u635f\u8017\uff09");
    model.component("comp1").variable("var1").set("F_D", "intop((down(p)-up(p))*nz)", "\u8f74\u5411\u538b\u529b");
    model.component("comp1").variable("var1")
         .set("P_AR", "intop(up(acpr.Ir)*nr+up(acpr.Iz)*nz)", "\u8f90\u5c04\u529f\u7387");
    model.component("comp1").variable("var1")
         .set("P_E", "0.5*realdot(V0,cir.R1.i)", "\u7535\u8f93\u5165\u529f\u7387\uff08\u5747\u65b9\u6839\uff09");
    model.component("comp1").variable("var1").set("eta", "P_AR/P_E", "\u9a71\u52a8\u5668\u6548\u7387");
    model.component("comp1").variable("var1").set("k0", "acpr.omega/c0", "\u81ea\u7531\u7a7a\u95f4\u6ce2\u6570");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u626c\u58f0\u5668");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(6, 11, 14);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u5185\u58c1");
    model.component("comp1").selection("sel2").geom(1);
    model.component("comp1").selection("sel2").set(7, 8, 15);

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().named("sel1");
    model.component("comp1").cpl("intop1").set("opname", "intop");

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().set(1, 4);
    model.component("comp1").coordSystem("pml1").set("stretchingType", "rational");
    model.component("comp1").coordSystem("pml1").set("PMLfactor", "0.5");
    model.component("comp1").coordSystem("pml1").set("PMLgamma", "5");

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

    model.component("comp1").physics("acpr").create("ishb1", "InteriorSoundHard", 1);
    model.component("comp1").physics("acpr").feature("ishb1").selection().named("sel2");
    model.component("comp1").physics("acpr").create("ina1", "InteriorNormalAcceleration", 1);
    model.component("comp1").physics("acpr").feature("ina1").selection().named("sel1");
    model.component("comp1").physics("acpr").feature("ina1").set("acc", new String[]{"0", "0", "lms.M1.a"});
    model.component("comp1").physics("acpr").create("efc1", "ExteriorFieldCalculation", 1);
    model.component("comp1").physics("acpr").feature("efc1").selection().set(12);
    model.component("comp1").physics("acpr").feature("efc1").setIndex("SymmetryCondition2", 1, 0);
    model.component("comp1").physics("cir").create("V1", "VoltageSource", -1);
    model.component("comp1").physics("cir").feature("V1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("V1").set("value", "V0");
    model.component("comp1").physics("cir").create("R1", "Resistor", -1);
    model.component("comp1").physics("cir").feature("R1").setIndex("Connections", 1, 0, 0);
    model.component("comp1").physics("cir").feature("R1").setIndex("Connections", 2, 1, 0);
    model.component("comp1").physics("cir").feature("R1").set("R", "R_g");
    model.component("comp1").physics("cir").create("R2", "Resistor", -1);
    model.component("comp1").physics("cir").feature("R2").setIndex("Connections", 2, 0, 0);
    model.component("comp1").physics("cir").feature("R2").setIndex("Connections", 3, 1, 0);
    model.component("comp1").physics("cir").feature("R2").set("R", "R_E");
    model.component("comp1").physics("cir").create("L1", "Inductor", -1);
    model.component("comp1").physics("cir").feature("L1").setIndex("Connections", 3, 0, 0);
    model.component("comp1").physics("cir").feature("L1").setIndex("Connections", 4, 1, 0);
    model.component("comp1").physics("cir").feature("L1").set("L", "L_E");
    model.component("comp1").physics("cir").create("R3", "Resistor", -1);
    model.component("comp1").physics("cir").feature("R3").setIndex("Connections", 3, 0, 0);
    model.component("comp1").physics("cir").feature("R3").setIndex("Connections", 4, 1, 0);
    model.component("comp1").physics("cir").feature("R3").set("R", "Rp_E");
    model.component("comp1").physics("cir").create("V2", "VoltageSource", -1);
    model.component("comp1").physics("cir").feature("V2").setIndex("Connections", 4, 0, 0);
    model.component("comp1").physics("cir").feature("V2").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("V2").set("value", "BL*lms.M1.v");
    model.component("comp1").physics("lms").create("K1", "Spring", -1);
    model.component("comp1").physics("lms").feature("K1").setIndex("Connections", 0, 0, 0);
    model.component("comp1").physics("lms").feature("K1").set("k", "1/C_MS");
    model.component("comp1").physics("lms").feature("K1").set("includeDisplacement", false);
    model.component("comp1").physics("lms").create("C1", "Damper", -1);
    model.component("comp1").physics("lms").feature("C1").setIndex("Connections", 0, 0, 0);
    model.component("comp1").physics("lms").feature("C1").setIndex("Connections", 1, 1, 0);
    model.component("comp1").physics("lms").feature("C1").set("c", "R_MS");
    model.component("comp1").physics("lms").feature("C1").set("includeDisplacement", false);
    model.component("comp1").physics("lms").create("M1", "Mass", -1);
    model.component("comp1").physics("lms").feature("M1").setIndex("Connections", 1, 0, 0);
    model.component("comp1").physics("lms").feature("M1").setIndex("Connections", 2, 1, 0);
    model.component("comp1").physics("lms").feature("M1").set("m", "M_MD");
    model.component("comp1").physics("lms").feature("M1").set("includeDisplacement", false);
    model.component("comp1").physics("lms").feature("M1").set("includeVelocity", true);
    model.component("comp1").physics("lms").create("frc1", "ForceNode", -1);
    model.component("comp1").physics("lms").feature("frc1").setIndex("Connections", 2, 0, 0);
    model.component("comp1").physics("lms").feature("frc1").set("fp1", "BL*cir.R2.i+F_D");

    model.component("comp1").mesh("mesh1").contribute("physics/cir", false);
    model.component("comp1").mesh("mesh1").contribute("physics/lms", false);

    model.component("comp1").physics("acpr").prop("MeshControl").set("ElementsPerWavelength", "UserDefined");
    model.component("comp1").physics("acpr").prop("MeshControl").set("nperlambda", 8);

    model.study("std1").feature("freq")
         .set("plist", "{10, 10.3, 10.6, 10.9, 11.2, 11.5, 11.8, 12.2, 12.5, 12.8, 13.2, 13.6, 14, 14.5, 15, 15.5, 16, 16.5, 17, 17.5, 18, 18.5, 19, 19.5, 20, 20.6, 21.2, 21.8, 22.4, 23, 23.6, 24.3, 25, 25.8, 26.5, 27.2, 28, 29, 30, 30.7, 31.5, 32.5, 33.5, 34.5, 35.5, 36.5, 37.5, 38.7, 40, 41.2, 42.5, 43.7, 45, 46.2, 47.5, 48.7, 50, 51.5, 53, 54.5, 56, 58, 60, 61.5, 63, 65, 67, 69, 71, 73, 75, 77.5, 80, 82.5, 85, 87.5, 90, 92.5, 95, 97.5, 100, 103, 106, 109, 112, 115, 118, 122, 125, 128, 132, 136, 140, 145, 150, 155, 160, 165, 170, 175, 180, 185, 190, 195, 200, 206, 212, 218, 224, 230, 236, 243, 250, 258, 265, 272, 280, 290, 300, 307, 315, 325, 335, 345, 355, 365, 375, 387, 400, 412, 425, 437, 450, 462, 475, 487, 500, 515, 530, 545, 560, 580, 600, 615, 630, 650, 670, 690, 710, 730, 750, 775, 800, 825, 850, 875, 900, 925, 950, 975, 1e3, 1.03e3, 1.06e3, 1.09e3, 1.12e3, 1.15e3, 1.18e3, 1.22e3, 1.25e3, 1.28e3, 1.32e3, 1.36e3, 1.4e3, 1.45e3, 1.5e3, 1.55e3, 1.6e3, 1.65e3, 1.7e3, 1.75e3, 1.8e3, 1.85e3, 1.9e3, 1.95e3, 2e3, 2.06e3, 2.12e3, 2.18e3, 2.24e3, 2.3e3, 2.36e3, 2.43e3, 2.5e3, 2.58e3, 2.65e3, 2.72e3, 2.8e3, 2.9e3, 3e3, 3.07e3, 3.15e3, 3.25e3, 3.35e3, 3.45e3, 3.55e3, 3.65e3, 3.75e3, 3.87e3, 4e3, 4.12e3, 4.25e3, 4.37e3, 4.5e3, 4.62e3, 4.75e3, 4.87e3, 5e3, 5.15e3, 5.3e3, 5.45e3, 5.6e3, 5.8e3, 6e3, 6.15e3, 6.3e3, 6.5e3, 6.7e3, 6.9e3, 7.1e3, 7.3e3, 7.5e3, 7.75e3, 8e3, 8.25e3, 8.5e3, 8.75e3, 9e3, 9.25e3, 9.5e3, 9.75e3, 1e4}");

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 241, 0);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").label("\u58f0\u538b (acpr)");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 241, 0);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").label("\u58f0\u538b\u7ea7 (acpr)");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "dset1");
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("hasspacevars", false);
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "rev1");
    model.result("pg3").setIndex("looplevel", 241, 0);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg3").feature("surf1").set("colortable", "Wave");
    model.result("pg3").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").label("\u58f0\u538b, 3D (acpr)");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "rev1");
    model.result("pg4").setIndex("looplevel", 241, 0);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").feature("surf1").set("colorscalemode", "linear");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").label("\u58f0\u538b\u7ea7, 3D (acpr)");
    model.result().create("pg5", "PolarGroup");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").create("rp1", "RadiationPattern");
    model.result("pg5").feature("rp1").set("expr", new String[]{"acpr.efc1.Lp_pext"});
    model.result("pg5").feature("rp1").set("legend", true);
    model.result("pg5").feature("rp1").set("phidisc", 180);
    model.result("pg5").label("\u5916\u573a\u58f0\u538b\u7ea7 (acpr)");
    model.result("pg5").setIndex("looplevelinput", "last", 0);
    model.result().create("pg6", "PolarGroup");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").create("rp1", "RadiationPattern");
    model.result("pg6").feature("rp1").set("expr", new String[]{"acpr.efc1.pext"});
    model.result("pg6").feature("rp1").set("legend", true);
    model.result("pg6").feature("rp1").set("phidisc", 180);
    model.result("pg6").label("\u5916\u573a\u538b\u529b (acpr)");
    model.result("pg6").setIndex("looplevelinput", "last", 0);
    model.result("pg5").set("symmetricangle", true);
    model.result("pg5").set("zeroangle", "up");
    model.result("pg5").set("rotdir", "cw");
    model.result("pg6").set("symmetricangle", true);
    model.result("pg6").set("zeroangle", "up");
    model.result("pg6").set("rotdir", "cw");
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").label("\u529b\u5927\u5c0f (K1)");
    model.result("pg7").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg7").set("titletype", "manual");
    model.result("pg7").set("title", "Force");
    model.result("pg7").set("ylabel", "Force");
    model.result("pg7").feature().create("glob1", "Global");
    model.result("pg7").feature("glob1").set("expr", new String[]{"lms.K1.fAmp"});
    model.result("pg7").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg7").feature("glob1").set("showunitcombo", "off");
    model.result("pg7").feature("glob1").set("linemarker", "cycle");
    model.result("pg7").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg7").feature("glob1").set("data", "parent");
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").label("\u529b\u76f8\u4f4d (K1)");
    model.result("pg8").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg8").set("titletype", "manual");
    model.result("pg8").set("title", "Phase");
    model.result("pg8").set("ylabel", "Phase");
    model.result("pg8").feature().create("glob1", "Global");
    model.result("pg8").feature("glob1").set("expr", new String[]{"lms.K1.fPhase"});
    model.result("pg8").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg8").feature("glob1").set("showunitcombo", "off");
    model.result("pg8").feature("glob1").set("linemarker", "cycle");
    model.result("pg8").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg8").feature("glob1").set("data", "parent");
    model.result("pg7").label("\u529b\u5927\u5c0f (lms)");
    model.result("pg7").feature("glob1").set("expr", new String[]{});
    model.result("pg7").feature("glob1").set("descr", new String[]{});
    model.result("pg7").feature("glob1").set("expr", new String[]{"lms.C1.fAmp"});
    model.result("pg7").feature("glob1")
         .set("descr", new String[]{"\u5f39\u7c27\u529b\u5927\u5c0f (K1)", "\u963b\u5c3c\u529b\u5927\u5c0f (C1)"});
    model.result("pg7").feature("glob1").set("expr", new String[]{"lms.K1.fAmp", "lms.C1.fAmp"});
    model.result("pg8").label("\u529b\u76f8\u4f4d (lms)");
    model.result("pg8").feature("glob1").set("expr", new String[]{});
    model.result("pg8").feature("glob1").set("descr", new String[]{});
    model.result("pg8").feature("glob1").set("expr", new String[]{"lms.C1.fPhase"});
    model.result("pg8").feature("glob1")
         .set("descr", new String[]{"\u5f39\u7c27\u529b\u76f8\u4f4d (K1)", "\u963b\u5c3c\u529b\u76f8\u4f4d (C1)"});
    model.result("pg8").feature("glob1").set("expr", new String[]{"lms.K1.fPhase", "lms.C1.fPhase"});
    model.result("pg7").label("\u529b\u5927\u5c0f (lms) 1");
    model.result("pg7").feature("glob1").set("expr", new String[]{});
    model.result("pg7").feature("glob1").set("descr", new String[]{});
    model.result("pg7").feature("glob1").set("expr", new String[]{"lms.M1.fAmp"});
    model.result("pg7").feature("glob1")
         .set("descr", new String[]{"\u5f39\u7c27\u529b\u5927\u5c0f (K1)", "\u963b\u5c3c\u529b\u5927\u5c0f (C1)", "\u60ef\u6027\u529b\u5927\u5c0f (M1)"});
    model.result("pg7").feature("glob1").set("expr", new String[]{"lms.K1.fAmp", "lms.C1.fAmp", "lms.M1.fAmp"});
    model.result("pg8").label("\u529b\u76f8\u4f4d (lms) 1");
    model.result("pg8").feature("glob1").set("expr", new String[]{});
    model.result("pg8").feature("glob1").set("descr", new String[]{});
    model.result("pg8").feature("glob1").set("expr", new String[]{"lms.M1.fPhase"});
    model.result("pg8").feature("glob1")
         .set("descr", new String[]{"\u5f39\u7c27\u529b\u76f8\u4f4d (K1)", "\u963b\u5c3c\u529b\u76f8\u4f4d (C1)", "\u60ef\u6027\u529b\u76f8\u4f4d (M1)"});
    model.result("pg8").feature("glob1")
         .set("expr", new String[]{"lms.K1.fPhase", "lms.C1.fPhase", "lms.M1.fPhase"});
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").label("\u901f\u5ea6\u5e45\u503c (M1)");
    model.result("pg9").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg9").set("titletype", "manual");
    model.result("pg9").set("title", "Velocity");
    model.result("pg9").set("ylabel", "Velocity");
    model.result("pg9").feature().create("glob1", "Global");
    model.result("pg9").feature("glob1").set("expr", new String[]{"lms.M1.vAmp"});
    model.result("pg9").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg9").feature("glob1").set("showunitcombo", "off");
    model.result("pg9").feature("glob1").set("linemarker", "cycle");
    model.result("pg9").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg9").feature("glob1").set("data", "parent");
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").label("\u901f\u5ea6\u76f8\u4f4d (M1)");
    model.result("pg10").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg10").set("titletype", "manual");
    model.result("pg10").set("title", "Phase");
    model.result("pg10").set("ylabel", "Phase");
    model.result("pg10").feature().create("glob1", "Global");
    model.result("pg10").feature("glob1").set("expr", new String[]{"lms.M1.vPhase"});
    model.result("pg10").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg10").feature("glob1").set("showunitcombo", "off");
    model.result("pg10").feature("glob1").set("linemarker", "cycle");
    model.result("pg10").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg10").feature("glob1").set("data", "parent");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 161, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").run();
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().set(2, 3);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 161, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 217, 0);
    model.result("pg1").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("rp1").set("anglerestr", "manual");
    model.result("pg5").feature("rp1").set("phimin", -90);
    model.result("pg5").feature("rp1").set("phirange", 180);
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("rp1").set("anglerestr", "manual");
    model.result("pg6").feature("rp1").set("phimin", -90);
    model.result("pg6").feature("rp1").set("phirange", 180);
    model.result("pg6").run();
    model.result("pg7").run();
    model.result("pg7").label("\u673a\u68b0\u529b\uff0c\u5927\u5c0f");
    model.result("pg7").set("titletype", "label");
    model.result("pg7").set("ylabel", "\u529b (N)");
    model.result("pg7").set("legendpos", "upperleft");
    model.result("pg7").set("xlog", true);
    model.result("pg7").run();
    model.result("pg8").run();
    model.result("pg8").label("\u673a\u68b0\u529b\uff0c\u76f8\u4f4d");
    model.result("pg8").set("titletype", "label");
    model.result("pg8").set("ylabel", "\u76f8\u4f4d (rad)");
    model.result("pg8").set("legendpos", "lowerright");
    model.result("pg8").set("xlog", true);
    model.result("pg8").run();
    model.result("pg9").run();
    model.result("pg9").label("\u819c\u7247\u901f\u5ea6\uff0c\u5e45\u503c");
    model.result("pg9").set("titletype", "label");
    model.result("pg9").set("ylabel", "\u901f\u5ea6 (m/s)");
    model.result("pg9").set("xlog", true);
    model.result("pg9").run();
    model.result("pg10").run();
    model.result("pg10").label("\u819c\u7247\u901f\u5ea6\uff0c\u76f8\u4f4d");
    model.result("pg10").set("titletype", "label");
    model.result("pg10").set("ylabel", "\u76f8\u4f4d (rad)");
    model.result("pg10").set("xlog", true);
    model.result("pg10").run();
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").run();
    model.result("pg11").label("\u58f0\u8f90\u5c04\u529f\u7387");
    model.result("pg11").set("titletype", "label");
    model.result("pg11").set("xlabelactive", true);
    model.result("pg11").set("xlabel", "f (Hz)");
    model.result("pg11").set("ylabelactive", true);
    model.result("pg11").set("ylabel", "\u529f\u7387 (W)");
    model.result("pg11").set("legendpos", "lowermiddle");
    model.result("pg11").create("glob1", "Global");
    model.result("pg11").feature("glob1").set("markerpos", "datapoints");
    model.result("pg11").feature("glob1").set("linewidth", "preference");
    model.result("pg11").feature("glob1").set("expr", new String[]{"P_AR"});
    model.result("pg11").feature("glob1").set("descr", new String[]{"\u8f90\u5c04\u529f\u7387"});
    model.result("pg11").feature("glob1").set("unit", new String[]{"W"});
    model.result("pg11").run();
    model.result("pg11").set("xlog", true);
    model.result().create("pg12", "PlotGroup1D");
    model.result("pg12").run();
    model.result("pg12").label("\u8f93\u5165\u7535\u529f\u7387");
    model.result("pg12").set("titletype", "label");
    model.result("pg12").set("xlabelactive", true);
    model.result("pg12").set("xlabel", "f (Hz)");
    model.result("pg12").set("ylabelactive", true);
    model.result("pg12").set("ylabel", "\u529f\u7387 (W)");
    model.result("pg12").set("legendpos", "lowerright");
    model.result("pg12").create("glob1", "Global");
    model.result("pg12").feature("glob1").set("markerpos", "datapoints");
    model.result("pg12").feature("glob1").set("linewidth", "preference");
    model.result("pg12").feature("glob1").set("expr", new String[]{"P_E"});
    model.result("pg12").feature("glob1")
         .set("descr", new String[]{"\u7535\u8f93\u5165\u529f\u7387\uff08\u5747\u65b9\u6839\uff09"});
    model.result("pg12").feature("glob1").set("unit", new String[]{"W"});
    model.result("pg12").run();
    model.result("pg12").set("xlog", true);
    model.result().create("pg13", "PlotGroup1D");
    model.result("pg13").run();
    model.result("pg13").label("\u9a71\u52a8\u5668\u6548\u7387");
    model.result("pg13").set("titletype", "label");
    model.result("pg13").set("xlabelactive", true);
    model.result("pg13").set("xlabel", "f (Hz)");
    model.result("pg13").set("ylabelactive", true);
    model.result("pg13").set("ylabel", "\u6548\u7387 (%)");
    model.result("pg13").create("glob1", "Global");
    model.result("pg13").feature("glob1").set("markerpos", "datapoints");
    model.result("pg13").feature("glob1").set("linewidth", "preference");
    model.result("pg13").feature("glob1").setIndex("expr", "eta*100", 0);
    model.result("pg13").feature("glob1").setIndex("descr", "\u9a71\u52a8\u5668\u6548\u7387", 0);
    model.result("pg13").run();
    model.result("pg13").set("xlog", true);
    model.result().dataset().create("pc1", "ParCurve2D");
    model.result().dataset("pc1").set("expry", "s*5[m]+(1-s)*Rair");
    model.result().dataset("pc1").set("global", true);
    model.result().create("pg14", "PlotGroup1D");
    model.result("pg14").run();
    model.result("pg14").label("\u8f74\u4e0a\u538b\u529b");
    model.result("pg14").set("data", "pc1");
    model.result("pg14").set("titletype", "label");
    model.result("pg14").set("xlabelactive", true);
    model.result("pg14").set("xlabel", "z (m)");
    model.result("pg14").set("ylabelactive", true);
    model.result("pg14").set("ylabel", "\u538b\u529b (Pa)");
    model.result("pg14").setIndex("looplevelinput", "manual", 0);
    model.result("pg14").setIndex("looplevel", new int[]{161}, 0);
    model.result("pg14").set("showlegends", false);
    model.result("pg14").create("lngr1", "LineGraph");
    model.result("pg14").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg14").feature("lngr1").set("linewidth", "preference");
    model.result("pg14").feature("lngr1").selection().set(4);
    model.result("pg14").feature("lngr1").set("expr", "pext(r,z)");
    model.result("pg14").feature("lngr1").set("xdata", "expr");
    model.result("pg14").feature("lngr1").set("xdataexpr", "z");
    model.result("pg14").feature("lngr1").set("resolution", "extrafine");
    model.result("pg14").run();
    model.result().create("pg15", "PlotGroup2D");
    model.result("pg15").run();
    model.result("pg15").label("\u5f3a\u5ea6");
    model.result("pg15").create("surf1", "Surface");
    model.result("pg15").feature("surf1").set("expr", "acpr.I_rms");
    model.result("pg15").run();
    model.result("pg15").create("arws1", "ArrowSurface");
    model.result("pg15").feature("arws1").set("expr", new String[]{"acpr.Ir", "acpr.Iz"});
    model.result("pg15").feature("arws1").set("descr", "\u5f3a\u5ea6");
    model.result("pg15").feature("arws1").set("color", "black");
    model.result("pg15").run();
    model.result("pg15").create("arwl1", "ArrowLine");
    model.result("pg15").feature("arwl1").set("expr", new String[]{"acpr.nr", "acpr.nz"});
    model.result("pg15").feature("arwl1").set("descr", "\u6cd5\u77e2");
    model.result("pg15").feature("arwl1").set("color", "black");
    model.result("pg15").run();
    model.result("pg15").run();
    model.result("pg15").setIndex("looplevel", 217, 0);
    model.result("pg15").run();
    model.result("pg15").setIndex("looplevel", 161, 0);
    model.result("pg15").run();
    model.result("pg15").setIndex("looplevel", 81, 0);
    model.result("pg15").run();
    model.result().create("pg16", "PlotGroup1D");
    model.result("pg16").run();
    model.result("pg16").label("\u65b9\u5411\u6027");
    model.result("pg16").create("dir1", "Directivity");
    model.result("pg16").feature("dir1").set("linewidth", "preference");
    model.result("pg16").feature("dir1").set("anglerestr", "manual");
    model.result("pg16").feature("dir1").set("phimin", -90);
    model.result("pg16").feature("dir1").set("phirange", 180);
    model.result("pg16").feature("dir1").set("layout", "frequencyy");
    model.result("pg16").run();
    model.result("pg16").set("ylog", true);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 217, 0);
    model.result("pg3").run();

    model
         .title("\u4f7f\u7528\u96c6\u603b\u673a\u68b0\u7cfb\u7edf\u7684\u626c\u58f0\u5668\u9a71\u52a8\u5668\u96c6\u603b\u6a21\u578b");

    model
         .description("\u8fd9\u662f\u4e00\u4e2a\u52a8\u5708\u626c\u58f0\u5668\u6a21\u578b\uff0c\u901a\u8fc7\u96c6\u603b\u53c2\u6570\u7c7b\u6bd4\u8868\u793a\u626c\u58f0\u5668\u7535\u5b50\u548c\u673a\u68b0\u90e8\u4ef6\u7684\u7279\u6027\u3002\u672c\u4f8b\u4f7f\u7528 Thiele-Small \u53c2\u6570\uff08\u5c0f\u4fe1\u53f7\u53c2\u6570\uff09\u4f5c\u4e3a\u96c6\u603b\u6a21\u578b\uff08\u7528\u201c\u7535\u8def\u201d\u63a5\u53e3\u8868\u793a\uff09\u7684\u8f93\u5165\uff0c\u5e76\u5c06\u8be5\u96c6\u603b\u6a21\u578b\u4e0e\u63cf\u8ff0\u5468\u56f4\u7a7a\u6c14\u57df\u7684\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u201c\u538b\u529b\u58f0\u5b66\u201d\u6a21\u578b\u8fdb\u884c\u8026\u5408\u3002\u6a21\u578b\u7684\u8f93\u51fa\u5305\u62ec\u963b\u6297\u548c\u8f90\u5c04\u58f0\u529f\u7387\u7b49\u5173\u952e\u7279\u6027\u3002\n\n\u6b64\u6a21\u578b\u4f7f\u7528\u201c\u96c6\u603b\u673a\u68b0\u7cfb\u7edf\u201d\u63a5\u53e3\u5bf9\u626c\u58f0\u5668\u673a\u68b0\u90e8\u4ef6\uff08\u5982\u79fb\u52a8\u8d28\u91cf\u3001\u60ac\u67b6\u67d4\u5ea6\u548c\u60ac\u67b6\u673a\u68b0\u635f\u8017\u7b49\uff09\u8fdb\u884c\u5efa\u6a21\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("lumped_loudspeaker_driver_mechanical.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
