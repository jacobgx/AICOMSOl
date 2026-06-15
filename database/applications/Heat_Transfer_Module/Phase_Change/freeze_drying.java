/*
 * freeze_drying.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:25 by COMSOL 6.3.0.290. */
public class freeze_drying {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Phase_Change");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("dl", "PorousMediaFlowDarcy", "geom1");
    model.component("comp1").physics().create("ht", "PorousMediaHeatTransfer", "geom1");
    model.component("comp1").physics().create("dg", "DeformedGeometry", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/dl", true);
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);
    model.study("std1").feature("time").setSolveFor("/physics/dg", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("rho_p", "498[kg/m^3]", "\u4ea7\u7269\u5bc6\u5ea6");
    model.param().set("Cp_p", "2595[J/kg/K]", "\u4ea7\u7269\u70ed\u5bb9");
    model.param().set("k_p", "0.027[W/(m*K)]", "\u4ea7\u7269\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("por_p", "0.709", "\u4ea7\u7269\u5b54\u9699\u7387");
    model.param().set("theta_p", "1-por_p", "\u4ea7\u7269\u4f53\u79ef\u5206\u6570");
    model.param().set("kappa_p", "3.62e-10[m^2]", "\u4ea7\u7269\u6e17\u900f\u7387");
    model.param().set("rho_ice", "913[kg/m^3]", "\u51b0\u7684\u5bc6\u5ea6");
    model.param().set("Cp_ice", "1967.8[J/kg/K]", "\u51b0\u7684\u70ed\u5bb9");
    model.param().set("k_ice", "2.1[W/m/K]", "\u51b0\u7684\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("M_v", "18[g/mol]", "\u84b8\u6c7d\u6469\u5c14\u8d28\u91cf");
    model.param().set("Cp_v", "1674.7[J/kg/K]", "\u84b8\u6c7d\u70ed\u5bb9");
    model.param().set("k_v", "0.025[W/m/K]", "\u84b8\u6c7d\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("mu_v", "8.36e-6[Pa*s]", "\u84b8\u6c7d\u9ecf\u5ea6");
    model.param().set("DelHs", "2.7912[MJ/kg]", "\u5347\u534e\u6f5c\u70ed");
    model.param().set("Pc", "24[Pa]", "\u771f\u7a7a\u5ba4\u538b\u529b");
    model.param().set("Ti", "241.8[K]", "\u521d\u59cb\u6e29\u5ea6");
    model.param().set("Ts", "263.15[K]", "\u68da\u677f\u6e29\u5ea6");
    model.param().set("Ta", "303.15[K]", "\u73af\u5883\u6e29\u5ea6");
    model.param().set("Z0", "2[cm]", "\u5c0f\u74f6\u9ad8\u5ea6");
    model.param().set("Zi", "0.04[cm]", "\u521d\u59cb\u51b0\u9699\u4e0e\u5c0f\u74f6\u9ad8\u5ea6");
    model.param().set("R0", "1[cm]", "\u5c0f\u74f6\u534a\u5f84");

    model.material().create("mat1", "Common", "");
    model.material("mat1").label("\u84b8\u6c7d");
    model.material().create("mat2", "Common", "");
    model.material("mat2").label("\u51b0");
    model.material().create("mat3", "Common", "");
    model.material("mat3").label("\u4ea7\u54c1\uff08\u8131\u8102\u725b\u5976\uff09");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "R0");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "Z0");
    model.component("comp1").geom("geom1").feature("cyl1").setIndex("layer", "Zi", 0);
    model.component("comp1").geom("geom1").feature("cyl1").set("layerside", false);
    model.component("comp1").geom("geom1").feature("cyl1").set("layertop", true);
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "R0");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "Z0");
    model.component("comp1").geom("geom1").feature("cyl2").setIndex("layer", "R0/2", 0);
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"R0*5", "R0*2", "Z0"});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"-R0*2", "-R0*2", "0"});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("cyl1", "cyl2");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("blk1");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").label("\u754c\u9762");
    model.component("comp1").variable("var1").set("T_s", "2.19e-3*DelHs*1[kg/J]/(28.89-log(p*1[1/Pa]))*1[K]");
    model.component("comp1").variable("var1").descr("T_s", "\u754c\u9762\u6e29\u5ea6");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().set(1, 3, 5);
    model.component("comp1").cpl().create("minop1", "Minimum");
    model.component("comp1").cpl("minop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("minop1").selection().set(6, 13, 20);

    model.component("comp1").material().create("pmat1", "PorousMedia");
    model.component("comp1").material().create("pmat2", "PorousMedia");
    model.component("comp1").material("pmat2").selection().set(1, 3, 5);

    model.component("comp1").physics("dl").selection().set(2, 4, 6);
    model.component("comp1").physics("dl").prop("EquationForm").setIndex("form", "Stationary", 0);
    model.component("comp1").physics("dl").prop("PhysicalModelProperty").set("pref", 0);
    model.component("comp1").physics("dl").prop("ShapeProperty").set("boundaryFlux_pressure", true);
    model.component("comp1").physics("dl").feature("porous1").feature("fluid1").set("fluidType", "idealGas");
    model.component("comp1").physics("dl").feature("porous1").feature("fluid1").set("gasConstantType", "numberAve");
    model.component("comp1").physics("dl").feature("init1").set("p", "Pc");
    model.component("comp1").physics("dl").create("pr1", "Pressure", 2);
    model.component("comp1").physics("dl").feature("pr1").selection().set(7, 14, 21);
    model.component("comp1").physics("dl").feature("pr1").set("p0", "Pc");
    model.component("comp1").physics("dl").create("mf1", "MassFlux", 2);
    model.component("comp1").physics("dl").feature("mf1").selection().set(6, 13, 20);
    model.component("comp1").physics("dl").feature("mf1").set("N0", "-ht.pci1.vn*rho_ice*por_p");
    model.component("comp1").physics("dl").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("dl").feature("sym1").selection().set(4, 12, 25);
    model.component("comp1").physics("ht").feature("porous1").label("\u5e72\u71e5\u5c42");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1").set("u_src", "root.comp1.dl.u");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1").set("fluidType", "idealGas");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1").set("gasConstantType", "numberAve");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1")
         .set("porousMatrixPropertiesType", "solidPhaseProperties");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "Ti");
    model.component("comp1").physics("ht").create("porous2", "PorousMediumHeatTransferModel", 3);
    model.component("comp1").physics("ht").feature("porous2").label("\u51bb\u7ed3\u5c42");
    model.component("comp1").physics("ht").feature("porous2").selection().set(1, 3, 5);
    model.component("comp1").physics("ht").feature("porous2").feature("pm1")
         .set("porousMatrixPropertiesType", "solidPhaseProperties");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf1").label("\u73af\u5883\u70ed\u901a\u91cf");
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", "3.6[W/(m^2*K)]");
    model.component("comp1").physics("ht").feature("hf1").set("Text", "Ta");
    model.component("comp1").physics("ht").feature("hf1").selection().set(2, 5, 7, 14, 21, 22, 23);
    model.component("comp1").physics("ht").create("sar1", "SurfaceToAmbientRadiation", 2);
    model.component("comp1").physics("ht").feature("sar1").selection().set(7, 14, 21);
    model.component("comp1").physics("ht").feature("sar1").set("epsilon_rad_mat", "userdef");
    model.component("comp1").physics("ht").feature("sar1").set("epsilon_rad", 0.9);
    model.component("comp1").physics("ht").feature("sar1").set("Tamb", "Ta");
    model.component("comp1").physics("ht").create("hf2", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf2")
         .label("\u6401\u67b6\u70ed\u901a\u91cf\uff08\u4e2d\u5fc3\uff09");
    model.component("comp1").physics("ht").feature("hf2").selection().set(10);
    model.component("comp1").physics("ht").feature("hf2").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf2").set("h", "11[W/(m^2*K)]");
    model.component("comp1").physics("ht").feature("hf2").set("Text", "Ts");
    model.component("comp1").physics("ht").create("hf3", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf3")
         .label("\u6401\u67b6\u70ed\u901a\u91cf\uff08\u5916\u90e8\uff09");
    model.component("comp1").physics("ht").feature("hf3").selection().set(3, 17);
    model.component("comp1").physics("ht").feature("hf3").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf3").set("h", "62.3[W/(m^2*K)]");
    model.component("comp1").physics("ht").feature("hf3").set("Text", "Ts");
    model.component("comp1").physics("ht").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("ht").feature("sym1").selection().set(1, 4, 9, 12, 24, 25);
    model.component("comp1").physics("ht").create("pci1", "PhaseChangeInterface", 2);
    model.component("comp1").physics("ht").feature("pci1").selection().set(6, 13, 20);
    model.component("comp1").physics("ht").feature("pci1").set("Tpc", "T_s");
    model.component("comp1").physics("ht").feature("pci1").set("L", "DelHs");
    model.component("comp1").physics("ht").feature("pci1").set("SolidSideType", "Downside");
    model.component("comp1").physics("dg").prop("FrameSettings").set("geometryShapeOrder", 1);
    model.component("comp1").physics("dg").prop("FreeDeformationSettings").set("smoothingType", "hyperelastic");
    model.component("comp1").physics("dg").create("free1", "FreeDeformation", 3);
    model.component("comp1").physics("dg").feature("free1").selection().all();
    model.component("comp1").physics("dg").create("disp2", "PrescribedMeshDisplacement", 2);
    model.component("comp1").physics("dg").feature("disp2").selection().set(1, 2, 4, 5, 9, 12, 22, 23, 24, 25);
    model.component("comp1").physics("dg").feature("disp2").setIndex("useDx", 0, 2);

    model.component("comp1").material("pmat1").propertyGroup("def")
         .set("hydraulicpermeability", new String[]{"kappa_p"});
    model.component("comp1").material("pmat1").feature().create("fluid1", "Fluid", "comp1");
    model.component("comp1").material("pmat1").feature().create("solid1", "Solid", "comp1");
    model.component("comp1").material("pmat1").feature("solid1").set("link", "mat3");
    model.component("comp1").material("pmat1").feature("solid1").set("vfrac", "1-por_p");
    model.component("comp1").material("pmat2").feature().create("solid1", "Solid", "comp1");
    model.component("comp1").material("pmat2").feature().create("fluid1", "Fluid", "comp1");
    model.component("comp1").material("pmat2").feature("fluid1").set("link", "mat2");
    model.component("comp1").material("pmat2").feature("solid1").set("link", "mat3");
    model.component("comp1").material("pmat2").feature("solid1").set("vfrac", "1-por_p");
    model.material("mat1").propertyGroup("def").set("molarmass", new String[]{"M_v"});
    model.material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"mu_v"});
    model.material("mat1").propertyGroup("def").set("thermalconductivity", new String[]{"k_v"});
    model.material("mat1").propertyGroup("def").set("heatcapacity", new String[]{"Cp_v"});
    model.material("mat2").propertyGroup("def").set("thermalconductivity", new String[]{"k_ice"});
    model.material("mat2").propertyGroup("def").set("density", new String[]{"rho_ice"});
    model.material("mat2").propertyGroup("def").set("heatcapacity", new String[]{"Cp_ice"});
    model.material("mat3").propertyGroup("def").set("thermalconductivity", new String[]{"k_p"});
    model.material("mat3").propertyGroup("def").set("heatcapacity", new String[]{"Cp_p"});
    model.material("mat3").propertyGroup("def").set("density", new String[]{"rho_p"});

    model.component("comp1").mesh("mesh1").autoMeshSize(4);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("ftet1").active(false);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(7, 14, 21);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(8, 29);
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set(7, 21);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().set(8, 29);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 4);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection("sourceface").set(7, 14, 21);
    model.component("comp1").mesh("mesh1").feature("swe1").selection("targetface").set(3, 10, 17);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").selection().set(2, 4, 6);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 16);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").selection().set(1, 3, 5);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("numelem", 8);

    model.study("std1").feature("time").set("tunit", "h");
    model.study("std1").feature("time").set("tlist", "range(0,0.1,0.5) range(0.5,0.5,24)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").create("st1", "StopCondition");
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "comp1.minop1(z)/Z0-0.03", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "negative", 0);
    model.sol("sol1").feature("t1").feature("st1")
         .setIndex("stopconddesc", "\u754c\u9762\u63a5\u8fd1\u5bb9\u5668\u5e95\u90e8", 0);
    model.sol("sol1").feature("t1").feature("st1").set("storestopcondsol", "stepafter");
    model.sol("sol1").feature("t1").feature("st1").set("stopcondwarn", false);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", "0.9");
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "once");
    model.sol("sol1").feature("t1").feature("fc1").set("stabacc", "aacc");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u901f\u5ea6 (dl)");
    model.result("pg1").set("titletype", "custom");
    model.result("pg1").feature().create("str1", "Streamline");
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("posmethod", "start");
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg1").feature("str1").set("smooth", "internal");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("data", "parent");
    model.result("pg1").feature("str1").selection().geom("geom1", 2);
    model.result("pg1").feature("str1").selection().set(4, 5, 6, 7, 11, 12, 13, 14, 18, 19, 20, 21, 23, 25);
    model.result("pg1").feature("str1").feature().create("col1", "Color");
    model.result("pg1").feature("str1").feature("col1").set("expr", "dl.U");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u538b\u529b (dl)");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u6e29\u5ea6 (ht)");
    model.result("pg3").feature().create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("solutionparams", "parent");
    model.result("pg3").feature("vol1").set("expr", "T");
    model.result("pg3").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("vol1").set("smooth", "internal");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("data", "parent");
    model.result("pg1").run();
    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset("mir1").set("quickplane", "xz");
    model.result().dataset().create("cpt1", "CutPoint3D");
    model.result().dataset("cpt1").set("pointx", 0);
    model.result().dataset("cpt1").set("pointy", 0);
    model.result().dataset("cpt1").set("pointz", "range(Z0,-Z0/6,0)");
    model.result().dataset("cpt1").set("snapping", "boundary");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("\u7f51\u683c");
    model.result("pg4").create("mesh1", "Mesh");
    model.result("pg4").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg4").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg4").feature("mesh1").set("colortable", "AuroraBorealis");
    model.result("pg4").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg4").feature("mesh1").feature("sel1").selection().set(1, 2, 3, 6, 9, 10, 13, 17, 20, 22, 24);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u521d\u59cb\u7f51\u683c");
    model.result("pg5").set("looplevel", new int[]{1});
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").run();
    model.result("pg6").label("\u5347\u534e\u754c\u9762");
    model.result("pg6").set("data", "mir1");
    model.result("pg6").set("titletype", "manual");
    model.result("pg6")
         .set("title", "\u5de6\uff1a\u6700\u7ec8\u754c\u9762\u4f4d\u7f6e\uff1b\u53f3\uff1a\u6e29\u5ea6\u548c\u603b\u70ed\u901a\u91cf");
    model.result("pg6").create("slc1", "Slice");
    model.result("pg6").feature("slc1").set("expr", "T");
    model.result("pg6").feature("slc1").set("quickxnumber", 1);
    model.result("pg6").feature("slc1").set("colortable", "HeatCameraLight");
    model.result("pg6").run();
    model.result("pg6").create("iso1", "Isosurface");
    model.result("pg6").feature("iso1").set("expr", "Zg");
    model.result("pg6").feature("iso1").set("levelmethod", "levels");
    model.result("pg6").feature("iso1").set("levels", "Z0-Zi");
    model.result("pg6").feature("iso1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg6").feature("iso1").set("colorlegend", false);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").create("str1", "Streamline");
    model.result("pg6").feature("str1").selection().set(1, 2, 3, 6, 9, 10, 13, 17, 20, 22, 24);
    model.result("pg6").feature("str1").set("expr", new String[]{"ht.tfluxx", "ht.tfluxy", "ht.tfluxz"});
    model.result("pg6").feature("str1")
         .set("descr", "\u603b\u70ed\u901a\u91cf \uff08\u7a7a\u95f4\u548c\u6750\u6599\u5750\u6807\u7cfb\uff09");
    model.result("pg6").feature("str1").set("posmethod", "magnitude");
    model.result("pg6").feature("str1").set("linetype", "tube");
    model.result("pg6").feature("str1").create("col1", "Color");
    model.result("pg6").run();
    model.result("pg6").feature("str1").feature("col1").set("expr", "T");
    model.result("pg6").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg6").feature("str1").feature("col1").set("colortable", "HeatCameraLight");
    model.result("pg6").run();
    model.result("pg6").create("line1", "Line");
    model.result("pg6").feature("line1").set("expr", "1");
    model.result("pg6").feature("line1").set("coloring", "uniform");
    model.result("pg6").feature("line1").set("color", "gray");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").create("vol1", "Volume");
    model.result("pg6").feature("vol1").set("data", "dset1");
    model.result("pg6").feature("vol1").set("expr", "dom==1||dom==3||dom==5");
    model.result("pg6").feature("vol1").set("colortable", "AuroraBorealis");
    model.result("pg6").feature("vol1").set("solutionparams", "parent");
    model.result("pg6").feature("vol1").set("colorlegend", false);
    model.result("pg6").feature("vol1").create("trn1", "Transformation");
    model.result("pg6").run();
    model.result("pg6").feature("vol1").feature("trn1").set("move", new String[]{"-2.5*R0", "0", "0"});
    model.result("pg6").feature("vol1").feature("trn1").set("applytodatasetedges", false);
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u6e29\u5ea6\u5386\u53f2");
    model.result("pg7").set("data", "none");
    model.result("pg7").set("titletype", "manual");
    model.result("pg7")
         .set("title", "\u5bb9\u5668\u4e2d\u5fc3\u4e03\u4e2a\u4e0d\u540c\u9ad8\u5ea6\u7684\u6e29\u5ea6\u5386\u53f2");
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("xlabel", "\u65f6\u95f4 (h)");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "\u6e29\u5ea6 (K)");
    model.result("pg7").set("legendpos", "upperleft");
    model.result("pg7").create("ptgr1", "PointGraph");
    model.result("pg7").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg7").feature("ptgr1").set("linewidth", "preference");
    model.result("pg7").feature("ptgr1").set("data", "cpt1");
    model.result("pg7").feature("ptgr1").set("expr", "T");
    model.result("pg7").run();
    model.result("pg7").feature("ptgr1").set("linewidth", 2);
    model.result("pg7").feature("ptgr1").set("legend", true);
    model.result("pg7").feature("ptgr1").set("legendmethod", "evaluated");
    model.result("pg7").feature("ptgr1").set("legendpattern", "z/Z0=eval(z/Z0)");
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u51b0\u8d28\u91cf");
    model.result("pg8").set("titletype", "manual");
    model.result("pg8").set("title", "\u51b0\u7684\u5269\u4f59\u767e\u5206\u6bd4");
    model.result("pg8").set("xlabelactive", true);
    model.result("pg8").set("xlabel", "\u65f6\u95f4 (h)");
    model.result("pg8").set("ylabelactive", true);
    model.result("pg8").set("ylabel", "(%)");
    model.result("pg8").set("showlegends", false);
    model.result("pg8").create("glob1", "Global");
    model.result("pg8").feature("glob1").set("markerpos", "datapoints");
    model.result("pg8").feature("glob1").set("linewidth", "preference");
    model.result("pg8").feature("glob1").setIndex("expr", "comp1.intop1(1)*2/(pi*R0^2*(Z0-Zi))", 0);
    model.result("pg8").run();
    model.result("pg8").feature("glob1").set("linewidth", 2);
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("\u521d\u59cb\u51b0\u8d28\u91cf");
    model.result().numerical("gev1").setIndex("expr", "comp1.intop1(rho_ice)*2*por_p", 0);
    model.result().numerical("gev1").setIndex("unit", "mg", 0);
    model.result().numerical("gev1").setIndex("descr", "\u51b0\u8d28\u91cf", 0);
    model.result().numerical("gev1").set("dataseries", "maximum");
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u521d\u59cb\u51b0\u8d28\u91cf");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().numerical().duplicate("gev2", "gev1");
    model.result().numerical("gev2").label("\u6700\u7ec8\u51b0\u8d28\u91cf");
    model.result().numerical("gev2").set("dataseries", "minimum");
    model.result().numerical("gev2").set("table", "tbl1");
    model.result().numerical("gev2").appendResult();
    model.result().numerical().create("int1", "IntSurface");
    model.result().numerical("int1").set("intvolume", true);
    model.result().numerical("int1").label("\u84b8\u6c7d\u901a\u91cf");
    model.result().numerical("int1").selection().set(7, 14, 21);
    model.result().numerical("int1").setIndex("expr", "dl.bndflux*2", 0);
    model.result().numerical("int1").setIndex("unit", "mg/h", 0);
    model.result().numerical("int1").setIndex("descr", "\u79bb\u5f00\u5bb9\u5668\u7684\u84b8\u6c7d\u901a\u91cf", 0);
    model.result().numerical("int1").set("intorderactive", true);
    model.result().numerical("int1").set("dataseries", "integral");
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u84b8\u6c7d\u901a\u91cf");
    model.result().numerical("int1").set("table", "tbl2");
    model.result().numerical("int1").setResult();
    model.result("pg6").run();

    model.title("\u51b7\u51bb\u5e72\u71e5");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u5728\u771f\u7a7a\u5ba4\u6761\u4ef6\u4e0b\uff0c\u74f6\u4e2d\u51b0\u7684\u5347\u534e\u8fc7\u7a0b\uff0c\u8fd9\u662f\u4e00\u4e2a\u9002\u7528\u4e8e\u8bb8\u591a\u51b7\u51bb\u5e72\u71e5\u8bbe\u5907\u7684\u6d4b\u8bd5\u7528\u4f8b\u3002\u5176\u4e2d\u4f7f\u7528\u201c\u53d8\u5f62\u51e0\u4f55\u201d\u63a5\u53e3\u6765\u8ba1\u7b97\u8026\u5408\u7684\u70ed\u91cf\u548c\u8d28\u91cf\u5e73\u8861\uff0c\u4ee5\u5904\u7406\u591a\u5b54\u4ecb\u8d28\u4e2d\u4e0d\u65ad\u63a8\u8fdb\u7684\u84b8\u6c7d-\u51b0\u754c\u9762\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("freeze_drying.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
