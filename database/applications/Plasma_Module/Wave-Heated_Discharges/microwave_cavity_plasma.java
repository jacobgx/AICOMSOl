/*
 * microwave_cavity_plasma.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:08 by COMSOL 6.3.0.290. */
public class microwave_cavity_plasma {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Plasma_Module\\Wave-Heated_Discharges");

    model.param().set("Pin", "500[W]", "\u8f93\u5165\u529f\u7387");
    model.param().set("p0", "25[kPa]", "\u538b\u529b");
    model.param().set("Qs", "400", "\u8d28\u91cf\u6d41");
    model.param().set("f0", "2.45[GHz]", "\u6fc0\u53d1\u9891\u7387");
    model.param().set("R0", "11.4[cm]", "\u8154\u534a\u5f84");
    model.param().set("H0", "38.7[cm]", "\u8154\u9ad8\u5ea6");
    model.param().set("Slw", "1.[cm]", "\u57fa\u677f\u63d0\u5347\u5bbd\u5ea6");
    model.param().set("Slh", "12.2[cm]", "\u57fa\u677f\u63d0\u5347\u9ad8\u5ea6");
    model.param().set("Shw", "2.5[cm]", "\u57fa\u677f\u652f\u67b6\u5bbd\u5ea6");
    model.param().set("Shh", "2[cm]", "\u57fa\u677f\u652f\u67b6\u9ad8\u5ea6");
    model.param().set("Bhw", "R0-Br", "\u949f\u7f69\u652f\u67b6\u5bbd\u5ea6");
    model.param().set("Bhh", "3[cm]", "\u949f\u7f69\u652f\u67b6\u9ad8\u5ea6");
    model.param().set("Br", "7.3[cm]", "\u949f\u7f69\u534a\u5f84");
    model.param().set("Bz", "12.8[cm]", "\u949f\u7f69\u9ad8\u5ea6");
    model.param().set("Bth", "0.7[cm]", "\u949f\u7f69\u539a\u5ea6");
    model.param().set("Cxw", "3[cm]", "\u5916\u90e8\u540c\u8f74\u5bbd\u5ea6");
    model.param().set("Cxh", "15[cm]", "\u5916\u90e8\u540c\u8f74\u9ad8\u5ea6");
    model.param().set("Th", "4[cm]", "\u8fc7\u6e21\u9ad8\u5ea6");
    model.param().set("Tw", "Th", "\u8fc7\u6e21\u5bbd\u5ea6");
    model.param().set("Cxiw", "0.5[cm]", "\u5185\u90e8\u540c\u8f74\u5bbd\u5ea6");
    model.param().set("Cxos", "0.5[cm]", "\u5185\u90e8\u540c\u8f74\u504f\u79fb\u91cf");
    model.param().set("Dw", "6[cm]", "\u91d1\u521a\u77f3\u4fa7");
    model.param().set("Dcut", "1.2[cm]", "\u91d1\u521a\u77f3\u5207\u5272");
    model.param().set("dielh", "6.5[cm]", "\u4ecb\u7535\u5c42\u9ad8\u5ea6");
    model.param().set("dielw", "1.7[cm]", "\u4ecb\u7535\u5c42\u5bbd\u5ea6");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").lengthUnit("cm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"R0", "H0"});
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"Slw", "Slh"});
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"Shw", "Shh"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"0", "Slh"});
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"Shw", "2[mm]"});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new String[]{"0", "Slh+Shh"});
    model.component("comp1").geom("geom1").create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("r5").set("size", new String[]{"Bhw-Bth/2", "Bhh"});
    model.component("comp1").geom("geom1").feature("r5").set("pos", new String[]{"R0-Bhw+Bth/2", "0"});
    model.component("comp1").geom("geom1").create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("ca1").set("center", new String[]{"0", "Bz"});
    model.component("comp1").geom("geom1").feature("ca1").set("r", "Br");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new String[]{"Br", "Bz"});
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"R0-Bhw", "0"});
    model.component("comp1").geom("geom1").create("ca2", "CircularArc");
    model.component("comp1").geom("geom1").feature("ca2").set("center", new String[]{"0", "Bz"});
    model.component("comp1").geom("geom1").feature("ca2").set("r", "Br");
    model.component("comp1").geom("geom1").feature("ca2").set("angle1", 80);
    model.component("comp1").geom("geom1").create("thi1", "Thicken2D");
    model.component("comp1").geom("geom1").feature("thi1").set("totalthick", "Bth");
    model.component("comp1").geom("geom1").feature("thi1").selection("input").set("ca1", "ca2", "ls1");
    model.component("comp1").geom("geom1").create("r6", "Rectangle");
    model.component("comp1").geom("geom1").feature("r6").set("size", new String[]{"Cxw", "Cxh"});
    model.component("comp1").geom("geom1").feature("r6").set("pos", new String[]{"0", "H0"});
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1")
         .set("table", new String[][]{{"Cxw", "H0+Th"}, {"Cxw+Tw", "H0"}, {"Cxw", "H0"}});
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("pol1", "r1", "r6");
    model.component("comp1").geom("geom1").create("r7", "Rectangle");
    model.component("comp1").geom("geom1").feature("r7").set("size", new String[]{"Dw", "Dw"});
    model.component("comp1").geom("geom1").feature("r7").set("base", "center");
    model.component("comp1").geom("geom1").feature("r7").set("pos", new String[]{"0", "H0"});
    model.component("comp1").geom("geom1").feature("r7").set("rot", 45);
    model.component("comp1").geom("geom1").create("cha1", "Chamfer");
    model.component("comp1").geom("geom1").feature("cha1").set("dist", "Dcut");
    model.component("comp1").geom("geom1").feature("cha1").selection("point").set("r7(1)", 1, 2, 3, 4);
    model.component("comp1").geom("geom1").create("r8", "Rectangle");
    model.component("comp1").geom("geom1").feature("r8").set("size", new String[]{"Cxiw", "Cxh"});
    model.component("comp1").geom("geom1").feature("r8").set("pos", new String[]{"0", "H0"});
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("cha1", "r2", "r3", "r5", "r8");
    model.component("comp1").geom("geom1").create("ca3", "CircularArc");
    model.component("comp1").geom("geom1").feature("ca3").set("center", new String[]{"0", "Shh+Slh"});
    model.component("comp1").geom("geom1").feature("ca3").set("r", "3[cm]");
    model.component("comp1").geom("geom1").create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls2").selection("vertex1").set("ca3(1)", 1);
    model.component("comp1").geom("geom1").feature("ls2").selection("vertex2").set("r4(1)", 2);
    model.component("comp1").geom("geom1").create("ls3", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls3").selection("vertex1").set("ls2(1)", 1);
    model.component("comp1").geom("geom1").feature("ls3").selection("vertex2").set("thi1(3)", 2);
    model.component("comp1").geom("geom1").create("cha2", "Chamfer");
    model.component("comp1").geom("geom1").feature("cha2").set("dist", "1[cm]");
    model.component("comp1").geom("geom1").feature("cha2").selection("point").set("dif1(1)", 9);
    model.component("comp1").geom("geom1").create("r9", "Rectangle");
    model.component("comp1").geom("geom1").feature("r9").set("size", new String[]{"dielw", "dielh"});
    model.component("comp1").geom("geom1").feature("r9").set("pos", new String[]{"Slw", "0"});
    model.component("comp1").geom("geom1").create("mce1", "MeshControlEdges");
    model.component("comp1").geom("geom1").feature("mce1").selection("input").set("fin(1)", 4, 22, 23, 26, 38);
    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom("geom1", 2, 1, new String[]{"exterior"});
    model.component("comp1").selection("sel1").set(1);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom("geom1", 1);
    model.component("comp1").selection("sel2").set(2, 13, 14, 15, 17, 18, 19, 24, 32, 34);
    model.component("comp1").selection("sel1").label("\u58c1");
    model.component("comp1").selection("sel2").label("\u4e2d\u6027\u58c1");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("r0", "0[cm]", "\u6700\u5927 r \u5750\u6807");
    model.component("comp1").variable("var1").set("z0", "14[cm]", "\u6700\u5927 z \u5750\u6807");
    model.component("comp1").variable("var1")
         .set("prf", "exp(-((r0-r)/4[cm])^2 -((z0-z)/2[cm])^2)", "\u521d\u59cb\u6e29\u5ea6\u66f2\u7ebf");
    model.component("comp1").variable("var1").set("Tinit", "2500[K]*prf+1200[K]", "\u521d\u59cb\u6e29\u5ea6");

    model.view().create("view2", 2);
    model.view().create("view3", 3);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material().create("mat2", "Common");
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
    model.component("comp1").material("mat2").selection().set(2, 4, 5, 6);
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");

    model.component("comp1").physics().create("plas", "ColdPlasma", "geom1");
    model.component("comp1").physics("plas").selection().set(1);
    model.component("comp1").physics("plas").create("rg1", "ReactionGroup", 2);
    model.component("comp1").physics("plas").create("rg2", "ReactionGroup", 2);
    model.component("comp1").physics("plas").create("rg3", "ReactionGroup", 2);
    model.component("comp1").physics("plas").create("e", "Species", 2);
    model.component("comp1").physics("plas").create("H", "Species", 2);
    model.component("comp1").physics("plas").create("Hn2", "Species", 2);
    model.component("comp1").physics("plas").create("Hn3", "Species", 2);
    model.component("comp1").physics("plas").create("H_1p", "Species", 2);
    model.component("comp1").physics("plas").create("H2", "Species", 2);
    model.component("comp1").physics("plas").create("H2_1p", "Species", 2);
    model.component("comp1").physics("plas").create("H3_1p", "Species", 2);
    model.component("comp1").physics("plas").create("sr1", "SurfaceReaction", 1);
    model.component("comp1").physics("plas").feature("sr1").selection().named("sel2");
    model.component("comp1").physics("plas").create("sr2", "SurfaceReaction", 1);
    model.component("comp1").physics("plas").feature("sr2").selection().named("sel2");
    model.component("comp1").physics("plas").create("sr3", "SurfaceReaction", 1);
    model.component("comp1").physics("plas").feature("sr3").selection().named("sel2");
    model.component("comp1").physics("plas").create("sr4", "SurfaceReaction", 1);
    model.component("comp1").physics("plas").feature("sr4").selection().named("sel1");
    model.component("comp1").physics("plas").create("sr5", "SurfaceReaction", 1);
    model.component("comp1").physics("plas").feature("sr5").selection().named("sel1");
    model.component("comp1").physics("plas").create("sr6", "SurfaceReaction", 1);
    model.component("comp1").physics("plas").feature("sr6").selection().named("sel1");
    model.component("comp1").physics("plas").create("out1", "Outflow", 1);
    model.component("comp1").physics("plas").feature("out1").selection().set(20);
    model.component("comp1").physics("plas").create("wall1", "WallDriftDiffusion", 1);
    model.component("comp1").physics("plas").feature("wall1").selection().named("sel1");
    model.component("comp1").physics("plas").create("gnd1", "Ground", 1);
    model.component("comp1").physics("plas").feature("gnd1").selection()
         .set(2, 13, 14, 15, 17, 18, 19, 20, 24, 32, 34);
    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics("spf").selection().set(1);
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(32);
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().set(20);
    model.component("comp1").physics().create("ht", "HeatTransferInFluids", "geom1");
    model.component("comp1").physics("ht").selection().set(1);
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp1").selection().set(2, 15, 17, 18);
    model.component("comp1").physics("ht").create("temp2", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp2").selection().set(13, 14, 19);
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf1").selection().set(24, 32, 34);
    model.component("comp1").physics("ht").create("ofl1", "ConvectiveOutflow", 1);
    model.component("comp1").physics("ht").feature("ofl1").selection().set(20);
    model.component("comp1").physics().create("emw", "ElectromagneticWaves", "geom1");
    model.component("comp1").physics("emw").create("port1", "Port", 1);
    model.component("comp1").physics("emw").feature("port1").selection().set(8);

    model.component("comp1").multiphysics().create("nipf1", "NonIsothermalPlasmaFlowMultiphysicsCoupling", 2);
    model.component("comp1").multiphysics().create("pcc1", "PlasmaConductivityMultiphysicsCoupling", 2);
    model.component("comp1").multiphysics().create("ehs1", "ElectronHeatSourceMultiphysicsCoupling", 2);

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(32);
    model.component("comp1").mesh("mesh1").feature("edg1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size3", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size4", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size5", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(2, 3, 5, 6);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().set(7);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").selection().set(8);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size4").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size4").selection().set(9);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size5").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size5").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set(1, 7, 8, 9);
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection()
         .set(13, 14, 15, 17, 18, 19, 24, 34);

    model.component("comp1").view("view1").axis().set("xmin", -27.872474670410156);
    model.component("comp1").view("view1").axis().set("xmax", 39.27247619628906);
    model.component("comp1").view("view1").axis().set("ymin", -1.5878620147705078);
    model.component("comp1").view("view1").axis().set("ymax", 55.287864685058594);
    model.view("view2").axis().set("xmin", -35.13884353637695);
    model.view("view2").axis().set("xmax", 35.13884353637695);
    model.view("view2").axis().set("ymin", -2.6850013732910156);
    model.view("view2").axis().set("ymax", 56.38500213623047);

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
    model.component("comp1").material("mat2").label("Glass (quartz)");
    model.component("comp1").material("mat2").set("family", "custom");
    model.component("comp1").material("mat2").set("diffuse", "custom");
    model.component("comp1").material("mat2").set("ambient", "custom");
    model.component("comp1").material("mat2").set("noise", true);
    model.component("comp1").material("mat2").set("fresnel", 0.99);
    model.component("comp1").material("mat2").set("roughness", 0.02);
    model.component("comp1").material("mat2").set("diffusewrap", 0);
    model.component("comp1").material("mat2").set("reflectance", 0);
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-14[S/m]", "0", "0", "0", "1e-14[S/m]", "0", "0", "0", "1e-14[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"4.2", "0", "0", "0", "4.2", "0", "0", "0", "4.2"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "2210[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.4[W/(m*K)]", "0", "0", "0", "1.4[W/(m*K)]", "0", "0", "0", "1.4[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "730[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1.5", "0", "0", "0", "1.5", "0", "0", "0", "1.5"});

    model.component("comp1").physics("plas").prop("TransportSettings").set("calcThermo", true);
    model.component("comp1").physics("plas").prop("TransportSettings").set("fullDiffusivity", true);
    model.component("comp1").physics("plas").prop("TransportSettings").set("MixtureDiffusionCorrection", true);
    model.component("comp1").physics("plas").prop("TransportSettings").set("Convection", true);
    model.component("comp1").physics("plas").prop("ElectronProperties").set("ReducedProps", true);
    model.component("comp1").physics("plas").prop("EEDFSettings").set("g", 1.2);
    model.component("comp1").physics("plas").prop("EEDFSettings").set("eedf", "Generalized");
    model.component("comp1").physics("plas").feature("rg1").set("reactionGroupType", "ElectronImpactReactions");
    model.component("comp1").physics("plas").feature("rg1")
         .set("Species", new String[][]{{"e"}, {"H"}, {"H_1p"}, {"Hn2"}, {"Hn3"}});
    model.component("comp1").physics("plas").feature("rg1")
         .set("fwdSpecies", new String[][]{{"e+H"}, {"e+H"}, {"e+H"}, {"e+H"}, {"e+H"}, {"e+H"}, {"e+H"}, {"e+H+"}, {"e+H+"}});
    model.component("comp1").physics("plas").feature("rg1")
         .set("revSpecies", new String[][]{{"e+H"}, {"e+Hn2"}, {"e+Hn2"}, {"e+Hn3"}, {"e+H"}, {"e+H"}, {"2e+H+"}, {"Hn2"}, {"Hn3"}});
    model.component("comp1").physics("plas").feature("rg1")
         .set("rsSequenceNo", new int[][]{{1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {9}});
    model.component("comp1").physics("plas").feature("rg1")
         .set("Af", new String[][]{{"0"}, {"0"}, {"0"}, {"0"}, {"0"}, {"0"}, {"0"}, {"3e-14[cm^3/s]*(plas.Te/1[V])^(-0.5)*N_A_const"}, {"3e-14[cm^3/s]*(plas.Te/1[V])^(-0.5)*N_A_const"}});
    model.component("comp1").physics("plas").feature("rg1")
         .set("nf", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("rg1")
         .set("Ef", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("rg1")
         .set("Ar", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("rg1")
         .set("nr", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("plas").feature("rg1")
         .set("Er", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("rg1")
         .set("xdata_crossSection", new String[][]{{}, 
         {"0.0", "1.0E-4", "0.001", "0.01", "0.05", "0.1", "0.3", "0.582", "1.207", "2.171", 
         "3.423", "4.889", "6.691", "8.704", "12.0", "20.0", "30.0", "50.0", "100.0", "300.0", 
         "400.0", "500.0", "680.0", "1000.0", "2000.0", "5000.0", "7000.0", "10000.0"}, 
         {"10.20427", "10.5", "11.0", "11.5", "12.0", "13.0", "14.0", "15.0", "16.0", "17.0", 
         "18.0", "19.0", "20.0", "21.0", "22.0", "23.0", "24.0", "25.0", "26.0", "27.0", 
         "28.0", "29.0", "30.0", "35.0", "40.0", "45.0", "50.0", "55.0", "60.0", "65.0", 
         "70.0", "75.0", "80.0", "85.0", "90.0", "95.0", "100.0", "150.0", "200.0", "250.0", 
         "300.0", "350.0", "400.0", "450.0", "500.0", "600.0", "700.0", "800.0", "900.0", "1000.0", 
         "2000.0", "4000.0", "6000.0", "8000.0", "10000.0"}, 
         {"10.20427", "11.6", "20.0", "30.0", "40.0", "54.4", "100.0", "200.0", "300.0", "500.0", 
         "700.0", "1000.0", "2000.0", "4000.0", "6000.0", "8000.0", "10000.0"}, 
         {"12.09395", "12.1", "13.0", "14.0", "15.0", "16.0", "17.0", "18.0", "19.0", "20.0", 
         "21.0", "22.0", "23.0", "24.0", "25.0", "26.0", "27.0", "28.0", "29.0", "30.0", 
         "35.0", "40.0", "45.0", "50.0", "55.0", "60.0", "65.0", "70.0", "75.0", "80.0", 
         "85.0", "90.0", "95.0", "100.0", "150.0", "200.0", "250.0", "300.0", "350.0", "400.0", 
         "450.0", "500.0", "600.0", "700.0", "800.0", "900.0", "1000.0", "2000.0", "4000.0", "6000.0", 
         "8000.0", "10000.0"}, 
         {"12.75534", "12.7554", "13.0", "14.0", "15.0", "16.0", "17.0", "18.0", "19.0", "20.0", 
         "21.0", "22.0", "23.0", "24.0", "25.0", "26.0", "27.0", "28.0", "29.0", "30.0", 
         "35.0", "40.0", "45.0", "50.0", "55.0", "60.0", "65.0", "70.0", "75.0", "80.0", 
         "85.0", "90.0", "95.0", "100.0", "150.0", "200.0", "250.0", "300.0", "350.0", "400.0", 
         "450.0", "500.0", "600.0", "700.0", "800.0", "900.0", "1000.0", "2000.0", "4000.0", "6000.0", 
         "8000.0", "10000.0"}, 
         {"13.06146", "13.1", "14.0", "15.0", "16.0", "17.0", "18.0", "19.0", "20.0", "21.0", 
         "22.0", "23.0", "24.0", "25.0", "26.0", "27.0", "28.0", "29.0", "30.0", "35.0", 
         "40.0", "45.0", "50.0", "55.0", "60.0", "65.0", "70.0", "75.0", "80.0", "85.0", 
         "90.0", "95.0", "100.0", "150.0", "200.0", "250.0", "300.0", "350.0", "400.0", "450.0", 
         "500.0", "600.0", "700.0", "800.0", "900.0", "1000.0", "2000.0", "4000.0", "6000.0", "8000.0", 
         "10000.0"}, 
         {"13.606", "14.6", "15.4", "16.4", "17.4", "18.4", "19.6", "21.4", "24.0", "27.3", 
         "31.6", "36.7", "42.9", "50.7", "60.1", "72.1", "89.0", "113.0", "148.2", "188.2", 
         "248.2", "347.9", "508.2", "748.2", "1100.0", "1662.7", "2448.1", "3998.1", "6000.0", "10000.0"}});
    model.component("comp1").physics("plas").feature("rg1")
         .set("ydata_crossSection", new String[][]{{}, 
         {"4.479E-19", "4.5107E-19", "4.5666E-19", "4.66E-19", "4.6014E-19", "4.4246E-19", "3.7413E-19", "2.972E-19", "2.4024E-19", "1.8717E-19", 
         "1.421E-19", "1.0579E-19", "7.6309E-20", "5.5808E-20", "3.3502E-20", "1.3779E-20", "6.8407E-21", "3.0097E-21", "6.939E-22", "9.14E-23", 
         "5.43E-23", "3.63E-23", "2.1E-23", "9.6948E-24", "2.6137E-24", "4.6205E-25", "2.4453E-25", "1.2457E-25"}, 
         {"0.0", "3.042749E-21", "3.482992E-21", "3.876862E-21", "4.231282E-21", "4.842746E-21", "5.350268E-21", "5.776445E-21", "6.137485E-21", "6.445402E-21", 
         "6.709352E-21", "6.936461E-21", "7.132383E-21", "7.301673E-21", "7.44805E-21", "7.574584E-21", "7.683838E-21", "7.777968E-21", "7.858803E-21", "7.927904E-21", 
         "7.986616E-21", "8.036098E-21", "8.077359E-21", "8.187667E-21", "8.188161E-21", "8.122753E-21", "8.017319E-21", "7.887782E-21", "7.744238E-21", "7.593232E-21", 
         "7.439063E-21", "7.284577E-21", "7.131657E-21", "6.981537E-21", "6.83501E-21", "6.692564E-21", "6.554477E-21", "5.410667E-21", "4.605682E-21", "4.017239E-21", 
         "3.569327E-21", "3.216823E-21", "2.931879E-21", "2.696501E-21", "2.498579E-21", "2.183704E-21", "1.943818E-21", "1.754534E-21", "1.601082E-21", "1.473974E-21", 
         "8.414795E-22", "4.703983E-22", "3.324452E-22", "2.592416E-22", "2.13498E-22"}, 
         {"0.0", "1.43277E-21", "9.8448E-22", "8.9658E-22", "7.7352E-22", "7.3836E-22", "5.9772E-22", "4.0434E-22", "2.886033E-22", "1.75599E-22", 
         "1.261739E-22", "8.871339E-23", "4.458517E-23", "2.23497E-23", "1.491249E-23", "1.118913E-23", "8.953589E-24"}, 
         {"0.0", "3.46371E-22", "4.235485E-22", "4.933579E-22", "5.510671E-22", "5.99732E-22", "6.413837E-22", "6.774425E-22", "7.089417E-22", "7.366565E-22", 
         "7.611841E-22", "7.829946E-22", "8.024642E-22", "8.198994E-22", "8.355526E-22", "8.496346E-22", "8.623228E-22", "8.737684E-22", "8.841008E-22", "8.934317E-22", 
         "9.280792E-22", "9.482561E-22", "9.588836E-22", "9.629683E-22", "9.624568E-22", "9.586636E-22", "9.525055E-22", "9.446375E-22", "9.355368E-22", "9.255567E-22", 
         "9.149616E-22", "9.039515E-22", "8.926789E-22", "8.812604E-22", "7.714062E-22", "6.805466E-22", "6.080056E-22", "5.495533E-22", "5.016701E-22", "4.617922E-22", 
         "4.280828E-22", "3.992124E-22", "3.523204E-22", "3.158274E-22", "2.865797E-22", "2.625846E-22", "2.425223E-22", "1.403588E-22", "7.885834E-23", "5.577387E-23", 
         "4.349062E-23", "3.580706E-23"}, 
         {"0.0", "1.015926E-22", "1.077878E-22", "1.292796E-22", "1.467872E-22", "1.614319E-22", "1.73919E-22", "1.847217E-22", "1.941732E-22", "2.025171E-22", 
         "2.099371E-22", "2.165753E-22", "2.225436E-22", "2.279322E-22", "2.328145E-22", "2.372512E-22", "2.41293E-22", "2.449828E-22", "2.483573E-22", "2.514477E-22", 
         "2.634798E-22", "2.713476E-22", "2.76409E-22", "2.79495E-22", "2.811474E-22", "2.817368E-22", "2.815255E-22", "2.807047E-22", "2.794164E-22", "2.777683E-22", 
         "2.758432E-22", "2.737055E-22", "2.714057E-22", "2.689839E-22", "2.427771E-22", "2.186139E-22", "1.98188E-22", "1.811054E-22", "1.667342E-22", "1.545223E-22", 
         "1.440349E-22", "1.349374E-22", "1.199426E-22", "1.080929E-22", "9.848643E-23", "9.053486E-23", "8.383917E-23", "4.911523E-23", "2.775665E-23", "1.966483E-23", 
         "1.534505E-23", "1.263851E-23"}, 
         {"0.0", "4.362161E-23", "5.197215E-23", "5.95587E-23", "6.587363E-23", "7.124446E-23", "7.588633E-23", "7.994836E-23", "8.353819E-23", "8.673614E-23", 
         "8.960376E-23", "9.21893E-23", "9.453127E-23", "9.66609E-23", "9.860392E-23", "1.003817E-22", "1.020124E-22", "1.035111E-22", "1.048912E-22", "1.103604E-22", 
         "1.140814E-22", "1.166154E-22", "1.183054E-22", "1.193753E-22", "1.199788E-22", "1.202253E-22", "1.20195E-22", "1.199478E-22", "1.195297E-22", "1.189764E-22", 
         "1.183159E-22", "1.175703E-22", "1.167575E-22", "1.071174E-22", "9.756282E-23", "8.919318E-23", "8.203148E-23", "7.590701E-23", "7.063753E-23", "6.60674E-23", 
         "6.207119E-23", "5.542315E-23", "5.011821E-23", "4.578583E-23", "4.217914E-23", "3.912807E-23", "2.311294E-23", "1.311778E-23", "9.306236E-24", "7.266545E-24", 
         "5.986993E-24"}, 
         {"0.0", "5.44E-22", "9.9E-22", "1.45E-21", "1.96E-21", "2.35E-21", "2.81E-21", "3.39E-21", "4.15E-21", "4.75E-21", 
         "5.27E-21", "5.74E-21", "6.02E-21", "6.27E-21", "6.13E-21", "6.01E-21", "5.78E-21", "5.23E-21", "4.62E-21", "4.1E-21", 
         "3.43E-21", "2.66E-21", "2.0E-21", "1.47E-21", "1.05E-21", "7.21E-22", "5.25E-22", "3.39E-22", "2.42E-22", "1.57E-22"}});
    model.component("comp1").physics("plas").feature("rg1")
         .set("collType", new String[][]{{"Elastic"}, {"Excitation"}, {"Excitation"}, {"Excitation"}, {"Excitation"}, {"Excitation"}, {"Ionization"}, {"Excitation"}, {"Excitation"}});
    model.component("comp1").physics("plas").feature("rg1")
         .set("mratio", new String[][]{{"5.486900e-4"}, {"0"}, {"0"}, {"0"}, {"0"}, {"0"}, {"0"}, {"0"}, {"0"}});
    model.component("comp1").physics("plas").feature("rg1")
         .set("de", new String[][]{{"0"}, {"10.2043"}, {"10.2043"}, {"12.0939"}, {"1.275530e+1"}, {"1.306150e+1"}, {"1.360570e+1"}, {"0"}, {"0"}});
    model.component("comp1").physics("plas").feature("rg1")
         .set("SpecifyReactionUsing", new String[][]{{"UseCrossSectionData"}, {"UseCrossSectionData"}, {"UseCrossSectionData"}, {"UseCrossSectionData"}, {"UseCrossSectionData"}, {"UseCrossSectionData"}, {"UseCrossSectionData"}, {"arrheniusparameters"}, {"arrheniusparameters"}});
    model.component("comp1").physics("plas").feature("rg1").set("Filepath", "H2_plasma_chemistry_import_H.txt");
    model.component("comp1").physics("plas").feature("rg1").label("H - Electron Impact Reactions");
    model.component("comp1").physics("plas").feature("rg2").set("reactionGroupType", "ElectronImpactReactions");
    model.component("comp1").physics("plas").feature("rg2")
         .set("Species", new String[][]{{"e"}, {"H"}, {"H2"}, {"H2_1p"}, {"H3_1p"}, {"H_1p"}, {"Hn2"}, {"Hn3"}});
    model.component("comp1").physics("plas").feature("rg2")
         .set("fwdSpecies", new String[][]{{"e+H2"}, 
         {"e+H2"}, 
         {"e+H2"}, 
         {"e+H2"}, 
         {"e+H2"}, 
         {"e+H2"}, 
         {"e+H2"}, 
         {"e+H2"}, 
         {"e+H2"}, 
         {"e+H2"}, 
         {"e+H2"}, 
         {"e+H2"}, 
         {"e+H2"}, 
         {"e+H2"}, 
         {"e+H2"}, 
         {"e+H2"}, 
         {"e+H2"}, 
         {"e+H2"}, 
         {"e+H2"}, 
         {"e+H2"}, 
         {"e+H2"}, 
         {"e+H2"}, 
         {"e+H3+"}, 
         {"e+H3+"}, 
         {"e+H2+"}, 
         {"e+H2+"}});
    model.component("comp1").physics("plas").feature("rg2")
         .set("revSpecies", new String[][]{{"e+H2"}, 
         {"e+H2"}, 
         {"e+H2"}, 
         {"e+H2"}, 
         {"e+H+H"}, 
         {"e+H+H"}, 
         {"e+H+H"}, 
         {"e+H+H"}, 
         {"e+H2"}, 
         {"e+H2"}, 
         {"e+H2"}, 
         {"e+H2"}, 
         {"e+H2"}, 
         {"e+H2"}, 
         {"e+H2"}, 
         {"e+H+Hn2"}, 
         {"e+H+Hn2"}, 
         {"e+H+Hn3"}, 
         {"e+H+H"}, 
         {"e+H+H"}, 
         {"2e+H2+"}, 
         {"2e+H+H+"}, 
         {"3H"}, 
         {"H2+Hn2"}, 
         {"H+Hn2"}, 
         {"H+Hn3"}});
    model.component("comp1").physics("plas").feature("rg2")
         .set("rsSequenceNo", new int[][]{{1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {9}, {10}, {11}, {12}, {13}, {14}, {15}, {16}, {17}, {18}, {19}, {20}, {21}, {22}, {23}, {24}, {25}, {26}});
    model.component("comp1").physics("plas").feature("rg2")
         .set("Af", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("rg2")
         .set("nf", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("rg2")
         .set("Ef", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("rg2")
         .set("Ar", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("rg2")
         .set("nr", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("rg2")
         .set("Er", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("rg2")
         .set("xdata_crossSection", new String[][]{{}, 
         {"0.0", "0.02", "0.04", "0.06", "0.08", "0.1", "0.2", "0.3", "0.4", "0.5", 
         "0.6", "0.7", "0.8", "0.9", "1.0", "1.2", "1.4", "1.5", "1.6", "1.8", 
         "2.0", "2.5", "3.0", "3.5", "4.0", "5.0", "6.0", "7.0", "8.0", "9.0", 
         "10.0", "12.0", "15.0", "20.0", "30.0", "40.0", "50.0", "60.0", "80.0", "100.0", 
         "150.0", "200.0", "300.0", "500.0", "700.0", "1000.0", "1500.0", "3000.0", "5000.0", "7000.0", 
         "10000.0"}, 
         {"0.516", "0.7", "1.0", "1.5", "2.0", "2.5", "3.0", "3.3", "4.0", "6.0", 
         "8.0", "10.0", "15.0", "20.0", "25.0", "30.0", "40.0", "50.0", "60.0", "80.0", 
         "100.0", "200.0", "300.0", "1000.0", "1500.0", "2000.0", "3000.0", "5000.0", "7000.0", "10000.0"}, 
         {"1.0", "1.3", "1.5", "1.8", "2.15", "2.3", "2.5", "3.0", "3.6", "4.0", 
         "6.0", "9.0", "12.0", "16.0", "20.0", "50.0", "10000.0"}, 
         {"1.5", "1.8", "1.9", "2.0", "2.2", "2.5", "3.0", "3.3", "3.7", "5.0", 
         "7.0", "10.0", "12.0", "14.0", "16.0", "50.0", "10000.0"}, 
         {"7.93", "8.9", "9.2", "9.4", "9.6", "9.8", "10.0", "10.2", "10.4", "10.6", 
         "10.8", "11.0", "11.2", "11.4", "11.6", "11.8", "12.0", "12.2", "12.4", "12.6", 
         "12.8", "13.0", "13.2", "13.4", "13.6", "13.8", "14.0", "14.2", "14.4", "14.6", 
         "14.8", "15.0", "15.2", "15.4", "15.6", "15.8", "16.0", "16.2", "16.4", "16.6", 
         "16.8", "17.0", "17.2", "17.4", "17.6", "17.8", "18.0", "18.2", "18.4", "18.6", 
         "18.8", "19.0", "19.2", "19.4", "19.6", "19.8", "20.0", "21.0", "22.0", "23.0", 
         "24.0", "25.0", "26.0", "27.0", "28.0", "29.0", "30.0", "31.0", "32.0", "33.0", 
         "34.0", "35.0", "36.0", "37.0", "38.0", "39.0", "40.0", "50.0", "60.0", "70.0", 
         "80.0", "90.0", "100.0", "200.0", "300.0", "400.0", "500.0", "600.0", "700.0", "800.0", 
         "900.0", "1000.0", "1500.0", "10000.0"}, 
         {"11.4", "11.6", "11.8", "12.0", "12.2", "12.4", "12.6", "12.8", "13.0", "13.2", 
         "13.4", "13.6", "13.8", "14.0", "14.2", "14.4", "14.6", "14.8", "15.0", "15.2", 
         "15.4", "15.6", "15.8", "16.0", "16.2", "16.4", "16.6", "16.8", "17.0", "17.2", 
         "17.4", "17.6", "17.8", "18.0", "18.2", "18.4", "18.6", "18.8", "19.0", "19.2", 
         "19.4", "19.6", "19.8", "20.0", "21.0", "22.0", "23.0", "24.0", "25.0", "26.0", 
         "27.0", "28.0", "29.0", "30.0", "31.0", "32.0", "33.0", "34.0", "35.0", "36.0", 
         "37.0", "38.0", "39.0", "40.0", "50.0", "60.0", "70.0", "80.0", "90.0", "100.0", 
         "200.0", "300.0", "500.0", "700.0", "1000.0", "1500.0", "2000.0", "3000.0", "5000.0", "7000.0", 
         "10000.0"}, 
         {"11.72", "12.0", "12.2", "12.4", "12.6", "12.8", "13.0", "13.2", "13.4", "13.6", 
         "13.8", "14.0", "14.2", "14.4", "14.6", "14.8", "15.0", "15.2", "15.4", "15.6", 
         "15.8", "16.0", "16.2", "16.4", "16.6", "16.8", "17.0", "17.2", "17.4", "17.6", 
         "17.8", "18.0", "18.2", "18.4", "18.6", "18.8", "19.0", "19.2", "19.4", "19.6", 
         "19.8", "20.0", "21.0", "22.0", "23.0", "24.0", "25.0", "26.0", "27.0", "28.0", 
         "29.0", "30.0", "31.0", "32.0", "33.0", "34.0", "35.0", "36.0", "37.0", "38.0", 
         "39.0", "40.0", "50.0", "60.0", "70.0", "80.0", "90.0", "100.0", "200.0", "300.0", 
         "400.0", "500.0", "600.0", "700.0", "800.0", "900.0", "1000.0", "1500.0", "10000.0"}, 
         {"11.72", "12.0", "12.2", "12.4", "12.6", "12.8", "13.0", "13.2", "13.4", "13.6", 
         "13.8", "14.0", "14.2", "14.4", "14.6", "14.8", "15.0", "15.2", "15.4", "15.6", 
         "15.8", "16.0", "16.2", "16.4", "16.6", "16.8", "17.0", "17.2", "17.4", "17.6", 
         "17.8", "18.0", "18.2", "18.4", "18.6", "18.8", "19.0", "19.2", "19.4", "19.6", 
         "19.8", "20.0", "21.0", "22.0", "23.0", "24.0", "25.0", "26.0", "27.0", "28.0", 
         "29.0", "30.0", "31.0", "32.0", "33.0", "34.0", "35.0", "36.0", "37.0", "38.0", 
         "39.0", "40.0", "50.0", "60.0", "70.0", "80.0", "90.0", "100.0", "200.0", "300.0", 
         "400.0", "500.0", "600.0", "700.0", "800.0", "900.0", "1000.0", "1500.0", "10000.0"}, 
         {"12.4", "12.6", "12.8", "13.0", "13.2", "13.4", "13.6", "13.8", "14.0", "14.2", 
         "14.4", "14.6", "14.8", "15.0", "15.2", "15.4", "15.6", "15.8", "16.0", "16.2", 
         "16.4", "16.6", "16.8", "17.0", "17.2", "17.4", "17.6", "17.8", "18.0", "18.2", 
         "18.4", "18.6", "18.8", "19.0", "19.2", "19.4", "19.6", "19.8", "20.0", "21.0", 
         "22.0", "23.0", "24.0", "25.0", "26.0", "27.0", "28.0", "29.0", "30.0", "31.0", 
         "32.0", "33.0", "34.0", "35.0", "36.0", "37.0", "38.0", "39.0", "40.0", "50.0", 
         "60.0", "70.0", "80.0", "90.0", "100.0", "200.0", "300.0", "500.0", "700.0", "1000.0", 
         "1500.0", "2000.0", "3000.0", "5000.0", "7000.0", "10000.0"}, 
         {"12.4", "12.6", "12.8", "13.0", "13.2", "13.4", "13.6", "13.8", "14.0", "14.2", 
         "14.4", "14.6", "14.8", "15.0", "15.2", "15.4", "15.6", "15.8", "16.0", "16.2", 
         "16.4", "16.6", "16.8", "17.0", "17.2", "17.4", "17.6", "17.8", "18.0", "18.2", 
         "18.4", "18.6", "18.8", "19.0", "19.2", "19.4", "19.6", "19.8", "20.0", "21.0", 
         "22.0", "23.0", "24.0", "25.0", "26.0", "27.0", "28.0", "29.0", "30.0", "31.0", 
         "32.0", "33.0", "34.0", "35.0", "36.0", "37.0", "38.0", "39.0", "40.0", "50.0", 
         "60.0", "70.0", "80.0", "90.0", "100.0", "200.0", "300.0", "500.0", "700.0", "1000.0", 
         "1500.0", "2000.0", "3000.0", "5000.0", "7000.0", "10000.0"}, 
         {"13.0", "13.6", "13.8", "14.0", "14.2", "14.4", "14.6", "14.8", "15.0", "15.2", 
         "15.4", "15.6", "15.8", "16.0", "16.2", "16.4", "16.6", "16.8", "17.0", "17.2", 
         "17.4", "17.6", "17.8", "18.0", "18.2", "18.4", "18.6", "18.8", "19.0", "19.2", 
         "19.4", "19.6", "19.8", "20.0", "21.0", "22.0", "23.0", "24.0", "25.0", "26.0", 
         "27.0", "28.0", "29.0", "30.0", "31.0", "32.0", "33.0", "34.0", "35.0", "36.0", 
         "37.0", "38.0", "39.0", "40.0", "50.0", "60.0", "70.0", "80.0", "90.0", "100.0", 
         "200.0", "300.0", "400.0", "500.0", "600.0", "700.0", "800.0", "900.0", "1000.0", "1500.0", 
         "10000.0"}, 
         {"13.8", "14.0", "14.2", "14.4", "14.6", "14.8", "15.0", "15.2", "15.4", "15.6", 
         "15.8", "16.0", "16.2", "16.4", "16.6", "16.8", "17.0", "17.2", "17.4", "17.6", 
         "17.8", "18.0", "18.2", "18.4", "18.6", "18.8", "19.0", "19.2", "19.4", "19.6", 
         "19.8", "20.0", "21.0", "22.0", "23.0", "24.0", "25.0", "26.0", "27.0", "28.0", 
         "29.0", "30.0", "31.0", "32.0", "33.0", "34.0", "35.0", "36.0", "37.0", "38.0", 
         "39.0", "40.0", "50.0", "60.0", "70.0", "80.0", "90.0", "100.0", "200.0", "300.0", 
         "500.0", "700.0", "1000.0", "1500.0", "2000.0", "3000.0", "5000.0", "7000.0", "10000.0"}, 
         {"14.0", "14.2", "14.4", "14.6", "14.8", "15.0", "15.2", "15.4", "15.6", "15.8", 
         "16.0", "16.2", "16.4", "16.6", "16.8", "17.0", "17.2", "17.4", "17.6", "17.8", 
         "18.0", "18.2", "18.4", "18.6", "18.8", "19.0", "19.2", "19.4", "19.6", "19.8", 
         "20.0", "21.0", "22.0", "23.0", "24.0", "25.0", "26.0", "27.0", "28.0", "29.0", 
         "30.0", "31.0", "32.0", "33.0", "34.0", "35.0", "36.0", "37.0", "38.0", "39.0", 
         "40.0", "50.0", "60.0", "70.0", "80.0", "90.0", "100.0", "200.0", "300.0", "500.0", 
         "700.0", "1000.0", "1500.0", "2000.0", "3000.0", "5000.0", "7000.0", "10000.0"}, 
         {"14.6", "14.8", "15.0", "15.2", "15.4", "15.6", "15.8", "16.0", "16.2", "16.4", 
         "16.6", "16.8", "17.0", "17.2", "17.4", "17.6", "17.8", "18.0", "18.2", "18.4", 
         "18.6", "18.8", "19.0", "19.2", "19.4", "19.6", "19.8", "20.0", "21.0", "22.0", 
         "23.0", "24.0", "25.0", "26.0", "27.0", "28.0", "29.0", "30.0", "31.0", "32.0", 
         "33.0", "34.0", "35.0", "36.0", "37.0", "38.0", "39.0", "40.0", "50.0", "60.0", 
         "70.0", "80.0", "90.0", "100.0", "200.0", "300.0", "500.0", "700.0", "1000.0", "1500.0", 
         "2000.0", "3000.0", "5000.0", "7000.0", "10000.0"}, 
         {"14.6", "14.8", "15.0", "15.2", "15.4", "15.6", "15.8", "16.0", "16.2", "16.4", 
         "16.6", "16.8", "17.0", "17.2", "17.4", "17.6", "17.8", "18.0", "18.2", "18.4", 
         "18.6", "18.8", "19.0", "19.2", "19.4", "19.6", "19.8", "20.0", "21.0", "22.0", 
         "23.0", "24.0", "25.0", "26.0", "27.0", "28.0", "29.0", "30.0", "31.0", "32.0", 
         "33.0", "34.0", "35.0", "36.0", "37.0", "38.0", "39.0", "40.0", "50.0", "60.0", 
         "70.0", "80.0", "90.0", "100.0", "200.0", "300.0", "500.0", "700.0", "1000.0", "1500.0", 
         "2000.0", "3000.0", "5000.0", "7000.0", "10000.0"}, 
         {"14.68", "15.0", "16.0", "17.0", "18.0", "19.0", "20.0", "21.0", "22.0", "23.0", 
         "24.0", "25.0", "26.0", "27.0", "28.0", "29.0", "30.0", "31.0", "32.0", "33.0", 
         "34.0", "35.0", "36.0", "37.0", "38.0", "39.0", "40.0", "41.0", "42.0", "43.0", 
         "44.0", "45.0", "46.0", "47.0", "48.0", "49.0", "50.0", "100.0", "150.0", "200.0", 
         "250.0", "300.0", "500.0", "700.0", "900.0", "1000.0", "1500.0", "2000.0", "3000.0", "5000.0", 
         "7000.0", "10000.0"}, 
         {"14.68", "14.77", "14.98", "15.19", "15.49", "15.79", "16.81", "17.81", "18.84", "20.86", 
         "23.9", "28.98", "34.05", "39.1", "45.21", "49.26", "54.33", "59.38", "69.58", "79.68", 
         "89.88", "99.88", "120.28", "140.58", "160.78", "181.28", "201.58", "252.38", "303.18", "404.78", 
         "506.18", "800.0", "1000.0", "1500.0", "2000.0", "3000.0", "5000.0", "7000.0", "10000.0"}, 
         {"16.57", "16.728", "16.87", "17.013", "17.157", "17.302", "17.449", "17.596", "17.745", "17.896", 
         "18.047", "18.2", "18.354", "18.51", "18.667", "18.825", "18.984", "19.307", "19.471", "19.97", 
         "22.0", "24.0", "26.0", "28.0", "30.0", "35.0", "40.0", "45.0", "50.0", "60.0", 
         "70.0", "80.0", "90.0", "100.0", "150.0", "200.0", "300.0", "400.0", "500.0", "600.0", 
         "700.0", "800.0", "900.0", "1000.0", "1500.0", "2000.0", "3000.0", "5000.0", "7000.0", "10000.0"}, 
         {"17.22", "17.783", "17.934", "18.086", "18.24", "18.395", "18.552", "18.709", "18.868", "19.029", 
         "19.191", "19.354", "19.518", "19.684", "20.0", "22.0", "24.0", "26.0", "28.0", "30.0", 
         "35.0", "40.0", "45.0", "50.0", "60.0", "70.0", "80.0", "90.0", "100.0", "150.0", 
         "200.0", "300.0", "400.0", "500.0", "600.0", "700.0", "800.0", "900.0", "1000.0", "1500.0", 
         "2000.0", "3000.0", "5000.0", "7000.0", "10000.0"}, 
         {"17.53", "17.841", "17.995", "18.152", "18.309", "18.468", "18.628", "18.79", "18.953", "19.117", 
         "19.283", "19.619", "20.0", "22.0", "24.0", "26.0", "28.0", "30.0", "35.0", "40.0", 
         "45.0", "50.0", "60.0", "70.0", "80.0", "90.0", "100.0", "150.0", "200.0", "300.0", 
         "400.0", "500.0", "600.0", "700.0", "800.0", "900.0", "1000.0", "1500.0", "2000.0", "3000.0", 
         "5000.0", "7000.0", "10000.0"}, 
         {"15.4", "15.6", "15.8", "16.0", "16.2", "16.4", "16.6", "16.8", "17.0", "17.2", 
         "17.4", "17.6", "17.8", "18.0", "18.2", "18.4", "18.6", "18.8", "19.0", "19.2", 
         "19.4", "19.6", "19.8", "20.0", "21.0", "23.0", "25.0", "28.0", "32.0", "40.0", 
         "50.0", "70.0", "100.0", "150.0", "200.0", "300.0", "500.0", "700.0", "1000.0", "1500.0", 
         "2000.0", "3000.0", "5000.0", "7000.0", "10000.0"}, 
         {"19.0", "19.2", "19.4", "19.6", "19.8", "20.0", "21.0", "23.0", "25.0", "28.0", 
         "32.0", "40.0", "50.0", "70.0", "100.0", "150.0", "200.0", "300.0", "500.0", "700.0", 
         "1000.0", "1500.0", "2000.0", "3000.0", "5000.0", "7000.0", "10000.0"}, 
         {"0.0", "0.02", "0.06", "0.08", "0.1", "0.2", "0.3", "0.4", "0.5", "0.6", 
         "0.7", "0.8", "0.9", "1.0", "1.2", "1.4", "1.5", "1.6", "1.8", "2.0", 
         "2.5", "3.0", "3.5", "4.0", "5.0", "6.0", "7.0", "8.0", "9.0", "10.0", 
         "12.0", "15.0", "20.0", "30.0", "40.0", "50.0", "60.0", "80.0", "100.0", "150.0", 
         "200.0", "300.0", "500.0", "700.0", "1000.0", "1500.0", "3000.0", "5000.0", "7000.0", "10000.0"}, 
         {"0.0", "0.02", "0.06", "0.08", "0.1", "0.2", "0.3", "0.4", "0.5", "0.6", 
         "0.7", "0.8", "0.9", "1.0", "1.2", "1.4", "1.5", "1.6", "1.8", "2.0", 
         "2.5", "3.0", "3.5", "4.0", "5.0", "6.0", "7.0", "8.0", "9.0", "10.0", 
         "12.0", "15.0", "20.0", "30.0", "40.0", "50.0", "60.0", "80.0", "100.0", "150.0", 
         "200.0", "300.0", "500.0", "700.0", "1000.0", "1500.0", "3000.0", "5000.0", "7000.0", "10000.0"}, 
         {"0.01", "0.02", "0.04", "0.06", "0.08", "0.1", "0.2", "0.3", "0.4", "0.5", 
         "0.6", "0.7", "0.8", "0.9", "1.0", "1.2", "1.4", "1.5", "1.6", "1.8", 
         "2.0", "2.5", "3.0", "3.5", "4.0", "5.0", "6.0", "7.0", "8.0", "9.0", 
         "10.0", "12.0", "15.0", "20.0", "30.0", "40.0", "50.0", "60.0", "80.0", "100.0", 
         "150.0", "200.0", "300.0", "500.0", "700.0", "1000.0", "1500.0", "3000.0", "5000.0", "7000.0", 
         "10000.0"}, 
         {"0.01", "0.02", "0.04", "0.06", "0.08", "0.1", "0.2", "0.3", "0.4", "0.5", 
         "0.6", "0.7", "0.8", "0.9", "1.0", "1.2", "1.4", "1.5", "1.6", "1.8", 
         "2.0", "2.5", "3.0", "3.5", "4.0", "5.0", "6.0", "7.0", "8.0", "9.0", 
         "10.0", "12.0", "15.0", "20.0", "30.0", "40.0", "50.0", "60.0", "80.0", "100.0", 
         "150.0", "200.0", "300.0", "500.0", "700.0", "1000.0", "1500.0", "3000.0", "5000.0", "7000.0", 
         "10000.0"}});
    model.component("comp1").physics("plas").feature("rg2")
         .set("ydata_crossSection", new String[][]{{}, 
         {"6.4E-20", "8.0E-20", "8.96E-20", "9.56517E-20", "1.00634E-19", "1.04594E-19", "1.1925E-19", "1.28927E-19", "1.3759E-19", "1.45241E-19", 
         "1.52776E-19", "1.60216E-19", "1.65553E-19", "1.67968E-19", "1.69275E-19", "1.71896E-19", "1.73936E-19", "1.74013E-19", "1.73452E-19", "1.70558E-19", 
         "1.67099E-19", "1.53727E-19", "1.4192E-19", "1.32536E-19", "1.22719E-19", "1.06391E-19", "8.99846E-20", "7.64475E-20", "6.72987E-20", "5.83477E-20", 
         "4.96533E-20", "3.89838E-20", "2.88705E-20", "1.82034E-20", "9.929E-21", "6.1977E-21", "4.2239E-21", "3.5146E-21", "2.4544E-21", "1.375E-21", 
         "8.83E-22", "4.327E-22", "2.209E-22", "1.025E-22", "5.93E-23", "3.31E-23", "1.71E-23", "5.54E-24", "2.41E-24", "1.39E-24", 
         "7.79E-25"}, 
         {"0.0", "2.0E-22", "6.0E-22", "2.0E-21", "4.0E-21", "4.9E-21", "5.1E-21", "5.0E-21", "4.62E-21", "3.69E-21", 
         "2.04E-21", "1.52E-21", "5.65E-22", "3.32E-22", "2.42E-22", "1.7E-22", "1.12E-22", "9.68E-23", "8.17E-23", "6.09E-23", 
         "4.51E-23", "1.9E-23", "1.0E-23", "2.2E-24", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"}, 
         {"0.0", "0.0", "3.0E-23", "8.0E-23", "1.8E-22", "2.4E-22", "2.9E-22", "3.6E-22", "3.8E-22", "3.8E-22", 
         "3.0E-22", "1.7E-22", "8.0E-23", "0.0", "0.0", "0.0", "0.0"}, 
         {"0.0", "3.0E-24", "1.0E-23", "1.3E-23", "2.0E-23", "2.9E-23", "3.7E-23", "4.1E-23", "4.1E-23", "3.4E-23", 
         "2.3E-23", "1.2E-23", "6.0E-24", "1.0E-24", "0.0", "0.0", "0.0"}, 
         {"0.0", "6.6E-23", "1.32E-22", "2.376E-22", "3.3E-22", "4.62E-22", "5.676E-22", "6.6E-22", "7.92E-22", "9.24E-22", 
         "1.122E-21", "1.254E-21", "1.452E-21", "1.6104E-21", "1.848E-21", "1.98E-21", "2.178E-21", "2.31E-21", "2.508E-21", "2.64E-21", 
         "2.772E-21", "2.8776E-21", "3.036E-21", "3.102E-21", "3.234E-21", "3.3E-21", "3.366E-21", "3.4584E-21", "3.5376E-21", "3.63E-21", 
         "3.6696E-21", "3.696E-21", "3.7224E-21", "3.762E-21", "3.762E-21", "3.762E-21", "3.762E-21", "3.762E-21", "3.762E-21", "3.762E-21", 
         "3.7488E-21", "3.7356E-21", "3.7092E-21", "3.696E-21", "3.6828E-21", "3.6696E-21", "3.6432E-21", "3.63E-21", "3.6036E-21", "3.564E-21", 
         "3.5376E-21", "3.498E-21", "3.4584E-21", "3.432E-21", "3.3792E-21", "3.3528E-21", "3.3264E-21", "3.102E-21", "2.9304E-21", "2.7456E-21", 
         "2.5608E-21", "2.376E-21", "2.2308E-21", "2.0724E-21", "1.914E-21", "1.782E-21", "1.65E-21", "1.518E-21", "1.4256E-21", "1.32E-21", 
         "1.2144E-21", "1.122E-21", "1.056E-21", "9.768E-22", "9.24E-22", "8.58E-22", "8.052E-22", "4.38647E-22", "2.67044E-22", "1.75531E-22", 
         "1.2204E-22", "8.85652E-23", "6.64831E-23", "1.00764E-23", "3.34186E-24", "1.52723E-24", "8.31984E-25", "5.06504E-25", "3.3293E-25", "2.31473E-25", 
         "1.67982E-25", "1.26098E-25", "0.0", "0.0"}, 
         {"0.0", "1.45161E-22", "2.41935E-22", "3.3871E-22", "4.16129E-22", "4.83871E-22", "5.80645E-22", "6.77419E-22", "7.74194E-22", "8.51613E-22", 
         "9.19355E-22", "1.01613E-21", "1.08387E-21", "1.16129E-21", "1.23871E-21", "1.30645E-21", "1.37419E-21", "1.45161E-21", "1.50968E-21", "1.59677E-21", 
         "1.64516E-21", "1.72258E-21", "1.79032E-21", "1.83871E-21", "1.91613E-21", "1.98387E-21", "2.03226E-21", "2.10968E-21", "2.15806E-21", "2.20645E-21", 
         "2.27419E-21", "2.32258E-21", "2.37097E-21", "2.41935E-21", "2.46774E-21", "2.53548E-21", "2.57419E-21", "2.6129E-21", "2.66129E-21", "2.70968E-21", 
         "2.76774E-21", "2.80645E-21", "2.85484E-21", "2.90323E-21", "3.10645E-21", "3.29032E-21", "3.44516E-21", "3.58065E-21", "3.71613E-21", "3.84194E-21", 
         "3.95806E-21", "4.05484E-21", "4.15161E-21", "4.21935E-21", "4.30645E-21", "4.37419E-21", "4.45161E-21", "4.5E-21", "4.55806E-21", "4.61613E-21", 
         "4.68387E-21", "4.72258E-21", "4.76129E-21", "4.79032E-21", "4.72452E-21", "4.435E-21", "4.30339E-21", "4.15863E-21", "3.90858E-21", "3.65855E-21", 
         "2.75048E-21", "2.21435E-21", "1.63677E-21", "1.29968E-21", "9.62742E-22", "7.22097E-22", "5.77742E-22", "4.23548E-22", "2.79194E-22", "2.02258E-22", 
         "1.54032E-22"}, 
         {"0.0", "1.98E-23", "7.92E-23", "1.188E-22", "1.683E-22", "2.178E-22", "2.574E-22", "3.465E-22", "4.059E-22", "4.95E-22", 
         "5.544E-22", "6.237E-22", "6.93E-22", "7.92E-22", "8.415E-22", "8.7615E-22", "8.8011E-22", "8.712E-22", "8.613E-22", "8.415E-22", 
         "8.316E-22", "8.118E-22", "8.019E-22", "7.821E-22", "7.722E-22", "7.524E-22", "7.3755E-22", "7.227E-22", "7.128E-22", "6.93E-22", 
         "6.831E-22", "6.633E-22", "6.534E-22", "6.435E-22", "6.237E-22", "6.138E-22", "6.039E-22", "5.94E-22", "5.7915E-22", "5.6925E-22", 
         "5.544E-22", "5.445E-22", "4.95E-22", "4.554E-22", "4.2075E-22", "3.9105E-22", "3.6135E-22", "3.366E-22", "3.168E-22", "2.97E-22", 
         "2.772E-22", "2.6235E-22", "2.4255E-22", "2.277E-22", "2.1681E-22", "1.98E-22", "1.881E-22", "1.782E-22", "1.6335E-22", "1.5345E-22", 
         "1.4355E-22", "1.3662E-22", "7.44261E-23", "4.53099E-23", "2.97827E-23", "2.07067E-23", "1.5027E-23", "1.12803E-23", "1.70969E-24", "5.67018E-25", 
         "2.59128E-25", "1.41164E-25", "8.59398E-26", "5.64891E-26", "3.92745E-26", "2.85019E-26", "2.13955E-26", "0.0", "0.0"}, 
         {"0.0", "3.63E-22", "8.25E-22", "1.155E-21", "1.32E-21", "1.485E-21", "1.551E-21", "1.617E-21", "1.6665E-21", "1.6995E-21", 
         "1.749E-21", "1.7985E-21", "1.8282E-21", "1.848E-21", "1.8645E-21", "1.8678E-21", "1.8711E-21", "1.8645E-21", "1.848E-21", "1.8249E-21", 
         "1.7985E-21", "1.7754E-21", "1.749E-21", "1.716E-21", "1.683E-21", "1.65E-21", "1.617E-21", "1.5774E-21", "1.5444E-21", "1.5015E-21", 
         "1.4685E-21", "1.4355E-21", "1.4025E-21", "1.3629E-21", "1.3365E-21", "1.3035E-21", "1.287E-21", "1.254E-21", "1.221E-21", "1.1979E-21", 
         "1.1715E-21", "1.1385E-21", "1.0065E-21", "9.075E-22", "8.085E-22", "7.095E-22", "6.435E-22", "5.61E-22", "5.115E-22", "4.455E-22", 
         "4.125E-22", "3.63E-22", "3.3E-22", "3.102E-22", "2.772E-22", "2.508E-22", "2.31E-22", "2.112E-22", "1.881E-22", "1.683E-22", 
         "1.485E-22", "1.32E-22", "7.19093E-23", "4.37778E-23", "2.87756E-23", "2.00065E-23", "1.45189E-23", "1.08989E-23", "1.65188E-24", "5.47844E-25", 
         "2.50365E-25", "1.36391E-25", "8.30335E-26", "5.45788E-26", "3.79464E-26", "2.7538E-26", "2.06719E-26", "0.0", "0.0"}, 
         {"0.0", "9.23077E-23", "1.93846E-22", "3.23077E-22", "4.24615E-22", "5.35385E-22", "6.46154E-22", "7.38462E-22", "8.30769E-22", "9.23077E-22", 
         "1.01538E-21", "1.10769E-21", "1.18154E-21", "1.27385E-21", "1.34769E-21", "1.42154E-21", "1.49538E-21", "1.56E-21", "1.61538E-21", "1.69846E-21", 
         "1.75385E-21", "1.81846E-21", "1.89231E-21", "1.95692E-21", "2.00308E-21", "2.04923E-21", "2.12308E-21", "2.16923E-21", "2.21538E-21", "2.28E-21", 
         "2.33538E-21", "2.38154E-21", "2.43692E-21", "2.47385E-21", "2.52923E-21", "2.57538E-21", "2.61231E-21", "2.64923E-21", "2.67692E-21", "2.86154E-21", 
         "3.02769E-21", "3.14769E-21", "3.25846E-21", "3.36E-21", "3.45231E-21", "3.51692E-21", "3.6E-21", "3.64615E-21", "3.69231E-21", "3.73846E-21", 
         "3.78462E-21", "3.83077E-21", "3.86769E-21", "3.89538E-21", "3.92308E-21", "3.94154E-21", "3.96E-21", "3.96923E-21", "3.98769E-21", "3.98769E-21", 
         "3.86149E-21", "3.62174E-21", "3.50815E-21", "3.39458E-21", "3.19268E-21", "2.32195E-21", "1.80455E-21", "1.26644E-21", "9.8938E-22", "7.5466E-22", 
         "5.83846E-22", "4.68923E-22", "3.36154E-22", "2.21231E-22", "1.68154E-22", "1.23846E-22"}, 
         {"0.0", "9.6E-23", "1.728E-22", "2.496E-22", "3.36E-22", "4.32E-22", "5.28E-22", "6.048E-22", "6.72E-22", "7.296E-22", 
         "8.064E-22", "8.544E-22", "9.024E-22", "9.504E-22", "9.888E-22", "1.0464E-21", "1.0944E-21", "1.1328E-21", "1.1712E-21", "1.2096E-21", 
         "1.2288E-21", "1.2576E-21", "1.2864E-21", "1.3056E-21", "1.3296E-21", "1.344E-21", "1.3488E-21", "1.3488E-21", "1.344E-21", "1.3392E-21", 
         "1.3344E-21", "1.3296E-21", "1.3248E-21", "1.3152E-21", "1.3056E-21", "1.296E-21", "1.2864E-21", "1.2768E-21", "1.2672E-21", "1.2192E-21", 
         "1.1664E-21", "1.1136E-21", "1.0656E-21", "1.0176E-21", "9.744E-22", "9.36E-22", "9.024E-22", "8.688E-22", "8.352E-22", "8.16E-22", 
         "7.92E-22", "7.728E-22", "7.536E-22", "7.392E-22", "7.2E-22", "7.056E-22", "6.96E-22", "6.816E-22", "6.72E-22", "6.30149E-22", 
         "5.54819E-22", "5.38355E-22", "5.20245E-22", "4.88965E-22", "4.57685E-22", "3.44086E-22", "2.74939E-22", "1.76675E-22", "1.37714E-22", "1.04839E-22", 
         "7.75309E-23", "6.15309E-23", "4.41482E-23", "2.88395E-23", "2.16296E-23", "1.6E-23"}, 
         {"0.0", "2.145E-22", "3.498E-22", "4.2405E-22", "3.894E-22", "3.63E-22", "3.399E-22", "3.234E-22", "3.069E-22", "2.937E-22", 
         "2.805E-22", "2.706E-22", "2.64E-22", "2.541E-22", "2.475E-22", "2.376E-22", "2.31E-22", "2.2605E-22", "2.211E-22", "2.145E-22", 
         "2.112E-22", "2.079E-22", "2.013E-22", "1.98E-22", "1.947E-22", "1.881E-22", "1.848E-22", "1.815E-22", "1.7655E-22", "1.749E-22", 
         "1.6995E-22", "1.6665E-22", "1.6335E-22", "1.6005E-22", "1.452E-22", "1.32E-22", "1.188E-22", "1.089E-22", "9.9E-23", "8.91E-23", 
         "8.25E-23", "7.59E-23", "6.93E-23", "6.435E-23", "5.94E-23", "5.61E-23", "5.115E-23", "4.785E-23", "4.455E-23", "4.125E-23", 
         "3.795E-23", "3.63E-23", "3.3E-23", "2.97E-23", "1.61796E-23", "9.85E-24", "6.47451E-24", "4.50145E-24", "3.26675E-24", "2.45224E-24", 
         "3.71672E-25", "1.23265E-25", "5.63322E-26", "3.06879E-26", "1.86825E-26", "1.22802E-26", "8.53793E-27", "6.19606E-27", "4.65119E-27", "0.0", 
         "0.0"}, 
         {"0.0", "1.2E-23", "3.0E-23", "4.5E-23", "5.7E-23", "6.9E-23", "8.1E-23", "9.0E-23", "1.02E-22", "1.14E-22", 
         "1.23E-22", "1.32E-22", "1.44E-22", "1.53E-22", "1.62E-22", "1.71E-22", "1.77E-22", "1.86E-22", "1.95E-22", "2.01E-22", 
         "2.1E-22", "2.19E-22", "2.25E-22", "2.34E-22", "2.4E-22", "2.46E-22", "2.52E-22", "2.61E-22", "2.67E-22", "2.73E-22", 
         "2.79E-22", "2.85E-22", "3.12E-22", "3.36E-22", "3.6E-22", "3.81E-22", "4.02E-22", "4.23E-22", "4.41E-22", "4.59E-22", 
         "4.74E-22", "4.89E-22", "5.04E-22", "5.16E-22", "5.22E-22", "5.31E-22", "5.4E-22", "5.46E-22", "5.52E-22", "5.55E-22", 
         "5.58E-22", "5.61E-22", "5.58515E-22", "5.38524E-22", "5.07279E-22", "4.91301E-22", "4.73583E-22", "4.4744E-22", "3.25639E-22", "2.53238E-22", 
         "1.77856E-22", "1.39003E-22", "1.06128E-22", "7.85E-23", "6.23E-23", "4.47E-23", "2.92E-23", "2.19E-23", "1.62E-23"}, 
         {"0.0", "6.318E-24", "1.6848E-23", "2.9484E-23", "3.7908E-23", "5.0544E-23", "6.318E-23", "7.1604E-23", "8.0028E-23", "8.6346E-23", 
         "9.2664E-23", "1.01088E-22", "1.07406E-22", "1.13724E-22", "1.20042E-22", "1.24254E-22", "1.30572E-22", "1.3689E-22", "1.41102E-22", "1.4742E-22", 
         "1.53738E-22", "1.5795E-22", "1.64268E-22", "1.6848E-22", "1.72692E-22", "1.76904E-22", "1.83222E-22", "1.87434E-22", "1.91646E-22", "1.95858E-22", 
         "2.0007E-22", "2.19024E-22", "2.35872E-22", "2.5272E-22", "2.67462E-22", "2.82204E-22", "2.96946E-22", "3.09582E-22", "3.22218E-22", "3.32748E-22", 
         "3.43278E-22", "3.53808E-22", "3.62232E-22", "3.66444E-22", "3.72762E-22", "3.7908E-22", "3.83292E-22", "3.87504E-22", "3.8961E-22", "3.91716E-22", 
         "3.93822E-22", "3.92078E-22", "3.78044E-22", "3.5611E-22", "3.44893E-22", "3.32455E-22", "3.14103E-22", "2.28599E-22", "1.77773E-22", "1.24855E-22", 
         "9.75801E-23", "7.45019E-23", "5.5107E-23", "4.37346E-23", "3.13794E-23", "2.04984E-23", "1.53738E-23", "1.13724E-23"}, 
         {"0.0", "2.97E-25", "4.95E-25", "5.94E-25", "6.93E-25", "7.92E-25", "9.9E-25", "1.089E-24", "1.188E-24", "1.287E-24", 
         "1.4256E-24", "1.5444E-24", "1.6434E-24", "1.7424E-24", "1.8414E-24", "1.9404E-24", "2.0394E-24", "2.178E-24", "2.2374E-24", "2.3364E-24", 
         "2.4156E-24", "2.4948E-24", "2.574E-24", "2.673E-24", "2.7324E-24", "2.8314E-24", "2.8908E-24", "2.97E-24", "3.267E-24", "3.564E-24", 
         "3.8016E-24", "4.0392E-24", "4.2174E-24", "4.3956E-24", "4.554E-24", "4.6926E-24", "4.8114E-24", "4.95E-24", "5.049E-24", "5.148E-24", 
         "5.247E-24", "5.2866E-24", "5.346E-24", "5.3856E-24", "5.445E-24", "5.4648E-24", "5.5044E-24", "5.544E-24", "5.43863E-24", "5.3695E-24", 
         "5.03682E-24", "4.78556E-24", "4.57604E-24", "4.432E-24", "3.43154E-24", "2.65692E-24", "1.86603E-24", "1.45839E-24", "1.11348E-24", "8.217E-25", 
         "6.534E-25", "4.653E-25", "3.03686E-25", "2.27824E-25", "1.67464E-25"}, 
         {"0.0", "1.263E-24", "3.789E-24", "7.578E-24", "8.841E-24", "1.1367E-23", "1.5156E-23", "1.7682E-23", "1.8945E-23", "2.1471E-23", 
         "2.3997E-23", "2.6523E-23", "2.7786E-23", "3.0312E-23", "3.1575E-23", "3.4101E-23", "3.5364E-23", "3.789E-23", "3.9153E-23", "4.0416E-23", 
         "4.2942E-23", "4.4205E-23", "4.5468E-23", "4.6731E-23", "4.7994E-23", "4.9257E-23", "5.052E-23", "5.1783E-23", "5.6835E-23", "6.11292E-23", 
         "6.49182E-23", "6.76968E-23", "7.02228E-23", "7.24962E-23", "7.42644E-23", "7.578E-23", "7.7043E-23", "7.8306E-23", "7.9569E-23", "8.03268E-23", 
         "8.0832E-23", "8.15898E-23", "8.2095E-23", "8.28528E-23", "8.3358E-23", "8.3358E-23", "8.36106E-23", "8.38632E-23", "8.65883E-23", "8.65883E-23", 
         "8.38632E-23", "8.25328E-23", "7.74194E-23", "7.61913E-23", "5.71352E-23", "4.14969E-23", "2.91445E-23", "2.27777E-23", "1.73908E-23", "1.33878E-23", 
         "1.06092E-23", "7.6201E-24", "4.9678E-24", "3.72025E-24", "2.7372E-24"}, 
         {"0.0", "1.95842E-23", "5.82441E-23", "9.89764E-23", "1.81619E-22", "2.64424E-22", "3.43877E-22", "4.03797E-22", "4.57091E-22", "5.03484E-22", 
         "5.49628E-22", "5.79727E-22", "6.09826E-22", "6.33026E-22", "6.49325E-22", "6.65689E-22", "6.78346E-22", "6.91003E-22", "7.0366E-22", "7.09416E-22", 
         "7.22073E-22", "7.27047E-22", "7.31979E-22", "7.43812E-22", "7.48744E-22", "7.53677E-22", "7.5977E-22", "7.65993E-22", "7.79115E-22", "7.85337E-22", 
         "7.9156E-22", "7.97782E-22", "8.04217E-22", "8.10708E-22", "8.17199E-22", "8.2369E-22", "8.30001E-22", "8.28886E-22", "6.88449E-22", "5.8258E-22", 
         "4.74738E-22", "4.10876E-22", "3.036E-22", "2.2218E-22", "1.9044E-22", "1.7112E-22", "1.254E-22", "9.77E-23", "6.79E-23", "4.22E-23", 
         "3.05E-23", "2.14E-23"}, 
         {"0.0", "8.76E-24", "2.892E-23", "5.724E-23", "9.696E-23", "1.536E-22", "2.604E-22", "2.928E-22", "3.144E-22", "3.444E-22", 
         "3.528E-22", "3.852E-22", "3.936E-22", "4.08E-22", "4.14E-22", "4.164E-22", "4.212E-22", "4.224E-22", "4.176E-22", "4.08E-22", 
         "3.996E-22", "3.888E-22", "3.744E-22", "3.54E-22", "3.288E-22", "3.156E-22", "2.94E-22", "2.604E-22", "2.328E-22", "1.956E-22", 
         "1.716E-22", "1.2E-22", "1.04E-22", "7.64E-23", "6.14E-23", "4.48E-23", "2.99E-23", "2.29E-23", "1.71E-23"}, 
         {"0.0", "6.61015E-25", "6.61015E-25", "2.64406E-24", "2.64406E-24", "4.6271E-24", "1.58644E-23", "2.44576E-23", "2.90847E-23", "3.37118E-23", 
         "6.41184E-23", "7.13896E-23", "7.79998E-23", "7.99828E-23", "8.39489E-23", "8.52709E-23", "8.7254E-23", "9.05591E-23", "9.12201E-23", "9.51861E-23", 
         "9.79504E-23", "9.84402E-23", "9.87667E-23", "1.05297E-22", "1.12806E-22", "1.25866E-22", "1.35825E-22", "1.42681E-22", "1.48068E-22", "1.57537E-22", 
         "1.64883E-22", "1.68148E-22", "1.69781E-22", "1.63251E-22", "1.39253E-22", "1.1803E-22", "8.32578E-23", "6.46473E-23", "5.4036E-23", "4.55469E-23", 
         "3.96699E-23", "3.52621E-23", "3.18339E-23", "2.88954E-23", "2.01E-23", "1.49E-23", "9.58E-24", "5.34E-24", "3.56E-24", "2.28E-24"}, 
         {"0.0", "5.13E-25", "7.695E-25", "1.026E-24", "1.026E-24", "1.539E-24", "1.539E-24", "2.8215E-24", "4.10625E-24", "6.15825E-24", 
         "7.18425E-24", "1.28295E-23", "1.38555E-23", "1.43685E-23", "1.4625E-23", "1.53E-23", "1.5525E-23", "1.5525E-23", "1.665E-23", "2.34E-23", 
         "3.1725E-23", "3.6675E-23", "3.9825E-23", "4.185E-23", "4.4775E-23", "4.635E-23", "4.635E-23", "4.4775E-23", "4.32E-23", "3.5325E-23", 
         "2.79E-23", "1.93275E-23", "1.48275E-23", "1.1835E-23", "1.0035E-23", "8.5725E-24", "7.56E-24", "6.795E-24", "6.075E-24", "3.77E-24", 
         "2.69E-24", "1.65E-24", "8.74E-25", "5.66E-25", "3.51E-25"}, 
         {"0.0", "1.24701E-25", "3.1218E-25", "6.8628E-25", "6.8628E-25", "3.18114E-24", "3.30498E-24", "3.42882E-24", "3.49074E-24", "3.55524E-24", 
         "3.61716E-24", "3.67908E-24", "3.741E-24", "3.9732E-24", "3.999E-24", "3.999E-24", "4.4892E-24", "5.3406E-24", "7.8174E-24", "9.7008E-24", 
         "1.12746E-23", "1.25904E-23", "1.44222E-23", "1.5093E-23", "1.50156E-23", "1.46544E-23", "1.38804E-23", "1.1223E-23", "9.0816E-24", "6.45E-24", 
         "4.8504E-24", "3.87E-24", "3.3024E-24", "2.838E-24", "2.45358E-24", "2.19042E-24", "1.98144E-24", "1.34E-24", "9.72E-25", "6.14E-25", 
         "3.39E-25", "2.27E-25", "1.47E-25"}, 
         {"0.0", "1.0E-22", "2.0E-22", "3.0E-22", "4.5E-22", "5.5E-22", "7.0E-22", "8.0E-22", "9.5E-22", "1.05E-21", 
         "1.2E-21", "1.3E-21", "1.45E-21", "1.55E-21", "1.75E-21", "1.85E-21", "1.95E-21", "2.1E-21", "2.25E-21", "2.368E-21", 
         "2.486E-21", "2.604E-21", "2.722E-21", "2.84E-21", "3.35E-21", "4.37E-21", "5.21E-21", "5.838E-21", "6.6753E-21", "8.35E-21", 
         "8.9E-21", "8.95E-21", "8.53E-21", "7.54E-21", "6.55E-21", "5.29E-21", "3.78E-21", "2.97E-21", "2.24E-21", "1.62E-21", 
         "1.27E-21", "9.16E-22", "5.76E-22", "4.34E-22", "3.21E-22"}, 
         {"0.0", "1.5878E-24", "3.1756E-24", "4.7634E-24", "6.3512E-24", "7.939E-24", "1.1366E-23", "1.822E-23", "3.11E-23", "8.398E-23", 
         "1.5449E-22", "2.955E-22", "4.99E-22", "7.184E-22", "7.86E-22", "6.8E-22", "5.74E-22", "4.231E-22", "2.61E-22", "1.909E-22", 
         "1.314E-22", "9.537E-23", "7.43E-23", "5.379E-23", "3.382E-23", "2.551E-23", "1.885E-23"}, 
         {"0.0", "0.0", "4.1162005E-19", "3.1778315E-19", "2.6E-19", "1.393918E-19", "9.67981E-20", "7.473105E-20", "6.114255E-20", "5.18956E-20", 
         "4.5177385E-20", "4.0064995E-20", "3.6037965E-20", "3.2779895E-20", "2.7822405E-20", "2.4220605E-20", "2.27634E-20", "2.147974E-20", "1.932076E-20", "1.7574035E-20", 
         "1.437851E-20", "1.2203965E-20", "1.062408E-20", "9.421835E-21", "7.70864E-21", "6.54282E-21", "5.695805E-21", "5.051255E-21", "4.5435415E-21", "4.132775E-21", 
         "3.507752E-21", "2.86993E-21", "2.215673E-21", "1.5386335E-21", "1.187872E-21", "9.718785E-22", "8.24896E-22", "6.368445E-22", "5.21046E-22", "3.618308E-22", 
         "2.7934435E-22", "1.9398555E-22", "1.225311E-22", "9.05365E-23", "6.569165E-23", "4.5618365E-23", "2.4457025E-23", "1.544829E-23", "1.1414525E-23", "8.282175E-24"}, 
         {"0.0", "0.0", "4.1162005E-19", "3.1778315E-19", "2.6E-19", "1.393918E-19", "9.67981E-20", "7.473105E-20", "6.114255E-20", "5.18956E-20", 
         "4.5177385E-20", "4.0064995E-20", "3.6037965E-20", "3.2779895E-20", "2.7822405E-20", "2.4220605E-20", "2.27634E-20", "2.147974E-20", "1.932076E-20", "1.7574035E-20", 
         "1.437851E-20", "1.2203965E-20", "1.062408E-20", "9.421835E-21", "7.70864E-21", "6.54282E-21", "5.695805E-21", "5.051255E-21", "4.5435415E-21", "4.132775E-21", 
         "3.507752E-21", "2.86993E-21", "2.215673E-21", "1.5386335E-21", "1.187872E-21", "9.718785E-22", "8.24896E-22", "6.368445E-22", "5.21046E-22", "3.618308E-22", 
         "2.7934435E-22", "1.9398555E-22", "1.225311E-22", "9.05365E-23", "6.569165E-23", "4.5618365E-23", "2.4457025E-23", "1.544829E-23", "1.1414525E-23", "8.282175E-24"}, 
         {"0.0", "2.94168E-18", "1.3624135E-18", "8.68488E-19", "6.3099E-19", "4.925E-19", "2.280971E-19", "1.4540345E-19", "1.056412E-19", "8.2455E-20", 
         "6.734235E-20", "5.674735E-20", "4.8926795E-20", "4.2928245E-20", "3.818832E-20", "3.118902E-20", "2.628203E-20", "2.434364E-20", "2.266002E-20", "1.9881845E-20", 
         "1.768659E-20", "1.380473E-20", "1.1274545E-20", "9.500715E-21", "8.19139E-21", "6.39354E-21", "5.221705E-21", "4.400173E-21", "3.793771E-21", "3.328645E-21", 
         "2.9611125E-21", "2.4183885E-21", "1.8875995E-21", "1.3714135E-21", "8.74225E-22", "6.35158E-22", "4.957534E-22", "4.048898E-22", "2.94168E-22", "2.2960385E-22", 
         "1.4636395E-22", "1.0633905E-22", "6.77872E-23", "3.844059E-23", "2.6455645E-23", "1.7803425E-23", "1.1349025E-23", "5.2562E-24", "2.980673E-24", "2.051364E-24", 
         "1.380473E-24"}, 
         {"0.0", "2.94168E-18", "1.3624135E-18", "8.68488E-19", "6.3099E-19", "4.925E-19", "2.280971E-19", "1.4540345E-19", "1.056412E-19", "8.2455E-20", 
         "6.734235E-20", "5.674735E-20", "4.8926795E-20", "4.2928245E-20", "3.818832E-20", "3.118902E-20", "2.628203E-20", "2.434364E-20", "2.266002E-20", "1.9881845E-20", 
         "1.768659E-20", "1.380473E-20", "1.1274545E-20", "9.500715E-21", "8.19139E-21", "6.39354E-21", "5.221705E-21", "4.400173E-21", "3.793771E-21", "3.328645E-21", 
         "2.9611125E-21", "2.4183885E-21", "1.8875995E-21", "1.3714135E-21", "8.74225E-22", "6.35158E-22", "4.957534E-22", "4.048898E-22", "2.94168E-22", "2.2960385E-22", 
         "1.4636395E-22", "1.0633905E-22", "6.77872E-23", "3.844059E-23", "2.6455645E-23", "1.7803425E-23", "1.1349025E-23", "5.2562E-24", "2.980673E-24", "2.051364E-24", 
         "1.380473E-24"}});

    return model;
  }

  public static Model run3(Model model) {
    model.component("comp1").physics("plas").feature("rg2")
         .set("collType", new String[][]{{"Elastic"}, 
         {"Excitation"}, 
         {"Excitation"}, 
         {"Excitation"}, 
         {"Excitation"}, 
         {"Excitation"}, 
         {"Excitation"}, 
         {"Excitation"}, 
         {"Excitation"}, 
         {"Excitation"}, 
         {"Excitation"}, 
         {"Excitation"}, 
         {"Excitation"}, 
         {"Excitation"}, 
         {"Excitation"}, 
         {"Excitation"}, 
         {"Excitation"}, 
         {"Excitation"}, 
         {"Excitation"}, 
         {"Excitation"}, 
         {"Ionization"}, 
         {"Ionization"}, 
         {"Excitation"}, 
         {"Excitation"}, 
         {"Excitation"}, 
         {"Excitation"}});
    model.component("comp1").physics("plas").feature("rg2")
         .set("mratio", new String[][]{{"2.743480e-4"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}});
    model.component("comp1").physics("plas").feature("rg2")
         .set("de", new String[][]{{"0"}, 
         {"0.516"}, 
         {"1.0"}, 
         {"1.5"}, 
         {"7.93"}, 
         {"11.4"}, 
         {"11.72"}, 
         {"11.72"}, 
         {"12.4"}, 
         {"12.4"}, 
         {"13.0"}, 
         {"13.8"}, 
         {"14.0"}, 
         {"14.6"}, 
         {"14.6"}, 
         {"14.68"}, 
         {"14.68"}, 
         {"16.57"}, 
         {"17.22"}, 
         {"17.53"}, 
         {"1.540000e+1"}, 
         {"1.900000e+1"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"1.000000e-2"}, 
         {"1.000000e-2"}});
    model.component("comp1").physics("plas").feature("rg2").set("Filepath", "H2_plasma_chemistry_import_H2.txt");
    model.component("comp1").physics("plas").feature("rg2").label("H2 - Electron Impact Reactions");
    model.component("comp1").physics("plas").feature("rg3")
         .set("Species", new String[][]{{"e"}, {"H"}, {"H2"}, {"H2_1p"}, {"H3_1p"}, {"Hn2"}, {"Hn3"}});
    model.component("comp1").physics("plas").feature("rg3")
         .set("fwdSpecies", new String[][]{{"Hn2+H2"}, {"Hn3+H2"}, {"H2+H2+"}, {"H2+H2"}, {"2H+H2"}, {"H2+H"}, {"3H"}});
    model.component("comp1").physics("plas").feature("rg3")
         .set("revSpecies", new String[][]{{"H3++e"}, {"H3++e"}, {"H3++H"}, {"2H+H2"}, {"H2+H2"}, {"3H"}, {"H2+H"}});
    model.component("comp1").physics("plas").feature("rg3")
         .set("rsSequenceNo", new int[][]{{1}, {2}, {3}, {4}, {5}, {6}, {7}});
    model.component("comp1").physics("plas").feature("rg3")
         .set("Af", new String[][]{{"1.0e-11[cm^3/s]*N_A_const*sqrt(plas.T/1[K])"}, {"1.0e-11[cm^3/s]*N_A_const*sqrt(plas.T/1[K])"}, {"2.1e-9[cm^3/s]*N_A_const"}, {"3.7e-10[cm^3/s]*N_A_const*exp(-48300[K]/plas.T)"}, {"8.33e-33[cm^6/s]*N_A_const*N_A_const*(300[K]/plas.T)"}, {"3.7e-10[cm^3/s]*N_A_const*exp(-48300[K]/plas.T)"}, {"8.33e-33[cm^6/s]*N_A_const*N_A_const*(300[K]/plas.T)"}});
    model.component("comp1").physics("plas").feature("rg3")
         .set("nf", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("rg3")
         .set("Ef", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("rg3")
         .set("Ar", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("rg3")
         .set("nr", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("rg3")
         .set("Er", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("rg3").set("Filepath", "H2_plasma_chemistry_import_mixed.txt");
    model.component("comp1").physics("plas").feature("rg3").label("Mixed - Heavy Species Reactions");
    model.component("comp1").physics("plas").feature("e").set("specName", "e");
    model.component("comp1").physics("plas").feature("e").set("specLabel", "e");
    model.component("comp1").physics("plas").feature("e").set("sType", "electron");
    model.component("comp1").physics("plas").feature("e").set("sisDef", true);
    model.component("comp1").physics("plas").feature("e").label("\u7269\u8d28: e");
    model.component("comp1").physics("plas").feature("H").set("specName", "H");
    model.component("comp1").physics("plas").feature("H").set("specLabel", "H");
    model.component("comp1").physics("plas").feature("H").set("sisDef", true);
    model.component("comp1").physics("plas").feature("H").set("M", 0.00101);
    model.component("comp1").physics("plas").feature("H").set("sigma", "2.050e-10");
    model.component("comp1").physics("plas").feature("H").set("epsilonkb", "145.000");
    model.component("comp1").physics("plas").feature("H").set("mu", "0.000");
    model.component("comp1").physics("plas").feature("H").set("x0", "1E-5");
    model.component("comp1").physics("plas").feature("H").set("Tlo", 200);
    model.component("comp1").physics("plas").feature("H").set("Tmid", 1000);
    model.component("comp1").physics("plas").feature("H").set("Thi", 3500);
    model.component("comp1").physics("plas").feature("H").set("aLo1", "2.50000000E00");
    model.component("comp1").physics("plas").feature("H").set("aLo2", 7.05332819E-13);
    model.component("comp1").physics("plas").feature("H").set("aLo3", -1.99591964E-15);
    model.component("comp1").physics("plas").feature("H").set("aLo4", 2.30081632E-18);
    model.component("comp1").physics("plas").feature("H").set("aLo5", -9.27732332E-22);
    model.component("comp1").physics("plas").feature("H").set("aLo6", "2.54736599E04");
    model.component("comp1").physics("plas").feature("H").set("aLo7", "-4.46682853E-01");
    model.component("comp1").physics("plas").feature("H").set("aHi1", "2.50000001E00");
    model.component("comp1").physics("plas").feature("H").set("aHi2", -2.30842973E-11);
    model.component("comp1").physics("plas").feature("H").set("aHi3", 1.61561948E-14);
    model.component("comp1").physics("plas").feature("H").set("aHi4", -4.73515235E-18);
    model.component("comp1").physics("plas").feature("H").set("aHi5", 4.98197357E-22);
    model.component("comp1").physics("plas").feature("H").set("aHi6", "2.54736599E04");
    model.component("comp1").physics("plas").feature("H").set("aHi7", "-4.46682914E-01");
    model.component("comp1").physics("plas").feature("H").label("\u7269\u8d28: H");
    model.component("comp1").physics("plas").feature("Hn2").set("specName", "Hn2");
    model.component("comp1").physics("plas").feature("Hn2").set("specLabel", "Hn2");
    model.component("comp1").physics("plas").feature("Hn2").set("sisDef", true);
    model.component("comp1").physics("plas").feature("Hn2").set("M", 0.00101);
    model.component("comp1").physics("plas").feature("Hn2").set("sigma", "2.050e-10");
    model.component("comp1").physics("plas").feature("Hn2").set("epsilonkb", "145.000");
    model.component("comp1").physics("plas").feature("Hn2").set("mu", "0.000");
    model.component("comp1").physics("plas").feature("Hn2").set("Tlo", 200);
    model.component("comp1").physics("plas").feature("Hn2").set("Tmid", 1000);
    model.component("comp1").physics("plas").feature("Hn2").set("Thi", 3500);
    model.component("comp1").physics("plas").feature("Hn2").set("hadd", 10.2043);
    model.component("comp1").physics("plas").feature("Hn2").set("aLo1", "2.50000000E00");
    model.component("comp1").physics("plas").feature("Hn2").set("aLo2", 7.05332819E-13);
    model.component("comp1").physics("plas").feature("Hn2").set("aLo3", -1.99591964E-15);
    model.component("comp1").physics("plas").feature("Hn2").set("aLo4", 2.30081632E-18);
    model.component("comp1").physics("plas").feature("Hn2").set("aLo5", -9.27732332E-22);
    model.component("comp1").physics("plas").feature("Hn2").set("aLo6", "2.54736599E04");
    model.component("comp1").physics("plas").feature("Hn2").set("aLo7", "-4.46682853E-01");
    model.component("comp1").physics("plas").feature("Hn2").set("aHi1", "2.50000001E00");
    model.component("comp1").physics("plas").feature("Hn2").set("aHi2", -2.30842973E-11);
    model.component("comp1").physics("plas").feature("Hn2").set("aHi3", 1.61561948E-14);
    model.component("comp1").physics("plas").feature("Hn2").set("aHi4", -4.73515235E-18);
    model.component("comp1").physics("plas").feature("Hn2").set("aHi5", 4.98197357E-22);
    model.component("comp1").physics("plas").feature("Hn2").set("aHi6", "2.54736599E04");
    model.component("comp1").physics("plas").feature("Hn2").set("aHi7", "-4.46682914E-01");
    model.component("comp1").physics("plas").feature("Hn2").label("\u7269\u8d28: Hn2");
    model.component("comp1").physics("plas").feature("Hn3").set("specName", "Hn3");
    model.component("comp1").physics("plas").feature("Hn3").set("specLabel", "Hn3");
    model.component("comp1").physics("plas").feature("Hn3").set("sisDef", true);
    model.component("comp1").physics("plas").feature("Hn3").set("M", 0.00101);
    model.component("comp1").physics("plas").feature("Hn3").set("sigma", "2.050e-10");
    model.component("comp1").physics("plas").feature("Hn3").set("epsilonkb", "145.000");
    model.component("comp1").physics("plas").feature("Hn3").set("mu", "0.000");
    model.component("comp1").physics("plas").feature("Hn3").set("x0", "1E-10");
    model.component("comp1").physics("plas").feature("Hn3").set("Tlo", 200);
    model.component("comp1").physics("plas").feature("Hn3").set("Tmid", 1000);
    model.component("comp1").physics("plas").feature("Hn3").set("Thi", 3500);
    model.component("comp1").physics("plas").feature("Hn3").set("hadd", 12.0939);
    model.component("comp1").physics("plas").feature("Hn3").set("aLo1", "2.50000000E00");
    model.component("comp1").physics("plas").feature("Hn3").set("aLo2", 7.05332819E-13);
    model.component("comp1").physics("plas").feature("Hn3").set("aLo3", -1.99591964E-15);
    model.component("comp1").physics("plas").feature("Hn3").set("aLo4", 2.30081632E-18);
    model.component("comp1").physics("plas").feature("Hn3").set("aLo5", -9.27732332E-22);
    model.component("comp1").physics("plas").feature("Hn3").set("aLo6", "2.54736599E04");
    model.component("comp1").physics("plas").feature("Hn3").set("aLo7", "-4.46682853E-01");
    model.component("comp1").physics("plas").feature("Hn3").set("aHi1", "2.50000001E00");
    model.component("comp1").physics("plas").feature("Hn3").set("aHi2", -2.30842973E-11);
    model.component("comp1").physics("plas").feature("Hn3").set("aHi3", 1.61561948E-14);
    model.component("comp1").physics("plas").feature("Hn3").set("aHi4", -4.73515235E-18);
    model.component("comp1").physics("plas").feature("Hn3").set("aHi5", 4.98197357E-22);
    model.component("comp1").physics("plas").feature("Hn3").set("aHi6", "2.54736599E04");
    model.component("comp1").physics("plas").feature("Hn3").set("aHi7", "-4.46682914E-01");
    model.component("comp1").physics("plas").feature("Hn3").label("\u7269\u8d28: Hn3");
    model.component("comp1").physics("plas").feature("H_1p").set("specName", "H_1p");
    model.component("comp1").physics("plas").feature("H_1p").set("specLabel", "H+");
    model.component("comp1").physics("plas").feature("H_1p").set("sType", "ion");
    model.component("comp1").physics("plas").feature("H_1p").set("sisDef", true);
    model.component("comp1").physics("plas").feature("H_1p").set("M", 0.00101);
    model.component("comp1").physics("plas").feature("H_1p").set("sigma", "2.050e-10");
    model.component("comp1").physics("plas").feature("H_1p").set("epsilonkb", "145.000");
    model.component("comp1").physics("plas").feature("H_1p").set("mu", "0.000");
    model.component("comp1").physics("plas").feature("H_1p").set("z", 1);
    model.component("comp1").physics("plas").feature("H_1p").set("n0", "1E7[1/m^3]");
    model.component("comp1").physics("plas").feature("H_1p").set("Tlo", 200);
    model.component("comp1").physics("plas").feature("H_1p").set("Tmid", 1000);
    model.component("comp1").physics("plas").feature("H_1p").set("Thi", 3500);
    model.component("comp1").physics("plas").feature("H_1p").set("hadd", "1.360570e+1");
    model.component("comp1").physics("plas").feature("H_1p").set("aLo1", "2.50000000E00");
    model.component("comp1").physics("plas").feature("H_1p").set("aLo2", 7.05332819E-13);
    model.component("comp1").physics("plas").feature("H_1p").set("aLo3", -1.99591964E-15);
    model.component("comp1").physics("plas").feature("H_1p").set("aLo4", 2.30081632E-18);
    model.component("comp1").physics("plas").feature("H_1p").set("aLo5", -9.27732332E-22);
    model.component("comp1").physics("plas").feature("H_1p").set("aLo6", "2.54736599E04");
    model.component("comp1").physics("plas").feature("H_1p").set("aLo7", "-4.46682853E-01");
    model.component("comp1").physics("plas").feature("H_1p").set("aHi1", "2.50000001E00");
    model.component("comp1").physics("plas").feature("H_1p").set("aHi2", -2.30842973E-11);
    model.component("comp1").physics("plas").feature("H_1p").set("aHi3", 1.61561948E-14);
    model.component("comp1").physics("plas").feature("H_1p").set("aHi4", -4.73515235E-18);
    model.component("comp1").physics("plas").feature("H_1p").set("aHi5", 4.98197357E-22);
    model.component("comp1").physics("plas").feature("H_1p").set("aHi6", "2.54736599E04");
    model.component("comp1").physics("plas").feature("H_1p").set("aHi7", "-4.46682914E-01");
    model.component("comp1").physics("plas").feature("H_1p").label("\u7269\u8d28: H+");
    model.component("comp1").physics("plas").feature("H2").set("specName", "H2");
    model.component("comp1").physics("plas").feature("H2").set("specLabel", "H2");
    model.component("comp1").physics("plas").feature("H2").set("FromMassConstraint", true);
    model.component("comp1").physics("plas").feature("H2").set("sisDef", true);
    model.component("comp1").physics("plas").feature("H2").set("M", 0.00202);
    model.component("comp1").physics("plas").feature("H2").set("sigma", "2.920e-10");
    model.component("comp1").physics("plas").feature("H2").set("epsilonkb", "38.0000");
    model.component("comp1").physics("plas").feature("H2").set("mu", "0.000");
    model.component("comp1").physics("plas").feature("H2").set("Tlo", 200);
    model.component("comp1").physics("plas").feature("H2").set("Tmid", 1000);
    model.component("comp1").physics("plas").feature("H2").set("Thi", 3500);
    model.component("comp1").physics("plas").feature("H2").set("aLo1", "2.34433112E00");
    model.component("comp1").physics("plas").feature("H2").set("aLo2", "7.98052075E-03");
    model.component("comp1").physics("plas").feature("H2").set("aLo3", "-1.94781510E-05");
    model.component("comp1").physics("plas").feature("H2").set("aLo4", "2.01572094E-08");
    model.component("comp1").physics("plas").feature("H2").set("aLo5", -7.37611761E-12);
    model.component("comp1").physics("plas").feature("H2").set("aLo6", "-9.17935173E02");
    model.component("comp1").physics("plas").feature("H2").set("aLo7", "6.83010238E-01");
    model.component("comp1").physics("plas").feature("H2").set("aHi1", "3.33727920E00");
    model.component("comp1").physics("plas").feature("H2").set("aHi2", "-4.94024731E-05");
    model.component("comp1").physics("plas").feature("H2").set("aHi3", "4.99456778E-07");
    model.component("comp1").physics("plas").feature("H2").set("aHi4", -1.79566394E-10);
    model.component("comp1").physics("plas").feature("H2").set("aHi5", 2.00255376E-14);
    model.component("comp1").physics("plas").feature("H2").set("aHi6", "-9.50158922E02");
    model.component("comp1").physics("plas").feature("H2").set("aHi7", "-3.20502331E001");
    model.component("comp1").physics("plas").feature("H2").label("\u7269\u8d28: H2");
    model.component("comp1").physics("plas").feature("H2_1p").set("specName", "H2_1p");
    model.component("comp1").physics("plas").feature("H2_1p").set("specLabel", "H2+");
    model.component("comp1").physics("plas").feature("H2_1p").set("sType", "ion");
    model.component("comp1").physics("plas").feature("H2_1p").set("sisDef", true);
    model.component("comp1").physics("plas").feature("H2_1p").set("M", 0.00202);
    model.component("comp1").physics("plas").feature("H2_1p").set("sigma", "2.920e-10");
    model.component("comp1").physics("plas").feature("H2_1p").set("epsilonkb", "38.0000");
    model.component("comp1").physics("plas").feature("H2_1p").set("mu", "0.000");
    model.component("comp1").physics("plas").feature("H2_1p").set("z", 1);
    model.component("comp1").physics("plas").feature("H2_1p").set("n0", "1E7[1/m^3]");
    model.component("comp1").physics("plas").feature("H2_1p").set("Tlo", 200);
    model.component("comp1").physics("plas").feature("H2_1p").set("Tmid", 1000);
    model.component("comp1").physics("plas").feature("H2_1p").set("Thi", 3500);
    model.component("comp1").physics("plas").feature("H2_1p").set("hadd", "1.540000e+1");
    model.component("comp1").physics("plas").feature("H2_1p").set("aLo1", "2.34433112E00");
    model.component("comp1").physics("plas").feature("H2_1p").set("aLo2", "7.98052075E-03");
    model.component("comp1").physics("plas").feature("H2_1p").set("aLo3", "-1.94781510E-05");
    model.component("comp1").physics("plas").feature("H2_1p").set("aLo4", "2.01572094E-08");
    model.component("comp1").physics("plas").feature("H2_1p").set("aLo5", -7.37611761E-12);
    model.component("comp1").physics("plas").feature("H2_1p").set("aLo6", "-9.17935173E02");
    model.component("comp1").physics("plas").feature("H2_1p").set("aLo7", "6.83010238E-01");
    model.component("comp1").physics("plas").feature("H2_1p").set("aHi1", "3.33727920E00");
    model.component("comp1").physics("plas").feature("H2_1p").set("aHi2", "-4.94024731E-05");
    model.component("comp1").physics("plas").feature("H2_1p").set("aHi3", "4.99456778E-07");
    model.component("comp1").physics("plas").feature("H2_1p").set("aHi4", -1.79566394E-10);
    model.component("comp1").physics("plas").feature("H2_1p").set("aHi5", 2.00255376E-14);
    model.component("comp1").physics("plas").feature("H2_1p").set("aHi6", "-9.50158922E02");
    model.component("comp1").physics("plas").feature("H2_1p").set("aHi7", "-3.20502331E001");
    model.component("comp1").physics("plas").feature("H2_1p").label("\u7269\u8d28: H2+");
    model.component("comp1").physics("plas").feature("H3_1p").set("specName", "H3_1p");
    model.component("comp1").physics("plas").feature("H3_1p").set("specLabel", "H3+");
    model.component("comp1").physics("plas").feature("H3_1p").set("sType", "ion");
    model.component("comp1").physics("plas").feature("H3_1p").set("InitIon", true);
    model.component("comp1").physics("plas").feature("H3_1p").set("sisDef", true);
    model.component("comp1").physics("plas").feature("H3_1p").set("M", 0.00303);
    model.component("comp1").physics("plas").feature("H3_1p").set("sigma", "2.920e-10");
    model.component("comp1").physics("plas").feature("H3_1p").set("epsilonkb", "38.0000");
    model.component("comp1").physics("plas").feature("H3_1p").set("mu", "0.000");
    model.component("comp1").physics("plas").feature("H3_1p").set("z", 1);
    model.component("comp1").physics("plas").feature("H3_1p").set("Tlo", 200);
    model.component("comp1").physics("plas").feature("H3_1p").set("Tmid", 1000);
    model.component("comp1").physics("plas").feature("H3_1p").set("Thi", 3500);
    model.component("comp1").physics("plas").feature("H3_1p").set("hadd", "1.540000e+1");
    model.component("comp1").physics("plas").feature("H3_1p").set("aLo1", "2.34433112E00");
    model.component("comp1").physics("plas").feature("H3_1p").set("aLo2", "7.98052075E-03");
    model.component("comp1").physics("plas").feature("H3_1p").set("aLo3", "-1.94781510E-05");
    model.component("comp1").physics("plas").feature("H3_1p").set("aLo4", "2.01572094E-08");
    model.component("comp1").physics("plas").feature("H3_1p").set("aLo5", -7.37611761E-12);
    model.component("comp1").physics("plas").feature("H3_1p").set("aLo6", "-9.17935173E02");
    model.component("comp1").physics("plas").feature("H3_1p").set("aLo7", "6.83010238E-01");
    model.component("comp1").physics("plas").feature("H3_1p").set("aHi1", "3.33727920E00");
    model.component("comp1").physics("plas").feature("H3_1p").set("aHi2", "-4.94024731E-05");
    model.component("comp1").physics("plas").feature("H3_1p").set("aHi3", "4.99456778E-07");
    model.component("comp1").physics("plas").feature("H3_1p").set("aHi4", -1.79566394E-10);
    model.component("comp1").physics("plas").feature("H3_1p").set("aHi5", 2.00255376E-14);
    model.component("comp1").physics("plas").feature("H3_1p").set("aHi6", "-9.50158922E02");
    model.component("comp1").physics("plas").feature("H3_1p").set("aHi7", "-3.20502331E001");
    model.component("comp1").physics("plas").feature("H3_1p").label("\u7269\u8d28: H3+");
    model.component("comp1").physics("plas").feature("sr1").set("rType", "irrev");
    model.component("comp1").physics("plas").feature("sr1").set("Species", new String[][]{{"H"}, {"H2"}});
    model.component("comp1").physics("plas").feature("sr1").set("fwdSpecies", "H");
    model.component("comp1").physics("plas").feature("sr1").set("revSpecies", "H2");
    model.component("comp1").physics("plas").feature("sr1").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("sr1")
         .set("fwdArray", new int[][]{{0}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("sr1")
         .set("revArray", new double[][]{{0}, {0}, {0}, {0}, {0}, {0.5}, {0}, {0}});
    model.component("comp1").physics("plas").feature("sr1")
         .set("stoichArray", new double[][]{{0}, {-1}, {0}, {0}, {0}, {0.5}, {0}, {0}});
    model.component("comp1").physics("plas").feature("sr1").set("formula", "H=>0.5H2");
    model.component("comp1").physics("plas").feature("sr1").set("gammaf", 0.02);
    model.component("comp1").physics("plas").feature("sr1").set("gammai", "0.0");
    model.component("comp1").physics("plas").feature("sr1").set("ebari", "0.0");
    model.component("comp1").physics("plas").feature("sr1").active(true);
    model.component("comp1").physics("plas").feature("sr1").label("1: H=>0.5H2");
    model.component("comp1").physics("plas").feature("sr2").set("rType", "irrev");
    model.component("comp1").physics("plas").feature("sr2").set("Species", new String[][]{{"Hn2"}, {"H"}});
    model.component("comp1").physics("plas").feature("sr2").set("fwdSpecies", "Hn2");
    model.component("comp1").physics("plas").feature("sr2").set("revSpecies", "H");
    model.component("comp1").physics("plas").feature("sr2").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("sr2")
         .set("fwdArray", new int[][]{{0}, {0}, {-1}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("sr2")
         .set("revArray", new int[][]{{0}, {1}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("sr2")
         .set("stoichArray", new int[][]{{0}, {1}, {-1}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("sr2").set("formula", "Hn2=>H");
    model.component("comp1").physics("plas").feature("sr2").set("gammai", "0.0");
    model.component("comp1").physics("plas").feature("sr2").set("ebari", "0.0");
    model.component("comp1").physics("plas").feature("sr2").active(true);
    model.component("comp1").physics("plas").feature("sr2").label("2: Hn2=>H");
    model.component("comp1").physics("plas").feature("sr3").set("rType", "irrev");
    model.component("comp1").physics("plas").feature("sr3").set("Species", new String[][]{{"Hn3"}, {"H"}});
    model.component("comp1").physics("plas").feature("sr3").set("fwdSpecies", "Hn3");
    model.component("comp1").physics("plas").feature("sr3").set("revSpecies", "H");
    model.component("comp1").physics("plas").feature("sr3").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("sr3")
         .set("fwdArray", new int[][]{{0}, {0}, {0}, {-1}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("sr3")
         .set("revArray", new int[][]{{0}, {1}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("sr3")
         .set("stoichArray", new int[][]{{0}, {1}, {0}, {-1}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("sr3").set("formula", "Hn3=>H");
    model.component("comp1").physics("plas").feature("sr3").set("gammai", "0.0");
    model.component("comp1").physics("plas").feature("sr3").set("ebari", "0.0");
    model.component("comp1").physics("plas").feature("sr3").active(true);
    model.component("comp1").physics("plas").feature("sr3").label("3: Hn3=>H");
    model.component("comp1").physics("plas").feature("sr4").set("rType", "irrev");
    model.component("comp1").physics("plas").feature("sr4").set("Species", new String[][]{{"H_1p"}, {"H"}});
    model.component("comp1").physics("plas").feature("sr4").set("fwdSpecies", "H+");
    model.component("comp1").physics("plas").feature("sr4").set("revSpecies", "H");
    model.component("comp1").physics("plas").feature("sr4").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("sr4")
         .set("fwdArray", new int[][]{{0}, {0}, {0}, {0}, {-1}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("sr4")
         .set("revArray", new int[][]{{0}, {1}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("sr4")
         .set("stoichArray", new int[][]{{0}, {1}, {0}, {0}, {-1}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("sr4").set("formula", "H+=>H");
    model.component("comp1").physics("plas").feature("sr4").set("gammai", "0.0");
    model.component("comp1").physics("plas").feature("sr4").set("ebari", "0.0");
    model.component("comp1").physics("plas").feature("sr4").active(true);
    model.component("comp1").physics("plas").feature("sr4").label("4: H+=>H");
    model.component("comp1").physics("plas").feature("sr5").set("rType", "irrev");
    model.component("comp1").physics("plas").feature("sr5").set("Species", new String[][]{{"H2_1p"}, {"H2"}});
    model.component("comp1").physics("plas").feature("sr5").set("fwdSpecies", "H2+");
    model.component("comp1").physics("plas").feature("sr5").set("revSpecies", "H2");
    model.component("comp1").physics("plas").feature("sr5").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("sr5")
         .set("fwdArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {-1}, {0}});
    model.component("comp1").physics("plas").feature("sr5")
         .set("revArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {1}, {0}, {0}});
    model.component("comp1").physics("plas").feature("sr5")
         .set("stoichArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {1}, {-1}, {0}});
    model.component("comp1").physics("plas").feature("sr5").set("formula", "H2+=>H2");
    model.component("comp1").physics("plas").feature("sr5").set("gammai", "0.0");
    model.component("comp1").physics("plas").feature("sr5").set("ebari", "0.0");
    model.component("comp1").physics("plas").feature("sr5").active(true);
    model.component("comp1").physics("plas").feature("sr5").label("5: H2+=>H2");
    model.component("comp1").physics("plas").feature("sr6").set("rType", "irrev");
    model.component("comp1").physics("plas").feature("sr6").set("Species", new String[][]{{"H3_1p"}, {"H2"}, {"H"}});
    model.component("comp1").physics("plas").feature("sr6").set("fwdSpecies", "H3+");
    model.component("comp1").physics("plas").feature("sr6").set("revSpecies", new String[][]{{"H2"}, {"H"}});
    model.component("comp1").physics("plas").feature("sr6").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("sr6").set("formula", "H3+=>H2+H");
    model.component("comp1").physics("plas").feature("sr6").set("gammai", "0.0");
    model.component("comp1").physics("plas").feature("sr6").set("ebari", "0.0");
    model.component("comp1").physics("plas").feature("sr6").active(true);
    model.component("comp1").physics("plas").feature("sr6").label("6: H3+=>H2+H");
    model.component("comp1").physics("plas").feature("pes1")
         .set("muN", new String[][]{{"1.4E24[1/(V*m*s)]"}, {"0"}, {"0"}, {"0"}, {"1.4E24[1/(V*m*s)]"}, {"0"}, {"0"}, {"0"}, {"1.4E24[1/(V*m*s)]"}});
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "CompressibleMALT03");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("pref", "p0");
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "MassFlow");
    model.component("comp1").physics("spf").feature("inl1").set("MassFlowType", "StandardFlowRateSCCM");
    model.component("comp1").physics("spf").feature("inl1").set("sccmmfr", "Qs");
    model.component("comp1").physics("ht").feature("fluid1").set("fluidType", "gasLiquid");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "Tinit");
    model.component("comp1").physics("ht").feature("temp1").set("T0", "1200[K]");
    model.component("comp1").physics("ht").feature("temp2").set("T0", "300[K]");
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", 200);
    model.component("comp1").physics("ht").feature("hf1").set("Text", "300[K]");
    model.component("comp1").physics("emw").prop("ShapeProperty").set("order_electricfield", 1);
    model.component("comp1").physics("emw").feature("port1").set("Pin", "Pin");
    model.component("comp1").physics("emw").feature("port1").set("PortType", "Coaxial");

    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("ftri1").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("custom", "on");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("table", "plasma");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmax", 0.15);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmin", 0.0269);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hminactive", false);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hcurveactive", false);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hnarrowactive", false);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hgradactive", false);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").set("custom", "on");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").set("table", "plasma");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").set("hmax", 0.25);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").set("hmaxactive", true);

    return model;
  }

  public static Model run4(Model model) {
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").set("hmin", 0.0269);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").set("hminactive", false);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").set("hcurveactive", false);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").set("hnarrowactive", false);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").set("hgradactive", false);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size4").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size4").set("custom", "on");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size4").set("table", "plasma");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size4").set("hmax", 1.5);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size4").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size4").set("hmin", 0.0269);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size4").set("hminactive", false);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size4").set("hcurveactive", false);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size4").set("hnarrowactive", false);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size4").set("hgradactive", false);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size5").set("custom", "on");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size5").set("hmax", 0.025);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size5").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("bl1").set("smoothtransition", false);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 3);
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std1");
    model.study("std1").create("ftrans", "FrequencyTransient");
    model.study("std1").feature("ftrans").set("plot", true);
    model.study("std1").feature("ftrans")
         .set("activate", new String[]{"plas", "on", "spf", "off", "ht", "on", "emw", "on", "frame:spatial1", "on", 
         "frame:material1", "on"});
    model.study().create("std2");
    model.study("std2").create("fstat", "FrequencyStationary");
    model.study("std2").feature("fstat").set("plot", true);

    model.sol().create("sol1");
    model.sol("sol1").attach("std1");
    model.sol().create("sol2");
    model.sol("sol2").attach("std2");

    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset().create("mir1", "Mirror2D");
    model.result().dataset("rev2").set("data", "dset2");
    model.result().dataset("mir1").set("data", "dset2");
    model.result().create("pg1", "PlotGroup2D");
    model.result().create("pg2", "PlotGroup2D");
    model.result().create("pg3", "PlotGroup2D");
    model.result().create("pg4", "PlotGroup2D");
    model.result().create("pg5", "PlotGroup2D");
    model.result().create("pg6", "PlotGroup2D");
    model.result().create("pg7", "PlotGroup2D");
    model.result().create("pg8", "PlotGroup2D");
    model.result().create("pg9", "PlotGroup2D");
    model.result().create("pg10", "PlotGroup2D");
    model.result().create("pg11", "PlotGroup3D");
    model.result().create("pg12", "PlotGroup2D");
    model.result().create("pg13", "PlotGroup2D");
    model.result().create("pg14", "PlotGroup1D");
    model.result().create("pg15", "SmithGroup");
    model.result().create("pg16", "PlotGroup2D");
    model.result().create("pg17", "PlotGroup2D");
    model.result().create("pg18", "PlotGroup2D");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "plas.Te");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "V");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "T");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "emw.normE");
    model.result("pg6").set("data", "mir1");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg7").set("data", "mir1");
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", "plas.Te");
    model.result("pg8").set("data", "mir1");
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", "V");
    model.result("pg9").set("data", "mir1");
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").create("str1", "Streamline");
    model.result("pg9").feature("surf1").set("expr", "spf.U");
    model.result("pg9").feature("str1").set("expr", new String[]{"u", "w"});
    model.result("pg10").set("data", "dset2");
    model.result("pg10").create("con1", "Contour");
    model.result("pg10").feature("con1").set("expr", "p");
    model.result("pg11").set("data", "rev2");
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("expr", "spf.U");
    model.result("pg12").set("data", "dset2");
    model.result("pg12").create("surf1", "Surface");
    model.result("pg12").feature("surf1").set("expr", "T");
    model.result("pg13").set("data", "mir1");
    model.result("pg13").create("surf1", "Surface");
    model.result("pg13").feature("surf1").set("expr", "emw.normE");
    model.result("pg14").set("data", "dset2");
    model.result("pg14").create("glob1", "Global");
    model.result("pg14").feature("glob1").set("expr", new String[]{"emw.S11dB"});
    model.result("pg15").set("data", "dset2");
    model.result("pg15").create("rgr1", "ReflectionGraph");
    model.result("pg15").feature("rgr1").create("col1", "Color");
    model.result("pg15").feature("rgr1").feature("col1").set("expr", "emw.freq/1e9");
    model.result("pg16").set("data", "mir1");
    model.result("pg16").create("surf1", "Surface");
    model.result("pg16").feature("surf1").set("expr", "T");
    model.result("pg17").set("data", "mir1");
    model.result("pg17").create("surf1", "Surface");
    model.result("pg17").feature("surf1").set("expr", "plas.n_wH");
    model.result("pg18").set("data", "mir1");
    model.result("pg18").create("surf1", "Surface");
    model.result("pg18").feature("surf1").set("expr", "emw.Qrh");
    model.result("pg18").feature("surf1").create("sel1", "Selection");
    model.result("pg18").feature("surf1").feature("sel1").selection().set(1);

    model.nodeGroup().create("grp1", "Physics", "plas");
    model.nodeGroup("grp1").placeAfter("rg3");
    model.nodeGroup().create("grp2", "Physics", "plas");
    model.nodeGroup("grp2").placeAfter("rg3");
    model.nodeGroup().create("grp3", "Results");
    model.nodeGroup("grp3").set("type", "plotgroup");
    model.nodeGroup("grp3").placeAfter(null);
    model.nodeGroup().create("grp4", "Results");
    model.nodeGroup("grp4").set("type", "plotgroup");
    model.nodeGroup().move("grp4", 3);
    model.nodeGroup("grp4").placeAfter(null);

    model.study("std1").label("Pin=500 W\uff0c\u65e0\u6d41\u4f53\u6d41\u52a8");
    model.study("std1").feature("ftrans").set("tlist", "0 10^{range(log10(1.0e-9),1/3,log10(100))}");
    model.study("std1").feature("ftrans").set("freq", "f0");
    model.study("std1").feature("ftrans").set("plotfreq", "tsteps");
    model.study("std2").label("Pin=500 \u81f3 5000 W");
    model.study("std2").feature("fstat").set("freq", "f0");
    model.study("std2").feature("fstat").set("plotgroup", "pg6");
    model.study("std2").feature("fstat").set("useinitsol", true);
    model.study("std2").feature("fstat").set("initmethod", "sol");
    model.study("std2").feature("fstat").set("initstudy", "std1");
    model.study("std2").feature("fstat").set("solnum", "auto");
    model.study("std2").feature("fstat").set("useparam", true);
    model.study("std2").feature("fstat").set("pname", new String[]{"Pin"});
    model.study("std2").feature("fstat").set("plistarr", new String[]{"range(500,100,5000)"});
    model.study("std2").feature("fstat").set("punit", new String[]{"W"});
    model.study("std2").feature("fstat").set("pcontinuationmode", "no");
    model.study("std2").feature("fstat").set("preusesol", "yes");

    model.sol("sol1").createAutoSequence("std1");

    model.study("std1").runNoGen();

    model.sol("sol2").createAutoSequence("std2");

    model.study("std2").runNoGen();

    model.result().dataset("rev1").label("\u4e8c\u7ef4\u65cb\u8f6c");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev2").label("\u4e8c\u7ef4\u65cb\u8f6c 1");
    model.result().dataset("rev2").set("startangle", -90);
    model.result().dataset("rev2").set("revangle", 225);
    model.result().dataset("mir1").set("removesymelem", true);
    model.result("pg1").label("\u7535\u5b50\u5bc6\u5ea6 (plas)");
    model.result("pg1").feature("surf1").set("resolution", "normal");
    model.result("pg2").label("\u7535\u5b50\u6e29\u5ea6 (plas)");
    model.result("pg2").set("looplevel", new int[]{1});
    model.result("pg2").feature("surf1").set("resolution", "normal");
    model.result("pg3").label("\u7535\u52bf (plas)");
    model.result("pg3").set("looplevel", new int[]{1});
    model.result("pg3").feature("surf1").set("colortable", "Dipole");
    model.result("pg3").feature("surf1").set("resolution", "normal");
    model.result("pg4").label("\u6e29\u5ea6 (ht)");
    model.result("pg4").set("looplevel", new int[]{1});
    model.result("pg4").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg4").feature("surf1").set("resolution", "normal");
    model.result("pg5").label("\u7535\u573a (emw)");
    model.result("pg5").set("looplevel", new int[]{1});
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").feature("surf1").label("\u8868\u9762");
    model.result("pg5").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg5").feature("surf1").set("smooth", "internal");
    model.result("pg5").feature("surf1").set("resolution", "normal");
    model.result("pg6").label("\u7535\u5b50\u5bc6\u5ea6 (plas) 1");
    model.result("pg6").set("looplevel", new int[]{46});
    model.result("pg6").feature("surf1").set("resolution", "normal");
    model.result("pg7").label("\u7535\u5b50\u6e29\u5ea6 (plas) 1");
    model.result("pg7").set("looplevel", new int[]{46});
    model.result("pg7").feature("surf1").set("resolution", "normal");
    model.result("pg8").label("\u7535\u52bf (plas) 1");
    model.result("pg8").set("looplevel", new int[]{46});
    model.result("pg8").feature("surf1").set("colortable", "Dipole");
    model.result("pg8").feature("surf1").set("resolution", "normal");
    model.result("pg9").label("\u901f\u5ea6 (spf)");
    model.result("pg9").set("looplevel", new int[]{46});
    model.result("pg9").set("frametype", "spatial");
    model.result("pg9").feature("surf1").label("\u8868\u9762");
    model.result("pg9").feature("surf1").set("smooth", "internal");
    model.result("pg9").feature("surf1").set("resolution", "normal");
    model.result("pg9").feature("str1").set("posmethod", "uniform");
    model.result("pg9").feature("str1").set("udist", 0.02);
    model.result("pg9").feature("str1").set("pointtype", "arrow");
    model.result("pg9").feature("str1").set("arrowcount", 54);
    model.result("pg9").feature("str1").set("arrowscale", 117.27825626043378);
    model.result("pg9").feature("str1").set("color", "gray");
    model.result("pg9").feature("str1").set("arrowcountactive", false);
    model.result("pg9").feature("str1").set("arrowscaleactive", false);
    model.result("pg9").feature("str1").set("resolution", "normal");
    model.result("pg10").label("\u538b\u529b (spf)");
    model.result("pg10").set("looplevel", new int[]{46});
    model.result("pg10").set("frametype", "spatial");
    model.result("pg10").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg10").feature("con1").set("number", 40);
    model.result("pg10").feature("con1").set("levelrounding", false);
    model.result("pg10").feature("con1").set("colortable", "Rainbow");
    model.result("pg10").feature("con1").set("smooth", "internal");
    model.result("pg10").feature("con1").set("resolution", "normal");
    model.result("pg11").label("\u4e09\u7ef4\u901f\u5ea6 (spf)");
    model.result("pg11").set("looplevel", new int[]{46});
    model.result("pg11").set("frametype", "spatial");
    model.result("pg11").feature("surf1").label("\u8868\u9762");
    model.result("pg11").feature("surf1").set("colortable", "Rainbow");
    model.result("pg11").feature("surf1").set("smooth", "internal");
    model.result("pg11").feature("surf1").set("resolution", "normal");
    model.result("pg12").label("\u6e29\u5ea6 (ht) 1");
    model.result("pg12").set("looplevel", new int[]{46});
    model.result("pg12").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg12").feature("surf1").set("resolution", "normal");
    model.result("pg13").label("\u7535\u573a (emw) 1");
    model.result("pg13").set("looplevel", new int[]{46});
    model.result("pg13").set("frametype", "spatial");
    model.result("pg13").set("showlegendsmaxmin", true);
    model.result("pg13").feature("surf1").label("\u8868\u9762");
    model.result("pg13").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg13").feature("surf1").set("smooth", "internal");
    model.result("pg13").feature("surf1").set("resolution", "normal");
    model.result("pg14").label("S \u53c2\u6570 (emw)");
    model.result("pg14").set("ylabel", "S \u53c2\u6570 (dB)");
    model.result("pg14").set("ylabelactive", true);
    model.result("pg14").feature("glob1").set("titletype", "none");
    model.result("pg14").feature("glob1").set("xdata", "expr");
    model.result("pg14").feature("glob1").set("xdataexpr", "Pin");
    model.result("pg14").feature("glob1").set("xdataunit", "W");
    model.result("pg14").feature("glob1").set("xdatadescr", "\u8f93\u5165\u529f\u7387");
    model.result("pg15").label("\u53f2\u5bc6\u65af\u56fe (emw)");
    model.result("pg15").feature("rgr1").set("titletype", "manual");
    model.result("pg15").feature("rgr1")
         .set("title", "\u53cd\u5c04\u56fe: S \u53c2\u6570, \u989c\u8272: \u9891\u7387 (GHz)");
    model.result("pg15").feature("rgr1").set("linemarker", "point");
    model.result("pg15").feature("rgr1").set("markerpos", "datapoints");
    model.result("pg15").feature("rgr1").feature("col1").set("colortable", "Spectrum");
    model.result("pg16").label("\u6e29\u5ea6\uff0c\u4e8c\u7ef4");
    model.result("pg16").set("looplevel", new int[]{46});
    model.result("pg16").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg16").feature("surf1").set("resolution", "normal");
    model.result("pg17").label("H \u6570\u5bc6\u5ea6");
    model.result("pg17").set("looplevel", new int[]{46});
    model.result("pg17").feature("surf1").set("resolution", "normal");
    model.result("pg18").label("\u5438\u6536\u529f\u7387");
    model.result("pg18").set("looplevel", new int[]{46});
    model.result("pg18").feature("surf1").set("colortable", "ThermalWave");
    model.result("pg18").feature("surf1").set("resolution", "normal");

    model.nodeGroup("grp1").add("e");
    model.nodeGroup("grp1").add("H");
    model.nodeGroup("grp1").add("Hn2");
    model.nodeGroup("grp1").add("Hn3");
    model.nodeGroup("grp1").add("H_1p");
    model.nodeGroup("grp1").add("H2");
    model.nodeGroup("grp1").add("H2_1p");
    model.nodeGroup("grp1").add("H3_1p");
    model.nodeGroup("grp2").label("\u8868\u9762\u53cd\u5e94");
    model.nodeGroup("grp2").add("sr1");
    model.nodeGroup("grp2").add("sr2");
    model.nodeGroup("grp2").add("sr3");
    model.nodeGroup("grp2").add("sr4");
    model.nodeGroup("grp2").add("sr5");
    model.nodeGroup("grp2").add("sr6");
    model.nodeGroup("grp3").label("Pin=500 W\uff0c\u65e0\u6d41\u4f53\u6d41\u52a8");
    model.nodeGroup("grp3").add("plotgroup", "pg1");
    model.nodeGroup("grp3").add("plotgroup", "pg2");
    model.nodeGroup("grp3").add("plotgroup", "pg3");
    model.nodeGroup("grp3").add("plotgroup", "pg4");
    model.nodeGroup("grp3").add("plotgroup", "pg5");
    model.nodeGroup("grp4").label("Pin=500 \u81f3 5000 W");
    model.nodeGroup("grp4").add("plotgroup", "pg6");
    model.nodeGroup("grp4").add("plotgroup", "pg7");
    model.nodeGroup("grp4").add("plotgroup", "pg8");
    model.nodeGroup("grp4").add("plotgroup", "pg9");
    model.nodeGroup("grp4").add("plotgroup", "pg10");
    model.nodeGroup("grp4").add("plotgroup", "pg11");
    model.nodeGroup("grp4").add("plotgroup", "pg12");
    model.nodeGroup("grp4").add("plotgroup", "pg13");
    model.nodeGroup("grp4").add("plotgroup", "pg14");
    model.nodeGroup("grp4").add("plotgroup", "pg15");
    model.nodeGroup("grp4").add("plotgroup", "pg16");
    model.nodeGroup("grp4").add("plotgroup", "pg17");
    model.nodeGroup("grp4").add("plotgroup", "pg18");

    model.title("\u5fae\u6ce2\u8154\u7b49\u79bb\u5b50\u4f53\u53cd\u5e94\u5668");

    model
         .description("\u672c\u6559\u5b66\u6a21\u578b\u6c42\u89e3\u5fae\u6ce2\u8154\u4e2d\u4ea7\u751f\u7684\u6c22\u7b49\u79bb\u5b50\u4f53\uff0c\u5e76\u81ea\u6d3d\u5730\u8ba1\u7b97\u6d41\u4f53\u6d41\u52a8\u548c\u6c14\u4f53\u52a0\u70ed\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("microwave_cavity_plasma.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    model = run3(model);
    run4(model);
  }

}
