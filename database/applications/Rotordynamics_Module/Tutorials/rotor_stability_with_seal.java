/*
 * rotor_stability_with_seal.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:48 by COMSOL 6.3.0.290. */
public class rotor_stability_with_seal {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Rotordynamics_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("rotbm", "BeamRotor", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/rotbm", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("x1", "0", "\u7ad9 1 \u7684\u4f4d\u7f6e");
    model.param().set("x2", "0.04[m]", "\u7ad9 2 \u7684\u4f4d\u7f6e");
    model.param().set("x3", "0.172[m]", "\u7ad9 3 \u7684\u4f4d\u7f6e");
    model.param().set("x4", "0.413[m]", "\u7ad9 4 \u7684\u4f4d\u7f6e");
    model.param().set("x5", "0.663[m]", "\u7ad9 5 \u7684\u4f4d\u7f6e");
    model.param().set("x6", "1.943[m]", "\u7ad9 6 \u7684\u4f4d\u7f6e");
    model.param().set("x7", "2.459[m]", "\u7ad9 7 \u7684\u4f4d\u7f6e");
    model.param().set("x8", "2.653[m]", "\u7ad9 8 \u7684\u4f4d\u7f6e");
    model.param().set("x9", "2.715[m]", "\u7ad9 9 \u7684\u4f4d\u7f6e");
    model.param().set("x10", "2.850[m]", "\u7ad9 10 \u7684\u4f4d\u7f6e");
    model.param().set("xTol", "1e-5[m]", "\u8f74\u5411\u6b65\u8ddd\u5bb9\u5dee");
    model.param().label("\u53c2\u6570\uff1a\u5de5\u4f4d");
    model.param().create("par2");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("d_1_2", "0.072[m]", "\u7ad9 1 \u548c 2 \u4e4b\u95f4\u7684\u76f4\u5f84");
    model.param("par2").set("d_2_3", "0.092[m]", "\u7ad9 2 \u548c 3 \u4e4b\u95f4\u7684\u76f4\u5f84");
    model.param("par2").set("d_3_4", "0.105[m]", "\u7ad9 3 \u548c 4 \u4e4b\u95f4\u7684\u76f4\u5f84");
    model.param("par2").set("d_4_5", "0.110[m]", "\u7ad9 4 \u548c 5 \u4e4b\u95f4\u7684\u76f4\u5f84");
    model.param("par2").set("d_5_6", "0.115[m]", "\u7ad9 5 \u548c 6 \u4e4b\u95f4\u7684\u76f4\u5f84");
    model.param("par2").set("d_6_7", "0.110[m]", "\u7ad9 6 \u548c 7 \u4e4b\u95f4\u7684\u76f4\u5f84");
    model.param("par2").set("d_7_8", "0.105[m]", "\u7ad9 7 \u548c 8 \u4e4b\u95f4\u7684\u76f4\u5f84");
    model.param("par2").set("d_8_9", "0.075[m]", "\u7ad9 8 \u548c 9 \u4e4b\u95f4\u7684\u76f4\u5f84");
    model.param("par2").set("d_9_10", "0.060[m]", "\u7ad9 9 \u548c 10 \u4e4b\u95f4\u7684\u76f4\u5f84");
    model.param("par2").label("\u53c2\u6570\uff1a\u8f6c\u5b50\u76f4\u5f84");
    model.param().create("par3");

//    To import content from file, use:
//    model.param("par3").loadFile("FILENAME");
    model.param("par3").set("Rb", "0.0525[m]", "\u8f74\u627f\u534a\u5f84");
    model.param("par3").set("Cb", "0.00015[m]", "\u8f74\u627f\u95f4\u9699");
    model.param("par3").set("Lb", "0.096[m]", "\u8f74\u627f\u957f\u5ea6");
    model.param("par3").set("mu_b", "0.0414[Pa*s]", "\u6da6\u6ed1\u6cb9\u9ecf\u5ea6");
    model.param("par3").set("xb1", "(x3+x4)/2", "\u8f74\u627f 1 \u7684\u4f4d\u7f6e");
    model.param("par3").set("xb2", "x8-Lb/2", "\u8f74\u627f 2 \u7684\u4f4d\u7f6e");
    model.param("par3").label("\u53c2\u6570\uff1a\u8f74\u627f");
    model.param().create("par4");

//    To import content from file, use:
//    model.param("par4").loadFile("FILENAME");
    model.param("par4").set("md1", "15[kg]", "\u53f6\u8f6e 1 \u7684\u8d28\u91cf");
    model.param("par4").set("Jd1", "0.131[kg*m^2]", "\u53f6\u8f6e 1 \u7684\u76f4\u5f84\u60ef\u6027\u77e9");
    model.param("par4").set("e1", "0.01[mm]", "\u53f6\u8f6e 1-10 \u7684\u4e0d\u5e73\u8861\u504f\u5fc3\u7387");
    model.param("par4").set("md2", "13[kg]", "\u53f6\u8f6e 2-9 \u7684\u8d28\u91cf");
    model.param("par4").set("Jd2", "0.114[kg*m^2]", "\u53f6\u8f6e 2-9 \u7684\u76f4\u5f84\u60ef\u6027\u77e9");
    model.param("par4").set("md10", "14[kg]", "\u53f6\u8f6e 10 \u7684\u8d28\u91cf");
    model.param("par4").set("Jd10", "0.122[kg*m^2]", "\u53f6\u8f6e 10 \u7684\u76f4\u5f84\u60ef\u6027\u77e9");
    model.param("par4").set("Ld", "0.08[m]", "\u53f6\u8f6e\u957f\u5ea6");
    model.param("par4").set("l1", "0.062[m]", "\u7b2c\u4e00\u4e2a\u53f6\u8f6e\u7684\u76f8\u5bf9\u4f4d\u7f6e");
    model.param("par4").set("gs", "(x6-x5)/10", "\u53f6\u8f6e\u7ea7\u4e4b\u95f4\u7684\u7a7a\u9699");
    model.param("par4").set("xd1", "x5+l1", "\u53f6\u8f6e 1 \u7684\u4f4d\u7f6e");
    model.param("par4").set("xd2", "xd1+gs", "\u53f6\u8f6e 2 \u7684\u4f4d\u7f6e");
    model.param("par4").set("xd3", "xd2+gs", "\u53f6\u8f6e 3 \u7684\u4f4d\u7f6e");
    model.param("par4").set("xd4", "xd3+gs", "\u53f6\u8f6e 4 \u7684\u4f4d\u7f6e");
    model.param("par4").set("xd5", "xd4+gs", "\u53f6\u8f6e 5 \u7684\u4f4d\u7f6e");
    model.param("par4").set("xd6", "xd5+gs", "\u53f6\u8f6e 6 \u7684\u4f4d\u7f6e");
    model.param("par4").set("xd7", "xd6+gs", "\u53f6\u8f6e 7 \u7684\u4f4d\u7f6e");
    model.param("par4").set("xd8", "xd7+gs", "\u53f6\u8f6e 8 \u7684\u4f4d\u7f6e");
    model.param("par4").set("xd9", "xd8+gs", "\u53f6\u8f6e 9 \u7684\u4f4d\u7f6e");
    model.param("par4").set("xd10", "xd9+gs", "\u53f6\u8f6e 10 \u7684\u4f4d\u7f6e");
    model.param("par4").label("\u53c2\u6570\uff1a\u53f6\u8f6e");
    model.param().create("par5");

//    To import content from file, use:
//    model.param("par5").loadFile("FILENAME");
    model.param("par5").set("mu_f", "0.02[Pa*s]", "\u6d41\u4f53\u9ecf\u5ea6");
    model.param("par5").set("rho_f", "800[kg/m^3]", "\u6d41\u4f53\u5bc6\u5ea6");
    model.param("par5").set("ds", "0.195[m]", "\u73af\u5f62\u5bc6\u5c01\u4ef6\u7684\u76f4\u5f84");
    model.param("par5").set("Cs", "0.0005[m]", "\u73af\u5f62\u5bc6\u5c01\u95f4\u9699");
    model.param("par5").set("Ls", "0.022[m]", "\u73af\u5f62\u5bc6\u5c01\u4ef6\u7684\u957f\u5ea6");
    model.param("par5").set("dPs", "3.2[MPa]", "\u6574\u4e2a\u73af\u5f62\u5bc6\u5c01\u4ef6\u7684\u538b\u964d");
    model.param("par5").set("dp", "0.08[m]", "\u6d3b\u585e\u5bc6\u5c01\u4ef6\u7684\u76f4\u5f84");
    model.param("par5").set("Cp", "0.0005[m]", "\u6d3b\u585e\u5bc6\u5c01\u95f4\u9699");
    model.param("par5").set("Lp", "0.165[m]", "\u6d3b\u585e\u5bc6\u5c01\u4ef6\u7684\u957f\u5ea6");
    model.param("par5").set("dPp", "32[MPa]", "\u6574\u4e2a\u6d3b\u585e\u5bc6\u5c01\u4ef6\u7684\u538b\u964d");
    model.param("par5").set("xs1", "x5+Ls/2", "\u73af\u5f62\u5bc6\u5c01\u4ef6 1 \u7684\u4f4d\u7f6e");
    model.param("par5").set("xs2", "xs1+gs", "\u73af\u5f62\u5bc6\u5c01\u4ef6 2 \u7684\u4f4d\u7f6e");
    model.param("par5").set("xs3", "xs2+gs", "\u73af\u5f62\u5bc6\u5c01\u4ef6 3 \u7684\u4f4d\u7f6e");
    model.param("par5").set("xs4", "xs3+gs", "\u73af\u5f62\u5bc6\u5c01\u4ef6 4 \u7684\u4f4d\u7f6e");
    model.param("par5").set("xs5", "xs4+gs", "\u73af\u5f62\u5bc6\u5c01\u4ef6 5 \u7684\u4f4d\u7f6e");
    model.param("par5").set("xs6", "xs5+gs", "\u73af\u5f62\u5bc6\u5c01\u4ef6 6 \u7684\u4f4d\u7f6e");
    model.param("par5").set("xs7", "xs6+gs", "\u73af\u5f62\u5bc6\u5c01\u4ef6 7 \u7684\u4f4d\u7f6e");
    model.param("par5").set("xs8", "xs7+gs", "\u73af\u5f62\u5bc6\u5c01\u4ef6 8 \u7684\u4f4d\u7f6e");
    model.param("par5").set("xs9", "xs8+gs", "\u73af\u5f62\u5bc6\u5c01\u4ef6 9 \u7684\u4f4d\u7f6e");
    model.param("par5").set("xs10", "xs9+gs", "\u73af\u5f62\u5bc6\u5c01\u4ef6 10 \u7684\u4f4d\u7f6e");
    model.param("par5").set("xp", "x7-Lp/2", "\u6d3b\u585e\u5bc6\u5c01\u4ef6\u7684\u4f4d\u7f6e");
    model.param("par5").label("\u53c2\u6570\uff1a\u5bc6\u5c01\u5708");

    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol1")
         .set("x", "x1 x2 x3 xb1 x4 x5 xs1 xd1 xs2 xd2 xs3 xd3 xs4 xd4 xs5 xd5 xs6 xd6 xs7 xd7 xs8 xd8 xs9 xd9 xs10 xd10 x6 xp x7 xb2 x8 x9 x10");
    model.component("comp1").geom("geom1").feature("pol1").set("y", 0);
    model.component("comp1").geom("geom1").feature("pol1").set("z", 0);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1")
         .set("table", new String[][]{{"x1", "d_1_2"}, 
         {"x2-xTol", "d_1_2"}, 
         {"x2", "d_2_3"}, 
         {"x3-xTol", "d_2_3"}, 
         {"x3", "d_3_4"}, 
         {"x4-xTol", "d_3_4"}, 
         {"x4", "d_4_5"}, 
         {"x5-xTol", "d_4_5"}, 
         {"x5", "d_5_6"}, 
         {"x6-xTol", "d_5_6"}, 
         {"x6", "d_6_7"}, 
         {"x7-xTol", "d_6_7"}, 
         {"x7", "d_7_8"}, 
         {"x8-xTol", "d_7_8"}, 
         {"x8", "d_8_9"}, 
         {"x9-xTol", "d_8_9"}, 
         {"x9", "d_9_10"}, 
         {"x10", "d_9_10"}});
    model.component("comp1").func("int1").label("\u63d2\u503c\uff1a\u76f4\u5f84");
    model.component("comp1").func("int1").set("funcname", "dia");
    model.component("comp1").func("int1").setIndex("fununit", "m", 0);
    model.component("comp1").func("int1").setIndex("argunit", "m", 0);

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("Enu").func().create("int2", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic material model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Ludwik", "Ludwik", "Ludwik");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("JohnsonCook", "JohnsonCook", "Johnson-Cook");
    model.component("comp1").material("mat1").propertyGroup().create("Swift", "Swift", "Swift");
    model.component("comp1").material("mat1").propertyGroup().create("Voce", "Voce", "Voce");
    model.component("comp1").material("mat1").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("HockettSherby", "HockettSherby", "Hockett-Sherby");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ArmstrongFrederick", "ArmstrongFrederick", "Armstrong-Frederick");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Norton", "Norton", "Norton");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Garofalo", "Garofalo", "Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ChabocheViscoplasticity", "ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.component("comp1").material("mat1").label("Structural steel");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.9);
    model.component("comp1").material("mat1").set("roughness", 0.3);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").set("lossfactor", "0.02");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").label("Interpolation 2");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("funcname", "nu");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2")
         .set("table", new String[][]{{"293.15", "0.30"}, {"793.15", "0.315"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "E(T)");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "nu(T)");
    model.component("comp1").material("mat1").propertyGroup("Enu").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .label("Elastoplastic material model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("Hillcoefficients", new String[]{"0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("ys", new String[]{"0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.component("comp1").material("mat1").propertyGroup("Swift").set("e0_swi", "0.021");
    model.component("comp1").material("mat1").propertyGroup("Swift").set("n_swi", "0.2");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("Voce").set("beta_voc", "9.3");
    model.component("comp1").material("mat1").propertyGroup("Voce").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").set("sigma_hoc", "684[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").set("m_hoc", "3.9");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").set("n_hoc", "0.85");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").set("Ck", "2.070[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").set("gammak", "8.0");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("Norton").label("Norton");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("n_nor", "4.5");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").label("Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("n_gar", "4.6");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity")
         .label("Chaboche viscoplasticity");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1[1/s]");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("sigRef_cha", "490[MPa]");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");

    model.component("comp1").physics("rotbm").prop("RotorProperties").set("freqr", "4500[rpm/s]*t+1000[rpm]");
    model.component("comp1").physics("rotbm").feature("rcs1").set("do_circ", "dia(x)");
    model.component("comp1").physics("rotbm").create("gr1", "Gravity", 1);
    model.component("comp1").physics("rotbm").create("jrb1", "JournalBearing", 0);
    model.component("comp1").physics("rotbm").feature("jrb1").selection().set(4, 30);
    model.component("comp1").physics("rotbm").feature("jrb1").set("BearingModel", "PlainHydrodynamic");
    model.component("comp1").physics("rotbm").feature("jrb1").set("mure_mat", "userdef");
    model.component("comp1").physics("rotbm").feature("jrb1").set("mure", "mu_b");
    model.component("comp1").physics("rotbm").feature("jrb1").set("C", "Cb");
    model.component("comp1").physics("rotbm").feature("jrb1").set("R", "Rb");
    model.component("comp1").physics("rotbm").feature("jrb1").set("L", "Lb");
    model.component("comp1").physics("rotbm").feature("jrb1").set("includeBendingStiffness", false);
    model.component("comp1").physics("rotbm").create("disk1", "Disk", 0);
    model.component("comp1").physics("rotbm").feature("disk1").selection().set(8);
    model.component("comp1").physics("rotbm").feature("disk1").set("mass", "md1");
    model.component("comp1").physics("rotbm").feature("disk1").set("Ip", "2*Jd1");
    model.component("comp1").physics("rotbm").feature("disk1").set("Id", "Jd1");
    model.component("comp1").physics("rotbm").feature("disk1").set("COM", "Relative");
    model.component("comp1").physics("rotbm").feature("disk1").set("zr", "e1");
    model.component("comp1").physics("rotbm").feature().duplicate("disk2", "disk1");
    model.component("comp1").physics("rotbm").feature("disk2").label("\u5706\u76d8 2-9");
    model.component("comp1").physics("rotbm").feature("disk2").selection().set(10, 12, 14, 16, 18, 20, 22, 24);
    model.component("comp1").physics("rotbm").feature("disk2").set("mass", "md2");
    model.component("comp1").physics("rotbm").feature("disk2").set("Ip", "2*Jd2");
    model.component("comp1").physics("rotbm").feature("disk2").set("Id", "Jd2");
    model.component("comp1").physics("rotbm").feature().duplicate("disk3", "disk1");
    model.component("comp1").physics("rotbm").feature("disk3").label("\u5706\u76d8 10-9");
    model.component("comp1").physics("rotbm").feature("disk3").selection().set(26);
    model.component("comp1").physics("rotbm").feature("disk3").set("mass", "md10");
    model.component("comp1").physics("rotbm").feature("disk3").set("Ip", "2*Jd10");
    model.component("comp1").physics("rotbm").feature("disk3").set("Id", "Jd10");

    model.nodeGroup().create("grp1", "Physics", "rotbm");
    model.nodeGroup("grp1").placeAfter("jrb1");
    model.nodeGroup("grp1").add("disk1");
    model.nodeGroup("grp1").add("disk2");
    model.nodeGroup("grp1").add("disk3");
    model.nodeGroup("grp1").label("\u53f6\u8f6e");

    model.study("std1").feature("time").set("tlist", "range(0,5e-4,2)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "auto");

    model.study("std1").label("\u7814\u7a76\uff1a\u65e0\u5bc6\u5c01\u5708");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 4001, 0);
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("expr", new String[]{"rotbm.mises"});
    model.result("pg1").feature("line1").set("threshold", "manual");
    model.result("pg1").feature("line1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("line1").set("colortable", "Rainbow");
    model.result("pg1").feature("line1").set("colortabletrans", "none");
    model.result("pg1").feature("line1").set("colorscalemode", "linear");
    model.result("pg1").label("\u5e94\u529b (rotbm)");
    model.result("pg1").feature("line1").set("colortable", "Rainbow");
    model.result("pg1").feature("line1").set("linetype", "tube");
    model.result("pg1").feature("line1").set("radiusexpr", "rotbm.re");
    model.result("pg1").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg1").feature("line1").set("tuberadiusscale", 1);
    model.result("pg1").feature("line1").set("tubeendcaps", false);
    model.result("pg1").feature("line1").create("def", "Deform");
    model.result("pg1").feature("line1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("line1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").create("line2", "Line");
    model.result("pg1").feature("line2").set("expr", new String[]{"1"});
    model.result("pg1").feature("line2").set("linetype", "tube");
    model.result("pg1").feature("line2").set("radiusexpr", new String[]{"rotbm.re "});
    model.result("pg1").feature("line2").set("tuberadiusscaleactive", true);
    model.result("pg1").feature("line2").set("tuberadiusscale", 1);
    model.result("pg1").feature("line2").set("tubeendcaps", false);
    model.result("pg1").feature("line2").set("coloring", "uniform");
    model.result("pg1").feature("line2").set("color", "custom");
    model.result("pg1").feature("line2")
         .set("customcolor", new double[]{0.9803921580314636, 0.7843137383460999, 0.7058823704719543});
    model.result("pg1").feature("line2").set("threshold", "manual");
    model.result("pg1").feature("line2").set("thresholdvalue", 0.2);
    model.result("pg1").feature("line2").set("titletype", "none");
    model.result("pg1").feature("line2").label("\u8f6c\u5b50");
    model.result("pg1").feature("line2").create("def", "Deform");
    model.result("pg1").feature("line2").feature("def").set("scaleactive", true);
    model.result("pg1").feature("line2").feature("def").set("scale", "1");
    model.result("pg1").feature("line2").feature("def")
         .set("expr", new String[]{"-rotbm.De_max*rotbm.e30x ", "-rotbm.De_max*rotbm.e30y ", "-rotbm.De_max*rotbm.e30z "});
    model.result("pg1").create("pttraj1", "PointTrajectories");
    model.result("pg1").feature("pttraj1").set("plotdata", "points");
    model.result("pg1").feature("pttraj1").selection().geom("geom1", 0);
    model.result("pg1").feature("pttraj1").selection().set(4, 30);
    model.result("pg1").feature("pttraj1").selection().inherit(false);
    model.result("pg1").feature("pttraj1").selection().embedded(false);
    model.result("pg1").feature("pttraj1").set("linetype", "none");
    model.result("pg1").feature("pttraj1").set("pointtype", "arrow");
    model.result("pg1").feature("pttraj1")
         .set("expr", new String[]{"X-1.0*rotbm.re*rotbm.jrb1.e3gx ", "Y-1.0*rotbm.re*rotbm.jrb1.e3gy ", "Z-1.0*rotbm.re*rotbm.jrb1.e3gz "});
    model.result("pg1").feature("pttraj1")
         .set("arrowexpr", new String[]{"rotbm.re*rotbm.jrb1.e3gx ", "rotbm.re*rotbm.jrb1.e3gy ", "rotbm.re*rotbm.jrb1.e3gz "});
    model.result("pg1").feature("pttraj1").set("arrowtype", "arrowhead");
    model.result("pg1").feature("pttraj1").set("arrowbase", "head");
    model.result("pg1").feature("pttraj1").set("arrowscale", "10");
    model.result("pg1").feature("pttraj1").set("arrowscaleactive", true);
    model.result("pg1").feature("pttraj1").set("pointcolor", "custom");
    model.result("pg1").feature("pttraj1")
         .set("custompointcolor", new double[]{0.5882353186607361, 0.8627451062202454, 0.5882353186607361});
    model.result("pg1").feature("pttraj1").set("titletype", "none");
    model.result("pg1").feature("pttraj1").label("\u8f74\u9888\u8f74\u627f 1");
    model.result("pg1").feature("pttraj1").create("def", "Deform");
    model.result("pg1").feature("pttraj1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("pttraj1").feature("def").set("scale", "1");
    model.result("pg1").feature("pttraj1").feature("def")
         .set("expr", new String[]{"-rotbm.De_max*rotbm.e30x ", "-rotbm.De_max*rotbm.e30y ", "-rotbm.De_max*rotbm.e30z "});

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg1").create("pttraj2", "PointTrajectories");
    model.result("pg1").feature("pttraj2").set("plotdata", "points");
    model.result("pg1").feature("pttraj2").selection().geom("geom1", 0);
    model.result("pg1").feature("pttraj2").selection().set(8);
    model.result("pg1").feature("pttraj2").selection().inherit(false);
    model.result("pg1").feature("pttraj2").selection().embedded(false);
    model.result("pg1").feature("pttraj2").set("linetype", "none");
    model.result("pg1").feature("pttraj2").set("expr", new String[]{"X", "Y", "Z"});
    model.result("pg1").feature("pttraj2").set("pointtype", "ellipse");
    model.result("pg1").feature("pttraj2").set("pointcolor", "custom");
    model.result("pg1").feature("pttraj2")
         .set("custompointcolor", new double[]{0.8039215803146362, 0.5215686559677124, 0.24705882370471954});
    model.result("pg1").feature("pttraj2")
         .set("semimajorexpr", new String[]{"0.5*rotbm.disk1.de*rotbm.e20x ", "0.5*rotbm.disk1.de*rotbm.e20y ", "0.5*rotbm.disk1.de*rotbm.e20z "});
    model.result("pg1").feature("pttraj2")
         .set("semiminorexpr", new String[]{"0.5*rotbm.disk1.de*rotbm.e30x ", "0.5*rotbm.disk1.de*rotbm.e30y ", "0.5*rotbm.disk1.de*rotbm.e30z "});
    model.result("pg1").feature("pttraj2").set("ellipsecount", 1);
    model.result("pg1").feature("pttraj2").set("ellipsearrowscaleactive", true);
    model.result("pg1").feature("pttraj2").set("ellipsearrowtype", "none");
    model.result("pg1").feature("pttraj2").set("titletype", "none");
    model.result("pg1").feature("pttraj2").label("\u5706\u76d8 1");
    model.result("pg1").feature("pttraj2").create("def", "Deform");
    model.result("pg1").feature("pttraj2").feature("def").set("scaleactive", true);
    model.result("pg1").feature("pttraj2").feature("def").set("scale", "1");
    model.result("pg1").feature("pttraj2").feature("def")
         .set("expr", new String[]{"-rotbm.De_max*rotbm.e30x ", "-rotbm.De_max*rotbm.e30y ", "-rotbm.De_max*rotbm.e30z "});
    model.result("pg1").create("pttraj3", "PointTrajectories");
    model.result("pg1").feature("pttraj3").set("plotdata", "points");
    model.result("pg1").feature("pttraj3").selection().geom("geom1", 0);
    model.result("pg1").feature("pttraj3").selection().set(10, 12, 14, 16, 18, 20, 22, 24);
    model.result("pg1").feature("pttraj3").selection().inherit(false);
    model.result("pg1").feature("pttraj3").selection().embedded(false);
    model.result("pg1").feature("pttraj3").set("linetype", "none");
    model.result("pg1").feature("pttraj3").set("expr", new String[]{"X", "Y", "Z"});
    model.result("pg1").feature("pttraj3").set("pointtype", "ellipse");
    model.result("pg1").feature("pttraj3").set("pointcolor", "custom");
    model.result("pg1").feature("pttraj3")
         .set("custompointcolor", new double[]{0.8039215803146362, 0.5215686559677124, 0.24705882370471954});
    model.result("pg1").feature("pttraj3")
         .set("semimajorexpr", new String[]{"0.5*rotbm.disk2.de*rotbm.e20x ", "0.5*rotbm.disk2.de*rotbm.e20y ", "0.5*rotbm.disk2.de*rotbm.e20z "});
    model.result("pg1").feature("pttraj3")
         .set("semiminorexpr", new String[]{"0.5*rotbm.disk2.de*rotbm.e30x ", "0.5*rotbm.disk2.de*rotbm.e30y ", "0.5*rotbm.disk2.de*rotbm.e30z "});
    model.result("pg1").feature("pttraj3").set("ellipsecount", 1);
    model.result("pg1").feature("pttraj3").set("ellipsearrowscaleactive", true);
    model.result("pg1").feature("pttraj3").set("ellipsearrowtype", "none");
    model.result("pg1").feature("pttraj3").set("titletype", "none");
    model.result("pg1").feature("pttraj3").label("\u5706\u76d8 2-9");
    model.result("pg1").feature("pttraj3").create("def", "Deform");
    model.result("pg1").feature("pttraj3").feature("def").set("scaleactive", true);
    model.result("pg1").feature("pttraj3").feature("def").set("scale", "1");
    model.result("pg1").feature("pttraj3").feature("def")
         .set("expr", new String[]{"-rotbm.De_max*rotbm.e30x ", "-rotbm.De_max*rotbm.e30y ", "-rotbm.De_max*rotbm.e30z "});
    model.result("pg1").create("pttraj4", "PointTrajectories");
    model.result("pg1").feature("pttraj4").set("plotdata", "points");
    model.result("pg1").feature("pttraj4").selection().geom("geom1", 0);
    model.result("pg1").feature("pttraj4").selection().set(26);
    model.result("pg1").feature("pttraj4").selection().inherit(false);
    model.result("pg1").feature("pttraj4").selection().embedded(false);
    model.result("pg1").feature("pttraj4").set("linetype", "none");
    model.result("pg1").feature("pttraj4").set("expr", new String[]{"X", "Y", "Z"});
    model.result("pg1").feature("pttraj4").set("pointtype", "ellipse");
    model.result("pg1").feature("pttraj4").set("pointcolor", "custom");
    model.result("pg1").feature("pttraj4")
         .set("custompointcolor", new double[]{0.8039215803146362, 0.5215686559677124, 0.24705882370471954});
    model.result("pg1").feature("pttraj4")
         .set("semimajorexpr", new String[]{"0.5*rotbm.disk3.de*rotbm.e20x ", "0.5*rotbm.disk3.de*rotbm.e20y ", "0.5*rotbm.disk3.de*rotbm.e20z "});
    model.result("pg1").feature("pttraj4")
         .set("semiminorexpr", new String[]{"0.5*rotbm.disk3.de*rotbm.e30x ", "0.5*rotbm.disk3.de*rotbm.e30y ", "0.5*rotbm.disk3.de*rotbm.e30z "});
    model.result("pg1").feature("pttraj4").set("ellipsecount", 1);
    model.result("pg1").feature("pttraj4").set("ellipsearrowscaleactive", true);
    model.result("pg1").feature("pttraj4").set("ellipsearrowtype", "none");
    model.result("pg1").feature("pttraj4").set("titletype", "none");
    model.result("pg1").feature("pttraj4").label("\u5706\u76d8 10-9");
    model.result("pg1").feature("pttraj4").create("def", "Deform");
    model.result("pg1").feature("pttraj4").feature("def").set("scaleactive", true);
    model.result("pg1").feature("pttraj4").feature("def").set("scale", "1");
    model.result("pg1").feature("pttraj4").feature("def")
         .set("expr", new String[]{"-rotbm.De_max*rotbm.e30x ", "-rotbm.De_max*rotbm.e30y ", "-rotbm.De_max*rotbm.e30z "});
    model.result("pg1").run();
    model.result("pg1").set("titletype", "label");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u8f68\u9053");
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr1").set("linewidth", "preference");
    model.result("pg2").feature("ptgr1").selection().set(4);
    model.result("pg2").feature("ptgr1").set("expr", "w/Cb");
    model.result("pg2").feature("ptgr1").set("xdata", "expr");
    model.result("pg2").feature("ptgr1").set("xdataexpr", "v/Cb");
    model.result("pg2").feature("ptgr1").set("linewidth", 3);
    model.result("pg2").feature("ptgr1").set("legend", true);
    model.result("pg2").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg2").feature("ptgr1").setIndex("legends", "\u65e0\u5bc6\u5c01\u5708", 0);
    model.result("pg2").run();
    model.result("pg2").set("preserveaspect", true);
    model.result("pg2").run();

    model.component("comp1").physics("rotbm").create("las1", "LiquidAnnularSeal", 0);
    model.component("comp1").physics("rotbm").feature("las1").set("R", "ds/2");
    model.component("comp1").physics("rotbm").feature("las1").set("Ls", "Ls");
    model.component("comp1").physics("rotbm").feature("las1").set("C", "Cs");
    model.component("comp1").physics("rotbm").feature("las1").set("rho_mat", "userdef");
    model.component("comp1").physics("rotbm").feature("las1").set("rho", "rho_f");
    model.component("comp1").physics("rotbm").feature("las1").set("mu_mat", "userdef");
    model.component("comp1").physics("rotbm").feature("las1").set("mu", "mu_f");
    model.component("comp1").physics("rotbm").feature("las1").set("dP", "dPs");
    model.component("comp1").physics("rotbm").feature("las1").set("V0", 60);
    model.component("comp1").physics("rotbm").feature("las1").set("SealModel", "Childs");
    model.component("comp1").physics("rotbm").feature("las1").set("alpha", 0.7);
    model.component("comp1").physics("rotbm").feature("las1").selection().set(7);
    model.component("comp1").physics("rotbm").feature().duplicate("las2", "las1");
    model.component("comp1").physics("rotbm").feature("las2").selection().set(9);
    model.component("comp1").physics("rotbm").feature().duplicate("las3", "las2");
    model.component("comp1").physics("rotbm").feature("las3").selection().set(11);
    model.component("comp1").physics("rotbm").feature().duplicate("las4", "las3");
    model.component("comp1").physics("rotbm").feature("las4").selection().set(13);
    model.component("comp1").physics("rotbm").feature().duplicate("las5", "las1");
    model.component("comp1").physics("rotbm").feature().duplicate("las6", "las2");
    model.component("comp1").physics("rotbm").feature().duplicate("las7", "las3");
    model.component("comp1").physics("rotbm").feature().duplicate("las8", "las4");
    model.component("comp1").physics("rotbm").feature("las5").selection().set(15);
    model.component("comp1").physics("rotbm").feature("las6").selection().set(17);
    model.component("comp1").physics("rotbm").feature("las7").selection().set(19);
    model.component("comp1").physics("rotbm").feature("las8").selection().set(21);
    model.component("comp1").physics("rotbm").feature().duplicate("las9", "las8");
    model.component("comp1").physics("rotbm").feature("las9").selection().set(23);
    model.component("comp1").physics("rotbm").feature().duplicate("las10", "las9");
    model.component("comp1").physics("rotbm").feature("las10").selection().set(25);

    model.nodeGroup().create("grp2", "Physics", "rotbm");
    model.nodeGroup("grp2").placeAfter("jrb1");
    model.nodeGroup("grp2").add("las1");
    model.nodeGroup("grp2").add("las2");
    model.nodeGroup("grp2").add("las3");
    model.nodeGroup("grp2").add("las4");
    model.nodeGroup("grp2").add("las5");
    model.nodeGroup("grp2").add("las6");
    model.nodeGroup("grp2").add("las7");
    model.nodeGroup("grp2").add("las8");
    model.nodeGroup("grp2").add("las9");
    model.nodeGroup("grp2").add("las10");
    model.nodeGroup("grp2").label("\u5bc6\u5c01\u5708");

    model.component("comp1").physics("rotbm").create("las11", "LiquidAnnularSeal", 0);
    model.component("comp1").physics("rotbm").feature("las11").set("R", "dp/2");
    model.component("comp1").physics("rotbm").feature("las11").set("Ls", "Lp");
    model.component("comp1").physics("rotbm").feature("las11").set("C", "Cp");
    model.component("comp1").physics("rotbm").feature("las11").set("rho_mat", "userdef");
    model.component("comp1").physics("rotbm").feature("las11").set("rho", "rho_f");
    model.component("comp1").physics("rotbm").feature("las11").set("mu_mat", "userdef");
    model.component("comp1").physics("rotbm").feature("las11").set("mu", "mu_f");
    model.component("comp1").physics("rotbm").feature("las11").set("dP", "dPp");
    model.component("comp1").physics("rotbm").feature("las11").set("V0", 90);
    model.component("comp1").physics("rotbm").feature("las11").selection().set(28);
    model.component("comp1").physics("rotbm").feature("las11").label("\u5e73\u8861\u6d3b\u585e\u5bc6\u5c01\u5708");

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/rotbm", true);
    model.study("std2").feature("time").set("tlist", "range(0,5e-4,2)");
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("t1").feature("fc1").set("dtech", "auto");

    model.study("std2").label("\u7814\u7a76\uff1a\u5e26\u5bc6\u5c01\u5708");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 4001, 0);
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("expr", new String[]{"rotbm.mises"});
    model.result("pg3").feature("line1").set("threshold", "manual");
    model.result("pg3").feature("line1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("line1").set("colortable", "Rainbow");
    model.result("pg3").feature("line1").set("colortabletrans", "none");
    model.result("pg3").feature("line1").set("colorscalemode", "linear");
    model.result("pg3").label("\u5e94\u529b (rotbm) 1");
    model.result("pg3").feature("line1").set("colortable", "Rainbow");
    model.result("pg3").feature("line1").set("linetype", "tube");
    model.result("pg3").feature("line1").set("radiusexpr", "rotbm.re");
    model.result("pg3").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg3").feature("line1").set("tuberadiusscale", 1);
    model.result("pg3").feature("line1").set("tubeendcaps", false);
    model.result("pg3").feature("line1").create("def", "Deform");
    model.result("pg3").feature("line1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg3").feature("line1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg3").create("line2", "Line");
    model.result("pg3").feature("line2").set("expr", new String[]{"1"});
    model.result("pg3").feature("line2").set("linetype", "tube");
    model.result("pg3").feature("line2").set("radiusexpr", new String[]{"rotbm.re "});
    model.result("pg3").feature("line2").set("tuberadiusscaleactive", true);
    model.result("pg3").feature("line2").set("tuberadiusscale", 1);
    model.result("pg3").feature("line2").set("tubeendcaps", false);
    model.result("pg3").feature("line2").set("coloring", "uniform");
    model.result("pg3").feature("line2").set("color", "custom");
    model.result("pg3").feature("line2")
         .set("customcolor", new double[]{0.9803921580314636, 0.7843137383460999, 0.7058823704719543});
    model.result("pg3").feature("line2").set("threshold", "manual");
    model.result("pg3").feature("line2").set("thresholdvalue", 0.2);
    model.result("pg3").feature("line2").set("titletype", "none");
    model.result("pg3").feature("line2").label("\u8f6c\u5b50");
    model.result("pg3").feature("line2").create("def", "Deform");
    model.result("pg3").feature("line2").feature("def").set("scaleactive", true);
    model.result("pg3").feature("line2").feature("def").set("scale", "1");
    model.result("pg3").feature("line2").feature("def")
         .set("expr", new String[]{"-rotbm.De_max*rotbm.e30x ", "-rotbm.De_max*rotbm.e30y ", "-rotbm.De_max*rotbm.e30z "});
    model.result("pg3").create("pttraj1", "PointTrajectories");
    model.result("pg3").feature("pttraj1").set("plotdata", "points");
    model.result("pg3").feature("pttraj1").selection().geom("geom1", 0);
    model.result("pg3").feature("pttraj1").selection().set(4, 30);
    model.result("pg3").feature("pttraj1").selection().inherit(false);
    model.result("pg3").feature("pttraj1").selection().embedded(false);
    model.result("pg3").feature("pttraj1").set("linetype", "none");
    model.result("pg3").feature("pttraj1").set("pointtype", "arrow");
    model.result("pg3").feature("pttraj1")
         .set("expr", new String[]{"X-1.0*rotbm.re*rotbm.jrb1.e3gx ", "Y-1.0*rotbm.re*rotbm.jrb1.e3gy ", "Z-1.0*rotbm.re*rotbm.jrb1.e3gz "});
    model.result("pg3").feature("pttraj1")
         .set("arrowexpr", new String[]{"rotbm.re*rotbm.jrb1.e3gx ", "rotbm.re*rotbm.jrb1.e3gy ", "rotbm.re*rotbm.jrb1.e3gz "});
    model.result("pg3").feature("pttraj1").set("arrowtype", "arrowhead");
    model.result("pg3").feature("pttraj1").set("arrowbase", "head");
    model.result("pg3").feature("pttraj1").set("arrowscale", "10");
    model.result("pg3").feature("pttraj1").set("arrowscaleactive", true);
    model.result("pg3").feature("pttraj1").set("pointcolor", "custom");
    model.result("pg3").feature("pttraj1")
         .set("custompointcolor", new double[]{0.5882353186607361, 0.8627451062202454, 0.5882353186607361});
    model.result("pg3").feature("pttraj1").set("titletype", "none");
    model.result("pg3").feature("pttraj1").label("\u8f74\u9888\u8f74\u627f 1");
    model.result("pg3").feature("pttraj1").create("def", "Deform");
    model.result("pg3").feature("pttraj1").feature("def").set("scaleactive", true);
    model.result("pg3").feature("pttraj1").feature("def").set("scale", "1");
    model.result("pg3").feature("pttraj1").feature("def")
         .set("expr", new String[]{"-rotbm.De_max*rotbm.e30x ", "-rotbm.De_max*rotbm.e30y ", "-rotbm.De_max*rotbm.e30z "});
    model.result("pg3").create("pttraj2", "PointTrajectories");
    model.result("pg3").feature("pttraj2").set("plotdata", "points");
    model.result("pg3").feature("pttraj2").selection().geom("geom1", 0);
    model.result("pg3").feature("pttraj2").selection().set(8);
    model.result("pg3").feature("pttraj2").selection().inherit(false);
    model.result("pg3").feature("pttraj2").selection().embedded(false);
    model.result("pg3").feature("pttraj2").set("linetype", "none");
    model.result("pg3").feature("pttraj2").set("expr", new String[]{"X", "Y", "Z"});
    model.result("pg3").feature("pttraj2").set("pointtype", "ellipse");
    model.result("pg3").feature("pttraj2").set("pointcolor", "custom");
    model.result("pg3").feature("pttraj2")
         .set("custompointcolor", new double[]{0.8039215803146362, 0.5215686559677124, 0.24705882370471954});
    model.result("pg3").feature("pttraj2")
         .set("semimajorexpr", new String[]{"0.5*rotbm.disk1.de*rotbm.e20x ", "0.5*rotbm.disk1.de*rotbm.e20y ", "0.5*rotbm.disk1.de*rotbm.e20z "});
    model.result("pg3").feature("pttraj2")
         .set("semiminorexpr", new String[]{"0.5*rotbm.disk1.de*rotbm.e30x ", "0.5*rotbm.disk1.de*rotbm.e30y ", "0.5*rotbm.disk1.de*rotbm.e30z "});
    model.result("pg3").feature("pttraj2").set("ellipsecount", 1);
    model.result("pg3").feature("pttraj2").set("ellipsearrowscaleactive", true);
    model.result("pg3").feature("pttraj2").set("ellipsearrowtype", "none");
    model.result("pg3").feature("pttraj2").set("titletype", "none");
    model.result("pg3").feature("pttraj2").label("\u5706\u76d8 1");
    model.result("pg3").feature("pttraj2").create("def", "Deform");
    model.result("pg3").feature("pttraj2").feature("def").set("scaleactive", true);
    model.result("pg3").feature("pttraj2").feature("def").set("scale", "1");
    model.result("pg3").feature("pttraj2").feature("def")
         .set("expr", new String[]{"-rotbm.De_max*rotbm.e30x ", "-rotbm.De_max*rotbm.e30y ", "-rotbm.De_max*rotbm.e30z "});
    model.result("pg3").create("pttraj3", "PointTrajectories");
    model.result("pg3").feature("pttraj3").set("plotdata", "points");
    model.result("pg3").feature("pttraj3").selection().geom("geom1", 0);
    model.result("pg3").feature("pttraj3").selection().set(10, 12, 14, 16, 18, 20, 22, 24);
    model.result("pg3").feature("pttraj3").selection().inherit(false);
    model.result("pg3").feature("pttraj3").selection().embedded(false);
    model.result("pg3").feature("pttraj3").set("linetype", "none");
    model.result("pg3").feature("pttraj3").set("expr", new String[]{"X", "Y", "Z"});
    model.result("pg3").feature("pttraj3").set("pointtype", "ellipse");
    model.result("pg3").feature("pttraj3").set("pointcolor", "custom");
    model.result("pg3").feature("pttraj3")
         .set("custompointcolor", new double[]{0.8039215803146362, 0.5215686559677124, 0.24705882370471954});
    model.result("pg3").feature("pttraj3")
         .set("semimajorexpr", new String[]{"0.5*rotbm.disk2.de*rotbm.e20x ", "0.5*rotbm.disk2.de*rotbm.e20y ", "0.5*rotbm.disk2.de*rotbm.e20z "});
    model.result("pg3").feature("pttraj3")
         .set("semiminorexpr", new String[]{"0.5*rotbm.disk2.de*rotbm.e30x ", "0.5*rotbm.disk2.de*rotbm.e30y ", "0.5*rotbm.disk2.de*rotbm.e30z "});
    model.result("pg3").feature("pttraj3").set("ellipsecount", 1);
    model.result("pg3").feature("pttraj3").set("ellipsearrowscaleactive", true);
    model.result("pg3").feature("pttraj3").set("ellipsearrowtype", "none");
    model.result("pg3").feature("pttraj3").set("titletype", "none");
    model.result("pg3").feature("pttraj3").label("\u5706\u76d8 2-9");
    model.result("pg3").feature("pttraj3").create("def", "Deform");
    model.result("pg3").feature("pttraj3").feature("def").set("scaleactive", true);
    model.result("pg3").feature("pttraj3").feature("def").set("scale", "1");
    model.result("pg3").feature("pttraj3").feature("def")
         .set("expr", new String[]{"-rotbm.De_max*rotbm.e30x ", "-rotbm.De_max*rotbm.e30y ", "-rotbm.De_max*rotbm.e30z "});
    model.result("pg3").create("pttraj4", "PointTrajectories");
    model.result("pg3").feature("pttraj4").set("plotdata", "points");
    model.result("pg3").feature("pttraj4").selection().geom("geom1", 0);
    model.result("pg3").feature("pttraj4").selection().set(26);
    model.result("pg3").feature("pttraj4").selection().inherit(false);
    model.result("pg3").feature("pttraj4").selection().embedded(false);
    model.result("pg3").feature("pttraj4").set("linetype", "none");
    model.result("pg3").feature("pttraj4").set("expr", new String[]{"X", "Y", "Z"});
    model.result("pg3").feature("pttraj4").set("pointtype", "ellipse");
    model.result("pg3").feature("pttraj4").set("pointcolor", "custom");
    model.result("pg3").feature("pttraj4")
         .set("custompointcolor", new double[]{0.8039215803146362, 0.5215686559677124, 0.24705882370471954});
    model.result("pg3").feature("pttraj4")
         .set("semimajorexpr", new String[]{"0.5*rotbm.disk3.de*rotbm.e20x ", "0.5*rotbm.disk3.de*rotbm.e20y ", "0.5*rotbm.disk3.de*rotbm.e20z "});
    model.result("pg3").feature("pttraj4")
         .set("semiminorexpr", new String[]{"0.5*rotbm.disk3.de*rotbm.e30x ", "0.5*rotbm.disk3.de*rotbm.e30y ", "0.5*rotbm.disk3.de*rotbm.e30z "});
    model.result("pg3").feature("pttraj4").set("ellipsecount", 1);
    model.result("pg3").feature("pttraj4").set("ellipsearrowscaleactive", true);
    model.result("pg3").feature("pttraj4").set("ellipsearrowtype", "none");
    model.result("pg3").feature("pttraj4").set("titletype", "none");
    model.result("pg3").feature("pttraj4").label("\u5706\u76d8 10-9");
    model.result("pg3").feature("pttraj4").create("def", "Deform");
    model.result("pg3").feature("pttraj4").feature("def").set("scaleactive", true);
    model.result("pg3").feature("pttraj4").feature("def").set("scale", "1");
    model.result("pg3").feature("pttraj4").feature("def")
         .set("expr", new String[]{"-rotbm.De_max*rotbm.e30x ", "-rotbm.De_max*rotbm.e30y ", "-rotbm.De_max*rotbm.e30z "});
    model.result("pg3").run();
    model.result("pg3").set("titletype", "label");
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr2").set("data", "dset2");
    model.result("pg2").feature("ptgr2").setIndex("legends", "\u5e26\u5bc6\u5c01\u5708", 0);
    model.result("pg2").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u5e73\u8861\u6d3b\u585e\u5bc6\u5c01\u5708\u521a\u5ea6");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").set("expr", new String[]{"rotbm.las11.Kd"});
    model.result("pg4").feature("glob1").set("descr", new String[]{"\u76f4\u63a5\u521a\u5ea6"});
    model.result("pg4").feature("glob1").set("unit", new String[]{"N/m"});
    model.result("pg4").feature("glob1").set("expr", new String[]{"rotbm.las11.Kd", "rotbm.las11.kc"});
    model.result("pg4").feature("glob1")
         .set("descr", new String[]{"\u76f4\u63a5\u521a\u5ea6", "\u4ea4\u53c9\u8026\u5408\u521a\u5ea6"});
    model.result("pg4").feature("glob1").set("xdata", "expr");
    model.result("pg4").feature("glob1").set("xdataexpr", "rotbm.las11.Omega");
    model.result("pg4").feature("glob1").set("linewidth", 3);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").set("legendpos", "upperleft");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u53f6\u8f6e 5 \u5904\u7684\u5782\u76f4\u4f4d\u79fb");
    model.result("pg5").create("ptgr1", "PointGraph");
    model.result("pg5").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg5").feature("ptgr1").set("linewidth", "preference");
    model.result("pg5").feature("ptgr1").label("\u70b9\u7ed3\u679c\u56fe\uff1a\u65e0\u5bc6\u5c01\u5708");
    model.result("pg5").feature("ptgr1").selection().set(16);
    model.result("pg5").feature("ptgr1").set("expr", "w");
    model.result("pg5").feature("ptgr1").set("xdata", "expr");
    model.result("pg5").feature("ptgr1").set("xdataexpr", "rotbm.Ov");
    model.result("pg5").feature("ptgr1").set("legend", true);
    model.result("pg5").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg5").feature("ptgr1").setIndex("legends", "\u65e0\u5bc6\u5c01\u5708", 0);
    model.result("pg5").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr2").label("\u70b9\u7ed3\u679c\u56fe\uff1a\u5e26\u5bc6\u5c01\u5708");
    model.result("pg5").feature("ptgr2").set("data", "dset2");
    model.result("pg5").feature("ptgr2").setIndex("legends", "\u5e26\u5bc6\u5c01\u5708", 0);
    model.result("pg5").run();

    model.study("std1").feature("time").set("useadvanceddisable", true);
    model.study("std1").feature("time")
         .set("disabledphysics", new String[]{"rotbm/las1", "rotbm/las2", "rotbm/las3", "rotbm/las4", "rotbm/las5", "rotbm/las6", "rotbm/las7", "rotbm/las8", "rotbm/las9", "rotbm/las10", 
         "rotbm/las11"});

    model.result("pg3").run();

    model.title("\u53d7\u5bc6\u5c01\u529b\u5f71\u54cd\u7684\u8f6c\u5b50\u54cd\u5e94");

    model
         .description("\u5bc6\u5c01\u4ef6\u4e2d\u7684\u6d41\u4f53\u6d41\u52a8\u4f1a\u4ea7\u751f\u5f3a\u5927\u7684\u6062\u590d\u5f84\u5411\u529b\uff0c\u4e0e\u8f74\u8fd0\u52a8\u76f8\u53cd\u3002\u7531\u4e8e\u5468\u5411\u6d41\u52a8\u7684\u8f74\u5411\u53d8\u5316\uff0c\u8fd8\u5b58\u5728\u4ea4\u53c9\u8026\u5408\u529b\u3002\u7b2c\u4e00\u79cd\u529b\u603b\u662f\u5bf9\u8f6c\u5b50\u6709\u7a33\u5b9a\u4f5c\u7528\uff0c\u800c\u7b2c\u4e8c\u79cd\u529b\u5728\u67d0\u4e9b\u60c5\u51b5\u4e0b\u53ef\u80fd\u4f1a\u5bfc\u81f4\u4e0d\u7a33\u5b9a\u3002\n\n\u672c\u4f8b\u4f7f\u7528\u201c\u6881\u8f6c\u5b50\u201d\u63a5\u53e3\u5bf9\u8f74\u5411\u538b\u7f29\u673a\u8fdb\u884c\u5efa\u6a21\u3002\u8be5\u538b\u7f29\u673a\u6709 10\u00a0\u7ea7\u53f6\u8f6e\uff0c\u6bcf\u4e2a\u53f6\u8f6e\u9644\u8fd1\u90fd\u6709\u4e00\u4e2a\u5bc6\u5c01\u5708\uff0c\u7528\u4e8e\u907f\u514d\u6d41\u4f53\u6e17\u6f0f\u3002\u6b64\u5916\uff0c\u53f6\u8f6e\u7ea7\u7684\u672b\u7aef\u8fd8\u6709\u4e00\u4e2a\u5e73\u8861\u6d3b\u585e\u5bc6\u5c01\u4ef6\u3002\u672c\u4f8b\u7814\u7a76\u5f53\u8f6c\u5b50\u8f6c\u901f\u9010\u6e10\u589e\u5927\u65f6\uff0c\u7cfb\u7edf\u7684\u77ac\u6001\u54cd\u5e94\u3002\u4eff\u771f\u4e2d\u8003\u8651\u4e86\u4e24\u79cd\u60c5\u51b5\uff1a\u6392\u9664\u6240\u6709\u5bc6\u5c01\u4ef6\u548c\u5305\u542b\u6240\u6709\u5bc6\u5c01\u4ef6\u3002\u901a\u8fc7\u5bf9\u8fd9\u4e24\u79cd\u60c5\u51b5\u4e0b\u7684\u7cfb\u7edf\u54cd\u5e94\u8fdb\u884c\u6bd4\u8f83\uff0c\u7ed3\u679c\u8868\u660e\uff0c\u5728\u6709\u5bc6\u5c01\u4ef6\u65f6\uff0c\u7cfb\u7edf\u7684\u7a33\u5b9a\u6027\u66f4\u597d\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("rotor_stability_with_seal.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
