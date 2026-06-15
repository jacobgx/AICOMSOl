/*
 * anode_film_resistance.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:40 by COMSOL 6.3.0.290. */
public class anode_film_resistance {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Corrosion_Module\\Cathodic_Protection");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("cd", "SecondaryCurrentDistribution", "geom1");

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
    model.study("std1").feature("cdi").setSolveFor("/physics/cd", true);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("ftplistmethod", "manual");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("solnum", "auto");
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/cd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("T", "283.15[K]", "\u6e29\u5ea6");
    model.param().set("rho_ZnOH2", "3.053[g/cm^3]", "\u9633\u6781\u819c\u5bc6\u5ea6");
    model.param().set("M_ZnOH2", "99.424[g/mol]", "\u9633\u6781\u819c\u6469\u5c14\u8d28\u91cf");
    model.param().set("sigma_ZnOH2", "1e-6[S/m]", "\u9633\u6781\u819c\u7535\u5bfc\u7387");
    model.param().set("lambda", "0.01", "\u6c27\u5316\u7269/\u91d1\u5c5e\u6eb6\u89e3\u7269\u4e4b\u6bd4");
    model.param()
         .set("Eeq_O2", "1.23[V]+R_const*T/(4*F_const)*log(1e-8)", "\u6c27\u8fd8\u539f\u5e73\u8861\u7535\u4f4d");
    model.param().set("A_O2", "-100[mV]", "Tafel \u659c\u7387\uff0c\u6c27\u8fd8\u539f");
    model.param().set("i0_O2", "1e-9[A/m^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6c27\u8fd8\u539f");
    model.param().set("ilim_O2", "-0.1[A/m^2]", "\u6781\u9650\u7535\u6d41\u5bc6\u5ea6\uff0c\u6c27\u8fd8\u539f");
    model.param()
         .set("Eeq_Fe", "-0.44[V]+R_const*T/(2*F_const)*log(1e-9)", "\u94a2\u6c27\u5316\u5e73\u8861\u7535\u4f4d");
    model.param().set("i0_Fe", "1e-3[A/m^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u94a2");
    model.param().set("A_Fe", "100[mV]", "Tafel \u659c\u7387\uff0c\u94a2\u6c27\u5316");
    model.param().set("Eeq_Zn", "-0.7[V]+R_const*T/(2*F_const)*log(1e-9)", "\u9633\u6781\u5e73\u8861\u7535\u4f4d");
    model.param().set("i0_Zn", "1.0E-01[mA/cm^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0cZn");
    model.param().set("A_Zn", "1.86[1]*R_const*T/F_const", "Tafel \u659c\u7387\uff0cZn");

    model.component("comp1").geom("geom1").insertFile("anode_film_resistance_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("rmd1");

    model.component("comp1").view("view1").set("transparency", true);

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

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.component("comp1").physics("cd").create("es1", "ElectrodeSurface", 2);
    model.component("comp1").physics("cd").feature("es1").label("\u7535\u6781\u8868\u9762 1 - \u950c");
    model.component("comp1").physics("cd").feature("es1").selection().named("geom1_csel3_bnd");
    model.component("comp1").physics("cd").feature("es1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("cd").feature("es1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("cd").feature("es1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("cd").feature("es1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("cd").feature("es1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("cd").feature("es1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("cd").feature("es1").setIndex("rhos", "rho_ZnOH2", 0, 0);
    model.component("comp1").physics("cd").feature("es1").setIndex("Ms", "M_ZnOH2", 0, 0);
    model.component("comp1").physics("cd").feature("es1").set("FilmResistanceType", "ThicknessAndConductivity");
    model.component("comp1").physics("cd").feature("es1").set("dsf_src", "root.comp1.cd.sbtot");
    model.component("comp1").physics("cd").feature("es1").set("sigmaf", "sigma_ZnOH2");
    model.component("comp1").physics("cd").feature("es1").feature("er1").label("\u950c\u6c27\u5316");
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("nm", 2);
    model.component("comp1").physics("cd").feature("es1").feature("er1").setIndex("Vib", "-lambda", 0, 0);
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("Eeq", "Eeq_Zn");
    model.component("comp1").physics("cd").feature("es1").feature("er1")
         .set("ElectrodeKinetics", "AnodicTafelEquation");
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("i0", "i0_Zn");
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("Aa", "A_Zn");
    model.component("comp1").physics("cd").create("es2", "ElectrodeSurface", 2);
    model.component("comp1").physics("cd").feature("es2").label("\u7535\u6781\u8868\u9762 2 - \u94a2");
    model.component("comp1").physics("cd").feature("es2").selection().named("geom1_csel1_bnd");
    model.component("comp1").physics("cd").feature("es2").feature("er1").label("\u94a2\u6c27\u5316");
    model.component("comp1").physics("cd").feature("es2").feature("er1").set("Eeq", "Eeq_Fe");
    model.component("comp1").physics("cd").feature("es2").feature("er1")
         .set("ElectrodeKinetics", "AnodicTafelEquation");
    model.component("comp1").physics("cd").feature("es2").feature("er1").set("i0", "i0_Fe");
    model.component("comp1").physics("cd").feature("es2").feature("er1").set("Aa", "A_Fe");
    model.component("comp1").physics("cd").feature("es2").create("er2", "ElectrodeReaction", 2);
    model.component("comp1").physics("cd").feature("es2").feature("er2").label("\u6c27\u8fd8\u539f");
    model.component("comp1").physics("cd").feature("es2").feature("er2").set("Eeq", "Eeq_O2");
    model.component("comp1").physics("cd").feature("es2").feature("er2")
         .set("ElectrodeKinetics", "CathodicTafelEquation");
    model.component("comp1").physics("cd").feature("es2").feature("er2").set("i0", "i0_O2");
    model.component("comp1").physics("cd").feature("es2").feature("er2").set("Ac", "A_O2");
    model.component("comp1").physics("cd").feature("es2").feature("er2").set("LimitingCurrentDensity", true);
    model.component("comp1").physics("cd").feature("es2").feature("er2").set("ilim", "ilim_O2");
    model.component("comp1").physics("cd").feature("init1").set("phil", "-Eeq_Zn");
    model.component("comp1").physics("cd").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("cd").feature("sym1").selection().set(1, 251);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 12);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 0.35);
    model.component("comp1").mesh("mesh1").feature("size").set("hgrad", 1.6);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature().move("ftri1", 3);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(14, 17, 18, 116, 117, 154, 155);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 4);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("cdi").set("initType", "secondary");
    model.study("std1").feature("time").set("tunit", "d");
    model.study("std1").feature("time").set("tlist", "0 10 20 30 range(60,60,300) 365");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 10, 0);
    model.result("pg1").label("\u7535\u89e3\u8d28\u7535\u4f4d (cd)");
    model.result("pg1").create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("expr", new String[]{"phil"});
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("expr", new String[]{"cd.Ilx", "cd.Ily", "cd.Ilz"});
    model.result("pg1").feature("str1").set("posmethod", "start");
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg1").feature("str1").set("color", "gray");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 10, 0);
    model.result("pg2").label("\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6 (cd)");
    model.result("pg2").create("str1", "Streamline");
    model.result("pg2").feature("str1").set("expr", new String[]{"cd.Ilx", "cd.Ily", "cd.Ilz"});
    model.result("pg2").feature("str1").set("posmethod", "start");
    model.result("pg2").feature("str1").set("pointtype", "arrow");
    model.result("pg2").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg2").feature("str1").set("color", "gray");
    model.result("pg2").feature("str1").create("col1", "Color");
    model.result("pg2").feature("str1").feature("col1").set("expr", "root.comp1.cd.IlMag");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"abs(cd.itot)"});
    model.result("pg2").feature("surf1").set("inheritplot", "str1");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 10, 0);
    model.result("pg3").label("\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d (cd)");
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1").set("expr", new String[]{"cd.Ilx", "cd.Ily", "cd.Ilz"});
    model.result("pg3").feature("str1").set("posmethod", "start");
    model.result("pg3").feature("str1").set("pointtype", "arrow");
    model.result("pg3").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg3").feature("str1").set("color", "gray");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"cd.Evsref"});
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 10, 0);
    model.result("pg4").label("\u7535\u6781\u603b\u539a\u5ea6\u53d8\u5316 (cd)");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"cd.sbtot"});
    model.result("pg4").feature("surf1").set("unit", "\u00b5m");
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").label("\u7535\u4f4d vs. SHE (V)");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u7535\u4f4d vs. SHE (V)");
    model.result("pg3").set("edges", false);
    model.result("pg3").run();
    model.result("pg3").feature("str1").active(false);
    model.result("pg3").run();
    model.result("pg3").feature("surf1").create("sel1", "Selection");
    model.result("pg3").feature("surf1").feature("sel1").selection().named("geom1_unisel1_bnd");
    model.result("pg3").run();
    model.result("pg3").run();

    model.component("comp1").view("view1").set("transparency", false);

    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").set("paramindicator", "Time= 0 d");
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").label("\u6c27\u5316\u7269\u5c42\u539a\u5ea6");
    model.result("pg4").selection().geom("geom1", 2);
    model.result("pg4").selection().named("geom1_csel3_bnd");
    model.result("pg4").set("edges", false);
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u5c40\u90e8\u8150\u8680\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("legendpos", "lowerright");
    model.result("pg5").create("ptgr1", "PointGraph");
    model.result("pg5").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg5").feature("ptgr1").set("linewidth", "preference");

    model.component("comp1").view("view1").set("transparency", true);

    model.result("pg5").feature("ptgr1").selection().set(28);
    model.result("pg5").feature("ptgr1").set("expr", "cd.iloc_er1");
    model.result("pg5").feature("ptgr1").set("descr", "\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg5").feature("ptgr1").set("legend", true);
    model.result("pg5").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg5").feature("ptgr1").setIndex("legends", "\u652f\u817f\u9876\u90e8", 0);
    model.result("pg5").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr2").selection().set(22);
    model.result("pg5").feature("ptgr2").setIndex("legends", "\u652f\u817f\u5e95\u90e8", 0);
    model.result("pg5").run();

    model.title("\u9633\u6781\u819c\u963b\u5bf9\u9634\u6781\u4fdd\u62a4\u7684\u5f71\u54cd");

    model
         .description("\u672c\u4f8b\u5bf9 oil_platform \u6a21\u578b\u8fdb\u884c\u6269\u5c55\uff0c\u4e3e\u4f8b\u8bf4\u660e\u7531\u4e8e\u53cd\u5e94\u4ea7\u7269\u5728\u727a\u7272\u9633\u6781\u4e0a\u5f62\u6210\u7535\u963b\u819c\uff0c\u94a2\u7684\u8150\u8680\u901f\u7387\u5982\u4f55\u968f\u65f6\u95f4\u800c\u589e\u5927\u3002\u8be5\u6a21\u578b\u8fd8\u5305\u542b\u53d7\u4fdd\u62a4\u7684\u94a2\u7ed3\u6784\u4e0a\u7684\u4e8c\u6b21\u7535\u6d41\u5206\u5e03\u7535\u6781\u52a8\u529b\u5b66\uff0c\u540c\u65f6\u5b9a\u4e49\u4e86\u91d1\u5c5e\u6eb6\u89e3\u548c\u6c27\u8fd8\u539f\uff08\u6df7\u5408\u7535\u4f4d\uff09\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("anode_film_resistance.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
