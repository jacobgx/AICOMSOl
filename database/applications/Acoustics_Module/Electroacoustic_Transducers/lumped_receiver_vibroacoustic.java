/*
 * lumped_receiver_vibroacoustic.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:39 by COMSOL 6.3.0.290. */
public class lumped_receiver_vibroacoustic {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Electroacoustic_Transducers");

    model.param().set("Vrms", "0.109[V]", "\u989d\u5b9a\u9a71\u52a8\u7535\u538b");
    model.param().set("fmax", "10[kHz]", "\u6700\u5927\u8c10\u632f\u9891\u7387");
    model.param().set("c0", "343[m/s]", "\u58f0\u901f");
    model.param().set("Mass", "3.223627e-04[kg]", "\u63a5\u6536\u5668\u603b\u8d28\u91cf");
    model.param()
         .set("CMx1", "-2.763900e-05[m]", "\u76f8\u5bf9\u4e8e\u76d2\u4e2d\u5fc3\u7684\u63a5\u6536\u5668\u8d28\u5fc3\u5750\u6807\uff08\u5c40\u90e8 x\uff09");
    model.param()
         .set("CMx2", "0.000000e+00[m]", "\u76f8\u5bf9\u4e8e\u76d2\u4e2d\u5fc3\u7684\u63a5\u6536\u5668\u8d28\u5fc3\u5750\u6807\uff08\u5c40\u90e8 y\uff09");
    model.param()
         .set("CMx3", "-2.193400e-05[m]", "\u76f8\u5bf9\u4e8e\u76d2\u4e2d\u5fc3\u7684\u63a5\u6536\u5668\u8d28\u5fc3\u5750\u6807\uff08\u5c40\u90e8 z\uff09");
    model.param()
         .set("Ixx", "7.551981e-10[m^2*kg]", "\u63a5\u6536\u5668\u8d28\u5fc3\u5904\u7684\u60ef\u6027\u5f20\u91cf\uff08\u5c40\u90e8 xx\uff09");
    model.param()
         .set("Iyy", "1.967544e-09[m^2*kg]", "\u63a5\u6536\u5668\u8d28\u5fc3\u5904\u7684\u60ef\u6027\u5f20\u91cf\uff08\u5c40\u90e8 yy\uff09");
    model.param()
         .set("Izz", "2.225126e-09[m^2*kg]", "\u63a5\u6536\u5668\u8d28\u5fc3\u5904\u7684\u60ef\u6027\u5f20\u91cf\uff08\u5c40\u90e8 zz\uff09");
    model.param()
         .set("Ixy", "0.000000e+00[m^2*kg]", "\u63a5\u6536\u5668\u8d28\u5fc3\u5904\u7684\u60ef\u6027\u5f20\u91cf\uff08\u5c40\u90e8 xy\uff09");
    model.param()
         .set("Ixz", "-3.945634e-11[m^2*kg]", "\u63a5\u6536\u5668\u8d28\u5fc3\u5904\u7684\u60ef\u6027\u5f20\u91cf\uff08\u5c40\u90e8 xz\uff09");
    model.param()
         .set("Iyz", "0.000000e+00[m^2*kg]", "\u63a5\u6536\u5668\u8d28\u5fc3\u5904\u7684\u60ef\u6027\u5f20\u91cf\uff08\u5c40\u90e8 yz\uff09");
    model.param().set("Fx1", "1.790000e-06[N/V]", "\u5c40\u90e8 x Leff_v \u529b\u5e38\u6570\u4e58\u5b50");
    model.param().set("Fx2", "-1.490000e-06[N/V]", "\u5c40\u90e8 x Lmech2_v \u529b\u5e38\u6570\u4e58\u5b50");
    model.param().set("Fz1", "-1.920000e-05[N/V]", "\u5c40\u90e8 z Leff_v \u529b\u5e38\u6570\u4e58\u5b50");
    model.param().set("Fz2", "-9.600000e-06[N/V]", "\u5c40\u90e8 z Lmech2_v \u529b\u5e38\u6570\u4e58\u5b50");
    model.param()
         .set("My1", "-1.490000e-08[N*m/V]", "\u5c40\u90e8 y \u8f74 Leff_v \u626d\u77e9\u5e38\u6570\u4e58\u5b50");
    model.param()
         .set("My2", "-5.740000e-09[N*m/V]", "\u5c40\u90e8 y \u8f74 Lmech2_v \u626d\u77e9\u5e38\u6570\u4e58\u5b50");
    model.param()
         .set("G_arm", "9.850000e-11[1]", "GKarm \u589e\u76ca - \u7528\u53d8\u91cf GKarm \u66ff\u4ee3 RKarm \u503c");
    model.param().set("Ltube", "9.00[mm]", "\u7845\u80f6\u7ba1\u957f\u5ea6");
    model.param().set("Ttube", "0.5[mm]", "\u7845\u80f6\u7ba1\u539a\u5ea6");
    model.param().set("d_2cc", "18.00[mm]", "\u8026\u5408\u5668\u76f4\u5f84");
    model.param().set("L_2cc", "7.00[mm]", "\u8026\u5408\u5668\u957f\u5ea6");
    model.param().set("SToffset", "0.5[mm]", "\u7845\u80f6\u7ba1\u7ba1\u53e3\u504f\u79fb\u91cf");
    model.param().set("CToffset", "1.5[mm]", "\u7845\u80f6\u7ba1\u8026\u5408\u5668\u504f\u79fb\u91cf");
    model.param().set("LtubeC", "2.5[mm]", "\u8026\u5408\u5668\u7ba1\u9002\u914d\u5668\u957f\u5ea6");
    model.param().set("Lx", "7.870000e-03[m]", "\u63a5\u6536\u76d2\u957f\u5ea6\uff08\u5c40\u90e8 x\uff09");
    model.param().set("Ly", "4.090000e-03[m]", "\u63a5\u6536\u76d2\u5bbd\u5ea6\uff08\u5c40\u90e8 y\uff09");
    model.param().set("Lz", "2.790000e-03[m]", "\u63a5\u6536\u76d2\u9ad8\u5ea6\uff08\u5c40\u90e8 z\uff09");
    model.param()
         .set("TL", "1.600000e-03[m]", "\u81ea\u76d2\u8868\u9762\u7684\u63a5\u6536\u7ba1\u957f\u5ea6\uff08\u5c40\u90e8 x\uff09");
    model.param().set("Td", "1.00e-03[m]", "\u63a5\u6536\u7ba1\u5185\u5f84");
    model.param()
         .set("Th", "3.250000e-04[m]", "\u76f8\u5bf9\u4e8e\u76d2\u4e2d\u5fc3\u7684\u63a5\u6536\u7ba1\u8f74\u4f4d\u7f6e\uff08\u5c40\u90e8 z\uff09");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").geomRep("comsol");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"Lx", "Ly", "Lz"});
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "Td/2");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "TL");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"Lx/2", "0", "Th"});
    model.component("comp1").geom("geom1").feature("cyl1").set("axis", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "Td/2+Ttube");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "Ltube");
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new String[]{"Lx/2+SToffset", "0", "Th"});
    model.component("comp1").geom("geom1").feature("cyl2").set("axis", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").feature("cyl2").set("layername", new String[]{"\u5c42 1"});
    model.component("comp1").geom("geom1").feature("cyl2").setIndex("layer", "Ttube", 0);
    model.component("comp1").geom("geom1").create("cyl3", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl3").set("r", "Td/2");
    model.component("comp1").geom("geom1").feature("cyl3").set("h", "LtubeC");
    model.component("comp1").geom("geom1").feature("cyl3")
         .set("pos", new String[]{"Lx/2+SToffset+Ltube-(LtubeC-CToffset)", "0", "Th"});
    model.component("comp1").geom("geom1").feature("cyl3").set("axis", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").create("cyl4", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl4").set("r", "d_2cc/2");
    model.component("comp1").geom("geom1").feature("cyl4").set("h", "L_2cc");
    model.component("comp1").geom("geom1").feature("cyl4")
         .set("pos", new String[]{"Lx/2+SToffset+Ltube+CToffset", "0", "Th"});
    model.component("comp1").geom("geom1").feature("cyl4").set("axis", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").selection("face").set("cyl1(1)", 4);
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").selection("face").set("cyl3(1)", 3);
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").feature("par1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("cyl2");
    model.component("comp1").geom("geom1").create("par2", "Partition");
    model.component("comp1").geom("geom1").feature("par2").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").feature("par2").selection("input").set("par1");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").set("displx", "1[mm]");
    model.component("comp1").geom("geom1").feature("mov1").set("disply", "1[mm]");
    model.component("comp1").geom("geom1").feature("mov1").selection("input")
         .set("blk1", "cyl1", "cyl3", "cyl4", "par2");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").set("axis", new int[]{0, 1, 0});
    model.component("comp1").geom("geom1").feature("rot1").setIndex("rot", "30", 0);
    model.component("comp1").geom("geom1").feature("rot1").set("pos", new String[]{"1[mm]", "1[mm]", "0"});
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("mov1");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp3").set("rot", 180);
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").selection("face").set("rot1(1)", 4);
    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").set(1, 2, 5);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").set(10, 16, 19);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").geom("geom1", 2);
    model.component("comp1").selection("sel3").set(35, 36, 40, 43);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").geom("geom1", 2);
    model.component("comp1").selection("sel4").set(34);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").geom("geom1", 2);
    model.component("comp1").selection("sel5").set(78);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").set(3, 4, 6, 7, 8, 9, 11, 12, 14, 15, 17, 18);
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection().create("sel7", "Explicit");
    model.component("comp1").selection("sel7").set(10, 13, 16, 19);
    model.component("comp1").selection("sel1").label("\u6362\u80fd\u5668");
    model.component("comp1").selection("sel2").label("\u5185\u7ba1");
    model.component("comp1").selection("sel3").label("\u58f0-\u7ed3\u6784\u76f8\u4e92\u4f5c\u7528");
    model.component("comp1").selection("sel4").label("\u5165\u53e3");
    model.component("comp1").selection("sel5").label("\u9ea6\u514b\u98ce");
    model.component("comp1").selection("sel6").label("\u7845\u80f6\u7ba1");
    model.component("comp1").selection("uni1").label("\u56fa\u4f53\u57df");
    model.component("comp1").selection("uni1").set("input", new String[]{"sel1", "sel6"});
    model.component("comp1").selection("sel7").label("\u7a7a\u6c14\u57df");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1")
         .set("V0", "sqrt(2)*Vrms", "\u5cf0\u95f4\u989d\u5b9a\u9a71\u52a8\u7535\u538b");
    model.component("comp1").variable("var1")
         .set("pmic", "intop_mic(p)/intop_mic(1)", "\u9ea6\u514b\u98ce\u7aef\u53e3\u5904\u7684\u5e73\u5747\u538b\u529b");
    model.component("comp1").variable("var1")
         .set("vx_local", "sys2.T11*solid.u_tX + sys2.T12*solid.u_tY + sys2.T13*solid.u_tZ", "\u5c40\u90e8\u5750\u6807\u7cfb\u4e2d\u7684 x \u901f\u5ea6\u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("vy_local", "sys2.T21*solid.u_tX + sys2.T22*solid.u_tY + sys2.T23*solid.u_tZ", "\u5c40\u90e8\u5750\u6807\u7cfb\u4e2d\u7684 y \u901f\u5ea6\u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("vz_local", "sys2.T31*solid.u_tX + sys2.T32*solid.u_tY + sys2.T33*solid.u_tZ", "\u5c40\u90e8\u5750\u6807\u7cfb\u4e2d\u7684 z \u901f\u5ea6\u5206\u91cf");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2")
         .set("CMx", "mass1.CMX+(sys2.invT11*CMx1+sys2.invT12*CMx2+sys2.invT13*CMx3)", "\u63a5\u6536\u5668\u8d28\u5fc3 x \u5750\u6807");
    model.component("comp1").variable("var2")
         .set("CMy", "mass1.CMY+(sys2.invT21*CMx1+sys2.invT22*CMx2+sys2.invT23*CMx3)", "\u63a5\u6536\u5668\u8d28\u5fc3 y \u5750\u6807");
    model.component("comp1").variable("var2")
         .set("CMz", "mass1.CMZ+(sys2.invT31*CMx1+sys2.invT32*CMx2+sys2.invT33*CMx3)", "\u63a5\u6536\u5668\u8d28\u5fc3 z \u5750\u6807");
    model.component("comp1").variable("var2")
         .set("GKarm", "1[ohm]/(G_arm*sqrt(i*2*pi*freq[1/Hz]))", "GKarm \u534a\u7535\u5bb9\u7684\u963b\u6297");
    model.component("comp1").variable("var2")
         .set("Fx", "Fx1*cir.X1.LEFF.v+Fx2*cir.X1.LMECH2.v", "\u529b\u7684 x\uff08\u7ba1\uff09\u5206\u91cf");
    model.component("comp1").variable("var2")
         .set("Fz", "Fz1*cir.X1.LEFF.v+Fz2*cir.X1.LMECH2.v", "\u529b\u7684 z\uff08\u76d6\uff09\u5206\u91cf");
    model.component("comp1").variable("var2")
         .set("My", "My1*cir.X1.LEFF.v+My2*cir.X1.LMECH2.v", "\u7ed5 y \u8f74\u7684\u626d\u77e9");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat1").selection().named("sel7");
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
    model.component("comp1").material("mat2").selection().named("sel6");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "\u6768\u6c0f\u6a21\u91cf\u548c\u6cca\u677e\u6bd4");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").selection().named("sel5");

    model.component("comp1").coordSystem().create("sys2", "VectorBase");

    model.component("comp1").physics().create("cir", "Circuit", "geom1");
    model.component("comp1").physics("cir").create("TEC30033", "SubCircuitBlock", -1);
    model.component("comp1").physics("cir").feature("TEC30033").create("RDC", "Resistor", -1);
    model.component("comp1").physics("cir").feature("TEC30033").create("G1_1", "VoltageCurrentSource", -1);
    model.component("comp1").physics("cir").feature("TEC30033").create("G1_2", "VoltageCurrentSource", -1);
    model.component("comp1").physics("cir").feature("TEC30033").create("RKARM", "Resistor", -1);
    model.component("comp1").physics("cir").feature("TEC30033").create("CGAP", "Capacitor", -1);
    model.component("comp1").physics("cir").feature("TEC30033").create("CEFF", "Capacitor", -1);
    model.component("comp1").physics("cir").feature("TEC30033").create("LEFF", "Inductor", -1);
    model.component("comp1").physics("cir").feature("TEC30033").create("CMECH2", "Capacitor", -1);
    model.component("comp1").physics("cir").feature("TEC30033").create("LMECH2", "Inductor", -1);
    model.component("comp1").physics("cir").feature("TEC30033").create("RPIERCE", "Resistor", -1);
    model.component("comp1").physics("cir").feature("TEC30033").create("CBACK", "Capacitor", -1);
    model.component("comp1").physics("cir").feature("TEC30033").create("CFRONT", "Capacitor", -1);
    model.component("comp1").physics("cir").feature("TEC30033").create("RPORT", "Resistor", -1);
    model.component("comp1").physics("cir").feature("TEC30033").create("LPORT", "Inductor", -1);
    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics("solid").selection().named("uni1");
    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");
    model.component("comp1").physics("acpr").selection().named("sel7");

    model.component("comp1").massProp().create("mass1", "MassProperties");
    model.component("comp1").massProp("mass1").selection().set(1);

    model.component("comp1").variable("var1").label("\u4e3b\u8981\u53d8\u91cf");
    model.component("comp1").variable("var2").label("\u63a5\u6536\u5668\u53d8\u91cf");

    model.component("comp1").view("view1").set("renderwireframe", true);

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
    model.component("comp1").material("mat2").label("\u7ba1\uff08\u7845\u80f6\uff09");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "1100[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "7e6[Pa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.47");

    model.component("comp1").cpl("intop1").set("opname", "intop_mic");

    model.component("comp1").coordSystem("sys2").set("workplane", "wp3");

    model.component("comp1").physics("cir").feature("TEC30033")
         .set("Connections", new String[][]{{"P1"}, {"N1"}, {"P2"}, {"N2"}});
    model.component("comp1").physics("cir").feature("TEC30033").label("\u5b50\u7535\u8def\u5b9a\u4e49 TEC30033");
    model.component("comp1").physics("cir").feature("TEC30033").feature("RDC").set("R", "22.0");
    model.component("comp1").physics("cir").feature("TEC30033").feature("RDC")
         .set("Connections", new String[][]{{"P1"}, {"KN010"}});
    model.component("comp1").physics("cir").feature("TEC30033").feature("RDC").label("\u7535\u963b RDC");
    model.component("comp1").physics("cir").feature("TEC30033").feature("G1_1")
         .set("Connections", new String[][]{{"KN020"}, {"N2"}, {"KN010"}, {"N1"}});
    model.component("comp1").physics("cir").feature("TEC30033").feature("G1_1").set("gain", 7.78E-6);
    model.component("comp1").physics("cir").feature("TEC30033").feature("G1_1")
         .label("\u7535\u538b\u63a7\u5236\u7684\u7535\u6d41\u6e90 G1_1");
    model.component("comp1").physics("cir").feature("TEC30033").feature("G1_2")
         .set("Connections", new String[][]{{"KN010"}, {"N1"}, {"KN020"}, {"N2"}});
    model.component("comp1").physics("cir").feature("TEC30033").feature("G1_2").set("gain", -7.78E-6);
    model.component("comp1").physics("cir").feature("TEC30033").feature("G1_2")
         .label("\u7535\u538b\u63a7\u5236\u7684\u7535\u6d41\u6e90 G1_2");
    model.component("comp1").physics("cir").feature("TEC30033").feature("RKARM").set("R", "1.0");
    model.component("comp1").physics("cir").feature("TEC30033").feature("RKARM")
         .set("Connections", new String[][]{{"KN020"}, {"KN040"}});
    model.component("comp1").physics("cir").feature("TEC30033").feature("RKARM").label("\u7535\u963b RKARM");
    model.component("comp1").physics("cir").feature("TEC30033").feature("CGAP").set("C", 2.06E-13);
    model.component("comp1").physics("cir").feature("TEC30033").feature("CGAP")
         .set("Connections", new String[][]{{"KN040"}, {"N2"}});
    model.component("comp1").physics("cir").feature("TEC30033").feature("CGAP").label("\u7535\u5bb9\u5668 CGAP");
    model.component("comp1").physics("cir").feature("TEC30033").feature("CEFF").set("C", 2.78E-13);
    model.component("comp1").physics("cir").feature("TEC30033").feature("CEFF")
         .set("Connections", new String[][]{{"KN040"}, {"KN050"}});
    model.component("comp1").physics("cir").feature("TEC30033").feature("CEFF").label("\u7535\u5bb9\u5668 CEFF");
    model.component("comp1").physics("cir").feature("TEC30033").feature("LEFF").set("L", "33100.0");
    model.component("comp1").physics("cir").feature("TEC30033").feature("LEFF")
         .set("Connections", new String[][]{{"KN050"}, {"KN060"}});
    model.component("comp1").physics("cir").feature("TEC30033").feature("LEFF").label("\u7535\u611f\u5668 LEFF");
    model.component("comp1").physics("cir").feature("TEC30033").feature("CMECH2").set("C", 6.21E-14);
    model.component("comp1").physics("cir").feature("TEC30033").feature("CMECH2")
         .set("Connections", new String[][]{{"KN060"}, {"N2"}});
    model.component("comp1").physics("cir").feature("TEC30033").feature("CMECH2").label("\u7535\u5bb9\u5668 CMECH2");
    model.component("comp1").physics("cir").feature("TEC30033").feature("LMECH2").set("L", "25300.0");
    model.component("comp1").physics("cir").feature("TEC30033").feature("LMECH2")
         .set("Connections", new String[][]{{"KN060"}, {"KN070"}});
    model.component("comp1").physics("cir").feature("TEC30033").feature("LMECH2").label("\u7535\u611f\u5668 LMECH2");
    model.component("comp1").physics("cir").feature("TEC30033").feature("RPIERCE").set("R", 1.05E10);
    model.component("comp1").physics("cir").feature("TEC30033").feature("RPIERCE")
         .set("Connections", new String[][]{{"KN070"}, {"N2"}});
    model.component("comp1").physics("cir").feature("TEC30033").feature("RPIERCE").label("\u7535\u963b RPIERCE");
    model.component("comp1").physics("cir").feature("TEC30033").feature("CBACK").set("C", 2.64E-13);
    model.component("comp1").physics("cir").feature("TEC30033").feature("CBACK")
         .set("Connections", new String[][]{{"KN070"}, {"KN090"}});
    model.component("comp1").physics("cir").feature("TEC30033").feature("CBACK").label("\u7535\u5bb9\u5668 CBACK");
    model.component("comp1").physics("cir").feature("TEC30033").feature("CFRONT").set("C", 4.44E-14);
    model.component("comp1").physics("cir").feature("TEC30033").feature("CFRONT")
         .set("Connections", new String[][]{{"KN090"}, {"N2"}});
    model.component("comp1").physics("cir").feature("TEC30033").feature("CFRONT").label("\u7535\u5bb9\u5668 CFRONT");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("cir").feature("TEC30033").feature("RPORT").set("R", "8.17E7");
    model.component("comp1").physics("cir").feature("TEC30033").feature("RPORT")
         .set("Connections", new String[][]{{"KN090"}, {"KN100"}});
    model.component("comp1").physics("cir").feature("TEC30033").feature("RPORT").label("\u7535\u963b RPORT");
    model.component("comp1").physics("cir").feature("TEC30033").feature("LPORT").set("L", "3170.0");
    model.component("comp1").physics("cir").feature("TEC30033").feature("LPORT")
         .set("Connections", new String[][]{{"KN100"}, {"P2"}});
    model.component("comp1").physics("cir").feature("TEC30033").feature("LPORT").label("\u7535\u611f\u5668 LPORT");

    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");

    model.component("comp1").physics("cir").feature("TEC30033").feature("RKARM").set("R", "GKarm");
    model.component("comp1").physics("cir").create("X1", "SubCircuit", -1);
    model.component("comp1").physics("cir").feature("X1").set("subCircuitName", "TEC30033");
    model.component("comp1").physics("cir").feature("X1").setIndex("Connections", "p1", 0, 0);
    model.component("comp1").physics("cir").feature("X1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("X1").setIndex("Connections", "p2", 2, 0);
    model.component("comp1").physics("cir").feature("X1").setIndex("Connections", 0, 3, 0);
    model.component("comp1").physics("cir").create("V1", "VoltageSource", -1);
    model.component("comp1").physics("cir").feature("V1").setIndex("Connections", "p1", 0, 0);
    model.component("comp1").physics("cir").feature("V1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("V1").set("value", "V0");
    model.component("comp1").physics("cir").create("IvsU1", "ModelDeviceIV", -1);
    model.component("comp1").physics("cir").feature("IvsU1").setIndex("Connections", "p2", 0, 0);
    model.component("comp1").physics("cir").feature("IvsU1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("solid").feature("lemm1").create("dmp1", "Damping", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1")
         .set("DampingType", "IsotropicLossFactor");

    model.component("comp1").material("mat2").propertyGroup("def").set("lossfactor", new String[]{"0.1"});

    model.component("comp1").physics("solid").create("rd1", "RigidDomain", 3);
    model.component("comp1").physics("solid").feature("rd1").selection().named("sel1");
    model.component("comp1").physics("solid").feature("rd1").set("rho_mat", "userdef");
    model.component("comp1").physics("solid").feature("rd1").set("CenterOfRotationType", "UserDefined");
    model.component("comp1").physics("solid").feature("rd1").set("xc", new String[]{"CMx", "CMy", "CMz"});
    model.component("comp1").physics("solid").feature("rd1").create("mmi1", "MassInertia", -1);
    model.component("comp1").physics("solid").feature("rd1").feature("mmi1").set("coordinateSystem", "sys2");
    model.component("comp1").physics("solid").feature("rd1").feature("mmi1").set("CenterOfMassType", "userDefined");
    model.component("comp1").physics("solid").feature("rd1").feature("mmi1")
         .set("xm", new String[]{"CMx", "CMy", "CMz"});
    model.component("comp1").physics("solid").feature("rd1").feature("mmi1").set("mt", "Mass");
    model.component("comp1").physics("solid").feature("rd1").feature("mmi1")
         .set("mi", new String[]{"Ixx", "Ixy", "Ixz", "Ixy", "Iyy", "Iyz", "Ixz", "Iyz", "Izz"});
    model.component("comp1").physics("solid").feature("rd1").create("af1", "AppliedForce", -1);
    model.component("comp1").physics("solid").feature("rd1").feature("af1").set("coordinateSystem", "sys2");
    model.component("comp1").physics("solid").feature("rd1").feature("af1")
         .set("PointOfApplicationType", "userDefined[RD]");
    model.component("comp1").physics("solid").feature("rd1").feature("af1")
         .set("Xpa", new String[]{"CMx", "CMy", "CMz"});
    model.component("comp1").physics("solid").feature("rd1").feature("af1").set("Ft", new String[]{"Fx", "0", "Fz"});
    model.component("comp1").physics("solid").feature("rd1").create("am1", "AppliedMoment", -1);
    model.component("comp1").physics("solid").feature("rd1").feature("am1").set("coordinateSystem", "sys2");
    model.component("comp1").physics("solid").feature("rd1").feature("am1").set("Mt", new String[]{"0", "My", "0"});
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().set(55, 56, 60, 63);
    model.component("comp1").physics("acpr").create("lport1", "LumpedPort", 2);
    model.component("comp1").physics("acpr").feature("lport1").selection().set(34);
    model.component("comp1").physics("acpr").feature("lport1").set("ConnectionType", "Circuit");
    model.component("comp1").physics("cir").feature("IvsU1").set("V_src", "root.comp1.acpr.lport1.V_cir");
    model.component("comp1").physics("acpr").create("nra1", "NarrowRegionAcousticsModel", 3);
    model.component("comp1").physics("acpr").feature("nra1").selection().named("sel2");
    model.component("comp1").physics("acpr").feature("nra1").set("DuctType", "CircularDuct");
    model.component("comp1").physics("acpr").feature("nra1").set("a", "Td/2");

    model.component("comp1").multiphysics().create("asb1", "AcousticStructureBoundary", 2);
    model.component("comp1").multiphysics("asb1").selection().named("sel3");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(10, 11, 17, 20, 24);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(24, 32, 33);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 3);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "2*Td");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "Td/2");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection()
         .set(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 15, 16, 17, 18, 19);
    model.component("comp1").mesh("mesh1").feature("swe1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmax", "Td/2");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("freq")
         .set("plist", "{10, 10.6, 11.2, 11.8, 12.5, 13.2, 14, 15, 16, 17, 18, 19, 20, 21.2, 22.4, 23.6, 25, 26.5, 28, 30, 31.5, 33.5, 35.5, 37.5, 40, 42.5, 45, 47.5, 50, 53, 56, 60, 63, 67, 71, 75, 80, 85, 90, 95, 100, 106, 112, 118, 125, 132, 140, 150, 160, 170, 180, 190, 200, 212, 224, 236, 250, 265, 280, 300, 315, 335, 355, 375, 400, 425, 450, 475, 500, 530, 560, 600, 630, 670, 710, 750, 800, 850, 900, 950, 1e3, 1.06e3, 1.12e3, 1.18e3, 1.25e3, 1.32e3, 1.4e3, 1.5e3, 1.6e3, 1.7e3, 1.8e3, 1.9e3, 2e3, 2.12e3, 2.24e3, 2.36e3, 2.5e3, 2.65e3, 2.8e3, 3e3, 3.15e3, 3.35e3, 3.55e3, 3.75e3, 4e3, 4.25e3, 4.5e3, 4.75e3, 5e3, 5.3e3, 5.6e3, 6e3, 6.3e3, 6.7e3, 7.1e3, 7.5e3, 8e3, 8.5e3, 9e3, 9.5e3, 1e4}");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 121, 0);
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", new String[]{"solid.misesGp_peak"});
    model.result("pg1").feature("vol1").set("threshold", "manual");
    model.result("pg1").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("vol1").set("colortable", "Rainbow");
    model.result("pg1").feature("vol1").set("colortabletrans", "none");
    model.result("pg1").feature("vol1").set("colorscalemode", "linear");
    model.result("pg1").feature("vol1").set("resolution", "custom");
    model.result("pg1").feature("vol1").set("refine", 2);
    model.result("pg1").feature("vol1").set("colortable", "Prism");
    model.result("pg1").feature("vol1").create("def", "Deform");
    model.result("pg1").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 121, 0);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg2").feature("surf1").set("colortable", "Wave");
    model.result("pg2").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").label("\u58f0\u538b (acpr)");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 121, 0);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg3").feature("surf1").set("colortable", "Rainbow");
    model.result("pg3").feature("surf1").set("colorscalemode", "linear");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").label("\u58f0\u538b\u7ea7 (acpr)");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 121, 0);
    model.result("pg4").create("iso1", "Isosurface");
    model.result("pg4").feature("iso1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg4").feature("iso1").set("number", "10");
    model.result("pg4").feature("iso1").set("colortable", "Wave");
    model.result("pg4").feature("iso1").set("colorscalemode", "linearsymmetric");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").label("\u58f0\u538b\uff0c\u7b49\u503c\u9762 (acpr)");
    model.result("pg1").run();
    model.result("pg1").label("\u4f4d\u79fb (solid)");
    model.result("pg1").run();
    model.result("pg1").feature("vol1").set("expr", "solid.disp");
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").create("ann1", "Annotation");
    model.result("pg3").feature("ann1").set("text", "\u8d28\u5fc3");
    model.result("pg3").feature("ann1").set("posxexpr", "CMx");
    model.result("pg3").feature("ann1").set("posyexpr", "CMy");
    model.result("pg3").feature("ann1").set("poszexpr", "CMz");
    model.result("pg3").run();
    model.result("pg3").create("ann2", "Annotation");
    model.result("pg3").feature("ann2").set("text", "\u76d2\u7684\u51e0\u4f55\u4e2d\u5fc3");
    model.result("pg3").feature("ann2").set("posxexpr", "mass1.CMX");
    model.result("pg3").feature("ann2").set("posyexpr", "mass1.CMY");
    model.result("pg3").feature("ann2").set("poszexpr", "mass1.CMZ");
    model.result("pg3").feature("ann2").set("color", "red");
    model.result("pg3").feature("ann2").set("anchorpoint", "lowerleft");
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("iso1").set("number", 20);
    model.result("pg4").run();
    model.result("pg4").set("titletype", "label");
    model.result("pg4").create("sysv1", "CoordSysVolume");
    model.result("pg4").feature("sysv1").set("sys", "sys2");
    model.result("pg4").feature("sysv1").set("arrowxmethod", "coord");
    model.result("pg4").feature("sysv1").set("xcoord", "CMx");
    model.result("pg4").feature("sysv1").set("arrowymethod", "coord");
    model.result("pg4").feature("sysv1").set("ycoord", "CMy");
    model.result("pg4").feature("sysv1").set("arrowzmethod", "coord");
    model.result("pg4").feature("sysv1").set("zcoord", "CMz");
    model.result("pg4").run();

    model.func().create("int1", "Interpolation");
    model.func("int1").set("source", "file");
    model.func("int1").set("filename", "lumped_receiver_vibroacoustic_SPL_data.txt");
    model.func("int1").importData();
    model.func("int1").setEntry("funcnames", "col2", "SPL_data");
    model.func().create("int2", "Interpolation");
    model.func("int2").set("source", "file");
    model.func("int2").set("filename", "lumped_receiver_vibroacoustic_xvel_data_01.txt");
    model.func("int2").importData();
    model.func("int2").setEntry("columnType", "col2", "value");
    model.func("int2").setEntry("funcnames", "col2", "xvel_real_01");
    model.func("int2").setEntry("funcnames", "col3", "xvel_imag_01");
    model.func().create("int3", "Interpolation");
    model.func("int3").set("source", "file");
    model.func("int3").set("filename", "lumped_receiver_vibroacoustic_xvel_data_02.txt");
    model.func("int3").importData();
    model.func("int3").set("funcname", "xvel_dB_02");
    model.func().create("int4", "Interpolation");
    model.func("int4").set("source", "file");
    model.func("int4").set("filename", "lumped_receiver_vibroacoustic_zvel_data_01.txt");
    model.func("int4").importData();
    model.func("int4").setEntry("columnType", "col2", "value");
    model.func("int4").setEntry("funcnames", "col2", "zvel_real_01");
    model.func("int4").setEntry("funcnames", "col3", "zvel_imag_01");
    model.func().create("int5", "Interpolation");
    model.func("int5").set("source", "file");
    model.func("int5").set("filename", "lumped_receiver_vibroacoustic_zvel_data_02.txt");
    model.func("int5").importData();
    model.func("int5").set("funcname", "zvel_dB_02");

    model.result().dataset().create("grid1", "Grid1D");
    model.result().dataset("grid1").set("source", "function");
    model.result().dataset("grid1").set("function", "all");
    model.result().dataset("grid1").set("par1", "f");
    model.result().dataset("grid1").set("parmin1", 100);
    model.result().dataset("grid1").set("parmax1", 10000);
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u9ea6\u514b\u98ce\u4e0a\u7684 SPL");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u58f0\u538b\u7ea7 (dB SPL)");
    model.result("pg5").set("xlog", true);
    model.result("pg5").set("legendpos", "lowerleft");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "20*log10(abs(pmic/V0/acpr.pref_SPL))", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "\u4eff\u771f", 0);
    model.result("pg5").run();
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").set("data", "grid1");
    model.result("pg5").feature("lngr1").set("expr", "SPL_data(f)");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "f[Hz/m]");
    model.result("pg5").feature("lngr1").set("xdatadescractive", true);
    model.result("pg5").feature("lngr1").set("xdatadescr", "freq");
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").feature("lngr1").set("legendmethod", "manual");
    model.result("pg5").feature("lngr1").setIndex("legends", "\u6d4b\u91cf", 0);
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("x \u65b9\u5411\u901f\u5ea6");
    model.result("pg6").set("titletype", "label");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "x \u65b9\u5411\u901f\u5ea6 (dB rel. 1 m/s/V)");
    model.result("pg6").set("xlog", true);
    model.result("pg6").set("legendpos", "lowerright");
    model.result("pg6").create("ptgr1", "PointGraph");
    model.result("pg6").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg6").feature("ptgr1").set("linewidth", "preference");
    model.result("pg6").feature("ptgr1").selection().set(1, 17);
    model.result("pg6").feature("ptgr1").set("expr", "20*log10(abs(vx_local)/V0)");
    model.result("pg6").feature("ptgr1").set("legend", true);
    model.result("pg6").feature("ptgr1").set("legendprefix", "\u70b9");
    model.result("pg6").feature("ptgr1").set("legendsuffix", "\uff0c\u4eff\u771f");
    model.result("pg6").run();
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").feature("lngr1").set("data", "grid1");
    model.result("pg6").feature("lngr1").set("expr", "20*log10(sqrt(xvel_real_01(f)^2+xvel_imag_01(f)^2))");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "f[Hz/m]");
    model.result("pg6").feature("lngr1").set("xdatadescractive", true);
    model.result("pg6").feature("lngr1").set("xdatadescr", "freq");
    model.result("pg6").feature("lngr1").set("legend", true);
    model.result("pg6").feature("lngr1").set("legendmethod", "manual");
    model.result("pg6").feature("lngr1").setIndex("legends", "\u6d4b\u91cf\u503c\uff08\u7cfb\u5217 1\uff09", 0);
    model.result("pg6").feature().duplicate("lngr2", "lngr1");
    model.result("pg6").run();
    model.result("pg6").feature("lngr2").set("expr", "xvel_dB_02(f)");
    model.result("pg6").feature("lngr2").setIndex("legends", "\u6d4b\u91cf\u503c\uff08\u7cfb\u5217 2\uff09", 0);
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("y \u65b9\u5411\u901f\u5ea6");
    model.result("pg7").set("titletype", "label");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "y \u65b9\u5411\u901f\u5ea6 (dB rel. 1 m/s/V)");
    model.result("pg7").set("xlog", true);
    model.result("pg7").create("ptgr1", "PointGraph");
    model.result("pg7").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg7").feature("ptgr1").set("linewidth", "preference");
    model.result("pg7").feature("ptgr1").selection().set(2);
    model.result("pg7").feature("ptgr1").set("expr", "20*log10(abs(vy_local)/V0)");
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("z \u65b9\u5411\u901f\u5ea6");
    model.result("pg8").set("titletype", "label");
    model.result("pg8").set("ylabelactive", true);
    model.result("pg8").set("ylabel", "z \u65b9\u5411\u901f\u5ea6 (dB rel. 1 m/s/V)");
    model.result("pg8").set("xlog", true);
    model.result("pg8").set("legendpos", "lowerright");
    model.result("pg8").create("ptgr1", "PointGraph");
    model.result("pg8").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg8").feature("ptgr1").set("linewidth", "preference");
    model.result("pg8").feature("ptgr1").selection().set(1, 17);
    model.result("pg8").feature("ptgr1").set("expr", "20*log10(abs(vz_local)/V0)");
    model.result("pg8").feature("ptgr1").set("legend", true);
    model.result("pg8").feature("ptgr1").set("legendprefix", "\u70b9");
    model.result("pg8").feature("ptgr1").set("legendsuffix", "\uff0c\u4eff\u771f");
    model.result("pg8").run();
    model.result("pg8").create("lngr1", "LineGraph");
    model.result("pg8").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg8").feature("lngr1").set("linewidth", "preference");
    model.result("pg8").feature("lngr1").set("data", "grid1");
    model.result("pg8").feature("lngr1").set("expr", "20*log10(sqrt(zvel_real_01(f)^2+zvel_imag_01(f)^2))");
    model.result("pg8").feature("lngr1").set("xdata", "expr");
    model.result("pg8").feature("lngr1").set("xdataexpr", "f[Hz/m]");
    model.result("pg8").feature("lngr1").set("xdatadescractive", true);
    model.result("pg8").feature("lngr1").set("xdatadescr", "freq");
    model.result("pg8").feature("lngr1").set("legend", true);
    model.result("pg8").feature("lngr1").set("legendmethod", "manual");
    model.result("pg8").feature("lngr1").setIndex("legends", "\u6d4b\u91cf\u503c\uff08\u7cfb\u5217 1\uff09", 0);
    model.result("pg8").feature().duplicate("lngr2", "lngr1");
    model.result("pg8").run();
    model.result("pg8").feature("lngr2").set("expr", "zvel_dB_02(f)");
    model.result("pg8").feature("lngr2").setIndex("legends", "\u6d4b\u91cf\u503c\uff08\u7cfb\u5217 2\uff09", 0);
    model.result("pg8").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 41, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 81, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 121, 0);
    model.result("pg1").run();
    model.result().duplicate("pg9", "pg1");
    model.result("pg9").run();
    model.result("pg9").setIndex("looplevel", 114, 0);
    model.result("pg9").label("\u7f29\u7565\u56fe");
    model.result("pg9").set("showlegends", false);
    model.result("pg9").set("titletype", "none");
    model.result("pg9").create("vol2", "Volume");
    model.result("pg9").feature("vol2").set("expr", "acpr.Lp_t");
    model.result("pg9").run();
    model.result("pg9").feature("vol1").set("colortable", "AuroraAustralis");
    model.result("pg9").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 41, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 81, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 121, 0);
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 41, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 81, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 121, 0);
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 81, 0);
    model.result("pg4").run();
    model.result("pg1").run();

    model.title("\u542b\u5b8c\u5168\u58f0\u632f\u8026\u5408\u7684\u63a5\u6536\u5668\u96c6\u603b\u6a21\u578b");

    model
         .description("\u5728\u79fb\u52a8\u8bbe\u5907\u3001\u6d88\u8d39\u7535\u5b50\u4ea7\u54c1\u3001\u52a9\u542c\u5668\u6216\u8033\u673a\u7684\u5f00\u53d1\u8fc7\u7a0b\u4e2d\u8fd0\u884c\u4eff\u771f\u65f6\uff0c\u6211\u4eec\u9700\u8981\u8003\u8651\u6362\u80fd\u5668\u5982\u4f55\u4e0e\u7cfb\u7edf\u7684\u5176\u4f59\u90e8\u5206\u8fdb\u884c\u76f8\u4e92\u4f5c\u7528\u3002\u5728\u672c\u4f8b\u4e2d\uff0c\u6211\u4eec\u5c06\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u6362\u80fd\u5668\u7684\u96c6\u603b\u8868\u793a\u6765\u5206\u6790\u9694\u632f\u88c5\u7f6e\u4e0e\u5fae\u578b\u52a9\u542c\u5668\u6362\u80fd\u5668\uff08Knowles TEC-30033 \u5e73\u8861\u7535\u67a2\u63a5\u6536\u5668\uff09\u4e4b\u95f4\u7684\u76f8\u4e92\u4f5c\u7528\u3002\u6211\u4eec\u5c06\u96c6\u603b\u6a21\u578b\u7b80\u5316\u4e3a\u7b49\u6548\u7684\u7535\u58f0\u7535\u8def\uff0c\u7136\u540e\u5c06\u8be5\u6a21\u578b\u7684\u58f0\u632f\u7279\u6027\u4e0e\u9694\u632f\u7cfb\u7edf\u7684\u591a\u7269\u7406\u573a\u6a21\u578b\u8fdb\u884c\u8026\u5408\uff0c\u4ece\u800c\u5b9e\u73b0\u5b8c\u6574\u7684\u7cfb\u7edf\u5206\u6790\u3002\n\n\u6a21\u62df\u7684\u7cfb\u7edf\u8868\u793a\u4e00\u4e2a\u5178\u578b\u7684\u6d4b\u8bd5\u88c5\u7f6e\uff0c\u5176\u4e2d\u5305\u542b\u4e00\u4e2a\u8fde\u63a5\u5230 2\u00a0cc \u8026\u5408\u5668\u7684\u7845\u80f6\u7ba1\uff0c\u8fd9\u662f\u4e00\u4e2a\u7528\u4f5c\u58f0\u8f7d\u8377\u7684\u5e38\u89c1\u8154\u4f53\u3002\n\n\u5728\u63a2\u6d4b\u5230\u96c6\u603b\u7f51\u7edc\u4e2d\u7684\u673a\u68b0\u529b\u540e\uff0c\u6211\u4eec\u5c06\u5176\u4f5c\u4e3a\u521a\u4f53\u8f7d\u8377\u65bd\u52a0\u5230\u63a5\u6536\u5668\u3002\u672c\u4f8b\u5bf9\u63a5\u6536\u5668\u7684\u8f93\u51fa\u58f0\u538b\u548c\u63a2\u6d4b\u5230\u7684\u521a\u4f53\u8fd0\u52a8\u4e0e\u7845\u80f6\u7ba1\u8fde\u63a5\u4ef6\u548c\u58f0\u8026\u5408\u5668\u7684\u6709\u9650\u5143 (FEM) \u6a21\u578b\u8fdb\u884c\u4e86\u8026\u5408\u5206\u6790\u3002\n\n\u6211\u4eec\u5728\u8026\u5408\u5668\u4e2d\u5bf9\u6a21\u62df\u7684\u58f0\u54cd\u5e94\u8fdb\u884c\u4e86\u6d4b\u91cf\uff0c\u5e76\u5728\u6a21\u578b\u4e2d\u83b7\u53d6\u4e86\u632f\u52a8\u7279\u6027\uff0c\u968f\u540e\u5c06\u4eff\u771f\u7ed3\u679c\u4e0e\u6d4b\u91cf\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83\uff0c\u5176\u4e2d\u58f0\u54cd\u5e94\u5728\u8026\u5408\u5668\u9ea6\u514b\u98ce\u4e2d\u6d4b\u5f97\uff0c\u632f\u52a8\u7279\u6027\u901a\u8fc7\u6fc0\u5149\u632f\u52a8\u8ba1\u6d4b\u5f97\u3002\n\n\u8be5\u6a21\u578b\u9700\u8981\u201c\u58f0\u5b66\u6a21\u5757\u201d\u3001\u201c\u7ed3\u6784\u529b\u5b66\u6a21\u5757\u201d\u548c\u201cAC/DC \u6a21\u5757\u201d\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("lumped_receiver_vibroacoustic.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
