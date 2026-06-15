/*
 * oil_platform.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:42 by COMSOL 6.3.0.290. */
public class oil_platform {

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
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/cp", true);

    model.param().set("i_oxygen", "-0.1[A/m^2]");
    model.param().set("Eeq_Al", "-1.05[V]", "\u94a2\u7ed3\u6784\u6c27\u8fd8\u539f\u7684\u6781\u9650\u7535\u6d41");
    model.param().descr("Eeq_Al", "\u9633\u6781\u5e73\u8861\u7535\u4f4d vs. Ag/AgCl");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "oil_platform.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").feature("imp1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("imp1").set("selresultshow", "bnd");
    model.component("comp1").geom("geom1").feature("imp1").set("selindividual", true);
    model.component("comp1").geom("geom1").feature("imp1").set("selindividualshow", "bnd");
    model.component("comp1").geom("geom1").run("imp1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 40);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 92);
    model.component("comp1").geom("geom1").run("cyl1");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").feature().duplicate("cyl2", "cyl1");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", 60);
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("cyl1", "cyl2");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("imp1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").coordSystem().create("ie1", "InfiniteElement");
    model.component("comp1").coordSystem("ie1").selection().set(1);
    model.component("comp1").coordSystem("ie1").set("ScalingType", "Cylindrical");

    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").label("\u9633\u6781");
    model.component("comp1").selection("dif1").set("entitydim", 2);
    model.component("comp1").selection("dif1").set("add", new String[]{"geom1_imp1_bnd"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"geom1_imp1_41_bnd"});

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
    model.component("comp1").physics("cp").feature("ice1").set("minput_temperature_src", "userdef");
    model.component("comp1").physics("cp").feature("ice1").set("minput_temperature", "10[degC]");
    model.component("comp1").physics("cp").create("es1", "ElectrodeSurface", 2);
    model.component("comp1").physics("cp").feature("es1").label("\u7535\u6781\u8868\u9762 - \u9633\u6781");
    model.component("comp1").physics("cp").feature("es1").selection().named("dif1");
    model.component("comp1").physics("cp").feature("es1").feature("er1").set("Eeq", "Eeq_Al");
    model.component("comp1").physics("cp").feature("es1").feature("er1")
         .set("ElectrodeKinetics", "PrimaryConditionThermodynamicEquilibrium");
    model.component("comp1").physics("cp").create("protms1", "ProtectedMetalSurface", 2);
    model.component("comp1").physics("cp").feature("protms1")
         .label("\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6 - \u94a2");
    model.component("comp1").physics("cp").feature("protms1").selection().named("geom1_imp1_41_bnd");
    model.component("comp1").physics("cp").feature("protms1").set("iO2", "i_oxygen");

    model.component("comp1").mesh("mesh1").contribute("geom/detail", false);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 15);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 0.5);
    model.component("comp1").mesh("mesh1").feature("size").set("hcurve", 0.9);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature().move("ftri1", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(17, 19, 145, 147, 314, 316, 368, 370);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmin", 1.08);
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("\u7535\u89e3\u8d28\u7535\u4f4d (cp)");
    model.result("pg1").create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("expr", new String[]{"phil"});
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("expr", new String[]{"cp.Ilx", "cp.Ily", "cp.Ilz"});
    model.result("pg1").feature("str1").set("posmethod", "start");
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg1").feature("str1").set("color", "gray");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").label("\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6 (cp)");
    model.result("pg2").create("str1", "Streamline");
    model.result("pg2").feature("str1").set("expr", new String[]{"cp.Ilx", "cp.Ily", "cp.Ilz"});
    model.result("pg2").feature("str1").set("posmethod", "start");
    model.result("pg2").feature("str1").set("pointtype", "arrow");
    model.result("pg2").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg2").feature("str1").set("color", "gray");
    model.result("pg2").feature("str1").create("col1", "Color");
    model.result("pg2").feature("str1").feature("col1").set("expr", "root.comp1.cp.IlMag");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"abs(cp.itot)"});
    model.result("pg2").feature("surf1").set("inheritplot", "str1");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").label("\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d (cp)");
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1").set("expr", new String[]{"cp.Ilx", "cp.Ily", "cp.Ilz"});
    model.result("pg3").feature("str1").set("posmethod", "start");
    model.result("pg3").feature("str1").set("pointtype", "arrow");
    model.result("pg3").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg3").feature("str1").set("color", "gray");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"cp.Evsref"});
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").label("\u9633\u6781\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u9633\u6781\u7535\u6d41\u5bc6\u5ea6 (A/m<sup>2</sup>)");
    model.result("pg2").set("edges", false);
    model.result("pg2").run();
    model.result("pg2").feature("str1").active(false);
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("inheritplot", "none");
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().named("dif1");
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").label("\u94a2\u7535\u4f4d");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u94a2\u7535\u4f4d vs. Ag/AgCl (V)");
    model.result("pg3").set("edges", false);
    model.result("pg3").run();
    model.result("pg3").feature("str1").active(false);
    model.result("pg3").run();
    model.result("pg3").feature("surf1").create("sel1", "Selection");
    model.result("pg3").feature("surf1").feature("sel1").selection().named("geom1_imp1_41_bnd");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf1").feature("sel1").selection().set(212, 213, 214, 218, 219, 220, 223, 224);
    model.result("pg4").run();
    model.result("pg4").label("\u94a2\u7535\u4f4d\uff0c\u7279\u5199");
    model.result("pg4").run();
    model.result("pg1").run();

    model.title("\u77f3\u6cb9\u5e73\u53f0\u7684\u727a\u7272\u9633\u6781\u8150\u8680\u9632\u62a4");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u77f3\u6cb9\u5e73\u53f0\u901a\u8fc7\u94dd\u727a\u7272\u9633\u6781\u7684\u8150\u8680\u9632\u62a4\u7cfb\u7edf\u3002\u65e0\u9650\u5143\u57df\u7528\u4e8e\u63cf\u8ff0\u6d77\u6d0b\u7684\u65e0\u9650\u6269\u5c55\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("oil_platform.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
