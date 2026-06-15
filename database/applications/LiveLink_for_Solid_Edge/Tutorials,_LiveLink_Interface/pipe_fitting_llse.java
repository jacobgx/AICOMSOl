/*
 * pipe_fitting_llse.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:50 by COMSOL 6.3.0.290. */
public class pipe_fitting_llse {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\LiveLink_for_Solid_Edge\\Tutorials,_LiveLink_Interface");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1").create("cad1", "LiveLinkSolidEdge");
    model.component("comp1").geom("geom1").feature("cad1").updateCadParamTable(false, false);
    model.component("comp1").geom("geom1").feature("cad1").importData();

    model.component("comp1").view("view1").set("transparency", false);

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "zx");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 2);
    model.component("comp2").geom("geom2").axisymmetric(true);

    model.component("comp2").mesh().create("mesh2");

    model.component("comp2").geom("geom2").lengthUnit("mm");
    model.component("comp2").geom("geom2").create("cro1", "CrossSection");
    model.component("comp2").geom("geom2").feature("cro1").set("selfrom3d", true);
    model.component("comp2").geom("geom2").run("cro1");
    model.component("comp2").geom("geom2").create("uni1", "Union");
    model.component("comp2").geom("geom2").feature("uni1").selection("input").named("cro1_cad1_Male_fitting");
    model.component("comp2").geom("geom2").feature("fin").set("action", "assembly");
    model.component("comp2").geom("geom2").feature("fin").set("createpairs", false);
    model.component("comp2").geom("geom2").run("fin");

    model.component("comp2").view("view3").axis().set("xmin", -4);
    model.component("comp2").view("view3").axis().set("xmax", 6.5);
    model.component("comp2").view("view3").axis().set("ymin", -4);
    model.component("comp2").view("view3").axis().set("ymax", 4);

    model.component("comp2").material().create("mat1", "Common");
    model.component("comp2").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp2").material("mat1").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.component("comp2").material("mat1").propertyGroup("Enu").func().create("int2", "Interpolation");
    model.component("comp2").material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp2").material("mat1").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic material model");
    model.component("comp2").material("mat1").propertyGroup("ElastoplasticModel").func()
         .create("int1", "Interpolation");
    model.component("comp2").material("mat1").propertyGroup().create("Ludwik", "Ludwik", "Ludwik");
    model.component("comp2").material("mat1").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.component("comp2").material("mat1").propertyGroup().create("JohnsonCook", "JohnsonCook", "Johnson-Cook");
    model.component("comp2").material("mat1").propertyGroup().create("Swift", "Swift", "Swift");
    model.component("comp2").material("mat1").propertyGroup().create("Voce", "Voce", "Voce");
    model.component("comp2").material("mat1").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.component("comp2").material("mat1").propertyGroup()
         .create("HockettSherby", "HockettSherby", "Hockett-Sherby");
    model.component("comp2").material("mat1").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.component("comp2").material("mat1").propertyGroup()
         .create("ArmstrongFrederick", "ArmstrongFrederick", "Armstrong-Frederick");
    model.component("comp2").material("mat1").propertyGroup("ArmstrongFrederick").func()
         .create("int1", "Interpolation");
    model.component("comp2").material("mat1").propertyGroup().create("Norton", "Norton", "Norton");
    model.component("comp2").material("mat1").propertyGroup()
         .create("Garofalo", "Garofalo", "Garofalo (hyperbolic sine)");
    model.component("comp2").material("mat1").propertyGroup()
         .create("ChabocheViscoplasticity", "ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.component("comp2").material("mat1").label("Structural steel");
    model.component("comp2").material("mat1").set("family", "custom");
    model.component("comp2").material("mat1")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp2").material("mat1").set("diffuse", "custom");
    model.component("comp2").material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp2").material("mat1").set("ambient", "custom");
    model.component("comp2").material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp2").material("mat1").set("noise", true);
    model.component("comp2").material("mat1").set("fresnel", 0.9);
    model.component("comp2").material("mat1").set("roughness", 0.3);
    model.component("comp2").material("mat1").set("diffusewrap", 0);
    model.component("comp2").material("mat1").set("reflectance", 0);
    model.component("comp2").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp2").material("mat1").propertyGroup("def").set("lossfactor", "0.02");
    model.component("comp2").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat1").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp2").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp2").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp2").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp2").material("mat1").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp2").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp2").material("mat1").propertyGroup("Enu").func("int1").label("Interpolation 1");
    model.component("comp2").material("mat1").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.component("comp2").material("mat1").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.component("comp2").material("mat1").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.component("comp2").material("mat1").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});
    model.component("comp2").material("mat1").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat1").propertyGroup("Enu").func("int2").label("Interpolation 2");
    model.component("comp2").material("mat1").propertyGroup("Enu").func("int2")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp2").material("mat1").propertyGroup("Enu").func("int2").set("funcname", "nu");
    model.component("comp2").material("mat1").propertyGroup("Enu").func("int2")
         .set("table", new String[][]{{"293.15", "0.30"}, {"793.15", "0.315"}});
    model.component("comp2").material("mat1").propertyGroup("Enu").func("int2").set("extrap", "linear");
    model.component("comp2").material("mat1").propertyGroup("Enu").func("int2").set("fununit", new String[]{"1"});
    model.component("comp2").material("mat1").propertyGroup("Enu").func("int2").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat1").propertyGroup("Enu").set("E", "E(T)");
    model.component("comp2").material("mat1").propertyGroup("Enu").set("nu", "nu(T)");
    model.component("comp2").material("mat1").propertyGroup("Enu").addInput("temperature");
    model.component("comp2").material("mat1").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.component("comp2").material("mat1").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.component("comp2").material("mat1").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.component("comp2").material("mat1").propertyGroup("ElastoplasticModel")
         .label("Elastoplastic material model");
    model.component("comp2").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .label("Interpolation 1");
    model.component("comp2").material("mat1").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "a");
    model.component("comp2").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp2").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp2").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp2").material("mat1").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.component("comp2").material("mat1").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.component("comp2").material("mat1").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.component("comp2").material("mat1").propertyGroup("ElastoplasticModel")
         .set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.component("comp2").material("mat1").propertyGroup("ElastoplasticModel")
         .set("Hillcoefficients", new String[]{"0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]"});
    model.component("comp2").material("mat1").propertyGroup("ElastoplasticModel")
         .set("ys", new String[]{"0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]"});
    model.component("comp2").material("mat1").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.component("comp2").material("mat1").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.component("comp2").material("mat1").propertyGroup("Ludwik").func("int1").label("Interpolation 1");
    model.component("comp2").material("mat1").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.component("comp2").material("mat1").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp2").material("mat1").propertyGroup("Ludwik").func("int1").set("fununit", new String[]{"1"});
    model.component("comp2").material("mat1").propertyGroup("Ludwik").func("int1").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat1").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.component("comp2").material("mat1").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.component("comp2").material("mat1").propertyGroup("Ludwik").addInput("temperature");
    model.component("comp2").material("mat1").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.component("comp2").material("mat1").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.component("comp2").material("mat1").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.component("comp2").material("mat1").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.component("comp2").material("mat1").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.component("comp2").material("mat1").propertyGroup("Swift").set("e0_swi", "0.021");
    model.component("comp2").material("mat1").propertyGroup("Swift").set("n_swi", "0.2");
    model.component("comp2").material("mat1").propertyGroup("Voce").func("int1").label("Interpolation 1");
    model.component("comp2").material("mat1").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.component("comp2").material("mat1").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp2").material("mat1").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.component("comp2").material("mat1").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat1").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.component("comp2").material("mat1").propertyGroup("Voce").set("beta_voc", "9.3");
    model.component("comp2").material("mat1").propertyGroup("Voce").addInput("temperature");
    model.component("comp2").material("mat1").propertyGroup("HockettSherby").func("int1").label("Interpolation 1");
    model.component("comp2").material("mat1").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.component("comp2").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp2").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp2").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp2").material("mat1").propertyGroup("HockettSherby").set("sigma_hoc", "684[MPa]*a(T)");
    model.component("comp2").material("mat1").propertyGroup("HockettSherby").set("m_hoc", "3.9");
    model.component("comp2").material("mat1").propertyGroup("HockettSherby").set("n_hoc", "0.85");
    model.component("comp2").material("mat1").propertyGroup("HockettSherby").addInput("temperature");
    model.component("comp2").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .label("Interpolation 1");
    model.component("comp2").material("mat1").propertyGroup("ArmstrongFrederick").func("int1").set("funcname", "a");
    model.component("comp2").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp2").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp2").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp2").material("mat1").propertyGroup("ArmstrongFrederick").set("Ck", "2.070[GPa]*a(T)");
    model.component("comp2").material("mat1").propertyGroup("ArmstrongFrederick").set("gammak", "8.0");
    model.component("comp2").material("mat1").propertyGroup("ArmstrongFrederick").addInput("temperature");
    model.component("comp2").material("mat1").propertyGroup("Norton").label("Norton");
    model.component("comp2").material("mat1").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.component("comp2").material("mat1").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.component("comp2").material("mat1").propertyGroup("Norton").set("n_nor", "4.5");
    model.component("comp2").material("mat1").propertyGroup("Garofalo").label("Garofalo (hyperbolic sine)");
    model.component("comp2").material("mat1").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.component("comp2").material("mat1").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.component("comp2").material("mat1").propertyGroup("Garofalo").set("n_gar", "4.6");
    model.component("comp2").material("mat1").propertyGroup("ChabocheViscoplasticity")
         .label("Chaboche viscoplasticity");
    model.component("comp2").material("mat1").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1[1/s]");
    model.component("comp2").material("mat1").propertyGroup("ChabocheViscoplasticity").set("sigRef_cha", "490[MPa]");
    model.component("comp2").material("mat1").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");

    model.component("comp2").physics().create("solid", "SolidMechanics", "geom2");

    model.component("comp2").pair().create("p1", "Contact");
    model.component("comp2").pair("p1").source().named("geom2_cro1_cad1_Faceset2_pipe_bnd");
    model.component("comp2").pair("p1").destination().named("geom2_cro1_cad1_Faceset2_adaptor_bnd");
    model.component("comp2").pair().create("p2", "Contact");
    model.component("comp2").pair("p2").source().named("geom2_cro1_cad1_Faceset1_housing_bnd");
    model.component("comp2").pair("p2").destination().named("geom2_cro1_cad1_Faceset1_adaptor_bnd");
    model.component("comp2").pair().create("p3", "Contact");
    model.component("comp2").pair("p3").source().named("geom2_cro1_cad1_Faceset2_housing_bnd");
    model.component("comp2").pair("p3").destination().named("geom2_cro1_cad1_Faceset1_pipe_bnd");

    model.component("comp2").physics("solid").create("cnt1", "SolidContact", 1);
    model.component("comp2").physics("solid").feature("cnt1").set("pairs", new String[]{"p1", "p2", "p3"});
    model.component("comp2").physics("solid").feature("cnt1").set("ContactMethodCtrl", "AugmentedLagrange");
    model.component("comp2").physics("solid").feature("cnt1").create("fric1", "Friction", 1);
    model.component("comp2").physics("solid").feature("cnt1").feature("fric1").set("mu_fric", "mu");
    model.component("comp2").physics("solid").create("roll1", "Roller", 1);
    model.component("comp2").physics("solid").feature("roll1").selection().set(14);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("d0", "8.75[mm]", "Thread mean diameter");
    model.param().set("d1", "5.8[mm]", "Nominal thread diameter");
    model.param().set("p", "1.5[mm]", "Pitch");
    model.param().set("beta", "30[deg]", "Semi thread angle");
    model.param().set("T", "1000[N*mm]", "Applied torque");
    model.param().set("A", "p/(pi*d0)", "Tangent of the helix angle");
    model.param().set("mu", "0.15", "Friction coefficient");
    model.param()
         .set("W", "2*T*(1-mu*A*sec(beta))/(d0*(A+mu*sec(beta))+mu*sec(0.7854)*d1*(1-mu*A*sec(beta)))", "Axial preload");
    model.param().set("k", "1e5[N/m^3]", "Spring coefficient");

    model.component("comp2").cpl().create("intop1", "Integration");
    model.component("comp2").cpl("intop1").set("axisym", true);
    model.component("comp2").cpl("intop1").selection().named("geom2_cro1_cad1_Pretension_domain_dom");
    model.component("comp2").cpl("intop1").set("frame", "material");

    model.component("comp2").physics("solid").create("ge1", "GlobalEquations", -1);
    model.component("comp2").physics("solid").feature("ge1").setIndex("name", "eZ", 0, 0);
    model.component("comp2").physics("solid").feature("ge1")
         .setIndex("equation", "intop1(solid.SZ/0.2[mm])+W", 0, 0);
    model.component("comp2").physics("solid").feature("ge1").setIndex("initialValueU", 0.1, 0, 0);
    model.component("comp2").physics("solid").feature("ge1").set("DependentVariableQuantity", "strain");
    model.component("comp2").physics("solid").feature("ge1").set("SourceTermQuantity", "force");
    model.component("comp2").physics("solid").feature("lemm1").create("iss1", "InitialStressandStrain", 2);
    model.component("comp2").physics("solid").feature("lemm1").feature("iss1").selection().set();
    model.component("comp2").physics("solid").feature("lemm1").feature("iss1").selection()
         .named("geom2_cro1_cad1_Pretension_domain_dom");
    model.component("comp2").physics("solid").feature("lemm1").feature("iss1")
         .set("eil", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "eZ"});
    model.component("comp2").physics("solid").create("spf1", "SpringFoundation1", 1);
    model.component("comp2").physics("solid").feature("spf1").selection().set(5, 63);
    model.component("comp2").physics("solid").feature("spf1")
         .set("kPerArea", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "k"});

    model.component("comp2").mesh("mesh2").automatic(false);
    model.component("comp2").mesh("mesh2").feature("ftri1").create("size1", "Size");
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("size1").selection().geom("geom2", 1);
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("size1").selection()
         .named("geom2_cro1_cad1_Faceset2_adaptor_bnd");
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("size1").set("hmax", 0.1);
    model.component("comp2").mesh("mesh2").run();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setEntry("mesh", "geom1", "nomesh");
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "d0", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "d0", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "T", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "100 500 1e3 5e3", 0);
    model.study("std1").feature("stat").setIndex("punit", "N*mm", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("se1").feature("ss1").set("subtermauto", "tol");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 4, 0);
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("resolution", "normal");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def").set("scale", "1");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "w"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().dataset().create("dset1solidrev", "Revolve2D");
    model.result().dataset("dset1solidrev").set("data", "dset1");
    model.result().dataset("dset1solidrev").set("revangle", 225);
    model.result().dataset("dset1solidrev").set("startangle", -90);
    model.result().dataset("dset1solidrev").set("hasspacevars", true);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1solidrev");
    model.result("pg2").setIndex("looplevel", 4, 0);
    model.result("pg2").label("\u5e94\u529b, 3D (solid)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg2").feature("surf1").set("threshold", "manual");
    model.result("pg2").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result().dataset("dset1solidrev").set("hasspacevars", true);
    model.result("pg2").feature("surf1").feature("def").set("revcoordsys", "cylindrical");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").feature("surf1").feature("def").set("descractive", true);
    model.result("pg2").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg2").feature("surf1").feature("def").set("scale", "1");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("unit", "MPa");
    model.result("pg2").run();
    model.result("pg2").set("showlegendsmaxmin", true);

    model.title("\u5bf9\u6765\u81ea CAD \u6587\u4ef6\u7684\u7ba1\u4ef6\u8fdb\u884c\u5e94\u529b\u5206\u6790");

    model
         .description("\u672c\u6559\u5b66\u6a21\u578b\u6f14\u793a\u5982\u4f55\u901a\u8fc7\u63a5\u89e6\u5bf9\u4e09\u7ef4\u87ba\u7eb9\u7ba1\u4ef6\u8fdb\u884c\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u5e94\u529b\u5206\u6790\u8bbe\u7f6e\u3002\n\n\u672c\u4f8b\u6d89\u53ca\u5c06\u4e09\u7ef4 Solid Edge\u00ae \u51e0\u4f55\u548c\u9009\u62e9\uff08\u7528\u4e8e\u6307\u5b9a\u63a5\u89e6\u9762\uff09\u4e0e COMSOL Multiphysics\u00ae \u4e2d\u7684\u4e8c\u7ef4\u51e0\u4f55\u8fdb\u884c\u540c\u6b65\u3002\u5728 COMSOL Multiphysics\u00ae \u4e2d\u5b9a\u4e49\u4e86\u4e00\u4e2a\u622a\u9762\uff0c\u4ee5\u57fa\u4e8e Solid Edge\u00ae \u4e09\u7ef4\u5bf9\u8c61\u521b\u5efa\u4e8c\u7ef4\u51e0\u4f55\uff0c\u4f46\u5728\u540c\u6b65\u540e\u65e0\u9700\u5728 COMSOL Multiphysics\u00ae \u73af\u5883\u4e2d\u91cd\u65b0\u5b9a\u4e49\u9009\u62e9\u3002\u8fd9\u610f\u5473\u7740\u60a8\u53ef\u4ee5\u901a\u8fc7\u4e8c\u7ef4\u5206\u6790\u8fd0\u884c\u53c2\u6570\u5316\u7814\u7a76\u6216\u4f18\u5316\u4e09\u7ef4 CAD \u8bbe\u8ba1\u3002\n\nSolid Edge \u662f Siemens Product Lifecycle Management Software Inc. \u7684\u6ce8\u518c\u5546\u6807\u3002");

    model.label("pipe_fitting_llse.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
