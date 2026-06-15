/*
 * induction_motor_llcreop.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:45 by COMSOL 6.3.0.290. */
public class induction_motor_llcreop {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\LiveLink_for_PTC_Creo_Parametric\\Tutorials,_LiveLink_Interface");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1").create("cad1", "LiveLinkPTCCreoParametric");
    model.component("comp1").geom("geom1").feature("cad1").updateCadParamTable(false, false);
    model.component("comp1").geom("geom1").feature("cad1").importData();

    model.param().set("x0", "0");
    model.param().descr("x0", "Section cut position");
    model.param().set("f0", "60[Hz]");
    model.param().descr("f0", "Supply frequency");
    model.param().set("a0", "1[mm^2]");
    model.param().descr("a0", "Coil wire cross-section area");
    model.param().set("I_stat", "5[A]");
    model.param().descr("I_stat", "Coil wire current");
    model.param().set("fs", "2*f0/LL_N_POLES");
    model.param().descr("fs", "Synchronous frequency");
    model.param().set("L", "80[mm]");
    model.param().descr("L", "Motor length");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp1").set("quickx", "x0");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"cad1_CASING_FAN"});
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("createpairs", false);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").hideObjects().create("hide1");
    model.component("comp1").view("view1").hideObjects("hide1").init(3);
    model.component("comp1").view("view1").hideObjects("hide1").add("fin", 20);
    model.component("comp1").view("view1").set("hidestatus", "showonlyhidden");
    model.component("comp1").view("view1").hideObjects().clear();
    model.component("comp1").view("view1").set("hidestatus", "hide");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("geom1_adjsel1");
    model.component("comp1").mesh("mesh1").run();

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 2);

    model.component("comp2").mesh().create("mesh2");

    model.component("comp2").geom("geom2").lengthUnit("mm");
    model.component("comp2").geom("geom2").create("cro1", "CrossSection");
    model.component("comp2").geom("geom2").feature("cro1").set("selresult", true);
    model.component("comp2").geom("geom2").feature("cro1").set("selresultshow", "all");
    model.component("comp2").geom("geom2").feature("cro1").set("selfrom3d", true);
    model.component("comp2").geom("geom2").run("cro1");
    model.component("comp2").geom("geom2").create("csol1", "ConvertToSolid");
    model.component("comp2").geom("geom2").feature("csol1").selection("input").named("cro1");
    model.component("comp2").geom("geom2").feature("csol1").set("repairtoltype", "relative");
    model.component("comp2").geom("geom2").feature("csol1").set("repairtol", 1.0E-5);
    model.component("comp2").geom("geom2").run("csol1");
    model.component("comp2").geom("geom2").create("c1", "Circle");
    model.component("comp2").geom("geom2").feature("c1").set("r", "LL_R_ROTOR");
    model.component("comp2").geom("geom2").feature("c1").set("selresult", true);
    model.component("comp2").geom("geom2").run("c1");
    model.component("comp2").geom("geom2").create("boxsel1", "BoxSelection");
    model.component("comp2").geom("geom2").feature("boxsel1").set("selshow", false);
    model.component("comp2").geom("geom2").run("boxsel1");
    model.component("comp2").geom("geom2").create("difsel1", "DifferenceSelection");
    model.component("comp2").geom("geom2").feature("difsel1").set("add", new String[]{"boxsel1"});
    model.component("comp2").geom("geom2").feature("difsel1")
         .set("subtract", new String[]{"cro1_cad1_BOLTS", "cro1_cad1_CASING_FAN"});
    model.component("comp2").geom("geom2").run("difsel1");
    model.component("comp2").geom("geom2").create("unisel1", "UnionSelection");
    model.component("comp2").geom("geom2").feature("unisel1")
         .set("input", new String[]{"cro1_cad1_ROTOR_CORE_SHAFT", "cro1_cad1_STATOR_CORE"});
    model.component("comp2").geom("geom2").run("fin");

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
    model.material("mat2").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.material("mat2").label("Aluminum");
    model.material("mat2").set("family", "aluminum");
    model.material("mat2").propertyGroup("def").label("Basic");
    model.material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat2").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.material("mat2").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.material("mat2").propertyGroup("Enu").set("E", "70[GPa]");
    model.material("mat2").propertyGroup("Enu").set("nu", "0.33");
    model.material("mat2").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.material("mat2").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.material("mat2").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.material().create("mat3", "Common", "");
    model.material("mat3").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat3").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.material("mat3").propertyGroup("Enu").func().create("int2", "Interpolation");
    model.material("mat3").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.material("mat3").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic material model");
    model.material("mat3").propertyGroup("ElastoplasticModel").func().create("int1", "Interpolation");
    model.material("mat3").propertyGroup().create("Ludwik", "Ludwik", "Ludwik");
    model.material("mat3").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.material("mat3").propertyGroup().create("JohnsonCook", "JohnsonCook", "Johnson-Cook");
    model.material("mat3").propertyGroup().create("Swift", "Swift", "Swift");
    model.material("mat3").propertyGroup().create("Voce", "Voce", "Voce");
    model.material("mat3").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.material("mat3").propertyGroup().create("HockettSherby", "HockettSherby", "Hockett-Sherby");
    model.material("mat3").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.material("mat3").propertyGroup().create("ArmstrongFrederick", "ArmstrongFrederick", "Armstrong-Frederick");
    model.material("mat3").propertyGroup("ArmstrongFrederick").func().create("int1", "Interpolation");
    model.material("mat3").propertyGroup().create("Norton", "Norton", "Norton");
    model.material("mat3").propertyGroup().create("Garofalo", "Garofalo", "Garofalo (hyperbolic sine)");
    model.material("mat3").propertyGroup()
         .create("ChabocheViscoplasticity", "ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.material("mat3").label("Structural steel");
    model.material("mat3").set("family", "custom");
    model.material("mat3")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.material("mat3").set("diffuse", "custom");
    model.material("mat3")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.material("mat3").set("ambient", "custom");
    model.material("mat3")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.material("mat3").set("noise", true);
    model.material("mat3").set("fresnel", 0.9);
    model.material("mat3").set("roughness", 0.3);
    model.material("mat3").set("diffusewrap", 0);
    model.material("mat3").set("reflectance", 0);
    model.material("mat3").propertyGroup("def").label("Basic");
    model.material("mat3").propertyGroup("def").set("lossfactor", "0.02");
    model.material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat3").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.material("mat3").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.material("mat3").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.material("mat3").propertyGroup("Enu").func("int1").label("Interpolation 1");
    model.material("mat3").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.material("mat3").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.material("mat3").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.material("mat3").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});
    model.material("mat3").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
    model.material("mat3").propertyGroup("Enu").func("int2").label("Interpolation 2");
    model.material("mat3").propertyGroup("Enu").func("int2").set("funcnametable", new String[][]{{"int1", "1"}});
    model.material("mat3").propertyGroup("Enu").func("int2").set("funcname", "nu");
    model.material("mat3").propertyGroup("Enu").func("int2")
         .set("table", new String[][]{{"293.15", "0.30"}, {"793.15", "0.315"}});
    model.material("mat3").propertyGroup("Enu").func("int2").set("extrap", "linear");
    model.material("mat3").propertyGroup("Enu").func("int2").set("fununit", new String[]{"1"});
    model.material("mat3").propertyGroup("Enu").func("int2").set("argunit", new String[]{"K"});
    model.material("mat3").propertyGroup("Enu").set("E", "E(T)");
    model.material("mat3").propertyGroup("Enu").set("nu", "nu(T)");
    model.material("mat3").propertyGroup("Enu").addInput("temperature");
    model.material("mat3").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.material("mat3").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.material("mat3").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.material("mat3").propertyGroup("ElastoplasticModel").label("Elastoplastic material model");
    model.material("mat3").propertyGroup("ElastoplasticModel").func("int1").label("Interpolation 1");
    model.material("mat3").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "a");
    model.material("mat3").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat3").propertyGroup("ElastoplasticModel").func("int1").set("fununit", new String[]{"1"});
    model.material("mat3").propertyGroup("ElastoplasticModel").func("int1").set("argunit", new String[]{"K"});
    model.material("mat3").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.material("mat3").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.material("mat3").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.material("mat3").propertyGroup("ElastoplasticModel").set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.material("mat3").propertyGroup("ElastoplasticModel")
         .set("Hillcoefficients", new String[]{"0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]"});
    model.material("mat3").propertyGroup("ElastoplasticModel")
         .set("ys", new String[]{"0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]"});
    model.material("mat3").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.material("mat3").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.material("mat3").propertyGroup("Ludwik").func("int1").label("Interpolation 1");
    model.material("mat3").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.material("mat3").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat3").propertyGroup("Ludwik").func("int1").set("fununit", new String[]{"1"});
    model.material("mat3").propertyGroup("Ludwik").func("int1").set("argunit", new String[]{"K"});
    model.material("mat3").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.material("mat3").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.material("mat3").propertyGroup("Ludwik").addInput("temperature");
    model.material("mat3").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.material("mat3").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.material("mat3").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.material("mat3").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.material("mat3").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.material("mat3").propertyGroup("Swift").set("e0_swi", "0.021");
    model.material("mat3").propertyGroup("Swift").set("n_swi", "0.2");
    model.material("mat3").propertyGroup("Voce").func("int1").label("Interpolation 1");
    model.material("mat3").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.material("mat3").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat3").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.material("mat3").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.material("mat3").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.material("mat3").propertyGroup("Voce").set("beta_voc", "9.3");
    model.material("mat3").propertyGroup("Voce").addInput("temperature");
    model.material("mat3").propertyGroup("HockettSherby").func("int1").label("Interpolation 1");
    model.material("mat3").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.material("mat3").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat3").propertyGroup("HockettSherby").func("int1").set("fununit", new String[]{"1"});
    model.material("mat3").propertyGroup("HockettSherby").func("int1").set("argunit", new String[]{"K"});
    model.material("mat3").propertyGroup("HockettSherby").set("sigma_hoc", "684[MPa]*a(T)");
    model.material("mat3").propertyGroup("HockettSherby").set("m_hoc", "3.9");
    model.material("mat3").propertyGroup("HockettSherby").set("n_hoc", "0.85");
    model.material("mat3").propertyGroup("HockettSherby").addInput("temperature");
    model.material("mat3").propertyGroup("ArmstrongFrederick").func("int1").label("Interpolation 1");
    model.material("mat3").propertyGroup("ArmstrongFrederick").func("int1").set("funcname", "a");
    model.material("mat3").propertyGroup("ArmstrongFrederick").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat3").propertyGroup("ArmstrongFrederick").func("int1").set("fununit", new String[]{"1"});
    model.material("mat3").propertyGroup("ArmstrongFrederick").func("int1").set("argunit", new String[]{"K"});
    model.material("mat3").propertyGroup("ArmstrongFrederick").set("Ck", "2.070[GPa]*a(T)");
    model.material("mat3").propertyGroup("ArmstrongFrederick").set("gammak", "8.0");
    model.material("mat3").propertyGroup("ArmstrongFrederick").addInput("temperature");
    model.material("mat3").propertyGroup("Norton").label("Norton");
    model.material("mat3").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.material("mat3").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.material("mat3").propertyGroup("Norton").set("n_nor", "4.5");
    model.material("mat3").propertyGroup("Garofalo").label("Garofalo (hyperbolic sine)");
    model.material("mat3").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.material("mat3").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.material("mat3").propertyGroup("Garofalo").set("n_gar", "4.6");
    model.material("mat3").propertyGroup("ChabocheViscoplasticity").label("Chaboche viscoplasticity");
    model.material("mat3").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1[1/s]");
    model.material("mat3").propertyGroup("ChabocheViscoplasticity").set("sigRef_cha", "490[MPa]");
    model.material("mat3").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");
    model.material("mat3").propertyGroup("def").set("relpermeability", new String[]{"1000"});
    model.material("mat3").propertyGroup("def").set("electricconductivity", new String[]{"0[S/m]"});
    model.component("comp2").material().create("matlnk1", "Link");
    model.component("comp2").material().create("matlnk2", "Link");
    model.component("comp2").material("matlnk2").selection().named("geom2_cro1_cad1_ROTOR_COIL_dom");
    model.component("comp2").material("matlnk2").set("link", "mat2");
    model.component("comp2").material().create("matlnk3", "Link");
    model.component("comp2").material("matlnk3").selection().named("geom2_unisel1");
    model.component("comp2").material("matlnk3").set("link", "mat3");
    model.component("comp2").material().create("mat4", "Common");
    model.component("comp2").material("mat4").selection().named("geom2_cro1_cad1_PHASE_A_dom");
    model.component("comp2").material("mat4").set("family", "custom");
    model.component("comp2").material("mat4").set("diffuse", "red");
    model.component("comp2").material().duplicate("mat5", "mat4");
    model.component("comp2").material("mat5").selection().set();
    model.component("comp2").material("mat5").selection().named("geom2_cro1_cad1_PHASE_B_dom");
    model.component("comp2").material("mat5").set("diffuse", "green");
    model.component("comp2").material().duplicate("mat6", "mat5");
    model.component("comp2").material("mat6").selection().set();
    model.component("comp2").material("mat6").selection().named("geom2_cro1_cad1_PHASE_C_dom");
    model.component("comp2").material("mat6").set("diffuse", "blue");

    model.component("comp2").view("view3").set("showmaterial", false);

    model.component("comp2").material().remove("mat4");
    model.component("comp2").material().remove("mat5");
    model.component("comp2").material().remove("mat6");

    model.component("comp2").cpl().create("intop1", "Integration");
    model.component("comp2").cpl("intop1").set("axisym", true);
    model.component("comp2").cpl("intop1").selection().named("geom2_cro1_cad1_PHASE_A_dom");

    model.component("comp2").variable().create("var1");
    model.component("comp2").variable("var1").set("A_poles", "intop1(1)/LL_N_POLES");
    model.component("comp2").variable("var1").descr("A_poles", "Total coil area per poles");

    model.component("comp2").cpl().create("intop2", "Integration");
    model.component("comp2").cpl("intop2").set("axisym", true);
    model.component("comp2").cpl("intop2").selection().named("geom2_c1_dom");

    model.component("comp2").physics().create("mf", "InductionCurrents", "geom2");
    model.component("comp2").physics("mf").selection().named("geom2_difsel1");
    model.component("comp2").physics("mf").prop("d").set("d", "L");
    model.component("comp2").physics("mf").create("als1", "AmperesLawSolid", 2);
    model.component("comp2").physics("mf").feature("als1").selection().named("geom2_unisel1");
    model.component("comp2").physics("mf").create("coil1", "Coil", 2);
    model.component("comp2").physics("mf").feature("coil1").selection().named("geom2_cro1_cad1_PHASE_A_dom");
    model.component("comp2").physics("mf").feature("coil1").set("CoilName", "A");
    model.component("comp2").physics("mf").feature("coil1").set("ConductorModel", "Multi");
    model.component("comp2").physics("mf").feature("coil1").set("coilGroup", true);
    model.component("comp2").physics("mf").feature("coil1").set("N", "0.9*A_poles/a0");
    model.component("comp2").physics("mf").feature("coil1").set("ICoil", "I_stat*sqrt(2)*exp(j*2*pi)");
    model.component("comp2").physics("mf").feature("coil1").set("AreaFrom", "UserDefined");
    model.component("comp2").physics("mf").feature("coil1").set("HarmonicLoss", false);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp2").physics("mf").feature("coil1").set("coilWindArea", "a0");
    model.component("comp2").physics("mf").feature("coil1").create("rcd1", "ReverseCoilGroupDomain", 2);
    model.component("comp2").physics("mf").feature("coil1").feature("rcd1").selection()
         .named("geom2_cro1_cad1_PHASE_AMINUS_dom");
    model.component("comp2").physics("mf").create("coil2", "Coil", 2);
    model.component("comp2").physics("mf").feature("coil2").selection().named("geom2_cro1_cad1_PHASE_B_dom");
    model.component("comp2").physics("mf").feature("coil2").set("CoilName", "B");
    model.component("comp2").physics("mf").feature("coil2").set("ConductorModel", "Multi");
    model.component("comp2").physics("mf").feature("coil2").set("coilGroup", true);
    model.component("comp2").physics("mf").feature("coil2").set("N", "0.9*A_poles/a0");
    model.component("comp2").physics("mf").feature("coil2").set("ICoil", "I_stat*sqrt(2)*exp(j*2*pi/6)");
    model.component("comp2").physics("mf").feature("coil2").set("AreaFrom", "UserDefined");
    model.component("comp2").physics("mf").feature("coil2").set("HarmonicLoss", false);
    model.component("comp2").physics("mf").feature("coil2").set("coilWindArea", "a0");
    model.component("comp2").physics("mf").feature("coil2").create("rcd1", "ReverseCoilGroupDomain", 2);
    model.component("comp2").physics("mf").feature("coil2").feature("rcd1").selection()
         .named("geom2_cro1_cad1_PHASE_BMINUS_dom");
    model.component("comp2").physics("mf").create("coil3", "Coil", 2);
    model.component("comp2").physics("mf").feature("coil3").selection().named("geom2_cro1_cad1_PHASE_C_dom");
    model.component("comp2").physics("mf").feature("coil3").set("CoilName", "C");
    model.component("comp2").physics("mf").feature("coil3").set("ConductorModel", "Multi");
    model.component("comp2").physics("mf").feature("coil3").set("coilGroup", true);
    model.component("comp2").physics("mf").feature("coil3").set("N", "0.9*A_poles/a0");
    model.component("comp2").physics("mf").feature("coil3").set("ICoil", "I_stat*sqrt(2)*exp(j*2*pi/3)");
    model.component("comp2").physics("mf").feature("coil3").set("AreaFrom", "UserDefined");
    model.component("comp2").physics("mf").feature("coil3").set("HarmonicLoss", false);
    model.component("comp2").physics("mf").feature("coil3").set("coilWindArea", "a0");
    model.component("comp2").physics("mf").feature("coil3").create("rcd1", "ReverseCoilGroupDomain", 2);
    model.component("comp2").physics("mf").feature("coil3").feature("rcd1").selection()
         .named("geom2_cro1_cad1_PHASE_CMINUS_dom");

    model.component("comp2").coordSystem().create("sys3", "Cylindrical");

    model.component("comp2").physics("mf").create("fcal1", "ForceCalculation", 2);
    model.component("comp2").physics("mf").feature("fcal1").selection().named("geom2_c1_dom");
    model.component("comp2").physics("mf").feature("fcal1").set("ForceName", "rotor");
    model.component("comp2").physics("mf").create("coil4", "Coil", 2);
    model.component("comp2").physics("mf").feature("coil4").selection().named("geom2_cro1_cad1_ROTOR_COIL_dom");
    model.component("comp2").physics("mf").feature("coil4").set("ICoil", "0[A]");

    model.component("comp2").mesh("mesh2").automatic(false);
    model.component("comp2").mesh("mesh2").feature("size").set("custom", true);
    model.component("comp2").mesh("mesh2").feature("size").set("hcurve", 0.7);
    model.component("comp2").mesh("mesh2").feature("ftri1").selection().all();
    model.component("comp2").mesh("mesh2").run();

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/mf", true);
    model.study("std1").feature("freq").set("plist", "fs*{range(0,1e-2,0.2) range(0.3,1e-1,1)}");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "LL_AIR_GAP", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "LL_AIR_GAP", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "x0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "-39.5 -20 0 20 39.5", 0);
    model.study("std1").feature("param").setIndex("punit", "mm", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u78c1\u901a\u5bc6\u5ea6 (mf)");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 29, 0);
    model.result("pg1").setIndex("looplevel", 5, 1);
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
    model.result("pg1").feature("str1").selection().geom("geom2", 1);
    model.result("pg1").feature("str1").selection()
         .set(44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 56, 57, 58, 59, 60, 61, 62, 63, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 86, 87, 90, 91, 92, 93, 94, 95, 96, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 135, 136, 137, 138, 139, 140, 142, 143, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 168, 169, 170, 171, 172, 174, 175, 176, 177, 178, 179, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 204, 205, 206, 207, 210, 211, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 228, 229, 230, 231, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 258, 259, 264, 265, 266, 267, 270, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 285, 286, 287, 288, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 301, 302, 303, 304, 305, 306, 311, 312, 315, 316, 317, 318, 319, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 339, 340, 341, 342, 343, 344, 345, 346, 349, 350, 357, 358, 359, 360, 361, 362, 365, 366, 367, 368, 369, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 380, 381, 382, 383, 384, 385, 386, 387, 388, 390, 391, 392, 393, 395, 396, 397, 398, 399, 400, 401, 402, 403, 404, 405, 406, 407, 408, 409, 410, 413, 414, 415, 416, 417, 418, 419, 420, 423, 424, 426, 427, 428, 429, 431, 432, 433, 434, 435, 436, 438, 439, 440, 441, 442, 443, 445, 446, 447, 448, 449, 450, 451, 452, 453, 454, 455, 456, 457, 458, 459, 460, 461, 462, 463, 464, 465, 466, 468, 469, 470, 471, 472, 473, 475, 476, 478, 481, 482, 483, 484, 485, 486, 487, 488, 489, 490, 491, 492, 493, 494, 495, 496, 501, 502, 503, 504, 505, 506, 507, 508, 509, 510, 513, 514, 517, 518, 519, 520, 577, 578, 579, 580, 583, 584, 585, 586, 587, 588, 589, 590, 591, 592, 593, 594, 595, 596, 598, 599, 601, 602, 604, 605, 606, 607, 608, 609, 610, 611, 612, 613, 614, 615, 616, 617, 618, 619, 620, 621, 622, 623, 624, 625, 626, 627, 628, 629, 630, 631, 633, 634, 635, 636, 637, 638, 639, 640, 641, 642, 643, 644, 645, 646, 648, 649, 650, 651, 652, 653, 654, 655, 656, 657, 658, 659, 660, 661, 662, 663, 665, 666, 667, 668, 669, 670, 671, 672, 673, 674, 675, 676, 677, 678, 679, 680, 681, 682, 683, 684, 685, 686, 687, 688, 689, 690, 691, 692, 693, 694, 695, 696, 697, 698, 699, 700, 701, 702, 703, 704, 705, 706, 707, 708, 709, 710, 711, 712, 713, 714, 715, 716, 717, 718, 719, 720, 721, 722, 723, 724, 725, 726, 727, 728, 729, 730, 731, 732, 733, 734, 735, 736, 737, 738, 739, 740, 741, 742, 745, 746, 747, 748, 749, 750, 751, 752, 753, 754, 755, 756, 759, 760, 761, 762, 763, 764, 765, 766, 767, 768, 769, 770, 771, 772, 773, 774, 775, 776, 777, 778, 779, 780, 781, 782, 783, 784, 785, 786, 787, 788, 789, 790, 791, 792, 793, 794, 795, 796, 797, 798, 799, 800, 801, 802, 803, 804, 805, 806, 807, 808, 811, 812, 813, 814, 815, 816, 817, 818, 819, 820, 821, 822, 823, 824, 825, 826, 827, 828, 829, 830, 831, 832, 833, 834, 835, 836, 837, 838, 839, 840, 841, 842, 843, 844, 845, 846, 847, 848, 849, 850, 851, 852, 853, 854, 855, 856, 857, 858, 859, 860, 863, 864, 865, 866, 867, 868, 869, 870, 871, 872, 873, 874, 875, 876, 877, 878, 879, 880, 881, 882, 883, 884, 885, 886, 887, 888, 889, 890, 891, 892, 893, 894, 895, 896, 897, 898, 899, 900, 901, 902, 903, 904, 905, 906, 909, 910, 911, 912, 913, 914, 915, 916, 917, 918, 921, 922, 923, 924, 925, 926, 927, 928, 929, 930, 931, 932, 933, 934, 935, 936, 937, 938, 939, 940, 941, 942, 943, 944, 945, 946, 947, 948, 949, 950, 951, 952, 953, 954, 955, 956, 957, 958, 959, 960, 961, 962, 963, 964, 965, 966, 967, 968, 969, 970, 971, 972, 973, 974, 975, 976, 977, 978, 979, 980, 981, 982, 984, 985, 986, 987, 988, 989, 990, 991, 992, 993, 994, 995, 996, 997, 998, 999, 1000, 1001, 1002, 1003, 1004, 1005, 1007, 1008, 1009, 1010, 1012, 1013, 1014, 1015, 1016, 1017, 1018, 1019, 1020, 1021, 1022, 1023, 1024, 1025, 1026, 1027, 1028, 1029, 1031, 1032, 1033, 1034, 1035, 1036, 1037, 1038, 1039, 1040, 1041, 1042, 1043, 1044, 1045, 1046, 1047, 1048, 1049, 1050, 1051, 1052, 1053, 1054, 1055, 1056, 1058, 1059, 1060, 1061, 1062, 1063, 1064, 1065, 1066, 1067, 1068, 1069, 1070, 1071, 1072, 1073, 1074, 1075, 1077, 1078, 1079, 1080, 1081, 1082, 1087, 1088, 1089, 1090, 1091, 1092);
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
    model.result("pg1").feature("con1").set("expr", "mf.Az");
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
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").set("data", "dset2");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "mf.Jz");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").set("data", "dset2");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").setIndex("expr", "mf.Tax_rotor", 0);
    model.result("pg3").feature("glob1").setIndex("descr", "Axial torque", 0);
    model.result("pg3").feature("glob1").set("xdata", "expr");
    model.result("pg3").feature("glob1").set("xdataexpr", "freq/fs*100");
    model.result("pg3").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("data", "dset2");
    model.result().numerical("gev1").set("tablecols", "inner");
    model.result().numerical("gev1").setIndex("expr", "mf.Tax_rotor", 0);
    model.result().numerical("gev1").setIndex("descr", "Axial torque", 0);
    model.result().numerical("gev1").setIndex("expr", "freq/fs*100", 1);
    model.result().numerical("gev1").setIndex("descr", "Slip", 1);
    model.result().numerical("gev1").set("dataseries", "average");
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result("pg3").run();
    model.result("pg3").create("tblp1", "Table");
    model.result("pg3").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg3").feature("tblp1").set("linewidth", "preference");
    model.result("pg3").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg3").feature("tblp1").set("plotcolumns", new int[]{2});
    model.result("pg3").feature("tblp1").set("xaxisdata", 3);
    model.result("pg3").feature("tblp1").set("linestyle", "none");
    model.result("pg3").feature("tblp1").set("linemarker", "cycle");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").set("data", "dset2");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "intop2(mf.Qh*L)", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "Rotor losses", 0);
    model.result("pg4").feature("glob1").set("xdata", "expr");
    model.result("pg4").feature("glob1").set("xdataexpr", "freq/fs*100");
    model.result("pg4").run();
    model.result().dataset().create("dset3", "Solution");
    model.result().dataset("dset3").set("solution", "sol2");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").set("edges", false);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("titletype", "none");
    model.result("pg5").feature("surf1").set("expr", "1");
    model.result("pg5").feature("surf1").set("coloring", "uniform");
    model.result("pg5").feature("surf1").set("color", "gray");
    model.result("pg5").feature("surf1").create("filt1", "Filter");
    model.result("pg5").run();
    model.result("pg5").feature("surf1").feature("filt1").set("expr", "X>x0");
    model.result("pg5").create("line1", "Line");
    model.result("pg5").feature("line1").set("titletype", "none");
    model.result("pg5").feature("line1").set("expr", "1");
    model.result("pg5").feature("line1").set("coloring", "uniform");
    model.result("pg5").feature("line1").set("color", "black");
    model.result("pg5").feature("line1").create("filt1", "Filter");
    model.result("pg5").run();
    model.result("pg5").feature("line1").feature("filt1").set("expr", "X>x0");
    model.result("pg5").create("surf2", "Surface");
    model.result("pg5").feature("surf2").set("data", "dset2");
    model.result("pg5").feature("surf2").set("expr", "mf.normB");
    model.result("pg5").feature("surf2").set("descr", "\u78c1\u901a\u5bc6\u5ea6\u6a21");
    model.result("pg5").feature("surf2").create("def1", "Deform");
    model.result("pg5").run();
    model.result("pg5").feature("surf2").feature("def1").set("expr", new String[]{"-X+x0", "-X-Y", "Y"});
    model.result("pg5").feature("surf2").feature("def1").set("scaleactive", true);
    model.result("pg5").feature("surf2").feature("def1").set("scale", 1);
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("surf3", "surf2");
    model.result("pg5").run();
    model.result("pg5").feature("surf3").set("expr", "1");
    model.result("pg5").feature("surf3").set("coloring", "uniform");
    model.result("pg5").feature("surf3").set("color", "gray");
    model.result("pg5").feature("surf3").create("sel1", "Selection");
    model.result("pg5").feature("surf3").feature("sel1").selection().named("geom2_cro1_cad1_CASING_FAN_dom");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 3, 1);

//    Started method call runPlot

    model.result("pg5").feature("surf2").setIndex("looplevel", 29, 0);
    model.result("pg5").feature("surf3").setIndex("looplevel", 29, 0);
    model.result("pg5").feature("surf2").setIndex("looplevel", 3, 1);
    model.result("pg5").feature("surf3").setIndex("looplevel", 3, 1);
    model.result("pg5").run();

//    Finished method call runPlot

    model.result("pg5").run();

    model.title("\u4e8c\u6781\u4e09\u76f8\u611f\u5e94\u7535\u673a");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u8ba1\u7b97\u4e09\u76f8\u7b3c\u578b\u611f\u5e94\u7535\u673a\u5728\u4e0d\u540c\u901f\u5ea6\u4e0b\u7684\u626d\u77e9\u3002\n\n\u672c\u4f8b\u901a\u8fc7\u5728\u6cbf\u611f\u5e94\u7535\u673a\u591a\u4e2a\u4f4d\u7f6e\u7684\u4e8c\u7ef4\u6a2a\u622a\u9762\u4e0a\u6267\u884c\u4eff\u771f\uff0c\u5e76\u5bf9\u4eff\u771f\u7ed3\u679c\u6c42\u5e73\u5747\u503c\u6765\u8ba1\u7b97\u626d\u77e9\uff0c\u5176\u4e2d\u4f7f\u7528 LiveLink\u2122 \u63a5\u53e3\u4ece PTC\u00ae Creo\u00ae Parametric\u2122 \u4f20\u8f93\u7528\u4e8e\u7269\u7406\u573a\u8bbe\u7f6e\u7684\u4e09\u7ef4\u51e0\u4f55\u548c\u9009\u62e9\u3002\u901a\u8fc7\u5728 COMSOL Multiphysics\u00ae \u4e2d\u7684\u4e09\u7ef4\u51e0\u4f55\u7ed3\u6784\u4e2d\u5b9a\u4e49\u622a\u9762\uff0c\u60a8\u53ef\u4ee5\u4f7f\u7528\u201c\u6a2a\u622a\u9762\u201d\u64cd\u4f5c\u6765\u751f\u6210\u7528\u4e8e\u4eff\u771f\u7684\u4e8c\u7ef4\u51e0\u4f55\uff0c\u8be5\u64cd\u4f5c\u8fd8\u4fdd\u7559\u4e86\u5728\u4e09\u7ef4\u4e2d\u5b9a\u4e49\u7684\u9009\u62e9\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();

    model.label("induction_motor_llcreop.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
