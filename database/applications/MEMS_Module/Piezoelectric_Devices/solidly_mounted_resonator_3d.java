/*
 * solidly_mounted_resonator_3d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:01 by COMSOL 6.3.0.290. */
public class solidly_mounted_resonator_3d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\MEMS_Module\\Piezoelectric_Devices");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics("solid").create("pzm1", "PiezoelectricMaterialModel");
    model.component("comp1").physics("solid").feature("pzm1").selection().all();
    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics("es").create("ccnp1", "ChargeConservationPiezo");
    model.component("comp1").physics("es").feature("ccnp1").selection().all();

    model.component("comp1").multiphysics().create("pze1", "PiezoelectricEffect", 3);
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
    model.param("par2").set("p1", "0");
    model.param("par2").descr("p1", "\u9897\u7c92 1 \u7684\u5f00\u5173");
    model.param("par2").set("p2", "0");
    model.param("par2").descr("p2", "\u9897\u7c92 2 \u7684\u5f00\u5173");
    model.param("par2").set("p3", "0");
    model.param("par2").descr("p3", "\u9897\u7c92 3 \u7684\u5f00\u5173");
    model.param("par2").set("p4", "0");
    model.param("par2").descr("p4", "\u9897\u7c92 4 \u7684\u5f00\u5173");
    model.param("par2").set("p5", "0");
    model.param("par2").descr("p5", "\u9897\u7c92 5 \u7684\u5f00\u5173");
    model.param("par2").set("p6", "0");
    model.param("par2").descr("p6", "\u9897\u7c92 6 \u7684\u5f00\u5173");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").label("\u538b\u7535 - ZnO");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"w_pe/2", "w_pe/2", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "t_pe", 2);
    model.component("comp1").geom("geom1").feature("blk1").set("selresult", true);
    model.component("comp1").geom("geom1").feature().duplicate("blk2", "blk1");
    model.component("comp1").geom("geom1").feature("blk2").label("\u5e95\u90e8\u7535\u6781");
    model.component("comp1").geom("geom1").feature("blk2").setIndex("size", "t_e", 2);
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"0", "0", "-t_e"});
    model.component("comp1").geom("geom1").feature("blk2").set("selresultshow", "all");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("Al");
    model.component("comp1").geom("geom1").feature("blk2").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature().duplicate("blk3", "blk2");
    model.component("comp1").geom("geom1").feature("blk3").label("\u9876\u90e8\u7535\u6781");
    model.component("comp1").geom("geom1").feature("blk3").set("size", new String[]{"w_ar/2", "w_ar/2", "t_e"});
    model.component("comp1").geom("geom1").feature("blk3").set("pos", new String[]{"0", "0", "t_pe"});
    model.component("comp1").geom("geom1").run("blk3");
    model.component("comp1").geom("geom1").create("blk4", "Block");
    model.component("comp1").geom("geom1").feature("blk4").label("\u4f4e\u963b\u6297 - SiO2");
    model.component("comp1").geom("geom1").feature("blk4").set("size", new String[]{"w/2", "w/2", "t_lil"});
    model.component("comp1").geom("geom1").feature("blk4").set("pos", new String[]{"0", "0", "-t_lil-t_e"});
    model.component("comp1").geom("geom1").feature("blk4").setIndex("layer", "(w-w_pe)/2", 0);
    model.component("comp1").geom("geom1").feature("blk4").set("layerright", true);
    model.component("comp1").geom("geom1").feature("blk4").set("layerback", true);
    model.component("comp1").geom("geom1").feature("blk4").set("layerbottom", false);
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("SiO2");
    model.component("comp1").geom("geom1").feature("blk4").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("blk4");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").label("\u9635\u5217 - SiO2");
    model.component("comp1").geom("geom1").feature("arr1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("arr1").selection("input").named("csel2");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{1, 1, 3});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"0", "0", "-t_lil-t_hil"});
    model.component("comp1").geom("geom1").feature().duplicate("blk5", "blk4");
    model.component("comp1").geom("geom1").feature("blk5").label("\u9ad8\u963b\u6297 - Mo");
    model.component("comp1").geom("geom1").feature("blk5").set("size", new String[]{"w/2", "w/2", "t_hil"});
    model.component("comp1").geom("geom1").feature("blk5").set("pos", new String[]{"0", "0", "-t_hil-t_lil-t_e"});
    model.component("comp1").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel3").label("Mo");
    model.component("comp1").geom("geom1").feature("blk5").set("contributeto", "csel3");
    model.component("comp1").geom("geom1").feature().duplicate("arr2", "arr1");
    model.component("comp1").geom("geom1").feature("arr2").label("\u9635\u5217 - Mo");
    model.component("comp1").geom("geom1").runPre("arr2");
    model.component("comp1").geom("geom1").feature("arr2").selection("input").named("csel3");
    model.component("comp1").geom("geom1").run("arr2");
    model.component("comp1").geom("geom1").feature().duplicate("blk6", "blk4");
    model.component("comp1").geom("geom1").feature("blk6").label("\u7edd\u7f18\u4f53 - SiO2");
    model.component("comp1").geom("geom1").feature("blk6").set("size", new String[]{"w/2", "w/2", "t_i"});
    model.component("comp1").geom("geom1").feature("blk6")
         .set("pos", new String[]{"0", "0", "-3*t_hil-3*t_lil-t_e-t_i"});
    model.component("comp1").geom("geom1").feature().duplicate("blk7", "blk6");
    model.component("comp1").geom("geom1").feature("blk7").label("\u57fa\u677f - Si");
    model.component("comp1").geom("geom1").feature("blk7").set("size", new String[]{"w/2", "w/2", "t_s"});
    model.component("comp1").geom("geom1").feature("blk7")
         .set("pos", new String[]{"0", "0", "-3*t_hil-3*t_lil-t_e-t_i-t_s"});
    model.component("comp1").geom("geom1").feature("blk7").set("selresult", true);
    model.component("comp1").geom("geom1").selection().create("csel4", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel4").label("Si");
    model.component("comp1").geom("geom1").feature("blk7").set("contributeto", "csel4");
    model.component("comp1").geom("geom1").feature().duplicate("blk8", "blk7");
    model.component("comp1").geom("geom1").feature("blk8").label("\u5e95\u90e8 PML - Si");
    model.component("comp1").geom("geom1").feature("blk8").set("size", new String[]{"w/2", "w/2", "lambda_Si"});
    model.component("comp1").geom("geom1").feature("blk8")
         .set("pos", new String[]{"0", "0", "-3*t_hil-3*t_lil-t_e-t_i-t_s-lambda_Si"});
    model.component("comp1").geom("geom1").run("blk8");
    model.component("comp1").geom("geom1").create("blk9", "Block");
    model.component("comp1").geom("geom1").feature("blk9").label("\u9897\u7c92 1");
    model.component("comp1").geom("geom1").feature("blk9").set("pos", new String[]{"15", "25", "t_pe+t_e"});
    model.component("comp1").geom("geom1").feature("blk9").set("selresult", true);
    model.component("comp1").geom("geom1").selection().create("csel5", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel5").label("\u9897\u7c92");
    model.component("comp1").geom("geom1").feature("blk9").set("contributeto", "csel5");
    model.component("comp1").geom("geom1").feature().duplicate("blk10", "blk9");
    model.component("comp1").geom("geom1").feature("blk10").label("\u9897\u7c92 2");
    model.component("comp1").geom("geom1").feature("blk10").set("pos", new String[]{"50", "15", "t_pe+t_e"});
    model.component("comp1").geom("geom1").feature().duplicate("blk11", "blk10");
    model.component("comp1").geom("geom1").feature("blk11").label("\u9897\u7c92 3");
    model.component("comp1").geom("geom1").feature("blk11").set("pos", new String[]{"39", "51", "t_pe+t_e"});
    model.component("comp1").geom("geom1").feature().duplicate("blk12", "blk11");
    model.component("comp1").geom("geom1").feature("blk12").label("\u9897\u7c92 4");
    model.component("comp1").geom("geom1").feature("blk12").set("pos", new String[]{"55", "35", "t_pe+t_e"});
    model.component("comp1").geom("geom1").feature().duplicate("blk13", "blk12");
    model.component("comp1").geom("geom1").feature("blk13").label("\u9897\u7c92 5");
    model.component("comp1").geom("geom1").feature("blk13").set("pos", new String[]{"62", "80", "t_pe+t_e"});
    model.component("comp1").geom("geom1").feature().duplicate("blk14", "blk13");
    model.component("comp1").geom("geom1").feature("blk14").label("\u9897\u7c92 6");
    model.component("comp1").geom("geom1").feature("blk14").set("pos", new String[]{"85", "55", "t_pe+t_e"});
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("box1", "Box");
    model.component("comp1").selection("box1").label("\u5bf9\u79f0\u8fb9\u754c\u6761\u4ef6 1");
    model.component("comp1").selection("box1").set("entitydim", 2);
    model.component("comp1").selection("box1").set("xmax", "eps");
    model.component("comp1").selection("box1").set("condition", "inside");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").selection().duplicate("box2", "box1");
    model.component("comp1").selection("box2").label("\u5bf9\u79f0\u8fb9\u754c\u6761\u4ef6 2");
    model.component("comp1").selection("box2").set("xmax", Double.POSITIVE_INFINITY);
    model.component("comp1").selection("box2").set("ymax", "eps");
    model.component("comp1").selection().duplicate("box3", "box2");
    model.component("comp1").selection("box3").label("\u5e95\u90e8\u56fa\u5b9a\u8fb9\u754c\u6761\u4ef6");
    model.component("comp1").selection("box3").set("ymax", Double.POSITIVE_INFINITY);
    model.component("comp1").selection("box3").set("zmax", "-3*t_hil-3*t_lil-t_e-t_i-t_s-lambda_Si/2");
    model.component("comp1").selection().duplicate("box4", "box3");
    model.component("comp1").selection("box4").label("\u56fa\u5b9a\u8fb9\u754c\u6761\u4ef6\u4fa7 1");
    model.component("comp1").selection("box4").set("xmin", "(w/2+w_pe/2)/2");
    model.component("comp1").selection("box4").set("zmax", Double.POSITIVE_INFINITY);
    model.component("comp1").selection().duplicate("box5", "box4");
    model.component("comp1").selection("box5").label("\u56fa\u5b9a\u8fb9\u754c\u6761\u4ef6\u4fa7 2");
    model.component("comp1").selection("box5").set("xmin", Double.NEGATIVE_INFINITY);
    model.component("comp1").selection("box5").set("ymin", "(w/2+w_pe/2)/2");
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u56fa\u5b9a\u8fb9\u754c\u6761\u4ef6");
    model.component("comp1").selection("uni1").set("entitydim", 2);
    model.component("comp1").selection("uni1").set("input", new String[]{"box3", "box4", "box5"});
    model.component("comp1").selection().create("box6", "Box");
    model.component("comp1").selection("box6").label("\u975e PML");
    model.component("comp1").selection("box6").set("xmax", "(w/2+w_pe/2)/2");
    model.component("comp1").selection("box6").set("ymax", "(w/2+w_pe/2)/2");
    model.component("comp1").selection("box6").set("zmin", "-3*t_hil-3*t_lil-t_e-t_i-t_s-lambda_Si/2");
    model.component("comp1").selection("box6").set("condition", "inside");
    model.component("comp1").selection().create("com1", "Complement");
    model.component("comp1").selection("com1").label("PML");
    model.component("comp1").selection("com1").set("input", new String[]{"box6"});
    model.component("comp1").selection().duplicate("box7", "box3");
    model.component("comp1").selection("box7").label("\u9897\u7c92\u9876\u9762");
    model.component("comp1").selection("box7").set("zmin", "t_pe+t_e+1/2");
    model.component("comp1").selection("box7").set("zmax", Double.POSITIVE_INFINITY);
    model.component("comp1").selection().create("uni2", "Union");
    model.component("comp1").selection("uni2").label("\u58f0\u955c");
    model.component("comp1").selection("uni2").set("input", new String[]{"geom1_csel3_dom", "geom1_arr1_dom"});

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").label("\u79ef\u5206 - \u9897\u7c92");
    model.component("comp1").cpl("intop1").selection().named("geom1_csel5_dom");

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().named("com1");

    model.component("comp1").physics("solid").feature("lemm1").create("dmp1", "Damping", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1")
         .set("DampingType", "IsotropicLossFactor");
    model.component("comp1").physics("solid").feature("pzm1").selection().named("geom1_blk1_dom");
    model.component("comp1").physics("solid").feature("pzm1").create("mdmp1", "MechanicalDamping", 3);
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().named("uni1");
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection().named("box1");
    model.component("comp1").physics("solid").create("sym2", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym2").selection().named("box2");
    model.component("comp1").physics("es").selection().named("geom1_blk1_dom");
    model.component("comp1").physics("es").create("gnd1", "Ground", 2);
    model.component("comp1").physics("es").feature("gnd1").selection().named("geom1_blk2_bnd");
    model.component("comp1").physics("es").create("term1", "Terminal", 2);
    model.component("comp1").physics("es").feature("term1").selection().named("geom1_blk3_bnd");
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
    model.component("comp1").material("mat1").selection().named("geom1_blk1_dom");
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

    return model;
  }

  public static Model run2(Model model) {
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

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("\u53d8\u91cf - \u9897\u7c92 1");
    model.component("comp1").variable("var1").selection().geom("geom1", 3);
    model.component("comp1").variable("var1").selection().named("geom1_blk9_dom");
    model.component("comp1").variable("var1").set("p", "p1");
    model.component("comp1").variable("var1").descr("p", "\u9897\u7c92 1 \u7684\u5f00\u5173");
    model.component("comp1").variable().duplicate("var2", "var1");
    model.component("comp1").variable("var2").label("\u53d8\u91cf - \u9897\u7c92 2");
    model.component("comp1").variable("var2").selection().named("geom1_blk10_dom");
    model.component("comp1").variable("var2").set("p", "p2");
    model.component("comp1").variable("var2").descr("p", "\u9897\u7c92 2 \u7684\u5f00\u5173");
    model.component("comp1").variable().duplicate("var3", "var2");
    model.component("comp1").variable("var3").label("\u53d8\u91cf - \u9897\u7c92 3");
    model.component("comp1").variable("var3").selection().named("geom1_blk11_dom");
    model.component("comp1").variable("var3").set("p", "p3");
    model.component("comp1").variable("var3").descr("p", "\u9897\u7c92 3 \u7684\u5f00\u5173");
    model.component("comp1").variable().duplicate("var4", "var3");
    model.component("comp1").variable("var4").label("\u53d8\u91cf - \u9897\u7c92 4");
    model.component("comp1").variable("var4").selection().named("geom1_blk12_dom");
    model.component("comp1").variable("var4").set("p", "p4");
    model.component("comp1").variable("var4").descr("p", "\u9897\u7c92 4 \u7684\u5f00\u5173");
    model.component("comp1").variable().duplicate("var5", "var4");
    model.component("comp1").variable("var5").label("\u53d8\u91cf - \u9897\u7c92 5");
    model.component("comp1").variable("var5").selection().named("geom1_blk13_dom");
    model.component("comp1").variable("var5").set("p", "p5");
    model.component("comp1").variable("var5").descr("p", "\u9897\u7c92 5 \u7684\u5f00\u5173");
    model.component("comp1").variable().duplicate("var6", "var5");
    model.component("comp1").variable("var6").label("\u53d8\u91cf - \u9897\u7c92 6");
    model.component("comp1").variable("var6").selection().named("geom1_blk14_dom");
    model.component("comp1").variable("var6").set("p", "p6");
    model.component("comp1").variable("var6").descr("p", "\u9897\u7c92 6 \u7684\u5f00\u5173");

    model.component("comp1").material().duplicate("mat6", "mat3");
    model.component("comp1").material("mat6").label("SiO2 \u9897\u7c92");
    model.component("comp1").material("mat6").selection().named("geom1_csel5_dom");
    model.component("comp1").material("mat6").propertyGroup("def").set("density", new String[]{"p*rho_SiO2"});

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1")
         .label("\u81ea\u7531\u4e09\u89d2\u5f62\u7f51\u683c - \u9897\u7c92\u7684\u9876\u9762");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("box7");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").label("\u626b\u63a0 - \u9897\u7c92");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().named("geom1_csel5_dom");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 3);
    model.component("comp1").mesh("mesh1").create("ftri2", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri2")
         .label("\u81ea\u7531\u4e09\u89d2\u5f62\u7f51\u683c - \u9876\u90e8\u7535\u6781");
    model.component("comp1").mesh("mesh1").feature("ftri2").selection().set(37);
    model.component("comp1").mesh("mesh1").feature("ftri2").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hmax", 10);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hmin", 1);
    model.component("comp1").mesh("mesh1").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe2").label("\u626b\u63a0 - \u9876\u90e8\u7535\u6781");
    model.component("comp1").mesh("mesh1").feature("swe2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe2").selection().named("geom1_blk3_dom");
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("ftri3", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri3")
         .label("\u81ea\u7531\u4e09\u89d2\u5f62\u7f51\u683c - \u538b\u7535");
    model.component("comp1").mesh("mesh1").feature("ftri3").selection().set(39);
    model.component("comp1").mesh("mesh1").create("swe3", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe3").label("\u626b\u63a0 - \u538b\u7535");
    model.component("comp1").mesh("mesh1").feature("swe3").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe3").selection().named("geom1_blk1_dom");
    model.component("comp1").mesh("mesh1").feature("swe3").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe3").feature("dis1").set("numelem", 6);
    model.component("comp1").mesh("mesh1").create("swe4", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe4").label("\u626b\u63a0 - \u5e95\u90e8\u7535\u6781");
    model.component("comp1").mesh("mesh1").feature("swe4").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe4").selection().named("geom1_blk2_dom");
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").label("\u6620\u5c04 - PML");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(68, 144, 173);
    model.component("comp1").mesh("mesh1").feature("map1").set("adjustedgdistr", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(233, 234);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 3);
    model.component("comp1").mesh("mesh1").create("swe5", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe5").label("\u626b\u63a0 - \u58f0\u955c");
    model.component("comp1").mesh("mesh1").feature("swe5").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe5").selection().named("uni2");
    model.component("comp1").mesh("mesh1").feature("swe5").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe5").feature("dis1").set("numelem", 3);
    model.component("comp1").mesh("mesh1").create("swe6", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe6").label("\u626b\u63a0 - \u5269\u4f59\u90e8\u5206");
    model.component("comp1").mesh("mesh1").feature("swe6").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe6").feature("dis1").label("\u5206\u5e03 - \u57fa\u677f");
    model.component("comp1").mesh("mesh1").feature("swe6").feature("dis1").selection().set();
    model.component("comp1").mesh("mesh1").feature("swe6").feature("dis1").selection().named("geom1_blk7_dom");
    model.component("comp1").mesh("mesh1").feature("swe6").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe6").feature("dis1").set("elemcount", 12);
    model.component("comp1").mesh("mesh1").feature("swe6").feature("dis1").set("elemratio", 5);
    model.component("comp1").mesh("mesh1").feature("swe6").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe6").feature("dis2").label("\u5206\u5e03 - PML");
    model.component("comp1").mesh("mesh1").feature("swe6").feature("dis2").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("swe6").feature("dis2").set("numelem", 3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1 - \u7279\u5f81\u9891\u7387\u548c\u7075\u654f\u5ea6");
    model.study("std1").feature("eig").set("neigsactive", true);
    model.study("std1").feature("eig").set("neigs", 1);
    model.study("std1").feature("eig").set("eigunit", "MHz");
    model.study("std1").feature("eig").set("shift", "870.6");
    model.study("std1").feature("eig").set("useparam", true);
    model.study("std1").feature("eig").setIndex("pname", "E_Al", 0);
    model.study("std1").feature("eig").setIndex("plistarr", "", 0);
    model.study("std1").feature("eig").setIndex("punit", "Pa", 0);
    model.study("std1").feature("eig").setIndex("pname", "E_Al", 0);
    model.study("std1").feature("eig").setIndex("plistarr", "", 0);
    model.study("std1").feature("eig").setIndex("punit", "Pa", 0);
    model.study("std1").feature("eig").setIndex("pname", "p1", 0);
    model.study("std1").feature("eig").setIndex("plistarr", "0 0 1 0 0 0 1 1", 0);
    model.study("std1").feature("eig").setIndex("pname", "E_Al", 1);
    model.study("std1").feature("eig").setIndex("plistarr", "", 1);
    model.study("std1").feature("eig").setIndex("punit", "Pa", 1);
    model.study("std1").feature("eig").setIndex("pname", "E_Al", 1);
    model.study("std1").feature("eig").setIndex("plistarr", "", 1);
    model.study("std1").feature("eig").setIndex("punit", "Pa", 1);
    model.study("std1").feature("eig").setIndex("pname", "p2", 1);
    model.study("std1").feature("eig").setIndex("plistarr", "0 0 1 0 1 0 1 1", 1);
    model.study("std1").feature("eig").setIndex("pname", "E_Al", 2);
    model.study("std1").feature("eig").setIndex("plistarr", "", 2);
    model.study("std1").feature("eig").setIndex("punit", "Pa", 2);
    model.study("std1").feature("eig").setIndex("pname", "E_Al", 2);
    model.study("std1").feature("eig").setIndex("plistarr", "", 2);
    model.study("std1").feature("eig").setIndex("punit", "Pa", 2);
    model.study("std1").feature("eig").setIndex("pname", "p3", 2);
    model.study("std1").feature("eig").setIndex("plistarr", "0 1 0 0 1 1 1 1", 2);
    model.study("std1").feature("eig").setIndex("pname", "E_Al", 3);
    model.study("std1").feature("eig").setIndex("plistarr", "", 3);
    model.study("std1").feature("eig").setIndex("punit", "Pa", 3);
    model.study("std1").feature("eig").setIndex("pname", "E_Al", 3);
    model.study("std1").feature("eig").setIndex("plistarr", "", 3);
    model.study("std1").feature("eig").setIndex("punit", "Pa", 3);
    model.study("std1").feature("eig").setIndex("pname", "p4", 3);
    model.study("std1").feature("eig").setIndex("plistarr", "0 0 0 0 0 1 1 1", 3);
    model.study("std1").feature("eig").setIndex("pname", "E_Al", 4);
    model.study("std1").feature("eig").setIndex("plistarr", "", 4);
    model.study("std1").feature("eig").setIndex("punit", "Pa", 4);
    model.study("std1").feature("eig").setIndex("pname", "E_Al", 4);
    model.study("std1").feature("eig").setIndex("plistarr", "", 4);
    model.study("std1").feature("eig").setIndex("punit", "Pa", 4);
    model.study("std1").feature("eig").setIndex("pname", "p5", 4);
    model.study("std1").feature("eig").setIndex("plistarr", "0 0 0 1 1 1 1 1", 4);
    model.study("std1").feature("eig").setIndex("pname", "E_Al", 5);
    model.study("std1").feature("eig").setIndex("plistarr", "", 5);
    model.study("std1").feature("eig").setIndex("punit", "Pa", 5);
    model.study("std1").feature("eig").setIndex("pname", "E_Al", 5);
    model.study("std1").feature("eig").setIndex("plistarr", "", 5);
    model.study("std1").feature("eig").setIndex("punit", "Pa", 5);
    model.study("std1").feature("eig").setIndex("pname", "p6", 5);
    model.study("std1").feature("eig").setIndex("plistarr", "0 0 0 1 0 1 0 1", 5);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").setIndex("looplevel", 8, 1);
    model.result("pg1").label("\u632f\u578b (solid)");
    model.result("pg1").set("showlegends", false);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.disp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().evaluationGroup().create("std1EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std1EvgFrq").set("data", "dset1");
    model.result().evaluationGroup("std1EvgFrq")
         .label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 1 - \u7279\u5f81\u9891\u7387\u548c\u7075\u654f\u5ea6)");
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
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u7535\u52bf (es)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("solutionparams", "parent");
    model.result("pg2").feature("mslc1").set("expr", "V");
    model.result("pg2").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg2").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg2").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg2").feature("mslc1").set("colortable", "Dipole");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("data", "parent");
    model.result("pg2").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg2").feature("strmsl1").set("expr", new String[]{"es.Ex", "es.Ey", "es.Ez"});
    model.result("pg2").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg2").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg2").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg2").feature("strmsl1").set("titletype", "none");
    model.result("pg2").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg2").feature("strmsl1").set("udist", 0.02);
    model.result("pg2").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg2").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("inheritcolor", false);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("data", "parent");
    model.result("pg2").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg2").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg2").feature("strmsl1").feature("col1").set("expr", "V");
    model.result("pg2").feature("strmsl1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg2").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u7535\u573a (es)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("mslc1", "Multislice");
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("solutionparams", "parent");
    model.result("pg3").feature("mslc1").set("expr", "es.normE");
    model.result("pg3").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg3").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg3").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg3").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg3").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg3").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg3").feature("mslc1").set("colortable", "Prism");
    model.result("pg3").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("data", "parent");
    model.result("pg3").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg3").feature("strmsl1").set("expr", new String[]{"es.Ex", "es.Ey", "es.Ez"});
    model.result("pg3").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg3").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg3").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg3").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg3").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg3").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg3").feature("strmsl1").set("titletype", "none");
    model.result("pg3").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg3").feature("strmsl1").set("udist", 0.02);
    model.result("pg3").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg3").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("inheritcolor", false);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("data", "parent");
    model.result("pg3").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg3").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg3").feature("strmsl1").feature("col1").set("expr", "es.normE");
    model.result("pg3").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg3").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg3").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg3").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().evaluationGroup().create("std1mpf1", "EvaluationGroup");
    model.result().evaluationGroup("std1mpf1").set("data", "dset1");
    model.result().evaluationGroup("std1mpf1")
         .label("\u53c2\u4e0e\u56e0\u5b50 (\u7814\u7a76 1 - \u7279\u5f81\u9891\u7387\u548c\u7075\u654f\u5ea6)");
    model.result().evaluationGroup("std1mpf1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormX", 0);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 0);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cX \u5e73\u79fb", 0);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormY", 1);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cY \u5e73\u79fb", 1);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormZ", 2);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u5e73\u79fb", 2);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormX", 3);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 3);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cX \u65cb\u8f6c", 3);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormY", 4);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 4);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cY \u65cb\u8f6c", 4);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormZ", 5);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 5);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u65cb\u8f6c", 5);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLX", 6);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg", 6);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cX \u5e73\u79fb", 6);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLY", 7);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg", 7);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cY \u5e73\u79fb", 7);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLZ", 8);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg", 8);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u5e73\u79fb", 8);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRX", 9);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg*m^2", 9);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cX \u65cb\u8f6c", 9);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRY", 10);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg*m^2", 10);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cY \u65cb\u8f6c", 10);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRZ", 11);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg*m^2", 11);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u65cb\u8f6c", 11);
    model.result().evaluationGroup("std1mpf1").run();
    model.result("pg1").run();

    model.component("comp1").selection().create("com2", "Complement");
    model.component("comp1").selection("com2").label("\u975e\u9897\u7c92");
    model.component("comp1").selection("com2").set("entitydim", 2);
    model.component("comp1").selection("com2").set("input", new String[]{"geom1_csel5_bnd"});

    model.result("pg1").run();
    model.result("pg1").feature("surf1").create("sel1", "Selection");
    model.result("pg1").feature("surf1").feature("sel1").selection().named("com2");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def").active(false);
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{1, 1});
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{1, 4});
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{1, 3});
    model.result("pg1").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u7075\u654f\u5ea6");
    model.result("pg4").set("titletype", "label");
    model.result("pg4").set("showlegends", false);
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "freq", 0);
    model.result("pg4").feature("glob1").setIndex("unit", "MHz", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "\u7279\u5f81\u9891\u7387", 0);
    model.result("pg4").feature("glob1").set("xdatasolnumtype", "all");
    model.result("pg4").feature("glob1").set("xdata", "expr");
    model.result("pg4").feature("glob1").set("xdataexpr", "intop1(solid.rho)");
    model.result("pg4").feature("glob1").set("xdataunit", "ng");
    model.result("pg4").feature("glob1").set("xdatadescractive", true);
    model.result("pg4").feature("glob1").set("xdatadescr", "\u6dfb\u52a0\u7684\u9897\u7c92\u8d28\u91cf");
    model.result("pg4").feature("glob1").set("linestyle", "none");
    model.result("pg4").feature("glob1").set("linemarker", "point");
    model.result("pg4").run();

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/solid", true);
    model.study("std2").feature("freq").setSolveFor("/physics/es", true);
    model.study("std2").feature("freq").setSolveFor("/multiphysics/pze1", true);
    model.study("std2").feature("freq").set("punit", "MHz");
    model.study("std2").feature("freq").set("plist", "range(870.3,0.05,870.9)");
    model.study("std2").label("\u7814\u7a76 2 - \u9891\u7387\u54cd\u5e94");
    model.study("std2").create("param", "Parametric");

    return model;
  }

  public static Model run3(Model model) {
    model.study("std2").feature("param").setIndex("pname", "E_Al", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "Pa", 0);
    model.study("std2").feature("param").setIndex("pname", "E_Al", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "Pa", 0);
    model.study("std2").feature("param").setIndex("pname", "p1", 0);
    model.study("std2").feature("param").setIndex("plistarr", "0 1", 0);
    model.study("std2").feature("param").setIndex("pname", "E_Al", 1);
    model.study("std2").feature("param").setIndex("plistarr", "", 1);
    model.study("std2").feature("param").setIndex("punit", "Pa", 1);
    model.study("std2").feature("param").setIndex("pname", "E_Al", 1);
    model.study("std2").feature("param").setIndex("plistarr", "", 1);
    model.study("std2").feature("param").setIndex("punit", "Pa", 1);
    model.study("std2").feature("param").setIndex("pname", "p2", 1);
    model.study("std2").feature("param").setIndex("plistarr", "0 1", 1);
    model.study("std2").feature("param").setIndex("pname", "E_Al", 2);
    model.study("std2").feature("param").setIndex("plistarr", "", 2);
    model.study("std2").feature("param").setIndex("punit", "Pa", 2);
    model.study("std2").feature("param").setIndex("pname", "E_Al", 2);
    model.study("std2").feature("param").setIndex("plistarr", "", 2);
    model.study("std2").feature("param").setIndex("punit", "Pa", 2);
    model.study("std2").feature("param").setIndex("pname", "p3", 2);
    model.study("std2").feature("param").setIndex("plistarr", "0 1", 2);
    model.study("std2").feature("param").setIndex("pname", "E_Al", 3);
    model.study("std2").feature("param").setIndex("plistarr", "", 3);
    model.study("std2").feature("param").setIndex("punit", "Pa", 3);
    model.study("std2").feature("param").setIndex("pname", "E_Al", 3);
    model.study("std2").feature("param").setIndex("plistarr", "", 3);
    model.study("std2").feature("param").setIndex("punit", "Pa", 3);
    model.study("std2").feature("param").setIndex("pname", "p4", 3);
    model.study("std2").feature("param").setIndex("plistarr", "0 1", 3);
    model.study("std2").feature("param").setIndex("pname", "E_Al", 4);
    model.study("std2").feature("param").setIndex("plistarr", "", 4);
    model.study("std2").feature("param").setIndex("punit", "Pa", 4);
    model.study("std2").feature("param").setIndex("pname", "E_Al", 4);
    model.study("std2").feature("param").setIndex("plistarr", "", 4);
    model.study("std2").feature("param").setIndex("punit", "Pa", 4);
    model.study("std2").feature("param").setIndex("pname", "p5", 4);
    model.study("std2").feature("param").setIndex("plistarr", "0 1", 4);
    model.study("std2").feature("param").setIndex("pname", "E_Al", 5);
    model.study("std2").feature("param").setIndex("plistarr", "", 5);
    model.study("std2").feature("param").setIndex("punit", "Pa", 5);
    model.study("std2").feature("param").setIndex("pname", "E_Al", 5);
    model.study("std2").feature("param").setIndex("plistarr", "", 5);
    model.study("std2").feature("param").setIndex("punit", "Pa", 5);
    model.study("std2").feature("param").setIndex("pname", "p6", 5);
    model.study("std2").feature("param").setIndex("plistarr", "0 1", 5);
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std2");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("p1").run("compute");

    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").setIndex("looplevel", 13, 0);
    model.result("pg5").setIndex("looplevel", 2, 1);
    model.result("pg5").label("\u5e94\u529b (solid)");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").create("vol1", "Volume");
    model.result("pg5").feature("vol1").set("expr", new String[]{"solid.misesGp_peak"});
    model.result("pg5").feature("vol1").set("threshold", "manual");
    model.result("pg5").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg5").feature("vol1").set("colortable", "Rainbow");
    model.result("pg5").feature("vol1").set("colortabletrans", "none");
    model.result("pg5").feature("vol1").set("colorscalemode", "linear");
    model.result("pg5").feature("vol1").set("resolution", "custom");
    model.result("pg5").feature("vol1").set("refine", 2);
    model.result("pg5").feature("vol1").set("colortable", "Prism");
    model.result("pg5").feature("vol1").create("def", "Deform");
    model.result("pg5").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg5").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u7535\u52bf (es) 1");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").setIndex("looplevel", 13, 0);
    model.result("pg6").setIndex("looplevel", 2, 1);
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").set("showlegendsmaxmin", true);
    model.result("pg6").feature().create("mslc1", "Multislice");
    model.result("pg6").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg6").feature("mslc1").set("solutionparams", "parent");
    model.result("pg6").feature("mslc1").set("expr", "V");
    model.result("pg6").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg6").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg6").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg6").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg6").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg6").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg6").feature("mslc1").set("colortable", "Dipole");
    model.result("pg6").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg6").feature("mslc1").set("data", "parent");
    model.result("pg6").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg6").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg6").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg6").feature("strmsl1").set("expr", new String[]{"es.Ex", "es.Ey", "es.Ez"});
    model.result("pg6").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg6").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg6").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg6").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg6").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg6").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg6").feature("strmsl1").set("titletype", "none");
    model.result("pg6").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg6").feature("strmsl1").set("udist", 0.02);
    model.result("pg6").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg6").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg6").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("strmsl1").set("inheritcolor", false);
    model.result("pg6").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg6").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg6").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg6").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg6").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("strmsl1").set("data", "parent");
    model.result("pg6").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg6").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg6").feature("strmsl1").feature("col1").set("expr", "V");
    model.result("pg6").feature("strmsl1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg6").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg6").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg6").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").label("\u7535\u573a (es) 1");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").setIndex("looplevel", 13, 0);
    model.result("pg7").setIndex("looplevel", 2, 1);
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").set("showlegendsmaxmin", true);
    model.result("pg7").feature().create("mslc1", "Multislice");
    model.result("pg7").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg7").feature("mslc1").set("solutionparams", "parent");
    model.result("pg7").feature("mslc1").set("expr", "es.normE");
    model.result("pg7").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg7").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg7").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg7").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg7").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg7").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg7").feature("mslc1").set("colortable", "Prism");
    model.result("pg7").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg7").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg7").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg7").feature("mslc1").set("data", "parent");
    model.result("pg7").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg7").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg7").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg7").feature("strmsl1").set("expr", new String[]{"es.Ex", "es.Ey", "es.Ez"});
    model.result("pg7").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg7").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg7").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg7").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg7").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg7").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg7").feature("strmsl1").set("titletype", "none");
    model.result("pg7").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg7").feature("strmsl1").set("udist", 0.02);
    model.result("pg7").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg7").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg7").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg7").feature("strmsl1").set("inheritcolor", false);
    model.result("pg7").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg7").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg7").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg7").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg7").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg7").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg7").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg7").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg7").feature("strmsl1").set("data", "parent");
    model.result("pg7").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg7").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg7").feature("strmsl1").feature("col1").set("expr", "es.normE");
    model.result("pg7").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg7").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg7").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg7").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg7").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg7").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 1, 1);
    model.result("pg6").setIndex("looplevel", 10, 0);
    model.result("pg6").run();
    model.result("pg6").feature("mslc1").active(false);
    model.result("pg6").run();
    model.result("pg6").feature("strmsl1").active(false);
    model.result("pg6").run();
    model.result("pg6").create("vol1", "Volume");
    model.result("pg6").feature("vol1").set("expr", "V");
    model.result("pg6").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u9891\u7387\u54cd\u5e94");
    model.result("pg8").set("data", "dset3");
    model.result("pg8").set("titletype", "label");
    model.result("pg8").set("legendpos", "uppermiddle");
    model.result("pg8").create("glob1", "Global");
    model.result("pg8").feature("glob1").set("markerpos", "datapoints");
    model.result("pg8").feature("glob1").set("linewidth", "preference");
    model.result("pg8").feature("glob1").setIndex("expr", "log10(abs(1/es.Y11)/1[ohm])", 0);
    model.result("pg8").feature("glob1").setIndex("descr", "log10|Z| (Ohms)", 0);
    model.result("pg8").run();

    model.title("\u56fa\u6001\u88c5\u914d\u8c10\u632f\u5668\uff08\u4e09\u7ef4\uff09");

    model
         .description("\u56fa\u6001\u88c5\u914d\u8c10\u632f\u5668 (SMR) \u662f\u4e00\u79cd\u538b\u7535 MEMS \u8c10\u632f\u5668\uff0c\u901a\u8fc7\u5728\u539a\u57fa\u677f\u4e0a\u6c89\u79ef\u58f0\u955c\u53e0\u5c42\u5f62\u6210\u3002\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u5728\u4e09\u7ef4\u4e2d\u6a21\u62df SMR\uff0c\u8ba1\u7b97\u4e86\u4e0d\u540c\u6570\u91cf\u7684\u7c92\u5b50\u9644\u7740\u5728\u4f20\u611f\u5668\u8868\u9762\u65f6\u7684\u7279\u5f81\u6a21\u6001\uff0c\u4ece\u800c\u8ba1\u7b97\u7075\u654f\u5ea6\uff0c\u5e76\u5206\u6790\u4e86\u76f8\u5e94\u7684\u9891\u7387\u54cd\u5e94\u53d8\u5316\u3002\u8c10\u632f\u9891\u7387\u968f\u7740\u9644\u52a0\u7c92\u5b50\u7684\u589e\u591a\u800c\u964d\u4f4e\uff0c\u7075\u654f\u5ea6\u53d6\u51b3\u4e8e\u9644\u7740\u7c92\u5b50\u76f8\u5bf9\u4e8e\u632f\u578b\u7684\u4f4d\u7f6e - \u8fd9\u4e24\u4e2a\u89c2\u5bdf\u7ed3\u679c\u90fd\u7b26\u5408\u9884\u671f\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("solidly_mounted_resonator_3d.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
