/*
 * al_anodization.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:56 by COMSOL 6.3.0.290. */
public class al_anodization {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electrodeposition_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("cd", "SecondaryCurrentDistribution", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/cd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("T", "25[degC]", "\u6e29\u5ea6");
    model.param().set("sigma", "0.55[S/cm]", "\u7535\u89e3\u8d28\u7535\u5bfc\u7387");
    model.param().set("i_avg", "100[A/m^2]", "\u5e73\u5747\u9633\u6781\u6c27\u5316\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("E_cell_init", "15[V]", "\u7535\u6c60\u7535\u4f4d\u521d\u59cb\u503c");
    model.param().set("rho", "3.97[g/cm^3]", "\u5bc6\u5ea6");
    model.param().set("M", "102[g/mol]", "\u6469\u5c14\u8d28\u91cf");
    model.param().set("eff", "70[%]", "\u7535\u6d41\u6548\u7387");
    model.param().set("por", "30[%]", "\u5c42\u5b54\u9699\u7387");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"5[cm]", "1[dm]"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"4[cm]", "8[cm]"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("pos", new String[]{"0", "2[cm]"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input2").set("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").selection("point")
         .set("dif1", 1, 2, 3, 4, 5, 6);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").set("radius", "3[mm]");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").selection("input").set("fil1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").set("fullsize", new int[]{1, 5});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1")
         .set("displ", new String[]{"0", "2[dm]"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("arr1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "2[m]", 0);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u9633\u6781");
    model.component("comp1").geom("geom1").feature("ext1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").set("ext1");
    model.component("comp1").geom("geom1").feature("mov1").set("displx", "1.25[dm]");
    model.component("comp1").geom("geom1").feature("mov1").set("disply", "1[dm]");
    model.component("comp1").geom("geom1").feature("mov1").set("displz", "1[dm]");
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"2.2[m]", "0.25[m]", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "1.2[m]", 2);
    model.component("comp1").geom("geom1").run("blk1");

    model.component("comp1").view("view1").set("transparency", true);

    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("blk1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("mov1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("cd").feature("ice1").set("sigmal_mat", "userdef");
    model.component("comp1").physics("cd").feature("ice1")
         .set("sigmal", new String[]{"sigma", "0", "0", "0", "sigma", "0", "0", "0", "sigma"});

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").set("source", "file");
    model.component("comp1").func("int1").set("filename", "al_polarization_data.csv");
    model.component("comp1").func("int1").importData();
    model.component("comp1").func("int1").setIndex("argunit", "V", 0);
    model.component("comp1").func("int1").setIndex("argunit", "degC", 1);
    model.component("comp1").func("int1").setEntry("funcnames", "col3", "iloc_Al");
    model.component("comp1").func("int1").setIndex("fununit", "A/m^2", 0);

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().named("geom1_csel1_bnd");

    model.component("comp1").physics("cd").create("es1", "ElectrodeSurface", 2);
    model.component("comp1").physics("cd").feature("es1").label("\u7535\u6781\u8868\u9762 - \u9633\u6781");
    model.component("comp1").physics("cd").feature("es1").selection().named("geom1_csel1_bnd");
    model.component("comp1").physics("cd").feature("es1").set("BoundaryCondition", "AverageCurrentDensity");
    model.component("comp1").physics("cd").feature("es1").set("Ial", "i_avg");
    model.component("comp1").physics("cd").feature("es1").set("phisext0init", "E_cell_init");
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("ilocmat_mat", "userdef");
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("ilocmat", "iloc_Al(cd.Evsref,T)");
    model.component("comp1").physics("cd").create("es2", "ElectrodeSurface", 2);
    model.component("comp1").physics("cd").feature("es2").label("\u7535\u6781\u8868\u9762 - \u9634\u6781");
    model.component("comp1").physics("cd").feature("es2").selection().set(2);
    model.component("comp1").physics("cd").feature("es2").feature("er1")
         .set("ElectrodeKinetics", "PrimaryConditionThermodynamicEquilibrium");

    model.component("comp1").mesh("mesh1").contribute("geom/detail", false);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "T", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "K", 0);
    model.study("std1").feature("stat").setIndex("pname", "T", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "K", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "15 20 25", 0);
    model.study("std1").feature("stat").setIndex("punit", "degC", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 3, 0);
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
    model.result("pg2").setIndex("looplevel", 3, 0);
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
    model.result("pg3").setIndex("looplevel", 3, 0);
    model.result("pg3").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (cd)");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"cd.phisext"});
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 3, 0);
    model.result("pg4").label("\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d (cd)");
    model.result("pg4").create("str1", "Streamline");
    model.result("pg4").feature("str1").set("expr", new String[]{"cd.Ilx", "cd.Ily", "cd.Ilz"});
    model.result("pg4").feature("str1").set("posmethod", "start");
    model.result("pg4").feature("str1").set("pointtype", "arrow");
    model.result("pg4").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg4").feature("str1").set("color", "gray");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"cd.Evsref"});
    model.result("pg1").run();

    model.component("comp1").view("view1").set("transparency", false);

    model.result("pg4").run();
    model.result("pg4").selection().geom("geom1", 2);
    model.result("pg4").selection().named("geom1_csel1_bnd");
    model.result("pg4").run();
    model.result("pg4").feature().remove("str1");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").label("\u5f52\u4e00\u5316\u7535\u6d41\u5206\u5e03");
    model.result("pg5").selection().geom("geom1", 2);
    model.result("pg5").selection().named("geom1_csel1_bnd");
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "cd.itot/i_avg");
    model.result("pg5").feature("surf1").set("descractive", true);
    model.result("pg5").feature("surf1").set("descr", "\u5f52\u4e00\u5316\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 2, 0);
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 1, 0);
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 3, 0);
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("25 \u5206\u949f\u540e\u7684\u6c89\u79ef\u5c42\u539a\u5ea6");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").set("expr", "cd.itot*25[min]*M*eff/(6*F_const*rho*(1-por))");
    model.result("pg6").feature("surf1").set("unit", "\u00b5m");
    model.result("pg6").feature("surf1").set("descr", "25 \u5206\u949f\u540e\u7684\u6c27\u5316\u5c42\u539a\u5ea6");
    model.result("pg6").run();
    model.result().dataset().create("grid1", "Grid1D");
    model.result().dataset("grid1").set("source", "function");
    model.result().dataset("grid1").set("function", "int1");
    model.result().dataset("grid1").set("parmax1", 16);
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u5b9e\u9a8c\u94dd\u6781\u5316\u6570\u636e");
    model.result("pg7").set("data", "grid1");
    model.result("pg7").set("titletype", "label");
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("xlabel", "\u9633\u6781\u6c27\u5316\u7535\u4f4d vs. SHE (V)");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "\u7535\u6d41\u5bc6\u5ea6 (A/m<sup>2</sup>)");
    model.result("pg7").set("axislimits", true);
    model.result("pg7").set("xmin", 0);
    model.result("pg7").set("xmax", 16);
    model.result("pg7").set("ymin", 0);
    model.result("pg7").set("ymax", 159);
    model.result("pg7").set("legendpos", "lowerright");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr1").set("linewidth", "preference");
    model.result("pg7").feature("lngr1").set("expr", "comp1.iloc_Al(root.x[V],15[degC])");
    model.result("pg7").feature("lngr1").set("legend", true);
    model.result("pg7").feature("lngr1").set("legendmethod", "manual");
    model.result("pg7").feature("lngr1").setIndex("legends", "15<sup>\\circ</sup>C", 0);
    model.result("pg7").feature().duplicate("lngr2", "lngr1");
    model.result("pg7").run();
    model.result("pg7").feature("lngr2").set("expr", "comp1.iloc_Al(root.x[V],20[degC])");
    model.result("pg7").feature("lngr2").setIndex("legends", "20<sup>\\circ</sup>C", 0);
    model.result("pg7").feature().duplicate("lngr3", "lngr2");
    model.result("pg7").run();
    model.result("pg7").feature("lngr3").set("expr", "comp1.iloc_Al(root.x[V],25[degC])");
    model.result("pg7").feature("lngr3").setIndex("legends", "25<sup>\\circ</sup>C", 0);
    model.result("pg7").run();

    model.title("\u94dd\u9633\u6781\u6c27\u5316");

    model
         .description("\u5728\u5bf9\u94dd\u4f5c\u9633\u6781\u6c27\u5316\u5904\u7406\u65f6\uff0c\u5176\u8868\u9762\u4f1a\u53d1\u751f\u7535\u5316\u5b66\u53cd\u5e94\uff0c\u5f62\u6210\u4e00\u5c42\u8010\u78e8\u4e14\u6297\u8150\u8680\u7684 Al\u2082O\u2083 \u819c\u3002\u5728\u6b64\u8fc7\u7a0b\u4e2d\uff0c\u968f\u7740\u6c27\u5316\u5c42\u7684\u589e\u957f\uff0c\u7535\u6781\u52a8\u529b\u5b66\u4ec5\u53d7\u5230\u8f7b\u5fae\u5f71\u54cd\uff0c\u56e0\u6b64\uff0c\u4e3a\u786e\u5b9a\u8be5\u5c42\u539a\u5ea6\u7684\u5747\u5300\u6027\uff0c\u5bf9\u7535\u6d41\u5206\u5e03\u8fdb\u884c\u7a33\u6001\u5206\u6790\u5c31\u8db3\u591f\u4e86\u3002\n\n\u6839\u636e\u5b9e\u9a8c\u6781\u5316\u6570\u636e\u5f97\u5230\u7684\u9633\u6781\u52a8\u529b\u5b66\u7528\u4e8e\u7814\u7a76\u5728\u6307\u5b9a\u5e73\u5747\u7535\u6d41\u5bc6\u5ea6\u4e0b\uff0c\u9633\u6781\u6c27\u5316\u7535\u6d41\u5bc6\u5ea6\u5728\u4e09\u79cd\u4e0d\u540c\u6e29\u5ea6\u4e0b\u7684\u5747\u5300\u6027\u3002\u5728\u6e29\u5ea6\u8f83\u9ad8\u7684\u60c5\u51b5\u4e0b\uff0c\u7535\u6c60\u7535\u4f4d\u964d\u4f4e\uff0c\u540c\u65f6\u7535\u6d41\u5206\u5e03\u548c\u539a\u5ea6\u53d8\u5f97\u4e0d\u591f\u5747\u5300\u3002\u8fd9\u5f52\u56e0\u4e8e\u6e29\u5ea6\u8f83\u9ad8\u65f6\u6d3b\u5316\u635f\u5931\u8f83\u5c0f\uff08\u66f4\u5feb\u7684\u52a8\u529b\u5b66\u7279\u6027\uff09\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("al_anodization.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
