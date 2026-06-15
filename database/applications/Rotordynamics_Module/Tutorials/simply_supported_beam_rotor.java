/*
 * simply_supported_beam_rotor.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:48 by COMSOL 6.3.0.290. */
public class simply_supported_beam_rotor {

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
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("ftplistmethod", "manual");
    model.study("std1").feature("stat").set("solnum", "auto");
    model.study("std1").feature("stat").set("notsolnum", "auto");
    model.study("std1").feature("stat").set("outputmap", new String[]{});
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").setSolveFor("/physics/rotbm", true);
    model.study("std1").create("eig", "Eigenfrequency");
    model.study("std1").feature("eig").set("ftplistmethod", "manual");
    model.study("std1").feature("eig").set("linpsolnum", "auto");
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").set("outputmap", new String[]{});
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").setSolveFor("/physics/rotbm", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Omega", "1000[rpm]", "\u65cb\u8f6c\u901f\u5ea6");
    model.param().set("C1", "130[um]", "\u8f74\u627f\u95f4\u9699 1");
    model.param().set("C2", "160[um]", "\u8f74\u627f\u95f4\u9699 2");
    model.param().set("xb1", "0.075[m]", "\u8f74\u627f\u4f4d\u7f6e 1");
    model.param().set("xb2", "1.75[m]", "\u8f74\u627f\u4f4d\u7f6e 2");
    model.param().set("wb1", "5[cm]", "\u8f74\u627f\u5bbd\u5ea6 1");
    model.param().set("wb2", "10[cm]", "\u8f74\u627f\u5bbd\u5ea6 2");
    model.param().set("mu0", "75[mPa*s]", "\u6d41\u4f53\u9ecf\u5ea6");
    model.param().create("par2");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("x1", "0.05[m]", "\u6bb5\u7ec8\u6b62\u4f4d\u7f6e 1");
    model.param("par2").set("x2", "0.1[m]", "\u6bb5\u7ec8\u6b62\u4f4d\u7f6e 2");
    model.param("par2").set("x3", "1.3[m]", "\u6bb5\u7ec8\u6b62\u4f4d\u7f6e 3");
    model.param("par2").set("x4", "1.7[m]", "\u6bb5\u7ec8\u6b62\u4f4d\u7f6e 4");
    model.param("par2").set("x5", "1.8[m]", "\u6bb5\u7ec8\u6b62\u4f4d\u7f6e 5");
    model.param("par2").set("x6", "2[m]", "\u6bb5\u7ec8\u6b62\u4f4d\u7f6e 6");
    model.param("par2").set("x7", "2.05[m]", "\u6bb5\u7ec8\u6b62\u4f4d\u7f6e 7");
    model.param("par2").set("x8", "2.1[m]", "\u6bb5\u7ec8\u6b62\u4f4d\u7f6e 8");
    model.param("par2").set("D1", "0.05[m]", "\u6bb5\u76f4\u5f84 1");
    model.param("par2").set("D2", "0.07[m]", "\u6bb5\u76f4\u5f84 2");
    model.param("par2").set("D3", "0.13[m]", "\u6bb5\u76f4\u5f84 3");
    model.param("par2").set("D4", "0.11[m]", "\u6bb5\u76f4\u5f84 4");
    model.param("par2").set("D5", "0.13[m]", "\u6bb5\u76f4\u5f84 5");
    model.param("par2").set("D6", "0.11[m]", "\u6bb5\u76f4\u5f84 6");
    model.param("par2").set("D7", "0.08[m]", "\u6bb5\u76f4\u5f84 7");
    model.param("par2").set("D8", "0.05[m]", "\u6bb5\u76f4\u5f84 8");
    model.param().create("par3");

//    To import content from file, use:
//    model.param("par3").loadFile("FILENAME");
    model.param("par3").set("xd1", "(x2+x3)/3", "\u76d8\u4f4d\u7f6e 1");
    model.param("par3").set("xd2", "2*(x2+x3)/3", "\u76d8\u4f4d\u7f6e 2");
    model.param("par3").set("xd3", "(x5+x6)/2", "\u76d8\u4f4d\u7f6e 3");
    model.param("par3").set("Dd1", "0.3[m]", "\u76d8\u76f4\u5f84 1");
    model.param("par3").set("Dd2", "0.3[m]", "\u76d8\u76f4\u5f84 2");
    model.param("par3").set("Dd3", "0.45[m]", "\u76d8\u76f4\u5f84 3");
    model.param("par3").set("wd1", "0.08[m]", "\u76d8\u5bbd\u5ea6 1");
    model.param("par3").set("wd2", "0.08[m]", "\u76d8\u5bbd\u5ea6 2");
    model.param("par3").set("wd3", "0.1[m]", "\u76d8\u5bbd\u5ea6 3");

    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol1").set("x", "0 x1 x2 x3 x4 x5 x6 x7 x8");
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "xb1", 0);
    model.component("comp1").geom("geom1").feature().duplicate("pt2", "pt1");
    model.component("comp1").geom("geom1").feature("pt2").setIndex("p", "xb2", 0);
    model.component("comp1").geom("geom1").feature().duplicate("pt3", "pt2");
    model.component("comp1").geom("geom1").feature("pt3").setIndex("p", "xd1", 0);
    model.component("comp1").geom("geom1").feature().duplicate("pt4", "pt3");
    model.component("comp1").geom("geom1").feature("pt4").setIndex("p", "xd2", 0);
    model.component("comp1").geom("geom1").feature().duplicate("pt5", "pt4");
    model.component("comp1").geom("geom1").feature("pt5").setIndex("p", "xd3", 0);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").func().create("pw1", "Piecewise");
    model.component("comp1").func("pw1").set("funcname", "D");
    model.component("comp1").func("pw1").setIndex("pieces", 0, 0, 0);
    model.component("comp1").func("pw1").setIndex("pieces", "x1", 0, 1);
    model.component("comp1").func("pw1").setIndex("pieces", "D1", 0, 2);
    model.component("comp1").func("pw1").setIndex("pieces", "x1", 1, 0);
    model.component("comp1").func("pw1").setIndex("pieces", "x2", 1, 1);
    model.component("comp1").func("pw1").setIndex("pieces", "D2", 1, 2);
    model.component("comp1").func("pw1").setIndex("pieces", "x2", 2, 0);
    model.component("comp1").func("pw1").setIndex("pieces", "x3", 2, 1);
    model.component("comp1").func("pw1").setIndex("pieces", "D3", 2, 2);
    model.component("comp1").func("pw1").setIndex("pieces", "x3", 3, 0);
    model.component("comp1").func("pw1").setIndex("pieces", "x4", 3, 1);
    model.component("comp1").func("pw1").setIndex("pieces", "D4", 3, 2);
    model.component("comp1").func("pw1").setIndex("pieces", "x4", 4, 0);
    model.component("comp1").func("pw1").setIndex("pieces", "x5", 4, 1);
    model.component("comp1").func("pw1").setIndex("pieces", "D5", 4, 2);
    model.component("comp1").func("pw1").setIndex("pieces", "x5", 5, 0);
    model.component("comp1").func("pw1").setIndex("pieces", "x6", 5, 1);
    model.component("comp1").func("pw1").setIndex("pieces", "D6", 5, 2);
    model.component("comp1").func("pw1").setIndex("pieces", "x6", 6, 0);
    model.component("comp1").func("pw1").setIndex("pieces", "x7", 6, 1);
    model.component("comp1").func("pw1").setIndex("pieces", "D7", 6, 2);
    model.component("comp1").func("pw1").setIndex("pieces", "x7", 7, 0);
    model.component("comp1").func("pw1").setIndex("pieces", "x8", 7, 1);
    model.component("comp1").func("pw1").setIndex("pieces", "D8", 7, 2);
    model.component("comp1").func("pw1").set("argunit", "m");
    model.component("comp1").func("pw1").set("fununit", "m");

    model.component("comp1").selection().create("ball1", "Ball");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("ball1").set("entitydim", 0);
    model.component("comp1").selection("ball1").set("posx", "xb1");
    model.component("comp1").selection().duplicate("ball2", "ball1");
    model.component("comp1").selection("ball2").set("posx", "xb2");
    model.component("comp1").selection().duplicate("ball3", "ball2");
    model.component("comp1").selection("ball3").set("posx", "xd1");
    model.component("comp1").selection().duplicate("ball4", "ball3");
    model.component("comp1").selection("ball4").set("posx", "xd2");
    model.component("comp1").selection().duplicate("ball5", "ball4");
    model.component("comp1").selection("ball5").set("posx", "xd3");

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

    model.component("comp1").physics("rotbm").prop("RotorProperties").set("freqr", "Omega");
    model.component("comp1").physics("rotbm").feature("rcs1").set("do_circ", "D(X)");
    model.component("comp1").physics("rotbm").create("jrb1", "JournalBearing", 0);
    model.component("comp1").physics("rotbm").feature("jrb1").selection().named("ball1");
    model.component("comp1").physics("rotbm").feature("jrb1").set("BearingModel", "PlainHydrodynamic");
    model.component("comp1").physics("rotbm").feature("jrb1").set("mure_mat", "userdef");
    model.component("comp1").physics("rotbm").feature("jrb1").set("mure", "mu0");
    model.component("comp1").physics("rotbm").feature("jrb1").set("C", "C1");
    model.component("comp1").physics("rotbm").feature("jrb1").set("R", "rotbm.re");
    model.component("comp1").physics("rotbm").feature("jrb1").set("L", "wb1");
    model.component("comp1").physics("rotbm").feature("jrb1").set("includeBendingStiffness", false);
    model.component("comp1").physics("rotbm").feature().duplicate("jrb2", "jrb1");
    model.component("comp1").physics("rotbm").feature("jrb2").selection().named("ball2");
    model.component("comp1").physics("rotbm").feature("jrb2").set("C", "C2");
    model.component("comp1").physics("rotbm").feature("jrb2").set("L", "wb2");
    model.component("comp1").physics("rotbm").create("disk1", "Disk", 0);
    model.component("comp1").physics("rotbm").feature("disk1").selection().named("ball3");
    model.component("comp1").physics("rotbm").feature("disk1").set("SpecifiedBy", "GeomDim");
    model.component("comp1").physics("rotbm").feature("disk1").set("rho", "mat1.def.rho");
    model.component("comp1").physics("rotbm").feature("disk1").set("d", "Dd1");
    model.component("comp1").physics("rotbm").feature("disk1").set("h", "wd1");
    model.component("comp1").physics("rotbm").feature().duplicate("disk2", "disk1");
    model.component("comp1").physics("rotbm").feature("disk2").selection().named("ball4");
    model.component("comp1").physics("rotbm").feature("disk2").set("d", "Dd2");
    model.component("comp1").physics("rotbm").feature("disk2").set("h", "wd2");
    model.component("comp1").physics("rotbm").feature().duplicate("disk3", "disk2");
    model.component("comp1").physics("rotbm").feature("disk3").selection().named("ball5");
    model.component("comp1").physics("rotbm").feature("disk3").set("d", "Dd3");
    model.component("comp1").physics("rotbm").feature("disk3").set("h", "wd3");
    model.component("comp1").physics("rotbm").create("gr1", "Gravity", 1);

    model.component("comp1").mesh("mesh1").autoMeshSize(3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "C1", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "C1", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "Omega", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(200,200,6600)", 0);
    model.study("std1").feature("param").setIndex("punit", "rpm", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std1");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset3");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").setIndex("looplevel", 33, 1);
    model.result("pg1").label("\u6da1\u52a8 (rotbm)");
    model.result("pg1").create("wp1", "Whirl");
    model.result("pg1").feature("wp1").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("wp1").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").feature("wp1").set("nplanes", "1");
    model.result("pg1").feature("wp1").set("nrings", "10");
    model.result("pg1").feature("wp1").set("colortable", "TrafficLight");
    model.result("pg1").feature("wp1").set("ringcolor", "blue");
    model.result("pg1").feature("wp1").selection().geom("geom1", 1);
    model.result("pg1").feature("wp1").selection().set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
    model.result("pg1").feature("wp1").selection().inherit(false);
    model.result("pg1").feature("wp1").selection().embedded(false);
    model.result().evaluationGroup().create("std1EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std1EvgFrq").set("data", "dset3");
    model.result().evaluationGroup("std1EvgFrq").label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 1)");
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
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("expr", new String[]{"1"});
    model.result("pg1").feature("line1").set("linetype", "tube");
    model.result("pg1").feature("line1").set("radiusexpr", new String[]{"rotbm.re "});
    model.result("pg1").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg1").feature("line1").set("tuberadiusscale", 1);
    model.result("pg1").feature("line1").set("tubeendcaps", false);
    model.result("pg1").feature("line1").set("coloring", "uniform");
    model.result("pg1").feature("line1").set("color", "custom");
    model.result("pg1").feature("line1")
         .set("customcolor", new double[]{0.9803921580314636, 0.7843137383460999, 0.7058823704719543});
    model.result("pg1").feature("line1").set("threshold", "manual");
    model.result("pg1").feature("line1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("line1").set("titletype", "none");
    model.result("pg1").feature("line1").label("\u8f6c\u5b50");
    model.result("pg1").feature("line1").create("def", "Deform");
    model.result("pg1").feature("line1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("line1").feature("def").set("scale", "1");
    model.result("pg1").feature("line1").feature("def")
         .set("expr", new String[]{"-rotbm.De_max*rotbm.e30x ", "-rotbm.De_max*rotbm.e30y ", "-rotbm.De_max*rotbm.e30z "});
    model.result("pg1").create("pttraj1", "PointTrajectories");
    model.result("pg1").feature("pttraj1").set("plotdata", "points");
    model.result("pg1").feature("pttraj1").selection().geom("geom1", 0);
    model.result("pg1").feature("pttraj1").selection().set(3);
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

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg1").feature("pttraj1")
         .set("custompointcolor", new double[]{0.5882353186607361, 0.8627451062202454, 0.5882353186607361});
    model.result("pg1").feature("pttraj1").set("titletype", "none");
    model.result("pg1").feature("pttraj1").label("\u8f74\u9888\u8f74\u627f 1");
    model.result("pg1").feature("pttraj1").create("def", "Deform");
    model.result("pg1").feature("pttraj1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("pttraj1").feature("def").set("scale", "1");
    model.result("pg1").feature("pttraj1").feature("def")
         .set("expr", new String[]{"-rotbm.De_max*rotbm.e30x ", "-rotbm.De_max*rotbm.e30y ", "-rotbm.De_max*rotbm.e30z "});
    model.result("pg1").create("pttraj2", "PointTrajectories");
    model.result("pg1").feature("pttraj2").set("plotdata", "points");
    model.result("pg1").feature("pttraj2").selection().geom("geom1", 0);
    model.result("pg1").feature("pttraj2").selection().set(9);
    model.result("pg1").feature("pttraj2").selection().inherit(false);
    model.result("pg1").feature("pttraj2").selection().embedded(false);
    model.result("pg1").feature("pttraj2").set("linetype", "none");
    model.result("pg1").feature("pttraj2").set("pointtype", "arrow");
    model.result("pg1").feature("pttraj2")
         .set("expr", new String[]{"X-1.0*rotbm.re*rotbm.jrb2.e3gx ", "Y-1.0*rotbm.re*rotbm.jrb2.e3gy ", "Z-1.0*rotbm.re*rotbm.jrb2.e3gz "});
    model.result("pg1").feature("pttraj2")
         .set("arrowexpr", new String[]{"rotbm.re*rotbm.jrb2.e3gx ", "rotbm.re*rotbm.jrb2.e3gy ", "rotbm.re*rotbm.jrb2.e3gz "});
    model.result("pg1").feature("pttraj2").set("arrowtype", "arrowhead");
    model.result("pg1").feature("pttraj2").set("arrowbase", "head");
    model.result("pg1").feature("pttraj2").set("arrowscale", "10");
    model.result("pg1").feature("pttraj2").set("arrowscaleactive", true);
    model.result("pg1").feature("pttraj2").set("pointcolor", "custom");
    model.result("pg1").feature("pttraj2")
         .set("custompointcolor", new double[]{0.5882353186607361, 0.8627451062202454, 0.5882353186607361});
    model.result("pg1").feature("pttraj2").set("titletype", "none");
    model.result("pg1").feature("pttraj2").label("\u8f74\u9888\u8f74\u627f 2");
    model.result("pg1").feature("pttraj2").create("def", "Deform");
    model.result("pg1").feature("pttraj2").feature("def").set("scaleactive", true);
    model.result("pg1").feature("pttraj2").feature("def").set("scale", "1");
    model.result("pg1").feature("pttraj2").feature("def")
         .set("expr", new String[]{"-rotbm.De_max*rotbm.e30x ", "-rotbm.De_max*rotbm.e30y ", "-rotbm.De_max*rotbm.e30z "});
    model.result("pg1").create("pttraj3", "PointTrajectories");
    model.result("pg1").feature("pttraj3").set("plotdata", "points");
    model.result("pg1").feature("pttraj3").selection().geom("geom1", 0);
    model.result("pg1").feature("pttraj3").selection().set(5);
    model.result("pg1").feature("pttraj3").selection().inherit(false);
    model.result("pg1").feature("pttraj3").selection().embedded(false);
    model.result("pg1").feature("pttraj3").set("linetype", "none");
    model.result("pg1").feature("pttraj3").set("expr", new String[]{"X", "Y", "Z"});
    model.result("pg1").feature("pttraj3").set("pointtype", "ellipse");
    model.result("pg1").feature("pttraj3").set("pointcolor", "custom");
    model.result("pg1").feature("pttraj3")
         .set("custompointcolor", new double[]{0.8039215803146362, 0.5215686559677124, 0.24705882370471954});
    model.result("pg1").feature("pttraj3")
         .set("semimajorexpr", new String[]{"0.5*rotbm.disk1.de*rotbm.e20x ", "0.5*rotbm.disk1.de*rotbm.e20y ", "0.5*rotbm.disk1.de*rotbm.e20z "});
    model.result("pg1").feature("pttraj3")
         .set("semiminorexpr", new String[]{"0.5*rotbm.disk1.de*rotbm.e30x ", "0.5*rotbm.disk1.de*rotbm.e30y ", "0.5*rotbm.disk1.de*rotbm.e30z "});
    model.result("pg1").feature("pttraj3").set("ellipsecount", 1);
    model.result("pg1").feature("pttraj3").set("ellipsearrowscaleactive", true);
    model.result("pg1").feature("pttraj3").set("ellipsearrowtype", "none");
    model.result("pg1").feature("pttraj3").set("titletype", "none");
    model.result("pg1").feature("pttraj3").label("\u5706\u76d8 1");
    model.result("pg1").feature("pttraj3").create("def", "Deform");
    model.result("pg1").feature("pttraj3").feature("def").set("scaleactive", true);
    model.result("pg1").feature("pttraj3").feature("def").set("scale", "1");
    model.result("pg1").feature("pttraj3").feature("def")
         .set("expr", new String[]{"-rotbm.De_max*rotbm.e30x ", "-rotbm.De_max*rotbm.e30y ", "-rotbm.De_max*rotbm.e30z "});
    model.result("pg1").create("pttraj4", "PointTrajectories");
    model.result("pg1").feature("pttraj4").set("plotdata", "points");
    model.result("pg1").feature("pttraj4").selection().geom("geom1", 0);
    model.result("pg1").feature("pttraj4").selection().set(6);
    model.result("pg1").feature("pttraj4").selection().inherit(false);
    model.result("pg1").feature("pttraj4").selection().embedded(false);
    model.result("pg1").feature("pttraj4").set("linetype", "none");
    model.result("pg1").feature("pttraj4").set("expr", new String[]{"X", "Y", "Z"});
    model.result("pg1").feature("pttraj4").set("pointtype", "ellipse");
    model.result("pg1").feature("pttraj4").set("pointcolor", "custom");
    model.result("pg1").feature("pttraj4")
         .set("custompointcolor", new double[]{0.8039215803146362, 0.5215686559677124, 0.24705882370471954});
    model.result("pg1").feature("pttraj4")
         .set("semimajorexpr", new String[]{"0.5*rotbm.disk2.de*rotbm.e20x ", "0.5*rotbm.disk2.de*rotbm.e20y ", "0.5*rotbm.disk2.de*rotbm.e20z "});
    model.result("pg1").feature("pttraj4")
         .set("semiminorexpr", new String[]{"0.5*rotbm.disk2.de*rotbm.e30x ", "0.5*rotbm.disk2.de*rotbm.e30y ", "0.5*rotbm.disk2.de*rotbm.e30z "});
    model.result("pg1").feature("pttraj4").set("ellipsecount", 1);
    model.result("pg1").feature("pttraj4").set("ellipsearrowscaleactive", true);
    model.result("pg1").feature("pttraj4").set("ellipsearrowtype", "none");
    model.result("pg1").feature("pttraj4").set("titletype", "none");
    model.result("pg1").feature("pttraj4").label("\u5706\u76d8 2");
    model.result("pg1").feature("pttraj4").create("def", "Deform");
    model.result("pg1").feature("pttraj4").feature("def").set("scaleactive", true);
    model.result("pg1").feature("pttraj4").feature("def").set("scale", "1");
    model.result("pg1").feature("pttraj4").feature("def")
         .set("expr", new String[]{"-rotbm.De_max*rotbm.e30x ", "-rotbm.De_max*rotbm.e30y ", "-rotbm.De_max*rotbm.e30z "});
    model.result("pg1").create("pttraj5", "PointTrajectories");
    model.result("pg1").feature("pttraj5").set("plotdata", "points");
    model.result("pg1").feature("pttraj5").selection().geom("geom1", 0);
    model.result("pg1").feature("pttraj5").selection().set(11);
    model.result("pg1").feature("pttraj5").selection().inherit(false);
    model.result("pg1").feature("pttraj5").selection().embedded(false);
    model.result("pg1").feature("pttraj5").set("linetype", "none");
    model.result("pg1").feature("pttraj5").set("expr", new String[]{"X", "Y", "Z"});
    model.result("pg1").feature("pttraj5").set("pointtype", "ellipse");
    model.result("pg1").feature("pttraj5").set("pointcolor", "custom");
    model.result("pg1").feature("pttraj5")
         .set("custompointcolor", new double[]{0.8039215803146362, 0.5215686559677124, 0.24705882370471954});
    model.result("pg1").feature("pttraj5")
         .set("semimajorexpr", new String[]{"0.5*rotbm.disk3.de*rotbm.e20x ", "0.5*rotbm.disk3.de*rotbm.e20y ", "0.5*rotbm.disk3.de*rotbm.e20z "});
    model.result("pg1").feature("pttraj5")
         .set("semiminorexpr", new String[]{"0.5*rotbm.disk3.de*rotbm.e30x ", "0.5*rotbm.disk3.de*rotbm.e30y ", "0.5*rotbm.disk3.de*rotbm.e30z "});
    model.result("pg1").feature("pttraj5").set("ellipsecount", 1);
    model.result("pg1").feature("pttraj5").set("ellipsearrowscaleactive", true);
    model.result("pg1").feature("pttraj5").set("ellipsearrowtype", "none");
    model.result("pg1").feature("pttraj5").set("titletype", "none");
    model.result("pg1").feature("pttraj5").label("\u5706\u76d8 3");
    model.result("pg1").feature("pttraj5").create("def", "Deform");
    model.result("pg1").feature("pttraj5").feature("def").set("scaleactive", true);
    model.result("pg1").feature("pttraj5").feature("def").set("scale", "1");
    model.result("pg1").feature("pttraj5").feature("def")
         .set("expr", new String[]{"-rotbm.De_max*rotbm.e30x ", "-rotbm.De_max*rotbm.e30y ", "-rotbm.De_max*rotbm.e30z "});
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{1, 5});
    model.result("pg1").set("titletype", "label");
    model.result("pg1").set("plotarrayenable", true);
    model.result("pg1").set("arrayaxis", "y");
    model.result("pg1").run();
    model.result("pg1").feature("line1").feature().remove("def");
    model.result("pg1").feature("line1").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("line1").set("manualindexing", true);
    model.result("pg1").run();
    model.result("pg1").feature("pttraj1").feature().remove("def");
    model.result("pg1").feature("pttraj1").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("pttraj1").set("manualindexing", true);
    model.result("pg1").run();
    model.result("pg1").feature("pttraj2").feature().remove("def");
    model.result("pg1").feature("pttraj2").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("pttraj2").set("manualindexing", true);
    model.result("pg1").run();
    model.result("pg1").feature("pttraj3").feature().remove("def");
    model.result("pg1").feature("pttraj3").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("pttraj3").set("manualindexing", true);
    model.result("pg1").run();
    model.result("pg1").feature("pttraj4").feature().remove("def");
    model.result("pg1").feature("pttraj4").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("pttraj4").set("manualindexing", true);
    model.result("pg1").run();
    model.result("pg1").feature("pttraj5").feature().remove("def");
    model.result("pg1").feature("pttraj5").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("pttraj5").set("manualindexing", true);
    model.result("pg1").feature("wp1").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("wp1").set("manualindexing", true);
    model.result("pg1").feature("wp1").set("arrayindex", 1);
    model.result("pg1").feature().duplicate("wp2", "wp1");
    model.result("pg1").feature("wp2").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("wp2").set("data", "dset3");
    model.result("pg1").feature("wp2").set("looplevel", new int[]{2, 5});
    model.result("pg1").feature("wp2").set("arrayindex", 2);
    model.result("pg1").feature().duplicate("wp3", "wp2");
    model.result("pg1").feature("wp3").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("wp3").set("looplevel", new int[]{3, 5});
    model.result("pg1").feature("wp3").set("arrayindex", 3);
    model.result("pg1").feature().duplicate("wp4", "wp3");
    model.result("pg1").feature("wp4").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("wp4").set("looplevel", new int[]{4, 5});
    model.result("pg1").feature("wp4").set("arrayindex", 4);
    model.result("pg1").feature().duplicate("wp5", "wp4");
    model.result("pg1").feature("wp5").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("wp5").set("looplevel", new int[]{5, 5});
    model.result("pg1").feature("wp5").set("arrayindex", 5);
    model.result("pg1").feature().duplicate("wp6", "wp5");
    model.result("pg1").feature("wp6").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("wp6").set("looplevel", new int[]{6, 5});
    model.result("pg1").feature("wp6").set("arrayindex", 6);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("ann1", "Annotation");
    model.result("pg1").feature("ann1").set("arraydim", "1");
    model.result("pg1").feature("ann1").set("posxexpr", "- 0.2[m]");
    model.result("pg1").feature("ann1").set("showpoint", false);
    model.result("pg1").feature("ann1").set("anchorpoint", "center");
    model.result("pg1").feature("ann1").set("manualindexing", true);
    model.result("pg1").feature("ann1").set("arrayindex", 1);
    model.result("pg1").feature().duplicate("ann2", "ann1");
    model.result("pg1").feature("ann2").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("ann2").set("arrayindex", 2);
    model.result("pg1").feature().duplicate("ann3", "ann2");
    model.result("pg1").feature("ann3").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("ann3").set("arrayindex", 3);
    model.result("pg1").feature().duplicate("ann4", "ann3");
    model.result("pg1").feature("ann4").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("ann4").set("arrayindex", 4);
    model.result("pg1").feature().duplicate("ann5", "ann4");
    model.result("pg1").feature("ann5").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("ann5").set("arrayindex", 5);
    model.result("pg1").feature().duplicate("ann6", "ann5");
    model.result("pg1").feature("ann6").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("ann6").set("arrayindex", 6);
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").create("lnsg1", "LineSegments");
    model.result("pg2").feature("lnsg1").set("data", "dset3");
    model.result("pg2").feature("lnsg1").set("looplevelinput", new String[][]{{"last", "last"}});
    model.result("pg2").feature("lnsg1").set("xexpr", new String[]{"0[Hz]", "min(real(freq),rotbm.freqr)"});
    model.result("pg2").feature("lnsg1").set("xunit", new String[]{"Hz", "Hz"});
    model.result("pg2").feature("lnsg1").set("xdescr", new String[]{"\u539f\u70b9", "\u89d2\u901f\u5ea6"});
    model.result("pg2").feature("lnsg1").set("yexpr", new String[]{"0[Hz]", "min(real(freq),rotbm.freqr)"});
    model.result("pg2").feature("lnsg1").set("yunit", new String[]{"Hz", "Hz"});
    model.result("pg2").feature("lnsg1").set("ydescr", new String[]{"\u539f\u70b9", "\u89d2\u901f\u5ea6"});
    model.result("pg2").feature("lnsg1").set("linestyle", "cycle");
    model.result("pg2").feature("lnsg1").set("linecolor", "black");
    model.result("pg2").feature("lnsg1").set("linewidth", 1);
    model.result("pg2").feature("lnsg1").set("legend", true);
    model.result("pg2").feature("lnsg1").set("legendmethod", "manual");
    model.result("pg2").feature("lnsg1").set("legends", "1\\times\\Omega");
    model.result("pg2").feature("lnsg1").label("\u8f85\u52a9\u7ebf (1\u00d7\u03a9)");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("expr", "real(freq)");
    model.result("pg2").feature("glob1").set("descr", "\u963b\u5c3c\u56fa\u6709\u9891\u7387");
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").feature("glob1").set("xdataexpr", "rotbm.freqr");
    model.result("pg2").feature("glob1").set("xdataunit", "Hz");
    model.result("pg2").feature("glob1").set("xdatadescr", "\u65cb\u8f6c\u901f\u5ea6");
    model.result("pg2").feature("glob1").set("linestyle", "solid");
    model.result("pg2").feature("glob1").set("linecolor", "cycle");
    model.result("pg2").feature("glob1").set("linewidth", 2);
    model.result("pg2").feature("glob1").set("legend", false);
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "outer");
    model.result("pg2").feature("glob1").label("\u56fa\u6709\u9891\u7387");
    model.result("pg2").feature("glob1").create("col1", "Color");
    model.result("pg2").feature("glob1").feature("col1").set("expr", "rotbm.i_sd");
    model.result("pg2").feature("glob1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("glob1").feature("col1").label("\u65b9\u5411\u6027");
    model.result("pg2").feature("glob1").feature("col1").set("colortable", "Spectrum");
    model.result("pg2").feature("glob1").feature("col1").set("rangecoloractive", true);
    model.result("pg2").feature("glob1").feature("col1").set("rangecolormin", -1.3);
    model.result("pg2").feature("glob1").feature("col1").set("rangecolormax", 1.3);
    model.result("pg2").feature("glob1").feature("col1").set("colorscalemode", "linearsymmetric");
    model.result("pg2").feature("glob1").create("gmrk1", "GraphMarker");
    model.result("pg2").feature("glob1").feature("gmrk1").set("displaymode", "intersection");
    model.result("pg2").feature("glob1").feature("gmrk1").set("intersectionline", "general");
    model.result("pg2").feature("glob1").feature("gmrk1").set("generalcoeffc", 0);
    model.result("pg2").feature("glob1").feature("gmrk1").set("generalcoeffa", " -1");
    model.result("pg2").feature("glob1").feature("gmrk1").set("generalcoeffb", 1);
    model.result("pg2").feature("glob1").feature("gmrk1").set("includexcoord", false);
    model.result("pg2").feature("glob1").feature("gmrk1").set("includeycoord", false);
    model.result("pg2").feature("glob1").feature("gmrk1").set("precision", 3);
    model.result("pg2").feature("glob1").feature("gmrk1").set("pointradius", 2.5);
    model.result("pg2").feature("glob1").feature("gmrk1").set("anchorpoint", "lowermiddle");
    model.result("pg2").feature("glob1").feature("gmrk1").label("\u4ea4\u96c6 (1\u00d7\u03a9)");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u574e\u8d1d\u5c14\u56fe");
    model.result("pg2").label("\u574e\u8d1d\u5c14\u56fe (rotbm)");
    model.result("pg2").label("\u574e\u8d1d\u5c14\u56fe (rotbm)");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("lnsg1").setIndex("xunit", "rpm", 0);
    model.result("pg2").feature("lnsg1").setIndex("xunit", "rpm", 1);
    model.result("pg2").run();
    model.result("pg2").feature("glob1").set("xdataunit", "rpm");
    model.result("pg2").run();
    model.result("pg2").feature("glob1").feature("gmrk1").set("generalcoeffb", 60);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("unit", new String[]{""});
    model.result("pg3").feature("glob1").set("expr", new String[]{"imag(rotbm.freq)/abs(rotbm.freq)"});
    model.result("pg3").feature("glob1").set("descr", "\u963b\u5c3c\u6bd4");
    model.result("pg3").feature("glob1").set("xdatasolnumtype", "outer");
    model.result("pg3").feature("glob1").set("linestyle", "solid");
    model.result("pg3").feature("glob1").set("linecolor", "cycle");
    model.result("pg3").feature("glob1").set("linewidth", 2);
    model.result("pg3").feature("glob1").set("legend", false);
    model.result("pg3").feature("glob1").label("\u963b\u5c3c\u6bd4");
    model.result("pg3").feature("glob1").create("col1", "Color");
    model.result("pg3").feature("glob1").feature("col1").set("expr", "(imag(freq)>0) - (imag(freq)<0)");
    model.result("pg3").feature("glob1").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("glob1").feature("col1").label("\u7a33\u5b9a\u6027\u6307\u793a\u5668");
    model.result("pg3").feature("glob1").feature("col1").set("colortable", "TrafficClassic");
    model.result("pg3").feature("glob1").feature("col1").set("rangecoloractive", true);
    model.result("pg3").feature("glob1").feature("col1").set("rangecolormin", -0.1);
    model.result("pg3").feature("glob1").feature("col1").set("rangecolormax", 0.1);
    model.result("pg3").feature("glob1").feature("col1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").feature("glob1").feature("col1").set("colortabletrans", "reverse");
    model.result("pg3").feature("glob1").feature("col1").set("descractive", true);
    model.result("pg3").feature("glob1").feature("col1").set("unit", "1");
    model.result("pg3").feature("glob1").feature("col1").set("descr", "\u7a33\u5b9a\u6027\u6307\u793a\u5668");
    model.result("pg3").feature("glob1").create("gmrk1", "GraphMarker");
    model.result("pg3").feature("glob1").feature("gmrk1").set("displaymode", "intersection");
    model.result("pg3").feature("glob1").feature("gmrk1").set("intersectionline", "general");
    model.result("pg3").feature("glob1").feature("gmrk1").set("generalcoeffc", 0);
    model.result("pg3").feature("glob1").feature("gmrk1").set("generalcoeffa", "0");
    model.result("pg3").feature("glob1").feature("gmrk1").set("generalcoeffb", 1);
    model.result("pg3").feature("glob1").feature("gmrk1").set("includexcoord", false);
    model.result("pg3").feature("glob1").feature("gmrk1").set("includeycoord", false);
    model.result("pg3").feature("glob1").feature("gmrk1").set("precision", 3);
    model.result("pg3").feature("glob1").feature("gmrk1").set("pointradius", 2.5);
    model.result("pg3").feature("glob1").feature("gmrk1").set("anchorpoint", "lowermiddle");
    model.result("pg3").feature("glob1").feature("gmrk1").label("\u4ea4\u96c6 (\u03b6=0)");
    model.result("pg3").feature("glob1").feature("gmrk1").set("showlines", true);
    model.result("pg3").feature("glob1").feature("gmrk1").set("linestyle", "dashed");
    model.result("pg3").label("\u7a33\u5b9a\u6027\u56fe (rotbm)");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u7a33\u5b9a\u6027\u56fe");
    model.result("pg3").label("\u7a33\u5b9a\u6027\u56fe (rotbm)");
    model.result("pg3").run();
    model.result("pg1").run();

    model.title("\u7b80\u652f\u6881\u8f6c\u5b50");

    model
         .description("\u672c\u6559\u5b66\u6a21\u578b\u6f14\u793a\u5982\u4f55\u4e3a\u5177\u6709\u591a\u4e2a\u5706\u76d8\u548c\u8f74\u627f\u7684\u8f6c\u5b50\u8bbe\u7f6e\u7279\u5f81\u9891\u7387\u5206\u6790\uff0c\u8bf4\u660e\u4e86\u5982\u4f55\u5229\u7528\u574e\u8d1d\u5c14\u56fe\u548c\u7a33\u5b9a\u6027\u56fe\u6765\u786e\u5b9a\u4e34\u754c\u8f6c\u901f\u548c\u7a33\u5b9a\u6027\u9608\u503c\u3002\n\n\u5176\u4e2d\u4f7f\u7528 COMSOL Multiphysics\u00ae \u8f6f\u4ef6\u9644\u52a0\u7684\u201c\u8f6c\u5b50\u52a8\u529b\u5b66\u6a21\u5757\u201d\u4e2d\u7684\u201c\u6881\u8f6c\u5b50\u201d\u63a5\u53e3\u5bf9\u8f6c\u5b50\u8fdb\u884c\u5efa\u6a21\uff0c\u5e76\u4f7f\u7528\u201c\u5706\u76d8\u201d\u7279\u5f81\u5bf9\u5706\u76d8\u8fdb\u884c\u5efa\u6a21\u3002\u8f6c\u5b50\u7531\u4e24\u4e2a\u5706\u67f1\u5f62\u6db2\u4f53\u52a8\u538b\u8f74\u627f\u652f\u6491\uff0c\u540e\u8005\u901a\u8fc7\u63a5\u53e3\u4e2d\u63d0\u4f9b\u7684\u201c\u8f74\u9888\u8f74\u627f\u201d\u7279\u5f81\u8fdb\u884c\u6a21\u62df\u3002");

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

    model.label("simply_supported_beam_rotor.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
