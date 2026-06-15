/*
 * outgassing_pipes.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:16 by COMSOL 6.3.0.290. */
public class outgassing_pipes {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Molecular_Flow_Module\\Benchmarks");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("fmf", "FreeMolecularFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/fmf", true);

    model.param().set("T0", "293.15[K]");
    model.param().descr("T0", "\u6e29\u5ea6");
    model.param().set("Mn0", "0.028[kg/mol]");
    model.param().descr("Mn0", "\u6469\u5c14\u8d28\u91cf");
    model.param().set("ps", "30[l/s]");
    model.param().descr("ps", "\u6cf5\u901f");

    model.component("comp1").geom("geom1").lengthUnit("cm");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 2);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 400);
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "x");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", 5);
    model.component("comp1").geom("geom1").feature("cyl2").set("h", 100);
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new int[]{400, 0, 0});
    model.component("comp1").geom("geom1").feature("cyl2").set("axistype", "x");
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("cyl3", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl3").set("r", 2);
    model.component("comp1").geom("geom1").feature("cyl3").set("h", 2);
    model.component("comp1").geom("geom1").feature("cyl3").set("pos", new int[]{100, 0, -2});
    model.component("comp1").geom("geom1").run("cyl3");
    model.component("comp1").geom("geom1").create("cyl4", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl4").set("r", 2);
    model.component("comp1").geom("geom1").feature("cyl4").set("h", 100);
    model.component("comp1").geom("geom1").feature("cyl4").set("pos", new int[]{300, 0, -100});
    model.component("comp1").geom("geom1").run("cyl4");
    model.component("comp1").geom("geom1").create("cyl5", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl5").set("r", 2);
    model.component("comp1").geom("geom1").feature("cyl5").set("h", 4);
    model.component("comp1").geom("geom1").feature("cyl5").set("pos", new int[]{98, 0, 0});
    model.component("comp1").geom("geom1").feature("cyl5").set("axistype", "x");
    model.component("comp1").geom("geom1").run("cyl5");
    model.component("comp1").geom("geom1").feature().duplicate("cyl6", "cyl5");
    model.component("comp1").geom("geom1").feature("cyl6").set("pos", new int[]{298, 0, 0});
    model.component("comp1").geom("geom1").run("cyl6");
    model.component("comp1").geom("geom1").create("cyl7", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl7").set("r", 2);
    model.component("comp1").geom("geom1").feature("cyl7").set("h", 2);
    model.component("comp1").geom("geom1").feature("cyl7").set("pos", new int[]{300, 0, -2});
    model.component("comp1").geom("geom1").run("cyl7");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("uni1").selection("input")
         .set("cyl1", "cyl2", "cyl3", "cyl4", "cyl5", "cyl6", "cyl7");
    model.component("comp1").geom("geom1").run("uni1");

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1")
         .set("table", new String[][]{{"0", "7.01409E-10"}, 
         {"10.0446", "7.01409E-10"}, 
         {"22.3214", "6.90021E-10"}, 
         {"32.9241", "6.75125E-10"}, 
         {"45.7589", "6.49825E-10"}, 
         {"59.1518", "6.22069E-10"}, 
         {"70.8705", "5.79473E-10"}, 
         {"82.5893", "5.36856E-10"}, 
         {"92.6339", "5.00095E-10"}, 
         {"100.446", "4.58287E-10"}, 
         {"107.143", "5.33935E-10"}, 
         {"114.397", "6.28895E-10"}, 
         {"122.768", "7.09106E-10"}, 
         {"132.813", "8.2166E-10"}, 
         {"141.183", "9.164E-10"}, 
         {"152.344", "1.03893E-09"}, 
         {"160.156", "1.13371E-09"}, 
         {"172.991", "1.23713E-09"}, 
         {"185.268", "1.37227E-09"}, 
         {"198.661", "1.48931E-09"}, 
         {"212.054", "1.57283E-09"}, 
         {"225.446", "1.69769E-09"}, 
         {"242.188", "1.79289E-09"}, 
         {"257.813", "1.88314E-09"}, 
         {"276.228", "1.97792E-09"}, 
         {"293.527", "2.01056E-09"}, 
         {"303.013", "2.05493E-09"}, 
         {"330.357", "2.54222E-09"}, 
         {"357.143", "2.85086E-09"}, 
         {"383.371", "3.24974E-09"}, 
         {"399.554", "3.50771E-09"}, 
         {"421.317", "3.50771E-09"}, 
         {"445.313", "3.5462E-09"}, 
         {"481.027", "3.5462E-09"}, 
         {"497.768", "3.5656E-09"}});
    model.component("comp1").func().create("int2", "Interpolation");
    model.component("comp1").func("int2")
         .set("table", new String[][]{{"1.61464", "6.71497E-10"}, 
         {"8.61141", "6.96253E-10"}, 
         {"28.5253", "7.0641E-10"}, 
         {"31.7546", "7.32453E-10"}, 
         {"40.9042", "6.76377E-10"}, 
         {"48.4392", "6.76377E-10"}, 
         {"49.5156", "6.9123E-10"}, 
         {"58.6652", "6.76377E-10"}, 
         {"67.8149", "6.76377E-10"}, 
         {"74.8116", "6.20087E-10"}, 
         {"83.423", "6.11172E-10"}, 
         {"84.4995", "5.89441E-10"}, 
         {"96.3401", "4.95414E-10"}, 
         {"103.337", "4.95414E-10"}, 
         {"111.948", "6.24594E-10"}, 
         {"120.022", "6.81292E-10"}, 
         {"132.939", "9.50577E-10"}, 
         {"139.935", "1E-09"}, 
         {"153.391", "1.17269E-09"}, 
         {"159.311", "1.17269E-09"}, 
         {"170.614", "1.31673E-09"}, 
         {"179.225", "1.2978E-09"}, 
         {"191.604", "1.55533E-09"}, 
         {"198.601", "1.57802E-09"}, 
         {"218.515", "1.90491E-09"}, 
         {"228.202", "1.90491E-09"}, 
         {"234.661", "1.98949E-09"}, 
         {"239.505", "2.01851E-09"}, 
         {"241.658", "2.13889E-09"}, 
         {"249.193", "2.12346E-09"}, 
         {"256.189", "2.21775E-09"}, 
         {"264.801", "2.21775E-09"}, 
         {"266.954", "2.13889E-09"}, 
         {"270.721", "2.20175E-09"}, 
         {"274.489", "2.21775E-09"}, 
         {"276.103", "2.17009E-09"}, 
         {"283.638", "2.28292E-09"}, 
         {"293.864", "2.29951E-09"}, 
         {"299.785", "2.40161E-09"}, 
         {"303.552", "2.40161E-09"}, 
         {"315.931", "2.73594E-09"}, 
         {"334.769", "3.16228E-09"}, 
         {"355.221", "3.52509E-09"}, 
         {"370.829", "3.76246E-09"}, 
         {"379.44", "4.104E-09"}, 
         {"388.59", "4.16387E-09"}, 
         {"405.813", "4.44425E-09"}, 
         {"496.233", "4.47655E-09"}});

    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("fmf").feature("fmfp1").setIndex("Mn_G", "Mn0", 0);
    model.component("comp1").physics("fmf").feature("st1").set("T", "T0");
    model.component("comp1").physics("fmf").feature("wall1").set("BCType", "OutgassingWall");
    model.component("comp1").physics("fmf").feature("wall1").set("BoundaryCondition", "ThermalDesorptionRate");
    model.component("comp1").physics("fmf").create("pmp1", "VacuumPump", 2);
    model.component("comp1").physics("fmf").feature("pmp1").selection().set(10, 12);
    model.component("comp1").physics("fmf").feature("pmp1").set("SpecifyPump", "PumpSpeed");
    model.component("comp1").physics("fmf").feature("pmp1").setIndex("pspeed", "ps", 0);
    model.component("comp1").physics("fmf").feature().duplicate("pmp2", "pmp1");
    model.component("comp1").physics("fmf").feature("pmp2").selection().set(26);

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection()
         .set(9, 10, 12, 13, 15, 18, 22, 26, 29, 30, 32, 34, 37, 38, 43, 44, 46, 48, 54, 60, 63, 64, 67, 69, 72, 73, 75, 76, 77, 79, 80, 81);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 2);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection()
         .set(2, 3, 4, 5, 18, 19, 20, 21, 24, 25, 31, 34, 37, 38, 39, 40, 42, 43, 44, 45);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(3, 51, 65, 74);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 80);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(31);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 160);
    model.component("comp1").mesh("mesh1").run("map1");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().remaining();
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u5165\u5c04\u5206\u5b50\u901a\u91cf (fmf)");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("expr", "fmf.Gtot");
    model.result("pg1").feature("surf1").set("resolution", "norefine");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u603b\u6570\u5bc6\u5ea6 (fmf)");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "fmf.ntot");
    model.result("pg2").feature("surf1").set("resolution", "norefine");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u603b\u538b (fmf)");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "fmf.ptot");
    model.result("pg3").feature("surf1").set("resolution", "norefine");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u4f4d\u7f6e (cm)");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u538b\u529b (torr)");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").set("expr", "fmf.ptot");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "x");
    model.result("pg4").feature("lngr1").set("unit", "Torr");
    model.result("pg4").feature("lngr1").selection().set(7, 19, 33, 35, 49, 70, 82);
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("legendmethod", "manual");
    model.result("pg4").feature("lngr1").setIndex("legends", "COMSOL \u4e09\u7ef4\u89d2\u7cfb\u6570", 0);
    model.result("pg4").feature().duplicate("lngr2", "lngr1");
    model.result("pg4").run();
    model.result("pg4").feature("lngr2").set("expr", "int1(x/1[cm])");
    model.result("pg4").feature("lngr2").setIndex("legends", "Howell \u4e00\u7ef4\u4f20\u5bfc\u7387\u6a21\u578b", 0);
    model.result("pg4").feature().duplicate("lngr3", "lngr2");
    model.result("pg4").run();
    model.result("pg4").feature("lngr3").set("expr", "int2(x/1[cm])");
    model.result("pg4").feature("lngr3").setIndex("legends", "Kersevan \u4e09\u7ef4\u8499\u7279\u5361\u7f57", 0);
    model.result("pg4").run();
    model.result("pg4").label("\u538b\u529b\u5206\u5e03");
    model.result("pg4").set("legendpos", "lowerright");
    model.result("pg4").set("axislimits", true);
    model.result("pg4").set("ymin", "1e-10");
    model.result("pg4").set("ymax", "1e-8");
    model.result("pg4").set("ylog", true);
    model.result("pg4").run();

    model.title("\u6392\u6c14\u7ba1");

    model
         .description("\u8fd9\u4e2a\u57fa\u51c6\u6a21\u578b\u53ef\u4ee5\u8ba1\u7b97\u4e00\u4e2a\u957f\u5f84\u6bd4\u8f83\u5927\u7684\u6392\u6c14\u7ba1\u4e2d\u7684\u538b\u529b\u3002\u7ed3\u679c\u4e0e\u4e00\u7ef4\u4eff\u771f\u6a21\u578b\u4ee5\u53ca\u6587\u732e\u4e2d\u8499\u7279\u5361\u7f57\u4eff\u771f\u7684\u7ed3\u679c\u8fdb\u884c\u4e86\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("outgassing_pipes.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
