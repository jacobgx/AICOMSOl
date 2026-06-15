/*
 * rotor_bearing_system_simulator.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:45 by COMSOL 6.3.0.290. */
public class rotor_bearing_system_simulator {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Rotordynamics_Module\\Applications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("rotbm", "BeamRotor", "geom1");

    model.study().create("std1");
    model.study("std1").create("eig", "Eigenfrequency");
    model.study("std1").feature("eig").setSolveFor("/physics/rotbm", true);

    model.param().set("L", "700[mm]");
    model.param().descr("L", "Length of the rotor");
    model.param().set("dout", "50[mm]");
    model.param().descr("dout", "Outer diameter of the rotor");
    model.param().set("din", "0[mm]");
    model.param().descr("din", "Inner diameter of the rotor");
    model.param().set("E0", "200e9[Pa]");
    model.param().descr("E0", "Young's modulus");
    model.param().set("nu0", "0.3");
    model.param().descr("nu0", "Poisson's ratio");
    model.param().set("rho0", "7850[kg/m^3]");
    model.param().descr("rho0", "Density of the rotor");
    model.param().set("Ow", "0[rpm]");
    model.param().descr("Ow", "Angular speed of the rotor");
    model.param().set("Ow_max", "50000[rpm]");
    model.param().descr("Ow_max", "Maximum angular speed of the rotor");
    model.param().set("Ow_step", "5000[rpm]");
    model.param().descr("Ow_step", "Angular speed step");

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup().create("Enu", "Young's modulus and Poisson's ratio");
    model.material("mat1").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup("Enu").func().create("int2", "Interpolation");
    model.material("mat1").propertyGroup().create("Murnaghan", "Murnaghan");
    model.material("mat1").propertyGroup().create("ElastoplasticModel", "Elastoplastic material model");
    model.material("mat1").propertyGroup("ElastoplasticModel").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup().create("Ludwik", "Ludwik");
    model.material("mat1").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup().create("JohnsonCook", "Johnson-Cook");
    model.material("mat1").propertyGroup().create("Swift", "Swift");
    model.material("mat1").propertyGroup().create("Voce", "Voce");
    model.material("mat1").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup().create("HockettSherby", "Hockett-Sherby");
    model.material("mat1").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup().create("ArmstrongFrederick", "Armstrong-Frederick");
    model.material("mat1").propertyGroup("ArmstrongFrederick").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup().create("Norton", "Norton");
    model.material("mat1").propertyGroup().create("Garofalo", "Garofalo (hyperbolic sine)");
    model.material("mat1").propertyGroup().create("ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.material("mat1").label("Structural steel");
    model.material("mat1").set("family", "custom");
    model.material("mat1")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.material("mat1").set("noise", true);
    model.material("mat1").set("fresnel", 0.9);
    model.material("mat1").set("roughness", 0.3);
    model.material("mat1").set("metallic", 0);
    model.material("mat1").set("pearl", 0);
    model.material("mat1").set("diffusewrap", 0);
    model.material("mat1").set("clearcoat", 0);
    model.material("mat1").set("reflectance", 0);
    model.material("mat1").propertyGroup("def").set("lossfactor", "0.02");
    model.material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.material("mat1").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.material("mat1").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.material("mat1").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.material("mat1").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.material("mat1").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});
    model.material("mat1").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("Enu").func("int2").set("funcname", "nu");
    model.material("mat1").propertyGroup("Enu").func("int2")
         .set("table", new String[][]{{"293.15", "0.30"}, {"793.15", "0.315"}});
    model.material("mat1").propertyGroup("Enu").func("int2").set("extrap", "linear");
    model.material("mat1").propertyGroup("Enu").func("int2").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("Enu").func("int2").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("Enu").set("E", "E(T)");
    model.material("mat1").propertyGroup("Enu").set("nu", "nu(T)");
    model.material("mat1").propertyGroup("Enu").addInput("temperature");
    model.material("mat1").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.material("mat1").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.material("mat1").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.material("mat1").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "a");
    model.material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat1").propertyGroup("ElastoplasticModel").func("int1").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("ElastoplasticModel").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.material("mat1").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.material("mat1").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.material("mat1").propertyGroup("ElastoplasticModel").set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.material("mat1").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.material("mat1").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.material("mat1").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.material("mat1").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat1").propertyGroup("Ludwik").func("int1").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("Ludwik").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.material("mat1").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.material("mat1").propertyGroup("Ludwik").addInput("temperature");
    model.material("mat1").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.material("mat1").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.material("mat1").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.material("mat1").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.material("mat1").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.material("mat1").propertyGroup("Swift").set("e0_swi", "0.021");
    model.material("mat1").propertyGroup("Swift").set("n_swi", "0.2");
    model.material("mat1").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.material("mat1").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat1").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.material("mat1").propertyGroup("Voce").set("beta_voc", "9.3");
    model.material("mat1").propertyGroup("Voce").addInput("temperature");
    model.material("mat1").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat1").propertyGroup("HockettSherby").func("int1").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("HockettSherby").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("HockettSherby").set("sigma_hoc", "684[MPa]*a(T)");
    model.material("mat1").propertyGroup("HockettSherby").set("m_hoc", "3.9");
    model.material("mat1").propertyGroup("HockettSherby").set("n_hoc", "0.85");
    model.material("mat1").propertyGroup("HockettSherby").addInput("temperature");
    model.material("mat1").propertyGroup("ArmstrongFrederick").func("int1").set("funcname", "a");
    model.material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat1").propertyGroup("ArmstrongFrederick").func("int1").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("ArmstrongFrederick").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("ArmstrongFrederick").set("Ck", "2.070[GPa]*a(T)");
    model.material("mat1").propertyGroup("ArmstrongFrederick").set("gammak", "8.0");
    model.material("mat1").propertyGroup("ArmstrongFrederick").addInput("temperature");
    model.material("mat1").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.material("mat1").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.material("mat1").propertyGroup("Norton").set("n_nor", "4.5");
    model.material("mat1").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.material("mat1").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.material("mat1").propertyGroup("Garofalo").set("n_gar", "4.6");
    model.material("mat1").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1");
    model.material("mat1").propertyGroup("ChabocheViscoplasticity").set("sigRef_cha", "490[MPa]");
    model.material("mat1").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");
    model.material().create("mat2", "Common", "");
    model.material("mat2").propertyGroup().create("Enu", "Young's modulus and Poisson's ratio");
    model.material("mat2").propertyGroup().create("linzRes", "Linearized resistivity");
    model.material("mat2").label("Copper");
    model.material("mat2").set("family", "copper");
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
    model.material("mat2").propertyGroup("Enu").set("E", "110[GPa]");
    model.material("mat2").propertyGroup("Enu").set("nu", "0.35");
    model.material("mat2").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.material("mat2").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.material("mat2").propertyGroup("linzRes").set("Tref", "298[K]");
    model.material("mat2").propertyGroup("linzRes").addInput("temperature");
    model.material().create("mat3", "Common", "");
    model.material("mat3").propertyGroup().create("Enu", "Young's modulus and Poisson's ratio");
    model.material("mat3").propertyGroup().create("Murnaghan", "Murnaghan");
    model.material("mat3").label("Aluminum");
    model.material("mat3").set("family", "aluminum");
    model.material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat3").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.material("mat3").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.material("mat3").propertyGroup("Enu").set("E", "70[GPa]");
    model.material("mat3").propertyGroup("Enu").set("nu", "0.33");
    model.material("mat3").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.material("mat3").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.material("mat3").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.material("mat1").propertyGroup("Enu").set("E", new String[]{"200e9[Pa]"});
    model.material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.material().create("mat4", "Common", "");
    model.material("mat4").label("User defined");
    model.material("mat4").propertyGroup("def").set("density", "");
    model.material("mat4").propertyGroup().create("Enu", "Young's_modulus_and_Poisson's_ratio");
    model.material("mat4").propertyGroup("def").set("density", new String[]{"7850[kg/m^3]"});
    model.material("mat4").propertyGroup("Enu").set("E", new String[]{"200e9[Pa]"});
    model.material("mat4").propertyGroup("Enu").set("nu", new String[]{"0.3"});

    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"L", "0", "0"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("matlnk1", "Link");

    model.component("comp1").physics("rotbm").prop("RotorProperties").set("rpt", "Ow");
    model.component("comp1").physics("rotbm").prop("Results").set("createUndefGeom", false);
    model.component("comp1").physics("rotbm").feature("rcs1").set("SectionType", "PipeSection");
    model.component("comp1").physics("rotbm").feature("rcs1").set("do_pipe", "dout");
    model.component("comp1").physics("rotbm").feature("rcs1").set("di_pipe", "din");
    model.component("comp1").physics("rotbm").create("jrb1", "JournalBearing", 0);
    model.component("comp1").physics("rotbm").feature("jrb1").selection().set(1);
    model.component("comp1").physics("rotbm").create("jrb2", "JournalBearing", 0);
    model.component("comp1").physics("rotbm").feature("jrb2").selection().set(2);

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("edg1").selection().all();
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("numelem", 50);

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);

    model.component("comp2").mesh().create("mesh2");

    model.component("comp2").geom("geom2").create("if1", "If");
    model.component("comp2").geom("geom2").feature().createAfter("endif1", "EndIf", "if1");
    model.component("comp2").geom("geom2").feature("if1").label("If: din != 0");
    model.component("comp2").geom("geom2").feature("if1").set("condition", "din != 0");
    model.component("comp2").geom("geom2").run("if1");
    model.component("comp2").geom("geom2").create("cyl1", "Cylinder");
    model.component("comp2").geom("geom2").feature("cyl1").set("type", "surface");
    model.component("comp2").geom("geom2").feature("cyl1").set("r", "dout/2");
    model.component("comp2").geom("geom2").feature("cyl1").set("h", "L");
    model.component("comp2").geom("geom2").feature("cyl1").set("axistype", "x");
    model.component("comp2").geom("geom2").feature("cyl1").setIndex("layername", "Layer 1", 0);
    model.component("comp2").geom("geom2").feature("cyl1").setIndex("layer", "(dout-din)/2.01", 0);
    model.component("comp2").geom("geom2").run("cyl1");
    model.component("comp2").geom("geom2").create("else1", "Else");
    model.component("comp2").geom("geom2").feature("else1").label("Else");
    model.component("comp2").geom("geom2").create("cyl2", "Cylinder");
    model.component("comp2").geom("geom2").feature("cyl2").set("r", "dout/2");
    model.component("comp2").geom("geom2").feature("cyl2").set("h", "L");
    model.component("comp2").geom("geom2").feature("cyl2").set("axistype", "x");
    model.component("comp2").geom("geom2").runPre("fin");

    model.study("std1").feature("eig").set("neigsactive", true);
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "L", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "L", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "Ow", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(0,Ow_step,Ow_max)", 0);
    model.study("std1").feature("param").setIndex("punit", "rpm", 0);

    model.sol().create("sol1");
    model.sol("sol1").study("std1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "eig");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.7000000000000001");
    model.sol("sol1").feature("v1").set("control", "eig");
    model.sol("sol1").create("e1", "Eigenvalue");
    model.sol("sol1").feature("e1").set("control", "eig");
    model.sol("sol1").attach("std1");

    model.batch().create("p1", "Parametric");
    model.batch("p1").study("std1");
    model.batch("p1").create("so1", "Solutionseq");
    model.batch("p1").feature("so1").set("seq", "sol1");
    model.batch("p1").feature("so1").set("store", "on");
    model.batch("p1").feature("so1").set("clear", "on");
    model.batch("p1").feature("so1").set("psol", "none");
    model.batch("p1").set("pname", new String[]{"Ow"});
    model.batch("p1").set("plistarr", new String[]{"range(0,Ow_step,Ow_max)"});
    model.batch("p1").set("sweeptype", "sparse");
    model.batch("p1").set("probesel", "all");
    model.batch("p1").set("probes", new String[]{});
    model.batch("p1").set("plot", "off");
    model.batch("p1").set("err", "on");
    model.batch("p1").attach("std1");
    model.batch("p1").set("control", "param");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("Parametric Solutions 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").setIndex("looplevel", 11, 1);
    model.result("pg1").set("defaultPlotID", "whirl");
    model.result("pg1").label("Whirl (rotbm)");
    model.result("pg1").create("wp1", "Whirl");
    model.result("pg1").feature("wp1").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("wp1").set("descr", "Displacement field");
    model.result("pg1").feature("wp1").set("nplanes", "1");
    model.result("pg1").feature("wp1").set("nrings", "10");
    model.result("pg1").feature("wp1").set("colortable", "TrafficLight");
    model.result("pg1").feature("wp1").set("ringcolor", "blue");
    model.result("pg1").feature("wp1").selection().geom("geom1", 1);
    model.result("pg1").feature("wp1").selection().set(1);
    model.result("pg1").feature("wp1").selection().inherit(false);
    model.result().evaluationGroup().create("std1EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std1EvgFrq").set("defaultPlotID", "eigenfrequenciesTable_rotbm");
    model.result().evaluationGroup("std1EvgFrq").set("data", "dset2");
    model.result().evaluationGroup("std1EvgFrq").label("Eigenfrequencies (Study 1)");
    model.result().evaluationGroup("std1EvgFrq").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "2*pi*freq", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "Angular frequency", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "Damping ratio", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "Quality factor", 2);
    model.result().evaluationGroup("std1EvgFrq").run();
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{""});
    model.result("pg2").feature("glob1").set("expr", new String[]{"rotbm.omegaf"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"Forward angular frequency"});
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").feature("glob1").set("xdataexpr", "rotbm.Ovg");
    model.result("pg2").feature("glob1").set("xdataunit", "rad/s");
    model.result("pg2").feature("glob1").label("Forward Whirl Mode");
    model.result("pg2").feature("glob1").set("linestyle", "dashed");
    model.result("pg2").feature("glob1").set("linecolor", "blue");
    model.result("pg2").feature("glob1").set("linewidth", 3);
    model.result("pg2").feature("glob1").set("legend", false);
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "outer");
    model.result("pg2").feature("glob1").create("gmrk1", "GraphMarker");
    model.result("pg2").feature("glob1").feature("gmrk1").set("displaymode", "intersection");
    model.result("pg2").feature("glob1").feature("gmrk1").set("intersectionline", "identity");
    model.result("pg2").feature("glob1").feature("gmrk1").set("precision", 4);
    model.result("pg2").feature("glob1").feature("gmrk1").set("labelprefix", "f: ");
    model.result("pg2").feature("glob1").feature("gmrk1").set("pointradius", 4);
    model.result("pg2").feature("glob1").feature("gmrk1").set("color", "custom");
    model.result("pg2").feature("glob1").feature("gmrk1")
         .set("customcolor", new double[]{1, 0.501960813999176, 0.250980406999588});
    model.result("pg2").feature("glob1").feature("gmrk1").set("anchorpoint", "lowermiddle");
    model.result("pg2").set("defaultPlotID", "campbell");
    model.result("pg2").label("Campbell Plot (rotbm)");
    model.result("pg2").create("glob2", "Global");
    model.result("pg2").feature("glob2").set("expr", new String[]{"rotbm.omegab"});
    model.result("pg2").feature("glob2").set("xdata", "expr");
    model.result("pg2").feature("glob2").set("xdataexpr", "rotbm.Ovg");
    model.result("pg2").feature("glob2").set("xdataunit", "rad/s");
    model.result("pg2").feature("glob2").set("linestyle", "dotted");
    model.result("pg2").feature("glob2").set("linecolor", "blue");
    model.result("pg2").feature("glob2").set("linewidth", 3);
    model.result("pg2").feature("glob2").set("legend", false);
    model.result("pg2").feature("glob2").label("Backward Whirl Mode");
    model.result("pg2").feature("glob2").set("xdatasolnumtype", "outer");
    model.result("pg2").feature("glob2").create("gmrk1", "GraphMarker");
    model.result("pg2").feature("glob2").feature("gmrk1").set("displaymode", "intersection");
    model.result("pg2").feature("glob2").feature("gmrk1").set("intersectionline", "identity");
    model.result("pg2").feature("glob2").feature("gmrk1").set("precision", 4);
    model.result("pg2").feature("glob2").feature("gmrk1").set("labelprefix", "b: ");
    model.result("pg2").feature("glob2").feature("gmrk1").set("pointradius", 4);
    model.result("pg2").feature("glob2").feature("gmrk1").set("color", "custom");
    model.result("pg2").feature("glob2").feature("gmrk1")
         .set("customcolor", new double[]{0.7490196228027344, 0.1411764770746231, 0.3686274588108063});
    model.result("pg2").feature("glob2").feature("gmrk1").set("anchorpoint", "uppermiddle");
    model.result("pg2").create("glob3", "Global");
    model.result("pg2").feature("glob3").set("expr", new String[]{"rotbm.omegan"});
    model.result("pg2").feature("glob3").set("xdata", "expr");
    model.result("pg2").feature("glob3").set("xdataexpr", "rotbm.Ovg");
    model.result("pg2").feature("glob3").set("xdataunit", "rad/s");
    model.result("pg2").feature("glob3").set("linecolor", "blue");
    model.result("pg2").feature("glob3").set("linewidth", 3);
    model.result("pg2").feature("glob3").set("legend", false);
    model.result("pg2").feature("glob3").label("Planar or Torsional Mode");
    model.result("pg2").feature("glob3").set("xdatasolnumtype", "outer");
    model.result("pg2").feature("glob3").create("gmrk1", "GraphMarker");
    model.result("pg2").feature("glob3").feature("gmrk1").set("displaymode", "intersection");
    model.result("pg2").feature("glob3").feature("gmrk1").set("intersectionline", "identity");
    model.result("pg2").feature("glob3").feature("gmrk1").set("precision", 4);
    model.result("pg2").feature("glob3").feature("gmrk1").set("labelprefix", "t,p: ");
    model.result("pg2").feature("glob3").feature("gmrk1").set("pointradius", 4);
    model.result("pg2").feature("glob3").feature("gmrk1").set("color", "custom");
    model.result("pg2").feature("glob3").feature("gmrk1")
         .set("customcolor", new double[]{0.03529411926865578, 0.4627451002597809, 0.03529411926865578});
    model.result("pg2").feature("glob3").feature("gmrk1").set("anchorpoint", "uppermiddle");
    model.result("pg2").create("glob4", "Global");
    model.result("pg2").feature("glob4").set("expr", new String[]{"if(rotbm.Ovg<=1.4*rotbm.omega,rotbm.Ovg,NaN)"});
    model.result("pg2").feature("glob4").set("data", "dset2");
    model.result("pg2").feature("glob4").set("xdata", "expr");
    model.result("pg2").feature("glob4").set("xdataexpr", "rotbm.Ovg");
    model.result("pg2").feature("glob4").set("xdataunit", "rad/s");
    model.result("pg2").feature("glob4").set("linecolor", "red");
    model.result("pg2").feature("glob4").set("linewidth", 3);
    model.result("pg2").feature("glob4").label("omega=Omega");
    model.result("pg2").feature("glob4").set("xdatasolnumtype", "outer");
    model.result("pg2").feature("glob4").set("solutionparams", "manual");
    model.result("pg2").feature("glob4").setIndex("looplevelinput", "last", 0);
    model.result("pg2").feature("glob4").set("legend", true);
    model.result("pg2").feature("glob4").set("legendmethod", "manual");
    model.result("pg2").feature("glob4").setIndex("legends", "\\omega=\\Omega", 0);
    model.result("pg2").set("ylabel", "Angular frequency (rad/s)");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "Campbell plot");
    model.result("pg2").label("Campbell Plot (rotbm)");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("glob1").feature("gmrk1").set("intersectionline", "general");
    model.result("pg2").feature("glob1").feature("gmrk1").set("generalcoeffa", "0.5 1 2");
    model.result("pg2").feature("glob1").feature("gmrk1").set("generalcoeffb", -1);
    model.result("pg2").feature("glob1").feature("gmrk1").set("generalcoeffc", 0);
    model.result("pg2").feature("glob1").feature("gmrk1").set("inclycoord", false);
    model.result("pg2").run();
    model.result("pg2").feature("glob2").feature("gmrk1").set("intersectionline", "general");
    model.result("pg2").feature("glob2").feature("gmrk1").set("generalcoeffa", "0.5 1 2");
    model.result("pg2").feature("glob2").feature("gmrk1").set("generalcoeffb", -1);
    model.result("pg2").feature("glob2").feature("gmrk1").set("generalcoeffc", 0);
    model.result("pg2").feature("glob2").feature("gmrk1").set("inclycoord", false);
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("glob5", "glob4");
    model.result("pg2").run();
    model.result("pg2").feature("glob5").label("omega=Omega/2");
    model.result("pg2").feature("glob5").setIndex("expr", "if(0.5*rotbm.Ovg<=1.4*rotbm.omega,0.5*rotbm.Ovg,NaN)", 0);
    model.result("pg2").feature("glob5").setIndex("unit", "rad/s", 0);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg2").feature("glob5").setIndex("descr", "", 0);
    model.result("pg2").feature("glob5").set("linecolor", "magenta");
    model.result("pg2").feature("glob5").setIndex("legends", "\\omega=\\Omega/2", 0);
    model.result("pg2").feature().duplicate("glob6", "glob5");
    model.result("pg2").run();
    model.result("pg2").feature("glob6").label("omega=2*Omega");
    model.result("pg2").feature("glob6").setIndex("expr", "if(2*rotbm.Ovg<=1.4*rotbm.omega,2*rotbm.Ovg,NaN)", 0);
    model.result("pg2").feature("glob6").setIndex("unit", "rad/s", 0);
    model.result("pg2").feature("glob6").setIndex("descr", "", 0);
    model.result("pg2").feature("glob6").set("linecolor", "green");
    model.result("pg2").feature("glob6").setIndex("legends", "\\omega=2\\Omega", 0);
    model.result("pg2").run();
    model.result("pg2").set("axislimits", true);
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("data", "dset2");
    model.result().numerical("gev1").setIndex("looplevelinput", "last", 1);
    model.result().numerical("gev1").set("expr", new String[]{"rotbm.omega"});
    model.result().numerical("gev1").set("descr", new String[]{"Angular frequency"});
    model.result().numerical("gev1").set("unit", new String[]{"rad/s"});
    model.result().numerical("gev1").set("dataseries", "maximum");
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").set("data", "dset2");
    model.result().numerical("gev2").setIndex("expr", "rotbm.freq-Ow", 0);
    model.result().numerical("gev2").setIndex("unit", "Hz", 0);
    model.result().numerical("gev2").setIndex("descr", "", 0);
    model.result().numerical().create("pev1", "EvalPoint");
    model.result().numerical("pev1").setIndex("looplevelinput", "first", 0);

    model.title("\u8f6c\u5b50\u8f74\u627f\u7cfb\u7edf\u6a21\u62df\u5668");

    model
         .description("\u672c App \u6a21\u62df\u4e00\u4e2a\u7531\u5b89\u88c5\u5728\u8f6c\u8f74\u4e0a\u7684\u5706\u76d8\u548c\u8f74\u627f\u7ec4\u6210\u7684\u8f6c\u5b50\u8f74\u627f\u7cfb\u7edf\u3002\u901a\u8fc7\u5bf9\u4e00\u5b9a\u8303\u56f4\u7684\u89d2\u901f\u5ea6\u6267\u884c\u7279\u5f81\u9891\u7387\u5206\u6790\uff0c\u4ee5\u786e\u5b9a\u7cfb\u7edf\u7684\u4e34\u754c\u8f6c\u901f\u3002\n\n\u6b64\u7c7b App \u5728\u65e9\u671f\u8bbe\u8ba1\u9636\u6bb5\u975e\u5e38\u6709\u7528\uff0c\u53ef\u4ee5\u5728\u6b64\u9636\u6bb5\u4fee\u6539\u8bbe\u8ba1\uff0c\u4f7f\u7cfb\u7edf\u7684\u4e34\u754c\u8f6c\u901f\u8fdc\u79bb\u5de5\u4f5c\u8f6c\u901f\u3002\n\n\u5206\u6790\u7ed3\u679c\u5305\u62ec\u6da1\u52a8\u6a21\u6001\u3001\u574e\u8d1d\u5c14\u56fe\u4ee5\u53ca\u4e34\u754c\u8f6c\u901f\u5217\u8868\u3002");

    model.label("rotor_bearing_system_simulator_embedded.mph");

    model.setExpectedComputationTime("40 seconds");

    model.result().report().create("rpt1", "Report");
    model.result().report("rpt1").set("format", "docx");
    model.result().report("rpt1").set("pagebreaklevel", "0");
    model.result().report("rpt1").set("imagesize", "xlarge");
    model.result().report("rpt1").feature().create("tp1", "TitlePage");
    model.result().report("rpt1").feature("tp1").set("frontmatterlayout", "headings");
    model.result().report("rpt1").feature("tp1").set("includecompany", false);
    model.result().report("rpt1").feature("tp1").set("includeversion", false);
    model.result().report("rpt1").feature("tp1").set("includeacknowledgment", false);
    model.result().report("rpt1").feature("tp1")
         .set("summary", "This application simulates a rotor bearing system with mountings at different locations of the rotor. The parametric eigenfrequency analysis of this system can be performed to determine the eigenfrequencies at different angular speeds. \n\nApplications finds its usage in determining the critical speeds of the rotating systems and helps in improving the design such that these critical speeds are away from the operating speed of the system. \n\nAs a result of the analysis, whirl modes, Campbell plot and critical speeds can be obtained.");
    model.result().report("rpt1").feature().create("toc1", "TableOfContents");
    model.result().report("rpt1").feature("toc1").set("levels", "1");
    model.result().report("rpt1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").feature().create("root1", "Model");
    model.result().report("rpt1").feature("sec1").feature("root1").set("includeunitsystem", true);
    model.result().report("rpt1").feature("sec1").feature().create("std1", "Study");
    model.result().report("rpt1").feature("sec1").feature("std1").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec1").feature("std1").setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").feature().create("geom1", "Geometry");
    model.result().report("rpt1").feature("sec2").feature("geom1").set("noderef", "geom2");
    model.result().report("rpt1").feature("sec2").feature("geom1").set("includeunits", false);
    model.result().report("rpt1").feature("sec2").feature("geom1").set("includestats", false);
    model.result().report("rpt1").feature("sec2").feature("geom1").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec2").feature("geom1").setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature("sec2").feature("geom1").setIndex("children", false, 2, 1);
    model.result().report("rpt1").feature("sec2").feature("geom1").setIndex("children", false, 3, 1);
    model.result().report("rpt1").feature("sec2").feature("geom1").setIndex("children", false, 4, 1);
    model.result().report("rpt1").feature("sec2").feature("geom1").setIndex("children", false, 5, 1);
    model.result().report("rpt1").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature().create("param1", "Parameter");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("param1")
         .setIndex("children", false, 3, 1);
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("param1")
         .setIndex("children", false, 4, 1);
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("param1")
         .setIndex("children", false, 5, 1);
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("param1")
         .setIndex("children", false, 6, 1);
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("param1")
         .setIndex("children", false, 7, 1);
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("param1")
         .setIndex("children", false, 8, 1);
    model.result().report("rpt1").feature("sec3").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature().create("mat1", "Material");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("mat1").set("noderef", "matlnk1");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("mat1").set("includeimage", false);
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("mat1").set("includeselection", false);
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("mat1").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec3").feature("sec2").feature().create("param1", "Parameter");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("param1")
         .setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("param1")
         .setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("param1")
         .setIndex("children", false, 2, 1);
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("param1")
         .setIndex("children", false, 6, 1);
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("param1")
         .setIndex("children", false, 7, 1);
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("param1")
         .setIndex("children", false, 8, 1);
    model.result().report("rpt1").feature().create("sec4", "Section");
    model.result().report("rpt1").feature("sec4").feature().create("arr1", "Arrays");
    model.result().report("rpt1").feature().create("sec5", "Section");
    model.result().report("rpt1").feature("sec5").feature().create("arr1", "Arrays");
    model.result().report("rpt1").feature().create("sec6", "Section");
    model.result().report("rpt1").feature("sec6").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec6").feature("sec1").feature().create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec6").feature("sec1").feature().create("pg2", "PlotGroup");
    model.result().report("rpt1").feature("sec6").feature("sec1").feature("pg2").set("noderef", "pg2");
    model.result().report("rpt1").feature("sec6").feature().create("arr1", "Arrays");

    app.form("main").formObject("collection1").setIndex("sectioncollapsible", false, 0);
    app.form("main").formObject("collection1").setIndex("sectioncollapsible", false, 1);
    app.form("bearingsStep").formObject("collection1").setIndex("sectioncollapsible", false, 0);
    app.form("studyStep").formObject("collection1").setIndex("sectioncollapsible", false, 0);
    app.form("resultsStep").formObject("collection1").setIndex("sectioncollapsible", false, 0);

    model.result("pg1").run();
    model.result().report("rpt1").feature("sec4").feature("arr1")
         .set("entityreferences", new String[]{"/AppBuilderFeatureList/field6:xBearing", "/AppBuilderFeatureList/field6:kBearing", "/AppBuilderFeatureList/field6:cBearing"});
    model.result().report("rpt1").feature("sec4").feature("arr1").setIndex("tableHeaders", "x (m)", 0);
    model.result().report("rpt1").feature("sec4").feature("arr1").setIndex("tableHeaders", "kyy (N/m)", 1);
    model.result().report("rpt1").feature("sec4").feature("arr1").setIndex("tableHeaders", "kzz (N/m)", 2);
    model.result().report("rpt1").feature("sec4").feature("arr1").setIndex("tableHeaders", "kyz (N/m)", 3);
    model.result().report("rpt1").feature("sec4").feature("arr1").setIndex("tableHeaders", "kzy (N/m)", 4);
    model.result().report("rpt1").feature("sec4").feature("arr1").setIndex("tableHeaders", "cyy (N*s/m)", 5);
    model.result().report("rpt1").feature("sec4").feature("arr1").setIndex("tableHeaders", "czz (N*s/m)", 6);
    model.result().report("rpt1").feature("sec4").feature("arr1").setIndex("tableHeaders", "cyz (N*s/m)", 7);
    model.result().report("rpt1").feature("sec4").feature("arr1").setIndex("tableHeaders", "czy (N*s/m)", 8);
    model.result().report("rpt1").feature("sec5").feature("arr1")
         .set("entityreferences", new String[]{"/AppBuilderFeatureList/field6:DiskTable"});
    model.result().report("rpt1").feature("sec5").feature("arr1").setIndex("tableHeaders", "x (m)", 0);
    model.result().report("rpt1").feature("sec5").feature("arr1").setIndex("tableHeaders", "d (m)", 1);
    model.result().report("rpt1").feature("sec5").feature("arr1").setIndex("tableHeaders", "h (m)", 2);
    model.result().report("rpt1").feature("sec5").feature("arr1").setIndex("tableHeaders", "rho (kg/m^3)", 3);
    model.result().report("rpt1").feature("sec5").feature("arr1").setIndex("tableHeaders", "m (kg)", 4);
    model.result().report("rpt1").feature("sec5").feature("arr1").setIndex("tableHeaders", "Ip (kg*m^2)", 5);
    model.result().report("rpt1").feature("sec5").feature("arr1").setIndex("tableHeaders", "Id (kg*m^2)", 6);
    model.result().report("rpt1").feature("sec6").feature("arr1")
         .set("entityreferences", new String[]{"/AppBuilderFeatureList/field4:freqIndex", "/AppBuilderFeatureList/field5:Owc"});
    model.result().report("rpt1").feature("sec6").feature("arr1").setIndex("tableHeaders", "Index", 0);
    model.result().report("rpt1").feature("sec6").feature("arr1")
         .setIndex("tableHeaders", "Critical speed (rad/s)", 1);

    model.title("\u8f6c\u5b50\u8f74\u627f\u7cfb\u7edf\u6a21\u62df\u5668");

    model
         .description("\u672c App \u6f14\u793a\u4ee5\u4e0b\u5185\u5bb9\uff1a\n\n\u2022 \u4f7f\u7528\u529f\u80fd\u533a\u4e2d\u7684\u5207\u6362\u6309\u94ae\u548c\u8bbe\u7f6e\u7a97\u53e3\u4e2d\u7684\u201c\u540e\u9000/\u524d\u8fdb\u201d\u6309\u94ae\u7684\u5bfc\u822a\u7cfb\u7edf\n\u2022 \u9009\u62e9\u9884\u5b9a\u4e49\u6216\u7528\u6237\u5b9a\u4e49\u7684\u6750\u6599\n\u2022 \u4f7f\u7528\u8868\u683c\u8f93\u5165\u51e0\u4f55\u5bf9\u8c61\n\n\u8be5 App \u6a21\u62df\u4e00\u4e2a\u7531\u5b89\u88c5\u5728\u8f6c\u8f74\u4e0a\u7684\u5706\u76d8\u548c\u8f74\u627f\u7ec4\u6210\u7684\u8f6c\u5b50\u8f74\u627f\u7cfb\u7edf\u3002\u901a\u8fc7\u5bf9\u4e00\u5b9a\u8303\u56f4\u7684\u89d2\u901f\u5ea6\u6267\u884c\u7279\u5f81\u9891\u7387\u5206\u6790\uff0c\u4ee5\u786e\u5b9a\u7cfb\u7edf\u7684\u4e34\u754c\u8f6c\u901f\u3002\n\n\u6b64\u7c7b App \u5728\u65e9\u671f\u8bbe\u8ba1\u9636\u6bb5\u975e\u5e38\u6709\u7528\uff0c\u53ef\u4ee5\u5728\u6b64\u9636\u6bb5\u4fee\u6539\u8bbe\u8ba1\uff0c\u4f7f\u7cfb\u7edf\u7684\u4e34\u754c\u8f6c\u901f\u8fdc\u79bb\u5de5\u4f5c\u8f6c\u901f\u3002\n\n\u5206\u6790\u7ed3\u679c\u5305\u62ec\u6da1\u52a8\u6a21\u6001\u3001\u574e\u8d1d\u5c14\u56fe\u4ee5\u53ca\u4e34\u754c\u8f6c\u901f\u5217\u8868\u3002");

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

    model.label("rotor_bearing_system_simulator.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
