/*
 * in_plane_switching_liquid_crystal_cell.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:39 by COMSOL 6.3.0.290. */
public class in_plane_switching_liquid_crystal_cell {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Modulators_and_Switches");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("w", "WeakFormPDE", "geom1");
    model.component("comp1").physics("w").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("w").feature("wfeq1")
         .set("weak", new String[][]{{"-test(ux)*ux-test(uy)*uy+1[m^-2]*test(u)"}});
    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics().create("ewfd", "ElectromagneticWavesFrequencyDomain", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/w", true);
    model.study("std1").feature("stat").setSolveFor("/physics/es", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ewfd", true);

    model.param().label("\u901a\u7528\u53c2\u6570");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("V0", "2[V]", "Applied potential");
    model.param().set("lda0", "632[nm]", "Wavelength");
    model.param().create("par2");
    model.param("par2").label("\u6750\u6599\u53c2\u6570");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("theta_anchoring", "2[deg]", "Anchoring angle, from x-axis in x-y plane");
    model.param("par2").set("phi_anchoring", "80[deg]", "Anchoring angle, from x-y plane");
    model.param("par2").set("n_o", "1.4771", "Refractive index, ordinary polarization");
    model.param("par2").set("n_e", "1.5621", "Refractive index, extraordinary polarization");
    model.param("par2").set("eps_o", "n_o^2", "Relative permittivity, ordinary polarization");
    model.param("par2").set("eps_e", "n_e^2", "Relative permittivity, extraordinary polarization");
    model.param("par2").set("eps_ortho", "5.2", "Dielectric constant, perpendicular to directors");
    model.param("par2").set("delta_eps", "10.3", "Dielectric constant difference");
    model.param("par2").set("eps_parallel", "delta_eps+eps_ortho", "Dielectric constant, along directors");
    model.param("par2").set("K_11", "9.2[pN]", "Splay elastic constant");
    model.param("par2").set("K_22", "6.1[pN]", "Twist elastic constant");
    model.param("par2").set("K_33", "14.6[pN]", "Bend elastic constant");
    model.param().create("par3");
    model.param("par3").label("\u51e0\u4f55\u53c2\u6570");

//    To import content from file, use:
//    model.param("par3").loadFile("FILENAME");
    model.param("par3").set("t_LC", "4[um]", "Thickness, liquid crystal cell");
    model.param("par3").set("d_1", "4[um]", "Applied potential electrode width");
    model.param("par3").set("d_2", "d_1", "Ground electrode width");
    model.param("par3").set("d_12", "8[um]", "Electrode separation");
    model.param("par3").set("width", "2*(d_1+d_2)+d_12", "Width");
    model.param("par3").set("height", "t_LC+2*(t_glass+t_PML)", "Height");
    model.param("par3").set("t_glass", "lda0", "Thickness, glass layer");
    model.param("par3").set("t_PML", "lda0", "Thickness, Perfectly Matched Layer");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"width", "height"});
    model.component("comp1").geom("geom1").feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "t_PML", 0);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "t_glass", 1);
    model.component("comp1").geom("geom1").feature("r1").set("layertop", true);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-d_12/2-d_1", 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-t_LC/2", 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-d_12/2", 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-t_LC/2", 1, 1);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u7535\u52bf");
    model.component("comp1").geom("geom1").feature("pol1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").feature().duplicate("pol2", "pol1");
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "d_12/2", 0, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "d_12/2+d_2", 1, 0);
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u63a5\u5730");
    model.component("comp1").geom("geom1").feature("pol2").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u6db2\u6676");
    model.component("comp1").selection("sel1").set(3);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("PML");
    model.component("comp1").selection("sel2").set(1, 5);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u73bb\u7483");
    model.component("comp1").selection("sel3").set(1, 2, 4, 5);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u5de6\u5468\u671f\u6027\u8fb9\u754c");
    model.component("comp1").selection("sel4").geom(1);
    model.component("comp1").selection("sel4").set(1, 3, 5, 7, 9);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("\u53f3\u5468\u671f\u6027\u8fb9\u754c");
    model.component("comp1").selection("sel5").geom(1);
    model.component("comp1").selection("sel5").set(16, 17, 18, 19, 20);
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u5468\u671f\u6027\u8fb9\u754c");
    model.component("comp1").selection("uni1").set("entitydim", 1);
    model.component("comp1").selection("uni1").set("input", new String[]{"sel4", "sel5"});
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").label("PML \u8fb9\u754c");
    model.component("comp1").selection("adj1").set("input", new String[]{"sel2"});
    model.component("comp1").selection().create("int1", "Intersection");
    model.component("comp1").selection("int1").label("PML \u5206\u5e03\u8fb9\u754c");
    model.component("comp1").selection("int1").set("entitydim", 1);
    model.component("comp1").selection("int1").set("input", new String[]{"uni1", "adj1"});
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").label("\u975e PML");
    model.component("comp1").selection("dif1").set("add", new String[]{"sel1", "sel3"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"sel2"});
    model.component("comp1").selection().create("uni2", "Union");
    model.component("comp1").selection("uni2").label("\u7535\u6781");
    model.component("comp1").selection("uni2").set("entitydim", 1);
    model.component("comp1").selection("uni2").set("input", new String[]{"geom1_csel1_bnd", "geom1_csel2_bnd"});

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("\u6db2\u6676\u57df\u53d8\u91cf");
    model.component("comp1").variable("var1").selection().geom("geom1", 2);
    model.component("comp1").variable("var1").selection().named("sel1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("nx", "cos(theta)*cos(phi)", "Director, x-component");
    model.component("comp1").variable("var1").set("ny", "sin(theta)*cos(phi)", "Director, y-component");
    model.component("comp1").variable("var1").set("nz", "sin(phi)", "Director, z-component");
    model.component("comp1").variable("var1").set("dnxx", "d(nx,x)", "Director, x-derivative of x-component");
    model.component("comp1").variable("var1").set("dnxy", "d(nx,y)", "Director, y-derivative of x-component");
    model.component("comp1").variable("var1").set("dnxz", "0", "Director, z-derivative of x-component");
    model.component("comp1").variable("var1").set("dnyx", "d(ny,x)", "Director, x-derivative of y-component");
    model.component("comp1").variable("var1").set("dnyy", "d(ny,y)", "Director, y-derivative of y-component");
    model.component("comp1").variable("var1").set("dnyz", "0", "Director, z-derivative of y-component");
    model.component("comp1").variable("var1").set("dnzx", "d(nz,x)", "Director, x-derivative of z-component");
    model.component("comp1").variable("var1").set("dnzy", "d(nz,y)", "Director, y-derivative of z-component");
    model.component("comp1").variable("var1").set("dnzz", "0", "Director, z-derivative of z-component");
    model.component("comp1").variable("var1").set("F1", "0.5*K_11*(dnxx+dnyy+dnzz)^2", "Free energy density, splay");
    model.component("comp1").variable("var1")
         .set("F2", "0.5*K_22*(nx*(dnzy-dnyz)+ny*(dnxz-dnzx)+nz*(dnyx-dnxy))^2", "Free energy density, twist");
    model.component("comp1").variable("var1")
         .set("F3", "0.5*K_33*((ny*(dnyx-dnxy)-nz*(dnxz-dnzx))^2+(nz*(dnzy-dnyz)-nx*(dnyx-dnxy))^2+(nx*(dnxz-dnzx)-ny*(dnzy-dnyz))^2)", "Free energy density, bend");
    model.component("comp1").variable("var1").set("F", "F1+F2+F3", "Free energy density");

    model.component("comp1").physics("w").label("Oseen-Frank");
    model.component("comp1").physics("w").selection().named("sel1");
    model.component("comp1").physics("w").prop("Units").set("DependentVariableQuantity", "planeangle");
    model.component("comp1").physics("w").field("dimensionless").component(new String[]{"u", "u2"});
    model.component("comp1").physics("w").field("dimensionless").component(1, "theta");
    model.component("comp1").physics("w").field("dimensionless").component(2, "phi");
    model.component("comp1").physics("w").feature("wfeq1").setIndex("weak", "test(F)", 0);
    model.component("comp1").physics("w").feature("wfeq1").setIndex("weak", "-var(es.W,theta,phi)", 1);
    model.component("comp1").physics("w").create("dir1", "DirichletBoundary", 1);
    model.component("comp1").physics("w").feature("dir1").label("\u5f3a\u951a\u5b9a");
    model.component("comp1").physics("w").feature("dir1").selection().set(6, 8, 12, 13, 14, 15);
    model.component("comp1").physics("w").feature("dir1").setIndex("r", "theta_anchoring", 0);
    model.component("comp1").physics("w").feature("dir1").setIndex("r", "phi_anchoring", 1);
    model.component("comp1").physics("w").create("pc1", "PeriodicCondition", 1);
    model.component("comp1").physics("w").feature("pc1").selection().named("uni1");
    model.component("comp1").physics("es").selection().named("sel1");
    model.component("comp1").physics("es").create("ccnf1", "ChargeConservationFluid", 2);
    model.component("comp1").physics("es").feature("ccnf1").selection().named("sel1");
    model.component("comp1").physics("es").feature("ccnf1").set("epsilonr_mat", "userdef");
    model.component("comp1").physics("es").feature("ccnf1")
         .set("epsilonr", new String[]{"eps_ortho*(1-nx*nx)+eps_parallel*nx*nx", "eps_ortho*(-nx*ny)+eps_parallel*nx*ny", "eps_ortho*(-nx*nz)+eps_parallel*nx*nz", "eps_ortho*(-nx*ny)+eps_parallel*nx*ny", "eps_ortho*(1-ny*ny)+eps_parallel*ny*ny", "eps_ortho*(-ny*nz)+eps_parallel*ny*nz", "eps_ortho*(-nx*nz)+eps_parallel*nx*nz", "eps_ortho*(-ny*nz)+eps_parallel*ny*nz", "eps_ortho*(1-nz*nz)+eps_parallel*nz*nz"});
    model.component("comp1").physics("es").create("gnd1", "Ground", 1);
    model.component("comp1").physics("es").feature("gnd1").selection().named("geom1_csel2_bnd");
    model.component("comp1").physics("es").create("pot1", "ElectricPotential", 1);
    model.component("comp1").physics("es").feature("pot1").selection().named("geom1_csel1_bnd");
    model.component("comp1").physics("es").feature("pot1").set("V0", "V0");
    model.component("comp1").physics("es").create("pc1", "PeriodicCondition", 1);
    model.component("comp1").physics("es").feature("pc1").selection().named("uni1");

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().named("sel2");

    model.component("comp1").physics("ewfd").create("ps1", "PeriodicStructure", 2);
    model.component("comp1").physics("ewfd").feature("ps1").feature("wee1")
         .set("DisplacementFieldModel", "RelativePermittivity");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u6db2\u6676");
    model.component("comp1").material("mat1").selection().named("sel1");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"eps_o*(1-nx*nx)+eps_e*nx*nx", "eps_o*(-nx*ny)+eps_e*nx*ny", "eps_o*(1-ny*ny)+eps_e*ny*ny", "eps_o*(-nx*nz)+eps_e*nx*nz", "eps_o*(-ny*nz)+eps_e*ny*nz", "eps_o*(1-nz*nz)+eps_e*nz*nz"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("electricconductivity", new String[]{"0"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u73bb\u7483");
    model.component("comp1").material("mat2").selection().named("sel3");
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"n_e"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("electricconductivity", new String[]{"0"});

    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").label("\u7aef\u53e3\u6a21\u573a\u504f\u632f\u53d8\u91cf");
    model.component("comp1").variable("var2").selection().geom("geom1", 2);
    model.component("comp1").variable("var2").selection().named("sel3");

//    To import content from file, use:
//    model.component("comp1").variable("var2").loadFile("FILENAME");
    model.component("comp1").variable("var2")
         .set("nx", "cos(theta_anchoring)*cos(phi_anchoring)", "Director, x-component");
    model.component("comp1").variable("var2")
         .set("ny", "sin(theta_anchoring)*cos(phi_anchoring)", "Director, y-component");
    model.component("comp1").variable("var2").set("nz", "sin(phi_anchoring)", "Director, z-component");

    model.component("comp1").physics("ewfd").feature("ps1").selection("excitedPortSelection").set(4);
    model.component("comp1").physics("ewfd").feature("ps1").set("Polarization", "UserDefined");
    model.component("comp1").physics("ewfd").feature("ps1").set("Eampl", new String[]{"nx", "0", "nz"});

    model.component("comp1").mesh("mesh1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("dis1").selection().named("int1");
    model.component("comp1").mesh("mesh1").feature("dis1").set("numelem", 10);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "lda0/n_e/6");
    model.component("comp1").mesh("mesh1").create("id1", "IdenticalMesh");
    model.component("comp1").mesh("mesh1").feature("id1").selection("group1").named("sel4");
    model.component("comp1").mesh("mesh1").feature("id1").selection("group2").named("sel5");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("dif1");
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "d_1", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "d_1", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "V0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(0[V],0.5[V],5[V])", 0);
    model.study("std1").feature("param").set("reusesol", true);
    model.study("std1").create("wave", "Wavelength");
    model.study("std1").feature("wave").set("plist", "lda0");
    model.study("std1").feature("wave").setSolveFor("/physics/w", false);
    model.study("std1").feature("wave").setSolveFor("/physics/es", false);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std1");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset3");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").setIndex("looplevel", 11, 1);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").label("Oseen-Frank");
    model.result("pg1").feature("surf1").set("expr", "theta");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u7535\u52bf (es)");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").setIndex("looplevel", 11, 1);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solutionparams", "parent");
    model.result("pg2").feature("surf1").set("expr", "V");
    model.result("pg2").feature("surf1").set("colortable", "Dipole");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature().create("str1", "Streamline");
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("solutionparams", "parent");
    model.result("pg2").feature("str1").set("expr", new String[]{"es.Ex", "es.Ey"});
    model.result("pg2").feature("str1").set("titletype", "none");
    model.result("pg2").feature("str1").set("posmethod", "uniform");
    model.result("pg2").feature("str1").set("udist", 0.02);
    model.result("pg2").feature("str1").set("maxlen", 0.4);
    model.result("pg2").feature("str1").set("maxsteps", 5000);
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("inheritcolor", false);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("data", "parent");
    model.result("pg2").feature("str1").selection().geom("geom1", 1);
    model.result("pg2").feature("str1").selection().set(5, 6, 8, 12, 13, 14, 15, 18);
    model.result("pg2").feature("str1").set("inheritplot", "surf1");
    model.result("pg2").feature("str1").feature().create("col1", "Color");
    model.result("pg2").feature("str1").feature("col1").set("expr", "V");
    model.result("pg2").feature("str1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg2").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("str1").feature().create("filt1", "Filter");
    model.result("pg2").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u7535\u573a (es)");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").setIndex("looplevel", 11, 1);
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").feature("surf1").set("expr", "es.normE");
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result("pg3").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").feature().create("str1", "Streamline");
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("solutionparams", "parent");
    model.result("pg3").feature("str1").set("expr", new String[]{"es.Ex", "es.Ey"});
    model.result("pg3").feature("str1").set("titletype", "none");
    model.result("pg3").feature("str1").set("posmethod", "uniform");
    model.result("pg3").feature("str1").set("udist", 0.02);
    model.result("pg3").feature("str1").set("maxlen", 0.4);
    model.result("pg3").feature("str1").set("maxsteps", 5000);
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("inheritcolor", false);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("data", "parent");
    model.result("pg3").feature("str1").selection().geom("geom1", 1);
    model.result("pg3").feature("str1").selection().set(5, 6, 8, 12, 13, 14, 15, 18);
    model.result("pg3").feature("str1").set("inheritplot", "surf1");
    model.result("pg3").feature("str1").feature().create("col1", "Color");
    model.result("pg3").feature("str1").feature("col1").set("expr", "es.normE");
    model.result("pg3").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg3").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg3").feature("str1").feature().create("filt1", "Filter");
    model.result("pg3").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u7535\u573a (ewfd)");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").setIndex("looplevel", 11, 1);
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "ewfd.normE");
    model.result("pg4").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("unit", new String[]{"", "", "", "", "", "", "", ""});
    model.result("pg5").feature("glob1")
         .set("expr", new String[]{"ewfd.Rorder_0", "ewfd.Rorder_0_orth", "ewfd.Rtotal", "ewfd.Torder_0", "ewfd.Torder_0_orth", "ewfd.Ttotal", "ewfd.RTtotal", "ewfd.Atotal"});
    model.result("pg5").feature("glob1")
         .set("descr", new String[]{"\u53cd\u5c04\u7387\uff0c\u9636\u6570 0", "\u53cd\u5c04\u7387\uff0c0 \u9636\uff0c\u6b63\u4ea4", "\u603b\u53cd\u5c04\u7387", "\u900f\u5c04\u7387\uff0c\u9636\u6570 0", "\u900f\u5c04\u7387\uff0c0 \u9636\uff0c\u6b63\u4ea4", "\u603b\u900f\u5c04\u7387", "\u603b\u53cd\u5c04\u7387\u548c\u900f\u5c04\u7387", "\u5438\u6536\u7387"});
    model.result("pg5").label("\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387 (ewfd)");
    model.result("pg5").feature("glob1").set("titletype", "none");
    model.result("pg5").feature("glob1").set("xdata", "expr");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387 (1)");
    model.result("pg5").feature("glob1").set("xdataexpr", "V0");
    model.result("pg5").feature("glob1").set("xdataunit", "V");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("xdatasolnumtype", "all");
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").label("\u6781\u5316\u56fe (ewfd)");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("title", "\u6781\u5316\u72b6\u6001\uff0cColor\uff1aPhase\uff08Radians\uff09");
    model.result("pg6").setIndex("looplevelinput", "manual", 0);
    model.result("pg6").setIndex("looplevelinput", "manual", 1);
    model.result("pg6").setIndex("looplevel", "1", 0);
    model.result("pg6").setIndex("looplevel", "1", 1);
    model.result("pg6").create("plz1", "Polarization");
    model.result("pg6").feature("plz1").set("linestyle", "solid");
    model.result("pg6").feature("plz1").set("linewidth", 2);
    model.result("pg6").feature("plz1").set("display", "0");
    model.result("pg6").feature("plz1").create("col1", "Color");
    model.result("pg6").feature("plz1").feature("col1").set("colortable", "Cyclic");
    model.result("pg6").feature("plz1").feature("col1").set("colorlegend", true);
    model.result("pg6").feature("plz1").set("legend", true);
    model.result("pg6").feature("plz1").set("legendmethod", "manual");
    model.result("pg6").feature("plz1").setIndex("legends", "\u53cd\u5c04", 0);
    model.result("pg6").create("plz2", "Polarization");
    model.result("pg6").feature("plz2").set("linestyle", "dashed");
    model.result("pg6").feature("plz2").set("linewidth", 2);
    model.result("pg6").feature("plz2").set("display", "1");
    model.result("pg6").feature("plz2").create("col1", "Color");
    model.result("pg6").feature("plz2").feature("col1").set("colortable", "Cyclic");
    model.result("pg6").feature("plz2").feature("col1").set("colorlegend", false);
    model.result("pg6").feature("plz2").set("legend", true);
    model.result("pg6").feature("plz2").set("legendmethod", "manual");
    model.result("pg6").feature("plz2").setIndex("legends", "\u4f20\u8f93", 0);
    model.result("pg1").run();
    model.result().dataset("dset3").selection().geom("geom1", 2);
    model.result().dataset("dset3").selection().named("dif1");
    model.result("pg1").run();
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").set("plotarrayenable", true);
    model.result("pg1").set("arrayaxis", "y");
    model.result("pg1").feature("surf1").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("unit", "\u00b0");
    model.result("pg1").feature("surf1").set("colortable", "Dipole");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").feature().duplicate("surf2", "surf1");
    model.result("pg1").feature("surf2").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").set("expr", "phi");
    model.result("pg1").feature("surf2").set("inheritplot", "surf1");
    model.result("pg1").run();
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").set("arraydim", "1");
    model.result("pg1").feature("arws1").set("expr", new String[]{"nx", "ny"});
    model.result("pg1").feature("arws1").set("xnumber", 25);
    model.result("pg1").run();
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("arraydim", "1");
    model.result("pg1").feature("line1").set("expr", "1");
    model.result("pg1").feature("line1").set("linetype", "tube");
    model.result("pg1").feature("line1").set("radiusexpr", "2");
    model.result("pg1").feature("line1").set("coloring", "uniform");
    model.result("pg1").feature("line1").set("color", "black");
    model.result("pg1").feature("line1").set("manualindexing", true);
    model.result("pg1").feature("line1").create("sel1", "Selection");
    model.result("pg1").feature("line1").feature("sel1").selection().named("uni2");
    model.result("pg1").feature("line1").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("line2", "line1");
    model.result("pg1").feature("line2").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("line2").set("arrayindex", 1);
    model.result("pg1").feature().duplicate("line3", "line2");
    model.result("pg1").feature("line3").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("line3").set("arrayindex", 2);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").stepFirst(1);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg4").run();
    model.result("pg4").set("xlabelactive", true);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg4").set("xlabel", "x");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "y");
    model.result("pg4").set("plotarrayenable", true);
    model.result("pg4").set("arrayaxis", "y");
    model.result("pg4").feature("surf1").set("arraydim", "1");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "nx*ewfd.Ex+nz*ewfd.Ez");
    model.result("pg4").feature("surf1").set("colortable", "WaveLight");
    model.result("pg4").feature().duplicate("surf2", "surf1");
    model.result("pg4").feature("surf2").set("arraydim", "1");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").set("expr", "-nz*ewfd.Ex+nx*ewfd.Ez");
    model.result("pg4").feature("surf2").set("inheritplot", "surf1");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").stepFirst(1);
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").label("\u900f\u5c04\u7387 (ewfd)");
    model.result("pg5").set("ylabel", "\u900f\u5c04\u7387 (1)");
    model.result("pg5").run();
    model.result("pg5").feature("glob1").remove("unit", new int[]{0, 1, 2, 5, 6, 7});
    model.result("pg5").feature("glob1").remove("descr", new int[]{0, 1, 2, 5, 6, 7});
    model.result("pg5").feature("glob1").remove("expr", new int[]{0, 1, 2, 5, 6, 7});
    model.result("pg5").feature("glob1").setIndex("expr", "1-ewfd.RTtotal", 2);
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", new int[]{1, 10}, 1);
    model.result("pg6").run();
    model.result("pg6").feature().remove("plz1");
    model.result("pg6").run();
    model.result("pg6").feature("plz2").set("linestyle", "cycle");
    model.result("pg6").feature("plz2").setIndex("legends", "0 V", 0);
    model.result("pg6").feature("plz2").setIndex("legends", "4.5 V", 1);
    model.result("pg6").run();
    model.result().dataset().duplicate("dset4", "dset3");
    model.result().dataset("dset4").selection().named("sel1");
    model.result().dataset().create("extr1", "Extrude2D");
    model.result().dataset("extr1").set("data", "dset4");
    model.result().dataset("extr1").set("zmax", "t_LC");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").run();
    model.result("pg7").label("\u6307\u5411\u77e2\u548c\u5916\u52a0\u7535\u573a");
    model.result("pg7").create("arwv1", "ArrowVolume");
    model.result("pg7").feature("arwv1").set("expr", new String[]{"nx", "ny", "nz"});
    model.result("pg7").feature("arwv1").set("xnumber", 11);
    model.result("pg7").feature("arwv1").set("ynumber", 9);
    model.result("pg7").feature("arwv1").set("znumber", 1);
    model.result("pg7").feature("arwv1").set("color", "custom");
    model.result("pg7").feature("arwv1")
         .set("customcolor", new double[]{0.4117647111415863, 0.4117647111415863, 0.4117647111415863});
    model.result("pg7").run();
    model.result("pg7").create("slc1", "Slice");
    model.result("pg7").feature("slc1").set("expr", "V");
    model.result("pg7").feature("slc1").set("descr", "\u7535\u52bf");
    model.result("pg7").feature("slc1").set("quickplane", "xy");
    model.result("pg7").feature("slc1").set("quickznumber", 1);
    model.result("pg7").feature("slc1").set("colortable", "Dipole");
    model.result("pg7").run();
    model.result("pg7").set("edges", false);

    model.view("view2").set("showgrid", false);

    model.result("pg7").create("str1", "Streamline");
    model.result("pg7").feature("str1").set("expr", new String[]{"es.Ex", "es.Ey", "es.Ez"});
    model.result("pg7").feature("str1").set("descr", "\u7535\u573a");
    model.result("pg7").feature("str1").set("posmethod", "uniform");
    model.result("pg7").feature("str1").set("color", "gray");
    model.result("pg7").run();

    model.title("\u6db2\u6676\u5355\u5143\u7684\u9762\u5185\u5207\u6362");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u6db2\u6676 (LC) \u663e\u793a\u5355\u5143\u5728\u9762\u5185\u5207\u6362 (IPS) \u914d\u7f6e\u4e0b\u7684\u5207\u6362\u80fd\u529b\u3002\n\n\u672c\u4f8b\u4f7f\u7528 Oseen-Frank \u6a21\u578b\u6c42\u89e3\u65bd\u52a0\u9759\u7535\u573a\u65f6\u7684\u6db2\u6676\u6307\u5411\u77e2\uff08\u5149\u8f74\uff09\u5206\u5e03\u3002\u901a\u8fc7\u201c\u5f31\u5f62\u5f0f\u504f\u5fae\u5206\u65b9\u7a0b\u201d\u63a5\u53e3\u5b9a\u4e49 Oseen-Frank \u5f31\u65b9\u7a0b\uff0c\u5e76\u4f7f\u7528\u201c\u9759\u7535\u201d\u63a5\u53e3\u6c42\u89e3\u7535\u4f4d\u5206\u5e03\u3002\n\n\u5bf9\u4e8e\u7ed9\u5b9a\u7684\u975e\u5747\u5300\u5404\u5411\u5f02\u6027\u6db2\u6676\u6750\u6599\uff0c\u4f7f\u7528\u201c\u7535\u78c1\u6ce2\uff0c\u9891\u57df\u201d\u63a5\u53e3\u8fdb\u884c\u4e86\u5168\u6ce2\u4eff\u771f\u3002\u7ed3\u679c\u663e\u793a\uff0c\u5f53\u504f\u632f\u5668\u548c\u5206\u6790\u5668\u6b63\u4ea4\u5b9a\u5411\u65f6\uff0c\u6db2\u6676\u5355\u5143\u5728\u900f\u5c04\u65b9\u9762\u5c55\u73b0\u51fa\u8f83\u5927\u7684\u52a8\u6001\u8303\u56f4\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();
    model.sol("sol13").clearSolutionData();
    model.sol("sol14").clearSolutionData();

    model.label("in_plane_switching_liquid_crystal_cell.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
