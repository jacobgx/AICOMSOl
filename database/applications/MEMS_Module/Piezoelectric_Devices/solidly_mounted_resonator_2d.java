/*
 * solidly_mounted_resonator_2d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:00 by COMSOL 6.3.0.290. */
public class solidly_mounted_resonator_2d {

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

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("solidly_mounted_resonator_2d.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
