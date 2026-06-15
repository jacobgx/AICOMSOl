/*
 * monopile.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:41 by COMSOL 6.3.0.290. */
public class monopile {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Corrosion_Module\\Cathodic_Protection");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("cp", "CathodicProtection", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/cp", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Eeq_AlZn", "-1.05[V]", "\u9633\u6781\u5e73\u8861\u7535\u4f4d");
    model.param().set("T", "283.15[K]", "\u6e29\u5ea6");
    model.param()
         .set("Eeq_Fe", "-0.44[V]+R_const*T/(2*F_const)*log(1e-9)", "\u94a2\u6c27\u5316\u5e73\u8861\u7535\u4f4d");
    model.param().set("i0_Fe", "1e-3[A/m^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u94a2");
    model.param().set("i0_Fe_coated", "i0_Fe/50", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6d82\u5c42\u94a2");
    model.param().set("A_Fe", "100[mV]", "Tafel \u659c\u7387\uff0c\u94a2\u6c27\u5316");
    model.param().set("ilim_O2", "-0.1[A/m^2]", "\u6781\u9650\u7535\u6d41\u5bc6\u5ea6\uff0c\u6c27\u8fd8\u539f");
    model.param()
         .set("ilim_O2_mud", "ilim_O2/5", "\u6781\u9650\u7535\u6d41\u5bc6\u5ea6\uff0c\u6ce5\u6d46\u4e2d\u7684\u6c27\u8fd8\u539f");
    model.param().set("A_O2", "-100[mV]", "Tafel \u659c\u7387\uff0c\u6c27\u8fd8\u539f");
    model.param().set("R0", "0.15[m]", "\u9633\u6781\u521d\u59cb\u534a\u5f84");
    model.param().set("Rf", "0.05[m]", "\u9633\u6781\u6700\u7ec8\u534a\u5f84");
    model.param().set("AnodeCap", "1e5[A*h/m]", "\u9633\u6781\u7535\u5bb9");
    model.param()
         .set("R_Tp", "1e-2[ohm]", "\u8fc7\u6e21\u8fde\u63a5\u4ef6\u4e0e\u5355\u6869\u4e4b\u95f4\u7684\u7535\u963b");
    model.param().set("sigma_mud", "1.3[S/m]", "\u6ce5\u6d46\u7684\u7535\u5bfc\u7387");

    model.component("comp1").geom("geom1").insertFile("monopile_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElectrolyteConductivity", "ElectrolyteConductivity", "Electrolyte conductivity");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").label("Seawater");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .label("Electrolyte conductivity");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("source", "file");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("importedname", "seawater conductivity.txt");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("importeddim", "2D");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("filecolumns", 3);
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("columnKeys", new String[]{"col1", "col2", "col3"});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("columnType", new String[]{"col1", "arg", "col2", "arg", "col3", "value"});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("fununit", new String[]{""});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("argunit", new String[]{"", ""});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("sourcetype", "model");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .set("sigmal", new String[]{"sigma0*int1(S,(T-0[degC])[1/K])", "0", "0", "0", "sigma0*int1(S,(T-0[degC])[1/K])", "0", "0", "0", "sigma0*int1(S,(T-0[degC])[1/K])"});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .setPropertyInfo("sigmal", "Fofonoff, N. P., and R. C. Millard, Jr., Algorithms for computation of\nfundamental properties of seawater, UNESCO, Tech. Pap. Mar.\nSci., 44, 53 pp., Paris, 1984.\n\nPhysical Properties of Seawater -\nA New Salinity Scale and Equation of State for Seawater, Fofonoff, J. Geophysical Research, Vol. 90, No. C2, 3332-3342, 1985\n");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").set("S", "35");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .descr("S", "Practical Salinity (PSS 78)");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").set("sigma0", "4.29[S/m]");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .descr("sigma0", "Conductivity at T=15[degC] and S=35");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").addInput("temperature");
    model.component("comp1").material("mat1").selection().set(2);
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u6ce5\u6d46");
    model.component("comp1").material("mat2").selection().set(1);
    model.component("comp1").material("mat2").propertyGroup()
         .create("ElectrolyteConductivity", "ElectrolyteConductivity", "Electrolyte_conductivity");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity")
         .set("sigmal", new String[]{"sigma_mud"});

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.component("comp1").selection().create("cyl1", "Cylinder");
    model.component("comp1").selection("cyl1").label("\u9876\u90e8");
    model.component("comp1").selection("cyl1").set("entitydim", 2);
    model.component("comp1").selection("cyl1").set("r", 10);
    model.component("comp1").selection("cyl1").set("top", 0.1);
    model.component("comp1").selection("cyl1").set("bottom", -20.1);
    model.component("comp1").selection("cyl1").set("condition", "inside");
    model.component("comp1").selection().duplicate("cyl2", "cyl1");
    model.component("comp1").selection("cyl2").label("\u4e2d\u90e8");
    model.component("comp1").selection("cyl2").set("top", 0);
    model.component("comp1").selection("cyl2").set("bottom", -40);
    model.component("comp1").selection("cyl2").set("pos", new int[]{0, 0, -15});
    model.component("comp1").selection().duplicate("cyl3", "cyl2");
    model.component("comp1").selection("cyl3").label("\u5e95\u90e8");
    model.component("comp1").selection("cyl3").set("bottom", -60);
    model.component("comp1").selection("cyl3").set("pos", new int[]{0, 0, -30});
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").set("entitydim", 2);
    model.component("comp1").selection("uni1").label("\u4e2d\u90e8\u548c\u5e95\u90e8");
    model.component("comp1").selection("uni1").set("input", new String[]{"cyl2", "cyl3"});

    model.component("comp1").physics("cp").selection().set(1, 2);
    model.component("comp1").physics("cp").create("protms1", "ProtectedMetalSurface", 2);
    model.component("comp1").physics("cp").feature("protms1")
         .label("\u53d7\u4fdd\u62a4\u91d1\u5c5e\u8868\u9762 - \u6d82\u5c42\u94a2");
    model.component("comp1").physics("cp").feature("protms1").selection().named("cyl1");
    model.component("comp1").physics("cp").feature("protms1").set("iO2", "ilim_O2");
    model.component("comp1").physics("cp").create("es1", "ElectrodeSurface", 2);
    model.component("comp1").physics("cp").feature("es1")
         .label("\u7535\u6781\u8868\u9762 - \u975e\u6d82\u5c42\u94a2");
    model.component("comp1").physics("cp").feature("es1").selection().named("uni1");
    model.component("comp1").physics("cp").feature("es1").feature("er1")
         .label("\u7535\u6781\u53cd\u5e94 1 - \u94a2\u6c27\u5316");
    model.component("comp1").physics("cp").feature("es1").feature("er1").set("Eeq", "Eeq_Fe");
    model.component("comp1").physics("cp").feature("es1").feature("er1")
         .set("ElectrodeKinetics", "AnodicTafelEquation");
    model.component("comp1").physics("cp").feature("es1").feature("er1").set("i0", "i0_Fe");
    model.component("comp1").physics("cp").feature("es1").feature("er1").set("Aa", "A_Fe");
    model.component("comp1").physics("cp").feature("es1").create("er2", "ElectrodeReaction", 2);
    model.component("comp1").physics("cp").feature("es1").feature("er2")
         .label("\u7535\u6781\u53cd\u5e94 2 - \u6c27\u8fd8\u539f\uff08\u6d77\u6c34\uff09");
    model.component("comp1").physics("cp").feature("es1").feature("er2").selection().set();
    model.component("comp1").physics("cp").feature("es1").feature("er2").selection().named("cyl2");
    model.component("comp1").physics("cp").feature("es1").feature("er2").set("ilocmat_mat", "userdef");
    model.component("comp1").physics("cp").feature("es1").feature("er2").set("ilocmat", "ilim_O2");
    model.component("comp1").physics("cp").feature("es1").feature().duplicate("er3", "er2");
    model.component("comp1").physics("cp").feature("es1").feature("er3")
         .label("\u7535\u6781\u53cd\u5e94 3 - \u6c27\u8fd8\u539f\uff08\u6ce5\u6d46\uff09");
    model.component("comp1").physics("cp").feature("es1").feature("er3").selection().named("cyl3");
    model.component("comp1").physics("cp").feature("es1").feature("er3").set("ilocmat", "ilim_O2_mud");
    model.component("comp1").physics("cp").create("sacredge1", "SacrificialEdgeAnode", 1);
    model.component("comp1").physics("cp").feature("sacredge1").selection().named("geom1_unisel1");
    model.component("comp1").physics("cp").feature("sacredge1").set("Q0", "AnodeCap");
    model.component("comp1").physics("cp").feature("sacredge1").set("r0", "R0");
    model.component("comp1").physics("cp").feature("sacredge1").set("rend", "Rf");
    model.component("comp1").physics("cp").feature("sacredge1").feature("er1").set("Eeq", "Eeq_AlZn");
    model.component("comp1").physics("cp").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("cp").feature("sym1").selection().set(2, 4, 266);
    model.component("comp1").physics("cp").feature("init1").set("phil", "-Eeq_AlZn");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 0.05);
    model.component("comp1").mesh("mesh1").feature("size").set("hcurve", 0.4);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().named("geom1_unisel1");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", 0.1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tunit", "a");
    model.study("std1").feature("time").set("tlist", "range(0,1,12)");
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset("mir1").set("quickplane", "zx");
    model.result().dataset().create("dset2", "Solution");
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().named("cyl1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u94a2\u7535\u6781\u7535\u4f4d");
    model.result("pg1").set("data", "mir1");
    model.result("pg1").set("edges", false);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "cp.Evsref");
    model.result("pg1").feature("surf1")
         .set("descr", "\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d");
    model.result("pg1").feature("surf1").set("colortable", "MetasepiaBlue");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("expr", "1");
    model.result("pg1").feature("line1").set("titletype", "none");
    model.result("pg1").feature("line1").set("linetype", "tube");
    model.result("pg1").feature("line1").set("radiusexpr", "cp.redge");
    model.result("pg1").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg1").feature("line1").set("coloring", "uniform");
    model.result("pg1").feature("line1").set("color", "gray");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 13, 0);
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg1").run();
    model.result().duplicate("pg3", "pg1");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("rangecoloractive", true);
    model.result("pg3").feature("surf1").set("rangecolormin", -1.01511);
    model.result("pg3").feature("surf1").set("rangecolormax", -0.64508);
    model.result("pg3").feature("surf1").create("sel1", "Selection");
    model.result("pg3").feature("surf1").feature("sel1").selection().named("cyl1");
    model.result("pg3").run();
    model.result("pg3").feature("line1").create("sel1", "Selection");
    model.result("pg3").feature("line1").feature("sel1").selection()
         .set(826, 827, 871, 872, 881, 882, 897, 898, 914, 915);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().remove("pg3");
    model.result("pg2").run();
    model.result("pg2").label("\u94c1\uff08\u6eb6\u89e3\uff09\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "cp.iloc_er1");
    model.result("pg2").feature("surf1").set("descr", "\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 13, 0);
    model.result("pg2").run();

    model.component("comp1").physics("cp").feature("es1").set("BoundaryCondition", "ExternalShort");
    model.component("comp1").physics("cp").feature("es1").set("R", "R_Tp");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").run();

    model.title("\u5e26\u53ef\u6eb6\u6027\u727a\u7272\u9633\u6781\u7684\u5355\u6869");

    model
         .description("\u5355\u6869\u57fa\u7840\u662f\u5927\u76f4\u5f84\u7ed3\u6784\u5143\u4ef6\uff0c\u53ef\u7528\u4e8e\u652f\u6491\u79bb\u5cb8\u98ce\u8f66\u7b49\u3002\n\n\u8be5 App \u4e3e\u4f8b\u8bf4\u660e\u5355\u6869\u7684\u9634\u6781\u4fdd\u62a4\u7ecf\u8fc7\u4e00\u6bb5\u65f6\u95f4\u540e\u5982\u4f55\u968f\u7740\u727a\u7272\u9633\u6781\u7684\u6eb6\u89e3\u800c\u51cf\u5f31\u3002\n\n\u672c\u4f8b\u6d89\u53ca\u53d7\u4fdd\u62a4\u94a2\u7ed3\u6784\u4e0a\u7684\u4e8c\u6b21\u7535\u6d41\u5206\u5e03\u7535\u6781\u52a8\u529b\u5b66\uff0c\u5b9a\u4e49\u4e86\u540c\u65f6\u53d1\u751f\u7684\u91d1\u5c5e\u6eb6\u89e3\u548c\u6c27\u8fd8\u539f\uff08\u6df7\u5408\u7535\u52bf\uff09\u3002\u5728\u6a21\u578b\u7684\u4e0a\u90e8\u7ed3\u6784\u4e0e\u4e0b\u90e8\u5355\u6869\u57fa\u7840\u4e4b\u95f4\u5f15\u5165\u4e86\u96c6\u603b\u7535\u963b\uff0c\u53ef\u4ee5\u770b\u5230\u5355\u6869\u9632\u8150\u8680\u80fd\u529b\u7684\u4e0b\u964d\u8fc7\u7a0b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("monopile.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
