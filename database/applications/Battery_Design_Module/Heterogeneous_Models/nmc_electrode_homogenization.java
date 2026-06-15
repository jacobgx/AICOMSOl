/*
 * nmc_electrode_homogenization.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:19 by COMSOL 6.3.0.290. */
public class nmc_electrode_homogenization {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Battery_Design_Module\\Heterogeneous_Models");

    model.param().label("\u51e0\u4f55\u53c2\u6570");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("vox_size", "0.37[um]", "\u8868\u6570\u636e\u4e2d\u7684\u4f53\u7d20\u5927\u5c0f");
    model.param().set("xmin", "290*vox_size", "\u8fb9\u6846\u53c2\u6570");
    model.param().set("xmax", "365*vox_size", "\u8fb9\u6846\u53c2\u6570");
    model.param().set("ymin", "190*vox_size", "\u8fb9\u6846\u53c2\u6570");
    model.param().set("ymax", "265*vox_size", "\u8fb9\u6846\u53c2\u6570");
    model.param()
         .set("geom_tol", "2*vox_size", "\u51e0\u4f55\u5bb9\u5dee\uff08\u6700\u5c0f\u9897\u7c92\u534a\u5f84\uff09");
    model.param().set("hmin", "0.25*vox_size", "\u6700\u5c0f\u7f51\u683c\u5355\u5143\u5927\u5c0f");
    model.param()
         .set("hmax", "min((xmax-xmin)/4,(ymax-ymin)/4)", "\u6700\u5927\u7f51\u683c\u5355\u5143\u5927\u5c0f");
    model.param().set("Vp", "2.257553763110658E-14[m^3]", "\u9897\u7c92\u4f53\u79ef");
    model.param().set("L_sep", "30[um]", "\u9694\u819c\u539a\u5ea6");
    model.param().set("L_elec", "9.524364283974882E-5[m]", "\u7535\u6781\u539a\u5ea6");
    model.param()
         .set("rp_avg_spheres", "8.18518101407029E-6[m]", "\u6240\u6dfb\u52a0\u7403\u4f53\u7684\u8d28\u91cf\u5e73\u5747\u534a\u5f84");
    model.param().set("A_cross", "(xmax-xmin)*(ymax-ymin)", "\u7535\u6781\u6a2a\u622a\u9762\u79ef");
    model.param()
         .set("eps_particles", "Vp/(A_cross*L_elec)", "\u6d3b\u6027 NMC \u9897\u7c92\u7684\u4f53\u79ef\u5206\u6570");
    model.param()
         .set("eps_binder", "1-eps_particles", "\u591a\u5b54\u5bfc\u7535\u9ecf\u5408\u5242\u7684\u4f53\u79ef\u5206\u6570");
    model.param().set("Ap", "1e-8[m^2]", "\u7535\u5316\u5b66\u6d3b\u6027\u9897\u7c92\u8868\u9762\u79ef");
    model.param().set("Av_particles", "Ap/(L_elec*A_cross)", "\u6bd4\u8868\u9762\u79ef");

    model.geom().create("part1", "Part", 3);
    model.geom("part1").geomRep("comsol");
    model.geom("part1").label("\u9897\u7c92");
    model.geom("part1").create("sph1", "Sphere");
    model.geom("part1").feature("sph1").set("r", "2e-6");
    model.geom("part1").feature("sph1").set("pos", new String[]{"11e-5", "7e-5", "4e-6"});
    model.geom("part1").run("sph1");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").label("\u9897\u7c92\u90e8\u4ef6");
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").label("\u9897\u7c92");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("pi1");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("uni1").set("selresult", true);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"xmax-xmin", "ymax-ymin", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "L_elec+L_sep", 2);
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"xmin", "ymin", "0"});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("par1").selection("tool").set("blk1");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmin", "xmin+geom_tol");
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmax", "xmax-geom_tol");
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymin", "ymin+geom_tol");
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymax", "ymax-geom_tol");
    model.component("comp1").geom("geom1").feature("boxsel1").set("selshow", false);
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("comsel1", "ComplementSelection");
    model.component("comp1").geom("geom1").feature("comsel1").set("input", new String[]{"boxsel1"});
    model.component("comp1").geom("geom1").feature("comsel1").set("selshow", false);
    model.component("comp1").geom("geom1").run("comsel1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").named("comsel1");
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").label("\u7535\u6781");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"xmax-xmin", "ymax-ymin", "1"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("size", "L_elec", 2);
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"xmin", "ymin", "0"});
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("blk2");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("del1");
    model.component("comp1").geom("geom1").feature("dif1").set("keepadd", true);
    model.component("comp1").geom("geom1").feature("dif1").set("keepsubtract", true);
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("del2", "Delete");
    model.component("comp1").geom("geom1").feature("del2").selection("input").init();
    model.component("comp1").geom("geom1").feature("del2").selection("input").set("dif1");
    model.component("comp1").geom("geom1").feature("del2").selection("input").init();
    model.component("comp1").geom("geom1").feature("del2").selection("input").set("blk2");
    model.component("comp1").geom("geom1").run("del2");
    model.component("comp1").geom("geom1").create("blk3", "Block");
    model.component("comp1").geom("geom1").feature("blk3").label("\u9694\u819c");
    model.component("comp1").geom("geom1").feature("blk3").set("size", new String[]{"xmax-xmin", "ymax-ymin", "1"});
    model.component("comp1").geom("geom1").feature("blk3").setIndex("size", "L_sep", 2);
    model.component("comp1").geom("geom1").feature("blk3").set("pos", new String[]{"xmin", "ymin", "L_elec"});
    model.component("comp1").geom("geom1").feature("blk3").set("selresult", true);
    model.component("comp1").geom("geom1").run("blk3");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("comsel2", "ComplementSelection");
    model.component("comp1").geom("geom1").feature("comsel2").label("\u591a\u5b54\u5bfc\u7535\u9ecf\u5408\u5242");
    model.component("comp1").geom("geom1").feature("comsel2").set("input", new String[]{"uni1", "blk3"});
    model.component("comp1").geom("geom1").run("comsel2");
    model.component("comp1").geom("geom1").create("boxsel2", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel2").label("\u9502\u7b94");
    model.component("comp1").geom("geom1").feature("boxsel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel2").set("zmin", "L_elec+L_sep/2");
    model.component("comp1").geom("geom1").feature("boxsel2").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel2");
    model.component("comp1").geom("geom1").create("boxsel3", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel3").label("NMC \u96c6\u6d41\u4f53");
    model.component("comp1").geom("geom1").feature("boxsel3").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel3").set("zmax", 0);
    model.component("comp1").geom("geom1").feature("boxsel3").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel3");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1").label("\u9897\u7c92\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"uni1"});
    model.component("comp1").geom("geom1").feature("adjsel1").set("selshow", false);
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").create("adjsel2", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel2").label("\u9ecf\u5408\u5242\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("adjsel2").set("input", new String[]{"comsel2"});
    model.component("comp1").geom("geom1").feature("adjsel2").set("selshow", false);
    model.component("comp1").geom("geom1").run("adjsel2");
    model.component("comp1").geom("geom1").create("intsel1", "IntersectionSelection");
    model.component("comp1").geom("geom1").feature("intsel1").label("\u9897\u7c92\u8868\u9762");
    model.component("comp1").geom("geom1").feature("intsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("intsel1").set("input", new String[]{"adjsel1", "adjsel2"});
    model.component("comp1").geom("geom1").run("intsel1");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("\u9694\u819c + \u9ecf\u5408\u5242");
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"blk3", "comsel2"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("boxsel4", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel4").label("\u6240\u6709\u57df");
    model.component("comp1").geom("geom1").feature("boxsel4").set("xmin", "xmin");
    model.component("comp1").geom("geom1").feature("boxsel4").set("xmax", "xmax");
    model.component("comp1").geom("geom1").feature("boxsel4").set("ymin", "ymin");
    model.component("comp1").geom("geom1").feature("boxsel4").set("ymax", "ymax");
    model.component("comp1").geom("geom1").feature("boxsel4").set("condition", "allvertices");
    model.component("comp1").geom("geom1").feature("boxsel4").set("selshow", false);
    model.component("comp1").geom("geom1").run("boxsel4");
    model.component("comp1").geom("geom1").create("adjsel3", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel3").label("\u6240\u6709\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("adjsel3").set("input", new String[]{"boxsel4"});

    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").label("\u9897\u7c92\u6570\u636e\u8868");
    model.result().table("tbl1")
         .importData("z:\\hub\\build\\patch\\daily\\locales\\zh_CN\\applications.compact\\Battery_Design_Module\\Heterogeneous_Models\\nmc_electrode_particle_data.txt");

//    Started method call GenerateGeometryFromTable

    model.geom("part1").feature().clear();
    model.geom("part1").create("sph1", "Sphere");
    model.geom("part1").feature("sph1")
         .set("pos", new String[]{"1.1963579999999999E-4[m]", "7.904679999999999E-5[m]", "2.067967E-5[m]"});
    model.geom("part1").feature("sph1").label("Particle 2994");
    model.geom("part1").feature("sph1").set("r", "3.5974757475150135E-6[m]");
    model.geom("part1").create("sph2", "Sphere");
    model.geom("part1").feature("sph2")
         .set("pos", new String[]{"1.103599E-4[m]", "7.15025E-5[m]", "2.2216279999999998E-5[m]"});
    model.geom("part1").feature("sph2").label("Particle 3150");
    model.geom("part1").feature("sph2").set("r", "5.027804607290078E-6[m]");
    model.geom("part1").create("sph3", "Sphere");
    model.geom("part1").feature("sph3")
         .set("pos", new String[]{"1.301401E-4[m]", "6.46649E-5[m]", "2.318161E-5[m]"});
    model.geom("part1").feature("sph3").label("Particle 3316");
    model.geom("part1").feature("sph3").set("r", "1.4644456085455302E-5[m]");
    model.geom("part1").create("sph4", "Sphere");
    model.geom("part1").feature("sph4")
         .set("pos", new String[]{"1.361933E-4[m]", "9.36988E-5[m]", "2.339658E-5[m]"});
    model.geom("part1").feature("sph4").label("Particle 3368");
    model.geom("part1").feature("sph4").set("r", "5.675687245402173E-6[m]");
    model.geom("part1").create("sph5", "Sphere");
    model.geom("part1").feature("sph5")
         .set("pos", new String[]{"1.2179290000000001E-4[m]", "1.0076579999999999E-4[m]", "2.7885790000000003E-5[m]"});
    model.geom("part1").feature("sph5").label("Particle 3479");
    model.geom("part1").feature("sph5").set("r", "7.74676095038691E-6[m]");
    model.geom("part1").create("sph6", "Sphere");
    model.geom("part1").feature("sph6")
         .set("pos", new String[]{"1.39379E-4[m]", "8.2399E-5[m]", "2.4990910000000002E-5[m]"});
    model.geom("part1").feature("sph6").label("Particle 3551");
    model.geom("part1").feature("sph6").set("r", "3.17313554431178E-6[m]");
    model.geom("part1").create("sph7", "Sphere");
    model.geom("part1").feature("sph7")
         .set("pos", new String[]{"1.165278E-4[m]", "7.347460000000001E-5[m]", "2.5893710000000003E-5[m]"});
    model.geom("part1").feature("sph7").label("Particle 3680");
    model.geom("part1").feature("sph7").set("r", "2.597967858364814E-6[m]");
    model.geom("part1").create("sph8", "Sphere");
    model.geom("part1").feature("sph8")
         .set("pos", new String[]{"1.26651E-4[m]", "8.82746E-5[m]", "2.6195999999999998E-5[m]"});
    model.geom("part1").feature("sph8").label("Particle 3683");
    model.geom("part1").feature("sph8").set("r", "2.202071482297025E-6[m]");
    model.geom("part1").create("sph9", "Sphere");
    model.geom("part1").feature("sph9")
         .set("pos", new String[]{"1.1649820000000001E-4[m]", "8.19254E-5[m]", "2.644649E-5[m]"});
    model.geom("part1").feature("sph9").label("Particle 3791");
    model.geom("part1").feature("sph9").set("r", "3.1898617016171288E-6[m]");
    model.geom("part1").create("sph10", "Sphere");
    model.geom("part1").feature("sph10")
         .set("pos", new String[]{"1.2930019999999999E-4[m]", "9.538230000000001E-5[m]", "2.950306E-5[m]"});
    model.geom("part1").feature("sph10").label("Particle 4282");
    model.geom("part1").feature("sph10").set("r", "1.8840233875368028E-6[m]");
    model.geom("part1").create("sph11", "Sphere");
    model.geom("part1").feature("sph11")
         .set("pos", new String[]{"1.298182E-4[m]", "8.718679999999999E-5[m]", "3.087021E-5[m]"});
    model.geom("part1").feature("sph11").label("Particle 4283");
    model.geom("part1").feature("sph11").set("r", "2.9238687967608155E-6[m]");
    model.geom("part1").create("sph12", "Sphere");
    model.geom("part1").feature("sph12")
         .set("pos", new String[]{"1.205127E-4[m]", "8.539970000000001E-5[m]", "3.0146490000000003E-5[m]"});
    model.geom("part1").feature("sph12").label("Particle 4327");
    model.geom("part1").feature("sph12").set("r", "4.0271917488453925E-6[m]");
    model.geom("part1").create("sph13", "Sphere");
    model.geom("part1").feature("sph13")
         .set("pos", new String[]{"1.0872820000000001E-4[m]", "8.10263E-5[m]", "3.141078E-5[m]"});
    model.geom("part1").feature("sph13").label("Particle 4495");
    model.geom("part1").feature("sph13").set("r", "6.0626470221353684E-6[m]");
    model.geom("part1").create("sph14", "Sphere");
    model.geom("part1").feature("sph14")
         .set("pos", new String[]{"1.351906E-4[m]", "8.937350000000001E-5[m]", "3.203534E-5[m]"});
    model.geom("part1").feature("sph14").label("Particle 4606");
    model.geom("part1").feature("sph14").set("r", "2.921981517957379E-6[m]");
    model.geom("part1").create("sph15", "Sphere");
    model.geom("part1").feature("sph15")
         .set("pos", new String[]{"1.3909410000000001E-4[m]", "9.988890000000001E-5[m]", "3.3004369999999997E-5[m]"});
    model.geom("part1").feature("sph15").label("Particle 4787");
    model.geom("part1").feature("sph15").set("r", "5.993762537463043E-6[m]");
    model.geom("part1").create("sph16", "Sphere");
    model.geom("part1").feature("sph16")
         .set("pos", new String[]{"1.329151E-4[m]", "9.31253E-5[m]", "3.4094020000000004E-5[m]"});
    model.geom("part1").feature("sph16").label("Particle 4958");
    model.geom("part1").feature("sph16").set("r", "1.6954291288053307E-6[m]");
    model.geom("part1").create("sph17", "Sphere");
    model.geom("part1").feature("sph17")
         .set("pos", new String[]{"1.151588E-4[m]", "7.0189E-5[m]", "3.577419E-5[m]"});
    model.geom("part1").feature("sph17").label("Particle 5213");
    model.geom("part1").feature("sph17").set("r", "6.109766755621732E-6[m]");
    model.geom("part1").create("sph18", "Sphere");
    model.geom("part1").feature("sph18")
         .set("pos", new String[]{"1.3433220000000001E-4[m]", "8.382350000000001E-5[m]", "3.586225E-5[m]"});
    model.geom("part1").feature("sph18").label("Particle 5219");
    model.geom("part1").feature("sph18").set("r", "1.7795351724043477E-6[m]");
    model.geom("part1").create("sph19", "Sphere");
    model.geom("part1").feature("sph19")
         .set("pos", new String[]{"1.248269E-4[m]", "9.29662E-5[m]", "3.600322E-5[m]"});
    model.geom("part1").feature("sph19").label("Particle 5265");
    model.geom("part1").feature("sph19").set("r", "5.7308341053244845E-6[m]");
    model.geom("part1").create("sph20", "Sphere");
    model.geom("part1").feature("sph20")
         .set("pos", new String[]{"1.247973E-4[m]", "7.777399999999999E-5[m]", "3.6337330000000004E-5[m]"});
    model.geom("part1").feature("sph20").label("Particle 5266");
    model.geom("part1").feature("sph20").set("r", "4.5078737666537404E-6[m]");
    model.geom("part1").create("sph21", "Sphere");
    model.geom("part1").feature("sph21")
         .set("pos", new String[]{"1.142597E-4[m]", "8.48817E-5[m]", "3.70518E-5[m]"});
    model.geom("part1").feature("sph21").label("Particle 5433");
    model.geom("part1").feature("sph21").set("r", "1.6507917251425737E-6[m]");
    model.geom("part1").create("sph22", "Sphere");
    model.geom("part1").feature("sph22").set("pos", new String[]{"1.330039E-4[m]", "9.4646E-5[m]", "3.81063E-5[m]"});
    model.geom("part1").feature("sph22").label("Particle 5489");
    model.geom("part1").feature("sph22").set("r", "2.737820878037563E-6[m]");
    model.geom("part1").create("sph23", "Sphere");
    model.geom("part1").feature("sph23")
         .set("pos", new String[]{"1.115143E-4[m]", "7.96536E-5[m]", "3.88278E-5[m]"});
    model.geom("part1").feature("sph23").label("Particle 5653");
    model.geom("part1").feature("sph23").set("r", "2.5436878988477595E-6[m]");
    model.geom("part1").create("sph24", "Sphere");
    model.geom("part1").feature("sph24")
         .set("pos", new String[]{"1.2205190000000001E-4[m]", "8.5951E-5[m]", "3.86243E-5[m]"});
    model.geom("part1").feature("sph24").label("Particle 5770");
    model.geom("part1").feature("sph24").set("r", "1.8493189372526178E-6[m]");
    model.geom("part1").create("sph25", "Sphere");
    model.geom("part1").feature("sph25").set("pos", new String[]{"1.37233E-4[m]", "9.42945E-5[m]", "4.05187E-5[m]"});
    model.geom("part1").feature("sph25").label("Particle 5775");
    model.geom("part1").feature("sph25").set("r", "3.7900601797076727E-6[m]");
    model.geom("part1").create("sph26", "Sphere");
    model.geom("part1").feature("sph26")
         .set("pos", new String[]{"1.185258E-4[m]", "9.44906E-5[m]", "4.16953E-5[m]"});
    model.geom("part1").feature("sph26").label("Particle 5959");
    model.geom("part1").feature("sph26").set("r", "3.1334064082304843E-6[m]");
    model.geom("part1").create("sph27", "Sphere");
    model.geom("part1").feature("sph27")
         .set("pos", new String[]{"1.122062E-4[m]", "9.741359999999998E-5[m]", "4.1921E-5[m]"});
    model.geom("part1").feature("sph27").label("Particle 6127");
    model.geom("part1").feature("sph27").set("r", "1.7628309850087896E-6[m]");
    model.geom("part1").create("sph28", "Sphere");
    model.geom("part1").feature("sph28")
         .set("pos", new String[]{"1.1044869999999999E-4[m]", "1.028378E-4[m]", "4.4777399999999996E-5[m]"});
    model.geom("part1").feature("sph28").label("Particle 6179");
    model.geom("part1").feature("sph28").set("r", "8.00530242274263E-6[m]");
    model.geom("part1").create("sph29", "Sphere");
    model.geom("part1").feature("sph29").set("pos", new String[]{"1.043511E-4[m]", "9.0539E-5[m]", "4.21097E-5[m]"});
    model.geom("part1").feature("sph29").label("Particle 6180");
    model.geom("part1").feature("sph29").set("r", "4.868619208161493E-6[m]");
    model.geom("part1").create("sph30", "Sphere");
    model.geom("part1").feature("sph30").set("pos", new String[]{"1.120138E-4[m]", "9.3277E-5[m]", "4.2069E-5[m]"});
    model.geom("part1").feature("sph30").label("Particle 6185");
    model.geom("part1").feature("sph30").set("r", "3.0019240827145328E-6[m]");
    model.geom("part1").create("sph31", "Sphere");
    model.geom("part1").feature("sph31")
         .set("pos", new String[]{"1.248084E-4[m]", "6.54308E-5[m]", "4.26018E-5[m]"});
    model.geom("part1").feature("sph31").label("Particle 6302");
    model.geom("part1").feature("sph31").set("r", "5.378607964541877E-6[m]");
    model.geom("part1").create("sph32", "Sphere");
    model.geom("part1").feature("sph32").set("pos", new String[]{"1.07485E-4[m]", "7.19317E-5[m]", "4.30088E-5[m]"});
    model.geom("part1").feature("sph32").label("Particle 6425");
    model.geom("part1").feature("sph32").set("r", "1.698229179197313E-6[m]");
    model.geom("part1").create("sph33", "Sphere");
    model.geom("part1").feature("sph33")
         .set("pos", new String[]{"1.09594E-4[m]", "8.43859E-5[m]", "4.3375100000000004E-5[m]"});
    model.geom("part1").feature("sph33").label("Particle 6426");
    model.geom("part1").feature("sph33").set("r", "2.129015499868244E-6[m]");
    model.geom("part1").create("sph34", "Sphere");
    model.geom("part1").feature("sph34")
         .set("pos", new String[]{"1.283826E-4[m]", "1.0248260000000001E-4[m]", "4.4259400000000005E-5[m]"});
    model.geom("part1").feature("sph34").label("Particle 6490");
    model.geom("part1").feature("sph34").set("r", "3.720895587120492E-6[m]");
    model.geom("part1").create("sph35", "Sphere");
    model.geom("part1").feature("sph35")
         .set("pos", new String[]{"1.307506E-4[m]", "9.11495E-5[m]", "4.36082E-5[m]"});
    model.geom("part1").feature("sph35").label("Particle 6492");
    model.geom("part1").feature("sph35").set("r", "3.956875407376958E-6[m]");
    model.geom("part1").create("sph36", "Sphere");
    model.geom("part1").feature("sph36")
         .set("pos", new String[]{"1.1492570000000001E-4[m]", "8.05823E-5[m]", "4.4762600000000004E-5[m]"});
    model.geom("part1").feature("sph36").label("Particle 6623");
    model.geom("part1").feature("sph36").set("r", "1.8656750859138983E-6[m]");
    model.geom("part1").create("sph37", "Sphere");
    model.geom("part1").feature("sph37")
         .set("pos", new String[]{"1.1344200000000002E-4[m]", "6.941199999999999E-5[m]", "4.47663E-5[m]"});
    model.geom("part1").feature("sph37").label("Particle 6673");
    model.geom("part1").feature("sph37").set("r", "2.8283663735121803E-6[m]");
    model.geom("part1").create("sph38", "Sphere");
    model.geom("part1").feature("sph38")
         .set("pos", new String[]{"1.252302E-4[m]", "9.655890000000001E-5[m]", "4.81111E-5[m]"});
    model.geom("part1").feature("sph38").label("Particle 6676");
    model.geom("part1").feature("sph38").set("r", "2.95927351165391E-6[m]");
    model.geom("part1").create("sph39", "Sphere");
    model.geom("part1").feature("sph39")
         .set("pos", new String[]{"1.341879E-4[m]", "7.725600000000001E-5[m]", "4.4733E-5[m]"});
    model.geom("part1").feature("sph39").label("Particle 6678");
    model.geom("part1").feature("sph39").set("r", "7.927687835740373E-6[m]");
    model.geom("part1").create("sph40", "Sphere");
    model.geom("part1").feature("sph40")
         .set("pos", new String[]{"1.386871E-4[m]", "1.0173890000000002E-4[m]", "4.58578E-5[m]"});
    model.geom("part1").feature("sph40").label("Particle 6800");
    model.geom("part1").feature("sph40").set("r", "3.795944064671376E-6[m]");
    model.geom("part1").create("sph41", "Sphere");
    model.geom("part1").feature("sph41")
         .set("pos", new String[]{"1.0719640000000001E-4[m]", "6.92566E-5[m]", "4.64757E-5[m]"});
    model.geom("part1").feature("sph41").label("Particle 6956");
    model.geom("part1").feature("sph41").set("r", "2.6280764752532554E-6[m]");
    model.geom("part1").create("sph42", "Sphere");
    model.geom("part1").feature("sph42").set("pos", new String[]{"1.215746E-4[m]", "8.6987E-5[m]", "4.773E-5[m]"});
    model.geom("part1").feature("sph42").label("Particle 7140");
    model.geom("part1").feature("sph42").set("r", "6.721040850183361E-6[m]");
    model.geom("part1").create("sph43", "Sphere");
    model.geom("part1").feature("sph43")
         .set("pos", new String[]{"1.1374170000000002E-4[m]", "8.412320000000001E-5[m]", "4.84478E-5[m]"});
    model.geom("part1").feature("sph43").label("Particle 7247");
    model.geom("part1").feature("sph43").set("r", "1.6237247022784333E-6[m]");
    model.geom("part1").create("sph44", "Sphere");
    model.geom("part1").feature("sph44")
         .set("pos", new String[]{"1.137713E-4[m]", "9.541190000000001E-5[m]", "5.06863E-5[m]"});
    model.geom("part1").feature("sph44").label("Particle 7248");
    model.geom("part1").feature("sph44").set("r", "2.1110785617786546E-6[m]");
    model.geom("part1").create("sph45", "Sphere");
    model.geom("part1").feature("sph45")
         .set("pos", new String[]{"1.1205450000000002E-4[m]", "7.92577E-5[m]", "4.9287700000000006E-5[m]"});
    model.geom("part1").feature("sph45").label("Particle 7303");
    model.geom("part1").feature("sph45").set("r", "7.783946317223771E-7[m]");
    model.geom("part1").create("sph46", "Sphere");
    model.geom("part1").feature("sph46")
         .set("pos", new String[]{"1.2420160000000001E-4[m]", "1.009101E-4[m]", "5.02423E-5[m]"});
    model.geom("part1").feature("sph46").label("Particle 7439");
    model.geom("part1").feature("sph46").set("r", "2.6546536564129194E-6[m]");
    model.geom("part1").create("sph47", "Sphere");
    model.geom("part1").feature("sph47")
         .set("pos", new String[]{"1.312353E-4[m]", "1.0212E-4[m]", "4.9994400000000004E-5[m]"});
    model.geom("part1").feature("sph47").label("Particle 7502");
    model.geom("part1").feature("sph47").set("r", "3.213456186961773E-6[m]");
    model.geom("part1").create("sph48", "Sphere");
    model.geom("part1").feature("sph48")
         .set("pos", new String[]{"1.3092450000000002E-4[m]", "9.5053E-5[m]", "5.14522E-5[m]"});
    model.geom("part1").feature("sph48").label("Particle 7739");
    model.geom("part1").feature("sph48").set("r", "3.1259988078354716E-6[m]");
    model.geom("part1").create("sph49", "Sphere");
    model.geom("part1").feature("sph49")
         .set("pos", new String[]{"1.050171E-4[m]", "8.95104E-5[m]", "5.3157899999999995E-5[m]"});
    model.geom("part1").feature("sph49").label("Particle 7794");
    model.geom("part1").feature("sph49").set("r", "3.890635853925118E-6[m]");
    model.geom("part1").create("sph50", "Sphere");
    model.geom("part1").feature("sph50")
         .set("pos", new String[]{"1.2125640000000001E-4[m]", "7.21759E-5[m]", "5.1977599999999995E-5[m]"});
    model.geom("part1").feature("sph50").label("Particle 7863");
    model.geom("part1").feature("sph50").set("r", "7.121947510829203E-6[m]");
    model.geom("part1").create("sph51", "Sphere");
    model.geom("part1").feature("sph51")
         .set("pos", new String[]{"1.383504E-4[m]", "8.599910000000001E-5[m]", "5.2972899999999996E-5[m]"});
    model.geom("part1").feature("sph51").label("Particle 7983");
    model.geom("part1").feature("sph51").set("r", "2.990699325311755E-6[m]");
    model.geom("part1").create("sph52", "Sphere");
    model.geom("part1").feature("sph52")
         .set("pos", new String[]{"1.089095E-4[m]", "6.97894E-5[m]", "5.28693E-5[m]"});
    model.geom("part1").feature("sph52").label("Particle 8040");
    model.geom("part1").feature("sph52").set("r", "5.21007785217621E-6[m]");
    model.geom("part1").create("sph53", "Sphere");
    model.geom("part1").feature("sph53")
         .set("pos", new String[]{"1.0606790000000001E-4[m]", "9.482730000000001E-5[m]", "5.4086600000000005E-5[m]"});
    model.geom("part1").feature("sph53").label("Particle 8174");
    model.geom("part1").feature("sph53").set("r", "2.413817193979606E-6[m]");
    model.geom("part1").create("sph54", "Sphere");
    model.geom("part1").feature("sph54")
         .set("pos", new String[]{"1.303399E-4[m]", "8.288740000000001E-5[m]", "5.44492E-5[m]"});
    model.geom("part1").feature("sph54").label("Particle 8243");
    model.geom("part1").feature("sph54").set("r", "3.6396506920387796E-6[m]");
    model.geom("part1").create("sph55", "Sphere");
    model.geom("part1").feature("sph55").set("pos", new String[]{"1.33311E-4[m]", "9.01653E-5[m]", "5.58959E-5[m]"});
    model.geom("part1").feature("sph55").label("Particle 8373");
    model.geom("part1").feature("sph55").set("r", "2.6902112419027094E-6[m]");
    model.geom("part1").create("sph56", "Sphere");
    model.geom("part1").feature("sph56")
         .set("pos", new String[]{"1.188625E-4[m]", "8.32204E-5[m]", "5.521880000000001E-5[m]"});
    model.geom("part1").feature("sph56").label("Particle 8423");
    model.geom("part1").feature("sph56").set("r", "1.8748941273504332E-6[m]");
    model.geom("part1").create("sph57", "Sphere");
    model.geom("part1").feature("sph57")
         .set("pos", new String[]{"1.26355E-4[m]", "8.8134E-5[m]", "5.5796000000000006E-5[m]"});
    model.geom("part1").feature("sph57").label("Particle 8424");
    model.geom("part1").feature("sph57").set("r", "1.7884013214082048E-6[m]");
    model.geom("part1").create("sph58", "Sphere");
    model.geom("part1").feature("sph58")
         .set("pos", new String[]{"1.32867E-4[m]", "6.92418E-5[m]", "5.6447200000000004E-5[m]"});
    model.geom("part1").feature("sph58").label("Particle 8470");
    model.geom("part1").feature("sph58").set("r", "5.699611892280002E-6[m]");
    model.geom("part1").create("sph59", "Sphere");
    model.geom("part1").feature("sph59")
         .set("pos", new String[]{"1.1399700000000001E-4[m]", "1.003366E-4[m]", "5.70947E-5[m]"});
    model.geom("part1").feature("sph59").label("Particle 8599");
    model.geom("part1").feature("sph59").set("r", "4.008714714690792E-6[m]");
    model.geom("part1").create("sph60", "Sphere");
    model.geom("part1").feature("sph60")
         .set("pos", new String[]{"1.35235E-4[m]", "9.901570000000001E-5[m]", "5.65656E-5[m]"});
    model.geom("part1").feature("sph60").label("Particle 8604");
    model.geom("part1").feature("sph60").set("r", "4.6825247393056295E-6[m]");
    model.geom("part1").create("sph61", "Sphere");
    model.geom("part1").feature("sph61")
         .set("pos", new String[]{"1.2281410000000001E-4[m]", "9.611489999999999E-5[m]", "5.7575700000000005E-5[m]"});

    return model;
  }

  public static Model run2(Model model) {
    model.geom("part1").feature("sph61").label("Particle 8662");
    model.geom("part1").feature("sph61").set("r", "2.312037047926752E-6[m]");
    model.geom("part1").create("sph62", "Sphere");
    model.geom("part1").feature("sph62")
         .set("pos", new String[]{"1.3686299999999998E-4[m]", "7.78702E-5[m]", "5.71798E-5[m]"});
    model.geom("part1").feature("sph62").label("Particle 8668");
    model.geom("part1").feature("sph62").set("r", "3.275113722204762E-6[m]");
    model.geom("part1").create("sph63", "Sphere");
    model.geom("part1").feature("sph63")
         .set("pos", new String[]{"1.1341609999999999E-4[m]", "8.64098E-5[m]", "5.7856900000000005E-5[m]"});
    model.geom("part1").feature("sph63").label("Particle 8732");
    model.geom("part1").feature("sph63").set("r", "3.712433016594984E-6[m]");
    model.geom("part1").create("sph64", "Sphere");
    model.geom("part1").feature("sph64")
         .set("pos", new String[]{"1.200872E-4[m]", "8.96584E-5[m]", "5.79161E-5[m]"});
    model.geom("part1").feature("sph64").label("Particle 8841");
    model.geom("part1").feature("sph64").set("r", "3.6481510246347676E-6[m]");
    model.geom("part1").create("sph65", "Sphere");
    model.geom("part1").feature("sph65")
         .set("pos", new String[]{"1.2612190000000002E-4[m]", "9.065740000000001E-5[m]", "5.8304600000000004E-5[m]"});
    model.geom("part1").feature("sph65").label("Particle 8895");
    model.geom("part1").feature("sph65").set("r", "8.729026457608417E-7[m]");
    model.geom("part1").create("sph66", "Sphere");
    model.geom("part1").feature("sph66").set("pos", new String[]{"1.129684E-4[m]", "7.61608E-5[m]", "5.9718E-5[m]"});
    model.geom("part1").feature("sph66").label("Particle 9070");
    model.geom("part1").feature("sph66").set("r", "4.150859271710123E-6[m]");
    model.geom("part1").create("sph67", "Sphere");
    model.geom("part1").feature("sph67")
         .set("pos", new String[]{"1.342619E-4[m]", "8.37569E-5[m]", "6.02915E-5[m]"});
    model.geom("part1").feature("sph67").label("Particle 9136");
    model.geom("part1").feature("sph67").set("r", "2.707367941945839E-6[m]");
    model.geom("part1").create("sph68", "Sphere");
    model.geom("part1").feature("sph68").set("pos", new String[]{"1.372996E-4[m]", "9.12901E-5[m]", "6.0458E-5[m]"});
    model.geom("part1").feature("sph68").label("Particle 9138");
    model.geom("part1").feature("sph68").set("r", "3.139961664307127E-6[m]");
    model.geom("part1").create("sph69", "Sphere");
    model.geom("part1").feature("sph69")
         .set("pos", new String[]{"1.0588660000000001E-4[m]", "6.9967E-5[m]", "6.08502E-5[m]"});
    model.geom("part1").feature("sph69").label("Particle 9189");
    model.geom("part1").feature("sph69").set("r", "3.7293198382191797E-6[m]");
    model.geom("part1").create("sph70", "Sphere");
    model.geom("part1").feature("sph70").set("pos", new String[]{"1.251525E-4[m]", "9.0465E-5[m]", "6.00621E-5[m]"});
    model.geom("part1").feature("sph70").label("Particle 9197");
    model.geom("part1").feature("sph70").set("r", "7.579009910300067E-7[m]");
    model.geom("part1").create("sph71", "Sphere");
    model.geom("part1").feature("sph71")
         .set("pos", new String[]{"1.268841E-4[m]", "7.27568E-5[m]", "6.07133E-5[m]"});
    model.geom("part1").feature("sph71").label("Particle 9198");
    model.geom("part1").feature("sph71").set("r", "2.3928810588851923E-6[m]");
    model.geom("part1").create("sph72", "Sphere");
    model.geom("part1").feature("sph72").set("pos", new String[]{"1.311946E-4[m]", "7.87767E-5[m]", "6.1383E-5[m]"});
    model.geom("part1").feature("sph72").label("Particle 9199");
    model.geom("part1").feature("sph72").set("r", "3.504125276566648E-6[m]");
    model.geom("part1").create("sph73", "Sphere");
    model.geom("part1").feature("sph73")
         .set("pos", new String[]{"1.162984E-4[m]", "9.18377E-5[m]", "6.18233E-5[m]"});
    model.geom("part1").feature("sph73").label("Particle 9310");
    model.geom("part1").feature("sph73").set("r", "1.208636551289078E-6[m]");
    model.geom("part1").create("sph74", "Sphere");
    model.geom("part1").feature("sph74").set("pos", new String[]{"1.253301E-4[m]", "8.3435E-5[m]", "6.19195E-5[m]"});
    model.geom("part1").feature("sph74").label("Particle 9311");
    model.geom("part1").feature("sph74").set("r", "4.855489300456815E-6[m]");
    model.geom("part1").create("sph75", "Sphere");
    model.geom("part1").feature("sph75")
         .set("pos", new String[]{"1.1783019999999999E-4[m]", "8.216589999999999E-5[m]", "6.13164E-5[m]"});
    model.geom("part1").feature("sph75").label("Particle 9384");
    model.geom("part1").feature("sph75").set("r", "1.412418328682426E-6[m]");
    model.geom("part1").create("sph76", "Sphere");
    model.geom("part1").feature("sph76")
         .set("pos", new String[]{"1.316793E-4[m]", "1.0013679999999999E-4[m]", "6.14126E-5[m]"});
    model.geom("part1").feature("sph76").label("Particle 9385");
    model.geom("part1").feature("sph76").set("r", "1.8806102976254705E-6[m]");
    model.geom("part1").create("sph77", "Sphere");
    model.geom("part1").feature("sph77").set("pos", new String[]{"1.098678E-4[m]", "8.6173E-5[m]", "6.35142E-5[m]"});
    model.geom("part1").feature("sph77").label("Particle 9595");
    model.geom("part1").feature("sph77").set("r", "2.071446165938878E-6[m]");
    model.geom("part1").create("sph78", "Sphere");
    model.geom("part1").feature("sph78").set("pos", new String[]{"1.097087E-4[m]", "9.4535E-5[m]", "6.41913E-5[m]"});
    model.geom("part1").feature("sph78").label("Particle 9660");
    model.geom("part1").feature("sph78").set("r", "4.361541620214566E-6[m]");
    model.geom("part1").create("sph79", "Sphere");
    model.geom("part1").feature("sph79")
         .set("pos", new String[]{"1.174232E-4[m]", "1.0081390000000001E-4[m]", "6.3936E-5[m]"});
    model.geom("part1").feature("sph79").label("Particle 9662");
    model.geom("part1").feature("sph79").set("r", "2.551761173086459E-6[m]");
    model.geom("part1").create("sph80", "Sphere");
    model.geom("part1").feature("sph80")
         .set("pos", new String[]{"1.1360109999999999E-4[m]", "7.102890000000001E-5[m]", "6.36733E-5[m]"});
    model.geom("part1").feature("sph80").label("Particle 9722");
    model.geom("part1").feature("sph80").set("r", "2.338134452035241E-6[m]");
    model.geom("part1").create("sph81", "Sphere");
    model.geom("part1").feature("sph81")
         .set("pos", new String[]{"1.3278929999999999E-4[m]", "8.214E-5[m]", "6.67036E-5[m]"});
    model.geom("part1").feature("sph81").label("Particle 10031");
    model.geom("part1").feature("sph81").set("r", "1.7230243188258753E-6[m]");
    model.geom("part1").create("sph82", "Sphere");
    model.geom("part1").feature("sph82")
         .set("pos", new String[]{"1.369481E-4[m]", "7.56613E-5[m]", "6.56047E-5[m]"});
    model.geom("part1").feature("sph82").label("Particle 10034");
    model.geom("part1").feature("sph82").set("r", "4.177129734484371E-6[m]");
    model.geom("part1").create("sph83", "Sphere");
    model.geom("part1").feature("sph83")
         .set("pos", new String[]{"1.1700140000000001E-4[m]", "8.47374E-5[m]", "6.619300000000001E-5[m]"});
    model.geom("part1").feature("sph83").label("Particle 10083");
    model.geom("part1").feature("sph83").set("r", "4.529392781932931E-6[m]");
    model.geom("part1").create("sph84", "Sphere");
    model.geom("part1").feature("sph84").set("pos", new String[]{"1.283308E-4[m]", "7.252E-5[m]", "6.76693E-5[m]"});
    model.geom("part1").feature("sph84").label("Particle 10193");
    model.geom("part1").feature("sph84").set("r", "5.666035559790616E-6[m]");
    model.geom("part1").create("sph85", "Sphere");
    model.geom("part1").feature("sph85")
         .set("pos", new String[]{"1.0748869999999999E-4[m]", "8.61286E-5[m]", "6.652229999999999E-5[m]"});
    model.geom("part1").feature("sph85").label("Particle 10248");
    model.geom("part1").feature("sph85").set("r", "1.2690351527154784E-6[m]");
    model.geom("part1").create("sph86", "Sphere");
    model.geom("part1").feature("sph86")
         .set("pos", new String[]{"1.3026959999999998E-4[m]", "9.96558E-5[m]", "6.77692E-5[m]"});
    model.geom("part1").feature("sph86").label("Particle 10252");
    model.geom("part1").feature("sph86").set("r", "5.449837794802714E-6[m]");
    model.geom("part1").create("sph87", "Sphere");
    model.geom("part1").feature("sph87")
         .set("pos", new String[]{"1.316682E-4[m]", "9.07203E-5[m]", "6.75065E-5[m]"});
    model.geom("part1").feature("sph87").label("Particle 10254");
    model.geom("part1").feature("sph87").set("r", "7.978624921305946E-7[m]");
    model.geom("part1").create("sph88", "Sphere");
    model.geom("part1").feature("sph88")
         .set("pos", new String[]{"1.313019E-4[m]", "8.89924E-5[m]", "6.69959E-5[m]"});
    model.geom("part1").feature("sph88").label("Particle 10255");
    model.geom("part1").feature("sph88").set("r", "1.0618338347536353E-6[m]");
    model.geom("part1").create("sph89", "Sphere");
    model.geom("part1").feature("sph89")
         .set("pos", new String[]{"1.0907600000000001E-4[m]", "7.90875E-5[m]", "6.7821E-5[m]"});
    model.geom("part1").feature("sph89").label("Particle 10381");
    model.geom("part1").feature("sph89").set("r", "4.725877126061932E-6[m]");
    model.geom("part1").create("sph90", "Sphere");
    model.geom("part1").feature("sph90")
         .set("pos", new String[]{"1.13035E-4[m]", "1.008583E-4[m]", "6.87682E-5[m]"});
    model.geom("part1").feature("sph90").label("Particle 10487");
    model.geom("part1").feature("sph90").set("r", "2.5640827480938494E-6[m]");
    model.geom("part1").create("sph91", "Sphere");
    model.geom("part1").feature("sph91")
         .set("pos", new String[]{"1.39564E-4[m]", "8.701660000000001E-5[m]", "6.79431E-5[m]"});
    model.geom("part1").feature("sph91").label("Particle 10492");
    model.geom("part1").feature("sph91").set("r", "5.857383661738509E-6[m]");
    model.geom("part1").create("sph92", "Sphere");
    model.geom("part1").feature("sph92").set("pos", new String[]{"1.400746E-4[m]", "6.808E-5[m]", "6.93935E-5[m]"});
    model.geom("part1").feature("sph92").label("Particle 10596");
    model.geom("part1").feature("sph92").set("r", "5.063901891487742E-6[m]");
    model.geom("part1").create("sph93", "Sphere");
    model.geom("part1").feature("sph93")
         .set("pos", new String[]{"1.1718640000000001E-4[m]", "9.51381E-5[m]", "6.99633E-5[m]"});
    model.geom("part1").feature("sph93").label("Particle 10703");
    model.geom("part1").feature("sph93").set("r", "1.9923721896549547E-6[m]");
    model.geom("part1").create("sph94", "Sphere");
    model.geom("part1").feature("sph94")
         .set("pos", new String[]{"1.1017859999999999E-4[m]", "8.990630000000001E-5[m]", "7.058490000000001E-5[m]"});
    model.geom("part1").feature("sph94").label("Particle 10821");
    model.geom("part1").feature("sph94").set("r", "3.4545115547359004E-6[m]");
    model.geom("part1").create("sph95", "Sphere");
    model.geom("part1").feature("sph95")
         .set("pos", new String[]{"1.128426E-4[m]", "9.482730000000001E-5[m]", "7.10178E-5[m]"});
    model.geom("part1").feature("sph95").label("Particle 10870");
    model.geom("part1").feature("sph95").set("r", "8.341778980893946E-7[m]");
    model.geom("part1").create("sph96", "Sphere");
    model.geom("part1").feature("sph96")
         .set("pos", new String[]{"1.1843329999999999E-4[m]", "8.40973E-5[m]", "7.29788E-5[m]"});
    model.geom("part1").feature("sph96").label("Particle 11115");
    model.geom("part1").feature("sph96").set("r", "2.014466805523636E-6[m]");
    model.geom("part1").create("sph97", "Sphere");
    model.geom("part1").feature("sph97")
         .set("pos", new String[]{"1.191955E-4[m]", "7.99718E-5[m]", "7.21981E-5[m]"});
    model.geom("part1").feature("sph97").label("Particle 11117");
    model.geom("part1").feature("sph97").set("r", "2.213648476820447E-6[m]");
    model.geom("part1").create("sph98", "Sphere");
    model.geom("part1").feature("sph98")
         .set("pos", new String[]{"1.257297E-4[m]", "9.75616E-5[m]", "7.34894E-5[m]"});
    model.geom("part1").feature("sph98").label("Particle 11204");
    model.geom("part1").feature("sph98").set("r", "2.619292740440205E-6[m]");
    model.geom("part1").create("sph99", "Sphere");
    model.geom("part1").feature("sph99")
         .set("pos", new String[]{"1.039626E-4[m]", "1.027897E-4[m]", "7.3963E-5[m]"});
    model.geom("part1").feature("sph99").label("Particle 11325");
    model.geom("part1").feature("sph99").set("r", "5.090640888200033E-6[m]");
    model.geom("part1").create("sph100", "Sphere");
    model.geom("part1").feature("sph100")
         .set("pos", new String[]{"1.286601E-4[m]", "8.54034E-5[m]", "7.42516E-5[m]"});
    model.geom("part1").feature("sph100").label("Particle 11332");
    model.geom("part1").feature("sph100").set("r", "7.0579558005782565E-6[m]");
    model.geom("part1").create("sph101", "Sphere");
    model.geom("part1").feature("sph101").set("pos", new String[]{"1.3616E-4[m]", "7.06441E-5[m]", "7.43108E-5[m]"});
    model.geom("part1").feature("sph101").label("Particle 11395");
    model.geom("part1").feature("sph101").set("r", "1.0689366062457294E-6[m]");
    model.geom("part1").create("sph102", "Sphere");
    model.geom("part1").feature("sph102")
         .set("pos", new String[]{"1.371035E-4[m]", "7.5591E-5[m]", "7.426270000000001E-5[m]"});
    model.geom("part1").feature("sph102").label("Particle 11396");
    model.geom("part1").feature("sph102").set("r", "3.232856880117078E-6[m]");
    model.geom("part1").create("sph103", "Sphere");
    model.geom("part1").feature("sph103")
         .set("pos", new String[]{"1.13405E-4[m]", "7.695259999999999E-5[m]", "7.41961E-5[m]"});
    model.geom("part1").feature("sph103").label("Particle 11464");
    model.geom("part1").feature("sph103").set("r", "2.690768105622074E-6[m]");
    model.geom("part1").create("sph104", "Sphere");
    model.geom("part1").feature("sph104")
         .set("pos", new String[]{"1.129758E-4[m]", "7.08772E-5[m]", "7.526910000000001E-5[m]"});
    model.geom("part1").feature("sph104").label("Particle 11520");
    model.geom("part1").feature("sph104").set("r", "1.5748728564874107E-6[m]");
    model.geom("part1").create("sph105", "Sphere");
    model.geom("part1").feature("sph105")
         .set("pos", new String[]{"1.187367E-4[m]", "8.95955E-5[m]", "7.57686E-5[m]"});
    model.geom("part1").feature("sph105").label("Particle 11589");
    model.geom("part1").feature("sph105").set("r", "3.3618607540090155E-6[m]");
    model.geom("part1").create("sph106", "Sphere");
    model.geom("part1").feature("sph106")
         .set("pos", new String[]{"1.130313E-4[m]", "9.832010000000001E-5[m]", "7.58056E-5[m]"});
    model.geom("part1").feature("sph106").label("Particle 11638");
    model.geom("part1").feature("sph106").set("r", "4.494543860924461E-6[m]");
    model.geom("part1").create("sph107", "Sphere");
    model.geom("part1").feature("sph107")
         .set("pos", new String[]{"1.22729E-4[m]", "7.12435E-5[m]", "7.63643E-5[m]"});
    model.geom("part1").feature("sph107").label("Particle 11728");
    model.geom("part1").feature("sph107").set("r", "6.086461312390929E-6[m]");
    model.geom("part1").create("sph108", "Sphere");
    model.geom("part1").feature("sph108")
         .set("pos", new String[]{"1.246678E-4[m]", "7.844E-5[m]", "7.770740000000001E-5[m]"});
    model.geom("part1").feature("sph108").label("Particle 11903");
    model.geom("part1").feature("sph108").set("r", "1.0399341023011831E-6[m]");
    model.geom("part1").create("sph109", "Sphere");
    model.geom("part1").feature("sph109")
         .set("pos", new String[]{"1.323194E-4[m]", "9.36618E-5[m]", "7.853250000000001E-5[m]"});
    model.geom("part1").feature("sph109").label("Particle 11906");
    model.geom("part1").feature("sph109").set("r", "3.1965822066292037E-6[m]");
    model.geom("part1").create("sph110", "Sphere");
    model.geom("part1").feature("sph110")
         .set("pos", new String[]{"1.0560170000000002E-4[m]", "8.370880000000001E-5[m]", "7.99792E-5[m]"});
    model.geom("part1").feature("sph110").label("Particle 12198");
    model.geom("part1").feature("sph110").set("r", "5.320007028971661E-6[m]");
    model.geom("part1").create("sph111", "Sphere");
    model.geom("part1").feature("sph111").set("pos", new String[]{"1.328263E-4[m]", "7.1262E-5[m]", "8.1104E-5[m]"});
    model.geom("part1").feature("sph111").label("Particle 12403");
    model.geom("part1").feature("sph111").set("r", "3.1739360318257523E-6[m]");
    model.geom("part1").create("sph112", "Sphere");
    model.geom("part1").feature("sph112")
         .set("pos", new String[]{"1.3173109999999998E-4[m]", "7.98941E-5[m]", "8.16442E-5[m]"});
    model.geom("part1").feature("sph112").label("Particle 12404");
    model.geom("part1").feature("sph112").set("r", "1.0209529680245113E-6[m]");
    model.geom("part1").create("sph113", "Sphere");
    model.geom("part1").feature("sph113")
         .set("pos", new String[]{"1.378694E-4[m]", "9.89084E-5[m]", "8.22325E-5[m]"});
    model.geom("part1").feature("sph113").label("Particle 12499");
    model.geom("part1").feature("sph113").set("r", "5.111708178330002E-6[m]");
    model.geom("part1").create("sph114", "Sphere");
    model.geom("part1").feature("sph114")
         .set("pos", new String[]{"1.396232E-4[m]", "8.14555E-5[m]", "8.21585E-5[m]"});
    model.geom("part1").feature("sph114").label("Particle 12501");
    model.geom("part1").feature("sph114").set("r", "5.806540123848875E-6[m]");
    model.geom("part1").create("sph115", "Sphere");
    model.geom("part1").feature("sph115")
         .set("pos", new String[]{"1.274724E-4[m]", "8.79342E-5[m]", "8.4323E-5[m]"});
    model.geom("part1").feature("sph115").label("Particle 12579");
    model.geom("part1").feature("sph115").set("r", "2.4797971435873444E-6[m]");
    model.geom("part1").create("sph116", "Sphere");
    model.geom("part1").feature("sph116")
         .set("pos", new String[]{"1.190401E-4[m]", "8.63691E-5[m]", "8.49446E-5[m]"});
    model.geom("part1").feature("sph116").label("Particle 12620");
    model.geom("part1").feature("sph116").set("r", "9.559042839748824E-6[m]");
    model.geom("part1").create("sph117", "Sphere");
    model.geom("part1").feature("sph117")
         .set("pos", new String[]{"1.319938E-4[m]", "8.57697E-5[m]", "8.43785E-5[m]"});
    model.geom("part1").feature("sph117").label("Particle 12714");
    model.geom("part1").feature("sph117").set("r", "1.4649938598533366E-6[m]");
    model.geom("part1").create("sph118", "Sphere");
    model.geom("part1").feature("sph118")
         .set("pos", new String[]{"1.345135E-4[m]", "8.74717E-5[m]", "8.57142E-5[m]"});
    model.geom("part1").feature("sph118").label("Particle 12810");
    model.geom("part1").feature("sph118").set("r", "1.4574422185298285E-6[m]");
    model.geom("part1").create("cyl1", "Cylinder");
    model.geom("part1").feature("cyl1")
         .set("pos", new String[]{"1.2146361940082375E-4[m]", "7.654425191581474E-5[m]", "2.1115024518787256E-5[m]"});
    model.geom("part1").feature("cyl1").set("axistype", "cartesian");
    model.geom("part1").feature("cyl1")
         .set("ax3", new String[]{"1.0504300000000014E-5[m]", "-1.4381899999999992E-5[m]", "2.50194E-6[m]"});
    model.geom("part1").feature("cyl1").set("r", "1.3987569416333624E-6[m]");
    model.geom("part1").feature("cyl1").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl2", "Cylinder");
    model.geom("part1").feature("cyl2")
         .set("pos", new String[]{"1.1467878377719897E-4[m]", "7.288340285137796E-5[m]", "2.479128815006482E-5[m]"});
    model.geom("part1").feature("cyl2").set("axistype", "cartesian");
    model.geom("part1").feature("cyl2")
         .set("ax3", new String[]{"-6.167900000000006E-6[m]", "-1.9721000000000078E-6[m]", "-3.677430000000005E-6[m]"});
    model.geom("part1").feature("cyl2").set("r", "9.51581196470357E-7[m]");
    model.geom("part1").feature("cyl2").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl3", "Cylinder");
    model.geom("part1").feature("cyl3")
         .set("pos", new String[]{"1.1788196601457288E-4[m]", "7.25982017176684E-5[m]", "2.5623907420852975E-5[m]"});
    model.geom("part1").feature("cyl3").set("axistype", "cartesian");
    model.geom("part1").feature("cyl3")
         .set("ax3", new String[]{"1.3612299999999998E-5[m]", "-8.809700000000012E-6[m]", "-2.712100000000004E-6[m]"});
    model.geom("part1").feature("cyl3").set("r", "1.853502016666169E-6[m]");
    model.geom("part1").feature("cyl3").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl4", "Cylinder");
    model.geom("part1").feature("cyl4")
         .set("pos", new String[]{"1.1925587520911256E-4[m]", "6.867827308293281E-5[m]", "3.233038022803317E-5[m]"});
    model.geom("part1").feature("cyl4").set("axistype", "cartesian");
    model.geom("part1").feature("cyl4")
         .set("ax3", new String[]{"1.4981300000000005E-5[m]", "-5.5241000000000036E-6[m]", "-1.259258E-5[m]"});
    model.geom("part1").feature("cyl4").set("r", "2.0758598243019633E-6[m]");
    model.geom("part1").feature("cyl4").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl5", "Cylinder");
    model.geom("part1").feature("cyl5")
         .set("pos", new String[]{"1.2816526018520806E-4[m]", "9.619616763456001E-5[m]", "2.925856409411259E-5[m]"});
    model.geom("part1").feature("cyl5").set("axistype", "cartesian");
    model.geom("part1").feature("cyl5")
         .set("ax3", new String[]{"-7.507299999999975E-6[m]", "5.38349999999998E-6[m]", "-1.6172699999999968E-6[m]"});
    model.geom("part1").feature("cyl5").set("r", "9.901889628382088E-7[m]");
    model.geom("part1").feature("cyl5").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl6", "Cylinder");
    model.geom("part1").feature("cyl6")
         .set("pos", new String[]{"1.2367571898779393E-4[m]", "9.592557752893955E-5[m]", "3.292324924063546E-5[m]"});
    model.geom("part1").feature("cyl6").set("axistype", "cartesian");
    model.geom("part1").feature("cyl6")
         .set("ax3", new String[]{"-3.0339999999999984E-6[m]", "7.799599999999985E-6[m]", "-8.11743E-6[m]"});
    model.geom("part1").feature("cyl6").set("r", "3.4061859771329625E-6[m]");
    model.geom("part1").feature("cyl6").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl7", "Cylinder");
    model.geom("part1").feature("cyl7")
         .set("pos", new String[]{"1.1806813516130174E-4[m]", "8.328408121332934E-5[m]", "2.7893434849126023E-5[m]"});
    model.geom("part1").feature("cyl7").set("axistype", "cartesian");
    model.geom("part1").feature("cyl7")
         .set("ax3", new String[]{"4.014499999999987E-6[m]", "3.4743000000000084E-6[m]", "3.700000000000002E-6[m]"});
    model.geom("part1").feature("cyl7").set("r", "1.6734034858544047E-6[m]");
    model.geom("part1").feature("cyl7").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl8", "Cylinder");
    model.geom("part1").feature("cyl8")
         .set("pos", new String[]{"1.1402248622895682E-4[m]", "8.163892454935071E-5[m]", "2.802823531743268E-5[m]"});
    model.geom("part1").feature("cyl8").set("axistype", "cartesian");
    model.geom("part1").feature("cyl8")
         .set("ax3", new String[]{"-7.770000000000002E-6[m]", "-8.990999999999996E-7[m]", "4.9642899999999985E-6[m]"});
    model.geom("part1").feature("cyl8").set("r", "5.793791467132914E-7[m]");
    model.geom("part1").feature("cyl8").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl9", "Cylinder");
    model.geom("part1").feature("cyl9")
         .set("pos", new String[]{"1.3271525298728321E-4[m]", "8.836597239358428E-5[m]", "3.1498503378578155E-5[m]"});
    model.geom("part1").feature("cyl9").set("axistype", "cartesian");
    model.geom("part1").feature("cyl9")
         .set("ax3", new String[]{"-5.372399999999989E-6[m]", "-2.1867000000000175E-6[m]", "-1.1651299999999954E-6[m]"});
    model.geom("part1").feature("cyl9").set("r", "2.5399163470920524E-7[m]");
    model.geom("part1").feature("cyl9").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl10", "Cylinder");
    model.geom("part1").feature("cyl10")
         .set("pos", new String[]{"1.1086145357620784E-4[m]", "7.997525474532125E-5[m]", "3.708982398255806E-5[m]"});
    model.geom("part1").feature("cyl10").set("axistype", "cartesian");
    model.geom("part1").feature("cyl10")
         .set("ax3", new String[]{"-2.78609999999999E-6[m]", "1.3726999999999945E-6[m]", "-7.417020000000002E-6[m]"});
    model.geom("part1").feature("cyl10").set("r", "1.4794377909461621E-6[m]");
    model.geom("part1").feature("cyl10").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl11", "Cylinder");
    model.geom("part1").feature("cyl11")
         .set("pos", new String[]{"1.378964105280833E-4[m]", "9.628868830708135E-5[m]", "3.784013053382837E-5[m]"});
    model.geom("part1").feature("cyl11").set("axistype", "cartesian");
    model.geom("part1").feature("cyl11")
         .set("ax3", new String[]{"1.8611000000000083E-6[m]", "5.594400000000015E-6[m]", "-7.514330000000006E-6[m]"});
    model.geom("part1").feature("cyl11").set("r", "1.2161316179319227E-6[m]");
    model.geom("part1").feature("cyl11").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl12", "Cylinder");
    model.geom("part1").feature("cyl12")
         .set("pos", new String[]{"1.3294333008127766E-4[m]", "9.360874014188001E-5[m]", "3.5369549172395845E-5[m]"});
    model.geom("part1").feature("cyl12").set("axistype", "cartesian");
    model.geom("part1").feature("cyl12")
         .set("ax3", new String[]{"8.880000000000498E-8[m]", "1.5207000000000073E-6[m]", "4.012279999999994E-6[m]"});
    model.geom("part1").feature("cyl12").set("r", "6.884436792935233E-7[m]");
    model.geom("part1").feature("cyl12").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl13", "Cylinder");
    model.geom("part1").feature("cyl13")
         .set("pos", new String[]{"1.2052225926056451E-4[m]", "9.400760457113766E-5[m]", "3.9891804447180014E-5[m]"});
    model.geom("part1").feature("cyl13").set("axistype", "cartesian");
    model.geom("part1").feature("cyl13")
         .set("ax3", new String[]{"6.301100000000013E-6[m]", "-1.524399999999995E-6[m]", "-5.692079999999998E-6[m]"});
    model.geom("part1").feature("cyl13").set("r", "1.1405722690030824E-6[m]");
    model.geom("part1").feature("cyl13").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl14", "Cylinder");
    model.geom("part1").feature("cyl14")
         .set("pos", new String[]{"1.342967823317055E-4[m]", "9.453854258835343E-5[m]", "3.8843797183090096E-5[m]"});
    model.geom("part1").feature("cyl14").set("axistype", "cartesian");
    model.geom("part1").feature("cyl14")
         .set("ax3", new String[]{"4.2290999999999965E-6[m]", "-3.51500000000005E-7[m]", "2.412400000000004E-6[m]"});
    model.geom("part1").feature("cyl14").set("r", "2.163859735261385E-6[m]");
    model.geom("part1").feature("cyl14").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl15", "Cylinder");
    model.geom("part1").feature("cyl15")
         .set("pos", new String[]{"1.1215457063349498E-4[m]", "9.630356862014179E-5[m]", "4.196071489731157E-5[m]"});
    model.geom("part1").feature("cyl15").set("axistype", "cartesian");
    model.geom("part1").feature("cyl15")
         .set("ax3", new String[]{"-1.9240000000000175E-7[m]", "-4.13659999999999E-6[m]", "1.4799999999999926E-7[m]"});
    model.geom("part1").feature("cyl15").set("r", "1.193950344828973E-6[m]");
    model.geom("part1").feature("cyl15").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl16", "Cylinder");
    model.geom("part1").feature("cyl16")
         .set("pos", new String[]{"1.1169707302324888E-4[m]", "9.521180498327403E-5[m]", "4.2617094910122515E-5[m]"});
    model.geom("part1").feature("cyl16").set("axistype", "cartesian");
    model.geom("part1").feature("cyl16")
         .set("ax3", new String[]{"-1.5651000000000098E-6[m]", "9.560800000000012E-6[m]", "2.708399999999996E-6[m]"});
    model.geom("part1").feature("cyl16").set("r", "2.019878455726704E-6[m]");
    model.geom("part1").feature("cyl16").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl17", "Cylinder");
    model.geom("part1").feature("cyl17")
         .set("pos", new String[]{"1.1317535171363967E-4[m]", "9.674382451082981E-5[m]", "4.9626467691183255E-5[m]"});
    model.geom("part1").feature("cyl17").set("axistype", "cartesian");
    model.geom("part1").feature("cyl17")
         .set("ax3", new String[]{"-3.322600000000008E-6[m]", "7.4258999999999995E-6[m]", "-5.908900000000007E-6[m]"});
    model.geom("part1").feature("cyl17").set("r", "7.090348697008097E-7[m]");
    model.geom("part1").feature("cyl17").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl18", "Cylinder");
    model.geom("part1").feature("cyl18")
         .set("pos", new String[]{"1.2336748081103848E-4[m]", "6.81670455015384E-5[m]", "4.640522627586302E-5[m]"});
    model.geom("part1").feature("cyl18").set("axistype", "cartesian");
    model.geom("part1").feature("cyl18")
         .set("ax3", new String[]{"-3.5519999999999823E-6[m]", "6.7450999999999975E-6[m]", "9.375799999999992E-6[m]"});
    model.geom("part1").feature("cyl18").set("r", "1.747034529692137E-6[m]");
    model.geom("part1").feature("cyl18").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl19", "Cylinder");
    model.geom("part1").feature("cyl19")
         .set("pos", new String[]{"1.0738669818072549E-4[m]", "7.102051775210925E-5[m]", "4.418968211102854E-5[m]"});
    model.geom("part1").feature("cyl19").set("axistype", "cartesian");
    model.geom("part1").feature("cyl19")
         .set("ax3", new String[]{"-2.8859999999999585E-7[m]", "-2.6750999999999907E-6[m]", "3.466899999999999E-6[m]"});
    model.geom("part1").feature("cyl19").set("r", "2.4957149572772296E-7[m]");
    model.geom("part1").feature("cyl19").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl20", "Cylinder");
    model.geom("part1").feature("cyl20")
         .set("pos", new String[]{"1.3003549935698927E-4[m]", "1.0227250384308048E-4[m]", "4.758234942066587E-5[m]"});
    model.geom("part1").feature("cyl20").set("axistype", "cartesian");
    model.geom("part1").feature("cyl20")
         .set("ax3", new String[]{"-2.852699999999987E-6[m]", "3.6260000000000903E-7[m]", "-5.7349999999999985E-6[m]"});
    model.geom("part1").feature("cyl20").set("r", "1.418781693880261E-6[m]");
    model.geom("part1").feature("cyl20").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl21", "Cylinder");
    model.geom("part1").feature("cyl21")
         .set("pos", new String[]{"1.2463134413878278E-4[m]", "9.909218954241534E-5[m]", "4.935189487791771E-5[m]"});
    model.geom("part1").feature("cyl21").set("axistype", "cartesian");
    model.geom("part1").feature("cyl21")
         .set("ax3", new String[]{"1.0285999999999922E-6[m]", "-4.351199999999986E-6[m]", "-2.1311999999999975E-6[m]"});
    model.geom("part1").feature("cyl21").set("r", "1.4017823380576564E-6[m]");
    model.geom("part1").feature("cyl21").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl22", "Cylinder");
    model.geom("part1").feature("cyl22")
         .set("pos", new String[]{"1.0759462727600843E-4[m]", "6.938045470355338E-5[m]", "4.79619564426405E-5[m]"});
    model.geom("part1").feature("cyl22").set("axistype", "cartesian");
    model.geom("part1").feature("cyl22")
         .set("ax3", new String[]{"1.7130999999999955E-6[m]", "5.328000000000028E-7[m]", "6.393599999999999E-6[m]"});
    model.geom("part1").feature("cyl22").set("r", "1.9795261955410996E-6[m]");
    model.geom("part1").feature("cyl22").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl23", "Cylinder");
    model.geom("part1").feature("cyl23")
         .set("pos", new String[]{"1.1503775056736683E-4[m]", "8.459705127026072E-5[m]", "4.8329031076963076E-5[m]"});
    model.geom("part1").feature("cyl23").set("axistype", "cartesian");
    model.geom("part1").feature("cyl23")
         .set("ax3", new String[]{"7.832899999999984E-6[m]", "2.863799999999991E-6[m]", "-7.178000000000018E-7[m]"});
    model.geom("part1").feature("cyl23").set("r", "4.140011584472425E-7[m]");
    model.geom("part1").feature("cyl23").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl24", "Cylinder");
    model.geom("part1").feature("cyl24")
         .set("pos", new String[]{"1.3251282267212546E-4[m]", "9.6513166164675E-5[m]", "5.3336373332941966E-5[m]"});

    return model;
  }

  public static Model run3(Model model) {
    model.geom("part1").feature("cyl24").set("axistype", "cartesian");
    model.geom("part1").feature("cyl24")
         .set("ax3", new String[]{"4.310499999999972E-6[m]", "3.962700000000009E-6[m]", "5.1133999999999975E-6[m]"});
    model.geom("part1").feature("cyl24").set("r", "6.876342751528888E-7[m]");
    model.geom("part1").feature("cyl24").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl25", "Cylinder");
    model.geom("part1").feature("cyl25")
         .set("pos", new String[]{"1.0575124493876294E-4[m]", "9.322506998944485E-5[m]", "5.380673936489259E-5[m]"});
    model.geom("part1").feature("cyl25").set("axistype", "cartesian");
    model.geom("part1").feature("cyl25")
         .set("ax3", new String[]{"-1.0508000000000137E-6[m]", "-5.31690000000001E-6[m]", "-9.287000000000103E-7[m]"});
    model.geom("part1").feature("cyl25").set("r", "1.5599537211993304E-6[m]");
    model.geom("part1").feature("cyl25").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl26", "Cylinder");
    model.geom("part1").feature("cyl26")
         .set("pos", new String[]{"1.279244138072893E-4[m]", "7.049083468796035E-5[m]", "5.454450907559129E-5[m]"});
    model.geom("part1").feature("cyl26").set("axistype", "cartesian");
    model.geom("part1").feature("cyl26")
         .set("ax3", new String[]{"-1.1610599999999993E-5[m]", "2.934100000000003E-6[m]", "-4.469600000000009E-6[m]"});
    model.geom("part1").feature("cyl26").set("r", "9.102374005169353E-7[m]");
    model.geom("part1").feature("cyl26").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl27", "Cylinder");
    model.geom("part1").feature("cyl27")
         .set("pos", new String[]{"1.0703856683548178E-4[m]", "6.989932018592029E-5[m]", "5.7808838354792935E-5[m]"});
    model.geom("part1").feature("cyl27").set("axistype", "cartesian");
    model.geom("part1").feature("cyl27")
         .set("ax3", new String[]{"3.0228999999999944E-6[m]", "-1.775999999999964E-7[m]", "-7.980900000000003E-6[m]"});
    model.geom("part1").feature("cyl27").set("r", "1.4451755034301328E-6[m]");
    model.geom("part1").feature("cyl27").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl28", "Cylinder");
    model.geom("part1").feature("cyl28")
         .set("pos", new String[]{"1.2848535234809624E-4[m]", "7.181605186722855E-5[m]", "5.9571528845173175E-5[m]"});
    model.geom("part1").feature("cyl28").set("axistype", "cartesian");
    model.geom("part1").feature("cyl28")
         .set("ax3", new String[]{"5.982900000000007E-6[m]", "-3.514999999999996E-6[m]", "-4.266099999999996E-6[m]"});
    model.geom("part1").feature("cyl28").set("r", "3.638343145951904E-7[m]");
    model.geom("part1").feature("cyl28").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl29", "Cylinder");
    model.geom("part1").feature("cyl29")
         .set("pos", new String[]{"1.3243494134454947E-4[m]", "9.989854887887774E-5[m]", "6.03825373971282E-5[m]"});
    model.geom("part1").feature("cyl29").set("axistype", "cartesian");
    model.geom("part1").feature("cyl29")
         .set("ax3", new String[]{"3.555699999999997E-6[m]", "-1.121099999999985E-6[m]", "-4.847000000000003E-6[m]"});
    model.geom("part1").feature("cyl29").set("r", "1.1544940114323319E-6[m]");
    model.geom("part1").feature("cyl29").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl30", "Cylinder");
    model.geom("part1").feature("cyl30")
         .set("pos", new String[]{"1.16988577563398E-4[m]", "8.81494757075227E-5[m]", "5.7888602518588115E-5[m]"});
    model.geom("part1").feature("cyl30").set("axistype", "cartesian");
    model.geom("part1").feature("cyl30")
         .set("ax3", new String[]{"-6.671100000000011E-6[m]", "-3.248600000000008E-6[m]", "-5.919999999999428E-8[m]"});
    model.geom("part1").feature("cyl30").set("r", "3.4708542427120104E-7[m]");
    model.geom("part1").feature("cyl30").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl31", "Cylinder");
    model.geom("part1").feature("cyl31")
         .set("pos", new String[]{"1.133962922933944E-4[m]", "7.269011806468982E-5[m]", "6.239295240724341E-5[m]"});
    model.geom("part1").feature("cyl31").set("axistype", "cartesian");
    model.geom("part1").feature("cyl31")
         .set("ax3", new String[]{"-6.326999999999982E-7[m]", "5.131899999999997E-6[m]", "-3.955300000000006E-6[m]"});
    model.geom("part1").feature("cyl31").set("r", "4.6048922397444015E-7[m]");
    model.geom("part1").feature("cyl31").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl32", "Cylinder");
    model.geom("part1").feature("cyl32")
         .set("pos", new String[]{"1.330652460396695E-4[m]", "8.181396124173113E-5[m]", "6.0717329817005434E-5[m]"});
    model.geom("part1").feature("cyl32").set("axistype", "cartesian");
    model.geom("part1").feature("cyl32")
         .set("ax3", new String[]{"-3.0673000000000104E-6[m]", "-4.980199999999997E-6[m]", "1.091500000000008E-6[m]"});
    model.geom("part1").feature("cyl32").set("r", "1.0239244416903303E-6[m]");
    model.geom("part1").feature("cyl32").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl33", "Cylinder");
    model.geom("part1").feature("cyl33")
         .set("pos", new String[]{"1.3802082182647627E-4[m]", "8.992897057258156E-5[m]", "6.284203881529654E-5[m]"});
    model.geom("part1").feature("cyl33").set("axistype", "cartesian");
    model.geom("part1").feature("cyl33")
         .set("ax3", new String[]{"2.264400000000005E-6[m]", "-4.2734999999999854E-6[m]", "7.4851000000000006E-6[m]"});
    model.geom("part1").feature("cyl33").set("r", "8.476163620818133E-7[m]");
    model.geom("part1").feature("cyl33").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl34", "Cylinder");
    model.geom("part1").feature("cyl34")
         .set("pos", new String[]{"1.2717783888097396E-4[m]", "7.27087199785618E-5[m]", "6.212565062974694E-5[m]"});
    model.geom("part1").feature("cyl34").set("axistype", "cartesian");
    model.geom("part1").feature("cyl34")
         .set("ax3", new String[]{"1.4466999999999941E-6[m]", "-2.3679999999999069E-7[m]", "6.955999999999999E-6[m]"});
    model.geom("part1").feature("cyl34").set("r", "1.75336187363033E-6[m]");
    model.geom("part1").feature("cyl34").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl35", "Cylinder");
    model.geom("part1").feature("cyl35")
         .set("pos", new String[]{"1.2903782835070135E-4[m]", "8.048987066654069E-5[m]", "6.158030718558253E-5[m]"});
    model.geom("part1").feature("cyl35").set("axistype", "cartesian");
    model.geom("part1").feature("cyl35")
         .set("ax3", new String[]{"-5.864499999999991E-6[m]", "4.6583000000000025E-6[m]", "5.364999999999905E-7[m]"});
    model.geom("part1").feature("cyl35").set("r", "1.896678294816561E-6[m]");
    model.geom("part1").feature("cyl35").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl36", "Cylinder");
    model.geom("part1").feature("cyl36")
         .set("pos", new String[]{"1.2081788284615602E-4[m]", "8.414059770686499E-5[m]", "6.423474247565074E-5[m]"});
    model.geom("part1").feature("cyl36").set("axistype", "cartesian");
    model.geom("part1").feature("cyl36")
         .set("ax3", new String[]{"8.328699999999987E-6[m]", "-1.3023999999999962E-6[m]", "-4.2735000000000125E-6[m]"});
    model.geom("part1").feature("cyl36").set("r", "3.496613128260997E-7[m]");
    model.geom("part1").feature("cyl36").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl37", "Cylinder");
    model.geom("part1").feature("cyl37")
         .set("pos", new String[]{"1.1770127832306145E-4[m]", "8.256590252442986E-5[m]", "6.20749659384152E-5[m]"});
    model.geom("part1").feature("cyl37").set("axistype", "cartesian");
    model.geom("part1").feature("cyl37")
         .set("ax3", new String[]{"-8.287999999999742E-7[m]", "2.5715000000000075E-6[m]", "4.876600000000014E-6[m]"});
    model.geom("part1").feature("cyl37").set("r", "9.422258000282088E-7[m]");
    model.geom("part1").feature("cyl37").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl38", "Cylinder");
    model.geom("part1").feature("cyl38")
         .set("pos", new String[]{"1.3146325017675855E-4[m]", "1.0006308221254228E-4[m]", "6.238680891424877E-5[m]"});
    model.geom("part1").feature("cyl38").set("axistype", "cartesian");
    model.geom("part1").feature("cyl38")
         .set("ax3", new String[]{"-1.4097000000000079E-6[m]", "-4.809999999999976E-7[m]", "6.356599999999993E-6[m]"});
    model.geom("part1").feature("cyl38").set("r", "1.459813092123085E-6[m]");
    model.geom("part1").feature("cyl38").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl39", "Cylinder");
    model.geom("part1").feature("cyl39")
         .set("pos", new String[]{"1.099843481081765E-4[m]", "9.181975761158417E-5[m]", "6.794185063723626E-5[m]"});
    model.geom("part1").feature("cyl39").set("axistype", "cartesian");
    model.geom("part1").feature("cyl39")
         .set("ax3", new String[]{"-4.698999999999936E-7[m]", "4.628699999999992E-6[m]", "-6.393600000000006E-6[m]"});
    model.geom("part1").feature("cyl39").set("r", "7.226142528238588E-8[m]");
    model.geom("part1").feature("cyl39").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl40", "Cylinder");
    model.geom("part1").feature("cyl40")
         .set("pos", new String[]{"1.3357292210677913E-4[m]", "7.443093244682502E-5[m]", "6.641335146604433E-5[m]"});
    model.geom("part1").feature("cyl40").set("axistype", "cartesian");
    model.geom("part1").feature("cyl40")
         .set("ax3", new String[]{"-8.61730000000001E-6[m]", "-3.1412999999999965E-6[m]", "2.0646000000000005E-6[m]"});
    model.geom("part1").feature("cyl40").set("r", "1.578663594776089E-6[m]");
    model.geom("part1").feature("cyl40").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl41", "Cylinder");
    model.geom("part1").feature("cyl41")
         .set("pos", new String[]{"1.3827271340361187E-4[m]", "7.244930844496955E-5[m]", "6.720991198260184E-5[m]"});
    model.geom("part1").feature("cyl41").set("axistype", "cartesian");
    model.geom("part1").feature("cyl41")
         .set("ax3", new String[]{"3.1265000000000047E-6[m]", "-7.5813000000000014E-6[m]", "3.7888E-6[m]"});
    model.geom("part1").feature("cyl41").set("r", "1.1618486606672665E-6[m]");
    model.geom("part1").feature("cyl41").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl42", "Cylinder");
    model.geom("part1").feature("cyl42")
         .set("pos", new String[]{"1.2578276023093017E-4[m]", "7.193937006583284E-5[m]", "7.162431549360241E-5[m]"});
    model.geom("part1").feature("cyl42").set("axistype", "cartesian");
    model.geom("part1").feature("cyl42")
         .set("ax3", new String[]{"-5.601799999999991E-6[m]", "-1.2765000000000004E-6[m]", "8.694999999999997E-6[m]"});
    model.geom("part1").feature("cyl42").set("r", "2.8007115668521333E-6[m]");
    model.geom("part1").feature("cyl42").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl43", "Cylinder");
    model.geom("part1").feature("cyl43")
         .set("pos", new String[]{"1.2695211414467968E-4[m]", "9.812548460137628E-5[m]", "7.194917810295454E-5[m]"});
    model.geom("part1").feature("cyl43").set("axistype", "cartesian");
    model.geom("part1").feature("cyl43")
         .set("ax3", new String[]{"4.539899999999973E-6[m]", "2.0941999999999977E-6[m]", "-5.720200000000007E-6[m]"});
    model.geom("part1").feature("cyl43").set("r", "1.3729696958255961E-6[m]");
    model.geom("part1").feature("cyl43").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl44", "Cylinder");
    model.geom("part1").feature("cyl44")
         .set("pos", new String[]{"1.3155912611700308E-4[m]", "9.020577976404483E-5[m]", "6.735445761764065E-5[m]"});
    model.geom("part1").feature("cyl44").set("axistype", "cartesian");
    model.geom("part1").feature("cyl44")
         .set("ax3", new String[]{"-3.6630000000001037E-7[m]", "-1.7279000000000009E-6[m]", "-5.105999999999947E-7[m]"});
    model.geom("part1").feature("cyl44").set("r", "3.1629616264661907E-7[m]");
    model.geom("part1").feature("cyl44").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl45", "Cylinder");
    model.geom("part1").feature("cyl45")
         .set("pos", new String[]{"1.3483855226554466E-4[m]", "8.631748387776637E-5[m]", "7.067702887249622E-5[m]"});
    model.geom("part1").feature("cyl45").set("axistype", "cartesian");
    model.geom("part1").feature("cyl45")
         .set("ax3", new String[]{"-1.0903899999999996E-5[m]", "-1.6132000000000136E-6[m]", "6.308500000000002E-6[m]"});
    model.geom("part1").feature("cyl45").set("r", "1.3947351840958993E-6[m]");
    model.geom("part1").feature("cyl45").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl46", "Cylinder");
    model.geom("part1").feature("cyl46")
         .set("pos", new String[]{"1.1875508071840657E-4[m]", "8.235562281056634E-5[m]", "7.264920906998161E-5[m]"});
    model.geom("part1").feature("cyl46").set("axistype", "cartesian");
    model.geom("part1").feature("cyl46")
         .set("ax3", new String[]{"7.622000000000043E-7[m]", "-4.1255E-6[m]", "-7.80700000000011E-7[m]"});
    model.geom("part1").feature("cyl46").set("r", "3.341593654695487E-7[m]");
    model.geom("part1").feature("cyl46").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl47", "Cylinder");
    model.geom("part1").feature("cyl47")
         .set("pos", new String[]{"1.3130625410791272E-4[m]", "9.137530694525907E-5[m]", "7.734725248013655E-5[m]"});
    model.geom("part1").feature("cyl47").set("axistype", "cartesian");
    model.geom("part1").feature("cyl47")
         .set("ax3", new String[]{"-3.6592999999999804E-6[m]", "-8.258400000000002E-6[m]", "-4.280900000000002E-6[m]"});
    model.geom("part1").feature("cyl47").set("r", "1.2249869178447745E-6[m]");
    model.geom("part1").feature("cyl47").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl48", "Cylinder");
    model.geom("part1").feature("cyl48")
         .set("pos", new String[]{"1.2497256317280954E-4[m]", "8.577357196611411E-5[m]", "7.83504390117617E-5[m]"});
    model.geom("part1").feature("cyl48").set("axistype", "cartesian");
    model.geom("part1").feature("cyl48")
         .set("ax3", new String[]{"-9.620000000000006E-6[m]", "9.6570000000001E-7[m]", "1.0693E-5[m]"});
    model.geom("part1").feature("cyl48").set("r", "4.14734857501967E-6[m]");
    model.geom("part1").feature("cyl48").set("h", "4.625E-7[m]");
    model.geom("part1").create("cyl49", "Cylinder");
    model.geom("part1").feature("cyl49")
         .set("pos", new String[]{"1.1009886711970105E-4[m]", "8.459907069357519E-5[m]", "8.164087353376619E-5[m]"});
    model.geom("part1").feature("cyl49").set("axistype", "cartesian");
    model.geom("part1").feature("cyl49")
         .set("ax3", new String[]{"1.343839999999999E-5[m]", "2.660299999999999E-6[m]", "4.965400000000005E-6[m]"});
    model.geom("part1").feature("cyl49").set("r", "1.6388311976884888E-6[m]");
    model.geom("part1").feature("cyl49").set("h", "4.625E-7[m]");

    model.param().set("L_elec", "9.524364283974882E-5[m]");

    model.geom("part1").run();
    model.component("comp1").geom("geom1").run();
    model.component("comp1").geom("geom1").measure().selection().init(3);
    model.component("comp1").geom("geom1").measure().selection().named("uni1");

    model.param().set("Vp", "2.2563042883443007E-14[m^3]");

    model.component("comp1").geom("geom1").measure().selection().init(2);
    model.component("comp1").geom("geom1").measure().selection().named("intsel1");

    model.param().set("Ap", "1.1896267024536184E-8[m^2]");
    model.param().set("rp_avg_spheres", "6.189833778159997E-6[m]");

//    Finished method call GenerateGeometryFromTable

    model.component("comp1").view("view2").camera().setIndex("position", "6.1e-4", 0);
    model.component("comp1").view("view2").camera().setIndex("position", "2.1e-4", 1);
    model.component("comp1").view("view2").camera().setIndex("position", "-4.1e-4", 2);
    model.component("comp1").view("view2").camera().setIndex("up", 0, 0);
    model.component("comp1").view("view2").camera().setIndex("up", 1, 1);
    model.component("comp1").view("view2").camera().set("up", new int[]{0, 1, 0});

    model.component("comp1").geom("geom1").run("adjsel3");

    model.title("\u5f02\u8d28\u7535\u6781\u51e0\u4f55\u751f\u6210");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u6839\u636e x \u5c04\u7ebf\u65ad\u5c42\u626b\u63cf\u6570\u636e\u6765\u521b\u5efa\u954d-\u9530-\u94b4 (NMC) \u7535\u6781\u7684\u4ee3\u8868\u6027\u4e09\u7ef4\u51e0\u4f55\u3002\u751f\u6210\u7684\u51e0\u4f55\u53ef\u7528\u4e8e\u540e\u7eed\u7684\u5f02\u8d28\u7535\u6781\u5efa\u6a21\u3002\n\n\u672c\u4f8b\u4f7f\u7528\u4e00\u79cd\u6a21\u578b\u65b9\u6cd5\u6765\u5faa\u73af\u904d\u5386\u9897\u7c92\u4f53\u79ef\u548c\u4e2d\u5fc3\u5750\u6807\u7684\u8868\u683c\u6570\u636e\u3002");

    model.mesh().clearMeshes();

    model.label("nmc_electrode_geometry.mph");

    model.component("comp1").geom("geom1").run("adjsel3");

    model.component("comp1").view("view2").set("renderwireframe", true);

    model.component("comp1").physics().create("lpeq", "LaplaceEquation", "geom1");
    model.component("comp1").physics("lpeq").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("lpeq").selection().named("geom1_comsel2");
    model.component("comp1").physics("lpeq").create("dir1", "DirichletBoundary", 2);
    model.component("comp1").physics("lpeq").feature("dir1").selection().set(18);
    model.component("comp1").physics("lpeq").create("dir2", "DirichletBoundary", 2);
    model.component("comp1").physics("lpeq").feature("dir2").selection().set(3);
    model.component("comp1").physics("lpeq").feature("dir2").setIndex("r", 1, 0);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "hmax");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "hmin");
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/lpeq", true);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("slc1", "Slice");
    model.result("pg1").label("\u62c9\u666e\u62c9\u65af\u65b9\u7a0b");
    model.result("pg1").feature("slc1").set("expr", "u");
    model.result("pg1").run();
    model.result("pg1").label("\u56e0\u53d8\u91cf u");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u901a\u91cf");
    model.result("pg2").create("str1", "Streamline");
    model.result("pg2").feature("str1").selection().set(18);
    model.result("pg2").feature("str1").set("pointtype", "arrow");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "1");
    model.result("pg2").feature("surf1").set("coloring", "uniform");
    model.result("pg2").feature("surf1").set("color", "green");
    model.result("pg2").feature("surf1").set("titletype", "none");
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().named("geom1_intsel1");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").create("surf2", "Surface");
    model.result("pg2").feature("surf2").set("expr", "1");
    model.result("pg2").feature("surf2").set("coloring", "uniform");
    model.result("pg2").feature("surf2").set("color", "gray");
    model.result("pg2").feature("surf2").set("titletype", "none");
    model.result("pg2").feature("surf2").create("sel1", "Selection");
    model.result("pg2").feature("surf2").feature("sel1").selection().set(1, 2, 18);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").set("edges", false);
    model.result("pg2").run();
    model.result().numerical().create("av1", "AvSurface");
    model.result().numerical("av1").set("intvolume", true);
    model.result().numerical("av1").label("\u8868\u9762\u5e73\u5747\u503c - \u6709\u6548\u901a\u91cf\u56e0\u5b50");
    model.result().numerical("av1").selection().set(3);
    model.result().numerical("av1").set("expr", new String[]{"dflux.u"});
    model.result().numerical("av1").set("descr", new String[]{"\u4e0b\u65b9\u8fb9\u754c\u901a\u91cf"});
    model.result().numerical("av1").set("unit", new String[]{"1/m"});
    model.result().numerical("av1").setIndex("expr", "dflux.u*L_elec", 0);
    model.result().numerical("av1").setIndex("descr", "\u6709\u6548\u901a\u91cf\u56e0\u5b50", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u8868\u9762\u5e73\u5747\u503c - \u6709\u6548\u901a\u91cf\u56e0\u5b50");
    model.result().numerical("av1").set("table", "tbl2");
    model.result().numerical("av1").setResult();

    model.param().set("f_eff", "0.554");
    model.param().descr("f_eff", "\u6709\u6548\u901a\u91cf\u56e0\u5b50");
    model.param().create("par2");
    model.param("par2").label("\u7535\u6781\u53c2\u6570");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2")
         .set("eps_l_b", "0.9", "\u9ecf\u5408\u5242\u4e2d\u7684\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570");
    model.param("par2")
         .set("eps_s_b", "0.1", "\u9ecf\u5408\u5242\u4e2d\u7684\u78b3\u586b\u6599\u4f53\u79ef\u5206\u6570");
    model.param("par2").set("csmax", "49000[mol/m^3]", "\u9897\u7c92\u4e2d\u7684\u6700\u5927\u9502\u6d53\u5ea6");
    model.param("par2").set("socmax", "0.975", "\u6700\u5927\u9897\u7c92\u9502\u5316\u6c34\u5e73");
    model.param("par2").set("socmin", "0.01", "\u6700\u5c0f\u9897\u7c92\u9502\u5316\u6c34\u5e73");
    model.param("par2")
         .set("Capacity", "csmax*(socmax-socmin)*F_const*Vp", "\u7535\u6781\u7684\u6807\u79f0\u5bb9\u91cf");
    model.param("par2").set("I_1C", "Capacity/1[h]", "1C \u653e\u7535\u7535\u6d41");
    model.param("par2").set("cs0", "csmax*socinit", "\u9897\u7c92\u4e2d\u7684\u521d\u59cb\u9502\u6d53\u5ea6");
    model.param("par2").set("sigma_s", "1[S/m]", "\u591a\u5b54\u5bfc\u7535\u9ecf\u5408\u5242\u7535\u5bfc\u7387");
    model.param("par2").set("C_rate", "2", "\u653e\u7535\u500d\u7387");
    model.param("par2")
         .set("socinit", "socmin+0.5*0", "\u521d\u59cb\u9502\u5316\u6c34\u5e73\uff0cnmc\uff08EIS \u7814\u7a76\u4f7f\u7528 0.5\uff0c\u653e\u7535\u4f7f\u7528 socmin\uff09");
    model.param("par2").set("epsl_sep", "0.4", "\u9694\u819c\u4e2d\u7684\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570");
    model.param("par2")
         .set("i0_ref_NMC", "10[A/m^2]", "\u9502\u5d4c\u5165 NMC \u7684\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param("par2")
         .set("i0_ref_Li", "100[A/m^2]", "\u9502\u6c89\u79ef/\u6eb6\u89e3\u7684\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param("par2").set("C_dl_NMC", "0.2[F/m^2]", "\u53cc\u7535\u5c42\u7535\u5bb9\uff0cNMC \u9897\u7c92");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 1);

    model.component("comp2").mesh().create("mesh2");

    model.component("comp2").geom("geom2").create("i1", "Interval");
    model.component("comp2").geom("geom2").feature("i1").set("specify", "len");
    model.component("comp2").geom("geom2").feature("i1").setIndex("len", "L_sep", 0);
    model.component("comp2").geom("geom2").feature("i1").setIndex("len", "L_elec", 1);
    model.component("comp2").geom("geom2").run("i1");

    model.component("comp2").physics().create("liion", "LithiumIonBatteryMPH", "geom2");

    model.study("std1").feature("stat").setSolveFor("/physics/liion", true);

    model.component("comp2").geom("geom2").run();

    model.component("comp2").material().create("mat1", "Common");
    model.component("comp2").material("mat1").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp2").material("mat1").propertyGroup()
         .create("ElectrodePotential", "ElectrodePotential", "Equilibrium potential");
    model.component("comp2").material("mat1").propertyGroup()
         .create("OperationalSOC", "OperationalSOC", "Operational electrode state of charge");
    model.component("comp2").material("mat1").propertyGroup().create("ic", "ic", "Intercalation strain");
    model.component("comp2").material("mat1").propertyGroup("ic").func().create("int1", "Interpolation");
    model.component("comp2").material("mat1").propertyGroup()
         .create("EquilibriumConcentration", "EquilibriumConcentration", "Equilibrium concentration");
    model.component("comp2").material("mat1").propertyGroup()
         .create("EquilibriumPotentialWithDOCInput", "EquilibriumPotentialWithDOCInput", "Equilibrium potential (using degree of conversion as model input)");
    model.component("comp2").material("mat1").propertyGroup()
         .create("EquilibriumDegreeOfConversion", "EquilibriumDegreeOfConversion", "Equilibrium degree of conversion");
    model.component("comp2").material("mat1").propertyGroup().create("pg1", "def", "Electric conductivity");
    model.component("comp2").material("mat1").propertyGroup("pg1").func().create("int1", "Interpolation");
    model.component("comp2").material("mat1").label("NMC 111, LiNi0.33Mn0.33Co0.33O2 (Positive, Li-ion Battery)");
    model.component("comp2").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp2").material("mat1").propertyGroup("def").func("int1").label("Interpolation 1");
    model.component("comp2").material("mat1").propertyGroup("def").func("int1").set("funcname", "Eeq");
    model.component("comp2").material("mat1").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"0", "4.44"}, 
         {"0.032", "4.34"}, 
         {"0.102", "4.23"}, 
         {"0.187", "4.13"}, 
         {"0.289", "4.025"}, 
         {"0.38", "3.945"}, 
         {"0.543", "3.835"}, 
         {"0.775", "3.71"}, 
         {"0.872", "3.62"}, 
         {"0.925", "3.51"}, 
         {"0.943", "3.42"}, 
         {"0.957", "3.30"}, 
         {"0.966", "3.165"}, 
         {"0.970", "3.02"}, 
         {"0.972", "2.90"}, 
         {"0.975", "2.688"}});
    model.component("comp2").material("mat1").propertyGroup("def").func("int1").set("extrap", "linear");
    model.component("comp2").material("mat1").propertyGroup("def").func("int1").set("fununit", new String[]{"V"});
    model.component("comp2").material("mat1").propertyGroup("def").func("int1").set("argunit", new String[]{""});
    model.component("comp2").material("mat1").propertyGroup("def").func("int1").set("defineinv", true);
    model.component("comp2").material("mat1").propertyGroup("def").func("int1").set("funcinvname", "Eeq_inv");
    model.component("comp2").material("mat1").propertyGroup("def").set("poissonsratio", "");
    model.component("comp2").material("mat1").propertyGroup("def").set("youngsmodulus", "");
    model.component("comp2").material("mat1").propertyGroup("def").set("thermalconductivity", "");
    model.component("comp2").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp2").material("mat1").propertyGroup("def").set("poissonsratio", "0.25");
    model.component("comp2").material("mat1").propertyGroup("def")
         .setPropertyInfo("poissonsratio", "Mechanical and physical properties of LiNi0.33Mn0.33Co0.33O2 (NMC),\nE Chenga, K. Hong, N. Taylor, H. Choe,\nJ. Wolfenstinec, J. Sakamotoa,\nJournal of the European Ceramic Society 37 (2017) 3213\u20133217");
    model.component("comp2").material("mat1").propertyGroup("def").set("youngsmodulus", "78[GPa]");
    model.component("comp2").material("mat1").propertyGroup("def")
         .setPropertyInfo("youngsmodulus", "Mechanical and physical properties of LiNi0.33Mn0.33Co0.33O2 (NMC),\nE Chenga, K. Hong, N. Taylor, H. Choe,\nJ. Wolfenstinec, J. Sakamotoa,\nJournal of the European Ceramic Society 37 (2017) 3213\u20133217");
    model.component("comp2").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"3.6[W/(m*K)]", "0", "0", "0", "3.6[W/(m*K)]", "0", "0", "0", "3.6[W/(m*K)]"});
    model.component("comp2").material("mat1").propertyGroup("def")
         .setPropertyInfo("thermalconductivity", "Mechanical and physical properties of LiNi0.33Mn0.33Co0.33O2 (NMC),\nE Chenga, K. Hong, N. Taylor, H. Choe,\nJ. Wolfenstinec, J. Sakamotoa,\nJournal of the European Ceramic Society 37 (2017) 3213\u20133217");
    model.component("comp2").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"1.2e-5[1/K]", "0", "0", "0", "1.2e-5[1/K]", "0", "0", "0", "1.2e-5[1/K]"});
    model.component("comp2").material("mat1").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Mechanical and physical properties of LiNi0.33Mn0.33Co0.33O2 (NMC),\nE Chenga, K. Hong, N. Taylor, H. Choe,\nJ. Wolfenstinec, J. Sakamotoa,\nJournal of the European Ceramic Society 37 (2017) 3213\u20133217");
    model.component("comp2").material("mat1").propertyGroup("def")
         .set("diffusion", new String[]{"1e-14[m^2/s]", "0", "0", "0", "1e-14[m^2/s]", "0", "0", "0", "1e-14[m^2/s]"});
    model.component("comp2").material("mat1").propertyGroup("def")
         .setPropertyInfo("diffusion", "Jing Ying Ko et al, J. Electrochem. Soc., 166, A2939");
    model.component("comp2").material("mat1").propertyGroup("def").set("csmax", "49000[mol/m^3]");
    model.component("comp2").material("mat1").propertyGroup("def").descr("csmax", "");
    model.component("comp2").material("mat1").propertyGroup("ElectrodePotential").label("Equilibrium potential");
    model.component("comp2").material("mat1").propertyGroup("ElectrodePotential").identifier("eeq");
    model.component("comp2").material("mat1").propertyGroup("ElectrodePotential")
         .set("Eeq", "def.Eeq(doc)+dEeqdT*(T-298[K])");
    model.component("comp2").material("mat1").propertyGroup("ElectrodePotential")
         .setPropertyInfo("Eeq", "W. Zheng, M. Shui, J. Shu, S. Gao, D. Xu, L. Chen, L. Feng and Y. Ren, \" GITT studies on oxide cathode LiNi1/3Co1/3Mn1/3O2 synthesized by citric acid assisted high-energy ball milling\", Bull. Mater. Sci., vol. 36, p. A495, 2013");
    model.component("comp2").material("mat1").propertyGroup("ElectrodePotential")
         .set("dEeqdT", "-10[J/mol/K]/F_const");
    model.component("comp2").material("mat1").propertyGroup("ElectrodePotential")
         .setPropertyInfo("dEeqdT", "V Viswanathan, D Choi, D Wang, W Xu, S Towne, R Williford, JG Zhang, J Liu and Z Yang \"Effect of entropy change on lithium intercalation in cathodes and anodes on Li-ion battery thermal management\", Journal of Power Sources 195 (2010) 3720-3729");
    model.component("comp2").material("mat1").propertyGroup("ElectrodePotential").set("cEeqref", "def.csmax");
    model.component("comp2").material("mat1").propertyGroup("ElectrodePotential")
         .setPropertyInfo("cEeqref", "W. Zheng, M. Shui, J. Shu, S. Gao, D. Xu, L. Chen, L. Feng and Y. Ren, \" GITT studies on oxide cathode LiNi1/3Co1/3Mn1/3O2 synthesized by citric acid assisted high-energy ball milling\", Bull. Mater. Sci., vol. 36, p. A495, 2013");
    model.component("comp2").material("mat1").propertyGroup("ElectrodePotential").set("doc", "c/cEeqref");
    model.component("comp2").material("mat1").propertyGroup("ElectrodePotential")
         .descr("doc", "Degree of conversion (state of lithiation)");
    model.component("comp2").material("mat1").propertyGroup("ElectrodePotential").addInput("concentration");
    model.component("comp2").material("mat1").propertyGroup("ElectrodePotential").addInput("temperature");
    model.component("comp2").material("mat1").propertyGroup("OperationalSOC")
         .label("Operational electrode state of charge");
    model.component("comp2").material("mat1").propertyGroup("OperationalSOC").identifier("opsoc");
    model.component("comp2").material("mat1").propertyGroup("OperationalSOC").set("socmax", "def.Eeq_inv(E_min)");
    model.component("comp2").material("mat1").propertyGroup("OperationalSOC").set("socmin", "def.Eeq_inv(E_max)");

    return model;
  }

  public static Model run4(Model model) {
    model.component("comp2").material("mat1").propertyGroup("OperationalSOC").set("E_max", "4.4[V]");
    model.component("comp2").material("mat1").propertyGroup("OperationalSOC").set("E_min", "3.3[V]");
    model.component("comp2").material("mat1").propertyGroup("ic").label("Intercalation strain");
    model.component("comp2").material("mat1").propertyGroup("ic").func("int1").label("Interpolation 1");
    model.component("comp2").material("mat1").propertyGroup("ic").func("int1").set("funcname", "dVOLdSOL");
    model.component("comp2").material("mat1").propertyGroup("ic").func("int1")
         .set("table", new String[][]{{"1", "0"}, 
         {"0.9260263416001121", "-0.010256410256411108"}, 
         {"0.8670351688384477", "-0.1948717948717955"}, 
         {"0.8113086731119519", "-0.27692307692307727"}, 
         {"0.7506669468964551", "-0.37948717948718036"}, 
         {"0.6949460557657279", "-0.502564102564103"}, 
         {"0.628563822334314", "-0.5846153846153856"}, 
         {"0.55562421185372", "-0.6666666666666674"}, 
         {"0.501531455793751", "-0.7076923076923083"}, 
         {"0.4441600112091916", "-0.7487179487179496"}, 
         {"0.3851716407454113", "-0.953846153846154"}, 
         {"0.3278338237354632", "-1.241025641025642"}, 
         {"0.2737943113352951", "-1.671794871794872"}, 
         {"0.24269440941572107", "-2.0205128205128213"}});
    model.component("comp2").material("mat1").propertyGroup("ic").func("int1").set("extrap", "linear");
    model.component("comp2").material("mat1").propertyGroup("ic").func("int1").set("fununit", new String[]{"%"});
    model.component("comp2").material("mat1").propertyGroup("ic").func("int1").set("argunit", new String[]{"1"});
    model.component("comp2").material("mat1").propertyGroup("ic").identifier("is");
    model.component("comp2").material("mat1").propertyGroup("ic").set("dvol", "dVOLdSOL(c/def.csmax)");
    model.component("comp2").material("mat1").propertyGroup("ic")
         .setPropertyInfo("dvol", "R. Koerver and others, \u201cChemo-mechanical expansion of lithium electrode materials \u2014 on the route to mechanically optimized all-solid-state batteries,\u201d Energy Environ. Sci., vol. 11, pp. 2142\u20132158, 201");
    model.component("comp2").material("mat1").propertyGroup("ic").addInput("concentration");
    model.component("comp2").material("mat1").propertyGroup("EquilibriumConcentration")
         .label("Equilibrium concentration");
    model.component("comp2").material("mat1").propertyGroup("EquilibriumConcentration")
         .set("csEq", "def.csmax*def.Eeq_inv(V)");
    model.component("comp2").material("mat1").propertyGroup("EquilibriumConcentration")
         .addInput("electricpotential");
    model.component("comp2").material("mat1").propertyGroup("EquilibriumPotentialWithDOCInput")
         .label("Equilibrium potential (using degree of conversion as model input)");
    model.component("comp2").material("mat1").propertyGroup("EquilibriumPotentialWithDOCInput")
         .set("Eeq", "def.Eeq(doc)+dEeqdT*(T-298[K])");
    model.component("comp2").material("mat1").propertyGroup("EquilibriumPotentialWithDOCInput")
         .set("dEeqdT", "-10[J/mol/K]/F_const");
    model.component("comp2").material("mat1").propertyGroup("EquilibriumPotentialWithDOCInput")
         .addInput("degreeofconversion");
    model.component("comp2").material("mat1").propertyGroup("EquilibriumPotentialWithDOCInput")
         .addInput("temperature");
    model.component("comp2").material("mat1").propertyGroup("EquilibriumDegreeOfConversion")
         .label("Equilibrium degree of conversion");
    model.component("comp2").material("mat1").propertyGroup("EquilibriumDegreeOfConversion")
         .set("docEq", "def.Eeq_inv(V)");
    model.component("comp2").material("mat1").propertyGroup("EquilibriumDegreeOfConversion")
         .addInput("electricpotential");
    model.component("comp2").material("mat1").propertyGroup("pg1").func("int1").set("source", "file");
    model.component("comp2").material("mat1").propertyGroup("pg1").func("int1").label("Interpolation 1");
    model.component("comp2").material("mat1").propertyGroup("pg1").func("int1").set("importedname", "NMC_333.txt");
    model.component("comp2").material("mat1").propertyGroup("pg1").func("int1").set("importeddim", "2D");
    model.component("comp2").material("mat1").propertyGroup("pg1").func("int1")
         .set("funcnametable", new String[][]{{"log_sigmas", "1"}});
    model.component("comp2").material("mat1").propertyGroup("pg1").func("int1").set("filecolumns", 3);
    model.component("comp2").material("mat1").propertyGroup("pg1").func("int1")
         .set("columnKeys", new String[]{"col1", "col2", "col3"});
    model.component("comp2").material("mat1").propertyGroup("pg1").func("int1")
         .set("columnType", new String[]{"col1", "arg", "col2", "arg", "col3", "value"});
    model.component("comp2").material("mat1").propertyGroup("pg1").func("int1")
         .set("funcnames", new String[]{"col1", "int1", "col2", "int1", "col3", "log_sigmas"});
    model.component("comp2").material("mat1").propertyGroup("pg1").func("int1").set("fununit", new String[]{""});
    model.component("comp2").material("mat1").propertyGroup("pg1").func("int1")
         .set("argunit", new String[]{"1", "1/K"});
    model.component("comp2").material("mat1").propertyGroup("pg1").func("int1").set("sourcetype", "model");
    model.component("comp2").material("mat1").propertyGroup("pg1")
         .set("electricconductivity", new String[]{"10^log_sigmas(x,1000/T)[S/cm]", "0", "0", "0", "10^log_sigmas(x,1000/T)[S/cm]", "0", "0", "0", "10^log_sigmas(x,1000/T)[S/cm]"});
    model.component("comp2").material("mat1").propertyGroup("pg1")
         .setPropertyInfo("electricconductivity", "Ruhul Amin and Yet-Ming Chiang 2016 J. Electrochem. Soc. 163 A1512");
    model.component("comp2").material("mat1").propertyGroup("pg1").set("x", "min(max(1-c/def.csmax,0),1)");
    model.component("comp2").material("mat1").propertyGroup("pg1").descr("x", "Degree of delithiation");
    model.component("comp2").material("mat1").propertyGroup("pg1").addInput("temperature");
    model.component("comp2").material("mat1").propertyGroup("pg1").addInput("concentration");
    model.component("comp2").material().create("mat2", "Common");
    model.component("comp2").material("mat2").propertyGroup()
         .create("ElectrodePotential", "ElectrodePotential", "Equilibrium potential");
    model.component("comp2").material("mat2").label("Lithium Metal, Li (Negative, Li-ion Battery)");
    model.component("comp2").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp2").material("mat2").propertyGroup("def").set("youngsmodulus", "");
    model.component("comp2").material("mat2").propertyGroup("def").set("poissonsratio", "");
    model.component("comp2").material("mat2").propertyGroup("def").set("density", "");
    model.component("comp2").material("mat2").propertyGroup("def").set("thermalconductivity", "");
    model.component("comp2").material("mat2").propertyGroup("def").set("electricconductivity", "");
    model.component("comp2").material("mat2").propertyGroup("def").set("heatcapacity", "");
    model.component("comp2").material("mat2").propertyGroup("def").set("youngsmodulus", "2[GPa]");
    model.component("comp2").material("mat2").propertyGroup("def")
         .setPropertyInfo("youngsmodulus", "Yue Qi et al 2010 J. Electrochem. Soc. 157 A558");
    model.component("comp2").material("mat2").propertyGroup("def").set("poissonsratio", "0.34");
    model.component("comp2").material("mat2").propertyGroup("def")
         .setPropertyInfo("poissonsratio", "Yue Qi et al 2010 J. Electrochem. Soc. 157 A558");
    model.component("comp2").material("mat2").propertyGroup("def").set("density", "0.534[g/cm^3]");
    model.component("comp2").material("mat2").propertyGroup("def")
         .setPropertyInfo("density", "https://en.wikipedia.org/wiki/Lithium");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"84.8[W/(m*K)]", "0", "0", "0", "84.8[W/(m*K)]", "0", "0", "0", "84.8[W/(m*K)]"});
    model.component("comp2").material("mat2").propertyGroup("def")
         .setPropertyInfo("thermalconductivity", "https://en.wikipedia.org/wiki/Lithium");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"1/(92.8[n\u03a9*m])", "0", "0", "0", "1/(92.8[n\u03a9*m])", "0", "0", "0", "1/(92.8[n\u03a9*m])"});
    model.component("comp2").material("mat2").propertyGroup("def")
         .setPropertyInfo("electricconductivity", "https://en.wikipedia.org/wiki/Lithium");
    model.component("comp2").material("mat2").propertyGroup("def").set("heatcapacity", "3.58[kJ/kg/K]");
    model.component("comp2").material("mat2").propertyGroup("def")
         .setPropertyInfo("heatcapacity", "https://en.wikipedia.org/wiki/Lithium");
    model.component("comp2").material("mat2").propertyGroup("ElectrodePotential").label("Equilibrium potential");
    model.component("comp2").material("mat2").propertyGroup("ElectrodePotential").identifier("eeq");
    model.component("comp2").material("mat2").propertyGroup("ElectrodePotential").set("Eeq", "0[V]");
    model.component("comp2").material("mat2").propertyGroup("ElectrodePotential").set("dEeqdT", "0[V/K]");
    model.component("comp2").material("mat2").propertyGroup("ElectrodePotential").set("cEeqref", "0[M]");
    model.component("comp2").material("mat2").propertyGroup("ElectrodePotential").addInput("concentration");
    model.component("comp2").material("mat2").propertyGroup("ElectrodePotential").addInput("temperature");
    model.component("comp2").material().create("mat3", "Common");
    model.component("comp2").material("mat3").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp2").material("mat3").propertyGroup()
         .create("ElectrolyteConductivity", "ElectrolyteConductivity", "Electrolyte conductivity");
    model.component("comp2").material("mat3").propertyGroup("ElectrolyteConductivity").func()
         .create("int1", "Interpolation");
    model.component("comp2").material("mat3").propertyGroup()
         .create("SpeciesProperties", "SpeciesProperties", "Species properties");
    model.component("comp2").material("mat3").propertyGroup("SpeciesProperties").func()
         .create("int1", "Interpolation");
    model.component("comp2").material("mat3").propertyGroup("SpeciesProperties").func()
         .create("int2", "Interpolation");
    model.component("comp2").material("mat3").propertyGroup()
         .create("ElectrolyteSaltConcentration", "ElectrolyteSaltConcentration", "Electrolyte salt concentration");
    model.component("comp2").material("mat3").label("LiPF6 in 3:7 EC:EMC (Liquid, Li-ion Battery)");
    model.component("comp2").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp2").material("mat3").propertyGroup("def").func("int1").label("Interpolation 1");
    model.component("comp2").material("mat3").propertyGroup("def").func("int1").set("funcname", "DL_int1");
    model.component("comp2").material("mat3").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"200", "3.9e-10/(1-200*59e-6)"}, 
         {"500", "4.12e-10/(1-500*59e-6)"}, 
         {"800", "4e-10/(1-800*59e-6)"}, 
         {"1000", "3.8e-10/(1-1000*59e-6)"}, 
         {"1200", "3.50e-10/(1-1200*59e-6)"}, 
         {"1600", "2.68e-10/(1-1600*59e-6)"}, 
         {"2000", "1.9e-10/(1-2000*59e-6)"}});
    model.component("comp2").material("mat3").propertyGroup("def").func("int1").set("interp", "piecewisecubic");
    model.component("comp2").material("mat3").propertyGroup("def").func("int1")
         .set("fununit", new String[]{"m^2/s"});
    model.component("comp2").material("mat3").propertyGroup("def").func("int1").set("argunit", new String[]{""});
    model.component("comp2").material("mat3").propertyGroup("def")
         .set("diffusion", new String[]{"DL_int1(c/1[mol/m^3])*exp(16500/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))", "0", "0", "0", "DL_int1(c/1[mol/m^3])*exp(16500/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))", "0", "0", "0", "DL_int1(c/1[mol/m^3])*exp(16500/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))"});
    model.component("comp2").material("mat3").propertyGroup("def")
         .setPropertyInfo("diffusion", "T. G. Zavalis, M. Behm, and G. Lindbergh, \"Investigations of Short-Circuit Scenarios in a Lithium-Ion Battery Cell,\" J. Electrochem. Soc., vol. 159, p. A848, 2012.");
    model.component("comp2").material("mat3").propertyGroup("def").set("T_ref", "298[K]");
    model.component("comp2").material("mat3").propertyGroup("def").descr("T_ref", "");
    model.component("comp2").material("mat3").propertyGroup("def").set("T2", "min(393.15,max(T,223.15))");
    model.component("comp2").material("mat3").propertyGroup("def").descr("T2", "");
    model.component("comp2").material("mat3").propertyGroup("def").addInput("concentration");
    model.component("comp2").material("mat3").propertyGroup("def").addInput("temperature");
    model.component("comp2").material("mat3").propertyGroup("ElectrolyteConductivity")
         .label("Electrolyte conductivity");
    model.component("comp2").material("mat3").propertyGroup("ElectrolyteConductivity").func("int1")
         .label("Interpolation 1");
    model.component("comp2").material("mat3").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("funcname", "sigmal_int1");
    model.component("comp2").material("mat3").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("table", new String[][]{{"0", "1e-6"}, 
         {"200", "0.455"}, 
         {"500", "0.783"}, 
         {"800", "0.935"}, 
         {"1000", "0.95"}, 
         {"1200", "0.927"}, 
         {"1600", "0.78"}, 
         {"2000", "0.60"}, 
         {"2200", "0.515"}});
    model.component("comp2").material("mat3").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("interp", "piecewisecubic");
    model.component("comp2").material("mat3").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("fununit", new String[]{"S/m"});
    model.component("comp2").material("mat3").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("argunit", new String[]{""});
    model.component("comp2").material("mat3").propertyGroup("ElectrolyteConductivity")
         .set("sigmal", new String[]{"sigmal_int1(c/1[mol/m^3])*exp(4000/8.314*(1/(T_ref2/1[K])-1/(T3/1[K])))", "0", "0", "0", "sigmal_int1(c/1[mol/m^3])*exp(4000/8.314*(1/(T_ref2/1[K])-1/(T3/1[K])))", "0", "0", "0", "sigmal_int1(c/1[mol/m^3])*exp(4000/8.314*(1/(T_ref2/1[K])-1/(T3/1[K])))"});
    model.component("comp2").material("mat3").propertyGroup("ElectrolyteConductivity")
         .setPropertyInfo("sigmal", "T. G. Zavalis, M. Behm, and G. Lindbergh, \"Investigations of Short-Circuit Scenarios in a Lithium-Ion Battery Cell,\" J. Electrochem. Soc., vol. 159, p. A848, 2012.");
    model.component("comp2").material("mat3").propertyGroup("ElectrolyteConductivity").set("T_ref2", "298[K]");
    model.component("comp2").material("mat3").propertyGroup("ElectrolyteConductivity").descr("T_ref2", "");
    model.component("comp2").material("mat3").propertyGroup("ElectrolyteConductivity")
         .set("T3", "min(393.15,max(T,223.15))");
    model.component("comp2").material("mat3").propertyGroup("ElectrolyteConductivity").descr("T3", "");
    model.component("comp2").material("mat3").propertyGroup("ElectrolyteConductivity").addInput("concentration");
    model.component("comp2").material("mat3").propertyGroup("ElectrolyteConductivity").addInput("temperature");
    model.component("comp2").material("mat3").propertyGroup("SpeciesProperties").label("Species properties");
    model.component("comp2").material("mat3").propertyGroup("SpeciesProperties").func("int1")
         .label("Interpolation 1");
    model.component("comp2").material("mat3").propertyGroup("SpeciesProperties").func("int1")
         .set("funcname", "transpNm_int1");
    model.component("comp2").material("mat3").propertyGroup("SpeciesProperties").func("int1")
         .set("table", new String[][]{{"200", "0.37"}, 
         {"500", "0.322"}, 
         {"800", "0.27"}, 
         {"1000", "0.251"}, 
         {"1200", "0.248"}, 
         {"1600", "0.236"}, 
         {"2000", "0.11"}});
    model.component("comp2").material("mat3").propertyGroup("SpeciesProperties").func("int1")
         .set("interp", "piecewisecubic");
    model.component("comp2").material("mat3").propertyGroup("SpeciesProperties").func("int1")
         .set("fununit", new String[]{""});
    model.component("comp2").material("mat3").propertyGroup("SpeciesProperties").func("int1")
         .set("argunit", new String[]{""});
    model.component("comp2").material("mat3").propertyGroup("SpeciesProperties").func("int2")
         .label("Interpolation 2");
    model.component("comp2").material("mat3").propertyGroup("SpeciesProperties").func("int2")
         .set("funcname", "actdep_int1");
    model.component("comp2").material("mat3").propertyGroup("SpeciesProperties").func("int2")
         .set("table", new String[][]{{"200", "0"}, 
         {"500", "0.29"}, 
         {"800", "0.695"}, 
         {"1000", "1"}, 
         {"1200", "1.32"}, 
         {"1600", "2.07"}, 
         {"2000", "2.50"}});
    model.component("comp2").material("mat3").propertyGroup("SpeciesProperties").func("int2")
         .set("interp", "piecewisecubic");
    model.component("comp2").material("mat3").propertyGroup("SpeciesProperties").func("int2")
         .set("fununit", new String[]{""});
    model.component("comp2").material("mat3").propertyGroup("SpeciesProperties").func("int2")
         .set("argunit", new String[]{""});
    model.component("comp2").material("mat3").propertyGroup("SpeciesProperties")
         .set("transpNum", "transpNm_int1(c/1[mol/m^3])");
    model.component("comp2").material("mat3").propertyGroup("SpeciesProperties")
         .setPropertyInfo("transpNum", "A. Nyman, M. Behm, and G. Lindbergh, \u201cElectrochemical characterisation and modelling of the mass transport phenomena in LiPF6-EC-EMC,\u201d Electrochim. Acta, vol. 53, p. 6356, 2008.");
    model.component("comp2").material("mat3").propertyGroup("SpeciesProperties")
         .set("fcl", "actdep_int1(c/1[mol/m^3])*exp(-1000/8.314*(1/(T_ref3/1[K])-1/(T4/1[K])))");
    model.component("comp2").material("mat3").propertyGroup("SpeciesProperties")
         .setPropertyInfo("fcl", "T. G. Zavalis, M. Behm, and G. Lindbergh, \"Investigations of Short-Circuit Scenarios in a Lithium-Ion Battery Cell,\" J. Electrochem. Soc., vol. 159, p. A848, 2012.");
    model.component("comp2").material("mat3").propertyGroup("SpeciesProperties")
         .set("T4", "min(393.15,max(T,223.15))");
    model.component("comp2").material("mat3").propertyGroup("SpeciesProperties").descr("T4", "");
    model.component("comp2").material("mat3").propertyGroup("SpeciesProperties").set("T_ref3", "298[K]");
    model.component("comp2").material("mat3").propertyGroup("SpeciesProperties").descr("T_ref3", "");
    model.component("comp2").material("mat3").propertyGroup("SpeciesProperties").addInput("concentration");
    model.component("comp2").material("mat3").propertyGroup("SpeciesProperties").addInput("temperature");
    model.component("comp2").material("mat3").propertyGroup("ElectrolyteSaltConcentration")
         .label("Electrolyte salt concentration");
    model.component("comp2").material("mat3").propertyGroup("ElectrolyteSaltConcentration").identifier("cElsalt");
    model.component("comp2").material("mat3").propertyGroup("ElectrolyteSaltConcentration")
         .set("cElsalt", "1200[mol/m^3]");
    model.component("comp2").material("mat2").selection().geom("geom2", 0);
    model.component("comp2").material("mat2").selection().set(1);
    model.component("comp2").material("mat3").selection().all();

    model.component("comp2").physics("liion").prop("Ac").set("Ac", "A_cross");
    model.component("comp2").physics("liion").feature("sep1").set("epsl", "epsl_sep");
    model.component("comp2").physics("liion").create("pce1", "PorousElectrode", 1);
    model.component("comp2").physics("liion").feature("pce1").selection().set(2);
    model.component("comp2").physics("liion").feature("pce1")
         .set("sigma", new String[]{"sigma_s", "0", "0", "0", "sigma_s", "0", "0", "0", "sigma_s"});
    model.component("comp2").physics("liion").feature("pce1").set("epss", "eps_particles");
    model.component("comp2").physics("liion").feature("pce1").set("epsl", "eps_l_b*eps_binder");
    model.component("comp2").physics("liion").feature("pce1").set("IonicCorrModel", "userdef");
    model.component("comp2").physics("liion").feature("pce1").set("fl", "f_eff*(eps_l_b^1.5)");
    model.component("comp2").physics("liion").feature("pce1").set("ElectricCorrModel", "userdef");
    model.component("comp2").physics("liion").feature("pce1").set("fs", "f_eff");
    model.component("comp2").physics("liion").feature("pce1").set("DiffusionCorrModel", "userdef");
    model.component("comp2").physics("liion").feature("pce1").set("fDl", "f_eff*(eps_l_b^1.5)");
    model.component("comp2").physics("liion").feature("pce1").feature("pin1").set("ParticleMaterial", "mat1");
    model.component("comp2").physics("liion").feature("pce1").feature("pin1").set("csinit", "cs0");
    model.component("comp2").physics("liion").feature("pce1").feature("pin1").set("rp", "rp_avg_spheres");
    model.component("comp2").physics("liion").feature("pce1").feature("per1").set("MaterialOption", "mat1");
    model.component("comp2").physics("liion").feature("pce1").feature("per1").set("i0_ref", "i0_ref_NMC");
    model.component("comp2").physics("liion").feature("pce1").feature("per1")
         .set("ActiveSpecificSurfaceAreaType", "userdef");
    model.component("comp2").physics("liion").feature("pce1").feature("per1").set("Av", "Av_particles");
    model.component("comp2").physics("liion").create("es1", "ElectrodeSurface", 0);
    model.component("comp2").physics("liion").feature("es1").selection().set(1);
    model.component("comp2").physics("liion").feature("es1").feature("er1").set("i0_ref", "i0_ref_Li");
    model.component("comp2").physics("liion").create("ec1", "ElectrodeCurrent", 0);
    model.component("comp2").physics("liion").feature("ec1").selection().set(3);
    model.component("comp2").physics("liion").feature("ec1").set("Its", "-I_1C*C_rate");

    model.component("comp2").cpl().create("intop1", "Integration");
    model.component("comp2").cpl("intop1").set("axisym", true);
    model.component("comp2").cpl("intop1").selection().geom("geom2", 0);
    model.component("comp2").cpl("intop1").selection().set(3);
    model.component("comp2").cpl("intop1").set("opname", "intop_nmc_cc");

    model.study().create("std2");
    model.study("std2").create("cdi", "CurrentDistributionInitialization");
    model.study("std2").feature("cdi").set("ftplistmethod", "manual");
    model.study("std2").feature("cdi").set("solnum", "auto");
    model.study("std2").feature("cdi").set("notsolnum", "auto");
    model.study("std2").feature("cdi").set("outputmap", new String[]{});
    model.study("std2").feature("cdi").set("ngenAUX", "1");
    model.study("std2").feature("cdi").set("goalngenAUX", "1");
    model.study("std2").feature("cdi").set("ngenAUX", "1");
    model.study("std2").feature("cdi").set("goalngenAUX", "1");
    model.study("std2").feature("cdi").setSolveFor("/physics/lpeq", true);
    model.study("std2").feature("cdi").setSolveFor("/physics/liion", true);
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").set("plotgroup", "Default");
    model.study("std2").feature("time").set("ftplistmethod", "manual");
    model.study("std2").feature("time").set("initialtime", "0");
    model.study("std2").feature("time").set("solnum", "auto");
    model.study("std2").feature("time").set("notsolnum", "auto");
    model.study("std2").feature("time").set("outputmap", new String[]{});
    model.study("std2").feature("time").setSolveFor("/physics/lpeq", true);
    model.study("std2").feature("time").setSolveFor("/physics/liion", true);
    model.study("std2").feature("cdi").setSolveFor("/physics/lpeq", false);
    model.study("std2").feature("cdi").setEntry("mesh", "geom1", "nomesh");
    model.study("std2").feature("time").set("tunit", "h");
    model.study("std2").feature("time").set("tlist", "range(0,0.1/C_rate,0.9/C_rate)");
    model.study("std2").feature("time").setSolveFor("/physics/lpeq", false);
    model.study("std2").feature("time").setEntry("mesh", "geom1", "nomesh");
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("t1").set("tout", "tsteps");
    model.sol("sol2").feature("t1").create("st1", "StopCondition");
    model.sol("sol2").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol2").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol2").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol2").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol2").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol2").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol2").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol2").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol2").feature("t1").feature("st1").setIndex("stopcondarr", "comp2.intop_nmc_cc(comp2.phis)<3[V]", 0);
    model.sol("sol2").feature("t1").feature("st1").set("stopcondwarn", false);

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("unit", new String[]{""});
    model.result("pg3").feature("glob1").set("expr", new String[]{"liion.phis0_ec1"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"\u8fb9\u754c\u7535\u4f4d"});
    model.result("pg3").label("\u5bf9\u5730\u8fb9\u754c\u7535\u6781\u7535\u4f4d (liion)");
    model.result("pg3").feature("glob1").set("xdatasolnumtype", "level1");
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("unit", new String[]{""});
    model.result("pg4").feature("glob1").set("expr", new String[]{"liion.soc_average_pce1"});
    model.result("pg4").feature("glob1")
         .set("descr", new String[]{"\u5e73\u5747 SOC\uff0c\u591a\u5b54\u7535\u6781 1"});
    model.result("pg4").label("\u7535\u6781\u5e73\u5747\u8377\u7535\u72b6\u6001 (liion)");
    model.result("pg4").feature("glob1").set("xdatasolnumtype", "level1");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").label("\u7535\u89e3\u8d28\u7535\u4f4d (liion)");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "x");
    model.result("pg5").feature("lngr1").selection().geom("geom2", 1);
    model.result("pg5").feature("lngr1").selection().set(1, 2);
    model.result("pg5").feature("lngr1").set("expr", new String[]{"phil"});
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (liion)");
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "x");
    model.result("pg6").feature("lngr1").selection().geom("geom2", 1);
    model.result("pg6").feature("lngr1").selection().set(1, 2);
    model.result("pg6").feature("lngr1").set("expr", new String[]{"phis"});
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("xdata", "expr");
    model.result("pg7").feature("lngr1").set("xdataexpr", "x");
    model.result("pg7").feature("lngr1").selection().geom("geom2", 1);
    model.result("pg7").feature("lngr1").selection().set(1, 2);
    model.result("pg7").feature("lngr1").set("expr", new String[]{"cl"});
    model.result("pg7").label("\u7535\u89e3\u8d28\u76d0\u6d53\u5ea6 (liion)");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().dataset().remove("dset2");
    model.result().dataset().remove("dset4");

    model.component("comp2").physics("liion").feature("pce1")
         .create("pdl1", "PorousMatrixDoubleLayerCapacitance", 1);
    model.component("comp2").physics("liion").feature("pce1").feature("pdl1").set("Cdl", "C_dl_NMC");
    model.component("comp2").physics("liion").feature("pce1").feature("pdl1")
         .set("ActiveSpecificSurfaceAreaType", "userdef");
    model.component("comp2").physics("liion").feature("pce1").feature("pdl1").set("av_dl", "Av_particles");
    model.component("comp2").physics("liion").feature().duplicate("ec2", "ec1");
    model.component("comp2").physics("liion").feature("ec2")
         .label("\u7535\u6781\u7535\u6d41 - \u8c10\u6ce2\u6270\u52a8");
    model.component("comp2").physics("liion").feature("ec2").set("Its", 0);
    model.component("comp2").physics("liion").feature("ec2").create("hp1", "HarmonicPerturbation", 0);
    model.component("comp2").physics("liion").feature("ec2").feature("hp1").set("deltaIts", "I_1C/20");

    model.param("par2").set("socinit", "socmin*0+0.5");

    model.study().create("std3");
    model.study("std3").create("frlin", "Frequencylinearized");
    model.study("std3").feature("frlin").set("plotgroup", "Default");
    model.study("std3").feature("frlin").set("ftplistmethod", "manual");
    model.study("std3").feature("frlin").set("solnum", "auto");
    model.study("std3").feature("frlin").set("notsolnum", "auto");
    model.study("std3").feature("frlin").set("outputmap", new String[]{});
    model.study("std3").feature("frlin").set("ngenAUX", "1");
    model.study("std3").feature("frlin").set("goalngenAUX", "1");
    model.study("std3").feature("frlin").set("ngenAUX", "1");
    model.study("std3").feature("frlin").set("goalngenAUX", "1");
    model.study("std3").feature("frlin").setSolveFor("/physics/lpeq", true);
    model.study("std3").feature("frlin").setSolveFor("/physics/liion", true);
    model.study("std3").feature("frlin").set("plist", "10^range(-2.6,0.2,5)");
    model.study("std3").feature("frlin").setSolveFor("/physics/lpeq", false);
    model.study("std3").feature("frlin").setEntry("mesh", "geom1", "nomesh");
    model.study("std3").create("cdi", "CurrentDistributionInitialization");
    model.study("std3").feature().move("cdi", 0);
    model.study("std3").feature("cdi").setSolveFor("/physics/lpeq", false);
    model.study("std3").feature("cdi").setEntry("mesh", "geom1", "nomesh");
    model.study("std3").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").set("data", "dset7");
    model.result("pg8").create("nyq1", "Nyquist");
    model.result("pg8").feature("nyq1").set("unit", new String[]{""});
    model.result("pg8").feature("nyq1").set("expr", new String[]{"conj(liion.Zvsgrnd_ec2) "});
    model.result("pg8").feature("nyq1").set("descr", new String[]{""});
    model.result("pg8").label("\u5bf9\u5730\u963b\u6297\uff0c\u5948\u594e\u65af\u7279 (liion)");
    model.result("pg8").feature("nyq1").setIndex("descr", "\u5bf9\u5730\u963b\u6297", 0);
    model.result("pg8").feature("nyq1").set("differential", "off");
    model.result("pg8").feature("nyq1").set("autodescr", "off");
    model.result("pg8").set("preserveaspect", "on");
    model.result("pg8").set("xlabelactive", true);
    model.result("pg8").set("xlabel", "real(Z) (\\Omega m<sup>2</sup>)");
    model.result("pg8").set("ylabelactive", true);

    return model;
  }

  public static Model run5(Model model) {
    model.result("pg8").set("ylabel", "-imag(Z) (\\Omega m<sup>2</sup>)");
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").set("data", "dset7");
    model.result("pg9").create("glob1", "Global");
    model.result("pg9").feature("glob1").set("unit", new String[]{""});
    model.result("pg9").feature("glob1").set("expr", new String[]{"real(conj(liion.Zvsgrnd_ec2)) "});
    model.result("pg9").feature("glob1").set("descr", new String[]{""});
    model.result("pg9").label("\u5bf9\u5730\u963b\u6297\uff0c\u5b9e\u90e8 (liion)");
    model.result("pg9").feature("glob1").setIndex("descr", "Impedance_vs_ground_real_part", 0);
    model.result("pg9").feature("glob1").set("differential", "off");
    model.result("pg9").feature("glob1").set("xdata", "expr");
    model.result("pg9").feature("glob1").set("xdataexpr", "freq");
    model.result("pg9").feature("glob1").set("autodescr", "off");
    model.result("pg9").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg9").set("xlog", "on");
    model.result("pg9").set("ylabelactive", true);
    model.result("pg9").set("ylabel", "real(Z) (\\Omega m<sup>2</sup>)");
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").set("data", "dset7");
    model.result("pg10").create("glob1", "Global");
    model.result("pg10").feature("glob1").set("unit", new String[]{""});
    model.result("pg10").feature("glob1").set("expr", new String[]{"imag(conj(liion.Zvsgrnd_ec2)) "});
    model.result("pg10").feature("glob1").set("descr", new String[]{""});
    model.result("pg10").label("\u5bf9\u5730\u963b\u6297\uff0c\u865a\u90e8 (liion)");
    model.result("pg10").feature("glob1").setIndex("descr", "Impedance_vs_ground_imaginary_part", 0);
    model.result("pg10").feature("glob1").set("differential", "off");
    model.result("pg10").feature("glob1").set("xdata", "expr");
    model.result("pg10").feature("glob1").set("xdataexpr", "freq");
    model.result("pg10").feature("glob1").set("autodescr", "off");
    model.result("pg10").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg10").set("xlog", "on");
    model.result("pg10").set("ylabelactive", true);
    model.result("pg10").set("ylabel", "-imag(Z) (\\Omega m<sup>2</sup>)");
    model.result("pg8").run();
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").label("\u5f02\u6784\u653e\u7535\u6570\u636e\uff08\u5df2\u5bfc\u5165\uff09");
    model.result().table("tbl3").importData("nmc_electrode_heterogeneous_discharge_data.txt");
    model.result().table().create("tbl4", "Table");
    model.result().table("tbl4").label("\u5f02\u6784 EIS \u6570\u636e\uff08\u5df2\u5bfc\u5165\uff09");
    model.result().table("tbl4").importData("nmc_electrode_heterogeneous_eis_data.txt");
    model.result("pg3").run();
    model.result("pg3").label("\u653e\u7535\u7535\u538b");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u7535\u6c60\u7535\u538b (V)");
    model.result("pg3").create("tblp1", "Table");
    model.result("pg3").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg3").feature("tblp1").set("linewidth", "preference");
    model.result("pg3").feature("tblp1").set("table", "tbl3");
    model.result("pg3").feature("tblp1").set("legend", true);
    model.result("pg3").feature("tblp1").set("legendmethod", "manual");
    model.result("pg3").feature("tblp1").setIndex("legends", "\u5f02\u6784", 0);
    model.result("pg3").run();
    model.result("pg3").feature("glob1").set("legendmethod", "manual");
    model.result("pg3").feature("glob1").setIndex("legends", "\u5747\u8d28", 0);
    model.result("pg3").run();
    model.result("pg8").run();
    model.result("pg8").set("xlabel", "Re(Z) (\\Omega m<sup>2</sup>)");
    model.result("pg8").set("ylabel", "-Im(Z) (\\Omega m<sup>2</sup>)");
    model.result("pg8").set("legendpos", "upperleft");
    model.result("pg8").create("tblp1", "Table");
    model.result("pg8").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg8").feature("tblp1").set("linewidth", "preference");
    model.result("pg8").feature("tblp1").set("table", "tbl4");
    model.result("pg8").feature("tblp1").set("xaxisdata", 3);
    model.result("pg8").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg8").feature("tblp1").set("plotcolumns", new int[]{2});
    model.result("pg8").feature("tblp1").set("legend", true);
    model.result("pg8").feature("tblp1").set("legendmethod", "manual");
    model.result("pg8").feature("tblp1").setIndex("legends", "\u5f02\u6784", 0);
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").feature("nyq1").set("legendmethod", "manual");
    model.result("pg8").feature("nyq1").setIndex("legends", "\u5747\u8d28", 0);
    model.result("pg8").run();
    model.result().dataset().remove("dset6");
    model.result().dataset().remove("dset8");

    model.study("std2").feature("cdi").set("useadvanceddisable", true);
    model.study("std2").feature("cdi").set("disabledphysics", new String[]{"liion/ec2"});
    model.study("std2").feature("time").set("useadvanceddisable", true);
    model.study("std2").feature("time").set("disabledphysics", new String[]{"liion/ec2"});
    model.study("std1").feature("stat").setSolveFor("/physics/liion", false);

    model.param("par2").set("socinit", "socmin+0.5*0");

    model.title("\u5f02\u8d28\u7535\u6781\u6a21\u578b\u5747\u8d28\u5316");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u8ba1\u7b97\u954d\u9530\u94b4 (NMC) \u7535\u6781\u7684\u5f02\u8d28\u4e09\u7ef4\u51e0\u4f55\u7684\u6709\u6548\u4f20\u9012\u5c5e\u6027\u3002\n\n\u7136\u540e\uff0c\u4f7f\u7528\u6709\u6548\u53c2\u6570\u6765\u521b\u5efa NMC \u7535\u6781\u7684\u4ee3\u8868\u6027\u4e00\u7ef4\u5747\u8d28\u6a21\u578b\u3002\u901a\u8fc7\u5747\u8d28\u5316\uff0c\u5185\u5b58\u9700\u6c42\u548c\u8ba1\u7b97\u65f6\u95f4\u90fd\u51cf\u5c11\u4e86\u51e0\u4e2a\u6570\u91cf\u7ea7\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("nmc_electrode_homogenization.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    model = run3(model);
    model = run4(model);
    run5(model);
  }

}
