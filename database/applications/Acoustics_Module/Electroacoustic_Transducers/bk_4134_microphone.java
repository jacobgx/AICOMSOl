/*
 * bk_4134_microphone.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:39 by COMSOL 6.3.0.290. */
public class bk_4134_microphone {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Electroacoustic_Transducers");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ta", "ThermoacousticsSinglePhysics", "geom1");
    model.component("comp1").physics().create("mbrn", "StructuralMembrane", "geom1");
    model.component("comp1").physics("mbrn").field("displacement").field("um");
    model.component("comp1").physics("mbrn").field("displacement").component(new String[]{"um", "vm", "wm"});
    model.component("comp1").physics().create("es", "Electrostatics", "geom1");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Tm0", "3160[N/m]", "\u819c\u5f20\u529b");
    model.param().set("Em", "2.21e11[Pa]", "\u819c\u7684\u6768\u6c0f\u6a21\u91cf");
    model.param().set("num", "0.4", "\u819c\u7684\u6cca\u677e\u6bd4");
    model.param().set("tm", "5[um]", "\u819c\u539a\u5ea6");
    model.param().set("Hmic", "3.35[mm]", "\u9ea6\u514b\u98ce\u9ad8\u5ea6");
    model.param().set("rhoms", "0.0445[kg/m^2]", "\u819c\u8868\u9762\u5bc6\u5ea6");
    model.param().set("rhom", "rhoms/tm", "\u819c\u5bc6\u5ea6");
    model.param().set("Q0", "3.145e-10[C]", "Vpol \u7684 1/12 \u80cc\u677f\u7535\u8377");
    model.param().set("pin", "1[Pa]", "\u8f93\u5165\u538b\u529b");
    model.param().set("pvent_e", "pin", "\u901a\u98ce\u53e3\u538b\u529b\uff08\u63a5\u89e6\u538b\u529b\u573a\uff09");
    model.param()
         .set("pvent_u", "0[Pa]", "\u901a\u98ce\u53e3\u538b\u529b\uff08\u4e0d\u63a5\u89e6\u538b\u529b\u573a\uff09");
    model.param().set("fmax", "20[kHz]", "\u6700\u5927\u9a71\u52a8\u9891\u7387");
    model.param()
         .set("dvisc", "0.22[mm]*sqrt(100[Hz]/fmax)", "fmax \u7684\u9ecf\u6ede\u8fb9\u754c\u5c42\u539a\u5ea6");
    model.param().set("L0", "39.5[dB]", "\u53c2\u8003\u7075\u654f\u5ea6");
    model.param().set("T0", "20[degC]", "\u5e73\u8861\uff08\u73af\u5883\uff09\u6e29\u5ea6");
    model.param().set("N0", "12", "\u6247\u533a\u6570");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "bk_4134_microphone.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").set("source", "file");
    model.component("comp1").func("int1").set("filename", "bk_4134_microphone_sensitivity_data.txt");
    model.component("comp1").func("int1").importData();
    model.component("comp1").func("int1").setEntry("columnType", "col2", "value");
    model.component("comp1").func("int1").setEntry("funcnames", "col2", "int_ave");
    model.component("comp1").func("int1").setEntry("columnType", "col3", "value");
    model.component("comp1").func("int1").setEntry("funcnames", "col3", "int_min");
    model.component("comp1").func("int1").setEntry("funcnames", "col4", "int_max");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(4, 16, 26);
    model.component("comp1").selection("sel1").label("\u819c");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u5bf9\u79f0");
    model.component("comp1").selection("sel2").geom(2);

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").selection("sel2").set(1, 2, 5, 11, 14, 21, 24, 29, 31, 34);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u538b\u529b\u91ca\u653e");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").set(32);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u63a5\u5730");
    model.component("comp1").selection("sel4").geom(2);
    model.component("comp1").selection("sel4").set(3, 6, 7, 8, 9, 10, 12, 17, 19, 22);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("\u7ec8\u7aef");
    model.component("comp1").selection("sel5").geom(2);
    model.component("comp1").selection("sel5").set(4, 16, 26, 27, 28, 36);

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
    model.component("comp1").material("mat2").label("\u819c\u6750\u6599");
    model.component("comp1").material("mat2").selection().geom("geom1", 2);
    model.component("comp1").material("mat2").selection().named("sel1");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", new String[]{"Em"});
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", new String[]{"num"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"rhom"});

    model.component("comp1").physics("ta").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("ta").feature("sym1").selection().named("sel2");
    model.component("comp1").physics("ta").create("pra1", "PressureAdiabatic", 2);
    model.component("comp1").physics("ta").feature("pra1").selection().named("sel3");
    model.component("comp1").physics("ta").feature("pra1")
         .set("pbnd", "linper(pvent_e*exp(-ta.iomega*Hmic/343[m/s]))");
    model.component("comp1").physics("ta").create("pra2", "PressureAdiabatic", 2);
    model.component("comp1").physics("ta").feature("pra2").selection().named("sel3");
    model.component("comp1").physics("ta").feature("pra2").set("pbnd", "linper(pvent_u)");
    model.component("comp1").physics("mbrn").selection().named("sel1");
    model.component("comp1").physics("mbrn").feature("to1").set("d", "tm");
    model.component("comp1").physics("mbrn").feature("lemm1").create("iss1", "InitialStressandStrain", 2);
    model.component("comp1").physics("mbrn").feature("lemm1").feature("iss1")
         .set("N0", new String[]{"Tm0", "0", "0", "Tm0"});
    model.component("comp1").physics("mbrn").create("fix1", "Fixed", 1);
    model.component("comp1").physics("mbrn").feature("fix1").selection().set(74);
    model.component("comp1").physics("mbrn").create("sym1", "Symmetry", 1);
    model.component("comp1").physics("mbrn").feature("sym1").selection().set(4, 5, 25, 42, 49, 57);
    model.component("comp1").physics("mbrn").create("fl1", "FaceLoad", 2);
    model.component("comp1").physics("mbrn").feature("fl1").selection().named("sel1");
    model.component("comp1").physics("mbrn").feature("fl1").set("forceType", "FollowerPressure");
    model.component("comp1").physics("mbrn").feature("fl1").set("pressure", "linper(pin)");
    model.component("comp1").physics("es").selection().set(1, 2, 3, 4);
    model.component("comp1").physics("es").create("gnd1", "Ground", 2);
    model.component("comp1").physics("es").feature("gnd1").selection().named("sel4");
    model.component("comp1").physics("es").create("term1", "Terminal", 2);
    model.component("comp1").physics("es").feature("term1").selection().named("sel5");
    model.component("comp1").physics("es").feature("term1").set("Q0", "Q0");
    model.component("comp1").physics("es").create("symp1", "SymmetryPlane", 2);
    model.component("comp1").physics("es").feature("symp1").selection().named("sel2");

    model.component("comp1").common().create("free1", "DeformingDomain");
    model.component("comp1").common("free1").selection().all();
    model.component("comp1").common("free1").set("smoothingType", "laplace");
    model.component("comp1").common("free1").selection().set(1, 3, 4);
    model.component("comp1").common().create("fix1", "FixedBoundary");
    model.component("comp1").common("fix1").selection().set(3, 15, 25);
    model.component("comp1").common().create("sym1", "Symmetry");
    model.component("comp1").common("sym1").selection().named("sel2");

    model.component("comp1").multiphysics().create("tsb1", "ThermoacousticStructureBoundary", 2);
    model.component("comp1").multiphysics("tsb1").selection().all();
    model.component("comp1").multiphysics().create("emfb1", "ElectromechanicsBoundary", 2);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(33);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(61);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(4, 16, 18, 26);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "0.4[mm]");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().set(24, 35, 48);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmax", "4*dvisc");
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(1, 3, 4, 5);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").selection().set(1, 3, 4);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").selection().set(5);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("elemcount", 10);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("elemratio", 10);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").run("swe1");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", "0.5[mm]");
    model.component("comp1").mesh("mesh1").run("ftet1");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").set("smoothtransition", false);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection()
         .set(6, 7, 8, 9, 10, 12, 13, 17, 18, 19, 20, 22, 23, 27, 28, 36);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhtot");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhtot", "pi*dvisc");
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ta", false);
    model.study("std1").feature("stat").setSolveFor("/physics/mbrn", true);
    model.study("std1").feature("stat").setSolveFor("/physics/es", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/tsb1", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/emfb1", true);
    model.study("std1").label("\u7814\u7a76 1 - \u655e\u53e3\u5f0f\u901a\u98ce\u53e3");
    model.study("std1").setGenPlots(false);
    model.study("std1").create("frlin", "Frequencylinearized");
    model.study("std1").feature("frlin").set("plist", "10^{range(0,3/10,3)} 10^{range(3.3,1/20,4.3)}");
    model.study("std1").feature("frlin").set("useadvanceddisable", true);
    model.study("std1").feature("frlin").set("disabledphysics", new String[]{"ta/pra2"});
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s2").feature("d1").set("pivotperturb", 1.0E-9);
    model.sol("sol1").feature("s2").feature("i1").active(true);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/ta", false);
    model.study("std2").feature("stat").setSolveFor("/physics/mbrn", true);
    model.study("std2").feature("stat").setSolveFor("/physics/es", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/tsb1", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/emfb1", true);
    model.study("std2").label("\u7814\u7a76 2 - \u5c01\u95ed\u5f0f\u901a\u98ce\u53e3");
    model.study("std2").setGenPlots(false);
    model.study("std2").create("frlin", "Frequencylinearized");
    model.study("std2").feature("frlin").set("plist", "10^{range(0,3/10,3)} 10^{range(3.3,1/20,4.3)}");
    model.study("std2").feature("frlin").set("useadvanceddisable", true);
    model.study("std2").feature("frlin").set("disabledphysics", new String[]{"ta/pra1"});
    model.study("std2").showAutoSequences("all");

    model.sol("sol3").feature("s2").feature("d1").set("pivotperturb", 1.0E-9);
    model.sol("sol3").feature("s2").feature("i1").active(true);

    model.study("std2").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().dataset("dset1").set("frametype", "material");
    model.result().dataset().create("sec1", "Sector3D");
    model.result().dataset("sec1").set("sectors", "N0");
    model.result().dataset("sec1").set("trans", "rotrefl");
    model.result().dataset("sec1").set("reflaxis", new int[]{0, -1, 0});
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("expr", new String[]{"es.V0_1"});
    model.result().numerical("gev1").set("descr", new String[]{"\u7ec8\u7aef\u7535\u538b"});
    model.result().numerical("gev1").set("unit", new String[]{"V"});
    model.result().numerical("gev1").set("evalmethod", "linpoint");
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u819c\u53d8\u5f62");
    model.result("pg1").set("data", "sec1");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "mbrn.disp");
    model.result("pg1").feature("surf1").set("unit", "\u00b5m");
    model.result("pg1").feature("surf1").set("expr", "mbrn.disp");
    model.result("pg1").feature("surf1").set("descr", "\u4f4d\u79fb\u5927\u5c0f");
    model.result("pg1").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg1").feature("surf1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 11, 0);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u901f\u5ea6");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").create("slc1", "Slice");
    model.result("pg2").feature("slc1").set("expr", "ta.v_inst");
    model.result("pg2").feature("slc1").set("unit", "mm/s");
    model.result("pg2").feature("slc1").set("quickplane", "zx");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u58f0\u538b\u7ea7");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "ta.Lp_t");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("\u58f0\u6e29\u53d8\u5316");
    model.result("pg4").setIndex("looplevel", 11, 0);
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "ta.T_t");
    model.result("pg4").feature("surf1").set("unit", "mK");
    model.result("pg4").feature("surf1").set("colortable", "ThermalWave");
    model.result("pg4").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").label("\u7535\u52bf\uff08\u7a33\u6001\uff09");
    model.result("pg5").set("data", "sec1");
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").create("mslc1", "Multislice");
    model.result("pg5").feature("mslc1").set("expr", "V");
    model.result("pg5").feature("mslc1").set("evalmethod", "linpoint");
    model.result("pg5").feature("mslc1").set("znumber", "3");
    model.result("pg5").feature("mslc1").set("colortable", "Dipole");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u7075\u654f\u5ea6");
    model.result("pg6").set("titletype", "label");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "f (Hz)");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "dB\uff08\u76f8\u5bf9 1 V/Pa\uff09");
    model.result("pg6").set("legendpos", "lowerright");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").setIndex("expr", "20*log10(abs(es.V0_1/pin))+L0", 0);
    model.result("pg6").feature("glob1")
         .setIndex("descr", "\u6a21\u578b\uff08\u655e\u53e3\u5f0f\u901a\u98ce\u53e3\u538b\u529b\u573a\uff09", 0);
    model.result("pg6").feature("glob1").setIndex("expr", "int_ave(freq)", 1);
    model.result("pg6").feature("glob1").setIndex("descr", "\u5e73\u5747\u6d4b\u91cf\u503c", 1);
    model.result("pg6").feature("glob1").setIndex("expr", "int_min(freq)", 2);
    model.result("pg6").feature("glob1").setIndex("descr", "\u8f83\u5c0f\u6d4b\u91cf\u503c", 2);
    model.result("pg6").feature("glob1").setIndex("expr", "int_max(freq)", 3);
    model.result("pg6").feature("glob1").setIndex("descr", "\u8f83\u5927\u6d4b\u91cf\u503c", 3);
    model.result("pg6").run();
    model.result("pg6").set("xlog", true);
    model.result("pg6").run();

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg6").create("glob2", "Global");
    model.result("pg6").feature("glob2").set("markerpos", "datapoints");
    model.result("pg6").feature("glob2").set("linewidth", "preference");
    model.result("pg6").feature("glob2").set("data", "dset3");
    model.result("pg6").feature("glob2").setIndex("expr", "20*log10(abs(es.V0_1/pin))+L0", 0);
    model.result("pg6").feature("glob2")
         .setIndex("descr", "\u6a21\u578b\uff08\u5c01\u95ed\u5f0f\u901a\u98ce\u53e3\u538b\u529b\u573a\uff09", 0);
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u9759\u6001\u819c\u53d8\u5f62");
    model.result("pg7").set("titletype", "label");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr1").set("linewidth", "preference");
    model.result("pg7").feature("lngr1").set("expr", "wm");
    model.result("pg7").feature("lngr1").set("unit", "\u00b5m");
    model.result("pg7").feature("lngr1").selection().set(4, 25, 42, 57);
    model.result("pg7").feature("lngr1").set("evalmethod", "linpoint");
    model.result("pg7").run();
    model.result("pg7").create("lngr2", "LineGraph");
    model.result("pg7").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr2").set("linewidth", "preference");
    model.result("pg7").feature("lngr2").set("expr", "wm");
    model.result("pg7").feature("lngr2").set("unit", "\u00b5m");
    model.result("pg7").feature("lngr2").selection().set(5, 49);
    model.result("pg7").feature("lngr2").set("evalmethod", "linpoint");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("\u9ea6\u514b\u65af\u97e6\u5e94\u529b");
    model.result("pg8").run();
    model.result("pg8").feature("lngr1").set("expr", "es.dnTz");
    model.result("pg8").run();
    model.result("pg8").feature("lngr2").set("expr", "es.dnTz");
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").label("\u7075\u654f\u5ea6\uff0c1/3 \u500d\u9891\u5e26");
    model.result("pg9").set("titletype", "label");
    model.result("pg9").create("oct1", "OctaveBand");
    model.result("pg9").feature("oct1").set("quantity", "bandpower");
    model.result("pg9").feature("oct1").set("markerpos", "datapoints");
    model.result("pg9").feature("oct1").set("linewidth", "preference");
    model.result("pg9").feature("oct1").selection().geom("geom1");
    model.result("pg9").feature("oct1").set("exprtype", "transfer");
    model.result("pg9").feature("oct1").set("levelref", "L0");
    model.result("pg9").feature("oct1").set("expr", "abs(es.V0_1/pin)^2");
    model.result("pg9").feature("oct1").set("quantity", "bandaveragepsd");
    model.result("pg9").feature("oct1").set("bandtype", "octave3");
    model.result("pg9").run();

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "intop_vol");
    model.component("comp1").cpl("intop1").selection().all();
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").set("opname", "intop_mem");
    model.component("comp1").cpl("intop2").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop2").selection().named("sel1");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("Qmem", "N0*intop_mem(ta.iomega*wm)", "\u819c\u4f53\u79ef\u901f\u5ea6");
    model.component("comp1").variable("var1")
         .set("Pvisc", "N0*intop_vol(ta.diss_visc)", "\u9ecf\u6027\u8017\u6563\u529f\u7387");
    model.component("comp1").variable("var1")
         .set("Ptherm", "N0*intop_vol(ta.diss_therm)", "\u70ed\u8017\u6563\u529f\u7387");
    model.component("comp1").variable("var1")
         .set("Ptot", "N0*intop_vol(ta.diss_tot)", "\u70ed\u548c\u9ecf\u6027\u603b\u8017\u6563\u529f\u7387");

    model.sol("sol3").updateSolution();

    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").run();
    model.result("pg10").label("\u8017\u6563\u80fd");
    model.result("pg10").set("data", "dset3");
    model.result("pg10").set("titletype", "label");
    model.result("pg10").set("xlabelactive", true);
    model.result("pg10").set("xlabel", "f (Hz)");
    model.result("pg10").set("ylabelactive", true);
    model.result("pg10").set("ylabel", "P (W)");
    model.result("pg10").set("xlog", true);
    model.result("pg10").set("ylog", true);
    model.result("pg10").set("legendpos", "upperleft");
    model.result("pg10").create("glob1", "Global");
    model.result("pg10").feature("glob1").set("markerpos", "datapoints");
    model.result("pg10").feature("glob1").set("linewidth", "preference");
    model.result("pg10").feature("glob1").setIndex("expr", "Ptot", 0);
    model.result("pg10").feature("glob1").setIndex("descr", "\u603b\u70ed\u548c\u8017\u6563\u80fd", 0);
    model.result("pg10").feature("glob1").setIndex("expr", "Pvisc", 1);
    model.result("pg10").feature("glob1").setIndex("descr", "\u9ecf\u6027\u8017\u6563\u80fd", 1);
    model.result("pg10").feature("glob1").setIndex("expr", "Ptherm", 2);
    model.result("pg10").feature("glob1").setIndex("descr", "\u70ed\u8017\u6563\u80fd", 2);
    model.result("pg10").run();
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").run();
    model.result("pg11").label("\u7b49\u6548\u58f0\u963b");
    model.result("pg11").set("data", "dset3");
    model.result("pg11").set("xlabelactive", true);
    model.result("pg11").set("xlabel", "f (Hz)");
    model.result("pg11").set("ylabelactive", true);
    model.result("pg11").set("ylabel", "R = real(Z) (kg/(m<sup>4</sup>s))");
    model.result("pg11").set("xlog", true);
    model.result("pg11").set("ylog", true);
    model.result("pg11").create("glob1", "Global");
    model.result("pg11").feature("glob1").set("markerpos", "datapoints");
    model.result("pg11").feature("glob1").set("linewidth", "preference");
    model.result("pg11").feature("glob1").setIndex("expr", "real(-pin/Qmem)", 0);
    model.result("pg11").feature("glob1").setIndex("descr", "real(Z)", 0);
    model.result("pg11").run();
    model.result("pg11").create("glob2", "Global");
    model.result("pg11").feature("glob2").set("markerpos", "datapoints");
    model.result("pg11").feature("glob2").set("linewidth", "preference");
    model.result("pg11").feature("glob2").setIndex("expr", "Ptot/(0.5*abs(Qmem)^2)", 0);
    model.result("pg11").feature("glob2").setIndex("descr", "real(Z) - \u603b\u8017\u6563\u80fd", 0);
    model.result("pg11").feature("glob2").setIndex("expr", "Pvisc/(0.5*abs(Qmem)^2)", 1);
    model.result("pg11").feature("glob2").setIndex("descr", "real(Z) - \u9ecf\u6027\u8017\u6563\u80fd", 1);
    model.result("pg11").feature("glob2").setIndex("expr", "Ptherm/(0.5*abs(Qmem)^2)", 2);
    model.result("pg11").feature("glob2").setIndex("descr", "real(Z) - \u70ed\u8017\u6563\u80fd", 2);
    model.result("pg11").feature("glob2").set("linestyle", "none");
    model.result("pg11").feature("glob2").set("linemarker", "point");
    model.result("pg11").run();
    model.result().create("pg12", "PlotGroup1D");
    model.result("pg12").run();
    model.result("pg12").label("\u566a\u97f3\u529f\u7387\u8c31\u5bc6\u5ea6");
    model.result("pg12").set("data", "dset3");
    model.result("pg12").set("titletype", "label");
    model.result("pg12").set("xlabelactive", true);
    model.result("pg12").set("xlabel", "f (Hz)");
    model.result("pg12").set("ylabelactive", true);
    model.result("pg12").set("ylabel", "\u529f\u7387\u8c31\u5bc6\u5ea6 (Pa<sup>2</sup>/Hz)");
    model.result("pg12").set("xlog", true);
    model.result("pg12").set("ylog", true);
    model.result("pg12").set("showlegends", false);
    model.result("pg12").create("glob1", "Global");
    model.result("pg12").feature("glob1").set("markerpos", "datapoints");
    model.result("pg12").feature("glob1").set("linewidth", "preference");
    model.result("pg12").feature("glob1").setIndex("expr", "4*k_B_const*T0*real(-pin/Qmem)*1[Hz]", 0);
    model.result("pg12").run();
    model.result().create("pg13", "PlotGroup1D");
    model.result("pg13").run();
    model.result("pg13").label("\u566a\u97f3\u538b\u529b\u8c31\u5bc6\u5ea6");
    model.result("pg13").set("data", "dset3");
    model.result("pg13").set("titletype", "label");
    model.result("pg13").set("xlabelactive", true);
    model.result("pg13").set("xlabel", "f (Hz)");
    model.result("pg13").set("ylabelactive", true);
    model.result("pg13").set("ylabel", "\u538b\u529b\u8c31\u5bc6\u5ea6 (Pa/Hz<sup>1/2</sup>)");
    model.result("pg13").set("xlog", true);
    model.result("pg13").set("ylog", true);
    model.result("pg13").set("showlegends", false);
    model.result("pg13").create("glob1", "Global");
    model.result("pg13").feature("glob1").set("markerpos", "datapoints");
    model.result("pg13").feature("glob1").set("linewidth", "preference");
    model.result("pg13").feature("glob1").setIndex("expr", "sqrt(4*k_B_const*T0*real(-pin/Qmem)*1[Hz])", 0);
    model.result("pg13").run();
    model.result().create("pg14", "PlotGroup1D");
    model.result("pg14").run();
    model.result("pg14").label("\u5e95\u566a\u6c34\u5e73");
    model.result("pg14").set("data", "dset3");
    model.result("pg14").set("titletype", "label");
    model.result("pg14").set("xlabelactive", true);
    model.result("pg14").set("xlabel", "f (Hz)");
    model.result("pg14").set("ylabelactive", true);
    model.result("pg14").set("ylabel", "\u58f0\u538b\u7ea7 (dB SPL)");
    model.result("pg14").set("xlog", true);
    model.result("pg14").set("showlegends", false);
    model.result("pg14").create("glob1", "Global");
    model.result("pg14").feature("glob1").set("markerpos", "datapoints");
    model.result("pg14").feature("glob1").set("linewidth", "preference");
    model.result("pg14").feature("glob1")
         .setIndex("expr", "10*log10(4*k_B_const*T0*real(-pin/Qmem)*1[Hz]/(20[uPa])^2)", 0);
    model.result("pg14").run();
    model.result("pg10").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").placeAfter("plotgroup", "pg9");
    model.nodeGroup("grp1").add("plotgroup", "pg10");
    model.nodeGroup("grp1").add("plotgroup", "pg11");
    model.nodeGroup("grp1").add("plotgroup", "pg12");
    model.nodeGroup("grp1").add("plotgroup", "pg13");
    model.nodeGroup("grp1").add("plotgroup", "pg14");
    model.nodeGroup("grp1").label("\u673a\u68b0-\u70ed\u566a\u56fe");

    model.result("pg1").run();

    model.title("Br\u00fcel & Kj\u00e6r 4134 \u7535\u5bb9\u5f0f\u9ea6\u514b\u98ce");

    model
         .description("\u672c\u4f8b\u662f\u4e00\u4e2a Br\u00fcel & Kj\u00e6r 4134 \u7535\u5bb9\u5f0f\u9ea6\u514b\u98ce\u6a21\u578b\uff0c\u5176\u4e2d\u7684\u51e0\u4f55\u548c\u6750\u6599\u53c2\u6570\u5747\u57fa\u4e8e\u5b9e\u9645\u9ea6\u514b\u98ce\u3002\u901a\u8fc7\u5c06\u8ba1\u7b97\u5f97\u5230\u7684\u7075\u654f\u5ea6\u6c34\u5e73\u4e0e\u5b9e\u9645\u9ea6\u514b\u98ce\u7684\u6d4b\u91cf\u6570\u636e\u8fdb\u884c\u6bd4\u8f83\uff0c\u7ed3\u679c\u663e\u793a\u51fa\u826f\u597d\u7684\u4e00\u81f4\u6027\u3002\u6b64\u5916\uff0c\u8fd8\u786e\u5b9a\u4e86\u819c\u53d8\u5f62\u3001\u538b\u529b\u3001\u901f\u5ea6\u548c\u7535\u573a\u3002\u6700\u540e\uff0c\u5728\u540e\u5904\u7406\u4e2d\u8ba1\u7b97\u4e86\u673a\u68b0-\u70ed\u672c\u5e95\u566a\u58f0\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("bk_4134_microphone.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
