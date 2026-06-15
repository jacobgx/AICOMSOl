/*
 * solidly_mounted_resonator_2d_uncertainty_quantification.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:00 by COMSOL 6.3.0.290. */
public class solidly_mounted_resonator_2d_uncertainty_quantification {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\MEMS_Module\\Piezoelectric_Devices");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics("solid").create("pzm1", "PiezoelectricMaterialModel");
    model.component("comp1").physics("solid").feature("pzm1").selection().all();
    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics("es").create("ccnp1", "ChargeConservationPiezo");
    model.component("comp1").physics("es").feature("ccnp1").selection().all();

    model.component("comp1").multiphysics().create("pze1", "PiezoelectricEffect", 2);
    model.component("comp1").multiphysics("pze1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("pze1").set("Electrostatics_physics", "es");

    model.study().create("std1");
    model.study("std1").create("eig", "Eigenfrequency");
    model.study("std1").feature("eig").set("shift", "1[Hz]");
    model.study("std1").feature("eig").set("chkeigregion", true);
    model.study("std1").feature("eig").set("storefact", false);
    model.study("std1").feature("eig").set("linpsolnum", "auto");
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").set("outputmap", new String[]{});
    model.study("std1").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").setSolveFor("/physics/solid", true);
    model.study("std1").feature("eig").setSolveFor("/physics/es", true);
    model.study("std1").feature("eig").setSolveFor("/multiphysics/pze1", true);

    model.component("comp1").common().create("mpf1", "ParticipationFactors");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");

    model.component("comp1").view("view1").axis().set("viewscaletype", "manual");
    model.component("comp1").view("view1").axis().set("yscale", 35);

    model.param().label("\u53c2\u6570 1 - \u51e0\u4f55");
    model.param().set("t_s", "500[um]/25");
    model.param().descr("t_s", "\u57fa\u677f\u539a\u5ea6\uff08\u622a\u65ad\uff09");
    model.param().set("t_i", "200[nm]");
    model.param().descr("t_i", "\u7edd\u7f18\u4f53\u539a\u5ea6");
    model.param().set("t_hil", "1.82[um]");
    model.param().descr("t_hil", "\u9ad8\u963b\u6297\u5c42\u539a\u5ea6");
    model.param().set("t_lil", "1.65[um]");
    model.param().descr("t_lil", "\u4f4e\u963b\u6297\u5c42\u539a\u5ea6");
    model.param().set("t_pe", "3.35[um]");
    model.param().descr("t_pe", "\u538b\u7535\u5c42\u539a\u5ea6");
    model.param().set("t_e", "200[nm]");
    model.param().descr("t_e", "\u7535\u6781\u539a\u5ea6");
    model.param().set("w_ar", "200[um]");
    model.param().descr("w_ar", "\u6709\u6e90\u533a\u57df\u5bbd\u5ea6");
    model.param().set("w_pe", "800[um]");
    model.param().descr("w_pe", "\u538b\u7535\u5c42\u5bbd\u5ea6");
    model.param().set("w", "1000[um]");
    model.param().descr("w", "\u5668\u4ef6\u5bbd\u5ea6");
    model.param().create("par2");
    model.param("par2").label("\u53c2\u6570 2 - \u6750\u6599\u5c5e\u6027");
    model.param("par2").set("rho_ZnO", "5680[kg/m^3]");
    model.param("par2").descr("rho_ZnO", "ZnO \u7684\u5bc6\u5ea6");
    model.param("par2").set("rho_Mo", "10200[kg/m^3]");
    model.param("par2").descr("rho_Mo", "Mo \u7684\u5bc6\u5ea6");
    model.param("par2").set("rho_SiO2", "2170[kg/m^3]");
    model.param("par2").descr("rho_SiO2", "SiO2 \u7684\u5bc6\u5ea6");
    model.param("par2").set("rho_Al", "2700[kg/m^3]");
    model.param("par2").descr("rho_Al", "Al \u7684\u5bc6\u5ea6");
    model.param("par2").set("rho_Si", "2330[kg/m^3]");
    model.param("par2").descr("rho_Si", "Si \u7684\u5bc6\u5ea6");
    model.param("par2").set("v_ZnO", "6330[m/s]");
    model.param("par2").descr("v_ZnO", "ZnO \u7684\u58f0\u901f");
    model.param("par2").set("v_Mo", "6280[m/s]");
    model.param("par2").descr("v_Mo", "Mo \u7684\u58f0\u901f");
    model.param("par2").set("v_SiO2", "5540[m/s]");
    model.param("par2").descr("v_SiO2", "SiO2 \u7684\u58f0\u901f");
    model.param("par2").set("v_Al", "6450[m/s]");
    model.param("par2").descr("v_Al", "Al \u7684\u58f0\u901f");
    model.param("par2").set("v_Si", "8320[m/s]");
    model.param("par2").descr("v_Si", "Si \u7684\u58f0\u901f");
    model.param("par2").set("E_Mo", "rho_Mo*(v_Mo)^2");
    model.param("par2").descr("E_Mo", "Mo \u7684\u6768\u6c0f\u6a21\u91cf");
    model.param("par2").set("E_SiO2", "rho_SiO2*(v_SiO2)^2");
    model.param("par2").descr("E_SiO2", "SiO2 \u7684\u6768\u6c0f\u6a21\u91cf");
    model.param("par2").set("E_Al", "rho_Al*(v_Al)^2");
    model.param("par2").descr("E_Al", "Al \u7684\u6768\u6c0f\u6a21\u91cf");
    model.param("par2").set("E_Si", "rho_Si*(v_Si)^2");
    model.param("par2").descr("E_Si", "Si \u7684\u6768\u6c0f\u6a21\u91cf");
    model.param("par2").set("eta0", "1.5e-4");
    model.param("par2").descr("eta0", "\u635f\u8017\u56e0\u5b50\uff08\u731c\u6d4b\u503c\uff09");
    model.param("par2").set("lambda_Si", "v_Si/870[MHz]");
    model.param("par2").descr("lambda_Si", "Si \u7684\u6ce2\u957f");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").label("\u538b\u7535 - ZnO");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"w_pe", "t_pe"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"-w_pe/2", "t_e"});
    model.component("comp1").geom("geom1").feature("r1").set("selresult", true);
    model.component("comp1").geom("geom1").feature().duplicate("r2", "r1");
    model.component("comp1").geom("geom1").feature("r2").label("\u9876\u90e8\u7535\u6781 - Al");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"w_ar", "t_e"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"-w_ar/2", "t_pe+t_e"});
    model.component("comp1").geom("geom1").feature("r2").set("selresultshow", "all");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("Al");
    model.component("comp1").geom("geom1").feature("r2").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature().duplicate("r3", "r2");
    model.component("comp1").geom("geom1").feature("r3").label("\u5e95\u90e8\u7535\u6781 - Al");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"w_pe", "t_e"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"-w_pe/2", "0"});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").label("\u4f4e\u963b\u6297 - SiO2");
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"w", "t_lil"});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new String[]{"-w/2", "-t_lil"});
    model.component("comp1").geom("geom1").feature("r4").setIndex("layer", "(w-w_pe)/2", 0);
    model.component("comp1").geom("geom1").feature("r4").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r4").set("layerright", true);
    model.component("comp1").geom("geom1").feature("r4").set("layerbottom", false);
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("SiO2");
    model.component("comp1").geom("geom1").feature("r4").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").label("\u9635\u5217 - SiO2");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").named("csel2");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{1, 3});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"0", "-t_lil-t_hil"});
    model.component("comp1").geom("geom1").feature().duplicate("r5", "r4");
    model.component("comp1").geom("geom1").feature("r5").label("\u9ad8\u963b\u6297 - Mo");
    model.component("comp1").geom("geom1").feature("r5").set("size", new String[]{"w", "t_hil"});
    model.component("comp1").geom("geom1").feature("r5").set("pos", new String[]{"-w/2", "-t_lil-t_hil"});
    model.component("comp1").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel3").label("Mo");
    model.component("comp1").geom("geom1").feature("r5").set("contributeto", "csel3");
    model.component("comp1").geom("geom1").feature().duplicate("arr2", "arr1");
    model.component("comp1").geom("geom1").feature("arr2").label("\u9635\u5217 - Mo");
    model.component("comp1").geom("geom1").runPre("arr2");
    model.component("comp1").geom("geom1").feature("arr2").selection("input").named("csel3");
    model.component("comp1").geom("geom1").run("arr2");
    model.component("comp1").geom("geom1").feature().duplicate("r6", "r4");
    model.component("comp1").geom("geom1").feature("r6").label("\u7edd\u7f18\u4f53 - SiO2");
    model.component("comp1").geom("geom1").feature("r6").set("size", new String[]{"w", "t_i"});
    model.component("comp1").geom("geom1").feature("r6")
         .set("pos", new String[]{"-w/2", "-(t_lil*3)-(t_hil*3)-t_i"});
    model.component("comp1").geom("geom1").feature().duplicate("r7", "r6");
    model.component("comp1").geom("geom1").feature("r7").label("\u57fa\u677f - Si");
    model.component("comp1").geom("geom1").feature("r7").set("size", new String[]{"w", "t_s"});
    model.component("comp1").geom("geom1").feature("r7")
         .set("pos", new String[]{"-w/2", "-(t_lil*3)-(t_hil*3)-t_i-t_s"});
    model.component("comp1").geom("geom1").selection().create("csel4", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel4").label("Si");
    model.component("comp1").geom("geom1").feature("r7").set("contributeto", "csel4");
    model.component("comp1").geom("geom1").feature().duplicate("r8", "r7");
    model.component("comp1").geom("geom1").feature("r8").label("\u5e95\u90e8 PML - Si");
    model.component("comp1").geom("geom1").feature("r8").set("size", new String[]{"w", "lambda_Si"});
    model.component("comp1").geom("geom1").feature("r8")
         .set("pos", new String[]{"-w/2", "-(t_lil*3)-(t_hil*3)-t_i-t_s-lambda_Si"});
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("box1", "Box");
    model.component("comp1").selection("box1").label("\u975e PML");
    model.component("comp1").selection("box1").set("xmin", "-w_ar");
    model.component("comp1").selection("box1").set("xmax", "w_ar");
    model.component("comp1").selection("box1").set("ymin", "-(t_hil*3)-(t_lil*3)-t_i-t_s/2");
    model.component("comp1").selection().create("com1", "Complement");
    model.component("comp1").selection("com1").label("PML");
    model.component("comp1").selection("com1").set("input", new String[]{"box1"});
    model.component("comp1").selection().create("box2", "Box");
    model.component("comp1").selection("box2").label("\u5de6\u56fa\u5b9a\u8fb9\u754c\u6761\u4ef6");
    model.component("comp1").selection("box2").set("entitydim", 1);
    model.component("comp1").selection("box2").set("xmax", "-(w+w_pe)/4");
    model.component("comp1").selection("box2").set("condition", "inside");
    model.component("comp1").selection().duplicate("box3", "box2");
    model.component("comp1").selection("box3").label("\u53f3\u56fa\u5b9a\u8fb9\u754c\u6761\u4ef6");
    model.component("comp1").selection("box3").set("xmin", "(w+w_pe)/4");
    model.component("comp1").selection("box3").set("xmax", Double.POSITIVE_INFINITY);
    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u5e95\u90e8\u56fa\u5b9a\u8fb9\u754c\u6761\u4ef6");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(2, 21, 49);
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u56fa\u5b9a\u8fb9\u754c\u6761\u4ef6");
    model.component("comp1").selection("uni1").set("entitydim", 1);
    model.component("comp1").selection("uni1").set("input", new String[]{"box2", "box3", "sel1"});

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().named("com1");

    model.component("comp1").physics("solid").prop("d").set("d", "w_ar");
    model.component("comp1").physics("solid").feature("lemm1").create("dmp1", "Damping", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1")
         .set("DampingType", "IsotropicLossFactor");
    model.component("comp1").physics("solid").feature("pzm1").selection().named("geom1_r1_dom");
    model.component("comp1").physics("solid").feature("pzm1").create("mdmp1", "MechanicalDamping", 2);
    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().named("uni1");
    model.component("comp1").physics("es").selection().named("geom1_r1_dom");
    model.component("comp1").physics("es").prop("d").set("d", "w_ar");
    model.component("comp1").physics("es").create("gnd1", "Ground", 1);
    model.component("comp1").physics("es").feature("gnd1").selection().named("geom1_r3_bnd");
    model.component("comp1").physics("es").create("term1", "Terminal", 1);
    model.component("comp1").physics("es").feature("term1").selection().named("geom1_r2_bnd");
    model.component("comp1").physics("es").feature("term1").set("TerminalType", "Voltage");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("StrainCharge", "StrainCharge", "Strain-charge form");
    model.component("comp1").material("mat1").propertyGroup()
         .create("StressCharge", "StressCharge", "Stress-charge form");
    model.component("comp1").material("mat1").label("Zinc Oxide");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.9);
    model.component("comp1").material("mat1").set("roughness", 0.1);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"8.5446", "0", "0", "0", "8.5446", "0", "0", "0", "10.204"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "5680[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("StrainCharge").label("Strain-charge form");
    model.component("comp1").material("mat1").propertyGroup("StrainCharge")
         .set("sE", new String[]{"7.86e-012[1/Pa]", "-3.43e-012[1/Pa]", "-2.21e-012[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "-3.43e-012[1/Pa]", "7.86e-012[1/Pa]", "-2.21e-012[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "0[1/Pa]", "-2.21e-012[1/Pa]", "-2.21e-012[1/Pa]", "6.94e-012[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "2.36e-011[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "2.36e-011[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "2.26e-011[1/Pa]"});
    model.component("comp1").material("mat1").propertyGroup("StrainCharge")
         .set("dET", new String[]{"0[C/N]", "0[C/N]", "-5.43e-012[C/N]", "0[C/N]", "0[C/N]", "-5.43e-012[C/N]", "0[C/N]", "0[C/N]", "1.167e-011[C/N]", "0[C/N]", 
         "-1.134e-011[C/N]", "0[C/N]", "-1.134e-011[C/N]", "0[C/N]", "0[C/N]", "0[C/N]", "0[C/N]", "0[C/N]"});
    model.component("comp1").material("mat1").propertyGroup("StrainCharge")
         .set("epsilonrT", new String[]{"9.16", "0", "0", "0", "9.16", "0", "0", "0", "12.64"});
    model.component("comp1").material("mat1").propertyGroup("StressCharge").label("Stress-charge form");
    model.component("comp1").material("mat1").propertyGroup("StressCharge")
         .set("cE", new String[]{"2.09714e+011[Pa]", "1.2114e+011[Pa]", "1.05359e+011[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "1.2114e+011[Pa]", "2.09714e+011[Pa]", "1.05359e+011[Pa]", "0[Pa]", 
         "0[Pa]", "0[Pa]", "1.05359e+011[Pa]", "1.05359e+011[Pa]", "2.11194e+011[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", 
         "0[Pa]", "4.23729e+010[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "4.23729e+010[Pa]", "0[Pa]", 
         "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "4.42478e+010[Pa]"});
    model.component("comp1").material("mat1").propertyGroup("StressCharge")
         .set("eES", new String[]{"0[C/m^2]", "0[C/m^2]", "-0.567005[C/m^2]", "0[C/m^2]", "0[C/m^2]", "-0.567005[C/m^2]", "0[C/m^2]", "0[C/m^2]", "1.32044[C/m^2]", "0[C/m^2]", 
         "-0.480508[C/m^2]", "0[C/m^2]", "-0.480508[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]"});
    model.component("comp1").material("mat1").propertyGroup("StressCharge")
         .set("epsilonrS", new String[]{"8.5446", "0", "0", "0", "8.5446", "0", "0", "0", "10.204"});
    model.component("comp1").material("mat1").selection().named("geom1_r1_dom");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rho_ZnO"});
    model.component("comp1").material("mat1").propertyGroup("StressCharge").set("eta_cE", new String[]{"eta0"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").label("Al - Aluminum");
    model.component("comp1").material("mat2").set("family", "aluminum");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"35.5e6[S/m]", "0", "0", "0", "35.5e6[S/m]", "0", "0", "0", "35.5e6[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23.1e-6[1/K]", "0", "0", "0", "23.1e-6[1/K]", "0", "0", "0", "23.1e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "904[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"237[W/(m*K)]", "0", "0", "0", "237[W/(m*K)]", "0", "0", "0", "237[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "70.0e9[Pa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat2").selection().named("geom1_csel1_dom");
    model.component("comp1").material("mat2").propertyGroup("def").set("lossfactor", new String[]{"eta0"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"rho_Al"});
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", new String[]{"E_Al"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").label("SiO2 - Silicon oxide");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"0.5e-6[1/K]", "0", "0", "0", "0.5e-6[1/K]", "0", "0", "0", "0.5e-6[1/K]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "730[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"4.2", "0", "0", "0", "4.2", "0", "0", "0", "4.2"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "2200[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.4[W/(m*K)]", "0", "0", "0", "1.4[W/(m*K)]", "0", "0", "0", "1.4[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", "70e9[Pa]");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", "0.17");
    model.component("comp1").material("mat3").selection().named("geom1_csel2_dom");
    model.component("comp1").material("mat3").propertyGroup("def").set("lossfactor", new String[]{"eta0"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", new String[]{"rho_SiO2"});
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", new String[]{"E_SiO2"});
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat4").label("Si - Silicon (single-crystal, isotropic)");
    model.component("comp1").material("mat4").set("family", "custom");
    model.component("comp1").material("mat4").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.component("comp1").material("mat4").set("diffuse", "custom");
    model.component("comp1").material("mat4")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.component("comp1").material("mat4").set("ambient", "custom");
    model.component("comp1").material("mat4")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.component("comp1").material("mat4").set("noise", true);
    model.component("comp1").material("mat4").set("fresnel", 0.7);
    model.component("comp1").material("mat4").set("roughness", 0.5);
    model.component("comp1").material("mat4").set("diffusewrap", 0);
    model.component("comp1").material("mat4").set("reflectance", 0);
    model.component("comp1").material("mat4").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"2.6e-6[1/K]", "0", "0", "0", "2.6e-6[1/K]", "0", "0", "0", "2.6e-6[1/K]"});
    model.component("comp1").material("mat4").propertyGroup("def").set("heatcapacity", "700[J/(kg*K)]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermittivity", new String[]{"11.7", "0", "0", "0", "11.7", "0", "0", "0", "11.7"});
    model.component("comp1").material("mat4").propertyGroup("def").set("density", "2329[kg/m^3]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"130[W/(m*K)]", "0", "0", "0", "130[W/(m*K)]", "0", "0", "0", "130[W/(m*K)]"});
    model.component("comp1").material("mat4").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("E", "170e9[Pa]");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp1").material("mat4").selection().named("geom1_csel4_dom");
    model.component("comp1").material("mat4").propertyGroup("def").set("lossfactor", new String[]{"eta0"});
    model.component("comp1").material("mat4").propertyGroup("def").set("density", new String[]{"rho_Si"});
    model.component("comp1").material("mat4").propertyGroup("Enu").set("E", new String[]{"E_Si"});
    model.component("comp1").material().create("mat5", "Common");
    model.component("comp1").material("mat5").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat5").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat5").label("Molybdenum");
    model.component("comp1").material("mat5").set("family", "custom");
    model.component("comp1").material("mat5").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.component("comp1").material("mat5").set("diffuse", "custom");
    model.component("comp1").material("mat5")
         .set("customdiffuse", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat5").set("ambient", "custom");
    model.component("comp1").material("mat5")
         .set("customambient", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat5").set("noise", true);
    model.component("comp1").material("mat5").set("fresnel", 0.3);
    model.component("comp1").material("mat5").set("roughness", 0.1);
    model.component("comp1").material("mat5").set("diffusewrap", 0);
    model.component("comp1").material("mat5").set("reflectance", 0);
    model.component("comp1").material("mat5").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"4.8e-6[1/K]", "0", "0", "0", "4.8e-6[1/K]", "0", "0", "0", "4.8e-6[1/K]"});
    model.component("comp1").material("mat5").propertyGroup("def").set("density", "10200[kg/m^3]");
    model.component("comp1").material("mat5").propertyGroup("def").set("heatcapacity", "250[J/(kg*K)]");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalconductivity", new String[]{"138[W/(m*K)]", "0", "0", "0", "138[W/(m*K)]", "0", "0", "0", "138[W/(m*K)]"});
    model.component("comp1").material("mat5").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat5").propertyGroup("Enu").set("E", "312[GPa]");
    model.component("comp1").material("mat5").propertyGroup("Enu").set("nu", "0.31");
    model.component("comp1").material("mat5").propertyGroup("Murnaghan").set("l", "-300[GPa]");
    model.component("comp1").material("mat5").propertyGroup("Murnaghan").set("m", "-850[GPa]");
    model.component("comp1").material("mat5").propertyGroup("Murnaghan").set("n", "-910[GPa]");
    model.component("comp1").material("mat5").selection().named("geom1_csel3_dom");
    model.component("comp1").material("mat5").propertyGroup("def").set("lossfactor", new String[]{"eta0"});
    model.component("comp1").material("mat5").propertyGroup("def").set("density", new String[]{"rho_Mo"});
    model.component("comp1").material("mat5").propertyGroup("Enu").set("E", new String[]{"E_Mo"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().named("geom1_r2_dom");
    model.component("comp1").mesh("mesh1").feature("map1").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").feature("map1").set("adjustedgdistr", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(45);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 16);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(43);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 1);
    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(42, 47);
    model.component("comp1").mesh("mesh1").feature("edg1").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").selection().set(47);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemcount", 16);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemratio", 2);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("edg1").feature().duplicate("dis2", "dis1");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis2").selection().set(42);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis2").set("reverse", true);
    model.component("comp1").mesh("mesh1").create("cpe1", "CopyEdge");
    model.component("comp1").mesh("mesh1").feature("cpe1").selection("source").geom(1);
    model.component("comp1").mesh("mesh1").feature("cpe1").selection("destination").geom(1);
    model.component("comp1").mesh("mesh1").feature("cpe1").selection("source").set(42, 44, 47);
    model.component("comp1").mesh("mesh1").feature("cpe1").selection("destination")
         .set(21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41);
    model.component("comp1").mesh("mesh1").create("map2", "Map");
    model.component("comp1").mesh("mesh1").feature("map2").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").feature("map2").set("adjustedgdistr", true);
    model.component("comp1").mesh("mesh1").feature("map2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").selection().set(40);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").set("numelem", 8);
    model.component("comp1").mesh("mesh1").feature("map2").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis2").selection().set(5, 38);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis2").set("numelem", 1);
    model.component("comp1").mesh("mesh1").feature("map2").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis3").selection()
         .set(7, 9, 11, 13, 15, 17, 19, 67);
    model.component("comp1").mesh("mesh1").feature("map2").create("dis4", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis4").selection().set(3, 22, 50, 70);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis4").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis4").set("elemcount", 16);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis4").set("elemratio", 2);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis4").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis4").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("map2").create("dis5", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis5").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis5").set("numelem", 8);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1 - \u6a21\u5f0f");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("eig").set("neigsactive", true);
    model.study("std1").feature("eig").set("neigs", 4);
    model.study("std1").feature("eig").set("eigunit", "MHz");
    model.study("std1").feature("eig").set("shift", "870");
    model.study("std1").feature("eig").set("eigwhich", "lr");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").run();
    model.result("pg1").label("\u632f\u578b (solid)");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg1").feature("surf1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def1").set("scale", "2E12");
    model.result("pg1").run();
    model.result("pg1").run();

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/solid", true);
    model.study("std2").feature("freq").setSolveFor("/physics/es", true);
    model.study("std2").feature("freq").setSolveFor("/multiphysics/pze1", true);
    model.study("std2").feature("freq").set("punit", "MHz");
    model.study("std2").feature("freq")
         .set("plist", "range(500,50,800) range(810,10,850) range(860,2,870) range(870.1,0.05,870.9) range(871,1,910) 920 930 940 range(950,50,1200)");
    model.study("std2").label("\u7814\u7a76 2 - \u9891\u7387\u54cd\u5e94");
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").label("\u7535\u52bf (es)");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "V");
    model.result("pg2").feature("surf1").set("descractive", true);
    model.result("pg2").feature("surf1").set("colortable", "Dipole");
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 28, 0);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("Log10|Z| - \u56fe 4");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").set("titletype", "label");
    model.result("pg3").set("showlegends", false);
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").setIndex("expr", "log10(abs(1/es.Y11)/1[ohm])", 0);
    model.result("pg3").feature("glob1").setIndex("descr", "log10|Z| (Ohms)", 0);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").run();
    model.result("pg4").label("\u4f4d\u79fb\u5256\u9762 - \u56fe 5(a)");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 28, 0);
    model.result("pg4").set("titletype", "label");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "sqrt(abs(u)^2+abs(v)^2)");
    model.result("pg4").feature("surf1").set("rangecoloractive", true);
    model.result("pg4").feature("surf1").set("rangecolormax", 0.02);
    model.result("pg4").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").set("data", "dset2");
    model.result().dataset("cln1").setIndex("genpoints", "4[um]", 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", "-20[um]", 1, 1);
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("Y \u4f4d\u79fb- \u56fe 5(b)");
    model.result("pg5").set("data", "cln1");
    model.result("pg5").setIndex("looplevelinput", "manual", 0);
    model.result("pg5").setIndex("looplevel", new int[]{28}, 0);
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("axislimits", true);
    model.result("pg5").set("xmin", -0.02);
    model.result("pg5").set("xmax", 0.02);
    model.result("pg5").set("ymin", -20.5);
    model.result("pg5").set("ymax", 4.5);
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").set("expr", "y");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "-imag(v)");
    model.result("pg5").feature("lngr1").set("xdatadescractive", true);
    model.result("pg5").feature("lngr1").set("xdatadescr", "\u4f4d\u79fb\uff0cy \u5206\u91cf");
    model.result("pg5").feature("lngr1").set("linecolor", "red");
    model.result("pg5").feature("lngr1").set("linewidth", 2);
    model.result("pg5").run();
    model.result("pg5").create("lnsg1", "LineSegments");
    model.result("pg5").feature("lnsg1").set("markerpos", "datapoints");
    model.result("pg5").feature("lnsg1").set("linewidth", "preference");
    model.result("pg5").feature("lnsg1").setIndex("xexpr", -0.02, 0);
    model.result("pg5").feature("lnsg1").setIndex("xexpr", 0.02, 1);
    model.result("pg5").feature("lnsg1").setIndex("xexpr", 0.02, 2);
    model.result("pg5").feature("lnsg1").setIndex("xexpr", -0.02, 3);
    model.result("pg5").feature("lnsg1").setIndex("xexpr", -0.02, 4);
    model.result("pg5").feature("lnsg1").setIndex("xexpr", 0.02, 5);
    model.result("pg5").feature("lnsg1").setIndex("xexpr", 0.02, 6);
    model.result("pg5").feature("lnsg1").setIndex("xexpr", -0.02, 7);
    model.result("pg5").feature("lnsg1").setIndex("xexpr", -0.02, 8);
    model.result("pg5").feature("lnsg1").setIndex("xexpr", 0.02, 9);
    model.result("pg5").feature("lnsg1").setIndex("xexpr", 0.02, 10);
    model.result("pg5").feature("lnsg1").setIndex("xexpr", -0.02, 11);
    model.result("pg5").feature("lnsg1").setIndex("xexpr", -0.02, 12);
    model.result("pg5").feature("lnsg1").setIndex("xexpr", 0.02, 13);
    model.result("pg5").feature("lnsg1").setIndex("xexpr", 0.02, 14);
    model.result("pg5").feature("lnsg1").setIndex("xexpr", -0.02, 15);
    model.result("pg5").feature("lnsg1").setIndex("xexpr", -0.02, 16);
    model.result("pg5").feature("lnsg1").setIndex("xexpr", 0.02, 17);
    model.result("pg5").feature("lnsg1").setIndex("xexpr", 0.02, 18);
    model.result("pg5").feature("lnsg1").setIndex("xexpr", -0.02, 19);
    model.result("pg5").feature("lnsg1").setIndex("xexpr", -0.02, 20);
    model.result("pg5").feature("lnsg1").setIndex("xexpr", 0.02, 21);
    model.result("pg5").feature("lnsg1").setIndex("yexpr", "2*t_e+t_pe", 0);
    model.result("pg5").feature("lnsg1").setIndex("yexpr", "2*t_e+t_pe", 1);
    model.result("pg5").feature("lnsg1").setIndex("yexpr", "t_e+t_pe", 2);
    model.result("pg5").feature("lnsg1").setIndex("yexpr", "t_e+t_pe", 3);
    model.result("pg5").feature("lnsg1").setIndex("yexpr", "t_e", 4);
    model.result("pg5").feature("lnsg1").setIndex("yexpr", "t_e", 5);
    model.result("pg5").feature("lnsg1").setIndex("yexpr", 0, 6);
    model.result("pg5").feature("lnsg1").setIndex("yexpr", 0, 7);
    model.result("pg5").feature("lnsg1").setIndex("yexpr", "-t_lil", 8);
    model.result("pg5").feature("lnsg1").setIndex("yexpr", "-t_lil", 9);
    model.result("pg5").feature("lnsg1").setIndex("yexpr", "-t_lil-t_hil", 10);
    model.result("pg5").feature("lnsg1").setIndex("yexpr", "-t_lil-t_hil", 11);
    model.result("pg5").feature("lnsg1").setIndex("yexpr", "-2*t_lil-t_hil", 12);
    model.result("pg5").feature("lnsg1").setIndex("yexpr", "-2*t_lil-t_hil", 13);
    model.result("pg5").feature("lnsg1").setIndex("yexpr", "-2*t_lil-2*t_hil", 14);
    model.result("pg5").feature("lnsg1").setIndex("yexpr", "-2*t_lil-2*t_hil", 15);
    model.result("pg5").feature("lnsg1").setIndex("yexpr", "-3*t_lil-2*t_hil", 16);
    model.result("pg5").feature("lnsg1").setIndex("yexpr", "-3*t_lil-2*t_hil", 17);
    model.result("pg5").feature("lnsg1").setIndex("yexpr", "-3*t_lil-3*t_hil", 18);
    model.result("pg5").feature("lnsg1").setIndex("yexpr", "-3*t_lil-3*t_hil", 19);
    model.result("pg5").feature("lnsg1").setIndex("yexpr", "-3*t_lil-3*t_hil-t_i", 20);
    model.result("pg5").feature("lnsg1").setIndex("yexpr", "-3*t_lil-3*t_hil-t_i", 21);
    model.result("pg5").feature("lnsg1").set("linecolor", "black");
    model.result("pg5").run();
    model.result("pg5").create("tlan1", "TableAnnotation");
    model.result("pg5").feature("tlan1").set("source", "localtable");
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", -0.02, 0, 0);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", 2, 0, 1);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "ZnO", 0, 2);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", -0.02, 1, 0);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", -0.85, 1, 1);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "SiO2", 1, 2);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", -0.02, 2, 0);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", -2.6, 2, 1);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "Mo", 2, 2);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", -0.02, 3, 0);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", -4.3, 3, 1);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "SiO2", 3, 2);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", -0.02, 4, 0);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", -6, 4, 1);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "Mo", 4, 2);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", -0.02, 5, 0);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", -7.8, 5, 1);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "SiO2", 5, 2);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", -0.02, 6, 0);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", -9.5, 6, 1);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "Mo", 6, 2);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", -0.02, 7, 0);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", -14, 7, 1);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "\u57fa\u677f", 7, 2);
    model.result("pg5").feature("tlan1").set("showpoint", false);
    model.result("pg5").feature("tlan1").set("anchorpoint", "middleleft");
    model.result("pg5").run();

    model.title("\u56fa\u6001\u88c5\u914d\u8c10\u632f\u5668\uff08\u4e8c\u7ef4\uff09");

    model
         .description("\u56fa\u6001\u88c5\u914d\u8c10\u632f\u5668 (SMR) \u662f\u4e00\u79cd\u538b\u7535 MEMS \u8c10\u632f\u5668\uff0c\u5728\u539a\u57fa\u677f\u4e0a\u6c89\u79ef\u7684\u58f0\u955c\u53e0\u5c42\u9876\u90e8\u5f62\u6210\u3002\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u5728\u4e8c\u7ef4\u4e2d\u6a21\u62df SMR\uff0c\u5176\u4e2d\u8ba1\u7b97\u4e86\u7279\u5f81\u6a21\u6001\uff0c\u5e76\u5206\u6790\u4e86\u4ece 500 \u5230 1200\u00a0MHz \u7684\u9891\u7387\u54cd\u5e94\u3002\u8ba1\u7b97\u5f97\u5230\u7684\u963b\u6297\u66f2\u7ebf\u548c\u4f4d\u79fb\u5256\u9762\u4e0e\u6587\u732e\u4e2d\u7684\u4eff\u771f\u7ed3\u679c\u9ad8\u5ea6\u4e00\u81f4\u3002");

    model.label("solidly_mounted_resonator_2d.mph");

    model.result("pg5").run();

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().set(44);

    model.study("std1").feature("eig").set("neigs", 30);
    model.study("std1").feature("eig").set("shift", "855");
    model.study("std1").create("cmbsol", "CombineSolution");
    model.study("std1").feature("cmbsol").set("soloper", "remsol");
    model.study("std1").feature("cmbsol").set("excmethod", "implicit");
    model.study("std1").feature("cmbsol")
         .set("remsolfromexprexc", "abs(comp1.intop1(comp1.v))/comp1.intop1(abs(comp1.v))<0.99");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.study().create("std3");
    model.study("std3").create("ref", "StudyReference");
    model.study("std3").feature("ref").set("studyref", "std1");
    model.study("std3").create("uq", "UncertaintyQuantification");
    model.study("std3").label("\u7814\u7a76 3 - \u7b5b\u9009");
    model.study("std3").feature("uq").setIndex("funcname", "", 0);
    model.study("std3").feature("uq").setIndex("qoiexpression", "", 0);
    model.study("std3").feature("uq").setIndex("qoisolutionindv", "parent", 0);
    model.study("std3").feature("uq").setIndex("failif", "larger", 0);
    model.study("std3").feature("uq").setIndex("threshold", "", 0);
    model.study("std3").feature("uq").setIndex("funcname", "", 0);
    model.study("std3").feature("uq").setIndex("qoiexpression", "", 0);
    model.study("std3").feature("uq").setIndex("qoisolutionindv", "parent", 0);
    model.study("std3").feature("uq").setIndex("failif", "larger", 0);
    model.study("std3").feature("uq").setIndex("threshold", "", 0);
    model.study("std3").feature("uq").setIndex("qoiexpression", "real(freq)", 0);
    model.study("std3").feature("uq").setIndex("pname", "E_Al", 0);
    model.study("std3").feature("uq").setEntry("sourceType", "col1", "analytic");
    model.study("std3").feature("uq").setIndex("paramDescription", "Pa", 0);
    model.study("std3").feature("uq").setIndex("pname", "E_Al", 0);
    model.study("std3").feature("uq").setEntry("sourceType", "col1", "analytic");
    model.study("std3").feature("uq").setIndex("paramDescription", "Pa", 0);
    model.study("std3").feature("uq").setIndex("pname", "t_pe", 0);
    model.study("std3").feature("uq").setEntry("distributionselection", "col1", "normal");
    model.study("std3").feature("uq").setEntry("s1selection", "col1", "t_pe");
    model.study("std3").feature("uq").setEntry("s2selection", "col1", "0.005*t_pe");
    model.study("std3").feature("uq").setEntry("lcdfselection", "col1", "manual");
    model.study("std3").feature("uq").setEntry("lboundselection", "col1", "t_pe-2*0.005*t_pe");
    model.study("std3").feature("uq").setEntry("ucdfselection", "col1", "manual");
    model.study("std3").feature("uq").setEntry("uboundselection", "col1", "t_pe+2*0.005*t_pe");
    model.study("std3").feature("uq").setIndex("pname", "E_Al", 1);
    model.study("std3").feature("uq").setEntry("sourceType", "col2", "analytic");
    model.study("std3").feature("uq").setIndex("paramDescription", "Pa", 1);
    model.study("std3").feature("uq").setIndex("pname", "E_Al", 1);
    model.study("std3").feature("uq").setEntry("sourceType", "col2", "analytic");
    model.study("std3").feature("uq").setIndex("paramDescription", "Pa", 1);
    model.study("std3").feature("uq").setIndex("pname", "t_lil", 1);
    model.study("std3").feature("uq").setEntry("distributionselection", "col2", "normal");
    model.study("std3").feature("uq").setEntry("s1selection", "col2", "t_lil");
    model.study("std3").feature("uq").setEntry("s2selection", "col2", "0.005*t_lil");
    model.study("std3").feature("uq").setEntry("lcdfselection", "col2", "manual");
    model.study("std3").feature("uq").setEntry("ucdfselection", "col2", "manual");
    model.study("std3").feature("uq").setEntry("lboundselection", "col2", "t_lil-2*0.005*t_lil");
    model.study("std3").feature("uq").setEntry("uboundselection", "col2", "t_lil+2*0.005*t_lil");
    model.study("std3").feature("uq").setIndex("pname", "E_Al", 2);
    model.study("std3").feature("uq").setEntry("sourceType", "col3", "analytic");
    model.study("std3").feature("uq").setIndex("paramDescription", "Pa", 2);
    model.study("std3").feature("uq").setIndex("pname", "E_Al", 2);
    model.study("std3").feature("uq").setEntry("sourceType", "col3", "analytic");
    model.study("std3").feature("uq").setIndex("paramDescription", "Pa", 2);
    model.study("std3").feature("uq").setIndex("pname", "t_hil", 2);
    model.study("std3").feature("uq").setEntry("distributionselection", "col3", "normal");
    model.study("std3").feature("uq").setEntry("s1selection", "col3", "t_hil");
    model.study("std3").feature("uq").setEntry("s2selection", "col3", "0.005*t_hil");
    model.study("std3").feature("uq").setEntry("lcdfselection", "col3", "manual");
    model.study("std3").feature("uq").setEntry("ucdfselection", "col3", "manual");
    model.study("std3").feature("uq").setEntry("lboundselection", "col3", "t_hil-2*0.005*t_hil");
    model.study("std3").feature("uq").setEntry("uboundselection", "col3", "t_hil+2*0.005*t_hil");
    model.study("std3").feature("uq").setIndex("pname", "E_Al", 3);
    model.study("std3").feature("uq").setEntry("sourceType", "col4", "analytic");
    model.study("std3").feature("uq").setIndex("paramDescription", "Pa", 3);
    model.study("std3").feature("uq").setIndex("pname", "E_Al", 3);
    model.study("std3").feature("uq").setEntry("sourceType", "col4", "analytic");
    model.study("std3").feature("uq").setIndex("paramDescription", "Pa", 3);
    model.study("std3").feature("uq").setIndex("pname", "t_e", 3);
    model.study("std3").feature("uq").setEntry("distributionselection", "col4", "normal");
    model.study("std3").feature("uq").setEntry("s1selection", "col4", "t_e");
    model.study("std3").feature("uq").setEntry("s2selection", "col4", "0.005*t_e");
    model.study("std3").feature("uq").setEntry("lcdfselection", "col4", "manual");
    model.study("std3").feature("uq").setEntry("ucdfselection", "col4", "manual");
    model.study("std3").feature("uq").setEntry("lboundselection", "col4", "t_e-2*0.005*t_e");
    model.study("std3").feature("uq").setEntry("uboundselection", "col4", "t_e+2*0.005*t_e");
    model.study("std3").feature("uq").set("errorhandling", "later");
    model.study("std3").createAutoSequences("all");

    model.sol().create("sol5");
    model.sol("sol5").study("std3");
    model.sol("sol5").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("pd1").feature("so2").set("psol", "sol5");
    model.batch("uq1").run("compute");

    model.result("pg6").run();

    model.study().create("std4");
    model.study("std4").feature().copy("uq", "std3/uq", "");
    model.study("std4").feature().copy("ref", "std3/ref", "");
    model.study("std4").feature("uq").set("uqtype", "screening");
    model.study("std4").feature("uq").set("uqresultgrp", "new");
    model.study("std4").feature("uq").set("uqtype", "sensitivityanalysis");
    model.study("std4").feature("uq").set("uqresultgrp", "new");
    model.study("std4").feature("uq").set("uqtype", "uncertaintypropagation");
    model.study("std4").feature("uq").set("uqresultgrp", "new");
    model.study("std4").feature("uq").set("uqtype", "reliabilityanalysis");
    model.study("std4").feature("uq").set("uqresultgrp", "new");
    model.study("std4").feature("uq").set("uqtype", "inverseuq");
    model.study("std4").feature("uq").set("uqresultgrp", "new");
    model.study("std4").feature("uq").set("uqtype", "sensitivityanalysis");
    model.study("std4").feature("uq").set("computeaction", "recompute");
    model.study("std4").feature("uq").set("designtable", "new");
    model.study("std4").feature("uq").set("verifyaction", "recompute");
    model.study("std4").feature("uq").set("tablegraphgrp", "new");
    model.study("std4").label("\u7814\u7a76 4 - \u7075\u654f\u5ea6\u5206\u6790");
    model.study("std4").feature("uq").set("selected", new String[]{"2"});
    model.study("std4").feature("uq")
         .set("distributionselection", new String[]{"col1", "normal", "col2", "normal", "col3", "normal"});
    model.study("std4").feature("uq")
         .set("s1selection", new String[]{"col1", "t_pe", "col2", "t_lil", "col3", "t_e"});
    model.study("std4").feature("uq")
         .set("s2selection", new String[]{"col1", "0.005*t_pe", "col2", "0.005*t_lil", "col3", "0.005*t_e"});
    model.study("std4").feature("uq")
         .set("lcdfselection", new String[]{"col1", "manual", "col2", "manual", "col3", "manual"});
    model.study("std4").feature("uq")
         .set("lboundselection", new String[]{"col1", "t_pe-2*0.005*t_pe", "col2", "t_lil-2*0.005*t_lil", "col3", "t_e-2*0.005*t_e"});
    model.study("std4").feature("uq")
         .set("ucdfselection", new String[]{"col1", "manual", "col2", "manual", "col3", "manual"});
    model.study("std4").feature("uq")
         .set("uboundselection", new String[]{"col1", "t_pe+2*0.005*t_pe", "col2", "t_lil+2*0.005*t_lil", "col3", "t_e+2*0.005*t_e"});
    model.study("std4").feature("uq").set("punitselection", new String[]{"col1", "m", "col2", "m", "col3", "m"});
    model.study("std4").feature("uq")
         .set("inputdatasource", new String[]{"col1", "specified", "col2", "specified", "col3", "specified"});
    model.study("std4").feature("uq").set("tablecolumnselection", new String[]{"col1", "", "col2", "", "col3", ""});
    model.study("std4").feature("uq").set("plistarrselection", new String[]{"col1", "", "col2", "", "col3", ""});
    model.study("std4").feature("uq").set("plistarrsizeselection", new String[]{"col1", "", "col2", "", "col3", ""});
    model.study("std4").feature("uq").remove("sourceType", 2);
    model.study("std4").feature("uq").remove("paramDescription", 2);
    model.study("std4").feature("uq").remove("pname", new int[]{2});
    model.study("std4").createAutoSequences("all");

    model.sol().create("sol8");
    model.sol("sol8").study("std4");
    model.sol("sol8").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("pd2").feature("so2").set("psol", "sol8");
    model.batch("uq2").run("compute");

    model.result("pg7").run();

    model.study().create("std5");
    model.study("std5").feature().copy("uq", "std4/uq", "");
    model.study("std5").feature().copy("ref", "std4/ref", "");
    model.study("std5").feature("uq").set("uqtype", "screening");
    model.study("std5").feature("uq").set("uqresultgrp", "new");
    model.study("std5").feature("uq").set("uqtype", "sensitivityanalysis");
    model.study("std5").feature("uq").set("uqresultgrp", "new");
    model.study("std5").feature("uq").set("uqtype", "uncertaintypropagation");
    model.study("std5").feature("uq").set("uqresultgrp", "new");
    model.study("std5").feature("uq").set("uqtype", "reliabilityanalysis");
    model.study("std5").feature("uq").set("uqresultgrp", "new");
    model.study("std5").feature("uq").set("uqtype", "inverseuq");
    model.study("std5").feature("uq").set("uqresultgrp", "new");
    model.study("std5").feature("uq").set("uqtype", "uncertaintypropagation");

    model.func().create("gpm1", "GaussianProcess");
    model.func("gpm1").active(false);

    model.study("std5").feature("uq").set("globalgpfunction", "gpm1");

    model.result().table().create("tbl8", "Table");
    model.result().table("tbl8")
         .addRows(new double[][]{{3.351636369717934E-6, 1.6503485728934143E-6, 2.0126316686528022E-7, 8.69934513461776E8}, {3.3306641098609407E-6, 1.6627327678798516E-6, 2.0060305889037571E-7, 8.732763450344542E8}, {3.3606016618469106E-6, 1.6427041251469649E-6, 2.0101256056114026E-7, 8.688120388032154E8}, {3.3378373111277173E-6, 1.6589523626221244E-6, 1.99834622545504E-7, 8.72291295677761E8}, {3.340499287026675E-6, 1.6488155716117903E-6, 1.987826874578544E-7, 8.728012991567684E8}, {3.354330187575892E-6, 1.6647324619615637E-6, 1.994708007083896E-7, 8.685686523487872E8}, {3.3467142208959724E-6, 1.6420156638538379E-6, 2.0175234540414562E-7, 8.715147739705322E8}, {3.3695185501810336E-6, 1.6468847534800963E-6, 1.9918232425690644E-7, 8.670473491836159E8}, {3.3632901132427974E-6, 1.6359901913666403E-6, 1.9923017253806183E-7, 8.691765987163668E8}, {3.3425621514578717E-6, 1.6381771668282064E-6, 1.9906624946616931E-7, 8.732076263806283E8}, {3.3278781351336986E-6, 1.6409641658377469E-6, 2.0044948764779644E-7, 8.756813668725253E8}, {3.350944106165254E-6, 1.6570253392757538E-6, 2.0058538403222916E-7, 8.696594758916104E8}, {3.3338104830825876E-6, 1.6461324264419245E-6, 1.997163360398743E-7, 8.741924978157226E8}, {3.3724511599030907E-6, 1.650855004417973E-6, 2.0070532414133465E-7, 8.658390207532744E8}, {3.364774867008596E-6, 1.6563414709563474E-6, 1.9825196045365357E-7, 8.674059126169509E8}, {3.3390461857785214E-6, 1.6553497535346282E-6, 2.015566008334717E-7, 8.720009638991233E8}, {3.3777284357278925E-6, 1.6450206115323086E-6, 2.000604046879065E-7, 8.653905088574055E8}, {3.3573093657358743E-6, 1.6390054302599132E-6, 2.0010889062348566E-7, 8.699558145131308E8}, {3.3734479564735982E-6, 1.6578076867670101E-6, 1.9992677790829177E-7, 8.652278274338545E8}, {3.359696045432886E-6, 1.6529303918305411E-6, 1.9950285650689974E-7, 8.684540962306049E8}, {3.344380513082959E-6, 1.6513910199225657E-6, 1.9986570926977843E-7, 8.715880296361043E8}, {3.3539689176463533E-6, 1.6482831246093116E-6, 1.9863159576469445E-7, 8.701578580230818E8}, {3.3493543207666656E-6, 1.6452209879560905E-6, 1.9959729493956454E-7, 8.711471680585147E8}, {3.3653651178220683E-6, 1.6609950640027788E-6, 2.0089649637219377E-7, 8.663869594854614E8}, {3.3474950756247107E-6, 1.6548275786896476E-6, 1.989226102346875E-7, 8.708645339899683E8}, {3.3242419182653175E-6, 1.6520193717836707E-6, 2.0016753695178735E-7, 8.755579705877569E8}, {3.345476777232826E-6, 1.6441334396668884E-6, 2.0038783702287505E-7, 8.71861701125904E8}, {3.3554532961999386E-6, 1.649611568650628E-6, 2.00243858744113E-7, 8.694304651697404E8}, {3.321005665817592E-6, 1.6533857555942353E-6, 1.9932741220089306E-7, 8.76272775938611E8}, {3.3350333471686894E-6, 1.6475235207039397E-6, 2.0115363947234712E-7, 8.735410506045911E8}});

    model.func("gpm1").set("source", "resultTable");
    model.func("gpm1").set("resultTable", "tbl8");
    model.func("gpm1").set("ignorenaninf", true);

    model.study("std5").feature("uq").set("designtable", "tbl8");

    model.func("gpm1").set("covfunction", "matern32");
    model.func("gpm1").set("meanfunction", "const");
    model.func("gpm1").set("improvementfunction", "entropy");
    model.func("gpm1").set("lastinternalseed", 1014);
    model.func("gpm1").set("gpadpoptmethod", "direct");
    model.func("gpm1").set("maxgpevals", 10000);
    model.func("gpm1").set("maxgpiters", 500);
    model.func("gpm1").set("adpevals", 10000);
    model.func("gpm1").set("setupfromstudy", "on");
    model.func("gpm1").setEntry("distributionselection", "col1", "normal");
    model.func("gpm1").setEntry("s1selection", "col1", "3.35E-6");
    model.func("gpm1").setEntry("s2selection", "col1", "1.675E-8");
    model.func("gpm1").setEntry("lboundselection", "col1", "3.3165E-6");
    model.func("gpm1").setEntry("uboundselection", "col1", "3.3835000000000003E-6");
    model.func("gpm1").setEntry("lcdfselection", "col1", "manual");
    model.func("gpm1").setEntry("ucdfselection", "col1", "manual");
    model.func("gpm1").setEntry("distributionselection", "col2", "normal");
    model.func("gpm1").setEntry("s1selection", "col2", "1.6499999999999999E-6");
    model.func("gpm1").setEntry("s2selection", "col2", "8.25E-9");
    model.func("gpm1").setEntry("lboundselection", "col2", "1.6335E-6");
    model.func("gpm1").setEntry("uboundselection", "col2", "1.6664999999999998E-6");
    model.func("gpm1").setEntry("lcdfselection", "col2", "manual");
    model.func("gpm1").setEntry("ucdfselection", "col2", "manual");
    model.func("gpm1").setEntry("distributionselection", "col3", "normal");
    model.func("gpm1").setEntry("s1selection", "col3", "2.0000000000000002E-7");
    model.func("gpm1").setEntry("s2selection", "col3", "1.0E-9");
    model.func("gpm1").setEntry("lboundselection", "col3", "1.9800000000000003E-7");
    model.func("gpm1").setEntry("uboundselection", "col3", "2.02E-7");
    model.func("gpm1").setEntry("lcdfselection", "col3", "manual");
    model.func("gpm1").setEntry("ucdfselection", "col3", "manual");
    model.func("gpm1").setEntry("args", "col1", "t_pe");
    model.func("gpm1").setEntry("unit", "col1", "m");
    model.func("gpm1").setEntry("columnType", "col2", "arg");
    model.func("gpm1").setEntry("args", "col2", "t_lil");
    model.func("gpm1").setEntry("unit", "col2", "m");
    model.func("gpm1").setEntry("columnType", "col3", "arg");
    model.func("gpm1").setEntry("args", "col3", "t_e");
    model.func("gpm1").setEntry("unit", "col3", "m");
    model.func("gpm1").setEntry("funcs", "col4", "gpm1_1");

    model.study("std5").feature("uq").set("computeaction", "append");
    model.study("std5").feature("uq").set("tablegraphgrp", "new");
    model.study("std5").label("\u7814\u7a76 5 - \u4e0d\u786e\u5b9a\u6027\u4f20\u64ad");
    model.study("std5").feature("uq").set("computeaction", "analysis");
    model.study("std5").createAutoSequences("all");

    model.sol().create("sol11");
    model.sol("sol11").study("std5");
    model.sol("sol11").label("\u53c2\u6570\u5316\u89e3 3");

    model.batch("pd3").feature("so2").set("psol", "sol11");
    model.batch("uq3").run("compute");

    model.result("pg8").run();
    model.result("pg8").run();

    model.study().create("std6");
    model.study("std6").feature().copy("uq", "std5/uq", "");
    model.study("std6").feature().copy("ref", "std5/ref", "");
    model.study("std6").feature("uq").set("uqtype", "screening");
    model.study("std6").feature("uq").set("uqresultgrp", "new");
    model.study("std6").feature("uq").set("uqtype", "sensitivityanalysis");
    model.study("std6").feature("uq").set("uqresultgrp", "new");
    model.study("std6").feature("uq").set("uqtype", "uncertaintypropagation");
    model.study("std6").feature("uq").set("uqresultgrp", "new");
    model.study("std6").feature("uq").set("uqtype", "reliabilityanalysis");
    model.study("std6").feature("uq").set("uqresultgrp", "new");

    return model;
  }

  public static Model run3(Model model) {
    model.study("std6").feature("uq").set("uqtype", "inverseuq");
    model.study("std6").feature("uq").set("uqresultgrp", "new");
    model.study("std6").feature("uq").set("uqtype", "reliabilityanalysis");

    model.func().duplicate("gpm2", "gpm1");

    model.study("std6").feature("uq").set("globalgpfunction", "gpm2");

    model.result().table().create("tbl17", "Table");
    model.result().table("tbl17")
         .addRows(new double[][]{{3.351636369717934E-6, 1.6503485728934143E-6, 2.0126316686528022E-7, 8.69934513461776E8}, {3.3306641098609407E-6, 1.6627327678798516E-6, 2.0060305889037571E-7, 8.732763450344542E8}, {3.3606016618469106E-6, 1.6427041251469649E-6, 2.0101256056114026E-7, 8.688120388032154E8}, {3.3378373111277173E-6, 1.6589523626221244E-6, 1.99834622545504E-7, 8.72291295677761E8}, {3.340499287026675E-6, 1.6488155716117903E-6, 1.987826874578544E-7, 8.728012991567684E8}, {3.354330187575892E-6, 1.6647324619615637E-6, 1.994708007083896E-7, 8.685686523487872E8}, {3.3467142208959724E-6, 1.6420156638538379E-6, 2.0175234540414562E-7, 8.715147739705322E8}, {3.3695185501810336E-6, 1.6468847534800963E-6, 1.9918232425690644E-7, 8.670473491836159E8}, {3.3632901132427974E-6, 1.6359901913666403E-6, 1.9923017253806183E-7, 8.691765987163668E8}, {3.3425621514578717E-6, 1.6381771668282064E-6, 1.9906624946616931E-7, 8.732076263806283E8}, {3.3278781351336986E-6, 1.6409641658377469E-6, 2.0044948764779644E-7, 8.756813668725253E8}, {3.350944106165254E-6, 1.6570253392757538E-6, 2.0058538403222916E-7, 8.696594758916104E8}, {3.3338104830825876E-6, 1.6461324264419245E-6, 1.997163360398743E-7, 8.741924978157226E8}, {3.3724511599030907E-6, 1.650855004417973E-6, 2.0070532414133465E-7, 8.658390207532744E8}, {3.364774867008596E-6, 1.6563414709563474E-6, 1.9825196045365357E-7, 8.674059126169509E8}, {3.3390461857785214E-6, 1.6553497535346282E-6, 2.015566008334717E-7, 8.720009638991233E8}, {3.3777284357278925E-6, 1.6450206115323086E-6, 2.000604046879065E-7, 8.653905088574055E8}, {3.3573093657358743E-6, 1.6390054302599132E-6, 2.0010889062348566E-7, 8.699558145131308E8}, {3.3734479564735982E-6, 1.6578076867670101E-6, 1.9992677790829177E-7, 8.652278274338545E8}, {3.359696045432886E-6, 1.6529303918305411E-6, 1.9950285650689974E-7, 8.684540962306049E8}, {3.344380513082959E-6, 1.6513910199225657E-6, 1.9986570926977843E-7, 8.715880296361043E8}, {3.3539689176463533E-6, 1.6482831246093116E-6, 1.9863159576469445E-7, 8.701578580230818E8}, {3.3493543207666656E-6, 1.6452209879560905E-6, 1.9959729493956454E-7, 8.711471680585147E8}, {3.3653651178220683E-6, 1.6609950640027788E-6, 2.0089649637219377E-7, 8.663869594854614E8}, {3.3474950756247107E-6, 1.6548275786896476E-6, 1.989226102346875E-7, 8.708645339899683E8}, {3.3242419182653175E-6, 1.6520193717836707E-6, 2.0016753695178735E-7, 8.755579705877569E8}, {3.345476777232826E-6, 1.6441334396668884E-6, 2.0038783702287505E-7, 8.71861701125904E8}, {3.3554532961999386E-6, 1.649611568650628E-6, 2.00243858744113E-7, 8.694304651697404E8}, {3.321005665817592E-6, 1.6533857555942353E-6, 1.9932741220089306E-7, 8.76272775938611E8}, {3.3350333471686894E-6, 1.6475235207039397E-6, 2.0115363947234712E-7, 8.735410506045911E8}});

    model.func("gpm2").set("source", "resultTable");
    model.func("gpm2").set("resultTable", "tbl17");
    model.func("gpm2").set("ignorenaninf", true);

    model.study("std6").feature("uq").set("designtable", "tbl17");

    model.func("gpm2").setEntry("funcs", "col4", "gpm2_1");

    model.study("std6").feature("uq").set("computeaction", "append");
    model.study("std6").feature("uq").set("tablegraphgrp", "new");
    model.study("std6").label("\u7814\u7a76 6 - \u53ef\u9760\u6027\u5206\u6790");
    model.study("std6").feature("uq").set("surrogatetol", 0.01);
    model.study("std6").feature("uq").setIndex("failif", "smaller", 0);
    model.study("std6").feature("uq").setIndex("threshold", "8.65e8[Hz]", 0);
    model.study("std6").createAutoSequences("all");

    model.sol().create("sol13");
    model.sol("sol13").study("std6");
    model.sol("sol13").label("\u53c2\u6570\u5316\u89e3 4");

    model.batch("pd4").feature("so2").set("psol", "sol13");
    model.batch("uq4").run("compute");

    model.result("pg7").run();

    model.study("std6").createAutoSequences("all");

    model.batch("uq4").run("postprocess");

    model.result("pg9").run();

    model.study().create("std7");
    model.study("std7").feature().copy("uq", "std4/uq", "");
    model.study("std7").feature().copy("ref", "std4/ref", "");
    model.study("std7").feature("uq").set("uqtype", "screening");
    model.study("std7").feature("uq").set("uqresultgrp", "new");
    model.study("std7").feature("uq").set("uqtype", "sensitivityanalysis");
    model.study("std7").feature("uq").set("uqresultgrp", "new");
    model.study("std7").feature("uq").set("uqtype", "uncertaintypropagation");
    model.study("std7").feature("uq").set("uqresultgrp", "new");
    model.study("std7").feature("uq").set("uqtype", "reliabilityanalysis");
    model.study("std7").feature("uq").set("uqresultgrp", "new");
    model.study("std7").feature("uq").set("uqtype", "inverseuq");
    model.study("std7").feature("uq").set("uqresultgrp", "new");
    model.study("std7").feature("uq").set("uqtype", "uncertaintypropagation");

    model.func().create("gpm3", "GaussianProcess");
    model.func("gpm3").active(false);

    model.study("std7").feature("uq").set("globalgpfunction", "gpm3");

    model.result().table().create("tbl25", "Table");
    model.result().table("tbl25")
         .addRows(new double[][]{{3.351636369717934E-6, 1.6503485728934143E-6, 2.0126316686528022E-7, 8.69934513461776E8}, {3.3306641098609407E-6, 1.6627327678798516E-6, 2.0060305889037571E-7, 8.732763450344542E8}, {3.3606016618469106E-6, 1.6427041251469649E-6, 2.0101256056114026E-7, 8.688120388032154E8}, {3.3378373111277173E-6, 1.6589523626221244E-6, 1.99834622545504E-7, 8.72291295677761E8}, {3.340499287026675E-6, 1.6488155716117903E-6, 1.987826874578544E-7, 8.728012991567684E8}, {3.354330187575892E-6, 1.6647324619615637E-6, 1.994708007083896E-7, 8.685686523487872E8}, {3.3467142208959724E-6, 1.6420156638538379E-6, 2.0175234540414562E-7, 8.715147739705322E8}, {3.3695185501810336E-6, 1.6468847534800963E-6, 1.9918232425690644E-7, 8.670473491836159E8}, {3.3632901132427974E-6, 1.6359901913666403E-6, 1.9923017253806183E-7, 8.691765987163668E8}, {3.3425621514578717E-6, 1.6381771668282064E-6, 1.9906624946616931E-7, 8.732076263806283E8}, {3.3278781351336986E-6, 1.6409641658377469E-6, 2.0044948764779644E-7, 8.756813668725253E8}, {3.350944106165254E-6, 1.6570253392757538E-6, 2.0058538403222916E-7, 8.696594758916104E8}, {3.3338104830825876E-6, 1.6461324264419245E-6, 1.997163360398743E-7, 8.741924978157226E8}, {3.3724511599030907E-6, 1.650855004417973E-6, 2.0070532414133465E-7, 8.658390207532744E8}, {3.364774867008596E-6, 1.6563414709563474E-6, 1.9825196045365357E-7, 8.674059126169509E8}, {3.3390461857785214E-6, 1.6553497535346282E-6, 2.015566008334717E-7, 8.720009638991233E8}, {3.3777284357278925E-6, 1.6450206115323086E-6, 2.000604046879065E-7, 8.653905088574055E8}, {3.3573093657358743E-6, 1.6390054302599132E-6, 2.0010889062348566E-7, 8.699558145131308E8}, {3.3734479564735982E-6, 1.6578076867670101E-6, 1.9992677790829177E-7, 8.652278274338545E8}, {3.359696045432886E-6, 1.6529303918305411E-6, 1.9950285650689974E-7, 8.684540962306049E8}, {3.344380513082959E-6, 1.6513910199225657E-6, 1.9986570926977843E-7, 8.715880296361043E8}, {3.3539689176463533E-6, 1.6482831246093116E-6, 1.9863159576469445E-7, 8.701578580230818E8}, {3.3493543207666656E-6, 1.6452209879560905E-6, 1.9959729493956454E-7, 8.711471680585147E8}, {3.3653651178220683E-6, 1.6609950640027788E-6, 2.0089649637219377E-7, 8.663869594854614E8}, {3.3474950756247107E-6, 1.6548275786896476E-6, 1.989226102346875E-7, 8.708645339899683E8}, {3.3242419182653175E-6, 1.6520193717836707E-6, 2.0016753695178735E-7, 8.755579705877569E8}, {3.345476777232826E-6, 1.6441334396668884E-6, 2.0038783702287505E-7, 8.71861701125904E8}, {3.3554532961999386E-6, 1.649611568650628E-6, 2.00243858744113E-7, 8.694304651697404E8}, {3.321005665817592E-6, 1.6533857555942353E-6, 1.9932741220089306E-7, 8.76272775938611E8}, {3.3350333471686894E-6, 1.6475235207039397E-6, 2.0115363947234712E-7, 8.735410506045911E8}});

    model.func("gpm3").set("source", "resultTable");
    model.func("gpm3").set("resultTable", "tbl25");
    model.func("gpm3").set("ignorenaninf", true);

    model.study("std7").feature("uq").set("designtable", "tbl25");

    model.func("gpm3").set("covfunction", "matern32");
    model.func("gpm3").set("meanfunction", "const");
    model.func("gpm3").set("improvementfunction", "entropy");
    model.func("gpm3").set("lastinternalseed", 1014);
    model.func("gpm3").set("gpadpoptmethod", "direct");
    model.func("gpm3").set("maxgpevals", 10000);
    model.func("gpm3").set("maxgpiters", 500);
    model.func("gpm3").set("adpevals", 10000);
    model.func("gpm3").set("setupfromstudy", "on");
    model.func("gpm3").setEntry("distributionselection", "col1", "normal");
    model.func("gpm3").setEntry("s1selection", "col1", "3.35E-6");
    model.func("gpm3").setEntry("s2selection", "col1", "1.675E-8");
    model.func("gpm3").setEntry("lboundselection", "col1", "3.3165E-6");
    model.func("gpm3").setEntry("uboundselection", "col1", "3.3835000000000003E-6");
    model.func("gpm3").setEntry("lcdfselection", "col1", "manual");
    model.func("gpm3").setEntry("ucdfselection", "col1", "manual");
    model.func("gpm3").setEntry("distributionselection", "col2", "normal");
    model.func("gpm3").setEntry("s1selection", "col2", "1.6499999999999999E-6");
    model.func("gpm3").setEntry("s2selection", "col2", "8.25E-9");
    model.func("gpm3").setEntry("lboundselection", "col2", "1.6335E-6");
    model.func("gpm3").setEntry("uboundselection", "col2", "1.6664999999999998E-6");
    model.func("gpm3").setEntry("lcdfselection", "col2", "manual");
    model.func("gpm3").setEntry("ucdfselection", "col2", "manual");
    model.func("gpm3").setEntry("distributionselection", "col3", "normal");
    model.func("gpm3").setEntry("s1selection", "col3", "2.0000000000000002E-7");
    model.func("gpm3").setEntry("s2selection", "col3", "1.0E-9");
    model.func("gpm3").setEntry("lboundselection", "col3", "1.9800000000000003E-7");
    model.func("gpm3").setEntry("uboundselection", "col3", "2.02E-7");
    model.func("gpm3").setEntry("lcdfselection", "col3", "manual");
    model.func("gpm3").setEntry("ucdfselection", "col3", "manual");
    model.func("gpm3").setEntry("args", "col1", "t_pe");
    model.func("gpm3").setEntry("unit", "col1", "m");
    model.func("gpm3").setEntry("args", "col2", "t_lil");
    model.func("gpm3").setEntry("unit", "col2", "m");
    model.func("gpm3").setEntry("args", "col3", "t_e");
    model.func("gpm3").setEntry("unit", "col3", "m");
    model.func("gpm3").setEntry("funcs", "col4", "gpm3_1");

    model.study("std7").feature("uq").set("computeaction", "append");
    model.study("std7").feature("uq").set("tablegraphgrp", "new");
    model.study("std7").label("\u7814\u7a76 7 - \u4e0d\u786e\u5b9a\u6027\u4f20\u64ad\uff0c\u76f8\u5173");
    model.study("std7").feature("uq").set("surrogatetol", 0.005);
    model.study("std7").feature("uq").set("correlatedinput", true);
    model.study("std7").feature("uq").setIndex("inputCorrelations", "", 0, 0);
    model.study("std7").feature("uq").setIndex("inputCorrelations", "", 0, 1);
    model.study("std7").feature("uq").setIndex("inputCorrelations", "0x0", 0, 2);
    model.study("std7").feature("uq").setIndex("inputCorrelations", "on", 0, 3);
    model.study("std7").feature("uq").setIndex("inputCorrelations", "", 0, 0);
    model.study("std7").feature("uq").setIndex("inputCorrelations", "", 0, 1);
    model.study("std7").feature("uq").setIndex("inputCorrelations", "0x0", 0, 2);
    model.study("std7").feature("uq").setIndex("inputCorrelations", "on", 0, 3);
    model.study("std7").feature("uq").setIndex("inputCorrelations", "{1,0.7,1,0.4,0.15,1}", 0, 1);
    model.study("std7").createAutoSequences("all");

    model.sol().create("sol16");
    model.sol("sol16").study("std7");
    model.sol("sol16").label("\u53c2\u6570\u5316\u89e3 5");

    model.batch("pd5").feature("so2").set("psol", "sol16");
    model.batch("uq5").run("compute");

    model.result("pg10").run();

    model.study().create("std8");
    model.study("std8").feature().copy("uq", "std7/uq", "");
    model.study("std8").feature().copy("ref", "std7/ref", "");
    model.study("std8").feature("uq").set("uqtype", "screening");
    model.study("std8").feature("uq").set("uqresultgrp", "new");
    model.study("std8").feature("uq").set("uqtype", "sensitivityanalysis");
    model.study("std8").feature("uq").set("uqresultgrp", "new");
    model.study("std8").feature("uq").set("uqtype", "uncertaintypropagation");
    model.study("std8").feature("uq").set("uqresultgrp", "new");
    model.study("std8").feature("uq").set("uqtype", "reliabilityanalysis");
    model.study("std8").feature("uq").set("uqresultgrp", "new");
    model.study("std8").feature("uq").set("uqtype", "inverseuq");
    model.study("std8").feature("uq").set("uqresultgrp", "new");
    model.study("std8").feature("uq").set("uqtype", "reliabilityanalysis");

    model.func().duplicate("gpm4", "gpm3");

    model.study("std8").feature("uq").set("globalgpfunction", "gpm4");

    model.result().table().create("tbl34", "Table");
    model.result().table("tbl34")
         .addRows(new double[][]{{3.351636369717934E-6, 1.6503485728934143E-6, 2.0126316686528022E-7, 8.69934513461776E8}, {3.3306641098609407E-6, 1.6627327678798516E-6, 2.0060305889037571E-7, 8.732763450344542E8}, {3.3606016618469106E-6, 1.6427041251469649E-6, 2.0101256056114026E-7, 8.688120388032154E8}, {3.3378373111277173E-6, 1.6589523626221244E-6, 1.99834622545504E-7, 8.72291295677761E8}, {3.340499287026675E-6, 1.6488155716117903E-6, 1.987826874578544E-7, 8.728012991567684E8}, {3.354330187575892E-6, 1.6647324619615637E-6, 1.994708007083896E-7, 8.685686523487872E8}, {3.3467142208959724E-6, 1.6420156638538379E-6, 2.0175234540414562E-7, 8.715147739705322E8}, {3.3695185501810336E-6, 1.6468847534800963E-6, 1.9918232425690644E-7, 8.670473491836159E8}, {3.3632901132427974E-6, 1.6359901913666403E-6, 1.9923017253806183E-7, 8.691765987163668E8}, {3.3425621514578717E-6, 1.6381771668282064E-6, 1.9906624946616931E-7, 8.732076263806283E8}, {3.3278781351336986E-6, 1.6409641658377469E-6, 2.0044948764779644E-7, 8.756813668725253E8}, {3.350944106165254E-6, 1.6570253392757538E-6, 2.0058538403222916E-7, 8.696594758916104E8}, {3.3338104830825876E-6, 1.6461324264419245E-6, 1.997163360398743E-7, 8.741924978157226E8}, {3.3724511599030907E-6, 1.650855004417973E-6, 2.0070532414133465E-7, 8.658390207532744E8}, {3.364774867008596E-6, 1.6563414709563474E-6, 1.9825196045365357E-7, 8.674059126169509E8}, {3.3390461857785214E-6, 1.6553497535346282E-6, 2.015566008334717E-7, 8.720009638991233E8}, {3.3777284357278925E-6, 1.6450206115323086E-6, 2.000604046879065E-7, 8.653905088574055E8}, {3.3573093657358743E-6, 1.6390054302599132E-6, 2.0010889062348566E-7, 8.699558145131308E8}, {3.3734479564735982E-6, 1.6578076867670101E-6, 1.9992677790829177E-7, 8.652278274338545E8}, {3.359696045432886E-6, 1.6529303918305411E-6, 1.9950285650689974E-7, 8.684540962306049E8}, {3.344380513082959E-6, 1.6513910199225657E-6, 1.9986570926977843E-7, 8.715880296361043E8}, {3.3539689176463533E-6, 1.6482831246093116E-6, 1.9863159576469445E-7, 8.701578580230818E8}, {3.3493543207666656E-6, 1.6452209879560905E-6, 1.9959729493956454E-7, 8.711471680585147E8}, {3.3653651178220683E-6, 1.6609950640027788E-6, 2.0089649637219377E-7, 8.663869594854614E8}, {3.3474950756247107E-6, 1.6548275786896476E-6, 1.989226102346875E-7, 8.708645339899683E8}, {3.3242419182653175E-6, 1.6520193717836707E-6, 2.0016753695178735E-7, 8.755579705877569E8}, {3.345476777232826E-6, 1.6441334396668884E-6, 2.0038783702287505E-7, 8.71861701125904E8}, {3.3554532961999386E-6, 1.649611568650628E-6, 2.00243858744113E-7, 8.694304651697404E8}, {3.321005665817592E-6, 1.6533857555942353E-6, 1.9932741220089306E-7, 8.76272775938611E8}, {3.3350333471686894E-6, 1.6475235207039397E-6, 2.0115363947234712E-7, 8.735410506045911E8}, {3.3637620999457256E-6, 1.6493233122462366E-6, 1.9864071761287537E-7, 8.681056998463415E8}, {3.329604756861686E-6, 1.6517486324507041E-6, 1.9800293776982779E-7, 8.749243197574674E8}, {3.3702946315651597E-6, 1.6549257317283633E-6, 2.0143209350212644E-7, 8.657940018522465E8}, {3.348719257880377E-6, 1.6523430559910667E-6, 2.0020931472696499E-7, 8.705666557400287E8}, {3.356637447155081E-6, 1.6601065223105915E-6, 1.9926726403083122E-7, 8.685256165122768E8}, {3.3834872346728783E-6, 1.6445693165752486E-6, 1.9910049065435988E-7, 8.644713006095854E8}, {3.3577928858375028E-6, 1.6617384643454898E-6, 2.0091897957568137E-7, 8.678340652710048E8}, {3.3462761689991452E-6, 1.6400377726732995E-6, 2.0132126873970503E-7, 8.718523728069177E8}, {3.3532746081102536E-6, 1.6541620321500494E-6, 1.999614746642319E-7, 8.695503084988725E8}, {3.3169708782008283E-6, 1.6415862291378788E-6, 2.003651384494397E-7, 8.778737407073643E8}, {3.3367173644975828E-6, 1.6499388175160071E-6, 2.0048066046492484E-7, 8.731344341326609E8}, {3.3420210903106654E-6, 1.635399854032596E-6, 1.9988451021164525E-7, 8.733836515714226E8}, {3.3438115188546327E-6, 1.647702360397217E-6, 1.994333080237937E-7, 8.720936802016168E8}, {3.3613289609601414E-6, 1.6584396458136637E-6, 2.0065295492756663E-7, 8.674492778337116E8}, {3.335894559258435E-6, 1.6464072242811313E-6, 1.9972468683663567E-7, 8.7374515282411E8}, {3.383499164235241E-6, 1.6664998730566986E-6, 2.0199999963303606E-7, 8.621266747832639E8}, {3.316502507044117E-6, 1.6335000055705566E-6, 1.9800000555342093E-7, 8.791263880123075E8}, {3.319550712409418E-6, 1.633500107971189E-6, 2.019994529315177E-7, 8.776921674227082E8}, {3.3823592512516333E-6, 1.6664999740151123E-6, 1.980010557395384E-7, 8.631323319663154E8}, {3.382322774627985E-6, 1.6394163681553863E-6, 2.0199999991760824E-7, 8.645529694668927E8}, {3.3721071683135462E-6, 1.6664967796701455E-6, 2.0199904979577395E-7, 8.643817807545315E8}, {3.333626101785378E-6, 1.6335104004153438E-6, 1.9800181363394946E-7, 8.756237093497477E8}, {3.3833632135478642E-6, 1.6664999862991428E-6, 2.0039448269882433E-7, 8.624665928164456E8}, {3.3213034981181404E-6, 1.6419810438623957E-6, 1.9800004860003826E-7, 8.774321796881794E8}, {3.3218364897160476E-6, 1.6335048596957034E-6, 1.9918755817744814E-7, 8.777923167008325E8}, {3.350742724298045E-6, 1.6664234420765726E-6, 1.9800002637584775E-7, 8.694410285604492E8}, {3.375737497977903E-6, 1.6664878728914775E-6, 1.9849555957963872E-7, 8.643486872732952E8}, {3.3646034759333748E-6, 1.6463948900376065E-6, 2.019999446943919E-7, 8.675135507687241E8}, {3.331650735965697E-6, 1.6335346170110332E-6, 2.016868087549496E-7, 8.752830322168174E8}, {3.321080114761517E-6, 1.6367439610696556E-6, 2.0156575617166428E-7, 8.771956768340105E8}});

    model.func("gpm4").set("source", "resultTable");
    model.func("gpm4").set("resultTable", "tbl34");
    model.func("gpm4").set("ignorenaninf", true);

    model.study("std8").feature("uq").set("designtable", "tbl34");

    model.func("gpm4").setEntry("funcs", "col4", "gpm4_1");

    model.study("std8").feature("uq").set("computeaction", "append");
    model.study("std8").feature("uq").set("tablegraphgrp", "new");
    model.study("std8").label("\u7814\u7a76 8 - \u53ef\u9760\u6027\u5206\u6790\uff0c\u76f8\u5173");
    model.study("std8").feature("uq").setIndex("failif", "smaller", 0);
    model.study("std8").feature("uq").setIndex("threshold", "8.65e8[Hz]", 0);
    model.study("std8").createAutoSequences("all");

    model.sol().create("sol19");
    model.sol("sol19").study("std8");
    model.sol("sol19").label("\u53c2\u6570\u5316\u89e3 6");

    model.batch("pd6").feature("so2").set("psol", "sol19");
    model.batch("uq6").run("compute");

    model.result("pg7").run();

    model.study("std8").createAutoSequences("all");

    model.batch("uq6").run("postprocess");

    model.result("pg11").run();

    model
         .title("\u4f7f\u7528\u4e0d\u786e\u5b9a\u6027\u91cf\u5316\u7814\u7a76\u56fa\u6001\u88c5\u914d\u8c10\u632f\u5668\uff08\u4e8c\u7ef4\uff09");

    model
         .description("\u56fa\u6001\u88c5\u914d\u8c10\u632f\u5668 (SMR) \u662f\u7531\u591a\u5c42\u6750\u6599\u5236\u6210\u7684 MEMS \u538b\u7535\u8c10\u632f\u5668\u3002\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u201c\u4e0d\u786e\u5b9a\u6027\u91cf\u5316\u201d\u6765\u7814\u7a76\u5236\u9020\u53d8\u5316\u5bf9 SMR \u8c10\u632f\u9891\u7387\u7684\u5f71\u54cd\uff0c\u4ee5\u53ca\u5982\u4f55\u5c06\u201c\u7075\u654f\u5ea6\u5206\u6790\u201d\u3001\u201c\u4e0d\u786e\u5b9a\u6027\u4f20\u64ad\u201d\u548c\u201c\u53ef\u9760\u6027\u5206\u6790\u201d\u4e0e\u201c\u7279\u5f81\u9891\u7387\u7814\u7a76\u201d\u76f8\u7ed3\u5408\uff0c\u4ee5\u5b9e\u73b0\u66f4\u5168\u9762\u7684\u5206\u6790\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
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
    model.sol("sol3").clearSolutionData();

    model.label("solidly_mounted_resonator_2d_uncertainty_quantification.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
