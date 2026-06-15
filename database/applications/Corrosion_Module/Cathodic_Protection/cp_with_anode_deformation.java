/*
 * cp_with_anode_deformation.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:41 by COMSOL 6.3.0.290. */
public class cp_with_anode_deformation {

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
    model.component("comp1").physics().create("ls", "LevelSet", "geom1");

    model.study().create("std1");
    model.study("std1").create("cdi", "CurrentDistributionInitialization");
    model.study("std1").feature("cdi").set("ftplistmethod", "manual");
    model.study("std1").feature("cdi").set("solnum", "auto");
    model.study("std1").feature("cdi").set("notsolnum", "auto");
    model.study("std1").feature("cdi").set("outputmap", new String[]{});
    model.study("std1").feature("cdi").set("ngenAUX", "1");
    model.study("std1").feature("cdi").set("goalngenAUX", "1");
    model.study("std1").feature("cdi").set("ngenAUX", "1");
    model.study("std1").feature("cdi").set("goalngenAUX", "1");
    model.study("std1").feature("cdi").setSolveFor("/physics/cp", true);
    model.study("std1").feature("cdi").setSolveFor("/physics/ls", true);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("ftplistmethod", "manual");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("solnum", "auto");
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/cp", true);
    model.study("std1").feature("time").setSolveFor("/physics/ls", true);

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "cp_with_anode_deformation.mphbin");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").set("transparency", false);

    model.component("comp1").geom("geom1").run("fin");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("rho", "2700[kg/m^3]", "\u7535\u6781\u5bc6\u5ea6");
    model.param().set("cap", "2000[Ah/kg]", "\u7535\u6781\u91cd\u91cf\u5bb9\u91cf");
    model.param().set("E_zero_steel", "-0.6[V]", "\u96f6\u7535\u6d41\u7535\u4f4d\uff0c\u94a2");
    model.param().set("Eeq_anodes", "-1.05[V]", "\u5e73\u8861\u7535\u538b\uff0c\u9633\u6781");
    model.param().set("i0_anodes", "1[A/m^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u9633\u6781");
    model.param().set("h_interface", "1e-2[m]", "\u6c34\u5e73\u96c6\u754c\u9762\u539a\u5ea6");
    model.param()
         .set("E_lim_O2", "-0.8[V]", "\u6781\u9650\u6c27\u8fd8\u539f\u7535\u6d41\u5bc6\u5ea6\u7684\u8d77\u59cb\u7535\u4f4d");
    model.param().set("i_lim_O2", "-0.1[A/m^2]", "\u6c27\u8fd8\u539f\u6781\u9650\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("T", "15[degC]", "\u6d77\u6c34\u6e29\u5ea6");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("Vn", "-cp.iloc_per1/(cap*rho)", "\u9633\u6781\u8868\u9762\u6eb6\u89e3\u901f\u5ea6");
    model.component("comp1").variable("var1")
         .set("epsl", "max(min(phils,1),1e-3)", "\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570");
    model.component("comp1").variable("var1")
         .set("phils_init", "min(1,2*sphavg(h_interface,dom==1,8))", "\u521d\u59cb\u6c34\u5e73\u96c6\u53d8\u91cf\u8868\u8fbe\u5f0f");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u7535\u89e3\u8d28");
    model.component("comp1").selection("sel1").set(1);
    model.component("comp1").selection().create("com1", "Complement");
    model.component("comp1").selection("com1").label("\u9633\u6781");
    model.component("comp1").selection("com1").set("input", new String[]{"sel1"});
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").label("\u7535\u89e3\u8d28\u8fb9\u754c");
    model.component("comp1").selection("adj1").set("input", new String[]{"sel1"});
    model.component("comp1").selection().duplicate("adj2", "adj1");
    model.component("comp1").selection("adj2").label("\u9633\u6781\u8fb9\u754c");
    model.component("comp1").selection("adj2").set("input", new String[]{"com1"});
    model.component("comp1").selection().create("int1", "Intersection");
    model.component("comp1").selection("int1").label("\u7535\u89e3\u8d28-\u9633\u6781\u8fb9\u754c");
    model.component("comp1").selection("int1").set("entitydim", 2);
    model.component("comp1").selection("int1").set("input", new String[]{"adj1", "adj2"});
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u7535\u89e3\u8d28\u5916\u8fb9\u754c");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(1, 2, 3, 4, 5, 394);
    model.component("comp1").selection().create("com2", "Complement");
    model.component("comp1").selection("com2").label("\u7535\u89e3\u8d28\u5916\u8fb9\u754c\u7684\u8865\u96c6");
    model.component("comp1").selection("com2").set("entitydim", 2);
    model.component("comp1").selection("com2").set("input", new String[]{"sel2"});
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").label("\u7ed3\u6784");
    model.component("comp1").selection("dif1").set("entitydim", 2);
    model.component("comp1").selection("dif1").set("add", new String[]{"com2"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"int1"});
    model.component("comp1").selection().create("dif2", "Difference");
    model.component("comp1").selection("dif2").label("\u53d7\u4fdd\u62a4\u7684\u94a2\u8fb9\u754c");
    model.component("comp1").selection("dif2").set("entitydim", 2);
    model.component("comp1").selection("dif2").set("add", new String[]{"com2"});
    model.component("comp1").selection("dif2").set("subtract", new String[]{"adj2"});

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

    model.component("comp1").physics("cp").prop("ShapeProperty").set("order_electricpotentialionicphase", 1);

    model.component("comp1").view().create("view2", "geom1");
    model.component("comp1").view("view2").hideEntities().create("hide1");
    model.component("comp1").view("view2").hideEntities("hide1").geom("geom1", 2);
    model.component("comp1").view("view2").hideEntities("hide1").named("sel2");

    model.component("comp1").physics("cp").create("hcpce1", "HighlyConductivePorousElectrode", 3);
    model.component("comp1").physics("cp").feature("hcpce1")
         .label("\u9ad8\u5bfc\u7535\u6027\u591a\u5b54\u7535\u6781 - \u9633\u6781");
    model.component("comp1").physics("cp").feature("hcpce1").selection().named("com1");
    model.component("comp1").physics("cp").feature("hcpce1").set("epsl", "epsl");
    model.component("comp1").physics("cp").feature("hcpce1").set("IonicCorrModel", "Tortuosity");
    model.component("comp1").physics("cp").feature("hcpce1").feature("per1").set("Eeq", "Eeq_anodes");
    model.component("comp1").physics("cp").feature("hcpce1").feature("per1").set("i0", "i0_anodes");
    model.component("comp1").physics("cp").feature("hcpce1").feature("per1").set("Av", "ls.delta");
    model.component("comp1").physics("cp").create("protms1", "ProtectedMetalSurface", 2);
    model.component("comp1").physics("cp").feature("protms1").selection().named("dif2");
    model.component("comp1").physics("cp").feature("protms1")
         .set("OxygenReductionExpressionType", "LimitedLinearRamp");
    model.component("comp1").physics("cp").feature("protms1").set("E0", "E_zero_steel");
    model.component("comp1").physics("cp").feature("protms1").set("Elim", "E_lim_O2");
    model.component("comp1").physics("cp").feature("protms1").set("iO2lim", "i_lim_O2");

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.component("comp1").physics("ls").selection().named("com1");
    model.component("comp1").physics("ls").feature("lsm1").set("gamma", "max(Vn,eps)");
    model.component("comp1").physics("ls").feature("lsm1").set("epsilon_ls", "h_interface");
    model.component("comp1").physics("ls").feature("lsm1")
         .set("u", new String[]{"Vn*ls.intnormx", "Vn*ls.intnormy", "Vn*ls.intnormz"});
    model.component("comp1").physics("ls").feature("init1").set("InitialValuesOption", "UserDefined");
    model.component("comp1").physics("ls").feature("init1").set("phils_init", "phils_init");
    model.component("comp1").physics("ls").feature("initfluid2").active(false);
    model.component("comp1").physics("ls").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("ls").feature("inl1").selection().named("int1");
    model.component("comp1").physics("ls").feature("inl1").set("lscond", "Fluid2ls");

    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().named("com1");
    model.component("comp1").mesh("mesh1").feature("swe1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmax", 0.05);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 0.05);
    model.component("comp1").mesh("mesh1").run("swe1");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().named("com1");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().all();
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 1);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhmin", "h_interface");
    model.component("comp1").mesh("mesh1").run("bl1");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("cdi").set("initType", "secondary");
    model.study("std1").feature("time").set("tunit", "a");
    model.study("std1").feature("time").set("tlist", "range(0,1,30)");
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d");
    model.result("pg1").set("view", "view2");
    model.result("pg1").set("edges", false);
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").set("legendactive", true);
    model.result("pg1").set("legendprecision", 4);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "cp.Evsref");
    model.result("pg1").feature("surf1")
         .set("descr", "\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d");
    model.result("pg1").feature("surf1").set("unit", "mV");
    model.result("pg1").feature("surf1").set("colortable", "Viridis");
    model.result("pg1").feature("surf1").create("sel1", "Selection");
    model.result("pg1").feature("surf1").feature("sel1").selection().named("dif1");
    model.result("pg1").run();
    model.result("pg1").create("iso1", "Isosurface");
    model.result("pg1").feature("iso1").set("expr", "ls.Vf2");
    model.result("pg1").feature("iso1").set("descr", "\u6d41\u4f53 2 \u7684\u4f53\u79ef\u5206\u6570");
    model.result("pg1").feature("iso1").set("titletype", "none");
    model.result("pg1").feature("iso1").set("number", 1);
    model.result("pg1").feature("iso1").set("coloring", "uniform");
    model.result("pg1").feature("iso1").set("color", "gray");
    model.result("pg1").feature("iso1").set("colorlegend", false);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 11, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 21, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 31, 0);

    model.title("\u9633\u6781\u53d8\u5f62\u7684\u9634\u6781\u4fdd\u62a4");

    model
         .description("\u672c\u6559\u7a0b\u5bf9\u77f3\u6cb9\u94bb\u673a\u7ed3\u6784\u5728 30\u00a0\u5e74\u95f4\u7684\u9634\u6781\u4fdd\u62a4\u8fdb\u884c\u5efa\u6a21\u3002\n\n\u7531\u4e8e\u727a\u7272\u9633\u6781\u7684\u6d88\u8017\uff0c\u7cfb\u7edf\u7684\u4fdd\u62a4\u80fd\u529b\u4f1a\u968f\u7740\u65f6\u95f4\u7684\u63a8\u79fb\u800c\u964d\u4f4e\u3002\n\n\u5176\u4e2d\u9633\u6781\u5f62\u72b6\u53d8\u5316\u901a\u8fc7\u201c\u6c34\u5e73\u96c6\u201d\u63a5\u53e3\u8fdb\u884c\u5b9a\u4e49\uff0c\u5e76\u4e0e\u201c\u9634\u6781\u4fdd\u62a4\u201d\u63a5\u53e3\u5b9a\u4e49\u7684\u9633\u6781\u6eb6\u89e3\u901f\u7387\u76f8\u8026\u5408\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("cp_with_anode_deformation.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
