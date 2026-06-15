/*
 * roller_chain_dynamics.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:26 by COMSOL 6.3.0.290. */
public class roller_chain_dynamics {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Multibody_Dynamics_Module\\Tutorials,_Transmission");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("mbd", "MultibodyDynamics", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").activate("mbd", true);

    model.param().set("omega", "10[rad/s]");
    model.param().descr("omega", "Angular velocity of drive shaft");
    model.param().set("T_ext", "0.01[N*m]");
    model.param().descr("T_ext", "External torque");
    model.param().set("I_ext", "1e-5[kg*m^2]");
    model.param().descr("I_ext", "External moment of inertia");
    model.param().set("kb", "1e5[N/m]");
    model.param().descr("kb", "Spring constant, bushing");
    model.param().set("cb", "1e5[N*s/m]");
    model.param().descr("cb", "Damping coefficient, bushing");
    model.param().set("para", "0");
    model.param().descr("para", "Load parameter");

    model.component("comp1").func().create("step1", "Step");
    model.component("comp1").func("step1").set("location", "5e-3");
    model.component("comp1").func("step1").set("smooth", "1e-2");
    model.component("comp1").func().duplicate("step2", "step1");
    model.component("comp1").func("step2").set("location", "15e-3");

    model.geom()
         .load(new String[]{"part1"}, "Multibody_Dynamics_Module/2D/Roller_Chains/roller_chain_sprocket_assembly_2d.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", true);
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("createpairs", false);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup().create("Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup().create("Murnaghan", "Murnaghan");
    model.component("comp1").material("mat1").propertyGroup().create("Lame", "Lam\u00e9 parameters");
    model.component("comp1").material("mat1").label("Structural steel");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.9);
    model.component("comp1").material("mat1").set("roughness", 0.3);
    model.component("comp1").material("mat1").set("metallic", 0);
    model.component("comp1").material("mat1").set("pearl", 0);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("clearcoat", 0);
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
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
    model.component("comp1").material("mat1").propertyGroup("Enu").set("youngsmodulus", "200e9[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("poissonsratio", "0.30");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Lame").label("Lam\u00e9 parameters");
    model.component("comp1").material("mat1").propertyGroup("Lame").set("lambLame", "");
    model.component("comp1").material("mat1").propertyGroup("Lame").set("muLame", "");
    model.component("comp1").material("mat1").propertyGroup("Lame").set("lambLame", "");
    model.component("comp1").material("mat1").propertyGroup("Lame").set("muLame", "");
    model.component("comp1").material("mat1").propertyGroup("Lame").set("lambLame", "1.15e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Lame").set("muLame", "7.69e10[Pa]");
    model.component("comp1").material("mat1").set("groups", new String[][]{});
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1").set("lighting", "cooktorrance");
    model.component("comp1").material("mat1").set("fresnel", 0.9);
    model.component("comp1").material("mat1").set("roughness", 0.3);
    model.component("comp1").material("mat1").set("metallic", 0);
    model.component("comp1").material("mat1").set("pearl", 0);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("clearcoat", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1").set("specular", "custom");
    model.component("comp1").material("mat1")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat1").set("noisecolor", "custom");
    model.component("comp1").material("mat1").set("customnoisecolor", new double[]{0, 0, 0});
    model.component("comp1").material("mat1").set("noisescale", 0);
    model.component("comp1").material("mat1").set("noise", "off");
    model.component("comp1").material("mat1").set("noisefreq", 1);
    model.component("comp1").material("mat1").set("normalnoisebrush", "0");
    model.component("comp1").material("mat1").set("normalnoisetype", "0");
    model.component("comp1").material("mat1").set("alpha", 1);

    model.component("comp1").physics("mbd").prop("d").set("d", "2[mm]");
    model.component("comp1").physics("mbd").create("cdr1", "ChainDrive", -1);
    model.component("comp1").physics("mbd").feature("cdr1").set("SprocketType", "RigidSprocket");
    model.component("comp1").physics("mbd").feature("cdr1").set("pc", "mbd.cdr1.Eequ*(0.1*mbd.diag)/1e5");
    model.component("comp1").physics("mbd").feature("cdr1").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1").feature("je1")
         .set("k_u", new String[]{"kb", "0", "0", "0", "kb", "0", "0", "0", "kb"});
    model.component("comp1").physics("mbd").feature("cdr1").feature("je1")
         .set("c_u", new String[]{"cb", "0", "0", "0", "cb", "0", "0", "0", "cb"});

    model.nodeGroup().create("mbdcdr1rgrp", "Physics", "mbd");
    model.nodeGroup("mbdcdr1rgrp").label("cdr1: \u94fe\u63a5\uff08\u521a\u6027\u57df\uff09");
    model.nodeGroup("mbdcdr1rgrp").placeAfter("cdr1");

    model.component("comp1").physics("mbd").create("cdr1rd1", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd1").selection().add(2, 3, 4, 5, 6);
    model.component("comp1").physics("mbd").feature("cdr1rd1")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 1 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd1");

    model.component("comp1").physics("mbd").create("cdr1rd2", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd2").selection().add(7, 8, 9, 10, 11);
    model.component("comp1").physics("mbd").feature("cdr1rd2")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 2 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd2");

    model.component("comp1").physics("mbd").create("cdr1rd3", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd3").selection().add(12, 13, 14, 15, 16);
    model.component("comp1").physics("mbd").feature("cdr1rd3")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 3 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd3");

    model.component("comp1").physics("mbd").create("cdr1rd4", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd4").selection().add(17, 18, 19, 20, 21);
    model.component("comp1").physics("mbd").feature("cdr1rd4")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 4 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd4");

    model.component("comp1").physics("mbd").create("cdr1rd5", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd5").selection().add(22, 23, 24, 25, 26);
    model.component("comp1").physics("mbd").feature("cdr1rd5")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 5 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd5");

    model.component("comp1").physics("mbd").create("cdr1rd6", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd6").selection().add(27, 28, 29, 30, 31);
    model.component("comp1").physics("mbd").feature("cdr1rd6")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 6 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd6");

    model.component("comp1").physics("mbd").create("cdr1rd7", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd7").selection().add(32, 33, 34, 35, 36);
    model.component("comp1").physics("mbd").feature("cdr1rd7")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 7 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd7");

    model.component("comp1").physics("mbd").create("cdr1rd8", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd8").selection().add(37, 38, 39, 40, 41);
    model.component("comp1").physics("mbd").feature("cdr1rd8")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 8 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd8");

    model.component("comp1").physics("mbd").create("cdr1rd9", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd9").selection().add(42, 43, 44, 45, 46);
    model.component("comp1").physics("mbd").feature("cdr1rd9")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 9 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd9");

    model.component("comp1").physics("mbd").create("cdr1rd10", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd10").selection().add(47, 48, 49, 50, 51);
    model.component("comp1").physics("mbd").feature("cdr1rd10")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 10 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd10");

    model.component("comp1").physics("mbd").create("cdr1rd11", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd11").selection().add(52, 53, 54, 55, 56);
    model.component("comp1").physics("mbd").feature("cdr1rd11")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 11 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd11");

    model.component("comp1").physics("mbd").create("cdr1rd12", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd12").selection().add(57, 58, 59, 60, 61);
    model.component("comp1").physics("mbd").feature("cdr1rd12")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 12 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd12");

    model.component("comp1").physics("mbd").create("cdr1rd13", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd13").selection().add(62, 63, 64, 65, 66);
    model.component("comp1").physics("mbd").feature("cdr1rd13")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 13 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd13");

    model.component("comp1").physics("mbd").create("cdr1rd14", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd14").selection().add(67, 68, 69, 70, 71);
    model.component("comp1").physics("mbd").feature("cdr1rd14")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 14 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd14");

    model.component("comp1").physics("mbd").create("cdr1rd15", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd15").selection().add(72, 73, 74, 75, 76);
    model.component("comp1").physics("mbd").feature("cdr1rd15")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 15 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd15");

    model.component("comp1").physics("mbd").create("cdr1rd16", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd16").selection().add(77, 78, 79, 80, 81);
    model.component("comp1").physics("mbd").feature("cdr1rd16")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 16 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd16");

    model.component("comp1").physics("mbd").create("cdr1rd17", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd17").selection().add(82, 83, 84, 85, 86);
    model.component("comp1").physics("mbd").feature("cdr1rd17")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 17 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd17");

    model.component("comp1").physics("mbd").create("cdr1rd18", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd18").selection().add(87, 88, 89, 90, 91);
    model.component("comp1").physics("mbd").feature("cdr1rd18")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 18 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd18");

    model.component("comp1").physics("mbd").create("cdr1rd19", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd19").selection().add(92, 93, 94, 95, 96);
    model.component("comp1").physics("mbd").feature("cdr1rd19")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 19 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd19");

    model.component("comp1").physics("mbd").create("cdr1rd20", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd20").selection().add(97, 98, 99, 100, 101);
    model.component("comp1").physics("mbd").feature("cdr1rd20")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 20 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd20");

    model.component("comp1").physics("mbd").create("cdr1rd21", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd21").selection().add(102, 103, 104, 105, 106);
    model.component("comp1").physics("mbd").feature("cdr1rd21")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 21 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd21");

    model.component("comp1").physics("mbd").create("cdr1rd22", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd22").selection().add(107, 108, 109, 110, 111);
    model.component("comp1").physics("mbd").feature("cdr1rd22")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 22 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd22");

    model.component("comp1").physics("mbd").create("cdr1rd23", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd23").selection().add(112, 113, 114, 115, 116);
    model.component("comp1").physics("mbd").feature("cdr1rd23")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 23 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd23");

    model.component("comp1").physics("mbd").create("cdr1rd24", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd24").selection().add(117, 118, 119, 120, 121);
    model.component("comp1").physics("mbd").feature("cdr1rd24")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 24 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd24");

    model.component("comp1").physics("mbd").create("cdr1rd25", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd25").selection().add(122, 123, 124, 125, 126);
    model.component("comp1").physics("mbd").feature("cdr1rd25")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 25 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd25");

    model.component("comp1").physics("mbd").create("cdr1rd26", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd26").selection().add(127, 128, 129, 130, 131);
    model.component("comp1").physics("mbd").feature("cdr1rd26")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 26 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd26");

    model.component("comp1").physics("mbd").create("cdr1rd27", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd27").selection().add(132, 133, 134, 135, 136);
    model.component("comp1").physics("mbd").feature("cdr1rd27")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 27 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd27");

    model.component("comp1").physics("mbd").create("cdr1rd28", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd28").selection().add(137, 138, 139, 140, 141);
    model.component("comp1").physics("mbd").feature("cdr1rd28")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 28 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd28");

    model.component("comp1").physics("mbd").create("cdr1rd29", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd29").selection().add(142, 143, 144, 145, 146);
    model.component("comp1").physics("mbd").feature("cdr1rd29")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 29 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd29");

    model.component("comp1").physics("mbd").create("cdr1rd30", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd30").selection().add(147, 148, 149, 150, 151);
    model.component("comp1").physics("mbd").feature("cdr1rd30")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 30 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd30");

    model.component("comp1").physics("mbd").create("cdr1rd31", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd31").selection().add(152, 153, 154, 155, 156);
    model.component("comp1").physics("mbd").feature("cdr1rd31")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 31 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd31");

    model.component("comp1").physics("mbd").create("cdr1rd32", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd32").selection().add(157, 158, 159, 160, 161);
    model.component("comp1").physics("mbd").feature("cdr1rd32")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 32 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd32");

    model.component("comp1").physics("mbd").create("cdr1rd33", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd33").selection().add(162, 163, 164, 165, 166);
    model.component("comp1").physics("mbd").feature("cdr1rd33")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 33 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd33");

    model.component("comp1").physics("mbd").create("cdr1rd34", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd34").selection().add(167, 168, 169, 170, 171);
    model.component("comp1").physics("mbd").feature("cdr1rd34")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 34 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd34");

    model.component("comp1").physics("mbd").create("cdr1rd35", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd35").selection().add(172, 173, 174, 175, 176);
    model.component("comp1").physics("mbd").feature("cdr1rd35")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 35 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd35");

    model.component("comp1").physics("mbd").create("cdr1rd36", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd36").selection().add(177, 178, 179, 180, 181);
    model.component("comp1").physics("mbd").feature("cdr1rd36")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 36 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd36");

    model.component("comp1").physics("mbd").create("cdr1rd37", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd37").selection().add(182, 183, 184, 185, 186);
    model.component("comp1").physics("mbd").feature("cdr1rd37")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 37 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd37");

    model.component("comp1").physics("mbd").create("cdr1rd38", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd38").selection().add(188, 189, 190, 191, 192);
    model.component("comp1").physics("mbd").feature("cdr1rd38")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 38 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd38");

    model.component("comp1").physics("mbd").create("cdr1rd39", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd39").selection().add(193, 194, 195, 196, 197);
    model.component("comp1").physics("mbd").feature("cdr1rd39")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 39 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd39");

    model.component("comp1").physics("mbd").create("cdr1rd40", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd40").selection().add(198, 199, 200, 201, 202);
    model.component("comp1").physics("mbd").feature("cdr1rd40")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 40 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd40");

    model.component("comp1").physics("mbd").create("cdr1rd41", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd41").selection().add(203, 204, 205, 206, 207);
    model.component("comp1").physics("mbd").feature("cdr1rd41")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 41 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd41");

    model.component("comp1").physics("mbd").create("cdr1rd42", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd42").selection().add(208, 209, 210, 211, 212);
    model.component("comp1").physics("mbd").feature("cdr1rd42")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 42 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd42");

    model.component("comp1").physics("mbd").create("cdr1rd43", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd43").selection().add(213, 214, 215, 216, 217);
    model.component("comp1").physics("mbd").feature("cdr1rd43")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 43 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd43");

    model.component("comp1").physics("mbd").create("cdr1rd44", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd44").selection().add(218, 219, 220, 221, 222);
    model.component("comp1").physics("mbd").feature("cdr1rd44")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 44 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd44");

    model.component("comp1").physics("mbd").create("cdr1rd45", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd45").selection().add(223, 224, 225, 226, 227);
    model.component("comp1").physics("mbd").feature("cdr1rd45")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 45 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd45");

    model.component("comp1").physics("mbd").create("cdr1rd46", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd46").selection().add(228, 229, 230, 231, 232);
    model.component("comp1").physics("mbd").feature("cdr1rd46")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 46 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd46");

    model.component("comp1").physics("mbd").create("cdr1rd47", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd47").selection().add(233, 234, 235, 236, 237);
    model.component("comp1").physics("mbd").feature("cdr1rd47")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 47 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd47");

    model.component("comp1").physics("mbd").create("cdr1rd48", "RigidDomain");
    model.component("comp1").physics("mbd").feature("cdr1rd48").selection().add(238, 239, 240, 241, 242);
    model.component("comp1").physics("mbd").feature("cdr1rd48")
         .label("\u521a\u6027\u57df\uff1a\u8fde\u6746 48 (cdr1)");

    model.nodeGroup("mbdcdr1rgrp").add("cdr1rd48");
    model.nodeGroup().create("mbdcdr1agrp", "Physics", "mbd");
    model.nodeGroup("mbdcdr1agrp").label("cdr1: \u94fe\u63a5\uff08\u8fde\u63a5\u4ef6\uff09");
    model.nodeGroup("mbdcdr1agrp").placeAfter("cdr1");

    model.component("comp1").physics("mbd").create("cdr1att1", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att1").selection().add(257, 258, 263, 264);
    model.component("comp1").physics("mbd").feature("cdr1att1")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 1 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att1");

    model.component("comp1").physics("mbd").feature("cdr1att1").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att2", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att2").selection().add(259, 260, 269, 270);
    model.component("comp1").physics("mbd").feature("cdr1att2")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 2 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att2");

    model.component("comp1").physics("mbd").feature("cdr1att2").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att3", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att3").selection().add(331, 332, 335, 337);
    model.component("comp1").physics("mbd").feature("cdr1att3")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 3 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att3");

    model.component("comp1").physics("mbd").feature("cdr1att3").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att4", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att4").selection().add(345, 346, 351, 353);
    model.component("comp1").physics("mbd").feature("cdr1att4")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 4 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att4");

    model.component("comp1").physics("mbd").feature("cdr1att4").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att5", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att5").selection().add(363, 364, 367, 369);
    model.component("comp1").physics("mbd").feature("cdr1att5")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 5 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att5");

    model.component("comp1").physics("mbd").feature("cdr1att5").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att6", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att6").selection().add(378, 379, 383, 385);
    model.component("comp1").physics("mbd").feature("cdr1att6")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 6 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att6");

    model.component("comp1").physics("mbd").feature("cdr1att6").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att7", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att7").selection().add(443, 444, 445, 448);
    model.component("comp1").physics("mbd").feature("cdr1att7")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 7 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att7");

    model.component("comp1").physics("mbd").feature("cdr1att7").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att8", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att8").selection().add(462, 463, 464, 466);
    model.component("comp1").physics("mbd").feature("cdr1att8")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 8 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att8");

    model.component("comp1").physics("mbd").feature("cdr1att8").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att9", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att9").selection().add(475, 476, 477, 480);
    model.component("comp1").physics("mbd").feature("cdr1att9")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 9 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att9");

    model.component("comp1").physics("mbd").feature("cdr1att9").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att10", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att10").selection().add(494, 495, 496, 498);
    model.component("comp1").physics("mbd").feature("cdr1att10")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 10 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att10");

    model.component("comp1").physics("mbd").feature("cdr1att10").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att11", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att11").selection().add(553, 554, 557, 560);
    model.component("comp1").physics("mbd").feature("cdr1att11")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 11 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att11");

    return model;
  }

  public static Model run2(Model model) {

    model.component("comp1").physics("mbd").feature("cdr1att11").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att12", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att12").selection().add(572, 573, 576, 578);
    model.component("comp1").physics("mbd").feature("cdr1att12")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 12 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att12");

    model.component("comp1").physics("mbd").feature("cdr1att12").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att13", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att13").selection().add(585, 586, 589, 592);
    model.component("comp1").physics("mbd").feature("cdr1att13")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 13 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att13");

    model.component("comp1").physics("mbd").feature("cdr1att13").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att14", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att14").selection().add(603, 604, 608, 610);
    model.component("comp1").physics("mbd").feature("cdr1att14")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 14 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att14");

    model.component("comp1").physics("mbd").feature("cdr1att14").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att15", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att15").selection().add(665, 666, 669, 670);
    model.component("comp1").physics("mbd").feature("cdr1att15")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 15 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att15");

    model.component("comp1").physics("mbd").feature("cdr1att15").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att16", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att16").selection().add(683, 684, 689, 690);
    model.component("comp1").physics("mbd").feature("cdr1att16")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 16 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att16");

    model.component("comp1").physics("mbd").feature("cdr1att16").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att17", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att17").selection().add(697, 698, 701, 702);
    model.component("comp1").physics("mbd").feature("cdr1att17")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 17 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att17");

    model.component("comp1").physics("mbd").feature("cdr1att17").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att18", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att18").selection().add(715, 716, 721, 722);
    model.component("comp1").physics("mbd").feature("cdr1att18")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 18 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att18");

    model.component("comp1").physics("mbd").feature("cdr1att18").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att19", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att19").selection().add(777, 778, 781, 782);
    model.component("comp1").physics("mbd").feature("cdr1att19")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 19 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att19");

    model.component("comp1").physics("mbd").feature("cdr1att19").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att20", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att20").selection().add(795, 796, 801, 802);
    model.component("comp1").physics("mbd").feature("cdr1att20")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 20 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att20");

    model.component("comp1").physics("mbd").feature("cdr1att20").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att21", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att21").selection().add(809, 810, 813, 814);
    model.component("comp1").physics("mbd").feature("cdr1att21")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 21 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att21");

    model.component("comp1").physics("mbd").feature("cdr1att21").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att22", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att22").selection().add(827, 828, 833, 834);
    model.component("comp1").physics("mbd").feature("cdr1att22")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 22 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att22");

    model.component("comp1").physics("mbd").feature("cdr1att22").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att23", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att23").selection().add(889, 890, 893, 894);
    model.component("comp1").physics("mbd").feature("cdr1att23")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 23 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att23");

    model.component("comp1").physics("mbd").feature("cdr1att23").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att24", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att24").selection().add(907, 908, 913, 914);
    model.component("comp1").physics("mbd").feature("cdr1att24")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 24 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att24");

    model.component("comp1").physics("mbd").feature("cdr1att24").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att25", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att25").selection().add(921, 922, 925, 926);
    model.component("comp1").physics("mbd").feature("cdr1att25")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 25 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att25");

    model.component("comp1").physics("mbd").feature("cdr1att25").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att26", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att26").selection().add(939, 940, 945, 946);
    model.component("comp1").physics("mbd").feature("cdr1att26")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 26 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att26");

    model.component("comp1").physics("mbd").feature("cdr1att26").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att27", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att27").selection().add(1001, 1002, 1005, 1006);
    model.component("comp1").physics("mbd").feature("cdr1att27")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 27 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att27");

    model.component("comp1").physics("mbd").feature("cdr1att27").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att28", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att28").selection().add(1019, 1020, 1025, 1026);
    model.component("comp1").physics("mbd").feature("cdr1att28")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 28 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att28");

    model.component("comp1").physics("mbd").feature("cdr1att28").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att29", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att29").selection().add(1033, 1034, 1037, 1038);
    model.component("comp1").physics("mbd").feature("cdr1att29")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 29 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att29");

    model.component("comp1").physics("mbd").feature("cdr1att29").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att30", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att30").selection().add(1051, 1052, 1057, 1058);
    model.component("comp1").physics("mbd").feature("cdr1att30")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 30 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att30");

    model.component("comp1").physics("mbd").feature("cdr1att30").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att31", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att31").selection().add(1113, 1114, 1117, 1118);
    model.component("comp1").physics("mbd").feature("cdr1att31")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 31 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att31");

    model.component("comp1").physics("mbd").feature("cdr1att31").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att32", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att32").selection().add(1131, 1132, 1137, 1138);
    model.component("comp1").physics("mbd").feature("cdr1att32")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 32 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att32");

    model.component("comp1").physics("mbd").feature("cdr1att32").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att33", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att33").selection().add(1145, 1146, 1149, 1150);
    model.component("comp1").physics("mbd").feature("cdr1att33")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 33 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att33");

    model.component("comp1").physics("mbd").feature("cdr1att33").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att34", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att34").selection().add(1163, 1164, 1169, 1170);
    model.component("comp1").physics("mbd").feature("cdr1att34")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 34 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att34");

    model.component("comp1").physics("mbd").feature("cdr1att34").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att35", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att35").selection().add(1225, 1226, 1229, 1230);
    model.component("comp1").physics("mbd").feature("cdr1att35")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 35 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att35");

    model.component("comp1").physics("mbd").feature("cdr1att35").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att36", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att36").selection().add(1243, 1244, 1249, 1250);
    model.component("comp1").physics("mbd").feature("cdr1att36")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 36 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att36");

    model.component("comp1").physics("mbd").feature("cdr1att36").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att37", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att37").selection().add(1257, 1258, 1261, 1262);
    model.component("comp1").physics("mbd").feature("cdr1att37")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 37 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att37");

    model.component("comp1").physics("mbd").feature("cdr1att37").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att38", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att38").selection().add(1275, 1276, 1281, 1282);
    model.component("comp1").physics("mbd").feature("cdr1att38")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 38 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att38");

    model.component("comp1").physics("mbd").feature("cdr1att38").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att39", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att39").selection().add(1461, 1462, 1465, 1466);
    model.component("comp1").physics("mbd").feature("cdr1att39")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 39 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att39");

    model.component("comp1").physics("mbd").feature("cdr1att39").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att40", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att40").selection().add(1479, 1480, 1485, 1486);
    model.component("comp1").physics("mbd").feature("cdr1att40")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 40 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att40");

    model.component("comp1").physics("mbd").feature("cdr1att40").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att41", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att41").selection().add(1493, 1494, 1497, 1498);
    model.component("comp1").physics("mbd").feature("cdr1att41")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 41 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att41");

    model.component("comp1").physics("mbd").feature("cdr1att41").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att42", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att42").selection().add(1511, 1512, 1517, 1518);
    model.component("comp1").physics("mbd").feature("cdr1att42")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 42 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att42");

    model.component("comp1").physics("mbd").feature("cdr1att42").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att43", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att43").selection().add(1575, 1576, 1577, 1580);
    model.component("comp1").physics("mbd").feature("cdr1att43")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 43 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att43");

    model.component("comp1").physics("mbd").feature("cdr1att43").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att44", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att44").selection().add(1594, 1595, 1596, 1598);
    model.component("comp1").physics("mbd").feature("cdr1att44")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 44 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att44");

    model.component("comp1").physics("mbd").feature("cdr1att44").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att45", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att45").selection().add(1607, 1608, 1609, 1612);
    model.component("comp1").physics("mbd").feature("cdr1att45")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 45 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att45");

    model.component("comp1").physics("mbd").feature("cdr1att45").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att46", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att46").selection().add(1626, 1627, 1628, 1630);
    model.component("comp1").physics("mbd").feature("cdr1att46")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 46 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att46");

    model.component("comp1").physics("mbd").feature("cdr1att46").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att47", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att47").selection().add(1693, 1694, 1699, 1700);
    model.component("comp1").physics("mbd").feature("cdr1att47")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 47 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att47");

    model.component("comp1").physics("mbd").feature("cdr1att47").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att48", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att48").selection().add(1695, 1696, 1705, 1706);
    model.component("comp1").physics("mbd").feature("cdr1att48")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6eda\u5b50 48 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att48");

    model.component("comp1").physics("mbd").feature("cdr1att48").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att49", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att49").selection().add(281, 282, 288, 291);
    model.component("comp1").physics("mbd").feature("cdr1att49")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 1 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att49");

    model.component("comp1").physics("mbd").feature("cdr1att49").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att50", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att50").selection().add(289, 290, 294, 295);
    model.component("comp1").physics("mbd").feature("cdr1att50")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 2 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att50");

    model.component("comp1").physics("mbd").feature("cdr1att50").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att51", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att51").selection().add(305, 306, 308, 313);
    model.component("comp1").physics("mbd").feature("cdr1att51")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 3 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att51");

    model.component("comp1").physics("mbd").feature("cdr1att51").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att52", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att52").selection().add(314, 315, 318, 322);
    model.component("comp1").physics("mbd").feature("cdr1att52")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 4 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att52");

    model.component("comp1").physics("mbd").feature("cdr1att52").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att53", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att53").selection().add(392, 393, 396, 397);
    model.component("comp1").physics("mbd").feature("cdr1att53")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 5 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att53");

    model.component("comp1").physics("mbd").feature("cdr1att53").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att54", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att54").selection().add(407, 408, 409, 410);
    model.component("comp1").physics("mbd").feature("cdr1att54")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 6 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att54");

    model.component("comp1").physics("mbd").feature("cdr1att54").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att55", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att55").selection().add(416, 417, 420, 421);
    model.component("comp1").physics("mbd").feature("cdr1att55")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 7 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att55");

    model.component("comp1").physics("mbd").feature("cdr1att55").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att56", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att56").selection().add(431, 432, 433, 434);
    model.component("comp1").physics("mbd").feature("cdr1att56")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 8 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att56");

    model.component("comp1").physics("mbd").feature("cdr1att56").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att57", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att57").selection().add(503, 504, 506, 509);
    model.component("comp1").physics("mbd").feature("cdr1att57")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 9 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att57");

    model.component("comp1").physics("mbd").feature("cdr1att57").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att58", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att58").selection().add(518, 519, 521, 523);
    model.component("comp1").physics("mbd").feature("cdr1att58")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 10 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att58");

    model.component("comp1").physics("mbd").feature("cdr1att58").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att59", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att59").selection().add(527, 528, 530, 533);
    model.component("comp1").physics("mbd").feature("cdr1att59")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 11 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att59");

    model.component("comp1").physics("mbd").feature("cdr1att59").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att60", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att60").selection().add(542, 543, 545, 547);
    model.component("comp1").physics("mbd").feature("cdr1att60")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 12 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att60");

    model.component("comp1").physics("mbd").feature("cdr1att60").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att61", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att61").selection().add(615, 616, 618, 619);
    model.component("comp1").physics("mbd").feature("cdr1att61")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 13 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att61");

    model.component("comp1").physics("mbd").feature("cdr1att61").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att62", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att62").selection().add(629, 630, 634, 635);
    model.component("comp1").physics("mbd").feature("cdr1att62")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 14 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att62");

    model.component("comp1").physics("mbd").feature("cdr1att62").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att63", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att63").selection().add(639, 640, 642, 643);
    model.component("comp1").physics("mbd").feature("cdr1att63")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 15 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att63");

    model.component("comp1").physics("mbd").feature("cdr1att63").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att64", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att64").selection().add(653, 654, 658, 659);
    model.component("comp1").physics("mbd").feature("cdr1att64")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 16 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att64");

    model.component("comp1").physics("mbd").feature("cdr1att64").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att65", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att65").selection().add(727, 728, 730, 731);
    model.component("comp1").physics("mbd").feature("cdr1att65")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 17 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att65");

    model.component("comp1").physics("mbd").feature("cdr1att65").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att66", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att66").selection().add(741, 742, 746, 747);
    model.component("comp1").physics("mbd").feature("cdr1att66")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 18 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att66");

    model.component("comp1").physics("mbd").feature("cdr1att66").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att67", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att67").selection().add(751, 752, 754, 755);
    model.component("comp1").physics("mbd").feature("cdr1att67")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 19 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att67");

    model.component("comp1").physics("mbd").feature("cdr1att67").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att68", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att68").selection().add(765, 766, 770, 771);
    model.component("comp1").physics("mbd").feature("cdr1att68")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 20 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att68");

    model.component("comp1").physics("mbd").feature("cdr1att68").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att69", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att69").selection().add(839, 840, 842, 843);
    model.component("comp1").physics("mbd").feature("cdr1att69")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 21 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att69");

    model.component("comp1").physics("mbd").feature("cdr1att69").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att70", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att70").selection().add(853, 854, 858, 859);
    model.component("comp1").physics("mbd").feature("cdr1att70")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 22 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att70");

    model.component("comp1").physics("mbd").feature("cdr1att70").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att71", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att71").selection().add(863, 864, 866, 867);
    model.component("comp1").physics("mbd").feature("cdr1att71")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 23 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att71");

    model.component("comp1").physics("mbd").feature("cdr1att71").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att72", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att72").selection().add(877, 878, 882, 883);
    model.component("comp1").physics("mbd").feature("cdr1att72")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 24 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att72");

    model.component("comp1").physics("mbd").feature("cdr1att72").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att73", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att73").selection().add(951, 952, 954, 955);
    model.component("comp1").physics("mbd").feature("cdr1att73")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 25 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att73");

    model.component("comp1").physics("mbd").feature("cdr1att73").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att74", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att74").selection().add(965, 966, 970, 971);
    model.component("comp1").physics("mbd").feature("cdr1att74")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 26 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att74");

    model.component("comp1").physics("mbd").feature("cdr1att74").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att75", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att75").selection().add(975, 976, 978, 979);
    model.component("comp1").physics("mbd").feature("cdr1att75")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 27 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att75");

    model.component("comp1").physics("mbd").feature("cdr1att75").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att76", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att76").selection().add(989, 990, 994, 995);
    model.component("comp1").physics("mbd").feature("cdr1att76")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 28 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att76");

    model.component("comp1").physics("mbd").feature("cdr1att76").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att77", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att77").selection().add(1063, 1064, 1066, 1067);
    model.component("comp1").physics("mbd").feature("cdr1att77")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 29 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att77");

    model.component("comp1").physics("mbd").feature("cdr1att77").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att78", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att78").selection().add(1077, 1078, 1082, 1083);
    model.component("comp1").physics("mbd").feature("cdr1att78")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 30 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att78");

    model.component("comp1").physics("mbd").feature("cdr1att78").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att79", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att79").selection().add(1087, 1088, 1090, 1091);
    model.component("comp1").physics("mbd").feature("cdr1att79")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 31 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att79");

    model.component("comp1").physics("mbd").feature("cdr1att79").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att80", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att80").selection().add(1101, 1102, 1106, 1107);
    model.component("comp1").physics("mbd").feature("cdr1att80")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 32 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att80");

    model.component("comp1").physics("mbd").feature("cdr1att80").set("ConnectionType", "RigidType");

    return model;
  }

  public static Model run3(Model model) {
    model.component("comp1").physics("mbd").create("cdr1att81", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att81").selection().add(1175, 1176, 1178, 1181);
    model.component("comp1").physics("mbd").feature("cdr1att81")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 33 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att81");

    model.component("comp1").physics("mbd").feature("cdr1att81").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att82", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att82").selection().add(1189, 1190, 1193, 1195);
    model.component("comp1").physics("mbd").feature("cdr1att82")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 34 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att82");

    model.component("comp1").physics("mbd").feature("cdr1att82").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att83", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att83").selection().add(1199, 1200, 1202, 1203);
    model.component("comp1").physics("mbd").feature("cdr1att83")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 35 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att83");

    model.component("comp1").physics("mbd").feature("cdr1att83").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att84", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att84").selection().add(1213, 1214, 1218, 1219);
    model.component("comp1").physics("mbd").feature("cdr1att84")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 36 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att84");

    model.component("comp1").physics("mbd").feature("cdr1att84").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att85", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att85").selection().add(1411, 1412, 1414, 1415);
    model.component("comp1").physics("mbd").feature("cdr1att85")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 37 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att85");

    model.component("comp1").physics("mbd").feature("cdr1att85").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att86", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att86").selection().add(1425, 1426, 1430, 1431);
    model.component("comp1").physics("mbd").feature("cdr1att86")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 38 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att86");

    model.component("comp1").physics("mbd").feature("cdr1att86").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att87", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att87").selection().add(1435, 1436, 1438, 1439);
    model.component("comp1").physics("mbd").feature("cdr1att87")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 39 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att87");

    model.component("comp1").physics("mbd").feature("cdr1att87").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att88", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att88").selection().add(1449, 1450, 1454, 1455);
    model.component("comp1").physics("mbd").feature("cdr1att88")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 40 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att88");

    model.component("comp1").physics("mbd").feature("cdr1att88").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att89", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att89").selection().add(1523, 1524, 1526, 1529);
    model.component("comp1").physics("mbd").feature("cdr1att89")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 41 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att89");

    model.component("comp1").physics("mbd").feature("cdr1att89").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att90", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att90").selection().add(1537, 1538, 1541, 1543);
    model.component("comp1").physics("mbd").feature("cdr1att90")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 42 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att90");

    model.component("comp1").physics("mbd").feature("cdr1att90").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att91", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att91").selection().add(1547, 1548, 1550, 1553);
    model.component("comp1").physics("mbd").feature("cdr1att91")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 43 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att91");

    model.component("comp1").physics("mbd").feature("cdr1att91").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att92", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att92").selection().add(1562, 1563, 1565, 1567);
    model.component("comp1").physics("mbd").feature("cdr1att92")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 44 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att92");

    model.component("comp1").physics("mbd").feature("cdr1att92").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att93", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att93").selection().add(1637, 1638, 1640, 1641);
    model.component("comp1").physics("mbd").feature("cdr1att93")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 45 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att93");

    model.component("comp1").physics("mbd").feature("cdr1att93").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att94", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att94").selection().add(1649, 1650, 1653, 1654);
    model.component("comp1").physics("mbd").feature("cdr1att94")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 46 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att94");

    model.component("comp1").physics("mbd").feature("cdr1att94").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att95", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att95").selection().add(1661, 1662, 1664, 1666);
    model.component("comp1").physics("mbd").feature("cdr1att95")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 47 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att95");

    model.component("comp1").physics("mbd").feature("cdr1att95").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("cdr1att96", "Attachment");
    model.component("comp1").physics("mbd").feature("cdr1att96").selection().add(1671, 1672, 1676, 1678);
    model.component("comp1").physics("mbd").feature("cdr1att96")
         .label("\u8fde\u63a5\u4ef6\uff1a\u9500\u8f74 48 (cdr1)");

    model.nodeGroup("mbdcdr1agrp").add("cdr1att96");

    model.component("comp1").physics("mbd").feature("cdr1att96").set("ConnectionType", "RigidType");

    model.nodeGroup().create("mbdcdr1jgrp", "Physics", "mbd");
    model.nodeGroup("mbdcdr1jgrp").label("cdr1: \u94fe\u63a5\uff08\u94f0\u94fe\u5173\u8282\uff09");
    model.nodeGroup("mbdcdr1jgrp").placeAfter("cdr1");

    model.component("comp1").physics("mbd").create("cdr1hgj1", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj1").set("Source", "cdr1att1");
    model.component("comp1").physics("mbd").feature("cdr1hgj1").set("Destination", "cdr1att49");
    model.component("comp1").physics("mbd").feature("cdr1hgj1")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 1 - \u9500\u8f74 1 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj1");

    model.component("comp1").physics("mbd").feature("cdr1hgj1").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj1").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj1").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj1").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj1").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj2", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj2").set("Source", "cdr1att2");
    model.component("comp1").physics("mbd").feature("cdr1hgj2").set("Destination", "cdr1att51");
    model.component("comp1").physics("mbd").feature("cdr1hgj2")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 2 - \u9500\u8f74 3 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj2");

    model.component("comp1").physics("mbd").feature("cdr1hgj2").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj2").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj2").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj2").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj2").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj3", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj3").set("Source", "cdr1att3");
    model.component("comp1").physics("mbd").feature("cdr1hgj3").set("Destination", "cdr1att50");
    model.component("comp1").physics("mbd").feature("cdr1hgj3")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 3 - \u9500\u8f74 2 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj3");

    model.component("comp1").physics("mbd").feature("cdr1hgj3").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj3").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj3").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj3").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj3").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj4", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj4").set("Source", "cdr1att4");
    model.component("comp1").physics("mbd").feature("cdr1hgj4").set("Destination", "cdr1att53");
    model.component("comp1").physics("mbd").feature("cdr1hgj4")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 4 - \u9500\u8f74 5 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj4");

    model.component("comp1").physics("mbd").feature("cdr1hgj4").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj4").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj4").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj4").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj4").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj5", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj5").set("Source", "cdr1att5");
    model.component("comp1").physics("mbd").feature("cdr1hgj5").set("Destination", "cdr1att52");
    model.component("comp1").physics("mbd").feature("cdr1hgj5")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 5 - \u9500\u8f74 4 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj5");

    model.component("comp1").physics("mbd").feature("cdr1hgj5").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj5").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj5").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj5").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj5").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj6", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj6").set("Source", "cdr1att6");
    model.component("comp1").physics("mbd").feature("cdr1hgj6").set("Destination", "cdr1att55");
    model.component("comp1").physics("mbd").feature("cdr1hgj6")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 6 - \u9500\u8f74 7 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj6");

    model.component("comp1").physics("mbd").feature("cdr1hgj6").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj6").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj6").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj6").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj6").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj7", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj7").set("Source", "cdr1att7");
    model.component("comp1").physics("mbd").feature("cdr1hgj7").set("Destination", "cdr1att54");
    model.component("comp1").physics("mbd").feature("cdr1hgj7")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 7 - \u9500\u8f74 6 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj7");

    model.component("comp1").physics("mbd").feature("cdr1hgj7").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj7").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj7").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj7").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj7").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj8", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj8").set("Source", "cdr1att8");
    model.component("comp1").physics("mbd").feature("cdr1hgj8").set("Destination", "cdr1att57");
    model.component("comp1").physics("mbd").feature("cdr1hgj8")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 8 - \u9500\u8f74 9 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj8");

    model.component("comp1").physics("mbd").feature("cdr1hgj8").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj8").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj8").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj8").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj8").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj9", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj9").set("Source", "cdr1att9");
    model.component("comp1").physics("mbd").feature("cdr1hgj9").set("Destination", "cdr1att56");
    model.component("comp1").physics("mbd").feature("cdr1hgj9")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 9 - \u9500\u8f74 8 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj9");

    model.component("comp1").physics("mbd").feature("cdr1hgj9").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj9").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj9").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj9").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj9").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj10", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj10").set("Source", "cdr1att10");
    model.component("comp1").physics("mbd").feature("cdr1hgj10").set("Destination", "cdr1att59");
    model.component("comp1").physics("mbd").feature("cdr1hgj10")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 10 - \u9500\u8f74 11 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj10");

    model.component("comp1").physics("mbd").feature("cdr1hgj10").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj10").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj10").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj10").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj10").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj11", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj11").set("Source", "cdr1att11");
    model.component("comp1").physics("mbd").feature("cdr1hgj11").set("Destination", "cdr1att58");
    model.component("comp1").physics("mbd").feature("cdr1hgj11")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 11 - \u9500\u8f74 10 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj11");

    model.component("comp1").physics("mbd").feature("cdr1hgj11").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj11").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj11").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj11").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj11").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj12", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj12").set("Source", "cdr1att12");
    model.component("comp1").physics("mbd").feature("cdr1hgj12").set("Destination", "cdr1att61");
    model.component("comp1").physics("mbd").feature("cdr1hgj12")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 12 - \u9500\u8f74 13 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj12");

    model.component("comp1").physics("mbd").feature("cdr1hgj12").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj12").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj12").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj12").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj12").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj13", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj13").set("Source", "cdr1att13");
    model.component("comp1").physics("mbd").feature("cdr1hgj13").set("Destination", "cdr1att60");
    model.component("comp1").physics("mbd").feature("cdr1hgj13")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 13 - \u9500\u8f74 12 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj13");

    model.component("comp1").physics("mbd").feature("cdr1hgj13").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj13").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj13").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj13").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj13").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj14", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj14").set("Source", "cdr1att14");
    model.component("comp1").physics("mbd").feature("cdr1hgj14").set("Destination", "cdr1att63");
    model.component("comp1").physics("mbd").feature("cdr1hgj14")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 14 - \u9500\u8f74 15 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj14");

    model.component("comp1").physics("mbd").feature("cdr1hgj14").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj14").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj14").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj14").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj14").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj15", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj15").set("Source", "cdr1att15");
    model.component("comp1").physics("mbd").feature("cdr1hgj15").set("Destination", "cdr1att62");
    model.component("comp1").physics("mbd").feature("cdr1hgj15")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 15 - \u9500\u8f74 14 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj15");

    model.component("comp1").physics("mbd").feature("cdr1hgj15").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj15").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj15").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj15").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj15").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj16", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj16").set("Source", "cdr1att16");
    model.component("comp1").physics("mbd").feature("cdr1hgj16").set("Destination", "cdr1att65");
    model.component("comp1").physics("mbd").feature("cdr1hgj16")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 16 - \u9500\u8f74 17 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj16");

    model.component("comp1").physics("mbd").feature("cdr1hgj16").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj16").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj16").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj16").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj16").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj17", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj17").set("Source", "cdr1att17");
    model.component("comp1").physics("mbd").feature("cdr1hgj17").set("Destination", "cdr1att64");
    model.component("comp1").physics("mbd").feature("cdr1hgj17")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 17 - \u9500\u8f74 16 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj17");

    model.component("comp1").physics("mbd").feature("cdr1hgj17").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj17").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj17").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj17").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj17").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj18", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj18").set("Source", "cdr1att18");
    model.component("comp1").physics("mbd").feature("cdr1hgj18").set("Destination", "cdr1att67");
    model.component("comp1").physics("mbd").feature("cdr1hgj18")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 18 - \u9500\u8f74 19 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj18");

    model.component("comp1").physics("mbd").feature("cdr1hgj18").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj18").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj18").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj18").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj18").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj19", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj19").set("Source", "cdr1att19");
    model.component("comp1").physics("mbd").feature("cdr1hgj19").set("Destination", "cdr1att66");
    model.component("comp1").physics("mbd").feature("cdr1hgj19")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 19 - \u9500\u8f74 18 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj19");

    model.component("comp1").physics("mbd").feature("cdr1hgj19").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj19").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj19").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj19").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj19").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj20", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj20").set("Source", "cdr1att20");
    model.component("comp1").physics("mbd").feature("cdr1hgj20").set("Destination", "cdr1att69");
    model.component("comp1").physics("mbd").feature("cdr1hgj20")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 20 - \u9500\u8f74 21 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj20");

    model.component("comp1").physics("mbd").feature("cdr1hgj20").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj20").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj20").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj20").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj20").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj21", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj21").set("Source", "cdr1att21");
    model.component("comp1").physics("mbd").feature("cdr1hgj21").set("Destination", "cdr1att68");
    model.component("comp1").physics("mbd").feature("cdr1hgj21")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 21 - \u9500\u8f74 20 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj21");

    model.component("comp1").physics("mbd").feature("cdr1hgj21").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj21").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj21").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj21").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj21").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj22", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj22").set("Source", "cdr1att22");
    model.component("comp1").physics("mbd").feature("cdr1hgj22").set("Destination", "cdr1att71");
    model.component("comp1").physics("mbd").feature("cdr1hgj22")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 22 - \u9500\u8f74 23 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj22");

    model.component("comp1").physics("mbd").feature("cdr1hgj22").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj22").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj22").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj22").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj22").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj23", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj23").set("Source", "cdr1att23");
    model.component("comp1").physics("mbd").feature("cdr1hgj23").set("Destination", "cdr1att70");
    model.component("comp1").physics("mbd").feature("cdr1hgj23")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 23 - \u9500\u8f74 22 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj23");

    model.component("comp1").physics("mbd").feature("cdr1hgj23").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj23").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj23").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj23").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj23").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj24", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj24").set("Source", "cdr1att24");
    model.component("comp1").physics("mbd").feature("cdr1hgj24").set("Destination", "cdr1att73");
    model.component("comp1").physics("mbd").feature("cdr1hgj24")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 24 - \u9500\u8f74 25 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj24");

    model.component("comp1").physics("mbd").feature("cdr1hgj24").set("JointElasticity", "ElasticJoint");

    return model;
  }

  public static Model run4(Model model) {
    model.component("comp1").physics("mbd").feature("cdr1hgj24").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj24").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj24").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj24").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj25", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj25").set("Source", "cdr1att25");
    model.component("comp1").physics("mbd").feature("cdr1hgj25").set("Destination", "cdr1att72");
    model.component("comp1").physics("mbd").feature("cdr1hgj25")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 25 - \u9500\u8f74 24 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj25");

    model.component("comp1").physics("mbd").feature("cdr1hgj25").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj25").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj25").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj25").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj25").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj26", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj26").set("Source", "cdr1att26");
    model.component("comp1").physics("mbd").feature("cdr1hgj26").set("Destination", "cdr1att75");
    model.component("comp1").physics("mbd").feature("cdr1hgj26")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 26 - \u9500\u8f74 27 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj26");

    model.component("comp1").physics("mbd").feature("cdr1hgj26").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj26").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj26").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj26").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj26").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj27", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj27").set("Source", "cdr1att27");
    model.component("comp1").physics("mbd").feature("cdr1hgj27").set("Destination", "cdr1att74");
    model.component("comp1").physics("mbd").feature("cdr1hgj27")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 27 - \u9500\u8f74 26 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj27");

    model.component("comp1").physics("mbd").feature("cdr1hgj27").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj27").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj27").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj27").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj27").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj28", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj28").set("Source", "cdr1att28");
    model.component("comp1").physics("mbd").feature("cdr1hgj28").set("Destination", "cdr1att77");
    model.component("comp1").physics("mbd").feature("cdr1hgj28")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 28 - \u9500\u8f74 29 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj28");

    model.component("comp1").physics("mbd").feature("cdr1hgj28").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj28").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj28").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj28").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj28").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj29", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj29").set("Source", "cdr1att29");
    model.component("comp1").physics("mbd").feature("cdr1hgj29").set("Destination", "cdr1att76");
    model.component("comp1").physics("mbd").feature("cdr1hgj29")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 29 - \u9500\u8f74 28 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj29");

    model.component("comp1").physics("mbd").feature("cdr1hgj29").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj29").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj29").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj29").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj29").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj30", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj30").set("Source", "cdr1att30");
    model.component("comp1").physics("mbd").feature("cdr1hgj30").set("Destination", "cdr1att79");
    model.component("comp1").physics("mbd").feature("cdr1hgj30")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 30 - \u9500\u8f74 31 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj30");

    model.component("comp1").physics("mbd").feature("cdr1hgj30").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj30").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj30").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj30").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj30").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj31", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj31").set("Source", "cdr1att31");
    model.component("comp1").physics("mbd").feature("cdr1hgj31").set("Destination", "cdr1att78");
    model.component("comp1").physics("mbd").feature("cdr1hgj31")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 31 - \u9500\u8f74 30 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj31");

    model.component("comp1").physics("mbd").feature("cdr1hgj31").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj31").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj31").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj31").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj31").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj32", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj32").set("Source", "cdr1att32");
    model.component("comp1").physics("mbd").feature("cdr1hgj32").set("Destination", "cdr1att81");
    model.component("comp1").physics("mbd").feature("cdr1hgj32")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 32 - \u9500\u8f74 33 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj32");

    model.component("comp1").physics("mbd").feature("cdr1hgj32").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj32").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj32").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj32").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj32").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj33", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj33").set("Source", "cdr1att33");
    model.component("comp1").physics("mbd").feature("cdr1hgj33").set("Destination", "cdr1att80");
    model.component("comp1").physics("mbd").feature("cdr1hgj33")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 33 - \u9500\u8f74 32 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj33");

    model.component("comp1").physics("mbd").feature("cdr1hgj33").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj33").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj33").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj33").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj33").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj34", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj34").set("Source", "cdr1att34");
    model.component("comp1").physics("mbd").feature("cdr1hgj34").set("Destination", "cdr1att83");
    model.component("comp1").physics("mbd").feature("cdr1hgj34")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 34 - \u9500\u8f74 35 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj34");

    model.component("comp1").physics("mbd").feature("cdr1hgj34").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj34").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj34").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj34").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj34").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj35", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj35").set("Source", "cdr1att35");
    model.component("comp1").physics("mbd").feature("cdr1hgj35").set("Destination", "cdr1att82");
    model.component("comp1").physics("mbd").feature("cdr1hgj35")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 35 - \u9500\u8f74 34 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj35");

    model.component("comp1").physics("mbd").feature("cdr1hgj35").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj35").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj35").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj35").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj35").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj36", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj36").set("Source", "cdr1att36");
    model.component("comp1").physics("mbd").feature("cdr1hgj36").set("Destination", "cdr1att85");
    model.component("comp1").physics("mbd").feature("cdr1hgj36")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 36 - \u9500\u8f74 37 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj36");

    model.component("comp1").physics("mbd").feature("cdr1hgj36").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj36").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj36").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj36").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj36").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj37", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj37").set("Source", "cdr1att37");
    model.component("comp1").physics("mbd").feature("cdr1hgj37").set("Destination", "cdr1att84");
    model.component("comp1").physics("mbd").feature("cdr1hgj37")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 37 - \u9500\u8f74 36 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj37");

    model.component("comp1").physics("mbd").feature("cdr1hgj37").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj37").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj37").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj37").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj37").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj38", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj38").set("Source", "cdr1att38");
    model.component("comp1").physics("mbd").feature("cdr1hgj38").set("Destination", "cdr1att87");
    model.component("comp1").physics("mbd").feature("cdr1hgj38")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 38 - \u9500\u8f74 39 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj38");

    model.component("comp1").physics("mbd").feature("cdr1hgj38").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj38").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj38").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj38").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj38").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj39", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj39").set("Source", "cdr1att39");
    model.component("comp1").physics("mbd").feature("cdr1hgj39").set("Destination", "cdr1att86");
    model.component("comp1").physics("mbd").feature("cdr1hgj39")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 39 - \u9500\u8f74 38 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj39");

    model.component("comp1").physics("mbd").feature("cdr1hgj39").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj39").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj39").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj39").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj39").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj40", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj40").set("Source", "cdr1att40");
    model.component("comp1").physics("mbd").feature("cdr1hgj40").set("Destination", "cdr1att89");
    model.component("comp1").physics("mbd").feature("cdr1hgj40")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 40 - \u9500\u8f74 41 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj40");

    model.component("comp1").physics("mbd").feature("cdr1hgj40").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj40").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj40").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj40").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj40").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj41", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj41").set("Source", "cdr1att41");
    model.component("comp1").physics("mbd").feature("cdr1hgj41").set("Destination", "cdr1att88");
    model.component("comp1").physics("mbd").feature("cdr1hgj41")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 41 - \u9500\u8f74 40 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj41");

    model.component("comp1").physics("mbd").feature("cdr1hgj41").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj41").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj41").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj41").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj41").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj42", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj42").set("Source", "cdr1att42");
    model.component("comp1").physics("mbd").feature("cdr1hgj42").set("Destination", "cdr1att91");
    model.component("comp1").physics("mbd").feature("cdr1hgj42")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 42 - \u9500\u8f74 43 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj42");

    model.component("comp1").physics("mbd").feature("cdr1hgj42").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj42").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj42").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj42").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj42").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj43", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj43").set("Source", "cdr1att43");
    model.component("comp1").physics("mbd").feature("cdr1hgj43").set("Destination", "cdr1att90");
    model.component("comp1").physics("mbd").feature("cdr1hgj43")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 43 - \u9500\u8f74 42 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj43");

    model.component("comp1").physics("mbd").feature("cdr1hgj43").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj43").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj43").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj43").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj43").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj44", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj44").set("Source", "cdr1att44");
    model.component("comp1").physics("mbd").feature("cdr1hgj44").set("Destination", "cdr1att93");
    model.component("comp1").physics("mbd").feature("cdr1hgj44")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 44 - \u9500\u8f74 45 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj44");

    model.component("comp1").physics("mbd").feature("cdr1hgj44").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj44").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj44").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj44").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj44").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj45", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj45").set("Source", "cdr1att45");
    model.component("comp1").physics("mbd").feature("cdr1hgj45").set("Destination", "cdr1att92");
    model.component("comp1").physics("mbd").feature("cdr1hgj45")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 45 - \u9500\u8f74 44 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj45");

    model.component("comp1").physics("mbd").feature("cdr1hgj45").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj45").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj45").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj45").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj45").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj46", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj46").set("Source", "cdr1att46");
    model.component("comp1").physics("mbd").feature("cdr1hgj46").set("Destination", "cdr1att95");
    model.component("comp1").physics("mbd").feature("cdr1hgj46")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 46 - \u9500\u8f74 47 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj46");

    model.component("comp1").physics("mbd").feature("cdr1hgj46").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj46").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj46").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj46").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj46").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj47", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj47").set("Source", "cdr1att47");
    model.component("comp1").physics("mbd").feature("cdr1hgj47").set("Destination", "cdr1att94");
    model.component("comp1").physics("mbd").feature("cdr1hgj47")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 47 - \u9500\u8f74 46 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj47");

    model.component("comp1").physics("mbd").feature("cdr1hgj47").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj47").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj47").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj47").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj47").feature("je1").set("c_u", new String[][]{{"cb"}});
    model.component("comp1").physics("mbd").create("cdr1hgj48", "HingeJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj48").set("Source", "cdr1att48");
    model.component("comp1").physics("mbd").feature("cdr1hgj48").set("Destination", "cdr1att96");
    model.component("comp1").physics("mbd").feature("cdr1hgj48")
         .label("\u94f0\u94fe\u5173\u8282: \u6eda\u5b50 48 - \u9500\u8f74 48 (cdr1)");

    model.nodeGroup("mbdcdr1jgrp").add("cdr1hgj48");

    model.component("comp1").physics("mbd").feature("cdr1hgj48").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("cdr1hgj48").feature("je1").set("ul2", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj48").feature("je1").set("ul3", new String[][]{{"1"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj48").feature("je1").set("k_u", new String[][]{{"kb"}});
    model.component("comp1").physics("mbd").feature("cdr1hgj48").feature("je1").set("c_u", new String[][]{{"cb"}});

    model.nodeGroup().create("mbdcdr1sgrp", "Physics", "mbd");
    model.nodeGroup("mbdcdr1sgrp").label("cdr1: \u94fe\u8f6e");
    model.nodeGroup("mbdcdr1sgrp").placeAfter("cdr1");

    model.component("comp1").physics("mbd").create("rd1", "RigidDomain");
    model.component("comp1").physics("mbd").feature("rd1").selection().add(1);
    model.component("comp1").physics("mbd").feature("rd1").label("\u521a\u6027\u57df\uff1a\u94fe\u8f6e 1 (cdr1)");

    model.nodeGroup("mbdcdr1sgrp").add("rd1");

    model.component("comp1").physics("mbd").create("rd2", "RigidDomain");
    model.component("comp1").physics("mbd").feature("rd2").selection().add(187);
    model.component("comp1").physics("mbd").feature("rd2").label("\u521a\u6027\u57df\uff1a\u94fe\u8f6e 2 (cdr1)");

    model.nodeGroup("mbdcdr1sgrp").add("rd2");

    model.component("comp1").physics("mbd").create("att1", "Attachment");
    model.component("comp1").physics("mbd").feature("att1").selection().add(140, 141, 152, 157);
    model.component("comp1").physics("mbd").feature("att1").label("\u8fde\u63a5\u4ef6\uff1a\u94fe\u8f6e 1 (cdr1)");

    model.nodeGroup("mbdcdr1sgrp").add("att1");

    model.component("comp1").physics("mbd").feature("att1").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("att2", "Attachment");
    model.component("comp1").physics("mbd").feature("att2").selection().add(1358, 1359, 1360, 1368);
    model.component("comp1").physics("mbd").feature("att2").label("\u8fde\u63a5\u4ef6\uff1a\u94fe\u8f6e 2 (cdr1)");

    model.nodeGroup("mbdcdr1sgrp").add("att2");

    model.component("comp1").physics("mbd").feature("att2").set("ConnectionType", "RigidType");
    model.component("comp1").physics("mbd").create("hgj1", "HingeJoint");
    model.component("comp1").physics("mbd").feature("hgj1").set("Source", "fixed");
    model.component("comp1").physics("mbd").feature("hgj1").set("Destination", "att1");
    model.component("comp1").physics("mbd").feature("hgj1")
         .label("\u94f0\u94fe\u5173\u8282\uff1a\u94fe\u8f6e: 1 (cdr1)");

    model.nodeGroup("mbdcdr1sgrp").add("hgj1");

    model.component("comp1").physics("mbd").create("hgj2", "HingeJoint");
    model.component("comp1").physics("mbd").feature("hgj2").set("Source", "fixed");
    model.component("comp1").physics("mbd").feature("hgj2").set("Destination", "att2");
    model.component("comp1").physics("mbd").feature("hgj2")
         .label("\u94f0\u94fe\u5173\u8282\uff1a\u94fe\u8f6e: 2 (cdr1)");

    model.nodeGroup("mbdcdr1sgrp").add("hgj2");

    model.component("comp1").physics("mbd").feature("cdr1").set("FeatStatus", "FeatUpdated");
    model.component("comp1").physics("mbd").feature("cdr1").set("NameChg", "0");
    model.component("comp1").physics("mbd").feature("cdr1").set("Initialize", "0");
    model.component("comp1").physics("mbd").feature("cdr1").set("SelChanged", "0");
    model.component("comp1").physics("mbd").feature("cdr1").set("SpRdChg", "0");
    model.component("comp1").physics("mbd").feature("cdr1").set("SpAttChg", "0");
    model.component("comp1").physics("mbd").feature("rd2").create("mmi1", "MassInertia", -1);
    model.component("comp1").physics("mbd").feature("rd2").feature("mmi1").set("Iz", "I_ext*para");
    model.component("comp1").physics("mbd").feature("hgj1").create("pm1", "PrescribedMotion", -1);
    model.component("comp1").physics("mbd").feature("hgj1").feature("pm1")
         .set("PrescribedMotionThroughRotational", "AngularVelocity");
    model.component("comp1").physics("mbd").feature("hgj1").feature("pm1").set("omegap", "omega*step1(t[1/s])");
    model.component("comp1").physics("mbd").feature("hgj2").create("afm1", "AppliedForceAndMoment", -1);
    model.component("comp1").physics("mbd").feature("hgj2").feature("afm1")
         .set("AppliedOnSelectedAttachment", "Joint");
    model.component("comp1").physics("mbd").feature("hgj2").feature("afm1").set("Ms", "-T_ext*step2(t[1/s])*para");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("Flj_1", "sqrt(mbd.cdr1hgj1.F_elx^2+mbd.cdr1hgj1.F_ely^2)");
    model.component("comp1").variable("var1").descr("Flj_1", "Link joint force (elastic) , joint 1");

    model.study("std1").feature("time").set("tlist", "range(0,1e-4,0.3)");
    model.study("std1").feature("time").set("useparam", true);
    model.study("std1").feature("time").setIndex("pname", "omega", 0);
    model.study("std1").feature("time").setIndex("plistarr", "", 0);
    model.study("std1").feature("time").setIndex("punit", "rad/s", 0);
    model.study("std1").feature("time").setIndex("pname", "omega", 0);
    model.study("std1").feature("time").setIndex("plistarr", "", 0);
    model.study("std1").feature("time").setIndex("punit", "rad/s", 0);
    model.study("std1").feature("time").setIndex("pname", "para", 0);
    model.study("std1").feature("time").setIndex("plistarr", "0 1", 0);
    model.study("std1").feature("time").setIndex("punit", "", 0);

    model.sol().create("sol1");
    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd26_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj24_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd2_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj18_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd41_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd4_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj6_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj6_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj18_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj18_u2").set("scalemethod", "manual");

    return model;
  }

  public static Model run5(Model model) {
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj30_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd41_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd45_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd37_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd15_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd38_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj6_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd30_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj24_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj24_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj35_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd25_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj41_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj13_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj1_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj30_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj1_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj13_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj30_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd27_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj29_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj29_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj48_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd3_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj48_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj5_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd22_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd48_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd42_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd14_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj41_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj41_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj35_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj35_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj48_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj29_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd8_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj12_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj12_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj23_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd16_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd39_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd29_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd33_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd31_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj12_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd26_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd28_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd5_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj23_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd20_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj23_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd42_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd43_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj34_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj11_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj40_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd36_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd32_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj28_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd17_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj28_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd2_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd11_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj17_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj40_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd32_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj5_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj5_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj17_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj40_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj17_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd1_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd9_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj34_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj28_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj34_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd29_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj9_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj22_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd21_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj39_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd21_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd44_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj16_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj44_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj44_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd9_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj16_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj4_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj16_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj39_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj39_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj4_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd18_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj44_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd10_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd15_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj4_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd10_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd33_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj22_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj22_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj33_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj11_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_hgj1_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd47_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj11_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj27_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj27_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd35_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd45_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj3_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd22_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd19_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd27_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj27_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj10_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj10_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj33_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj33_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj21_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd19_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd34_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj45_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj45_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd31_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj38_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd11_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj38_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj38_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd12_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj10_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd2_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd7_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj9_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj9_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd43_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj45_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd46_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd2_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd23_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj21_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj21_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd20_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj32_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj8_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj8_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_hgj2_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj26_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj15_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd16_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd35_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj26_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj8_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd12_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd46_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj15_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd8_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj26_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd6_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj43_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj3_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj32_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj3_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj32_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj15_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd4_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj20_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj46_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj14_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd47_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj46_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj7_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd18_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj37_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd24_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd44_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj43_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj43_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj37_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj37_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj2_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj14_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj14_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj2_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj46_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd3_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd24_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd36_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj20_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd13_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj20_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd38_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj31_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj2_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd30_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd5_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd48_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd39_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd13_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd25_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd40_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj25_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj25_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj1_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj13_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj25_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj42_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd23_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj31_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj31_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj19_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd37_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd40_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd28_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd7_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj47_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj47_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj36_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd14_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj42_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj7_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj42_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj7_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj36_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd17_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd6_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj19_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj36_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj19_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd34_phi").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj47_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj24_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd35_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj3_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj18_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd4_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd19_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj30_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd27_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj27_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd41_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd45_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj21_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd37_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj6_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd31_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj38_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj35_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd25_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd12_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj10_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd2_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj41_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd43_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj45_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj5_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd22_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd2_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd48_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd14_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd20_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj32_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_hgj2_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj48_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj29_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd8_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj23_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj15_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd29_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd16_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj8_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd33_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd46_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj12_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj26_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd6_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj43_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd26_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd5_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj20_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj14_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd42_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj7_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd18_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj37_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd44_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj34_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj11_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj40_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd36_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd32_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj46_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd3_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd11_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd24_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj17_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd38_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj31_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj2_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd1_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj28_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd30_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj9_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj22_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd21_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd39_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd13_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj39_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj16_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd9_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_phi").set("resscalemethod", "parent");

    return model;
  }

  public static Model run6(Model model) {
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj1_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj13_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj25_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj42_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd23_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj19_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj44_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd10_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd15_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd40_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd28_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj4_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd7_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj36_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj33_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_hgj1_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd17_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd34_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj47_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd47_phi").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd26_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj24_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd2_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj18_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd41_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd4_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj6_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj6_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj18_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj18_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj30_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd41_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd45_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd37_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd15_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd38_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj6_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd30_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj24_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj24_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj35_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd25_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj41_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj13_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj1_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj30_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj1_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj13_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj30_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd27_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj29_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj29_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj48_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd3_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj48_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj5_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd22_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd48_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd42_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd14_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj41_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj41_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj35_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj35_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj48_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj29_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd8_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj12_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj12_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj23_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd16_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd39_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd29_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd33_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd31_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj12_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd26_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd28_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd5_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj23_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd20_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj23_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd42_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd43_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj34_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj11_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj40_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd36_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd32_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj28_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd17_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj28_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd2_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd11_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj17_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj40_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd32_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj5_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj5_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj17_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj40_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj17_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd1_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd9_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj34_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj28_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj34_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd1_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd29_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj9_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj22_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd21_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj39_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd21_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd44_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj16_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj44_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj44_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd9_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj16_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj4_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj16_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj39_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj39_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj4_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd18_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj44_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd10_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd15_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj4_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd10_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd33_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj22_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj22_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj33_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj11_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_hgj1_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd47_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj11_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj27_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj27_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd35_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd45_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj3_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd22_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd19_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd27_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj27_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj10_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj10_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj33_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj33_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj21_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd19_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd34_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj45_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj45_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd31_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj38_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd11_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj38_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj38_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd12_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj10_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd2_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd7_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj9_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj9_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd43_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj45_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd46_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd2_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd23_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj21_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj21_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd20_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj32_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj8_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj8_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_hgj2_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj26_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj15_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd16_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd35_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj26_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj8_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd12_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd46_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj15_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd8_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj26_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd6_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj43_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj3_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj32_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj3_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj32_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj15_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd4_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj20_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj46_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj14_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd47_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj46_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj7_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd18_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj37_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd24_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd44_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj43_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj43_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj37_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj37_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj2_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj14_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj14_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj2_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj46_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd3_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd24_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd36_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj20_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd13_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj20_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd38_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj31_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj2_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd30_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd5_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd48_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd39_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd13_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd25_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd40_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj25_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj25_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj1_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj13_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj25_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj42_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd23_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj31_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj31_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj19_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd37_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd40_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd28_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd7_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj47_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj47_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj36_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd14_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj42_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj7_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj42_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj7_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj36_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd17_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd6_u").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj19_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj36_u2").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj19_u3").set("scaleval", "1e-2*0.14444476869958353");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1rd34_phi").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_cdr1hgj47_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,1e-4,0.3)");
    model.sol("sol1").feature("t1").set("plot", "off");
    model.sol("sol1").feature("t1").set("plotgroup", "Default");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("endtimeinterpolation", true);
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("rescaleafterinitbw", false);
    model.sol("sol1").feature("t1").create("tp1", "TimeParametric");
    model.sol("sol1").feature("t1").feature().remove("tpDef");
    model.sol("sol1").feature("t1").feature("tp1").set("control", "time");
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").feature("t1").set("tstepsbdf", "intermediate");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 5);

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u4f4d\u79fb (mbd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature("surf1").feature().create("def1", "Deform");
    model.result("pg1").feature("surf1").feature("def1").label("\u53d8\u5f62");
    model.result("pg1").feature("surf1").feature("def1").set("scaleactive", true);

    return model;
  }

  public static Model run7(Model model) {
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u901f\u5ea6 (mbd)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("expr", "mod(dom,10)");
    model.result("pg2").feature("surf1").set("unit", "1");
    model.result("pg2").feature("surf1").set("colortable", "Cyclic");
    model.result("pg2").feature("surf1").set("colorlegend", false);
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature("surf1").feature().create("def1", "Deform");
    model.result("pg2").feature("surf1").feature("def1").label("\u53d8\u5f62");
    model.result("pg2").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg2").feature().create("arwl1", "ArrowLine");
    model.result("pg2").feature("arwl1").label("\u7ebf\u4e0a\u7bad\u5934");
    model.result("pg2").feature("arwl1").set("expr", new String[]{"mbd.u_tX", "mbd.u_tY"});
    model.result("pg2").feature("arwl1").set("placement", "elements");
    model.result("pg2").feature("arwl1").set("data", "parent");
    model.result("pg2").feature("arwl1").feature().create("def1", "Deform");
    model.result("pg2").feature("arwl1").feature("def1").label("\u53d8\u5f62");
    model.result("pg2").feature("arwl1").feature("def1").set("scaleactive", true);

    model.nodeGroup().create("dset1mbdlgrp", "Results");
    model.nodeGroup("dset1mbdlgrp").label("\u5916\u52a0\u8f7d\u8377 (mbd)");
    model.nodeGroup("dset1mbdlgrp").set("type", "plotgroup");
    model.nodeGroup("dset1mbdlgrp").placeAfter("plotgroup", "pg2");
    model.nodeGroup().move("dset1mbdlgrp", 4);

    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").label("\u5168\u5c40\u8f7d\u8377 (mbd)");

    model.nodeGroup("dset1mbdlgrp").add("plotgroup", "pg3");

    model.result("pg3").set("showlegends", true);
    model.result("pg3").set("titletype", "label");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"1"});
    model.result("pg3").feature("surf1").label("\u7070\u8272\u8868\u9762");
    model.result("pg3").feature("surf1").set("coloring", "uniform");
    model.result("pg3").feature("surf1").set("color", "gray");
    model.result("pg3").feature("surf1").active(false);
    model.result("pg3").feature("surf1").create("def", "Deform");
    model.result("pg3").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg3").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg3").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg3").feature("surf1").feature("def").set("scale", 1);
    model.result("pg3").feature("surf1").create("sel1", "Selection");
    model.result("pg3").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242);
    model.result("pg3").create("pttraj1", "PointTrajectories");
    model.result("pg3").feature("pttraj1").set("plotdata", "global");
    model.result("pg3").feature("pttraj1")
         .set("expr", new String[]{"mbd.hgj2.afm1.loadposx", "mbd.hgj2.afm1.loadposy"});
    model.result("pg3").feature("pttraj1").set("pointtype", "arrow");
    model.result("pg3").feature("pttraj1").set("arrowexpr", new String[]{"mbd.hgj2.afm1.Fx", "mbd.hgj2.afm1.Fy"});
    model.result("pg3").feature("pttraj1").set("linetype", "none");
    model.result("pg3").feature("pttraj1").set("arrowbase", "tail");
    model.result("pg3").feature("pttraj1")
         .label("\u4f5c\u7528\u529b\u548c\u529b\u77e9 1 (\u94f0\u94fe\u5173\u8282\uff1a\u94fe\u8f6e: 2 (cdr1))");
    model.result("pg3").feature().move("surf1", 1);
    model.result("pg3").feature("pttraj1").set("inheritplot", "none");
    model.result("pg3").feature("pttraj1").create("col", "Color");
    model.result("pg3").feature("pttraj1").feature("col").set("expr", "comp1.mbd.hgj2.afm1.F_Mag");
    model.result("pg3").feature("pttraj1").feature("col").set("colortable", "Spectrum");
    model.result("pg3").feature("pttraj1").feature("col").set("coloring", "gradient");
    model.result("pg3").feature("pttraj1").feature("col").set("topcolor", "red");
    model.result("pg3").feature("pttraj1").set("pointcolor", "blue");

    model.nodeGroup("dset1mbdlgrp").placeAfter("plotgroup", "pg2");
    model.nodeGroup().move("dset1mbdlgrp", 4);

    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").label("\u5168\u5c40\u529b\u77e9 (mbd)");

    model.nodeGroup("dset1mbdlgrp").add("plotgroup", "pg4");

    model.result("pg4").set("showlegends", true);
    model.result("pg4").set("titletype", "label");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"1"});
    model.result("pg4").feature("surf1").label("\u7070\u8272\u8868\u9762");
    model.result("pg4").feature("surf1").set("coloring", "uniform");
    model.result("pg4").feature("surf1").set("color", "gray");
    model.result("pg4").feature("surf1").active(false);
    model.result("pg4").feature("surf1").create("def", "Deform");
    model.result("pg4").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg4").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg4").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg4").feature("surf1").feature("def").set("scale", 1);
    model.result("pg4").feature("surf1").create("sel1", "Selection");
    model.result("pg4").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242);
    model.result("pg4").create("pttraj1", "PointTrajectories");
    model.result("pg4").feature("pttraj1").set("plotdata", "global");
    model.result("pg4").feature("pttraj1")
         .set("expr", new String[]{"mbd.hgj2.afm1.momposx", "mbd.hgj2.afm1.momposy"});
    model.result("pg4").feature("pttraj1").set("pointtype", "arrow");
    model.result("pg4").feature("pttraj1").set("arrowexpr", new String[]{"mbd.hgj2.afm1.Mx", "mbd.hgj2.afm1.My"});
    model.result("pg4").feature("pttraj1").set("linetype", "none");
    model.result("pg4").feature("pttraj1").set("arrowtype", "double");
    model.result("pg4").feature("pttraj1").set("arrowbase", "tail");
    model.result("pg4").feature("pttraj1")
         .label("\u4f5c\u7528\u529b\u548c\u529b\u77e9 1 (\u94f0\u94fe\u5173\u8282\uff1a\u94fe\u8f6e: 2 (cdr1))");
    model.result("pg4").feature().move("surf1", 1);
    model.result("pg4").feature("pttraj1").set("inheritplot", "none");
    model.result("pg4").feature("pttraj1").create("col", "Color");
    model.result("pg4").feature("pttraj1").feature("col").set("expr", "comp1.mbd.hgj2.afm1.M_Mag");
    model.result("pg4").feature("pttraj1").feature("col").set("colortable", "Spectrum");
    model.result("pg4").feature("pttraj1").feature("col").set("coloring", "gradient");
    model.result("pg4").feature("pttraj1").feature("col").set("topcolor", "red");
    model.result("pg4").feature("pttraj1").set("pointcolor", "blue");

    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg1").run();
    model.result().duplicate("pg5", "pg1");
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 1, 1);
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("expr", "1");
    model.result("pg5").feature("surf1").set("titletype", "none");
    model.result("pg5").feature("surf1").set("coloring", "uniform");
    model.result("pg5").feature("surf1").set("color", "gray");
    model.result("pg5").feature().duplicate("surf2", "surf1");
    model.result("pg5").run();
    model.result("pg5").feature("surf2").set("color", "yellow");
    model.result("pg5").feature("surf2").create("sel1", "Selection");
    model.result("pg5").feature("surf2").feature("sel1").selection().named("geom1_pi1_unisel4");
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("surf3", "surf2");
    model.result("pg5").run();
    model.result("pg5").feature("surf3").set("color", "magenta");
    model.result("pg5").run();
    model.result("pg5").feature("surf3").feature("sel1").selection().set(89);
    model.result("pg5").run();
    model.result("pg5").create("arwl1", "ArrowLine");
    model.result("pg5").feature("arwl1").set("expr", new String[]{"mbd.u_tX", "mbd.u_tY"});
    model.result("pg5").feature("arwl1").set("arrowcount", 50);
    model.result("pg5").feature("arwl1").create("def1", "Deform");
    model.result("pg5").run();
    model.result("pg5").feature("arwl1").feature("def1").set("scaleactive", true);
    model.result("pg5").feature("arwl1").feature("def1").set("scale", 1);
    model.result("pg5").run();
    model.result("pg5").feature("arwl1").create("sel1", "Selection");
    model.result("pg5").feature("arwl1").feature("sel1").selection().named("geom1_pi1_unisel11");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 2, 1);
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").run();
    model.result("pg7").setIndex("looplevel", 1, 1);
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").create("arwl1", "ArrowLine");
    model.result("pg7").feature("arwl1").set("expr", new String[]{"mbd.cdr1.Fnx", "v"});
    model.result("pg7").feature("arwl1").setIndex("expr", "mbd.cdr1.Fny", 1);
    model.result("pg7").feature("arwl1").create("def1", "Deform");
    model.result("pg7").run();
    model.result("pg7").feature("arwl1").feature("def1").set("scaleactive", true);
    model.result("pg7").feature("arwl1").feature("def1").set("scale", 1);
    model.result("pg7").run();
    model.result("pg7").feature("arwl1").set("arrowcount", 2000);
    model.result("pg7").run();
    model.result("pg7").create("line1", "Line");
    model.result("pg7").feature("line1").set("expr", "mbd.cdr1.F");
    model.result("pg7").feature("line1").set("linetype", "tube");
    model.result("pg7").feature("line1").set("colortable", "RainbowLight");
    model.result("pg7").feature("line1").create("def1", "Deform");
    model.result("pg7").run();
    model.result("pg7").feature("line1").feature("def1").set("scaleactive", true);
    model.result("pg7").feature("line1").feature("def1").set("scale", 1);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").setIndex("looplevel", 2, 1);
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").set("titletype", "label");
    model.result("pg9").set("xlabelactive", true);
    model.result("pg9").set("ylabelactive", true);
    model.result("pg9").set("legendpos", "upperleft");
    model.result("pg9").create("glob1", "Global");
    model.result("pg9").feature("glob1").setIndex("expr", "mbd.cdr1hgj1.th", 0);
    model.result("pg9").feature("glob1").setIndex("unit", "rad", 0);
    model.result("pg9").feature("glob1").setIndex("descr", "Relative rotation", 0);
    model.result("pg9").feature("glob1").set("legendmethod", "manual");
    model.result("pg9").feature("glob1").setIndex("legends", "Unloaded", 0);
    model.result("pg9").feature("glob1").setIndex("legends", "Loaded", 1);
    model.result("pg9").run();
    model.result().duplicate("pg10", "pg9");
    model.result("pg10").run();
    model.result("pg10").set("legendpos", "upperright");
    model.result("pg10").run();
    model.result("pg10").feature("glob1").setIndex("expr", "Flj_1", 0);
    model.result("pg10").feature("glob1").setIndex("unit", "N", 0);
    model.result("pg10").feature("glob1").setIndex("descr", "Link joint force, joint 1", 0);
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").run();
    model.result("pg11").set("titletype", "label");
    model.result("pg11").set("xlabelactive", true);
    model.result("pg11").set("ylabelactive", true);
    model.result("pg11").set("legendpos", "upperleft");
    model.result("pg11").create("glob1", "Global");
    model.result("pg11").feature("glob1").set("data", "dset1");
    model.result("pg11").feature("glob1").setIndex("looplevelinput", "first", 1);
    model.result("pg11").feature("glob1").setIndex("expr", "mbd.hgj1.th", 0);
    model.result("pg11").feature("glob1").setIndex("unit", "rad", 0);
    model.result("pg11").feature("glob1").setIndex("descr", "Relative rotation", 0);
    model.result("pg11").feature("glob1").set("legendmethod", "manual");
    model.result("pg11").feature("glob1").setIndex("legends", "Driver", 0);
    model.result("pg11").feature().duplicate("glob2", "glob1");
    model.result("pg11").run();
    model.result("pg11").feature("glob2").setIndex("looplevelinput", "all", 1);
    model.result("pg11").feature("glob2").setIndex("expr", "mbd.hgj2.th", 0);
    model.result("pg11").feature("glob2").setIndex("unit", "rad", 0);
    model.result("pg11").feature("glob2").setIndex("descr", "Relative rotation", 0);
    model.result("pg11").feature("glob2").setIndex("legends", "Driven (unloaded)", 0);
    model.result("pg11").feature("glob2").setIndex("legends", "Driven (loaded)", 1);
    model.result("pg11").run();
    model.result().duplicate("pg12", "pg11");
    model.result("pg12").run();
    model.result("pg12").set("legendpos", "upperright");
    model.result("pg12").run();
    model.result("pg12").feature("glob1").setIndex("expr", "mbd.hgj1.tht", 0);
    model.result("pg12").feature("glob1").setIndex("unit", "rad/s", 0);
    model.result("pg12").feature("glob1").setIndex("descr", "Relative angular velocity", 0);
    model.result("pg12").run();
    model.result("pg12").feature("glob2").setIndex("expr", "mbd.hgj2.tht", 0);
    model.result("pg12").feature("glob2").setIndex("unit", "rad/s", 0);
    model.result("pg12").feature("glob2").setIndex("descr", "Relative angular velocity", 0);
    model.result("pg12").run();
    model.result().duplicate("pg13", "pg12");
    model.result("pg13").run();
    model.result("pg13").run();
    model.result("pg13").feature("glob1").setIndex("expr", "mbd.hgj1.thtt", 0);
    model.result("pg13").feature("glob1").setIndex("unit", "rad/s^2", 0);
    model.result("pg13").feature("glob1").setIndex("descr", "Relative rotation, second time derivative", 0);
    model.result("pg13").run();
    model.result("pg13").feature("glob2").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg13").feature("glob2").setIndex("looplevelindices", "range(2,1,3001)", 0);
    model.result("pg13").feature("glob2").setIndex("expr", "mbd.hgj2.thtt", 0);
    model.result("pg13").feature("glob2").setIndex("unit", "rad/s^2", 0);
    model.result("pg13").feature("glob2").setIndex("descr", "Relative rotation, second time derivative", 0);
    model.result("pg13").run();
    model.result().duplicate("pg14", "pg13");
    model.result("pg14").run();
    model.result("pg14").set("xlabelactive", false);
    model.result("pg14").set("ylabelactive", false);
    model.result("pg14").run();
    model.result("pg14").feature("glob1").set("xdata", "spectrum");
    model.result("pg14").run();
    model.result("pg14").feature("glob2").set("xdata", "spectrum");
    model.result("pg14").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("fontsize", "9");
    model.result().export("anim1").set("colortheme", "globaltheme");
    model.result().export("anim1").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim1").set("background", "color");
    model.result().export("anim1").set("gltfincludelines", "on");
    model.result().export("anim1").set("title1d", "on");
    model.result().export("anim1").set("legend1d", "on");
    model.result().export("anim1").set("logo1d", "on");
    model.result().export("anim1").set("options1d", "on");
    model.result().export("anim1").set("title2d", "on");
    model.result().export("anim1").set("legend2d", "on");
    model.result().export("anim1").set("logo2d", "on");
    model.result().export("anim1").set("options2d", "off");
    model.result().export("anim1").set("title3d", "on");
    model.result().export("anim1").set("legend3d", "on");
    model.result().export("anim1").set("logo3d", "on");
    model.result().export("anim1").set("options3d", "off");
    model.result().export("anim1").set("axisorientation", "on");
    model.result().export("anim1").set("grid", "on");
    model.result().export("anim1").set("axes1d", "on");
    model.result().export("anim1").set("axes2d", "on");
    model.result().export("anim1").set("showgrid", "on");
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("maxframes", 100);
    model.result().export().duplicate("anim2", "anim1");
    model.result().export("anim2").showFrame();
    model.result().export("anim2").set("plotgroup", "pg5");
    model.result().export().duplicate("anim3", "anim2");
    model.result().export("anim3").showFrame();
    model.result().export("anim3").set("plotgroup", "pg6");
    model.result().export().duplicate("anim4", "anim3");
    model.result().export("anim4").showFrame();
    model.result().export("anim4").set("plotgroup", "pg7");
    model.result().export("anim4").set("synchronize", false);
    model.result().export().duplicate("anim5", "anim4");
    model.result().export("anim5").showFrame();
    model.result().export("anim5").set("plotgroup", "pg8");
    model.result("pg1").run();

    model
         .comments("\u6eda\u5b50\u94fe\u8f6e\u603b\u6210\u7684\u52a8\u529b\u5b66\n\n\u672c\u4f8b\u6a21\u62df\u94fe\u8f6e\u603b\u6210\u7684\u52a8\u529b\u5b66\u3002\u8be5\u7cfb\u7edf\u7531\u4e8c\u7ef4\u6a21\u5f0f\u4e0b\u7f20\u7ed5\u5728\u4e24\u4e2a\u94fe\u8f6e\u4e0a\u7684\u6eda\u5b50\u94fe\u7ec4\u6210\u3002\u6eda\u5b50\u94fe\u901a\u8fc7\u7ec4\u88c5\u521a\u6027\u6eda\u5b50\u677f\u548c\u9500\u677f\u6784\u6210\uff0c\u4f7f\u5f97\u94fe\u8282\u4e4b\u95f4\u7684\u76f8\u5bf9\u65cb\u8f6c\u4e0d\u53d7\u9650\u5236\u3002\u8be5\u94fe\u6761\u7528\u4e8e\u5728\u76f8\u8ddd\u4e00\u5b9a\u8ddd\u79bb\u7684\u4e24\u4e2a\u94fe\u8f6e\u4e4b\u95f4\u4f20\u9012\u65cb\u8f6c\u8fd0\u52a8\u3002\n\n\u5728\u9a71\u52a8\u94fe\u8f6e\u4e0a\u6307\u5b9a\u89d2\u901f\u5ea6\uff0c\u5728\u4ece\u52a8\u94fe\u8f6e\u4e0a\u65bd\u52a0\u8d1f\u8f7d\u626d\u77e9\u3002\u901a\u8fc7\u77ac\u6001\u7814\u7a76\uff0c\u5206\u6790\u4e86\u4ece\u52a8\u8f74\u88c5\u8f7d\u548c\u5378\u8f7d\u4e24\u79cd\u60c5\u51b5\u4e0b\u7684\u7cfb\u7edf\u52a8\u529b\u5b66\u3002\u7ed3\u679c\u663e\u793a\u4e86\u4e24\u79cd\u60c5\u51b5\u4e0b\u94fe\u8282\u8fd0\u52a8\u3001\u63a5\u89e6\u529b\u548c\u5176\u4ed6\u53c2\u6570\u7684\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("roller_chain_dynamics.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    model = run3(model);
    model = run4(model);
    model = run5(model);
    model = run6(model);
    run7(model);
  }

}
